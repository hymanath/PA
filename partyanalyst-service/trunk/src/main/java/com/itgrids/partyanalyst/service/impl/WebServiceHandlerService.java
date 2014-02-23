package com.itgrids.partyanalyst.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.IBoothDAO;
import com.itgrids.partyanalyst.dao.IBoothPublicationVoterDAO;
import com.itgrids.partyanalyst.dao.IMobileAppPingingDAO;
import com.itgrids.partyanalyst.dao.IMobileAppUserAccessKeyDAO;
import com.itgrids.partyanalyst.dao.IMobileAppUserDAO;
import com.itgrids.partyanalyst.dao.IMobileAppUserProfileDAO;
import com.itgrids.partyanalyst.dao.IPingingTypeDAO;
import com.itgrids.partyanalyst.dao.IUserVoterDetailsDAO;
import com.itgrids.partyanalyst.dao.IVoiceRecordingDetailsDAO;
import com.itgrids.partyanalyst.dao.IVoterBoothActivitiesDAO;
import com.itgrids.partyanalyst.dao.IVoterTagDAO;
import com.itgrids.partyanalyst.dao.IWebServiceBaseUrlDAO;
import com.itgrids.partyanalyst.dto.CadreInfo;
import com.itgrids.partyanalyst.dto.FlagVO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.VoterDetailsVO;
import com.itgrids.partyanalyst.dto.WSResultVO;
import com.itgrids.partyanalyst.model.Booth;
import com.itgrids.partyanalyst.model.MobileAppPinging;
import com.itgrids.partyanalyst.model.MobileAppUser;
import com.itgrids.partyanalyst.model.MobileAppUserAccessKey;
import com.itgrids.partyanalyst.model.UserVoterDetails;
import com.itgrids.partyanalyst.model.VoterBoothActivities;
import com.itgrids.partyanalyst.model.VoterTag;
import com.itgrids.partyanalyst.service.IInfluencingPeopleService;
import com.itgrids.partyanalyst.service.ILoginService;
import com.itgrids.partyanalyst.service.IMailService;
import com.itgrids.partyanalyst.service.IMobileService;
import com.itgrids.partyanalyst.service.ISmsService;
import com.itgrids.partyanalyst.service.IVoiceSmsService;
import com.itgrids.partyanalyst.service.IVoterReportService;
import com.itgrids.partyanalyst.service.IWebServiceHandlerService;
import com.itgrids.partyanalyst.utils.DateUtilService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.itgrids.partyanalyst.webservice.utils.VoterTagVO;


public class WebServiceHandlerService implements IWebServiceHandlerService {
	
	private static final Logger log = Logger.getLogger(WebServiceHandlerService.class);
	
	private ILoginService loginService;
	
	private IMobileService mobileService;

	private ISmsService smsCountrySmsService;
	private IMailService mailService;
	
	private IMobileAppUserProfileDAO mobileAppUserProfileDAO;
	
	private IMobileAppUserDAO mobileAppUserDAO;
	private IMobileAppUserAccessKeyDAO mobileAppUserAccessKeyDAO ;
	private IWebServiceBaseUrlDAO webServiceBaseUrlDAO;
	private IVoiceSmsService voiceSmsService;
	private IVoiceRecordingDetailsDAO voiceRecordingDetailsDAO;
	private IPingingTypeDAO pingingTypeDAO;
	private IMobileAppPingingDAO mobileAppPingingDAO;
	private IBoothPublicationVoterDAO boothPublicationVoterDAO;
	private IUserVoterDetailsDAO userVoterDetailsDAO;
	private IBoothDAO boothDAO;
	private CadreManagementService cadreManagementService;
	private CastePredictionService castePredictionService; 
	private IInfluencingPeopleService influencingPeopleService;
	private IVoterReportService voterReportService;
	private IVoterTagDAO voterTagDAO;
	private IVoterBoothActivitiesDAO voterBoothActivitiesDAO;
	private DateUtilService dateUtilService = new DateUtilService();

	public IVoterBoothActivitiesDAO getVoterBoothActivitiesDAO() {
		return voterBoothActivitiesDAO;
	}

	public void setVoterBoothActivitiesDAO(
			IVoterBoothActivitiesDAO voterBoothActivitiesDAO) {
		this.voterBoothActivitiesDAO = voterBoothActivitiesDAO;
	}

