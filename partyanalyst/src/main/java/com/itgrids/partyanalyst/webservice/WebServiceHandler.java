package com.itgrids.partyanalyst.webservice;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.jfree.util.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.VoterDetailsVO;
import com.itgrids.partyanalyst.dto.WSResultVO;
import com.itgrids.partyanalyst.service.IWebServiceHandlerService;
import com.itgrids.partyanalyst.webservice.utils.VoterTagVO;

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
		message = message.replace("_"," ");
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
	
	@GET
    @Path("/addCadreIPFlag/{type}/{voterId}/{flag}")
	@Produces(MediaType.APPLICATION_JSON)
	public String addCadreIPFlagToVoter(@PathParam("type") String type , @PathParam("voterId") String voterId, @PathParam("flag") String flag)
    {
		VoterDetailsVO voterDetails=new VoterDetailsVO();
		voterDetails=webServiceHandlerService.getVoterDetailsBasedOnVoterId(voterId);
		
		if(type.equalsIgnoreCase("cadre")){
			String str=webServiceHandlerService.saveCadreFromAndroid(voterDetails);
		}
		return null;
	}
	
	@GET
    @Path("/updateVoterDetails/{uniqueCode}/{voterId}/{casteStateId}/{mobileNumber}")
	@Produces(MediaType.APPLICATION_JSON)
	public String updateVoterDetails(@PathParam("uniqueCode") String uniqueCode ,@PathParam("voterId") Long voterId, @PathParam("casteStateId") String casteStateId, @PathParam("mobileNumber") String mobileNumber)
    {
		String status="";
		Long casteStateIdl=0l;
		if(casteStateId.equalsIgnoreCase("null")||casteStateId.trim()==""){
			casteStateIdl=0l;
		}else{
			casteStateIdl=Long.valueOf(casteStateId);
		}
		status=webServiceHandlerService.updateVoterDetails(uniqueCode,voterId,casteStateIdl,mobileNumber);
		return status;
	}
	
	
	
	
	@GET
    @Path("/updateCadreDetails/{uniqueId}/{cadreDetails}")
	@Produces(MediaType.APPLICATION_JSON)
	public String updateCadreDetails(
			@PathParam("uniqueId") String uniqueId,
			@PathParam("cadreDetails") String cadreDetails)

    {
		
		try{
			net.sf.json.JSONObject mainObj = (net.sf.json.JSONObject) net.sf.json.JSONObject
					.fromObject(cadreDetails);
			
			String voterID = mainObj.getString("voterID");
			String casteStateId = mainObj.getString("casteStateId");
			String cadreLevelId = mainObj.getString("cadreLevelId");
			String mobileNo = mainObj.getString("mobileNo");
			
			
			return webServiceHandlerService.updateCadreDetails(voterID,
					Long.parseLong(casteStateId), Long.parseLong(cadreLevelId),
					mobileNo,uniqueId);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return "Failure";
		}
	
    }
	
	@GET
    @Path("/updateIPDetails/{uniqueId}/{IPDetails}")
	@Produces(MediaType.APPLICATION_JSON)
	public String updateIPDetails(
			@PathParam("uniqueId") String uniqueId,
			@PathParam("IPDetails") String IPDetails)

    {
		
		try{
			net.sf.json.JSONObject mainObj = (net.sf.json.JSONObject) net.sf.json.JSONObject
					.fromObject(IPDetails);
			
			String voterID = mainObj.getString("voterID");
			String casteStateId = mainObj.getString("casteStateId");
			String cadreLevelId = mainObj.getString("influencingScopeId");
			String mobileNo = mainObj.getString("mobileNo");
			String partyId = mainObj.getString("partyId");
			
		   return	webServiceHandlerService.updateIPDetails(voterID,
					Long.parseLong(casteStateId), Long.parseLong(cadreLevelId),
					mobileNo,uniqueId,Long.parseLong(partyId));
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return "Failure";
		}
    }
	
	@GET
    @Path("/updateFlagsDetails/{uniqueId}/{flagsDetails}")
	@Produces(MediaType.APPLICATION_JSON)
	public String updateFlagsDetails(
			@PathParam("uniqueId") String uniqueId,
			@PathParam("flagsDetails") String flagsDetails)

    {
		
		try{
			net.sf.json.JSONObject mainObj = (net.sf.json.JSONObject) net.sf.json.JSONObject
					.fromObject(flagsDetails);
			
			String flagName = mainObj.getString("flagName");
			String flagColor = mainObj.getString("flagColor");
			String voterIds = mainObj.getString("voterIds");
			
			return webServiceHandlerService.updateFalgDetails(flagName,flagName,flagColor,voterIds);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return "Failure";
		}
    }
	
	@GET
    @Path("/updateVoterMobileNumberAndCaste/{uniqueId}/{voterID}/{casteStateId}/{mobileNo}")
	@Produces(MediaType.APPLICATION_JSON)
	public String updateVoterMobileNumberAndCaste(
			@PathParam("uniqueId") String uniqueId,
			@PathParam("voterID") String voterID,
			@PathParam("casteStateId") String casteStateId,
			@PathParam("mobileNo") String mobileNo)

    {
		
		try{
			
		   return	webServiceHandlerService.updateVoterMobileNumberAndCaste(voterID,
					Long.parseLong(casteStateId),
					mobileNo,uniqueId);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return "Failure";
		}
    }
	
	@GET
    @Path("/updateVoterTagDetails/{uniqueCode}/{voterTagDetails}")
	@Produces(MediaType.APPLICATION_JSON)
	public String updateVoterTagDetails(
			@PathParam("uniqueCode") String uniqueCode,
			@PathParam("voterTagDetails") String voterTagDetails)
    {
		try{
			Log.error("Entered into updateVoterTagDetails() Method ");
			net.sf.json.JSONObject tag = (net.sf.json.JSONObject) net.sf.json.JSONObject
					.fromObject(voterTagDetails);
			
			LOG.error("uniqueCode -- "+uniqueCode);
			LOG.error("voterTagDetails Data -- "+voterTagDetails);
			
			Long voterId = tag.getLong("voterId");
			
			if(voterId == null || uniqueCode == null || uniqueCode.trim().isEmpty())
				return "Fail";
			
			VoterTagVO voterTagVO = new VoterTagVO();
			voterTagVO.setVoterId(tag.getLong("voterId"));
			voterTagVO.setIsCadre(tag.getString("isCadre"));
			voterTagVO.setIsInfluencingPeople(tag.getString("isInfluencingPeople"));
			voterTagVO.setTags(tag.getString("tags"));
			voterTagVO.setMobileNo(tag.getString("mobileNo"));
			voterTagVO.setPartyId(tag.getLong("partyId"));
			voterTagVO.setCasteStateId(tag.getLong("casteStateId"));
			voterTagVO.setLatitude(tag.getString("latitude"));
			voterTagVO.setLongitude(tag.getString("longitude"));
			voterTagVO.setInsertTime(tag.getString("insertTime"));
			voterTagVO.setUniqueCode(uniqueCode);
			
			return webServiceHandlerService.updateVoterTagDetails(voterTagVO);
			
		}
		catch(Exception e)
		{
			LOG.error("Exception Occured in updateVoterTagDetails() Method, Exception is ",e);
			return "Fail";
		}
	
    }
	
	@GET
    @Path("/updateVoterBoothActivitiesDetails/{uniqueCode}/{voterBoothActivitiesDetails}")
	@Produces(MediaType.APPLICATION_JSON)
	public String updateVoterBoothActivitiesDetails(
			@PathParam("uniqueCode") String uniqueCode,
			@PathParam("voterBoothActivitiesDetails") String voterBoothActivitiesDetails)
    {
		try{
			Log.error("Entered into updateVoterBoothActivitiesDetails() Method ");
			net.sf.json.JSONObject tag = (net.sf.json.JSONObject) net.sf.json.JSONObject
					.fromObject(voterBoothActivitiesDetails);
			
			LOG.error("uniqueCode -- "+uniqueCode);
			LOG.error("voterBoothActivitiesDetails Data -- "+voterBoothActivitiesDetails);
			
			Long voterId = tag.getLong("voterId");
			
			if(voterId == null || uniqueCode == null || uniqueCode.trim().isEmpty())
				return "Fail";
			
			VoterTagVO voterTagVO = new VoterTagVO();
			voterTagVO.setVoterId(tag.getLong("voterId"));
			voterTagVO.setBoothId(tag.getLong("boothId"));
			voterTagVO.setBoothActivitiesId(tag.getLong("boothActivitiesId"));
			voterTagVO.setLatitude(tag.getString("latitude"));
			voterTagVO.setLongitude(tag.getString("longitude"));
			voterTagVO.setInsertTime(tag.getString("insertTime"));
			voterTagVO.setUniqueCode(uniqueCode);
			
			return webServiceHandlerService.updateVoterBoothActivitiesDetails(voterTagVO);
			
		}
		catch(Exception e)
		{
			LOG.error("Exception Occured in updateVoterTagDetails() Method, Exception is ",e);
			return "Fail";
		}
	
    }
	
	@GET
	@Path("/requestForAuthorisationAccessKey/{uniqueCode}")
	@Produces(MediaType.APPLICATION_JSON)
	public String requestForAuthorisationForAccessKey(@PathParam("uniqueCode") String uniqueCode)
	{
		
		try{
			
			return webServiceHandlerService.requestForAuthorisationAccesskey(uniqueCode);
		}
		catch(Exception e)
		{
			LOG.error("Exception Occured in requestForAuthorisationForAccessKey() Method, Exception is ",e);
			return "Fail";
		}
	}

	
	@GET
	@Path("/verificationForAuthorisationAccessKey/{uniqueCode}/{accesskey}")
	@Produces(MediaType.APPLICATION_JSON)
	public String verificationForAuthorisationAccessKey(@PathParam("uniqueCode") String uniqueCode,@PathParam("accesskey") String accesskey)
	{
		try{
			
			return webServiceHandlerService.verificationForAuthorisationAccessKey(uniqueCode,accesskey);
		}
		catch(Exception e)
		{
			LOG.error("Exception Occured in verificationForAuthorisationAccessKey() Method, Exception is ",e);
			return "Fail";
		}
	}
}
