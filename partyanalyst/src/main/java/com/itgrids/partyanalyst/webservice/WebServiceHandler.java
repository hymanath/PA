package com.itgrids.partyanalyst.webservice;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.WSResultVO;
import com.itgrids.partyanalyst.service.IWebServiceHandlerService;

@Component
@Path("/")
public class WebServiceHandler {
	
	@Autowired
	private IWebServiceHandlerService  webServiceHandlerService;
	private ResultStatus resultStatus;
	private List<WSResultVO> wSResultVO;
	private final static Logger LOG = Logger.getLogger(WebServiceHandler.class);
	
	public List<WSResultVO> getwSResultVO() {
		return wSResultVO;
	}

	public void setwSResultVO(List<WSResultVO> wSResultVO) {
		this.wSResultVO = wSResultVO;
	}

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



	/*@GET
    @Path("{userName}/{passWord}")
	@Produces(MediaType.APPLICATION_JSON)
	public String checkForUserAuthentication(  @PathParam("userName")  String userName ,  @PathParam("passWord")  String passWord )
    {
		return webServiceHandlerService.checkForUserAuthentication(userName , passWord);
    }*/
	
	
	@GET
    @Path("/getMobileAppAuthorizationURL")
	@Produces(MediaType.TEXT_PLAIN)
	public String getMobileAppAuthorizationURL()
    {
		return "http://www.partyanalyst.com/WebService/appAuthorization";
    }
	
	@GET
    @Path("/getBaseUrlForApp/{appName}")
	@Produces(MediaType.TEXT_PLAIN)
	public String getBaseUrlForApp(@PathParam("appName") String appName)
    {
		try{
			return webServiceHandlerService.getBaseUrlForApp(appName);
		}
		catch(Exception e)
		{
			LOG.error("Exception Occured, Exception is - "+e);
			return "FAIL:URL not found"; 
		}
    }
	
	
	@GET
    @Path("/appAuthorization/{userId}/{macId}")
	@Produces(MediaType.APPLICATION_JSON)
	public String checkForUserAuthentication(@PathParam("userId") String userId , @PathParam("macId") String macId )
    {
		try{
		 resultStatus = webServiceHandlerService.checkUserAuthenticationAndUpdateAuthorisedTime(userId, macId);
		if(resultStatus.getResultCode() == 0)
		 return "true";
		else
			return "false";
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return "false";
		}
    }
	


	@GET
	@Path("/requestForForgotPwdAccessKey/{uniqueCode}")
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
	
	@GET
    @Path("/validateUserAccessKey/{uniqueCode}/{pwd}/{accessKey}")
	@Produces(MediaType.APPLICATION_JSON)
	public String validateUserAccessKeyAndUpdatePwd(@PathParam("uniqueCode") String uniqueCode , @PathParam("pwd") String pwd, @PathParam("accessKey") String accessKey)
    {
		try{
		 resultStatus = webServiceHandlerService.updatePassword(uniqueCode,pwd,accessKey);
		if(resultStatus.getResultCode() == 0)
		 return "Update Successfully";
		else
			return "Failure";
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return "Failure";
		}
    }
	@GET
    @Path("/getUserVoiceRecordedFiles/{uniqueCode}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<WSResultVO> getUserVoiceRecordedFiles(@PathParam("uniqueCode") String uniqueCode)

    {
		try{
			wSResultVO = webServiceHandlerService.getUserVoiceRecordedFiles(uniqueCode);
		 if(wSResultVO == null && wSResultVO.size() == 0)
			return null;
		 else 
			return wSResultVO;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
    }
	@GET
    @Path("/sendVoiceSMS/{uniqueCode}/{mobileNos}/{fileId}")
	@Produces(MediaType.APPLICATION_JSON)
	public String sendVoiceSMS(@PathParam("uniqueCode") String uniqueCode , @PathParam("mobileNos") String mobileNos, @PathParam("fileId") String fileId)

    {
		String returnString ="";
		try{
			returnString = webServiceHandlerService.sendVoiceSMS(uniqueCode,mobileNos,fileId);
		 if(returnString.equalsIgnoreCase("data not found"))
			return " FAIL : Register User Not avilable,Contact our Support center.";
		 if(returnString.equalsIgnoreCase("Successfully Sent.."))
			 return " OK : your messages Delivered Successfully";
		 else
			 return " FAIL : you dont have credits to send voice sms"; 
				
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return "Failure";
		}
    }
	
	@GET
    @Path("/sendSMS/{uniqueCode}/{mobileNos}/{message}")
	@Produces(MediaType.APPLICATION_JSON)
	public String sendSMS(@PathParam("uniqueCode") String uniqueCode , @PathParam("mobileNos") String mobileNos, @PathParam("message") String message)

    {
		
		try{
			resultStatus = webServiceHandlerService.sendSMS(uniqueCode,mobileNos,message);
			 if(resultStatus.getResultCode() == 0)
				 message = "OK :your sms send successfully";
			if(resultStatus.getResultCode() == 1)
				 message =  "FAIL : Fail to send sms";
			if(resultStatus.getResultCode() == 121)
				 message =  "FAIL : Register User Not avilable,Contact our Support center.";
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return "Failure";
		}
		return message;
	
    }
}
