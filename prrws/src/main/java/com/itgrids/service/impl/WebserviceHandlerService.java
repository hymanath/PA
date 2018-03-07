package com.itgrids.service.impl;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.itgrids.dao.IWebserviceDAO;
import com.itgrids.dao.impl.WebServiceDataDAO;
import com.itgrids.dto.WebserviceVO;
import com.itgrids.model.WebServiceData;
import com.itgrids.service.IWebserviceHandlerService;
import com.itgrids.service.integration.external.WebServiceUtilService;
import com.itgrids.utils.DateUtilService;
import com.itgrids.utils.IConstants;
import com.itgrids.utils.NREGSCumulativeThread;
import com.itgrids.utils.WebserviceDataSaveThread;
import com.sun.jersey.api.client.ClientResponse;

@Service
@Transactional
public class WebserviceHandlerService implements IWebserviceHandlerService{

	private static final Logger LOG = Logger.getLogger(WebserviceHandlerService.class);
	
	@Autowired
	private IWebserviceDAO webserviceDAO;
	
	@Autowired
	private WebServiceUtilService webServiceUtilService;
	
	@Autowired
	private WebServiceDataDAO webServiceDataDAO;
	
	@Autowired
	private Gson gson;
	
	@Autowired
	private DateUtilService dateUtilService;
	
	@Autowired
	private ApplicationContext applicationContext;
	
	public String callWebService(String url,Object input,String requestMethod)
	{
		ClientResponse clientResponse = null;
		try{
			String urlOrg = url; 
			String inputGet = null;
			
			if(requestMethod.equalsIgnoreCase(IConstants.REQUEST_METHOD_GET) && url != null && url.contains("?"))
			{
				int index = url.indexOf("?");
				if(index != -1)
				{
					inputGet = url.substring(index+1);
					url = url.substring(0,index);
				}
			}
				
			List<Object[]> list = webserviceDAO.getWebserviceDetailsByUrl(url.trim());
			
			if(list == null || list.size() == 0 || list.get(0)[1] == null)
				clientResponse = webServiceUtilService.callWebService(urlOrg,input,requestMethod);
			else
			{
				Object[] params = list.get(0);
				Long webserviceId = (Long) params[0];
				Long callTypeId = (Long) params[1];
				String inputData = null;
				String inputStr = null;
				
				if(input != null)
					inputStr = gson.toJson(input);
				
				if(callTypeId.equals(IConstants.WS_CALL_TYPE_LIVE))
					clientResponse = webServiceUtilService.callWebService(url,input,requestMethod);
				
				else if(callTypeId.equals(IConstants.WS_CALL_TYPE_DB) || callTypeId.equals(IConstants.WS_CALL_TYPE_LIVE_OR_DB) || 
						callTypeId.equals(IConstants.WS_CALL_TYPE_DB_THEN_LIVE))
				{
					if(requestMethod.equalsIgnoreCase(IConstants.REQUEST_METHOD_POST))
						inputData = webServiceUtilService.formatJsonString(inputStr);
					else if(requestMethod.equalsIgnoreCase(IConstants.REQUEST_METHOD_GET))
						inputData = inputGet;
					
					if(callTypeId.equals(IConstants.WS_CALL_TYPE_LIVE_OR_DB))
					{
						clientResponse = webServiceUtilService.callWebService(urlOrg,input,requestMethod);
						if(clientResponse == null || clientResponse.getStatus() != 200)
							return getResponseFromDB(webserviceId,inputData);
						else
						{
							WebserviceVO webserviceVO = new WebserviceVO();
							webserviceVO.setWebserviceId(webserviceId);
							webserviceVO.setInputData(inputData);
							String resStr = clientResponse.getEntity(String.class);
							webserviceVO.setResponseData(resStr != null ? resStr.trim() : null);
							
							saveWebserviceResponseData(webserviceVO);
							return resStr;
						}
					}
					else if(callTypeId.equals(IConstants.WS_CALL_TYPE_DB))
					{
						return getResponseFromDB(webserviceId,inputData);
					}
					else if(callTypeId.equals(IConstants.WS_CALL_TYPE_DB_THEN_LIVE))
					{
						String dataStr = getResponseFromDB(webserviceId,inputData);
						
						if(dataStr == null || dataStr.trim().length() == 0)
						{
							clientResponse = webServiceUtilService.callWebService(urlOrg,input,requestMethod);
						}
						saveWebserviceDataUsingThread(urlOrg,input,requestMethod,webserviceId,inputData);
						
						if(dataStr != null && dataStr.trim().length() > 0)
							return dataStr;
					}
				}
				else
					clientResponse = webServiceUtilService.callWebService(urlOrg,input,requestMethod);
			}
			
		}catch(Exception e)
		{
			LOG.error(e);
		}
		
		if(clientResponse != null && clientResponse.getStatus() == 200)
			return clientResponse.getEntity(String.class);
		else
			return null;
	}
	
	public void saveWebserviceDataUsingThread(String url,Object input,String requestMethod,Long webserviceId,String inputData)
	{
		try{
			ExecutorService executor = Executors.newSingleThreadExecutor();
			
			executor.execute(new WebserviceDataSaveThread(url,input,requestMethod,webserviceId,inputData,
					applicationContext.getBean("webserviceHandlerService",IWebserviceHandlerService.class),
					applicationContext.getBean("webServiceUtilService",WebServiceUtilService.class)));
			
		}catch(Exception e)
		{
			LOG.error(e);
		}
	}
	
	public String getResponseFromDB(Long webserviceId,String input)
	{
		try{
			return webServiceDataDAO.getWebserviceResponseData(webserviceId,input);
		}catch(Exception e)
		{
			LOG.error(e);
		}
		return null;
	}
	
	public void saveWebserviceResponseData(WebserviceVO webserviceVO)
	{
		try{
			WebServiceData webServiceData = new WebServiceData();
			webServiceData.setWebserviceId(webserviceVO.getWebserviceId());
			webServiceData.setInputData(webserviceVO.getInputData());
			webServiceData.setResponceData(webserviceVO.getResponseData());
			webServiceData.setDataDate(dateUtilService.getCurrentDateAndTime());
			webServiceData.setInsertedTime(dateUtilService.getCurrentDateAndTime());
			webServiceData.setIsDeleted("N");
			
			webServiceDataDAO.save(webServiceData);
		}catch(Exception e)
		{
			LOG.error(e);
		}
	}
	
}
