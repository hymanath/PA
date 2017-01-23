package com.itgrids.partyanalyst.webservice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.itgrids.partyanalyst.dto.AccommodationVO;
import com.itgrids.partyanalyst.dto.ActionableVO;
import com.itgrids.partyanalyst.dto.ActivityAttendanceVO;
import com.itgrids.partyanalyst.dto.ActivityLoginVO;
import com.itgrids.partyanalyst.dto.ActivityWSVO;
import com.itgrids.partyanalyst.dto.AlertCommentVO;
import com.itgrids.partyanalyst.dto.AlertCoreDashBoardVO;
import com.itgrids.partyanalyst.dto.AlertOverviewVO;
import com.itgrids.partyanalyst.dto.AlertVO;
import com.itgrids.partyanalyst.dto.AttendanceQuestionnariWSVO;
import com.itgrids.partyanalyst.dto.AttendanceTabUserVO;
import com.itgrids.partyanalyst.dto.AttendanceVO;
import com.itgrids.partyanalyst.dto.BasicVO;
import com.itgrids.partyanalyst.dto.CadreAddressVO;
import com.itgrids.partyanalyst.dto.CadreBasicVO;
import com.itgrids.partyanalyst.dto.CadreCommitteeMemberVO;
import com.itgrids.partyanalyst.dto.CadreOverviewVO;
import com.itgrids.partyanalyst.dto.CadrePrintInputVO;
import com.itgrids.partyanalyst.dto.CadreTabRecordsStatusVO;
import com.itgrids.partyanalyst.dto.CadreTravelsVO;
import com.itgrids.partyanalyst.dto.CadreVoterVO;
import com.itgrids.partyanalyst.dto.CardNFCDetailsVO;
import com.itgrids.partyanalyst.dto.CardPrintUserVO;
import com.itgrids.partyanalyst.dto.CasteDetailsVO;
import com.itgrids.partyanalyst.dto.EffectedBoothsResponse;
import com.itgrids.partyanalyst.dto.GISIssuesVO;
import com.itgrids.partyanalyst.dto.GISUserTrackingVO;
import com.itgrids.partyanalyst.dto.GISVisualizationDetailsVO;
import com.itgrids.partyanalyst.dto.GISVisualizationParameterVO;
import com.itgrids.partyanalyst.dto.IdAndNameVO;
import com.itgrids.partyanalyst.dto.ImageVO;
import com.itgrids.partyanalyst.dto.InviteesVO;
import com.itgrids.partyanalyst.dto.KeyValueVO;
import com.itgrids.partyanalyst.dto.MissedCallCampaignVO;
import com.itgrids.partyanalyst.dto.MobileAppUserSmsStatusVO;
import com.itgrids.partyanalyst.dto.MobileAppUserVO;
import com.itgrids.partyanalyst.dto.MobileAppUserVoterVO;
import com.itgrids.partyanalyst.dto.NotificationDeviceVO;
import com.itgrids.partyanalyst.dto.NtrTrustStudentVO;
import com.itgrids.partyanalyst.dto.PartyMeetingInviteeVO;
import com.itgrids.partyanalyst.dto.PartyMeetingVO;
import com.itgrids.partyanalyst.dto.PartyMeetingWSVO;
import com.itgrids.partyanalyst.dto.PashiAppNoCadreVO;
import com.itgrids.partyanalyst.dto.PeshiAppAppointmentVO;
import com.itgrids.partyanalyst.dto.PeshiAppGrievanceVO;
import com.itgrids.partyanalyst.dto.PeshiAppLoginVO;
import com.itgrids.partyanalyst.dto.PollManagementVO;
import com.itgrids.partyanalyst.dto.RegisteredMembershipCountVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SurveyTrainingsVO;
import com.itgrids.partyanalyst.dto.TdpCadreVO;
import com.itgrids.partyanalyst.dto.TdpCadreWSVO;
import com.itgrids.partyanalyst.dto.UnionTabUserVO;
import com.itgrids.partyanalyst.dto.UserAttendanceDetailsVO;
import com.itgrids.partyanalyst.dto.UserDetailsVO;
import com.itgrids.partyanalyst.dto.UserEventDetailsVO;
import com.itgrids.partyanalyst.dto.VoterDetailsVO;
import com.itgrids.partyanalyst.dto.WSResultVO;
import com.itgrids.partyanalyst.service.IAttendanceService;
import com.itgrids.partyanalyst.service.ICoreDashboardCadreRegistrationService;
import com.itgrids.partyanalyst.service.INotificationService;
import com.itgrids.partyanalyst.service.ISmsSenderService;
import com.itgrids.partyanalyst.service.IWebServiceHandlerService;
import com.itgrids.partyanalyst.utils.CommonUtilsService;
import com.itgrids.partyanalyst.webservice.android.abstractservice.IWebServiceHandlerService1;
import com.itgrids.partyanalyst.webservice.utils.VoterTagVO;
import com.itgrids.partyanalyst.webserviceutils.android.utilvos.UserLocationTrackingVo;
@Component
@Path("/")
public class WebServiceHandler {
	
	@Autowired
	private IWebServiceHandlerService  webServiceHandlerService;
	private ResultStatus resultStatus;
	private List<WSResultVO> wSResultVO;
	private WSResultVO wSResult;
	private final static Logger LOG = Logger.getLogger(WebServiceHandler.class);
	private List<CasteDetailsVO> casteDetailsVO;
	
	@Autowired
	private CommonUtilsService commonUtilsService;
	@Autowired 
	private IWebServiceHandlerService1 webServiceHandlerService1;
	private MissedCallCampaignVO MissedCallCampaignVO;
	private List<CadreAddressVO> cadreAddressVOList;
	@Autowired
	private ISmsSenderService smsSenderService;
	
	@Autowired
	private IAttendanceService attendanceService;
	@Autowired
	private INotificationService notificationService;
	
	@Autowired
	private ICoreDashboardCadreRegistrationService coreDashboardCadreRegistrationService;
	
	private PeshiAppLoginVO peshiAppLoginVO;
	private PeshiAppGrievanceVO peshiAppGrievanceVO;
	private PeshiAppAppointmentVO peshiAppAppointmentVO;
	private PashiAppNoCadreVO pashiAppNoCadreVO;
	
	
	public PashiAppNoCadreVO getPashiAppNoCadreVO() {
		return pashiAppNoCadreVO;
	}

	public void setPashiAppNoCadreVO(PashiAppNoCadreVO pashiAppNoCadreVO) {
		this.pashiAppNoCadreVO = pashiAppNoCadreVO;
	}

	public PeshiAppAppointmentVO getPeshiAppAppointmentVO() {
		return peshiAppAppointmentVO;
	}

	public void setPeshiAppAppointmentVO(PeshiAppAppointmentVO peshiAppAppointmentVO) {
		this.peshiAppAppointmentVO = peshiAppAppointmentVO;
	}

	public PeshiAppGrievanceVO getPeshiAppGrievanceVO() {
		return peshiAppGrievanceVO;
	}

	public void setPeshiAppGrievanceVO(PeshiAppGrievanceVO peshiAppGrievanceVO) {
		this.peshiAppGrievanceVO = peshiAppGrievanceVO;
	}

	public PeshiAppLoginVO getPeshiAppLoginVO() {
		return peshiAppLoginVO;
	}

	public void setPeshiAppLoginVO(PeshiAppLoginVO peshiAppLoginVO) {
		this.peshiAppLoginVO = peshiAppLoginVO;
	}

	public List<CadreAddressVO> getCadreAddressVOList() {
		return cadreAddressVOList;
	}

	public void setCadreAddressVOList(List<CadreAddressVO> cadreAddressVOList) {
		this.cadreAddressVOList = cadreAddressVOList;
	}

	public MissedCallCampaignVO getMissedCallCampaignVO() {
		return MissedCallCampaignVO;
	}

	public void setMissedCallCampaignVO(MissedCallCampaignVO missedCallCampaignVO) {
		MissedCallCampaignVO = missedCallCampaignVO;
	}

	public CommonUtilsService getCommonUtilsService() {
		return commonUtilsService;
	}

	public void setCommonUtilsService(CommonUtilsService commonUtilsService) {
		this.commonUtilsService = commonUtilsService;
	}

	

	public List<CasteDetailsVO> getCasteDetailsVO() {
		return casteDetailsVO;
	}

	public void setCasteDetailsVO(List<CasteDetailsVO> casteDetailsVO) {
		this.casteDetailsVO = casteDetailsVO;
	}

	public WSResultVO getwSResult() {
		return wSResult;
	}

	public void setwSResult(WSResultVO wSResult) {
		this.wSResult = wSResult;
	}

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
		 if(wSResultVO == null || wSResultVO.size() == 0)
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
		String messageInfo = message.replace("_"," ");
		try{
			resultStatus = webServiceHandlerService.sendSMS(uniqueCode,mobileNos,messageInfo);
			 if(resultStatus.getResultCode() == 0)
				 messageInfo = "OK :your sms send successfully";
			if(resultStatus.getResultCode() == 1)
				messageInfo =  "FAIL : Fail to send sms";
			if(resultStatus.getResultCode() == 121)
				messageInfo =  "FAIL : Register User Not avilable,Contact our Support center.";
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return "Failure";
		}
		return messageInfo;
	
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
			LOG.error("Entered into updateVoterTagDetails() Method ");
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
			LOG.error("Entered into updateVoterBoothActivitiesDetails() Method ");
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
	
