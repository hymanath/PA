package com.itgrids.service;

import com.itgrids.dto.WebserviceVO;


public interface IWebserviceHandlerService {

	public String callWebService(String url,Object input,String requestMethod);
	
	public void saveWebserviceResponseData(WebserviceVO webserviceVO);
	
	public WebserviceVO callWebServiceForThread(String url,Object input,String requestMethod);
}
