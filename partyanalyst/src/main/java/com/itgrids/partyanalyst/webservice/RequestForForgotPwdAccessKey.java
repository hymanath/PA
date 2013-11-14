package com.itgrids.partyanalyst.webservice;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.itgrids.partyanalyst.service.IWebServiceHandlerService;

import com.itgrids.partyanalyst.dto.ResultStatus;
import com.opensymphony.xwork2.Action;

@Component
@Path("/RequestForForgotPwdAccessKey")
public class RequestForForgotPwdAccessKey {
	
	@Autowired
	private IWebServiceHandlerService  webServiceHandlerService;
	
	private ResultStatus resultStatus;
	
	

	public ResultStatus getResultStatus() {
		return resultStatus;
	}



	public void setResultStatus(ResultStatus resultStatus) {
		this.resultStatus = resultStatus;
	}



	
	
	public IWebServiceHandlerService getWebServiceHandlerService() {
		return webServiceHandlerService;
	}



	public void setWebServiceHandlerService(
			IWebServiceHandlerService webServiceHandlerService) {
		this.webServiceHandlerService = webServiceHandlerService;
	}



	@GET
	@Path("/sendSmsToUser/{uniqueCode}")
	@Produces(MediaType.APPLICATION_JSON)
	public String sendSms(@PathParam("uniqueCode")String uniqueCode)
	{
		String message = "";
		try{
			 resultStatus = webServiceHandlerService.sendSmsToUser(uniqueCode);
			 if(resultStatus.getResultCode() == 0)
				 message = "OK : your Access key will be delivered shortly.";
			if(resultStatus.getResultCode() == 1)
				message = "FAIL : Mobile For this user not available, contact Our Support centre.";
			if(resultStatus.getResultCode() == 121)
				message = "FAIL : Register User Not avilable,Contact our Support center.";
			}
			catch(Exception e)
			{
				e.printStackTrace();
				return "message";
			}
		return message;
	}


}
