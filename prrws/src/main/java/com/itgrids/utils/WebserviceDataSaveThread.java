package com.itgrids.utils;

import org.apache.log4j.Logger;
import com.itgrids.dto.WebserviceVO;
import com.itgrids.service.IWebserviceHandlerService;
import com.itgrids.service.integration.external.WebServiceUtilService;
import com.sun.jersey.api.client.ClientResponse;

public class WebserviceDataSaveThread implements Runnable{
	
	private static final Logger LOG = Logger.getLogger(WebserviceDataSaveThread.class);
	
	private IWebserviceHandlerService webserviceHandlerService;
	private WebServiceUtilService webServiceUtilService;
	private String url;
	private Object input;
	private String requestMethod;
	private Long webserviceId;
	private String inputData;
	private String insertSource;
	
	public WebserviceDataSaveThread(String url,Object input,String requestMethod,Long webserviceId,String inputData,String insertSource,
			IWebserviceHandlerService webserviceHandlerService,WebServiceUtilService webServiceUtilService)
	{
		this.url = url;
		this.input = input;
		this.requestMethod = requestMethod;
		this.webserviceId = webserviceId;
		this.inputData = inputData;
		this.insertSource = insertSource;
		this.webserviceHandlerService = webserviceHandlerService;
		this.webServiceUtilService = webServiceUtilService;
	}
	
	public void run() 
	{
		try{
			ClientResponse clientResponse = webServiceUtilService.callWebService(url,input,requestMethod);
			
			if(clientResponse != null && clientResponse.getStatus() == 200)
			{
				WebserviceVO webserviceVO = new WebserviceVO();
				webserviceVO.setWebserviceId(webserviceId);
				webserviceVO.setInputData(inputData);
				webserviceVO.setInsertSource(insertSource);
				String resStr = clientResponse.getEntity(String.class);
				webserviceVO.setResponseData(resStr != null ? resStr.trim() : null);
				
				webserviceHandlerService.saveWebserviceResponseData(webserviceVO);
			}
			
		}catch(Exception e)
		{
			LOG.error("Exception Occured in run() Method of WebserviceDataSaveThread Class ",e);
		}
	}
}
