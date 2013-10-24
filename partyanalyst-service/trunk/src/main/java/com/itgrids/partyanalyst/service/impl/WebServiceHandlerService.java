package com.itgrids.partyanalyst.service.impl;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.service.ILoginService;
import com.itgrids.partyanalyst.service.IWebServiceHandlerService;

public class WebServiceHandlerService implements IWebServiceHandlerService {
	
	private static final Logger log = Logger.getLogger(WebServiceHandlerService.class);
	
	private ILoginService loginService;

	
	
	public ILoginService getLoginService() {
		return loginService;
	}



	public void setLoginService(ILoginService loginService) {
		this.loginService = loginService;
	}



	public String checkForUserAuthentication(String userName , String passWord)
	{
		log.debug("Entered into the checkForUserAuthentication  method in WebServiceHandlerService");
		try
		{
			
		}catch(Exception e)
		{
			log.error("Exception raised in checkForUserAuthentication  method in WebServiceHandlerService");
			e.printStackTrace();
		}
		return "error";
	}


}
