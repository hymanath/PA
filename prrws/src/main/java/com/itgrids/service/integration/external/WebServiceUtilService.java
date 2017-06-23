package com.itgrids.service.integration.external;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
@Transactional
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
	
	public ClientResponse callWebService(String url,Object input)
	{
		ClientResponse response = null;
		WebserviceVO webserviceVO = null;
		Date startTime = null;
		Date endTime = null;
		try{                                                                      
			ClientConfig clientConfig = new DefaultClientConfig();
			clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING,Boolean.TRUE);
			Client client = Client.create(clientConfig);
			WebResource resource = client.resource(url);
			
			webserviceVO = new WebserviceVO();
			webserviceVO.setUrl(url);
			startTime = dateUtilService.getCurrentDateAndTime();
			webserviceVO.setCallTime(startTime);
			
			response = resource.accept(javax.ws.rs.core.MediaType.APPLICATION_JSON).type(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(ClientResponse.class,input);
			
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
		saveWebserviceCallDetails(webserviceVO);
		return response;
	}
	
	public void testMethod()
	{
		String input = "{\"year\" : \"2018\",\"fromDate\" : \"2018-01-01\",\"toDate\" : \"2018-12-31\"}";
		ClientResponse response = callWebService("http://dbtrd.ap.gov.in2/NregaDashBoardService/rest/CMDashBoard/Abstract", input);
		String output = response.getEntity(String.class);
	    System.out.println(output);
	}
	
	public void saveWebserviceCallDetails(WebserviceVO webserviceVO)
	{
		try{
			
			WebserviceCallDetails webserviceCallDetails = new WebserviceCallDetails();
			
			if(webserviceVO.getUrl() != null)
			{
				List<Long> list = webserviceDAO.getWebserviceIdByUrl(webserviceVO.getUrl().trim());
				
				if(list != null && list.size() > 0)
					webserviceCallDetails.setWebserviceId(list.get(0));
			}
			webserviceCallDetails.setCallTime(webserviceVO.getCallTime());
			webserviceCallDetails.setStatus(webserviceVO.getStatus());
			webserviceCallDetails.setStatusCode(webserviceVO.getStatusCode());
			webserviceCallDetails.setTimeTaken(webserviceVO.getTimeTaken());
			webserviceCallDetailsDAO.save(webserviceCallDetails);
			
		}catch(Exception e)
		{
			LOG.error("Exception Occred in saveWebserviceCallDetails Method - ",e);
		}
	}
}
