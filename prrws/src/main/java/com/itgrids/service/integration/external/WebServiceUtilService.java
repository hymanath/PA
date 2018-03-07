package com.itgrids.service.integration.external;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.itgrids.dao.IWebserviceCallDetailsDAO;
import com.itgrids.dao.IWebserviceDAO;
import com.itgrids.dto.ErrorLogVO;
import com.itgrids.dto.WebserviceVO;
import com.itgrids.model.WebserviceCallDetails;
import com.itgrids.service.ILoggerService;
import com.itgrids.utils.DateUtilService;
import com.itgrids.utils.IConstants;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;

@Service
public class WebServiceUtilService {
	
	private static Logger LOG = Logger.getLogger(WebServiceUtilService.class);
	
	@Autowired
	private ILoggerService loggerService;
	
	@Autowired
	private DateUtilService dateUtilService;
	
	@Autowired
	private IWebserviceDAO webserviceDAO;
	
	@Autowired
	private IWebserviceCallDetailsDAO webserviceCallDetailsDAO;
	
	public ClientResponse callWebService(String url,Object input,String requestMethod)
	{
		ClientResponse response = null;
		WebserviceVO webserviceVO = null;
		Date startTime = null;
		Date endTime = null;
		Gson gson = new Gson();
		try{                                                                      
			ClientConfig clientConfig = new DefaultClientConfig();
			clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING,Boolean.TRUE);
			Client client = Client.create(clientConfig);
			client.setConnectTimeout(1000*60*2);
		    client.setReadTimeout(1000*60*3);
			WebResource resource = client.resource(url);
			
			webserviceVO = new WebserviceVO();
			webserviceVO.setUrl(url);
			startTime = dateUtilService.getCurrentDateAndTime();
			webserviceVO.setCallTime(startTime);
			
			if(url != null && url.contains("?"))
			{
				int index = url.indexOf("?");
				webserviceVO.setUrl(url.substring(0,index));
				webserviceVO.setInputData(url.substring(index+1));
			}
			
			if(input != null)
				webserviceVO.setInputData(gson.toJson(input));
			
			webserviceVO.setWebserviceTrackId(saveWebserviceCallDetails(webserviceVO));
			
			if(requestMethod.equalsIgnoreCase(IConstants.REQUEST_METHOD_POST))
				response = resource.accept(javax.ws.rs.core.MediaType.APPLICATION_JSON).type(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(ClientResponse.class,input);
			else if(requestMethod.equalsIgnoreCase(IConstants.REQUEST_METHOD_GET))
				response = resource.accept(javax.ws.rs.core.MediaType.APPLICATION_JSON).type(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(ClientResponse.class);
			
			endTime = dateUtilService.getCurrentDateAndTime();
			
			if(response == null || response.getStatus() != 200)
			{
				LOG.error("Failed to get External Webservice, URL - "+url+" Input - "+input);
				webserviceVO.setStatus(IConstants.STATUS_FAIL);
			}
			else
			{
				webserviceVO.setStatus(IConstants.STATUS_SUCCESS);
			}
			
			
		}catch(Exception e)
		{
			LOG.error("Exception Occured in calling Webservice, URL - "+url+" Input - "+input+" Exception - ",e);
			
			ErrorLogVO errorLogVO = new ErrorLogVO();
			errorLogVO.setInputUrl(url);
			errorLogVO.setInputJson(input != null ? input.toString() : null);
			errorLogVO.setStatusCode(response != null ? response.getStatus() : null);
			errorLogVO.setResponse(response != null ? response.getEntity(String.class) : null);
			errorLogVO.setExceptionMsg(e.getLocalizedMessage());
			errorLogVO.setExceptionStack(org.apache.commons.lang.exception.ExceptionUtils.getStackTrace(e));
			
			webserviceVO.setStatus(IConstants.STATUS_FAIL);
			endTime = dateUtilService.getCurrentDateAndTime();
			
			loggerService.saveErrorLog(errorLogVO);
		}
		
		try{
			webserviceVO.setTimeTaken(endTime.getTime()-startTime.getTime());
		}catch(Exception e)
		{
			LOG.error(e);
		}
		
		webserviceVO.setStatusCode(response != null ? new Long(response.getStatus()) : null);
		updateWebserviceCallDetails(webserviceVO);
		return response;
	}
	
	public void testMethod()
	{
		String input = "{\"year\" : \"2018\",\"fromDate\" : \"2018-01-01\",\"toDate\" : \"2018-12-31\"}";
		ClientResponse response = callWebService("http://dbtrd.ap.gov.in2/NregaDashBoardService/rest/CMDashBoard/Abstract", input,IConstants.REQUEST_METHOD_POST);
		String output = response.getEntity(String.class);
	    System.out.println(output);
	}
	
	@Transactional
	public Long saveWebserviceCallDetails(WebserviceVO webserviceVO)
	{
		try{
			
			WebserviceCallDetails webserviceCallDetails = new WebserviceCallDetails();
			
			if(webserviceVO.getUrl() != null)
			{
				List<Long> list = webserviceDAO.getWebserviceIdByUrl(webserviceVO.getUrl().trim());
				
				if(list != null && !list.isEmpty())
					webserviceCallDetails.setWebserviceId(list.get(0));
				else
					webserviceCallDetails.setUrl(webserviceVO.getUrl().trim());
			}
			webserviceCallDetails.setCallTime(webserviceVO.getCallTime());
			webserviceCallDetails.setInputData(webserviceVO.getInputData());
			webserviceCallDetails = webserviceCallDetailsDAO.save(webserviceCallDetails);
			
			return webserviceCallDetails.getWebserviceCallDetailsId();
		}catch(Exception e)
		{
			//LOG.error("Exception Occured in saveWebserviceCallDetails Method - ",e);
		}
		return null;
	}
	
	@Transactional
	public void updateWebserviceCallDetails(WebserviceVO webserviceVO)
	{
		try{
			if(webserviceVO.getWebserviceTrackId() == null)
				return;
			
			WebserviceCallDetails webserviceCallDetails = webserviceCallDetailsDAO.get(webserviceVO.getWebserviceTrackId());
			webserviceCallDetails.setStatus(webserviceVO.getStatus());
			webserviceCallDetails.setStatusCode(webserviceVO.getStatusCode());
			webserviceCallDetails.setTimeTaken(webserviceVO.getTimeTaken());
			webserviceCallDetailsDAO.save(webserviceCallDetails);
			
		}catch(Exception e)
		{
			//LOG.error("Exception Occured in saveWebserviceCallDetails Method - ",e);
		}
	}
	
	public ClientResponse createClientResponseFromString(String responseData)
	{
		ClientResponse clientResponse = null;
		try{
			clientResponse = new ClientResponse(200,null,new ByteArrayInputStream(responseData.getBytes(StandardCharsets.UTF_8)),null);
		}catch(Exception e)
		{
			LOG.error(e);
		}
		return clientResponse;
	}
	
	public String formatJsonString(String jsonStr)
	{
		try{
			jsonStr = jsonStr.replaceAll("\n","");
			jsonStr = jsonStr.replace("\\\"","\"");
			jsonStr = jsonStr.replace(" : ",":");
		}catch(Exception e)
		{
			LOG.error(e);
		}
		return jsonStr;
	}
}