	public IVoterTagDAO getVoterTagDAO() {
		return voterTagDAO;
	}

	public void setVoterTagDAO(IVoterTagDAO voterTagDAO) {
		this.voterTagDAO = voterTagDAO;
	}

	public IVoterReportService getVoterReportService() {
		return voterReportService;
	}

	public void setVoterReportService(IVoterReportService voterReportService) {
		this.voterReportService = voterReportService;
	}

	public IInfluencingPeopleService getInfluencingPeopleService() {
		return influencingPeopleService;
	}

	public void setInfluencingPeopleService(
			IInfluencingPeopleService influencingPeopleService) {
		this.influencingPeopleService = influencingPeopleService;
	}
	
	
	
	
	public CastePredictionService getCastePredictionService() {
		return castePredictionService;
	}

	public void setCastePredictionService(
			CastePredictionService castePredictionService) {
		this.castePredictionService = castePredictionService;
	}

	public CadreManagementService getCadreManagementService() {
		return cadreManagementService;
	}

	public void setCadreManagementService(
			CadreManagementService cadreManagementService) {
		this.cadreManagementService = cadreManagementService;
	}

	public IBoothDAO getBoothDAO() {
		return boothDAO;
	}

	public void setBoothDAO(IBoothDAO boothDAO) {
		this.boothDAO = boothDAO;
	}

	public IUserVoterDetailsDAO getUserVoterDetailsDAO() {
		return userVoterDetailsDAO;
	}

	public void setUserVoterDetailsDAO(IUserVoterDetailsDAO userVoterDetailsDAO) {
		this.userVoterDetailsDAO = userVoterDetailsDAO;
	}

	public IBoothPublicationVoterDAO getBoothPublicationVoterDAO() {
		return boothPublicationVoterDAO;
	}

	public void setBoothPublicationVoterDAO(
			IBoothPublicationVoterDAO boothPublicationVoterDAO) {
		this.boothPublicationVoterDAO = boothPublicationVoterDAO;
	}

	public IPingingTypeDAO getPingingTypeDAO() {
		return pingingTypeDAO;
	}

	public void setPingingTypeDAO(IPingingTypeDAO pingingTypeDAO) {
		this.pingingTypeDAO = pingingTypeDAO;
	}

	public IMobileAppPingingDAO getMobileAppPingingDAO() {
		return mobileAppPingingDAO;
	}

	public void setMobileAppPingingDAO(IMobileAppPingingDAO mobileAppPingingDAO) {
		this.mobileAppPingingDAO = mobileAppPingingDAO;
	}

	public IVoiceRecordingDetailsDAO getVoiceRecordingDetailsDAO() {
		return voiceRecordingDetailsDAO;
	}

	public IWebServiceBaseUrlDAO getWebServiceBaseUrlDAO() {
		return webServiceBaseUrlDAO;
	}

	public void setWebServiceBaseUrlDAO(IWebServiceBaseUrlDAO webServiceBaseUrlDAO) {
		this.webServiceBaseUrlDAO = webServiceBaseUrlDAO;
	}

	public void setVoiceRecordingDetailsDAO(
			IVoiceRecordingDetailsDAO voiceRecordingDetailsDAO) {
		this.voiceRecordingDetailsDAO = voiceRecordingDetailsDAO;
	}

	public IVoiceSmsService getVoiceSmsService() {
		return voiceSmsService;
	}

	public void setVoiceSmsService(IVoiceSmsService voiceSmsService) {
		this.voiceSmsService = voiceSmsService;
	}

	public IMobileAppUserAccessKeyDAO getMobileAppUserAccessKeyDAO() {
		return mobileAppUserAccessKeyDAO;
	}

	public void setMobileAppUserAccessKeyDAO(
			IMobileAppUserAccessKeyDAO mobileAppUserAccessKeyDAO) {
		this.mobileAppUserAccessKeyDAO = mobileAppUserAccessKeyDAO;
	}

	public IMobileAppUserDAO getMobileAppUserDAO() {
		return mobileAppUserDAO;
	}

	public void setMobileAppUserDAO(IMobileAppUserDAO mobileAppUserDAO) {
		this.mobileAppUserDAO = mobileAppUserDAO;
	}