	@GET
	@Path("/getSample/{constituencyId}")
	@Produces(MediaType.APPLICATION_JSON)
	public EffectedBoothsResponse getInfectedBoothsList(@PathParam("constituencyId") Long constituencyId) {
		try{
			return webServiceHandlerService.getInfectedBoothsOfConstituency(constituencyId);
		}
		catch(Exception e)
		{
			LOG.error("Exception Occured in verificationForAuthorisationAccessKey() Method, Exception is ",e);
			return null;
		}
		
		
 
	}
	
	
	@GET
	@Path("/loginFieldDataUser/{userName}/{passWord}")
	@Produces(MediaType.APPLICATION_JSON)
	public WSResultVO loginFieldDataUser (@PathParam("userName") String uname,@PathParam("passWord") String pwd)
	{
		try{
			
			wSResult =  webServiceHandlerService.getLoginFieldDataUser(uname,pwd);
			return wSResult;
		}
		catch(Exception e)
		{
			LOG.error("Exception Occured in loginFieldDataUser() Method,Exception is ",e);
		    return null;
		}
		
	}

	/*@GET
	@Path("/getCadreDetailsByPanchayat")
	@Produces(MediaType.TEXT_PLAIN)
	public Object getCadreDetailsByPanchayat (Long panchayatId)
	{
		Object object = null;
		object=(List<BasicVO>) webServiceHandlerService.getVCadreDataByPanchayatId(panchayatId);
		return object;
		
		
	}*/
	
	@GET
    @Path("/getCadreDetailsByPanchayat/{panchayatId}")
	@Produces(MediaType.TEXT_PLAIN)
	public Object getCadreDetailsByPanchayat(@PathParam("panchayatId") String panchayatId)
    {
		Object object = null;
		object=(List<BasicVO>) webServiceHandlerService.getVCadreDataByPanchayatId(Long.valueOf(panchayatId));
		return object;
    }
	
	
	@GET
	@Path("/getVCadreDataByPanchayatId/{uniqueCode}")
	@Produces(MediaType.APPLICATION_JSON)
	public Object getVCadreDataByPanchayatId(@PathParam("uniqueCode") String uniqueCode)
	{
		
		try{
			
			//return webServiceHandlerService.requestForAuthorisationAccesskey(uniqueCode);
			Object object = null;
			object= webServiceHandlerService.getVCadreDataByPanchayatId(Long.valueOf(uniqueCode));
			return object;
		}
		catch(Exception e)
		{
			LOG.error("Exception Occured in requestForAuthorisationForAccessKey() Method, Exception is ",e);
			return "Fail";
		}
	}
	
