package com.itgrids.partyanalyst.webservice.android.components;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.itgrids.partyanalyst.dto.CadreImageVO;
import com.itgrids.partyanalyst.dto.CadreRegistrationVO;
import com.itgrids.partyanalyst.dto.CadreTransactionVO;
import com.itgrids.partyanalyst.dto.LoginResponceVO;
import com.itgrids.partyanalyst.dto.ReconciliationVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SinkVO;
import com.itgrids.partyanalyst.dto.SurveyCadreResponceVO;
import com.itgrids.partyanalyst.dto.SurveyResponceVO;
import com.itgrids.partyanalyst.dto.TabRecordsStatusVO;
import com.itgrids.partyanalyst.dto.VoterWebServiceDataVO;
import com.itgrids.partyanalyst.service.ICadreSurveyTransactionService;
import com.itgrids.partyanalyst.webservice.android.abstractservice.IWebServiceHandlerService1;
import com.itgrids.partyanalyst.webserviceutils.android.utilvos.UserLocationTrackingVo;
import com.itgrids.partyanalyst.webserviceutils.android.utilvos.UserLoginUtils;
import com.itgrids.partyanalyst.webserviceutils.android.utilvos.UserResponseVO;

@Component
@Path("/android/fieldData")
public class WebServiceHandler2 {
	
	//@Autowired
	//@Qualifier("web1")
	private final static Logger LOG = Logger.getLogger(WebServiceHandler2.class);

	private IWebServiceHandlerService1  webServiceHandlerService1;
	
	
	private ICadreSurveyTransactionService cadreSurveyTransactionService;
	
	
	public ICadreSurveyTransactionService getCadreSurveyTransactionService() {
		return cadreSurveyTransactionService;
	}


	public void setCadreSurveyTransactionService(
			ICadreSurveyTransactionService cadreSurveyTransactionService) {
		this.cadreSurveyTransactionService = cadreSurveyTransactionService;
	}


	public IWebServiceHandlerService1 getWebServiceHandlerService1() {
		return webServiceHandlerService1;
	}


	public void setWebServiceHandlerService1(
			IWebServiceHandlerService1 webServiceHandlerService1) {
		this.webServiceHandlerService1 = webServiceHandlerService1;
	}