	public IMobileAppUserProfileDAO getMobileAppUserProfileDAO() {
		return mobileAppUserProfileDAO;
	}

	public void setMobileAppUserProfileDAO(
			IMobileAppUserProfileDAO mobileAppUserProfileDAO) {
		this.mobileAppUserProfileDAO = mobileAppUserProfileDAO;
	}

	public IMailService getMailService() {
		return mailService;
	}

	public void setMailService(IMailService mailService) {
		this.mailService = mailService;
	}

	public ISmsService getSmsCountrySmsService() {
		return smsCountrySmsService;
	}

	public void setSmsCountrySmsService(ISmsService smsCountrySmsService) {
		this.smsCountrySmsService = smsCountrySmsService;
	}

	public IMobileService getMobileService() {
		return mobileService;
	}

	public void setMobileService(IMobileService mobileService) {
		this.mobileService = mobileService;
	}

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
	public ResultStatus checkUserAuthenticationAndUpdateAuthorisedTime(String userId,String macAdressId)
	{
		try{
		List<Object> mobileAppUserId = mobileAppUserDAO.checkUniqueCode(userId);
		if(mobileAppUserId != null)
		{
		List<Object> pingingTypeId = pingingTypeDAO.getPingingTypeIdByType(IConstants.App_Authorization);
		saveMobileAppPingIngDetails((Long) mobileAppUserId.get(0),(Long)pingingTypeId.get(0),null,null);
		}
		return mobileService.checkAuthenticateUserAndUpdateLastAuthorisedTime(userId, macAdressId);
		}
		catch(Exception e)
		{
			log.error("Exception raised in checkUserAuthenticationAndUpdateAuthorisedTime  method in WebServiceHandlerService");
			return null;
		}
		
	}
	
	public ResultStatus sendSmsToUser(String uniquecode)
	{
		ResultStatus resultStatus = new ResultStatus();
		try{
			List<Object> mobileAppUserId = mobileAppUserDAO.checkUniqueCode(uniquecode);
			
			if(mobileAppUserId == null || mobileAppUserId .size() == 0)
				resultStatus.setResultCode(ResultCodeMapper.DATA_NOT_FOUND);
			
			if(mobileAppUserId != null)
			{
				List<Object> pingingTypeId = pingingTypeDAO.getPingingTypeIdByType(IConstants.Request_For_Forget_Pwd_Access_Key);
				saveMobileAppPingIngDetails((Long) mobileAppUserId.get(0),(Long)pingingTypeId.get(0),null,null);
				List<Object[]> list =mobileAppUserProfileDAO.getMobileNoByUniquecode(uniquecode);
				
				if(list == null || list.size() == 0)
					resultStatus.setResultCode(ResultCodeMapper.FAILURE);
				
				if(list != null && list.size() > 0)
				{
					for(Object[] params : list)
					{
						if(params[0] == null)
							resultStatus.setResultCode(ResultCodeMapper.FAILURE);
						else
						{
							String[] mobile = {params[0].toString()};
							String[] admingroupMobileNos = {IConstants.ADMINGROUPMOBILE};
							String name = params[1].toString()+" " +params[2].toString();
							String smsText = "Dear "+name+" your Request For forgot password is received. We will send Access key to this Mobile shortly.";
							String smsText1 = "Hi Admin Group,"+name+" requested for forgot access key, verify him and send a Access key as soon as possible.";
							
							resultStatus = smsCountrySmsService.sendSmsFromAdmin(smsText,true,mobile);
							smsCountrySmsService.sendSmsFromAdmin(smsText1,true,admingroupMobileNos);
							
							if(name != null && name !="")
								mailService.sendEmailToAdminGroupForAccessKey(name);
						}
					}
				}
			}
		
		}
		catch (Exception e) {
			e.printStackTrace();
			return resultStatus;
		}
		return resultStatus;
	
	}
	

