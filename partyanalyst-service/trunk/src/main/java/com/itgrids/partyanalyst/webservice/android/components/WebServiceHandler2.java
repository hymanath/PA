package com.itgrids.partyanalyst.webservice.android.components;

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

import com.itgrids.partyanalyst.dto.CadreRegistrationVO;
import com.itgrids.partyanalyst.dto.CadreTransactionVO;
import com.itgrids.partyanalyst.dto.LoginResponceVO;
import com.itgrids.partyanalyst.dto.ReconciliationVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SurveyCadreResponceVO;
import com.itgrids.partyanalyst.dto.SurveyResponceVO;
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
		LOG.debug(inputs.toString());
		
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
	@POST
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
			if(input.getMandalId() != null && input.getMandalId().trim().length() > 0){
				if(input.getMandalId().contains("M-")){
					input.setMuncipalityId(input.getMandalId().replace("M-", ""));
					input.setWardId(input.getPanchayatId());
					input.setMandalId(null);
					input.setPanchayatId(null);
				}
			}
			out=(SurveyCadreResponceVO) webServiceHandlerService1.saveSurveyFieldUsersForCadreOnline(inputs);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		if(out != null && out.getStatus() != null){
			if(!out.getStatus().equalsIgnoreCase("Success"))
				return "{\"status\":\"Failure\",\"orderId\":\""+input.getOrderId()+"\"}";
			
			 else return "{\"status\":\"Success\"}";
		}else{
			return "{\"status\":\"Failure\",\"orderId\":\""+input.getOrderId()+"\"}";
		}
	}
	
	@GET
	@Path("/registrationStatus/{data}")
	@Produces(MediaType.APPLICATION_JSON)
	public Object saveFieldDataForCadre3(@PathParam("data") String inputs)
	{
		LOG.debug("inside save field data for cadre");
		LOG.debug(inputs.toString());
		SurveyCadreResponceVO out=null;
		Map<String,String> userDetails= new HashMap<String, String>();
		userDetails.put("","");
		try{ 
			/*CadreRegistrationVO vo = new CadreRegistrationVO();
			String[] inputsArray = inputs.split("&");
			for(String input:inputsArray){
				String[] parameterArray = input.split("=");
				if(parameterArray.length > 0){
					if(parameterArray[0].equalsIgnoreCase("mobilenumber")){
						if(parameterArray.length > 1){
							vo.setMobileNumber(parameterArray[1]);
						}
					}else if(parameterArray[0].equalsIgnoreCase("message")){
						if(parameterArray.length > 1){
							vo.setRegistrationCount(parameterArray[1]);
						}
					}else if(parameterArray[0].equalsIgnoreCase("receivedon")){
						if(parameterArray.length > 1){
							vo.setDate(parameterArray[1]);
						}
					}
				}
			}
			LOG.error("mobilenumber"+vo.getMobileNumber()+",message"+vo.getRegistrationCount()+",receivedon"+vo.getDate());
			if((vo.getMobileNumber() != null && vo.getMobileNumber().trim().length() >0) || (vo.getRegistrationCount() != null && vo.getRegistrationCount().trim().length() >0) || (vo.getDate() != null && vo.getDate().trim().length() >0)){
			     return webServiceHandlerService1.saveStatus(vo);
			}else{
				return "Invalid Inputs !";
			}*/
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return "Error Occured";
		}
		return "success";
	}
}