	@GET
	@Path("/tagCardIdForNFCReader/{uniqueCode}/{voterId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Object tagCardIdForNFCReader(@PathParam("uniqueCode") String uniqueCode,@PathParam("voterId") String voterId)
	{
		
		try{

			Object status= webServiceHandlerService.tagCardIdForNFCReader(uniqueCode,Long.valueOf(voterId));
			return status;
		}
		catch(Exception e)
		{
			LOG.error("Exception Occured in requestForAuthorisationForAccessKey() Method, Exception is ",e);
			return "Fail";
		}
	}
	
	@GET
	@Path("/getCadreDetailsForPrinting/{uniqueCode}")
	@Produces(MediaType.APPLICATION_JSON)
	public Object getCadreDetailsForPrinting(@PathParam("uniqueCode") String uniqueCode)
	{
		
		try{
			
			//return webServiceHandlerService.requestForAuthorisationAccesskey(uniqueCode);
			Object object = null;
			object= webServiceHandlerService.getCadreDetailsForPrinting(uniqueCode);
			return object;
		}
		catch(Exception e)
		{
			LOG.error("Exception Occured in requestForAuthorisationForAccessKey() Method, Exception is ",e);
			return "Fail";
		}
	}
	
	@GET
	@Path("/checkNFCNumberForVoterId/{voterId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Object checkNFCNumberForVoterId(@PathParam("voterId") String voterId)
	{
		
		try{

			Object object= webServiceHandlerService.checkNFCNumberForVoterId(Long.valueOf(voterId));
			return object;
		}
		catch(Exception e)
		{
			LOG.error("Exception Occured in requestForAuthorisationForAccessKey() Method, Exception is ",e);
			return "Fail";
		}
	}
	
	@GET
	@Path("/delinkNFCNumber/{uniqueCode}/{voterId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Object delinkNFCNumber(@PathParam("uniqueCode") String uniqueCode,@PathParam("voterId") String voterId)
	{
		
		try{

			Object status= webServiceHandlerService.delinkNFCNumber(uniqueCode,Long.valueOf(voterId));
			return status;
		}
		catch(Exception e)
		{
			LOG.error("Exception Occured in requestForAuthorisationForAccessKey() Method, Exception is ",e);
			return "Fail";
		}
	}
	

	@GET
	@Path("/getAllCastes")
	@Produces(MediaType.APPLICATION_JSON)
	public List<CasteDetailsVO> getAllCastes()
	{
		
		try{

			casteDetailsVO = webServiceHandlerService.getAllCastes();
			return casteDetailsVO;
		}
		catch(Exception e)
		{
			LOG.error("Exception Occured in getAllCastes() Method, Exception is ",e);
			return null;
		}
	}
	
	@GET
	@Path("/getVCadreDataByPanchayatId1/{panchayatId}/{type}")
	@Produces(MediaType.APPLICATION_JSON)
	public Object getVCadreDataByPanchayatId1(@PathParam("panchayatId") String panchayatId,@PathParam("type") String type)
	{
		
		try{
			
			//return webServiceHandlerService.requestForAuthorisationAccesskey(uniqueCode);
			Object object = null;
			object= webServiceHandlerService.getVCadreDataByPanchayatId1(Long.valueOf(panchayatId),type);
			return object;
		}
		catch(Exception e)
		{
			LOG.error("Exception Occured in requestForAuthorisationForAccessKey() Method, Exception is ",e);
			return "Fail";
		}
	}
	
	@POST
	@Path("/getVCadreDataBySelection")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Object getVCadreDataBySelection(CadrePrintInputVO inputVO)
	{
		
		try{
			
			System.out.println(inputVO);
			//return webServiceHandlerService.requestForAuthorisationAccesskey(uniqueCode);
			Object object = null;
			  object = webServiceHandlerService.getVCadreDetailsBySelection(inputVO);
			//object= webServiceHandlerService.getVCadreDataByPanchayatId1(Long.valueOf(panchayatId),type);
			return object;
		}
		catch(Exception e)
		{
			LOG.error("Exception Occured in requestForAuthorisationForAccessKey() Method, Exception is ",e);
			return "Fail";
		}
	}
	
	@POST
	@Path("/getTDPCadreDetailsBySearch")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Object getTDPCadreDetailsBySearch(CadrePrintInputVO inputVO){
		
		try{
			
			if(inputVO==null){
				return "Inputs Are Empty";
			}
			
			
			Object object = null;
			object = webServiceHandlerService.getTDPCadreDetailsBySearch(inputVO);
			return object;
		}
		catch(Exception e)
		{
			LOG.error("Exception Occured in requestForAuthorisationForAccessKey() Method, Exception is ",e);
			return "Fail";
		}
	}
	
	@POST
	@Path("/getTDPCadreDetailsForSearch")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Object getTDPCadreDetailsForSearch(CadrePrintInputVO inputVO){
		
		try{
			
			if(inputVO==null){
				return "Inputs Are Empty";
			}
			
			
			Object object = null;
			object = webServiceHandlerService.getTDPCadreDetailsForSearch(inputVO);
			return object;
		}
		catch(Exception e)
		{
			LOG.error("Exception Occured in requestForAuthorisationForAccessKey() Method, Exception is ",e);
			return "Fail";
		}
	}
	
	@POST
	@Path("/getTDPCadreDetailsByMemberShip")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Object getTDPCadreDetailsByMemberShip(CadrePrintInputVO inputVO){
		
		try{
			
			if(inputVO==null){
				return "Inputs Are Empty";
			}
			
			
			Object object = null;
			object = webServiceHandlerService.getTDPCadreDetailsByMemberShip(inputVO);
			return object;
		}
		catch(Exception e)
		{
			LOG.error("Exception Occured in requestForAuthorisationForAccessKey() Method, Exception is ",e);
			return "Fail";
		}
	}
	
	@POST
	@Path("/updatePrintedCardDetails")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Object updatePrintedCardDetails(List<CardNFCDetailsVO> inputVOList){
		
		try{
			Object object = null;
			object = webServiceHandlerService.updatePrintedCardDetails(inputVOList);
			return object;
		}
		catch(Exception e)
		{
			LOG.error("Exception Occured in requestForAuthorisationForAccessKey() Method, Exception is ",e);
			return "Fail";
		}
	}
	@POST
	@Path("/updatePrintedCardInfo")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Object updatePrintedCardInfo(List<CardNFCDetailsVO> inputVOList){
		
		try{
			Object object = null;
			object = webServiceHandlerService.updatePrintedCardInfo(inputVOList);
			return object;
		}
		catch(Exception e)
		{
			LOG.error("Exception Occured in requestForAuthorisationForAccessKey() Method, Exception is ",e);
			return "Fail";
		}
	}
	
	@POST
	@Path("/getCardPrintCountForUsers")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Object getCardPrintUsersCount(CardPrintUserVO inputVO){
		
		try{
			Object object = null;
			object = webServiceHandlerService.getCardPrintCountForAllUsers(inputVO);
			return object;
		}
		catch(Exception e)
		{
			LOG.error("Exception Occured in requestForAuthorisationForAccessKey() Method, Exception is ",e);
			return "Fail";
		}
	}
	
	@POST
	@Path("/getCardPrintCountByUser")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Object getCardPrintCountByUser(CardPrintUserVO inputVO){
		
		try{
			Object object = null;
			object = webServiceHandlerService.getCardPrintCountByUser(inputVO);
			return object;
		}
		catch(Exception e)
		{
			LOG.error("Exception Occured in requestForAuthorisationForAccessKey() Method, Exception is ",e);
			return "Fail";
		}
	}
	
	@POST
	@Path("/cadreSurveyUserDetails")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Object getCadreSurveyUserDetails(List<UserDetailsVO> id){
		
		try{
			Object object = null;
			object = webServiceHandlerService.getCadreSurveyUserDetails(id);
			return object;
		}
		catch(Exception e)
		{
			LOG.error("Exception Occured in requestForAuthorisationForAccessKey() Method, Exception is ",e);
			return "Fail";
		}
	}
	
	/*@POST
	@Path("/Auth/validateMembership")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Boolean checkMembershipExists(CadreTravelsVO inputVO){
		
		try{
			
			return commonUtilsService.checkValidMember(inputVO.getMembershipNo());
			
		}
		catch(Exception e)
		{
			LOG.error("Exception Occured in checkMembershipExists() Method, Exception is ",e);
			return false;
		}
	}*/
	
	/*@POST
	@Path("/Auth/getMobileNo")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Object getMobileNoByMemberShip(CadreTravelsVO inputVO){
		
		try{
			Object object = null;
			object = webServiceHandlerService.getMobileNoByMemberShip(inputVO.getMembershipNo());
			return object;
		}
		catch(Exception e)
		{
			LOG.error("Exception Occured in getMobileNoByMemberShip() Method, Exception is ",e);
			return "false";
		}
	}
	*/
	/*@POST
	@Path("/Auth/getMemberData")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public CadreAddressVO getMemberDataByMemberShip(CadreTravelsVO inputVO){
		
		try{
			
			cadreAddressVO = webServiceHandlerService.getMemberDataByMemberShip(inputVO.getMembershipNo(),inputVO.getIsAddress());
		
		}
		catch(Exception e)
		{
			LOG.error("Exception Occured in getMobileNoByMemberShip() Method, Exception is ",e);
			e.printStackTrace();
		}
		return cadreAddressVO;
	}*/

	@POST
	@Path("/Auth/updateCadreTravelDiscountDetails")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Object updateCadreTravelDiscountDetails(CadreTravelsVO inputVO)
	{		
		try{
			Object object = null;
			object = webServiceHandlerService.updateCadreTravelDiscountDetails(inputVO);
			return object;
		}
		catch(Exception e){
			LOG.error("Exception Occured in requestForAuthorisationForAccessKey() Method, Exception is ",e);
			return "Fail";
		}
	}
	
	@POST
	@Path("/Auth/cancellationOfTicketDetails")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Object cancellationOfTicketDetails(CadreTravelsVO inputVO)
	{		
		try{
			Object object = null;
			object = webServiceHandlerService.cancellationOfTicketDetails(inputVO);
			return object;
		}
		catch(Exception e){
			LOG.error("Exception Occured in requestForAuthorisationForAccessKey() Method, Exception is ",e);
			return "Fail";
		}
	}

	@POST
	@Path("/Auth/searchTdpCadreDetailsBySearchCriteriaForCallCenter/{memberShipCardNo}/{mobileNo}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Object searchTdpCadreDetailsBySearchCriteriaForCallCenter(@PathParam("memberShipCardNo") String memberShipCardNo,@PathParam("mobileNo") String mobileNo)
	{
		try{			
			return webServiceHandlerService1.searchTdpCadreDetailsBySearchCriteria("0","",memberShipCardNo,"","",mobileNo);			
		}
		catch(Exception e)
		{
			LOG.error("Exception Occured in searchTdpCadreDetailsBySearchCriteria() Method, Exception is ",e);
			return "{\"status\":\"Failure\"}";
		}
	}
	
	
	@GET
    @Path("/missedCallCampaign/cadreMissedCallCampaign/{data}")
	@Produces(MediaType.TEXT_PLAIN)
	public Object saveMissedCallDataForCadre(@PathParam("data") String data)
	{
		
		LOG.debug("save missed call data for cadre");
		LOG.debug(data.toString());
		Map<String,String> details= new HashMap<String, String>();
		details.put("","");
		try{ 
			MissedCallCampaignVO vo = new MissedCallCampaignVO();
			String message="";
			String[] inputsArray = data.split("&");
			for(String input:inputsArray){
				String[] parameterArray = input.split("=");
				if(parameterArray.length > 0){
					
					
					 int start = parameterArray[0].indexOf("[");
	                 int end = parameterArray[0].indexOf("]");
	                 if(start != -1 && end != -1){
	                        message = input.substring(start + 1, end);
	                 }
					
					
					if(message.equalsIgnoreCase("from")){
						if(parameterArray.length > 1){
							vo.setFrom(parameterArray[1]);
						}
					}
					else if(message.equalsIgnoreCase("Ring_time")){
						if(parameterArray.length > 1){
							vo.setRing_time(Long.valueOf(parameterArray[1]));
						}
					}
				}
			}
			vo.setId(1l);
			LOG.error("In Web Service mobilenumber"+vo.getFrom());

			String regex = "\\d{10}";
	        Pattern mobNO = Pattern.compile(regex);
	        Matcher matcher = mobNO.matcher(vo.getFrom());
	        
			if((vo.getFrom() != null && vo.getFrom().trim().length() >0 ) && (vo.getRing_time() != null)){
			    return webServiceHandlerService1.saveMissedCallDetails(vo);
			}else{
			return "Invalid Inputs !";
			}
		}
		catch(Exception e)
		{
			LOG.error(e);
			return "Error Occured";
		}
		
	}
	
	@POST
	@Path("/Auth/validateMembership")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<CadreAddressVO> checkMembershipExists(List<CadreTravelsVO> inputVO){
		
		try{
			List<String> memberShipIdsList = new ArrayList<String>();
			for(CadreTravelsVO list: inputVO){
				if(!memberShipIdsList.contains(equals(list.getMembershipNo()))){
					memberShipIdsList.add(list.getMembershipNo());
				}
				
			}
			cadreAddressVOList = commonUtilsService.checkForValidMember(memberShipIdsList);
		
		}
		catch(Exception e)
		{
			LOG.error("Exception Occured in checkMembershipExists() Method, Exception is ",e);
			
		}
		return cadreAddressVOList;
	}
	
	
	
	
	@POST
	@Path("/Auth/getMobileNo")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<CadreAddressVO> getMobileNoByMemberShip(List<CadreTravelsVO> inputVOList){
		
		try{
			
			List<String> memberShipIdsList = new ArrayList<String>();
			for(CadreTravelsVO list: inputVOList){
				if(!memberShipIdsList.contains(equals(list.getMembershipNo()))){
					memberShipIdsList.add(list.getMembershipNo());
				}
				
			}
			cadreAddressVOList = webServiceHandlerService.getMobileNoByMemberShip(memberShipIdsList);
			
			
		}
		catch(Exception e)
		{
			LOG.error("Exception Occured in getMobileNoByMemberShip() Method, Exception is ",e);
			
		}
		return cadreAddressVOList;
	}
	
	@POST
	@Path("/Auth/getMemberData")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<CadreAddressVO> getMemberDataByMemberShip(List<CadreTravelsVO> inputVO){
		
		try{
			
			List<String> addressTrueList = new ArrayList<String>();
			List<String> addressFalseList = new ArrayList<String>();
			
			for(CadreTravelsVO vo: inputVO){
				if(!addressTrueList.contains(equals(vo.getMembershipNo()))){
					
					if(vo.getIsAddress().equalsIgnoreCase("true"))
						addressTrueList.add(vo.getMembershipNo());
					else{
						addressFalseList.add(vo.getMembershipNo());
					}
				}
				
			}
			cadreAddressVOList = webServiceHandlerService.getMemberDataByMemberShip(addressTrueList,addressFalseList);
		
		}
		catch(Exception e)
		{
			LOG.error("Exception Occured in getMemberDataByMemberShip() Method, Exception is ",e);
			e.printStackTrace();
		}
		return cadreAddressVOList;
	}
	
	@POST
	@Path("/getCadreBasicInfo")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public CadreAddressVO getMemberDataByMemberShipAndRefNo(CadreAddressVO inputVo){
		CadreAddressVO cadreAddressVO = new CadreAddressVO();
		try{
			
			 cadreAddressVO = webServiceHandlerService.getMemberDataByRefNoAndMemberShipNo(inputVo.getRefNo().trim(),inputVo.getMembershipNo().trim());
			if(cadreAddressVO.getMembershipNo() != null)
			return cadreAddressVO;
			else
			{
				cadreAddressVO = new CadreAddressVO();
				cadreAddressVO.setValue("Failure");
			}
			
		}
		catch(Exception e)
		{
			LOG.error("Exception Occured in getMemberDataByMemberShipAndRefNo() Method, Exception is ",e);
			e.printStackTrace();
			cadreAddressVO.setValue("Exception");
		}
		return cadreAddressVO;
		
	}
		 
	
	@POST
	@Path("/validateUserForEvent")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public UserEventDetailsVO checkValidUserForEvent(UserEventDetailsVO inputVo){
		UserEventDetailsVO userEventDetailsVO = new UserEventDetailsVO();
		try{
			
			userEventDetailsVO = webServiceHandlerService.validateUserForEvent(inputVo);
			if(userEventDetailsVO != null)
			return userEventDetailsVO;
			else
			{
				 userEventDetailsVO = new UserEventDetailsVO();
				 userEventDetailsVO.setStatus("Not Valid");
			}
		
		}
		catch(Exception e)
		{
			LOG.error("Exception Occured in checkValidUserForEvent() Method, Exception is ",e);
			e.printStackTrace();	
			userEventDetailsVO.setStatus("Exception");
		}
		return userEventDetailsVO;
		
		
	}
	
	@POST
	@Path("/insertEventAttendeeInfo")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public UserEventDetailsVO insertEventAttendeeInfo(UserEventDetailsVO inputVo){
		UserEventDetailsVO userEventDetailsVO = new UserEventDetailsVO();
		try{
			
			userEventDetailsVO = webServiceHandlerService.insertEventAttendeeInfo(inputVo);
		}
		catch(Exception e)
		{
			LOG.error("Exception Occured in checkValidUserForEvent() Method, Exception is ",e);
			e.printStackTrace();	
			
		}
		return userEventDetailsVO;
		
		
	}
	
	@POST
	@Path("/updateDatasyncurl")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public UserEventDetailsVO updateDatasyncurl(UserEventDetailsVO inputVo){
		UserEventDetailsVO userEventDetailsVO = new UserEventDetailsVO();
		try{
			
			userEventDetailsVO = webServiceHandlerService.updateDatasyncurl(inputVo);
			
		
		}
		catch(Exception e)
		{
			LOG.error("Exception Occured in checkValidUserForEvent() Method, Exception is ",e);
			e.printStackTrace();	
			userEventDetailsVO.setStatus("Exception");
		}
		return userEventDetailsVO;
		
		
	}
	@GET
	@Path("/validateMembership1/{data}")
	@Produces(MediaType.APPLICATION_JSON)
	
	public List<CadreAddressVO> checkMembershipExists1(@PathParam("data") String data){
		
		try{
			List<String> memberShipIdsList = new ArrayList<String>();
			String[] inputsArray = data.split(",");
			for(String input:inputsArray){
				if(!memberShipIdsList.contains(equals(input))){
					memberShipIdsList.add(input);
				}
			
			}
			cadreAddressVOList = commonUtilsService.checkForValidMember(memberShipIdsList);
		
		}
		catch(Exception e)
		{
			LOG.error("Exception Occured in checkMembershipExists1() Method, Exception is ",e);
			
		}
		return cadreAddressVOList;
	}
	
	@POST
	@Path("/verifyEventSyncData")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ResultStatus verifyEventSyncData(UserEventDetailsVO inputVo){
		ResultStatus resultStatus = new ResultStatus();
		try{
			
			resultStatus = webServiceHandlerService.verifyEventSyncData(inputVo);
			
		
		}
		catch(Exception e)
		{
			LOG.error("Exception Occured in verifyEventSyncData() Method, Exception is ",e);
			e.printStackTrace();	
			resultStatus.setMessage("Exception");
		}
		return resultStatus;
	}
	
	@POST
	@Path("/saveAttendance")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public AttendanceVO saveAttendance(AttendanceVO inputVo){
		try{
			return attendanceService.saveAttendance(inputVo);
		}
		catch(Exception e)
		{
			LOG.error("Exception Occured in saveAttendance() Method - ",e);
			return null;
		}
	}
	
	@POST
	@Path("/loginAttendanceTabUser")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public AttendanceTabUserVO loginAttendanceTabUser(AttendanceTabUserVO inputVo){
		try{
			return attendanceService.loginAttendanceTabUser(inputVo);
		}
		catch(Exception e)
		{
			LOG.error("Exception Occured in saveAttendance() Method - ",e);
			return null;
		}
	}
	
	@POST
	@Path("/getAttendanceMeetingAndCamps")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public UserAttendanceDetailsVO getAttendanceMeetingAndCamps(AttendanceTabUserVO inputVo){
		try{
			return attendanceService.getAttendanceMeetingAndCamps(inputVo);
		}
		catch(Exception e)
		{
			LOG.error("Exception Occured in saveAttendance() Method - ",e);
			return null;
		}
	}
	
	@POST
	@Path("/getPartyMeetingInvittees")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public PartyMeetingInviteeVO getPartyMeetingInvittees(PartyMeetingInviteeVO inputVO){
		try{
			return attendanceService.getPartyMeetingInvittees(inputVO.getPartyMeetingId());
		}
		catch(Exception e)
		{
			LOG.error("Exception Occured in saveAttendance() Method - ",e);
			return null;
		}
	}
	
	@POST
	@Path("/getTrainingCampBatchInvittees")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public PartyMeetingInviteeVO getTrainingCampBatchInvittees(PartyMeetingInviteeVO inputVO){
		try{
			return attendanceService.getTrainingCampBatchInvittees(inputVO.getPartyMeetingId());
		}
		catch(Exception e)
		{
			LOG.error("Exception Occured in saveAttendance() Method - ",e);
			return null;
		}
	}
	
	@POST
	@Path("/getCadreOverviewDetails")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public CadreOverviewVO getTdpCadreOverViewDetails(CadreOverviewVO inputVo){
		CadreOverviewVO returnVO = new CadreOverviewVO();
		try{
			
			returnVO = webServiceHandlerService.getTdpCadreOverViewDetails(inputVo);
		}
		catch(Exception e)
		{
			LOG.error("Exception Occured in getTdpCadreOverViewDetails() Method, Exception is ",e);
			returnVO = null;
		}
		return returnVO;
	}
	@POST
	@Path("/getVoterDetailsByVoterIdCardNum")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public CadreOverviewVO getVoterDetailsByVoterIdCardNum(CadreOverviewVO inputVo){
		CadreOverviewVO returnVO = new CadreOverviewVO();
		try{
			
			returnVO = webServiceHandlerService.getVoterDetailsByVoterIdCardNum(inputVo);
		}
		catch(Exception e)
		{
			LOG.error("Exception Occured in getVoterDetailsByVoterIdCardNum() Method, Exception is ",e);
			returnVO = null;
		}
		return returnVO;
	}
	
	@GET
	@Path("/getPartyMeetingsForCadrePeople/{tdpCadreId}")
	@Produces(MediaType.APPLICATION_JSON)
	public PartyMeetingVO getPartyMeetingsForCadrePeople(@PathParam("tdpCadreId") String tdpCadreId){
		
		try{
			return webServiceHandlerService.getPartyMeetingsForCadrePeople(Long.valueOf(tdpCadreId));
		}
		catch(Exception e)
		{
			LOG.error("Exception Occured in getPartyMeetingsForCadrePeople() Method, Exception is ",e);
		}
		return null;
	}
	
	@GET
	@Path("/getPartyMeetingsOverView/{tdpCadreId}")
	@Produces(MediaType.APPLICATION_JSON)
	public PartyMeetingVO getPartyMeetingsOverView(@PathParam("tdpCadreId") String tdpCadreId){
		
		try{
			return webServiceHandlerService.getPartyMeetingsForCadreOverview(Long.valueOf(tdpCadreId));
		}
		catch(Exception e)
		{
			LOG.error("Exception Occured in getPartyMeetingsForCadrePeople() Method, Exception is ",e);
		}
		return null;
	}
	@GET
	@Path("/getParticipatedCandidateEventDetails/{tdpCadreId}")
	@Produces(MediaType.APPLICATION_JSON)
	public PartyMeetingVO getParticipatedCandidateEventDetails(@PathParam("tdpCadreId") String tdpCadreId){
		
		try{
			return webServiceHandlerService.getParticipatedCandidateEventDetails(Long.valueOf(tdpCadreId));
		}
		catch(Exception e)
		{
			LOG.error("Exception Occured in getParticipatedCandidateEventDetails() Method, Exception is ",e);
		}
		return null;
	}
	
	@GET
	@Path("/getEventDetailsOfCadre/{tdpCadreId}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<CadreCommitteeMemberVO> getEventDetailsOfCadre(@PathParam("tdpCadreId") String tdpCadreId){
		
		try{
			return webServiceHandlerService.getEventDetailsOfCadre(Long.valueOf(tdpCadreId));
		}
		catch(Exception e)
		{
			LOG.error("Exception Occured in getEventDetailsOfCadre() Method, Exception is ",e);
			
		}
		return null;
	}
	
	@GET
	@Path("/getMeetingTypeWiseDescription/{tdpCadreId}/{partyMeetingTypeId}")
	@Produces(MediaType.APPLICATION_JSON)
	public PartyMeetingVO getMeetingTypeWiseDescription(@PathParam("tdpCadreId") String tdpCadreId,@PathParam("partyMeetingTypeId") String partyMeetingTypeId){
		
		try{
			return webServiceHandlerService.getMeetingTypeWiseDescription(Long.valueOf(tdpCadreId), Long.valueOf(partyMeetingTypeId));
		}
		catch(Exception e)
		{
			LOG.error("Exception Occured in getEventDetailsOfCadre() Method, Exception is ",e);
			
		}
		return null;
	}
	
	@GET
	@Path("/getElectionPerformanceInCadreLocation/{tdpCadreId}/{voterCardNo}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<RegisteredMembershipCountVO> getElectionPerformanceForCadre(@PathParam("tdpCadreId") String tdpCadreId,@PathParam("voterCardNo") String voterCardNo){
		
		try{
			return webServiceHandlerService.getElectionPerformanceInCadreLocation(Long.valueOf(tdpCadreId),voterCardNo);
		}
		catch(Exception e)
		{
			LOG.error("Exception Occured in getEventDetailsOfCadre() Method, Exception is ",e);
			
		}
		return null;
	}
	@GET
	@Path("/getNtrTrustStudentDetailsInstitutionWise/{cadreIdsStr}")
	@Produces(MediaType.APPLICATION_JSON)
	public NtrTrustStudentVO getNtrTrustStudentDetailsInstitutionWise(@PathParam("cadreIdsStr") String cadreIdsStr){
		try{
			
			List<Long> cadreIds = new ArrayList<Long>();
			
			String[] stringArr =null;
			if(cadreIdsStr !=null){
				stringArr =cadreIdsStr.split(",");
			}
			
			List<String> cadreIdsSTrrr = Arrays.asList(stringArr);
			
			for(String singleCadre:cadreIdsSTrrr ){
				Long cadreId = Long.parseLong(singleCadre);
				cadreIds.add(cadreId);
			}
			
			return webServiceHandlerService.getNtrTrustStudentDetailsInstitutionWise(cadreIds);
		}
		catch(Exception e)
		{
			LOG.error("Exception Occured in getNtrTrustStudentDetailsInstitutionWise() Method, Exception is ",e);
			
		}
		return null;
	}
	@GET
	@Path("/getStudentFormalDetailsByCadre/{cadreIdsStr}/{institutionId}")
	@Produces(MediaType.APPLICATION_JSON)
	 public List<NtrTrustStudentVO> getStudentFormalDetailsByCadre(@PathParam("cadreIdsStr") String cadreIdsStr,@PathParam("institutionId") Long institutionId){
		try{
			
            List<Long> cadreIds = new ArrayList<Long>();
			
			String[] stringArr =null;
			if(cadreIdsStr !=null){
				if (cadreIdsStr.endsWith(","))
				cadreIdsStr = cadreIdsStr.substring(0, cadreIdsStr.length() - 1);
				stringArr =cadreIdsStr.split(",");
			}
			
			List<String> cadreIdsSTrrr = Arrays.asList(stringArr);
			
			for(String singleCadre:cadreIdsSTrrr ){
				Long cadreId = Long.parseLong(singleCadre);
				cadreIds.add(cadreId);
			}
			
			
			return webServiceHandlerService.getStudentFormalDetailsByCadre(cadreIds,Long.valueOf(institutionId));
		}
		catch(Exception e)
		{
			LOG.error("Exception Occured in getStudentFormalDetailsByCadre() Method, Exception is ",e);
			
		}
		return null;
		
	 }
	@GET
	@Path("/getAllRemovedCadre")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<Long> getAllRemovedCadre(){
		try{
			return webServiceHandlerService.getAllRemovedCadre();
		}
		catch(Exception e)
		{
			LOG.error("Exception Occured in getAllRemovedCadre() Method, Exception is ",e);
			
		}
		return null;
	}
	
	@GET
	@Path("/getAllRecordsOfCampProgramScheduleAndBatch/{campId}/{programId}/{scheduleId}/{batchId}")
	@Produces(MediaType.APPLICATION_JSON)
	 public SurveyTrainingsVO getAllRecordsOfCampProgramScheduleAndBatch(@PathParam("campId") Long campId,@PathParam("programId") Long programId,
			 @PathParam("scheduleId") Long scheduleId,@PathParam("batchId") Long batchId ){
		try{
			return webServiceHandlerService.getAllRecordsOfCampProgramScheduleAndBatch(Long.valueOf(campId),Long.valueOf(programId),Long.valueOf(scheduleId),Long.valueOf(batchId));
		}
		catch(Exception e)
		{
			LOG.error("Exception Occured in getAllRecordsOfCampProgramScheduleAndBatch() Method, Exception is ",e);
			
		}
		return null;
		
	 }
	
	 @POST
	 @Path("/getTdpMemberShipIdsByEvent")
	 @Produces(MediaType.APPLICATION_JSON)
	 @Consumes(MediaType.APPLICATION_JSON)
	 public List<Long> getTdpMemberShipIdsByEvent(UserEventDetailsVO inputVo){
	 try {
	 return webServiceHandlerService.getTdpCadreMemberShipsIdsByEvent(inputVo.getEventId());
	 } catch (Exception e) {
	 LOG.error("Exception Occured in getTdpMemberShipIdsByEvent() Method, Exception is ",e);
	 }
	 return null;
	 }
	
	 @POST
	 @Path("/getWebServiceInviteesDetails")
	 @Produces(MediaType.APPLICATION_JSON)
	 @Consumes(MediaType.APPLICATION_JSON)
	 public List<TdpCadreVO> getWebServiceEventInviteesList(InviteesVO inviteesVO){
	 try {
		 return webServiceHandlerService.getWebServiceEventInviteesList(0L,
				 "state","1", inviteesVO.getStateId(), inviteesVO.getGroupVOList(),Long.valueOf(inviteesVO.getEventId()),inviteesVO.getActionType(),inviteesVO.getStateStr(),
				 inviteesVO.getReportType(), Integer.valueOf(inviteesVO.getStartIndex()),Integer.valueOf(inviteesVO.getMaxIndex()));
	 } catch (Exception e) {
	 LOG.error("Exception Occured in getWebServiceEventInviteesList() Method, Exception is ",e);
	 }
	 return null;
	 }
	 
	 
		@POST
		@Path("/saveActivitiesImages")
		@Produces(MediaType.APPLICATION_JSON)
		@Consumes(MediaType.APPLICATION_JSON)
		public ImageVO saveActivitiesImages(ImageVO inputVo){
			try{
				return webServiceHandlerService.saveActivitiesImages(inputVo);
			}
			catch(Exception e)
			{
				LOG.error("Exception Occured in saveAttendance() Method - ",e);
				return null;
			}
		}
		
	 @GET
	 @Path("/ActivityLoginChecker/{username}/{password}")
	 @Produces(MediaType.APPLICATION_JSON)
	 public ActivityLoginVO getActivityLoginChecker(@PathParam("username") String userName, @PathParam("password") String password){
		 return webServiceHandlerService.checkActivityTabUserLogin(userName,password);
	 }
	 
	 @GET
	 @Path("/ActivityDetails/{userId}")
	 @Produces(MediaType.APPLICATION_JSON)
	 public ActivityWSVO getUserActivityDetailsByUserId(@PathParam("userId") Long userId){
		 return webServiceHandlerService.getUserActivityDetailsByUserId(userId);
	 }
	 
	 
	 
	 @GET
	 @Path("/ActivityCadreDetails/{memberShipNo}/{voterCardNo}")
	 @Produces(MediaType.APPLICATION_JSON)
	 public TdpCadreWSVO getActivityCadreDetails(@PathParam("memberShipNo") String memberShipNo, @PathParam("voterCardNo") String voterCardNo){
		 return webServiceHandlerService.getActivityCadreDetails(memberShipNo,voterCardNo);
	 }
	 
	 @POST
	 @Path("/publicActivityAttendance")
	 @Produces(MediaType.APPLICATION_JSON)
	 @Consumes(MediaType.APPLICATION_JSON)
	 public ResultStatus savePublicActivityAttendance(ActivityAttendanceVO inputVo){
		 try {
			 return webServiceHandlerService.savePublicActivityAttendance(inputVo);
		 } catch (Exception e) {
			 LOG.error("Exception Occured in savePublicActivityAttendance() Method, Exception is ",e);
		 }
		 return null;
	 }
	 
	 @POST
	 @Path("/activityQuestionAnswer")
	 @Produces(MediaType.APPLICATION_JSON)
	 @Consumes(MediaType.APPLICATION_JSON)
	 public ResultStatus saveActivityQuestionAnswer(AttendanceQuestionnariWSVO aqWSVO){
		 try {
			 return webServiceHandlerService.saveActivityQuestionAnswer(aqWSVO);
		 } catch (Exception e) {
			 LOG.error("Exception Occured in saveActivityQuestionAnswer() Method, Exception is ",e);
		 }
		 return null;
	 }
	 
	 @POST
	 @Path("/loginMobileAppUser")
	 @Produces(MediaType.APPLICATION_JSON)
	 @Consumes(MediaType.APPLICATION_JSON)
	 public List<MobileAppUserVO> checkMobileAppUser(MobileAppUserVO inputVO){
		 try {
			 return webServiceHandlerService.checkMobileAppUser(inputVO);
		 } catch (Exception e) {
			 LOG.error("Exception Occured in checkMobileAppUser() Method, Exception is ",e);
		 }
		 return null;
	 }
	 
	 @POST
	 @Path("/getMobileAppUserSmsDetails")
	 @Produces(MediaType.APPLICATION_JSON)
	 @Consumes(MediaType.APPLICATION_JSON)
	 public List<MobileAppUserVO> getMobileAppUserSmsDetails(MobileAppUserVO inputVO){
		 try {
			 return webServiceHandlerService.getMobileAppUserSmsDetails(inputVO);
		 } catch (Exception e) {
			 LOG.error("Exception Occured in getMobileAppUserSmsDetails() Method, Exception is ",e);
		 }
		 return null;
	 }
	 
	 @POST
	 @Path("/saveMobileAppUserVoterData")
	 @Produces(MediaType.APPLICATION_JSON)
	 @Consumes(MediaType.APPLICATION_JSON)
	 public ResultStatus saveMobileAppUserVoterInfo(MobileAppUserVoterVO inputVo){
		 try {
			 return webServiceHandlerService.saveMobileAppUserVoterData(inputVo);
		 } catch (Exception e) {
			 LOG.error("Exception Occured in saveMobileAppUserVoterInfo() Method, Exception is ",e);
		 }
		 return null;
	 }
	 @POST
	 @Path("/saveMobileAppUserSmsStatus")
	 @Produces(MediaType.APPLICATION_JSON)
	 @Consumes(MediaType.APPLICATION_JSON)
	 public ResultStatus saveMobileAppUserSmsStatusInfo(MobileAppUserSmsStatusVO inputVo){
		 try {
			 return webServiceHandlerService.saveMobileAppUserSmsStatusData(inputVo);
		 } catch (Exception e) {
			 LOG.error("Exception Occured in saveMobileAppUserSmsStatus() Method, Exception is ",e);
		 }
		 return null;
	 }
	 
	 
	 
	 @POST
	 @Path("/sendSmsForCadre")
	 @Produces(MediaType.APPLICATION_JSON)
	 @Consumes(MediaType.APPLICATION_JSON)
	 public ResultStatus sendSmsForCadre(MobileAppUserVoterVO inputVO){
		 try {
			 return smsSenderService.sendSmsToCadre(inputVO);
		 } catch (Exception e) {
			 LOG.error("Exception Occured in sendSmsForCadre() Method, Exception is ",e);
		 }
		 return null;
	 }
	 
	 @POST
	 @Path("/updateBoothVoterData")
	 @Produces(MediaType.APPLICATION_JSON)
	 @Consumes(MediaType.APPLICATION_JSON)
	 public List<MobileAppUserVoterVO> updateBoothVoterData(MobileAppUserVoterVO inputVO){
		 try {
			 return webServiceHandlerService.updateBoothVoter(inputVO);
		 } catch (Exception e) {
			 LOG.error("Exception Occured in updateBoothVoterData() Method, Exception is ",e);
		 }
		 return null;
	 }
	 

		@POST
		@Path("/saveUserLocationData")
		@Produces(MediaType.APPLICATION_JSON)
		@Consumes(MediaType.APPLICATION_JSON)
		public String saveUserLocationData(UserLocationTrackingVo inputVO)
		{
			try
			{
	           return  webServiceHandlerService.saveUserLocationData(inputVO);
			}catch(Exception e)
			{
				LOG.error("Exception raised in  survey response data from app:"+e.getMessage());
			}
			return null;
		}
		
		
		@POST
		@Path("/updateVoterVotedData")
		@Produces(MediaType.APPLICATION_JSON)
		@Consumes(MediaType.APPLICATION_JSON)
		public String updateVoterVotedData(MobileAppUserVoterVO inputVO)
		{
			try
			{
	           return  webServiceHandlerService.updateVoterVotedData(inputVO);
			}catch(Exception e)
			{
				LOG.error("Exception raised in  updateVoterVotedData from app:"+e.getMessage());
			}
			return null;
		}
		
		@POST
		@Path("/SendSmsToVoter")
		@Produces(MediaType.APPLICATION_JSON)
		@Consumes(MediaType.APPLICATION_JSON)
		public ResultStatus SendSmsToVoter(MobileAppUserVoterVO inputVO)
		{
			try
			{
	           return smsSenderService.sendSmsToVoter(inputVO);
			}catch(Exception e)
			{
				LOG.error("Exception raised in  SendSmsToVoter from app:"+e.getMessage());
			}
			return null;
		}
		@POST
		@Path("/getDivisonWiseOverview")
		@Produces(MediaType.APPLICATION_JSON)
		@Consumes(MediaType.APPLICATION_JSON)
		public PollManagementVO getDivisonWiseOverview(MobileAppUserVoterVO inputVO)
		{
			try
			{
	           return webServiceHandlerService.getDivisonWiseOverview(inputVO);
			}catch(Exception e)
			{
				LOG.error("Exception raised in getDivisonWiseOverview from app:"+e.getMessage());
			}
			return null;
		}
		
		@POST
		@Path("/getVoterInfoForBooth")
		@Produces(MediaType.APPLICATION_JSON)
		@Consumes(MediaType.APPLICATION_JSON)
		public List<CadreVoterVO> getVoterInfoForBooth(MobileAppUserVoterVO inputVO)
		{
			try
			{
	           return webServiceHandlerService.getVoterInfoForBooth(inputVO);
			}catch(Exception e)
			{
				LOG.error("Exception raised in getVoterInfoForBooth from app:"+e.getMessage());
			}
			return null;
		}
		
		@POST
		@Path("/loginUnionTabUser")
		@Produces(MediaType.APPLICATION_JSON)
		@Consumes(MediaType.APPLICATION_JSON)
		public UnionTabUserVO checkLoginUnionTabUser(UnionTabUserVO inputVO)
		{
			try
			{
	           return webServiceHandlerService.checkLoginUnionTabUser(inputVO);
			}catch(Exception e)
			{
				LOG.error("Exception raised in checkLoginUnionTabUser from app:"+e.getMessage());
			}
			return null;
		}
		
		@GET
		@Path("/getAttendedDetails/{partyMeetingId}")
		@Produces(MediaType.APPLICATION_JSON)
		public PartyMeetingWSVO getAttendedDetailsForPartyMeeting(@PathParam("partyMeetingId") Long partyMeetingId){
		 return webServiceHandlerService.getAttendedDetailsForPartyMeeting(partyMeetingId);
		}
		
		@GET
		@Path("/getCadreFormalDetails/{partyMeetingId}/{searchType}")
		@Produces(MediaType.APPLICATION_JSON)
		public PartyMeetingWSVO getAttendedDetailsForPartyMeeting(@PathParam("partyMeetingId") Long partyMeetingId,@PathParam("searchType") String searchType){
		 return webServiceHandlerService.getTdpCadreDetailsForPartyMeeting(partyMeetingId,searchType);
		}
		
		@POST
		@Path("/saveNotificationDeviceInfo")
		@Produces(MediaType.APPLICATION_JSON)
		@Consumes(MediaType.APPLICATION_JSON)
		public NotificationDeviceVO saveNotificationDeviceInfo(NotificationDeviceVO inputVo){
			try{
			return (NotificationDeviceVO) notificationService.saveUsersDataInNotificationDeviceTable(inputVo);
		}
		catch(Exception e){
			LOG.error("Exception Occured in getNotificationDetailsBynotificationDeviceId() Method, Exception is ",e);
			e.printStackTrace();	
		}
			return null;  
		}
		
		@POST
		@Path("/getNotificationDetails")
		@Produces(MediaType.APPLICATION_JSON)
		@Consumes(MediaType.APPLICATION_JSON)
		public List<NotificationDeviceVO> getNotificationDetailsBynotificationDeviceId(NotificationDeviceVO inputVo){
			try{
				List<NotificationDeviceVO>  notificationDeviceVOList = notificationService.getActiveNotifications(inputVo);
				return notificationDeviceVOList;
		}
		catch(Exception e){
			LOG.error("Exception Occured in getNotificationDetailsBynotificationDeviceId() Method, Exception is ",e);
			e.printStackTrace();	
		}
			return null;  
		}
		
		@POST
		@Path("/getAccommodationTrackingInfoByNotificationType")
		@Produces(MediaType.APPLICATION_JSON)
		@Consumes(MediaType.APPLICATION_JSON)
		public List<AccommodationVO> getAccommodationTrackingInfoByNotificationType(AccommodationVO inputvo){
		 return webServiceHandlerService.getAccommodationTrackingInfoByNotificationType(inputvo);
		}
		
		@GET
		@Path("/StateWiseConstituency")
		@Produces(MediaType.APPLICATION_JSON)
		public List<IdAndNameVO> getStateWiseConstituency(){
		 return webServiceHandlerService.getStateWiseConstituency();
		}
		
		@GET
		@Path("/getConstitencyWiseTehsil/{constituencyId}")
		@Produces(MediaType.APPLICATION_JSON)
		public List<IdAndNameVO> getConstitencyWiseTehsil(@PathParam("constituencyId") Long constituencyId){
		 return webServiceHandlerService.getConstitencyWiseTehsil(constituencyId);
		}
		
		@GET
		@Path("/getPanchyatAndBoothsList/{tehsilId}")
		@Produces(MediaType.APPLICATION_JSON)
		public List<IdAndNameVO> getPanchyatAndBoothsList(@PathParam("tehsilId") Long tehsilId){
			return webServiceHandlerService.getPanchayatOrConsList(tehsilId);
		}
		
		@GET
		@Path("/getBoothsListForPancyat/{panchayatId}")
		@Produces(MediaType.APPLICATION_JSON)
		public List<IdAndNameVO> getBoothsListForPancyat(@PathParam("panchayatId") Long panchayatId){
			return webServiceHandlerService.getBoothsList(panchayatId);
		}
				
		@POST
		@Path("/Secure/getMembershipDriveVisualizationDetails")
		@Produces(MediaType.APPLICATION_JSON)
		@Consumes(MediaType.APPLICATION_JSON)
		public List<GISVisualizationDetailsVO> getSecureMembershipDriveVisualizationDetails(GISVisualizationParameterVO inputVO){
		 return webServiceHandlerService.getMembershipDriveVisualizationDetails(inputVO);
		}
		
		@POST
		@Path("/getMembershipDriveDayWiseVisualizationDetails")
		@Produces(MediaType.APPLICATION_JSON)
		@Consumes(MediaType.APPLICATION_JSON)
		public List<GISVisualizationDetailsVO> getMembershipDriveDayWiseVisualizationDetails(GISVisualizationParameterVO inputVO){
		 return webServiceHandlerService.getMembershipDriveDayWiseVisualizationDetails(inputVO);
		}
		
		@GET
		@Path("/Secure/getStateWiseAssemblyConstituency/{stateId}")
		@Produces(MediaType.APPLICATION_JSON)
		public List<KeyValueVO> getStateWiseAssemblyConstituency(@PathParam("stateId") Long stateId){
		 return webServiceHandlerService.getStateWiseAssemblyConstituency(stateId);
		}
		
		@POST
		@Path("/Secure/getLatestLattitudeLangitudeOfTabUser")
		@Produces(MediaType.APPLICATION_JSON)
		@Consumes(MediaType.APPLICATION_JSON)
		public List<GISUserTrackingVO> getLatestLattitudeLangitudeOfTabUser(GISUserTrackingVO input){
		 return webServiceHandlerService.getLatestLattitudeLangitudeOfTabUser(input);
		}
		
		@POST
		@Path("/syncCadreTabRecordsStatus")
		@Produces(MediaType.APPLICATION_JSON)
		@Consumes(MediaType.APPLICATION_JSON)
		public ResultStatus syncCadreTabRecordsStatus(List<CadreTabRecordsStatusVO> inputList){
			return webServiceHandlerService.syncCadreTabRecordsStatus(inputList);
		}
		@GET
		@Path("/getStateWiseDistrict/{state}")
		@Produces(MediaType.APPLICATION_JSON)
		public List<IdAndNameVO> getStateWiseDistrict(@PathParam("state") Long state){
			return webServiceHandlerService.getStateWiseDistrict(state);
			 //cadreRegistrationService.getStateWiseDistrict(state);
		}
		@GET
		@Path("/getDistrictWiseConstituency/{district}")
		@Produces(MediaType.APPLICATION_JSON)
		public List<IdAndNameVO> getDistrictWiseConstituency(@PathParam("district") Long district){
			return webServiceHandlerService.getDistrictWiseConstituency(district);
			//return cadreRegistrationService.getDistrictWiseConstituency(district);
		}
		@POST
		@Path("/Secure/getLocationWiseTabUserTrackingDetails")
		@Produces(MediaType.APPLICATION_JSON)
		@Consumes(MediaType.APPLICATION_JSON)
		public GISUserTrackingVO getLocationWiseTabUserTrackingDetails(GISVisualizationParameterVO inputVO){
			return webServiceHandlerService.getLocationWiseTabUserTrackingDetails(inputVO);
		}
		@POST
	    @Path("/Secure/getUserTrackinDetailsBySurveyUserId")
		@Produces(MediaType.APPLICATION_JSON)
		@Consumes(MediaType.APPLICATION_JSON)
		public CadreBasicVO getUserTrackingDetailsBySurveyUserId(CadreBasicVO inputVO)
	    {
			return coreDashboardCadreRegistrationService.getUserTrackngDtslBySurveyUserId(inputVO.getSurveyUserId(),inputVO.getFromDate(),inputVO.getToDate());
		 }
		
		@POST
	    @Path("/Secure/getCadreRegistrationIssuesStatusDetails")
		@Produces(MediaType.APPLICATION_JSON)
		@Consumes(MediaType.APPLICATION_JSON)
		public GISIssuesVO getCadreRegistrationIssuesStatusDetails(GISVisualizationParameterVO inputVO)
	    {
			return webServiceHandlerService.getCadreRegistrationIssuesStatusDetails(inputVO);
		}
		
		@POST
		@Path("/setArticleDetailsIntoAlert")
		@Produces(MediaType.APPLICATION_JSON)
		@Consumes(MediaType.APPLICATION_JSON)
		public String setArticleDetailsIntoAlert(ActionableVO VO){
			return webServiceHandlerService.setArticleDetailsIntoAlert(VO);
		}
		
		@GET
		@Path("/getAlertStatusOfArticle/{articleId}")
		@Produces(MediaType.APPLICATION_JSON)
		@Consumes(MediaType.APPLICATION_JSON)
		public String getAlertStatusOfArticle(@PathParam("articleId") Long articleId){
			return webServiceHandlerService.getAlertStatusOfArticle(articleId);
		}
		
		@POST
		@Path("/peshi/getPeshiAppValidateLoginDetails")
		@Produces(MediaType.APPLICATION_JSON)
		@Consumes(MediaType.APPLICATION_JSON)
		public PeshiAppLoginVO peshiApploginDataUser(PeshiAppLoginVO vo)
		{
			try{
				
				peshiAppLoginVO = webServiceHandlerService.getPeshiAppValidateLoginDetails(vo.getUserName(),vo.getPassword());
				return peshiAppLoginVO;
			}
			catch(Exception e)
			{
				LOG.error("Exception Occured in peshiApploginDataUser() Method,Exception is ",e);
			    return null;
			}
			
		}
		
		@POST
		@Path("/peshi/getPeshiAppGrievanceDetails")
		@Produces(MediaType.APPLICATION_JSON)
		@Consumes(MediaType.APPLICATION_JSON)
		public PeshiAppGrievanceVO peshiAppGrievanceDetails(PeshiAppGrievanceVO vo)
		{
			try{
				
				peshiAppGrievanceVO = webServiceHandlerService.getPeshiAppGrievanceDetails(vo.getFromDate(),vo.getToDate(),vo.getMembershipId());
				return peshiAppGrievanceVO;
			}
			catch(Exception e)
			{
				LOG.error("Exception Occured in peshiAppGrievanceDetails() Method,Exception is ",e);
			    return null;
			}
			
		}
		
		@POST
		@Path("/peshi/getPeshiAppAppointmentDetails")
		@Produces(MediaType.APPLICATION_JSON)
		@Consumes(MediaType.APPLICATION_JSON)
		public PeshiAppAppointmentVO peshiAppAppointmentDetails(PeshiAppGrievanceVO vo){
			try{
				peshiAppAppointmentVO = webServiceHandlerService.getAppointmentDetails(vo.getFromDate(),vo.getToDate(),vo.getMembershipId(),vo.getCadreType(),vo.getVoterId(),vo.getMobileNo());
				return peshiAppAppointmentVO;
			}catch(Exception e){
				LOG.error("Exception Occured in peshiAppAppointmentDetails() Method,Exception is ",e);
			    return null;
			}
		}
		
		@GET
		@Path("/searchTdpCadreDetailsBySearchCriteriaForCallCenter1/{memberShipCardNo}/{mobileNo}")
		@Produces(MediaType.APPLICATION_JSON)
		@Consumes(MediaType.APPLICATION_JSON)
		public Object searchTdpCadreDetailsBySearchCriteriaForCallCenter1(@PathParam("memberShipCardNo") String memberShipCardNo,@PathParam("mobileNo") String mobileNo)
		{
			try{			
				return webServiceHandlerService1.searchTdpCadreDetailsBySearchCriteria("0","",memberShipCardNo,"","",mobileNo);			
			}
			catch(Exception e)
			{
				LOG.error("Exception Occured in searchTdpCadreDetailsBySearchCriteria() Method, Exception is ",e);
				return "{\"status\":\"Failure\"}";
			}
		}
		
		@GET
		@Path("/getAlertOverviewDetails/{activityMemberId}/{stateId}/{fromDateStr}/{toDateStr}/{alertType}/{editionType}")
		@Produces(MediaType.APPLICATION_JSON)
		@Consumes(MediaType.APPLICATION_JSON)
		public AlertOverviewVO getAlertOverviewDetails(@PathParam("activityMemberId") Long activityMemberId,@PathParam("stateId") Long stateId,@PathParam("fromDateStr") String fromDateStr,
				@PathParam("toDateStr") String toDateStr,@PathParam("alertType") Long alertType,@PathParam("editionType") Long editionType)
		{
			try{			
				return webServiceHandlerService.getAlertOverviewDetails(activityMemberId, stateId, fromDateStr, toDateStr, alertType, editionType);			
			}
			catch(Exception e)
			{
				LOG.error("Exception Occured in getAlertOverviewDetails() Method, Exception is ",e);
				return null;
			}
		}
		
		@GET
		@Path("/getAlertCategoryDtlsLocationWise/{activityMemberId}/{stateId}/{fromDateStr}/{toDateStr}/{alertType}/{editionType}")
		@Produces(MediaType.APPLICATION_JSON)
		@Consumes(MediaType.APPLICATION_JSON)
		public List<AlertOverviewVO> getAlertCategoryDtlsLocationWise(@PathParam("activityMemberId") Long activityMemberId,@PathParam("stateId") Long stateId,@PathParam("fromDateStr") String fromDateStr,
				@PathParam("toDateStr") String toDateStr,@PathParam("alertType") Long alertType,@PathParam("editionType") Long editionType)
		{
			try{			
				return webServiceHandlerService.getAlertCategoryDtlsLocationWise(activityMemberId, stateId, fromDateStr, toDateStr, alertType, editionType);			
			}
			catch(Exception e)
			{
				LOG.error("Exception Occured in getAlertCategoryDtlsLocationWise() Method, Exception is ",e);
				return null;
			}
		}
		
		@GET
		@Path("/getDistrictAndStateImpactLevelWiseAlertDtls/{fromDateStr}/{toDateStr}/{stateId}/{impactLevelIds}/{activityMemberId}/{districtId}/{catId}/{alertTypeId}/{editionId}")
		@Produces(MediaType.APPLICATION_JSON)
		@Consumes(MediaType.APPLICATION_JSON)
		public List<AlertCoreDashBoardVO> getDistrictAndStateImpactLevelWiseAlertDtls(@PathParam("fromDateStr") String fromDateStr,@PathParam("toDateStr") String toDateStr,
				@PathParam("stateId") Long stateId,@PathParam("impactLevelIds") List<Long> impactLevelIds,@PathParam("activityMemberId") Long activityMemberId,
				@PathParam("districtId") Long districtId,@PathParam("catId") Long catId,@PathParam("alertTypeId") Long alertTypeId,@PathParam("editionId") Long editionId)
		{
			try{			
				return webServiceHandlerService.getDistrictAndStateImpactLevelWiseAlertDtls(fromDateStr, toDateStr, stateId, impactLevelIds, activityMemberId, districtId, catId, alertTypeId, editionId);			
			}
			catch(Exception e)
			{
				LOG.error("Exception Occured in getDistrictAndStateImpactLevelWiseAlertDtls() Method, Exception is ",e);
				return null;
			}
		}
		
		@GET
		@Path("/getAlertDtls/{fromDateStr}/{toDateStr}/{stateId}/{alertTypeId}/{alertStatusId}/{alertCategoryId}/{activityMemberId}/{editionId}/{isActionType}/{alertActionTypeId}")
		@Produces(MediaType.APPLICATION_JSON)
		@Consumes(MediaType.APPLICATION_JSON)
		public List<AlertCoreDashBoardVO> getAlertDtls(@PathParam("fromDateStr") String fromDateStr,@PathParam("toDateStr") String toDateStr,@PathParam("stateId") Long stateId,
				@PathParam("alertTypeId") Long alertTypeId,@PathParam("alertStatusId") Long alertStatusId,@PathParam("alertCategoryId") Long alertCategoryId,
				@PathParam("activityMemberId") Long activityMemberId,@PathParam("editionId") Long editionId,@PathParam("isActionType") String isActionType,
				@PathParam("alertActionTypeId") Long alertActionTypeId)
		{
			try{			
				return webServiceHandlerService.getAlertDtls(fromDateStr, toDateStr, stateId, alertTypeId, alertStatusId, alertCategoryId, activityMemberId, editionId, isActionType, alertActionTypeId);			
			}
			catch(Exception e)
			{
				LOG.error("Exception Occured in getAlertDtls() Method, Exception is ",e);
				return null;
			}
		}
		@POST
		@Path("/peshi/savingNewMembersForAppointment")
		@Produces(MediaType.APPLICATION_JSON)
		@Consumes(MediaType.APPLICATION_JSON)
		public String savingNewMembersForAppointment(PashiAppNoCadreVO inputVO){
			String result = null;
			try{
				result = webServiceHandlerService.savingNewMembersForAppointment(inputVO);
				return result;
			}catch(Exception e){
				LOG.error("Exception Occured in savingNewMembersForAppointment() Method,Exception is ",e);
			    return null;
			}
			
		}
		
		
		@GET
		@Path("/getStateImpactLevelAlertDtlsCnt/{activityMemberId}/{stateId}/{fromDateStr}/{toDateStr}/{impactLevelIds}/{alertTypeId}/{editionId}")
		@Produces(MediaType.APPLICATION_JSON)
		@Consumes(MediaType.APPLICATION_JSON)
		public AlertOverviewVO getStateImpactLevelAlertDtlsCnt(@PathParam("activityMemberId") Long activityMemberId,@PathParam("stateId") Long stateId,@PathParam("fromDateStr") String fromDateStr,
				@PathParam("toDateStr") String toDateStr,@PathParam("impactLevelIds") String impactLevelIds,@PathParam("alertTypeId") Long alertTypeId,@PathParam("editionId") Long editionId)
		{
			try{			
				return webServiceHandlerService.getStateImpactLevelAlertDtlsCnt(activityMemberId, stateId, fromDateStr, toDateStr, impactLevelIds, alertTypeId, editionId);			
			}
			catch(Exception e)
			{
				LOG.error("Exception Occured in getStateImpactLevelAlertDtlsCnt() Method, Exception is ",e);
				return null;
			}
		}
		
		@GET
		@Path("/getTotalAlertGroupByLocationThenCategory/{fromDateStr}/{toDateStr}/{stateId}/{scopeIdList}/{activityMemberId}/{group}/{alertTypeId}/{editionId}")
		@Produces(MediaType.APPLICATION_JSON)
		@Consumes(MediaType.APPLICATION_JSON)
		public List<AlertVO> getTotalAlertGroupByLocationThenCategory(@PathParam("fromDateStr") String fromDateStr,@PathParam("toDateStr") String toDateStr,
				@PathParam("stateId") Long stateId,@PathParam("scopeIdList") String scopeIdList,@PathParam("activityMemberId") Long activityMemberId,
				@PathParam("group") String group,@PathParam("alertTypeId") Long alertTypeId,@PathParam("editionId") Long editionId)
		{
			try{			
				return webServiceHandlerService.getTotalAlertGroupByLocationThenCategory(fromDateStr, toDateStr, stateId, scopeIdList, activityMemberId, group, alertTypeId, editionId);			
			}
			catch(Exception e)
			{
				LOG.error("Exception Occured in getTotalAlertGroupByLocationThenCategory() Method, Exception is ",e);
				return null;
			}
		}
		
		@GET
		@Path("/getTotalAlertGroupByLocationThenStatus/{fromDateStr}/{toDateStr}/{stateId}/{scopeIdList}/{activityMemberId}/{group}/{alertTypeId}/{editionId}")
		@Produces(MediaType.APPLICATION_JSON)
		@Consumes(MediaType.APPLICATION_JSON)
		public List<AlertVO> getTotalAlertGroupByLocationThenStatus(@PathParam("fromDateStr") String fromDateStr,@PathParam("toDateStr") String toDateStr,
				@PathParam("stateId") Long stateId,@PathParam("scopeIdList") String scopeIdList,@PathParam("activityMemberId") Long activityMemberId,
				@PathParam("group") String group,@PathParam("alertTypeId") Long alertTypeId,@PathParam("editionId") Long editionId)
		{
			try{			
				return webServiceHandlerService.getTotalAlertGroupByLocationThenStatus(fromDateStr, toDateStr, stateId, scopeIdList, activityMemberId, group, alertTypeId, editionId);			
			}
			catch(Exception e)
			{
				LOG.error("Exception Occured in getTotalAlertGroupByLocationThenStatus() Method, Exception is ",e);
				return null;
			}
		}
		
		@GET
		@Path("/getAssignGroupTypeAlertDtlsByImpactLevelWise/{activityMemberId}/{stateId}/{fromDateStr}/{toDateStr}/{impactLevelIds}/{alertTypeId}/{editionTypeId}")
		@Produces(MediaType.APPLICATION_JSON)
		@Consumes(MediaType.APPLICATION_JSON)
		public List<AlertOverviewVO> getAssignGroupTypeAlertDtlsByImpactLevelWise(@PathParam("activityMemberId") Long activityMemberId,@PathParam("stateId") Long stateId,
				@PathParam("fromDateStr") String fromDateStr,@PathParam("toDateStr") String toDateStr,@PathParam("impactLevelIds") String impactLevelIds,
				@PathParam("alertTypeId") Long alertTypeId,@PathParam("editionTypeId") Long editionTypeId)
		{
			try{			
				return webServiceHandlerService.getAssignGroupTypeAlertDtlsByImpactLevelWise(activityMemberId, stateId, fromDateStr, toDateStr, impactLevelIds, alertTypeId, editionTypeId);			
			}
			catch(Exception e)
			{
				LOG.error("Exception Occured in getAssignGroupTypeAlertDtlsByImpactLevelWise() Method, Exception is ",e);
				return null;
			}
		}
		
		@GET
		@Path("/getTotalAlertGroupByDist/{fromDateStr}/{toDateStr}/{stateId}/{scopeIdList}/{activityMemberId}/{alertTypeId}/{editionId}")
		@Produces(MediaType.APPLICATION_JSON)
		@Consumes(MediaType.APPLICATION_JSON)
		public List<AlertCommentVO> getTotalAlertGroupByDist(@PathParam("fromDateStr") String fromDateStr,@PathParam("toDateStr") String toDateStr,
				@PathParam("stateId") Long stateId,@PathParam("scopeIdList") String scopeIdList,@PathParam("activityMemberId") Long activityMemberId,
				@PathParam("alertTypeId") Long alertTypeId,@PathParam("editionId") Long editionId)
		{
			try{			
				return webServiceHandlerService.getTotalAlertGroupByDist(fromDateStr, toDateStr, stateId, scopeIdList, activityMemberId, alertTypeId, editionId);			
			}
			catch(Exception e)
			{
				LOG.error("Exception Occured in getTotalAlertGroupByDist() Method, Exception is ",e);
				return null;
			}
		}
		
		@GET
		@Path("/getDepartmentWiseStatusWiseCounts/{fromDateStr}/{toDateStr}/{stateId}/{scopeIdList}")
		@Produces(MediaType.APPLICATION_JSON)
		@Consumes(MediaType.APPLICATION_JSON)
		public List<AlertCommentVO> getDepartmentWiseStatusWiseCounts(@PathParam("fromDateStr") String fromDateStr,@PathParam("toDateStr") String toDateStr,
				@PathParam("stateId") Long stateId,@PathParam("scopeIdList") String scopeIdList)
		{
			try{			
				return webServiceHandlerService.getDepartmentWiseStatusWiseCounts(fromDateStr, toDateStr, stateId, scopeIdList);			
			}
			catch(Exception e)
			{
				LOG.error("Exception Occured in getDepartmentWiseStatusWiseCounts() Method, Exception is ",e);
				return null;
			}
		}
}