	public ResultStatus updatePassword(String uniqueCode,String pwd,String accessKey)
	{
		ResultStatus resultStatus = new ResultStatus();
		try{
			
			List<Object[]> list = mobileAppUserAccessKeyDAO.checkUniqueCodeByAccesskey(uniqueCode,accessKey);
			if(list == null || list.size() == 0)
				resultStatus.setResultCode(ResultCodeMapper.DATA_NOT_FOUND);
			if(list != null && list.size() > 0)
			{
				for(Object[] params : list)
				{
				List<Object> pingingTypeId = pingingTypeDAO.getPingingTypeIdByType(IConstants.Validate_User_Access_Key);	
				saveMobileAppPingIngDetails((Long)params[0],(Long)pingingTypeId.get(0),null,null);
				MobileAppUser mobileAppUser = mobileAppUserDAO.get((Long)params[0]);
				mobileAppUser.setPassword(pwd);
				mobileAppUser = mobileAppUserDAO.save(mobileAppUser);
				MobileAppUserAccessKey mobileAppUserAccessKey = mobileAppUserAccessKeyDAO.get((Long)params[1]);
				mobileAppUserAccessKey.setIsUsed("true");
				mobileAppUserAccessKeyDAO.save(mobileAppUserAccessKey);
				resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
				}
			}
		
		}
		catch (Exception e) {
			e.printStackTrace();
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
		}
		return resultStatus;
		
	}
	public List<WSResultVO> getUserVoiceRecordedFiles(String uniqueCode)
	{
		 List<WSResultVO> result = new ArrayList<WSResultVO>();
		try{
			List<Object> userId = mobileAppUserDAO.checkUniqueCode(uniqueCode);
				if(userId == null || userId .size() == 0)
					return result;
				else
				{
					List<Object> pingingTypeId = pingingTypeDAO.getPingingTypeIdByType(IConstants.Get_User_Voice_Recording_Files);
					saveMobileAppPingIngDetails((Long)userId.get(0),(Long)pingingTypeId.get(0),null,null);
					List<Object[]> list = voiceRecordingDetailsDAO.getAllTheRecordingDetailsOfUser((Long)userId.get(0));
						
					if(list == null || list.size() == 0)
							return null;
					else
					{
						
						 for(Object[] params : list)
						 {
							 WSResultVO wsResultVO = new WSResultVO();
							 wsResultVO.setId((Long)params[2]);
							 wsResultVO.setName(params[0].toString());
							 wsResultVO.setDescription(params[1].toString());
							 wsResultVO.setLocation(IConstants.LIVE_VOICE_RECORDINGS_URL+params[2].toString()+"/"+params[0]);
							 result.add(wsResultVO);		
						 }
					}
				}
				
		  }
		catch(Exception e)
		{
			e.printStackTrace();
			return result;
		}
		return result;
	}
	public String sendVoiceSMS(String uniqueCode,String mobileNos,String FilePath)
	{
		ResultStatus resultStatus = new ResultStatus();
		String result = "";
		String smsText = "Hi";
		StringBuffer audioFilePath = new StringBuffer();
		
		try{
			List<Object> userId = mobileAppUserDAO.checkUniqueCode(uniqueCode);
			if(userId == null || userId .size() == 0)
				result ="data not found";
			else
			{
				List<Object> pingingTypeId = pingingTypeDAO.getPingingTypeIdByType(IConstants.Send_Voice_Sms);
				saveMobileAppPingIngDetails((Long)userId.get(0),(Long)pingingTypeId.get(0),null,null);	
			audioFilePath.append(IConstants.LIVE_VOICE_RECORDINGS_URL+"/"+(Long)userId.get(0)+"/"+FilePath);
				
			//audioFilePath.append("http://122.169.253.134:8080/TDP/voice_recording/test6.wav");
			result = voiceSmsService.sendVoiceSMS(audioFilePath.toString(),(Long)userId.get(0),mobileNos,null,smsText,null);
			}
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public ResultStatus sendSMS(String uniqueCode,String mobileNos,String message)
	{
		ResultStatus resultStatus = new ResultStatus();
		try{
			
			List<Object> mobileAppUserId = mobileAppUserDAO.checkUniqueCode(uniqueCode);
			
			if(mobileAppUserId == null || mobileAppUserId .size() == 0)
				resultStatus.setResultCode(ResultCodeMapper.DATA_NOT_FOUND);
			else
			{
				List<Object> pingingTypeId = pingingTypeDAO.getPingingTypeIdByType(IConstants.Send_Text_Sms);
				saveMobileAppPingIngDetails((Long)mobileAppUserId.get(0),(Long)pingingTypeId.get(0),null,null);
				if(!(message.toString().equals("\"\"") && mobileNos.toString().equals("\"\"")))
				{
					String[] mobilenoarr = mobileNos.split(",");
					resultStatus = smsCountrySmsService.sendSmsFromAdmin(message, true, mobilenoarr);
				}
			
			}
		}
		catch (Exception e) {
			log.error("Exception Occured in sendSMS()",e);
			e.printStackTrace();
		}
		return resultStatus;
	}
	
	public String getBaseUrlForApp(String appName)
	{
		try{
			
			
			String url = webServiceBaseUrlDAO.getBaseURLForAnApp(appName);
			
			if(url == null || url.isEmpty())
				return "FAIL:URL not found"; 
			else
				return url.trim();
		}catch(Exception e)
		{
			log.error("Exception Occured, Exception is - "+e);
			return "FAIL:URL not found"; 
		}
	}
	
	public void saveMobileAppPingIngDetails(Long mobileAppUserId,Long pingTypeId,Double longitude,Double latitude)
	{
		try{
			DateUtilService date = new DateUtilService();
			MobileAppPinging mobileAppPinging = new MobileAppPinging();
			mobileAppPinging.setLatitude(latitude);
			mobileAppPinging.setLongitude(longitude);
			mobileAppPinging.setPingingType(pingingTypeDAO.get(pingTypeId));
			mobileAppPinging.setMobileAppUser(mobileAppUserDAO.get(mobileAppUserId));
			mobileAppPinging.setPingTime(date.getCurrentDateAndTime());
			mobileAppPingingDAO.save(mobileAppPinging);
		}
		catch (Exception e) {
			log.error("Exception Occured in saveMobileAppPingIngDetails() method of WebServiceHandlerService, Exception is - "+e);
			e.printStackTrace();
		}
	}
	
	public VoterDetailsVO getVoterDetailsBasedOnVoterId(String voterCardNo){
		List<Object[]> dtlsList=boothPublicationVoterDAO.getConstyPublicationIdByVoterId(voterCardNo);
		//Gives ConstyId,BoothId,PublicationId,VoterId,Name,Age,Gender
		
		VoterDetailsVO vo=new VoterDetailsVO();
		
		Object[] obj=dtlsList.get(0);
		
		vo.setConstituencyId(Long.valueOf(obj[0].toString()));
		vo.setBoothId(Long.valueOf(obj[1].toString()));
		vo.setPublicationDateId(Long.valueOf(obj[2].toString()));
		vo.setVoterId(Long.valueOf(obj[3].toString()));	
		vo.setVoterName(obj[4].toString());
		vo.setAge(obj[5].toString());
		
		if(obj[6].toString().equalsIgnoreCase("M")){
			vo.setGender("Male");
		}else{
			vo.setGender("Female");
		}
		
		vo.setDistrictId(Long.valueOf(obj[7].toString()));
		if(obj[8].toString()!=null){
			vo.setRelativeName(obj[8].toString());
		}else{
			vo.setRelativeName("");
		}
		if(obj[9].toString()!=null){
			vo.setHouseNo(obj[9].toString());
		}else{
			vo.setHouseNo("");
		}
		
		
		
		/*List<Object[]> voterCasteData = userVoterDetailsDAO.getVoterDetailsForSurveyForm(vo.getVoterId(),1l);
		if(voterCasteData != null && voterCasteData.size() > 0)
		{
			for (Object[] parms : voterCasteData) {
				
			  vo.setCaste(parms[1].toString());
			  vo.setCasteStateId(((Long)parms[0]));
			}
		}
		else
		{
			  vo.setCaste("");
			  vo.setCasteStateId(0l);
		}*/
		
		List<Booth> boothDtls=null;
		if(vo.getBoothId()!=null){
			boothDtls=boothDAO.getBoothDetailsByBoothId(vo.getBoothId());
		}
		
		if(boothDtls!=null && boothDtls.size()>0){
			if(boothDtls.get(0).getTehsil()!=null){
				vo.setTehsilId(boothDtls.get(0).getTehsil().getTehsilId());
			}
			
			if(boothDtls.get(0).getLocalBody()!=null){
				vo.setLocalElectionBodyId(boothDtls.get(0).getLocalBody().getLocalElectionBodyId());
			}
			
			//IF THE SELECTED CONSTITUENCY UNDER GHMC -- GETTING WARD ID AND NAME
			if(boothDtls.get(0).getLocalBody().getElectionType().getElectionTypeId()==7){
				vo.setWardId(boothDtls.get(0).getLocalBodyWard().getLocalElectionBodyWard().getLocalElectionBodyWardId());
				vo.setWardName(boothDtls.get(0).getLocalBodyWard().getLocalElectionBodyWard().getWardName());
			}
		}
		
		
		List<UserVoterDetails> uvDtls=userVoterDetailsDAO.getVoterDetailsByVoterId(vo.getVoterId(),1l);
		
		if(uvDtls!=null && uvDtls.size()>0){
			UserVoterDetails uv=uvDtls.get(0);
			
			if(uv.getCasteState()!=null){
				Long casteCategoryId=uv.getCasteState().getCasteCategoryGroup().getCasteCategory().getCasteCategoryId();
				Long socialCategoryId=0l;
				if(casteCategoryId==1l){
					socialCategoryId=5l;
				}else if(casteCategoryId==2l){
					socialCategoryId=3l;
				}else if(casteCategoryId==3l){
					socialCategoryId=2l;
				}else if(casteCategoryId==4l){
					socialCategoryId=1l;
				}
				vo.setCasteGroupId(socialCategoryId);
				vo.setCaste(uv.getCasteState().getCaste().getCasteName());
			}else{
				vo.setCaste("");
				vo.setCasteGroupId(0l);
			}
			
			if(uv.getHamlet()!=null){
				vo.setHamletId(uv.getHamlet().getHamletId());
			}
			
			if(uv.getWard()!=null){
				vo.setWardId(uv.getWard().getConstituencyId());
			}
			if(uv.getMobileNo()!=null){
				vo.setMobileNo(uv.getMobileNo());
			}
			
		}
		
		
	
		return vo;
	}
	
	public String saveCadreFromAndroid(VoterDetailsVO voterDetails){
		CadreInfo cadreInfoVO=new CadreInfo();
		
		cadreInfoVO.setAccessType("STATE");
		cadreInfoVO.setAge(voterDetails.getAge());
		cadreInfoVO.setCadreLevel(9l);
		cadreInfoVO.setCadreLevelBooth(voterDetails.getBoothId().toString());
		cadreInfoVO.setCadreLevelConstituency(voterDetails.getConstituencyId().toString());
		cadreInfoVO.setCadreLevelDistrict(voterDetails.getDistrictId().toString());
		cadreInfoVO.setMandal(voterDetails.getTehsilId().toString());
		cadreInfoVO.setBooth(voterDetails.getBoothId().toString());
		
		if(voterDetails.getHamletId()!=null){
			cadreInfoVO.setVillage(voterDetails.getHamletId().toString());
		}
		
		cadreInfoVO.setWardId(voterDetails.getWardId());
		cadreInfoVO.setLocalElectionBodyId(voterDetails.getLocalElectionBodyId());
		//cadreInfoVO.setState();
		cadreInfoVO.setConstituencyID(voterDetails.getConstituencyId());
		cadreInfoVO.setDistrict(voterDetails.getDistrictId().toString());
		cadreInfoVO.setDobOption("Age");
		cadreInfoVO.setEducation(8l);
		cadreInfoVO.setFirstName(voterDetails.getVoterName());
		cadreInfoVO.setLastName("");
		cadreInfoVO.setMandal(voterDetails.getTehsilId().toString());
		cadreInfoVO.setMemberType("Active");
		cadreInfoVO.setSocialStatus(voterDetails.getCasteGroupId());
		cadreInfoVO.setEducation(8l);
		cadreInfoVO.setProfession(16l);
		cadreInfoVO.setStrCadreLevelValue(voterDetails.getBoothId().toString());
		cadreInfoVO.setGender(voterDetails.getGender());
		cadreInfoVO.setFirstFamilyMemberName("");
		cadreInfoVO.setSecondFamilyMemberName("");
		cadreInfoVO.setThirdFamilyMemberName("");
		cadreInfoVO.setFatherOrSpouseName(voterDetails.getRelativeName());
		cadreInfoVO.setHouseNo(voterDetails.getHouseNo());
		
		cadreInfoVO.setState("1");
		cadreInfoVO.setUserID(1l);
		cadreInfoVO.setUserType("politician");
		cadreInfoVO.setBloodGroup(0l);
		cadreInfoVO.setSameAsCA(true);
		if(cadreInfoVO.getMobile()=="" || cadreInfoVO.getMobile()==null){
			cadreInfoVO.setMobile("9999999999");
		}else{
			cadreInfoVO.setMobile(voterDetails.getMobileNo());
		}
		cadreInfoVO.setSavingFrom("Android");
		cadreInfoVO.setVoterId(voterDetails.getVoterId());
		//cadreInfoVO.setVillage(village)
		
		ResultStatus rs=cadreManagementService.saveCader(cadreInfoVO, null, "new");
		return "SUC";
	}
	
	public String updateVoterDetails(String uniqueCode,Long voterId,Long casteStateId,String mobileNumber){
		List<UserVoterDetails> uvDtls=userVoterDetailsDAO.getVoterDetailsByVoterId(voterId,1l);
		int mobUpdated=0;
		int casteUpdated=0;
		
		if(uvDtls!=null && uvDtls.size()>0){
			if(mobileNumber.trim()!="" && !mobileNumber.equalsIgnoreCase("null")){
				mobUpdated=userVoterDetailsDAO.updateVoterMobileNo(mobileNumber,voterId);
			}
			if(!casteStateId.equals(0l)){
				casteUpdated=userVoterDetailsDAO.updateCasteStateId(Long.valueOf(casteStateId),voterId);
			}
		}
		else{
			UserVoterDetails uvDetails=castePredictionService.insertCasteAndPhoneNumberFromAndroid(voterId,casteStateId,mobileNumber);
		}		
		return "SUCCESS";
	}
	
	public String updateCadreDetails(String voterID, Long casteStateId,
			Long caddreLevelId, String mobileNo, String uniqueId)	{
		
		log.debug("Entered into the updateCadreDetails  method in WebServiceHandlerService");

		try
		{
			
			VoterDetailsVO voterDetails = mobileService.getVoterDetailsBasedOnVoterId(voterID);
			
			voterDetails.setMobileNo(mobileNo);
			voterDetails.setCasteStateId(casteStateId);
			voterDetails.setCadreLevelId(caddreLevelId);
			voterDetails.setUniqueId(uniqueId);
			
			   String existance = 	cadreManagementService.checkVoterExistAsCadrebyVoterId(voterDetails.getVoterId());
			   
			   if(existance.equalsIgnoreCase("notExist"))					
				cadreManagementService.saveCadreFromAndroid(voterDetails);
			
		}catch(Exception e)
		{
			log.error("Exception raised in updateCadreDetails  method in WebServiceHandlerService");
			e.printStackTrace();
			return "error";
		}
		return "success";
	}
	

	public String updateIPDetails(String voterID, Long casteStateId,
			Long caddreLevelId, String mobileNo, String uniqueId,Long partyId)
	{
		log.debug("Entered into the updateIPDetails  method in WebServiceHandlerService");

		try
		{
			
			VoterDetailsVO voterDetails = mobileService.getVoterDetailsBasedOnVoterId(voterID);

			
			voterDetails.setMobileNo(mobileNo);
			voterDetails.setCasteStateId(casteStateId);
			voterDetails.setInfleunceLevelId(caddreLevelId);
			voterDetails.setUniqueId(uniqueId);
			voterDetails.setPartyId(partyId);
			
			String existance = 	influencingPeopleService.checkVoterExistAsInfluencePeopleByVoterId(voterDetails.getVoterId());

			  if(existance.equalsIgnoreCase("notExist"))	
				influencingPeopleService.saveInfluencePeopleDetails(voterDetails);
				
			
		}catch(Exception e)
		{
			log.error("Exception raised in updateIPDetails  method in WebServiceHandlerService");
			e.printStackTrace();
			return "error";
		}
		return "success";
	}
	
	public String updateFalgDetails(String uniqueId,String flagName,String flagColor,String voterIds)
	{
		log.debug("Entered into the updateFalgDetails  method in WebServiceHandlerService");

		

		try {
			String[] voterIDs = voterIds.split(",");
			
			List<FlagVO> flagDtls = new ArrayList<FlagVO>();
			List<String> voters = new ArrayList<String>();
			
			FlagVO  flagVO = new FlagVO();
			flagVO.setFlagName(flagName);
			flagVO.setColour(flagColor);
			
			for(String voterID:voterIDs)
				voters.add(voterID);
			
			flagVO.setVoterIDS(voters);
			flagDtls.add(flagVO);
			
			voterReportService.addFlagToVoterFromMobileApp(flagDtls, uniqueId);
		} catch (Exception e) {
			
			log.error("Exception raised in updateFalgDetails  method in WebServiceHandlerService");
			e.printStackTrace();
			return "error";
		}
		return "success";
	}
	
	public String updateVoterMobileNumberAndCaste(String voterID,
			Long casteStateId,
			String mobileNo,String uniqueId)
	{
		
		return voterReportService.updateVoterMobileNumberAndCaste(voterID,casteStateId,mobileNo,uniqueId);
	}
	
	
	public String updateVoterTagDetails(VoterTagVO voterTagVO)
	{
		try{
			VoterTag voterTag = new VoterTag();
			voterTag = voterTagDAO.getVoterTagByVoterIdAndUniqueCode(voterTagVO.getVoterId(),voterTagVO.getUniqueCode());
			
			if(voterTag == null)
				voterTag = new VoterTag();
			
			Date insertTime = dateUtilService.getDateAndTime(voterTagVO.getInsertTime());
			String isCadre = voterTagVO.getIsCadre();
			String isInfluencingPeople = voterTagVO.getIsInfluencingPeople();
			String tags = voterTagVO.getTags();
			
			if(insertTime == null)
				insertTime = dateUtilService.getCurrentDateAndTime();
			if(isCadre == null)
				isCadre = "N";
			if(isInfluencingPeople == null)
				isInfluencingPeople = "N";
			if(tags == null)
				tags = "";
			
			voterTag.setVoterId(voterTagVO.getVoterId());
			voterTag.setIsCadre(isCadre);
			voterTag.setIsInfluencingPeople(isInfluencingPeople);
			voterTag.setTags(voterTagVO.getTags());
			voterTag.setMobileNo(voterTagVO.getMobileNo());
			voterTag.setPartyId(voterTagVO.getPartyId());
			voterTag.setCasteStateId(voterTagVO.getCasteStateId());
			voterTag.setLatitude(voterTagVO.getLatitude());
			voterTag.setLongitude(voterTagVO.getLongitude());
			voterTag.setInsertTime(insertTime);
			voterTag.setSyncTime(dateUtilService.getCurrentDateAndTime());
			voterTag.setUniqueCode(voterTagVO.getUniqueCode());
			voterTag.setIsdelete(IConstants.FALSE);
			
			voterTagDAO.save(voterTag);
			
			return "Success";
		}catch(Exception e)
		{
			log.error("Exception Occured in updateVoterTagDetails(), exception is ",e);
			return "Fail";
		}
	}
	
	public String updateVoterBoothActivitiesDetails(VoterTagVO voterTagVO)
	{
		try{
			VoterBoothActivities voterBoothActivities = new VoterBoothActivities();
			
			Date insertTime = dateUtilService.getDateAndTime(voterTagVO.getInsertTime());
			
			if(insertTime == null)
				insertTime = dateUtilService.getCurrentDateAndTime();
			
			voterBoothActivities.setVoterId(voterTagVO.getVoterId());
			voterBoothActivities.setBoothId(voterTagVO.getBoothId());
			voterBoothActivities.setBoothActivitiesId(voterTagVO.getBoothActivitiesId());
			voterBoothActivities.setLatitude(voterTagVO.getLatitude());
			voterBoothActivities.setLongitude(voterTagVO.getLongitude());
			voterBoothActivities.setInsertTime(insertTime);
			voterBoothActivities.setSyncTime(dateUtilService.getCurrentDateAndTime());
			voterBoothActivities.setUniqueCode(voterTagVO.getUniqueCode());
			voterBoothActivities.setIsdelete(IConstants.FALSE);
			
			voterBoothActivitiesDAO.save(voterBoothActivities);
			
			return "Success";
		}catch(Exception e)
		{
			log.error("Exception Occured in updateVoterTagDetails(), exception is ",e);
			return "Fail";
		}
	}

}

