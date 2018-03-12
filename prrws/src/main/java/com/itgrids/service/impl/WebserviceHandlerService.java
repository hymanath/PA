package com.itgrids.service.impl;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.itgrids.dao.IWebServiceDataDAO;
import com.itgrids.dao.IWebserviceDAO;
import com.itgrids.dto.WebserviceVO;
import com.itgrids.model.WebServiceData;
import com.itgrids.service.IWebserviceHandlerService;
import com.itgrids.service.integration.external.WebServiceUtilService;
import com.itgrids.utils.DateUtilService;
import com.itgrids.utils.IConstants;
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
	private IWebServiceDataDAO webServiceDataDAO;
	
	@Autowired
	private Gson gson;
	
	@Autowired
	private DateUtilService dateUtilService;
	
	@Autowired
	private ApplicationContext applicationContext;
	
	ExecutorService executor = Executors.newFixedThreadPool(50);
	
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
						{
							WebserviceVO webserviceVO = getResponseFromDB(webserviceId,inputData);
							if(webserviceVO != null && webserviceVO.getResponseData() != null)
								return webserviceVO.getResponseData().trim();
						}
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
						WebserviceVO webserviceVO = getResponseFromDB(webserviceId,inputData);
						if(webserviceVO != null && webserviceVO.getResponseData() != null)
							return webserviceVO.getResponseData().trim();
					}
					else if(callTypeId.equals(IConstants.WS_CALL_TYPE_DB_THEN_LIVE))
					{
						WebserviceVO webserviceVO = getResponseFromDB(webserviceId,inputData);
						
						String dataStr = null;
						Date insertionTime = null;
						boolean flag = false;
						
						if(webserviceVO != null && webserviceVO.getResponseData() != null)
						{
							dataStr = webserviceVO.getResponseData().trim();
							insertionTime = webserviceVO.getCallTime();
						}
						
						if(dataStr == null || dataStr.trim().length() == 0)
						{
							clientResponse = webServiceUtilService.callWebService(urlOrg,input,requestMethod);
						}
						
						if(insertionTime != null)
						{
							Date presentTime = dateUtilService.getCurrentDateAndTime();
							long timeDiff = (presentTime.getTime() - insertionTime.getTime());
							
							if(timeDiff > IConstants.WS_DATA_SAVE_INTERVAL)
								flag = true;
						}
						
						if(dataStr == null || flag)
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
			if(IConstants.DEFAULT_SCHEDULER_SEVER.equalsIgnoreCase(IConstants.SERVER))
			{
				executor.execute(new WebserviceDataSaveThread(url,input,requestMethod,webserviceId,inputData,IConstants.INSERT_SOURCE_LIVE,
						applicationContext.getBean("webserviceHandlerService",IWebserviceHandlerService.class),
						applicationContext.getBean("webServiceUtilService",WebServiceUtilService.class)));
			}
		}catch(Exception e)
		{
			LOG.error(e);
		}
	}
	
	public WebserviceVO getResponseFromDB(Long webserviceId,String input)
	{
		WebserviceVO webserviceVO = null;
		try{
			 List<Object[]> list = webServiceDataDAO.getWebserviceResponseData(webserviceId,input.trim());
			 
			 if(list != null && list.size() > 0)
			 {
				 webserviceVO = new WebserviceVO();
				 webserviceVO.setResponseData(list.get(0)[0] != null ? list.get(0)[0].toString().trim() : null);
				 webserviceVO.setCallTime(list.get(0)[1] != null ? (Date)list.get(0)[1] : null);
			 }
		}catch(Exception e)
		{
			LOG.error(e);
		}
		return webserviceVO;
	}
	
	public void saveWebserviceResponseData(WebserviceVO webserviceVO)
	{
		try{
			if(webserviceVO.getResponseData() != null && webserviceVO.getResponseData().trim().length() > 5)
			{
				WebServiceData webServiceData = new WebServiceData();
				webServiceData.setWebserviceId(webserviceVO.getWebserviceId());
				webServiceData.setInputData(webserviceVO.getInputData().trim());
				webServiceData.setResponceData(webserviceVO.getResponseData());
				webServiceData.setDataDate(dateUtilService.getCurrentDateAndTime());
				webServiceData.setInsertedTime(dateUtilService.getCurrentDateAndTime());
				webServiceData.setInsertSource(webserviceVO.getInsertSource());
				webServiceData.setIsDeleted("N");
				
				webServiceDataDAO.save(webServiceData);
			}
		}catch(Exception e)
		{
			LOG.error(e);
		}
	}
	
	public WebserviceVO callWebServiceForThread(String url,Object input,String requestMethod)
	{
		WebserviceVO webserviceVO = null;
		try{
			String data = callWebService(url,input,requestMethod);
			if(data != null && data.trim().length() > 0)
			{
				webserviceVO = new WebserviceVO();
				webserviceVO.setResponseData(data);
				webserviceVO.setUrl(url);
			}
		}catch(Exception e)
		{
			LOG.error(e);
		}
		return webserviceVO;
	}
	
	public void webserviceDataSaveJob()
	{
		try{
			List<Object[]> list = webServiceDataDAO.getWebserviceDataDetails();
			
			if(list != null && list.size() > 0)
			{
				LOG.fatal("Total Webservices - "+list.size());
				
				ExecutorService jobExecutor = Executors.newFixedThreadPool(100);
				for(Object[] params : list)
				{
					try{
						Long webserviceId = (Long) params[0];
						String url =  params[1].toString();
						String methodType =  params[2].toString();
						String input =  params[3] != null ? params[3].toString() : "";
						
						if(input.startsWith("\""))
							input = input.substring(1);
						
						if(input.endsWith("\""))
							input = input.substring(0,input.length()-1);
						
						String inputData =  input;
						
						if(methodType.equalsIgnoreCase(IConstants.REQUEST_METHOD_GET))
						{
							url = url+"?"+input;
							input = null;
						}
						
						jobExecutor.execute(new WebserviceDataSaveThread(url,input,methodType,webserviceId,inputData,IConstants.INSERT_SOURCE_JOB,
								applicationContext.getBean("webserviceHandlerService",IWebserviceHandlerService.class),
								applicationContext.getBean("webServiceUtilService",WebServiceUtilService.class)));
					}catch(Exception e)
					{
						LOG.error(e);
					}
				}
				jobExecutor.shutdown();
				
				try {
				    if (!jobExecutor.awaitTermination(60*9, TimeUnit.SECONDS)) {
				    	jobExecutor.shutdownNow();
				    } 
				} catch (InterruptedException e) {
					jobExecutor.shutdownNow();
				}
			}
		}catch(Exception e)
		{
			LOG.error("Exception Occured in webserviceDataSaveJob() Method - ",e);
		}
	}
}