	@POST
	@Path("/loginFieldDataUser")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Object loginFieldDataUser (UserLoginUtils inputs)
	{
		UserResponseVO out=null;
		Map<String,String> userDetails= new HashMap<String, String>();
		userDetails.put("","");
		try{
			
			out=(UserResponseVO) webServiceHandlerService1.checkForUserAuthentication(inputs);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		if(out==null)
			return "{\"status\":\"login failure\"}";
		out.setStatus("Success");

		 return out;
	}
	
	@POST
	@Path("/saveFieldData")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Object saveFieldData (SurveyResponceVO inputs)
	{
		LOG.debug("inside save field data");
		LOG.error(inputs.toString());
		
		ResultStatus out=null;
		Map<String,String> userDetails= new HashMap<String, String>();
		userDetails.put("","");
		try{ 
			out=(ResultStatus) webServiceHandlerService1.saveSurveyFieldUsers(inputs);
			
			//out=(UserResponseVO) webServiceHandlerService1.checkForUserAuthentication(inputs);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		if(!out.getMessage().equalsIgnoreCase("Success"))
			return "{\"status\":\"Failure\"}";
		
		 else return "{\"status\":\"Success\"}";
	}
	
	
	@POST
	@Path("/saveUserTrackingDetails")
	@Consumes(MediaType.APPLICATION_JSON)
	public Object saveUserLocations (UserLocationTrackingVo  userLocationTrackingVo)
	{
		
		LOG.debug("inside save field data");
		LOG.debug(userLocationTrackingVo.toString());
		
		
		ResultStatus out=null;

	
				
		String imeiNo=userLocationTrackingVo.getImeiNo();
		
		if(imeiNo==null || imeiNo.isEmpty() || imeiNo.equalsIgnoreCase("Null"))
		{
			LOG.debug("imei number null please check");
			 return "{\"status\":\"Failure\"}";
		}
		
		try{ 
			out=(ResultStatus) webServiceHandlerService1.saveUserTrackingLocation(userLocationTrackingVo);
			
			//out=(UserResponseVO) webServiceHandlerService1.checkForUserAuthentication(inputs);
		}
		catch(Exception e)
		{
			
			LOG.error("Exception raised in saveSurveyUserTrackingDetails service in SurveyDataDetailsService", e);

		}
		
		if(out.getMessage()!=null&&!out.getMessage().equalsIgnoreCase("Success"))
			  return "{\"status\":\"Failure\"}";
		
		 else return "{\"status\":\"Success\"}";
	}
	
	
	
	@POST
	@Path("/loginFieldDataUserForCadre")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Object loginFieldDataUserForCadre (UserLoginUtils inputs)
	{
		LoginResponceVO out=null;
		Map<String,String> userDetails= new HashMap<String, String>();
		userDetails.put("","");
		try{
			
			out=(LoginResponceVO) webServiceHandlerService1.checkForUserAuthenticationForCadre(inputs);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		try {
			webServiceHandlerService1.savingUserDetailsWhoLoggedIn(inputs,out);
			
		} catch (Exception e) {
			LOG.error("Exception occured in loginFieldDataUserForCadre method in WebServiceHandler2 class. ");
		}
		if(out==null)
			return "{\"status\":\"login failure\"}";
		//out.setStatus("Success");
		out.setStatusMsg("Success");

		 return out;
	}
	
	
	
	@POST
	@Path("/saveFieldDataForCadre")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Object saveFieldDataForCadre (List<CadreRegistrationVO> inputs)
	{
		String isCheckReq = webServiceHandlerService1.getDynamicValueOfAKey();
		try{
		  if(inputs != null && inputs.size() > 0){
			if(isCheckReq != null && isCheckReq.trim().equalsIgnoreCase("ON")){
				boolean status = webServiceHandlerService1.checkHasAccess(inputs.get(0).getCreatedUserId());
				if(!status){
					return "{\"status\":\"Failure\"}";
				}
			}
		  }
		}catch(Exception e){
			LOG.error("Exception rised in tab record sync ",e);
		}
		LOG.error("returnd user:");
		LOG.debug("inside save field data for cadre");
		LOG.debug(inputs.toString());
		SurveyCadreResponceVO out=null;
		Map<String,String> userDetails= new HashMap<String, String>();
		userDetails.put("","");
		try{ 
			out=(SurveyCadreResponceVO) webServiceHandlerService1.saveSurveyFieldUsersForCadre(inputs);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		if(out != null && out.getStatus() != null){
			if(!out.getStatus().equalsIgnoreCase("Success"))
				return "{\"status\":\"Failure\"}";
			
			 else return "{\"status\":\"Success\"}";
		}else{
			return "{\"status\":\"Failure\"}";
		}
	}
	
	
	@POST
	@Path("/databaseCheckForCadreUser")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Object databaseCheckForCadreUser (UserLoginUtils inputs)
	{

		LoginResponceVO out=null;
		Map<String,String> userDetails= new HashMap<String, String>();
		userDetails.put("","");
		try{
			
			out=(LoginResponceVO) webServiceHandlerService1.databaseCheckForCadreUser(inputs);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		if(out==null)
			return "{\"status\":\"login failure\"}";
		out.setStatusMsg("DBINITIALCHECK");

		 return out;
	}
	
	
	/*@POST
	@Path("/genarateOTPForCadreTxn")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public CadreTransactionVO genarateOTPForCadreTxn(CadreTransactionVO inputVo)
	{
		return cadreSurveyTransactionService.genarateOTPAndSaveTxnDetails(inputVo);
		
	}*/
	
	
	@POST
	@Path("/storeReconciliationData")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String storeReconciliationData(ReconciliationVO inputVo)
	{
		return cadreSurveyTransactionService.saveReconciliationData(inputVo);
		
	}
	/*@POST
	@Path("/generateOTPCadreTxn")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String generateOTPCadreTxn(CadreTransactionVO inputVo)
	{
		return cadreSurveyTransactionService.genarateOTPAndSaveTxnDetails(inputVo);
		
	}
	@POST
	@Path("/updateTxnStatus")
	@Produces(MediaType.APPLICATION_JSON)
	public String updateTxnStatus(CadreTransactionVO inputVo)
	{
		return cadreSurveyTransactionService.updateTxnStatus(inputVo);
	}
	
	@POST
	@Path("/validateOTP")
	@Produces(MediaType.APPLICATION_JSON)
	public String validateOTP(CadreTransactionVO inputVo)
	{
		return cadreSurveyTransactionService.validateOTPForMobile(inputVo);
	}
	*/
		
	/*@POST
	@Path("/storeReconciliationFailureData")
	@Produces(MediaType.APPLICATION_JSON)
	public String storeReconciliationFailureData(ReconciliationFailureVO inputVo)
	{
		return cadreSurveyTransactionService.saveReconsilationFailedDetails(inputVo);
	}*/
	
	
	@GET
	@Path("/getAllVersionsOfAnApp/{appName}/{version}/{isDebug}")
	@Produces(MediaType.APPLICATION_JSON)
	public Object getAllVersionsOfAnApp(@PathParam("appName") String appName,@PathParam("version") Double version,@PathParam("isDebug") String isDebug)
	{
		LOG.debug("inside getAllVersionsOfAnApp");
		 Boolean includeTest = false;
		 if(isDebug.equalsIgnoreCase("true")){
			 includeTest = true;
		 }
		return webServiceHandlerService1.getAllVersionsOfAnApp(appName, version, includeTest);
	}
	@GET
	@Path("/getAllUpdatesByVersion/{appName}/{version}")
	@Produces(MediaType.APPLICATION_JSON)
	public Object getAllUpdatesByVersion(@PathParam("appName") String appName,@PathParam("version") Double version)
	{
		LOG.debug("inside getAllUpdatesByVersion");
		return webServiceHandlerService1.getAllUpdatesByVersion(appName, version);
	}
	@POST
	@Path("/updatePendingAmount")
	@Produces(MediaType.APPLICATION_JSON)
	public String updatePendingAmount(CadreTransactionVO inputVo)
	{
		return cadreSurveyTransactionService.updatePendingAmount(inputVo);
	}
	
	@POST
	@Path("/saveFieldDataForOnlineCadre")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Object saveFieldDataForOnlineCadre(CadreRegistrationVO input)
	{
		LOG.debug("inside save field data for saveFieldDataForOnlineCadre");
		LOG.debug(input.toString());
          List<CadreRegistrationVO> inputs = new ArrayList<CadreRegistrationVO>();
          inputs.add(input);
		SurveyCadreResponceVO out=null;
		Map<String,String> userDetails= new HashMap<String, String>();
		userDetails.put("","");
		try{ 
			if(input.getDobStr() != null && input.getDobStr().trim().length() == 10){
				String[] dob = input.getDobStr().trim().split("/"); //26/08/1991
				String reqDob = dob[2]+"-"+dob[1]+"-"+dob[0];//1991-08-26
				input.setDobStr(reqDob);
			}else{
				input.setDobStr(null);
			}
			if(input.getSurveyTimeStr() != null && input.getSurveyTimeStr().trim().length() > 0){
				input.setSurveyTimeStr(input.getSurveyTimeStr().replace("T", " ").replace("[\"", "").replace("\"]", ""));
				LOG.error(input.getSurveyTimeStr());
				System.out.println(input.getSurveyTimeStr());
			}
			if(input.getMandalId() != null && input.getMandalId().trim().length() > 0){
				if(input.getMandalId().contains("M-")){
					input.setMuncipalityId(input.getMandalId().replace("M-", ""));
					input.setWardId(input.getPanchayatId());
					input.setMandalId(null);
					input.setPanchayatId(null);
				}else if(input.getMandalId().contains("G-")){
					input.setMuncipalityId("20");
					input.setWardId(input.getPanchayatId());
					input.setMandalId(null);
					input.setPanchayatId(null);
				}
			}
			if(input.getOccupationId() != null && input.getOccupationId().longValue() > 0){
				input.setOccupationId(WebServiceHandler2.getMap().get(input.getOccupationId()));
			}
			out=(SurveyCadreResponceVO) webServiceHandlerService1.saveSurveyFieldUsersForCadreOnline(inputs);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		if(out != null && out.getStatus() != null){
			if(!out.getStatus().equalsIgnoreCase("Success"))
				return "{\"status\":\"Failure\",\"orderId\":\""+input.getOrderId()+"\",\"onlineId\":\""+input.getOnlineId()+"\"}";
			
			 else return "{\"status\":\"Success\",\"orderId\":\""+input.getOrderId()+"\",\"onlineId\":\""+input.getOnlineId()+"\"}";
		}else{
			return "{\"status\":\"Failure\",\"orderId\":\""+input.getOrderId()+"\",\"onlineId\":\""+input.getOnlineId()+"\"}";
		}
	}
	@GET
    @Path("/registrationStatus/{data}")
	@Produces(MediaType.TEXT_PLAIN)
	public Object saveFieldDataForCadre3(@PathParam("data") String data)
	{
		LOG.debug("inside save field data for cadre");
		LOG.debug(data.toString());
		Map<String,String> userDetails= new HashMap<String, String>();
		userDetails.put("","");
		try{ 
			CadreRegistrationVO vo = new CadreRegistrationVO();
			String[] inputsArray = data.split("&");
			for(String input:inputsArray){
				String[] parameterArray = input.split("=");
				if(parameterArray.length > 0){
					if(parameterArray[0].equalsIgnoreCase("mobilenumber")){
						if(parameterArray.length > 1){
							vo.setMobileNumber(parameterArray[1]);
						}
					}else if(parameterArray[0].equalsIgnoreCase("message")){
						if(parameterArray.length > 1){
							vo.setArea(parameterArray[1]);//vo.setRegistrationCount(parameterArray[1]);
						}
					}
				}
			}
			LOG.error("In Web Service mobilenumber"+vo.getMobileNumber()+",message"+vo.getArea());
			if((vo.getMobileNumber() != null && vo.getMobileNumber().trim().length() >0) || (vo.getArea() != null && vo.getArea().trim().length() >0)){
			     return webServiceHandlerService1.saveStatus(vo);
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
	
	public static Map<Long,Long> getMap(){
		Map<Long,Long> map = new HashMap<Long,Long>();
		map.put(1L,17L);
		map.put(2L,18L);
		map.put(3L,1L);
		map.put(4L,19L); 
		map.put(5L,20L);  
		map.put(6L,21L); 
		map.put(7L,22L); 
		map.put(43L,3L); 
		map.put(8L,23L); 
		map.put(9L,24L);  
		map.put(10L,25L); 
		map.put(11L,26L); 
		map.put(50L,59L); 
		map.put(12L,27L); 
		map.put(13L,28L); 
		map.put(14L,29L); 
		map.put(15L,30L); 
		map.put(16L,31L); 
		map.put(17L,32L); 
		map.put(18L,33L); 
		map.put(45L,54L); 
		map.put(19L,34L);       
		map.put(44L,12L); 
		map.put(20L,4L);  
		map.put(21L,35L); 
		map.put(23L,37L); 
		map.put(22L,36L); 
		map.put(25L,39L); 
		map.put(26L,40L);                    
		map.put(27L,15L);                       
		map.put(28L,14L);                       
		map.put(29L,41L);                       
		map.put(30L,42L);                  
		map.put(42L,15L);                       
		map.put(31L,43L);                       
		map.put(32L,44L);                       
		map.put(48L,57L);                       
		map.put(33L,45L);                       
		map.put(46L,55L);                       
		map.put(34L,46L);                       
		map.put(35L,47L);                       
		map.put(36L,48L);                       
		map.put(37L,15L);                       
		map.put(49L,58L);                      
		map.put(38L,49L);                      
		map.put(39L,50L);                       
		map.put(47L,56L);                      
		map.put(24L,38L);                      
		map.put(40L,51L);                      
		map.put(41L,52L);  
		    return map;                 
	}
	
	@POST
	@Path("/checkValidCadreUser")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Object checkValidLoginOrNot(UserLoginUtils inputs)
	{

		LoginResponceVO out=null;
		Map<String,String> userDetails= new HashMap<String, String>();
		userDetails.put("","");
		try{
			
			return (LoginResponceVO) webServiceHandlerService1.checkValidLoginOrNot(inputs.getUserName(),inputs.getPassWord(), inputs.getImei1(), inputs.getImei2(), inputs.getVersion());
			
		}
		catch(Exception e)
		{
			out = new LoginResponceVO();
			out.setStatus("error");
			LOG.error("Exception rised in checkValidLoginOrNot",e);
			return out;
		}
		
	}
	
	
	@POST
	@Path("/storeTabUsersDetails")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Object getTabUsersRecordsDetails(List<TabRecordsStatusVO> tabRecordsStatusVOList)
	{
		try{
			  String status = webServiceHandlerService1.getTabUsersRecordsDetails(tabRecordsStatusVOList);
			  
			  if(status.equalsIgnoreCase("success"))
				 {				  
					  return "{\"status\":\"Success\"}";
				 }
				  else
				  {
					  return "{\"status\":\"Failure\"}";
				  }
		}
		catch(Exception e)
		{
			LOG.error("Exception Occured in getTabUsersLoginDetails() Method, Exception is ",e);
			 return "{\"status\":\"Failure\"}";
		}
	}
	
	@POST
	@Path("/storeLoginUserDetails")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Object storeLoginUserDetails(TabRecordsStatusVO tabRecordsStatusVO)
	{
		try{			
			 String status  = webServiceHandlerService1.getTabUsersLoginDetails(tabRecordsStatusVO);
			
			 if(status.equalsIgnoreCase("success"))
			 {				  
				  return "{\"status\":\"Success\"}";
			 }
			  else
			  {
				  return "{\"status\":\"Failure\"}";
			  }
		}
		catch(Exception e)
		{
			LOG.error("Exception Occured in storeLoginUserDetails() Method, Exception is ",e);
			return "{\"status\":\"Failure\"}";
		}
	}
	
	@POST
	@Path("/searchVoterDetails")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Object voterSearchDetails(VoterWebServiceDataVO dataVO)
	{
		try{
			if(dataVO.getConstituencyId() == null || dataVO.getConstituencyId().longValue() == 0){
				return new ArrayList<VoterWebServiceDataVO>();
			}
			if(dataVO.getPageNo() == null || dataVO.getPageNo().intValue() == 0 ){
				return new ArrayList<VoterWebServiceDataVO>();
			}
			if((dataVO.getName() == null || dataVO.getName().trim().length() <3) && (dataVO.getVoterCardNo() == null || dataVO.getVoterCardNo().trim().length() <3)){
				return new ArrayList<VoterWebServiceDataVO>();
			}
			List<VoterWebServiceDataVO> searchResult  = webServiceHandlerService1.voterSearchDetails(dataVO.getConstituencyId(),dataVO.getName(),dataVO.getVoterCardNo(),(dataVO.getPageNo()-1)*30);
			
			 return searchResult;
		}
		catch(Exception e)
		{
			LOG.error("Exception Occured in voterSearchDetails() Method, Exception is ",e);
			return new ArrayList<VoterWebServiceDataVO>();
		}
	}
	
	@POST
	@Path("/sinkMissingData")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Object sinkMissingData(List<SinkVO> inputs)
	{
		Object obj = null;
		try
		{
			List<SinkVO> returnList = webServiceHandlerService1.sinkMissingData(inputs);
			obj = returnList;
		} 
		catch (Exception e) 
		{
			obj = null;
			LOG.error("Exception Occured in sinkPendingData() Method, Exception is ",e);
		}
		return obj;
		
	}
	
	@POST
	@Path("/sinkImageMissingData")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public CadreImageVO sinkImageMissingData(CadreImageVO cadreImageVO)
	{
		CadreImageVO result = new CadreImageVO();
		try
		{
			result = webServiceHandlerService1.sinkImageMissingData(cadreImageVO);
		} 
		catch (Exception e) 
		{
			result = cadreImageVO;
			result.setImageStr("");
			result.setStatus("FAILURE");
			LOG.error("Exception Occured in sinkImageMissingData() Method, Exception is ",e);
		}
		return result;
	}
	
	@POST
	@Path("/storeLoginUserKeyDetails")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Object storeLoginUserKeyDetails(TabRecordsStatusVO tabRecordsStatusVO)
	{
		try{			
			 String status  = webServiceHandlerService1.getTabUsersLoginKeyDetails(tabRecordsStatusVO);
			
			 if(status.equalsIgnoreCase("success"))
			 {				  
				  return "{\"status\":\"Success\"}";
			 }
			  else
			  {
				  return "{\"status\":\"Failure\"}";
			  }
		}
		catch(Exception e)
		{
			LOG.error("Exception Occured in storeLoginUserDetails() Method, Exception is ",e);
			return "{\"status\":\"Failure\"}";
		}
	}
	
	/*
	@POST
	@Path("/searchTdpCadreDetailsBySearchCriteria/{constituencyId}/{name}/{memberShipCardNo}/{voterCardNo}/{refNo}/{mobileNo}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Object searchTdpCadreDetailsBySearchCriteria(@PathParam("constituencyId") String constituencyId,@PathParam("name") String name,
			@PathParam("memberShipCardNo") String memberShipCardNo,@PathParam("voterCardNo") String voterCardNo, @PathParam("refNo")String refNo,@PathParam("mobileNo") String mobileNo)
	{
		try{			
			return webServiceHandlerService1.searchTdpCadreDetailsBySearchCriteria(constituencyId,name,memberShipCardNo,voterCardNo,refNo,mobileNo);			
		}
		catch(Exception e)
		{
			LOG.error("Exception Occured in searchTdpCadreDetailsBySearchCriteria() Method, Exception is ",e);
			return "{\"status\":\"Failure\"}";
		}
	}
	
	@POST
	@Path("/searchTdpCadreDetailsBySearchCriteriaForCallCenter/{memberShipCardNo}/{mobileNo}")
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
	}*/
	
	/*@POST
	@Path("/searchTdpCadreDetailsByVoterCardNoForCallCenter/{voterIdCardNo}/{isFamilyVoter}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Object searchTdpCadreDetailsByVoterCardNoForCallCenter(@PathParam("voterIdCardNo") String voterIdCardNo,@PathParam("isFamilyVoter") String isFamilyVoter)
	{
		try{			
			return webServiceHandlerService1.searchTdpCadreDetailsBySVoterIdCardNo(voterIdCardNo,isFamilyVoter);
			
		}
		catch(Exception e)
		{
			LOG.error("Exception Occured in searchTdpCadreDetailsByVoterCardNoForCallCenter() Method, Exception is ",e);
			return "{\"status\":\"Failure\"}";
		}
	}*/
	
	@POST
	@Path("/saveFieldDataForAffliatedCadre")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Object saveFieldDataForAffliatedCadre (List<CadreRegistrationVO> inputs){
		String isCheckReq = webServiceHandlerService1.getDynamicValueOfAKey();
		try{
		  if(inputs != null && inputs.size() > 0){
			if(isCheckReq != null && isCheckReq.trim().equalsIgnoreCase("ON")){
				boolean status = webServiceHandlerService1.checkHasAccess(inputs.get(0).getCreatedUserId());
				if(!status){
					return "{\"status\":\"Failure\"}";
				}
			}
		  }
		}catch(Exception e){
			LOG.error("Exception rised in tab record sync ",e);
		}
		LOG.error("returnd user:");
		LOG.debug("inside save field data for cadre");
		LOG.debug(inputs.toString());
		SurveyCadreResponceVO out=null;
		Map<String,String> userDetails= new HashMap<String, String>();
		userDetails.put("","");
		try{ 
			out=(SurveyCadreResponceVO) webServiceHandlerService1.saveSurveyFieldUsersForAffliatedCadre(inputs);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		if(out != null && out.getStatus() != null){
			if(!out.getStatus().equalsIgnoreCase("Success"))
				return "{\"status\":\"Failure\"}";
			
			 else return "{\"status\":\"Success\"}";
		}else{
			return "{\"status\":\"Failure\"}";
		}
	}
	
	
}
