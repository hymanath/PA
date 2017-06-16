package com.itgrids.service.integration.external;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itgrids.dto.ErrorLogVO;
import com.itgrids.service.ILoggerService;
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

	public ClientResponse callWebService(String url,Object input)
	{
		ClientResponse response = null;
		try{
			ClientConfig clientConfig = new DefaultClientConfig();
			clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING,Boolean.TRUE);
			Client client = Client.create(clientConfig);
			WebResource resource = client.resource(url);
			response = resource.accept(javax.ws.rs.core.MediaType.APPLICATION_JSON).type(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(ClientResponse.class,input);
			
			if(response == null || response.getStatus() != 200)
			{
				LOG.error("Failed to get External Webservice, URL - "+url+" Input - "+input);
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
			
			loggerService.saveErrorLog(errorLogVO);
		}
		return response;
	}
	
	public void testMethod()
	{
		WebServiceUtilService service = new WebServiceUtilService();
		String input = "{\"year\" : \"2018\",\"fromDate\" : \"2018-01-01\",\"toDate\" : \"2018-12-31\"}";
		ClientResponse response = service.callWebService("http://dbtrd.ap.gov.in2/NregaDashBoardService/rest/CMDashBoard/Abstract", input);
		String output = response.getEntity(String.class);
	    System.out.println(output);
	}
}
