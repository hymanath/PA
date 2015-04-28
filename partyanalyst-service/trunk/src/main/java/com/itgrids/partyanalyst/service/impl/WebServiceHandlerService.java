package com.itgrids.partyanalyst.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.jfree.util.Log;
import org.springframework.beans.factory.annotation.Autowired;

import com.itgrids.partyanalyst.dao.IBoothDAO;
import com.itgrids.partyanalyst.dao.IBoothPublicationVoterDAO;
import com.itgrids.partyanalyst.dao.IEventAttendeeDAO;
import com.itgrids.partyanalyst.dao.IEventSurveyUserDAO;
import com.itgrids.partyanalyst.dao.IEventSurveyUserLoginDetailsDAO;
import com.itgrids.partyanalyst.dao.IEventUserDAO;
import com.itgrids.partyanalyst.dao.IMobileAppPingingDAO;
import com.itgrids.partyanalyst.dao.IMobileAppUserAccessKeyDAO;
import com.itgrids.partyanalyst.dao.IMobileAppUserDAO;
import com.itgrids.partyanalyst.dao.IMobileAppUserProfileDAO;
import com.itgrids.partyanalyst.dao.IPingingTypeDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreDAO;
import com.itgrids.partyanalyst.dao.IUserDAO;
import com.itgrids.partyanalyst.dao.IUserSurveyBoothsDAO;
import com.itgrids.partyanalyst.dao.IUserVoterDetailsDAO;
import com.itgrids.partyanalyst.dao.IVoiceRecordingDetailsDAO;
import com.itgrids.partyanalyst.dao.IVoterBoothActivitiesDAO;
import com.itgrids.partyanalyst.dao.IVoterDAO;
import com.itgrids.partyanalyst.dao.IVoterTagDAO;
import com.itgrids.partyanalyst.dao.IWebServiceBaseUrlDAO;
import com.itgrids.partyanalyst.dto.CadreAddressVO;
import com.itgrids.partyanalyst.dto.CadreInfo;
import com.itgrids.partyanalyst.dto.CadrePrintInputVO;
import com.itgrids.partyanalyst.dto.CadrePrintVO;
import com.itgrids.partyanalyst.dto.CadreTravelsVO;
import com.itgrids.partyanalyst.dto.CardNFCDetailsVO;
import com.itgrids.partyanalyst.dto.CasteDetailsVO;
import com.itgrids.partyanalyst.dto.EffectedBoothsResponse;
import com.itgrids.partyanalyst.dto.FlagVO;
import com.itgrids.partyanalyst.dto.PanchayatCountVo;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.TdpCadreVO;
import com.itgrids.partyanalyst.dto.UserDetailsVO;
import com.itgrids.partyanalyst.dto.UserEventDetailsVO;
import com.itgrids.partyanalyst.dto.VoterDetailsVO;
import com.itgrids.partyanalyst.dto.WSResultVO;
import com.itgrids.partyanalyst.model.Booth;
import com.itgrids.partyanalyst.model.EventAttendee;
import com.itgrids.partyanalyst.model.EventSurveyUserLoginDetails;
import com.itgrids.partyanalyst.model.MobileAppPinging;
import com.itgrids.partyanalyst.model.MobileAppUser;
import com.itgrids.partyanalyst.model.MobileAppUserAccessKey;
import com.itgrids.partyanalyst.model.User;
import com.itgrids.partyanalyst.model.UserVoterDetails;
import com.itgrids.partyanalyst.model.VoterBoothActivities;
import com.itgrids.partyanalyst.model.VoterTag;
import com.itgrids.partyanalyst.security.PBKDF2;
import com.itgrids.partyanalyst.service.ICadreDetailsService;
import com.itgrids.partyanalyst.service.ICadreRegistrationService;
import com.itgrids.partyanalyst.service.IInfluencingPeopleService;
import com.itgrids.partyanalyst.service.ILoginService;
import com.itgrids.partyanalyst.service.IMailService;
import com.itgrids.partyanalyst.service.IMobileService;
import com.itgrids.partyanalyst.service.ISmsService;
import com.itgrids.partyanalyst.service.IStrategyModelTargetingService;
import com.itgrids.partyanalyst.service.IVoiceSmsService;
import com.itgrids.partyanalyst.service.IVoterReportService;
import com.itgrids.partyanalyst.service.IWebServiceHandlerService;
import com.itgrids.partyanalyst.utils.DateUtilService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.itgrids.partyanalyst.utils.Util;
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
	@Autowired IUserDAO userDAO;
	@Autowired IStrategyModelTargetingService strategyModelTargetingService;
    @Autowired IUserSurveyBoothsDAO userSurveyBoothsDAO ;
    private ICadreRegistrationService cadreRegistrationService;
    @Autowired ITdpCadreDAO tdpCadreDAO;
    private ICadreDetailsService cadreDetailsService;
    @Autowired
    private IEventSurveyUserDAO eventSurveyUserDAO;
    @Autowired
    private IEventUserDAO eventUserDAO;
    @Autowired
    private IEventSurveyUserLoginDetailsDAO eventSurveyUserLoginDetailsDAO;
    @Autowired
    private IEventAttendeeDAO eventAttendeeDAO;
    @Autowired
    private IVoterDAO voterDAO;
    
    
    
    
	public void setCadreDetailsService(ICadreDetailsService cadreDetailsService) {
		this.cadreDetailsService = cadreDetailsService;
	}

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
    
	public void setDateUtilService(DateUtilService dateUtilService) {
		this.dateUtilService = dateUtilService;
	}

	public void setUserDAO(IUserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public void setStrategyModelTargetingService(
			IStrategyModelTargetingService strategyModelTargetingService) {
		this.strategyModelTargetingService = strategyModelTargetingService;
	}

	public void setUserSurveyBoothsDAO(IUserSurveyBoothsDAO userSurveyBoothsDAO) {
		this.userSurveyBoothsDAO = userSurveyBoothsDAO;
	}

	public void setCadreRegistrationService(
			ICadreRegistrationService cadreRegistrationService) {
		this.cadreRegistrationService = cadreRegistrationService;
	}

	public void setTdpCadreDAO(ITdpCadreDAO tdpCadreDAO) {
		this.tdpCadreDAO = tdpCadreDAO;
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
			
			String insertTimeStr = replaceString(voterTagVO.getInsertTime());
			
			Date insertTime = dateUtilService.getDateAndTime(insertTimeStr);
			String isCadre = voterTagVO.getIsCadre();
			String isInfluencingPeople = voterTagVO.getIsInfluencingPeople();
			String tags = voterTagVO.getTags();
			Long partyId = voterTagVO.getPartyId();
			Long casteStateId = voterTagVO.getCasteStateId();
			
			if(insertTime == null)
				insertTime = dateUtilService.getCurrentDateAndTime();
			if(isCadre == null)
				isCadre = "N";
			if(isInfluencingPeople == null)
				isInfluencingPeople = "N";
			if(tags == null)
				tags = "";
			if(partyId.equals(0L))
				partyId = null;
			if(casteStateId.equals(0L))
				casteStateId = null;
			
			voterTag.setVoterId(voterTagVO.getVoterId());
			voterTag.setIsCadre(isCadre);
			voterTag.setIsInfluencingPeople(isInfluencingPeople);
			voterTag.setTags(tags.trim());
			voterTag.setMobileNo(voterTagVO.getMobileNo().trim());
			voterTag.setPartyId(partyId);
			voterTag.setCasteStateId(casteStateId);
			voterTag.setLatitude(voterTagVO.getLatitude().trim());
			voterTag.setLongitude(voterTagVO.getLongitude().trim());
			voterTag.setInsertTime(insertTime);
			voterTag.setSyncTime(dateUtilService.getCurrentDateAndTime());
			voterTag.setUniqueCode(voterTagVO.getUniqueCode().trim());
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
			
			String insertTimeStr = replaceString(voterTagVO.getInsertTime());
			
			Date insertTime = dateUtilService.getDateAndTime(insertTimeStr);
			
			if(insertTime == null)
				insertTime = dateUtilService.getCurrentDateAndTime();
			
			voterBoothActivities.setVoterId(voterTagVO.getVoterId());
			voterBoothActivities.setBoothId(voterTagVO.getBoothId());
			voterBoothActivities.setBoothActivitiesId(voterTagVO.getBoothActivitiesId());
			voterBoothActivities.setLatitude(voterTagVO.getLatitude().trim());
			voterBoothActivities.setLongitude(voterTagVO.getLongitude().trim());
			voterBoothActivities.setInsertTime(insertTime);
			voterBoothActivities.setSyncTime(dateUtilService.getCurrentDateAndTime());
			voterBoothActivities.setUniqueCode(voterTagVO.getUniqueCode().trim());
			voterBoothActivities.setIsdelete(IConstants.FALSE);
			
			voterBoothActivitiesDAO.save(voterBoothActivities);
			
			return "Success";
		}catch(Exception e)
		{
			log.error("Exception Occured in updateVoterTagDetails(), exception is ",e);
			return "Fail";
		}
	}
	
	public String replaceString(String str)
	{
		try{
			str = str.replace("+"," ");
			return str;
		}catch(Exception e)
		{
			log.error("Exception occured in replaceString() Method ",e);
			return str;
		}
	}

	
	public String requestForAuthorisationAccesskey(String uniqueCode)
	{
		String resultstr = "";
		try{
			StringBuilder str =new StringBuilder();
			ResultStatus resultStatus = new ResultStatus();
			List<Object[]> list = mobileAppUserProfileDAO.getMobileNoByUniquecode(uniqueCode);
			
			if(list != null && list.size() > 0)
			{
				for(Object[] params : list)
				{
					try{
					List<Object> pingingTypeId = pingingTypeDAO.getPingingTypeIdByType(IConstants.Authorisation_Access_Key);
					saveMobileAppPingIngDetails((Long) params[4],(Long)pingingTypeId.get(0),null,null);
					String[] mobile = {params[0].toString()};
					String[] admingroupMobileNos = {IConstants.ADMINGROUPMOBILE};
					String name = params[1].toString()+" " +params[2].toString();
					String msg = "Dear "+name+", Your request for Authorisation access key hasbeen received in few minutes we can send Access Key to your mobile and email.";
					String adminMsg = "Hi Admin Group, "+name+" requested for Authorisation access key,verify his details and send access key to him immediately.";
					resultStatus = smsCountrySmsService.sendSmsFromAdmin(msg,true,mobile);
					smsCountrySmsService.sendSmsFromAdmin(adminMsg,true,admingroupMobileNos);
					if(name != null && name !="")
					{
					mailService.sendEmailToAdminGroupForAuthorisationAccessKey(name);
					if(params[3] != null && params[3] !="")
					mailService.sendEmailToMobileAppUserForAuthorisationAccessKey(name,params[3].toString());
					}
					}catch(Exception e)
					{
						log.error(e);
					}
				}
			}
			return "True : Accesskey will be Sent to your Mobile & Email shorty";
		}
		catch(Exception e)
		{
			log.error("Exception occured in requestForAuthorisationAccesskey() Method ",e);	
			return "False : Your request not processed, Please try again";
		}
	}
	public String verificationForAuthorisationAccessKey(String uniqueCode,String accesskey)
	{
		String resultstr = "";
		List<Object> pingingTypeId =null;
		try{
			List<Object[]> list = mobileAppUserAccessKeyDAO.checkUniqueCodeByAccesskey(uniqueCode,accesskey);	
			if(list != null && list.size() > 0)
			{
				for(Object[] params : list)
				{
				MobileAppUserAccessKey mobileAppUserAccessKey = mobileAppUserAccessKeyDAO.get((Long)params[1]);
				if(mobileAppUserAccessKey.getType().equalsIgnoreCase(IConstants.Request_For_Forget_Pwd_Access_Key))
				 pingingTypeId = pingingTypeDAO.getPingingTypeIdByType(IConstants.Request_For_Forget_Pwd_Access_Key);
				else if(mobileAppUserAccessKey.getType().equalsIgnoreCase(IConstants.Authorisation_Access_Key))
				 pingingTypeId = pingingTypeDAO.getPingingTypeIdByType(IConstants.Authorisation_Access_Key);
				saveMobileAppPingIngDetails((Long) params[0],(Long)pingingTypeId.get(0),null,null);	
				mobileAppUserAccessKey.setIsUsed("true");
				mobileAppUserAccessKeyDAO.save(mobileAppUserAccessKey);
				}
				resultstr = "True : Your Authorisation access key is accepted, please login now"	;
			}
				else
				resultstr = "False : Your Authorisation access key is not valid"	;	
		}
		catch(Exception e)
		{
			log.error("Exception occured in verificationForAuthorisationAccessKey() Method ",e);		
		}
		return resultstr;
	}
	
	public EffectedBoothsResponse getInfectedBoothsOfConstituency(Long constituencyId){
		List<PanchayatCountVo> list = new ArrayList<PanchayatCountVo>();
		PanchayatCountVo pvo = null ;
		pvo = new PanchayatCountVo();
		pvo.setPanchayatId(101l);
		pvo.setPanchayatName("ABPalem");
		List<Long> boothNos = new ArrayList<Long>();
		boothNos.add(61l);
		boothNos.add(62l);
		pvo.setBooths(boothNos);
		StringBuilder boothParts=new StringBuilder();
		for(Long booth:boothNos){
			boothParts.append(booth);
			boothParts.append(",");
		}
		
		pvo.setBoothsList(boothParts.deleteCharAt(boothParts.length()-1).toString());
		pvo.setEffectedCount(2);
		pvo.setEffected(true);
		list.add(pvo);
		
		pvo = new PanchayatCountVo();
		pvo.setPanchayatId(101l);
		pvo.setPanchayatName("S.Kota");
		List<Long> boothNos1 = new ArrayList<Long>();
		boothNos1.add(121l);
		boothNos1.add(122l);
		pvo.setBooths(boothNos1);
		StringBuilder boothParts1=new StringBuilder();
		for(Long booth:boothNos1){
			boothParts1.append(booth);
			boothParts1.append(",");
		}
		pvo.setBoothsList(boothParts1.deleteCharAt(boothParts1.length()-1).toString());
		pvo.setEffected(true);
		pvo.setEffectedCount(1);
		list.add(pvo);
		
		
		EffectedBoothsResponse effectedResponse = new EffectedBoothsResponse();
		effectedResponse.setPanchayats(list);
		
		return effectedResponse;
	}
	
	public WSResultVO getLoginFieldDataUser(String uname,String pwd)
	{
		WSResultVO wsResultVO = new WSResultVO();
		try{
			User user = null;
			List<User> userObj=userDAO.getModelByUserName(uname);
			
			if(userObj.size()==0){
				return null;
			}
			if(userObj.get(0).getPasswordHash() !=null && userObj.get(0).getPasswordSalt()!=null){
				user=userObj.get(0);
				String salt = userObj.get(0).getPasswordSalt();
				String hash = userObj.get(0).getPasswordHash();
				String md5Pwd=Util.MD5(Util.MD5(user.getUserName())+ Util.MD5(pwd));
				PBKDF2 pb= new PBKDF2();
				
				boolean validated = pb.validatePWD(md5Pwd, hash, salt);
				if(validated){
					wsResultVO.setUniqueCode(user.getUniqueCode() != null ? user.getUniqueCode() : "");
					wsResultVO.setUserId(user.getUserId());
					wsResultVO.setUserName(user.getFirstName() +" " +user.getLastName());
					wsResultVO.setPwd(pwd);
					List<Object[]> list = userSurveyBoothsDAO.getPublicationIdByUserId(user.getUserId());
					if(list != null && list.size() > 0)
					{
						for(Object[] params : list)
						{
					wsResultVO.setConstituencyId((Long)params[0]);
					wsResultVO.setPublicationDateId((Long)params[1]);
						}
					}
				}
				
			}
		}
		catch(Exception e)
		{
		return null;  	
		}
		return wsResultVO;
	}
	
	public Object getVCadreDataByPanchayatId(Long panchayatId)
	{
		List<CadrePrintVO> list = cadreRegistrationService.getSelectedLevelCadreDetails(panchayatId);
		return list;
	}
	
	public Object tagCardIdForNFCReader(String cardNo , Long voetrId)
	{
		String status = cadreRegistrationService.tagCardIdForNFCReader(cardNo,voetrId);
		return status;
	}
	
	public Object getCadreDetailsForPrinting(String memberNo)
	{
		CadrePrintVO cadrePrintVO = cadreRegistrationService.getCadreDetailsForPrinting(memberNo);
		return cadrePrintVO;
	}
	
	public Object checkNFCNumberForVoterId(Long voterId)
	{
		String status = cadreRegistrationService.checkNFCNumberForVoterId(voterId);
		return status;
	}
	public Object delinkNFCNumber(String cardNo , Long voterId)
	{
		String status = cadreRegistrationService.delinkNFCNumber(cardNo,voterId);
		return status;
	}
	
	public List<CasteDetailsVO> getAllCastes()
	{
		return cadreRegistrationService.getAllCastes();
	}
	
	public Object getVCadreDataByPanchayatId1(Long panchayatId,String type)
	{
		List<CadrePrintVO> list = cadreRegistrationService.getSelectedLevelCadreDetails1(panchayatId,type);
		return list;
	}
	
	public Object getVCadreDetailsBySelection(CadrePrintInputVO input){
		List<CadrePrintVO> list = cadreRegistrationService.getSelectedLevelCadreDetailsBySelection(input);
		return list;
	}
	
	public Object getTDPCadreDetailsBySearch(CadrePrintInputVO input){
		List<CadrePrintVO> status = cadreRegistrationService.getTDPCadreDetailsBySearch(input);
		return status;
	}
	
	
	public Object updatePrintedCardDetails(List<CardNFCDetailsVO> inputVOList){
		String status = cadreRegistrationService.updatePrintedCardDetails(inputVOList);
		return status;
	}
	
	public Object getCadreSurveyUserDetails(List<UserDetailsVO> cadreSurveyUserIds){
		List<UserDetailsVO> details = cadreRegistrationService.getCadreSurveyUserDetails(cadreSurveyUserIds);
		return details;
	}
	/*public Object getMobileNoByMemberShip(String memberShipNo)
	{
		String returnStr ="";
		try{
			/*
			String mobile = tdpCadreDAO.getMobileNoByMemberShipNo(memberShipNo);
			if(mobile!= null)
				returnStr = mobile;
			else
				returnStr = "MobileNo not available";
			*/
			/*String mobileNo = "";
			TdpCadreVO tdpCadreVO = cadreDetailsService.searchTdpCadreDetailsBySearchCriteriaForCommitte(0L, 0L, "", memberShipNo, "", "", "", 0L, "",null,null,null,null);
			if(tdpCadreVO != null)
			{
				if(tdpCadreVO.getTdpCadreDetailsList() != null && tdpCadreVO.getTdpCadreDetailsList().size()>0)
				{
					TdpCadreVO cadreVO = tdpCadreVO.getTdpCadreDetailsList().get(0);
					if(cadreVO != null)
					{
						mobileNo = cadreVO.getMobileNo();
					}					
				}				
			}
			
			if(mobileNo!= null && mobileNo.trim().length()>0)
				returnStr = mobileNo;
			else
				returnStr = "MobileNo not available";
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return returnStr;
		
	}*/
	
	public List<CadreAddressVO> getMobileNoByMemberShip(List<String> memberShipNos)
	{
		List<CadreAddressVO> returnVO = new ArrayList<CadreAddressVO>();
		try{
			
			StringBuilder queryStr = new StringBuilder();
			if(memberShipNos != null &&  memberShipNos.size() > 0)
			{
				for(String memberShipNo :memberShipNos){
					String memberShipNumber = "AP14"+memberShipNo;
					String memberShipNumber1 = "TS14"+memberShipNo;
					String temp =  memberShipNos.get(memberShipNos.size() - 1);
					
					if(temp.equalsIgnoreCase(memberShipNo) || memberShipNos.size() == 1)
						queryStr.append(" (model.memberShipNo ='"+memberShipNumber.trim()+"' OR model.memberShipNo ='"+memberShipNumber1.trim()+"')  ");
					else
						queryStr.append(" (model.memberShipNo ='"+memberShipNumber.trim()+"' OR model.memberShipNo ='"+memberShipNumber1.trim()+"')  OR ");
				}
			}
			List<Object[]>  mobileNos = tdpCadreDAO.getMobileNosByMemberShipId(queryStr.toString());
			
			if(mobileNos!= null && mobileNos.size() >0){
				for(Object[] params : mobileNos){
					CadreAddressVO vo = new CadreAddressVO();
					vo.setMembershipNo(params[0].toString().substring(4));
					if(params[1] != null && !params[1].toString().isEmpty())
						vo.setMobileNo(params[1].toString());
					else
						vo.setMobileNo("MobileNo not available");
					returnVO.add(vo);
				}
				
			}
				
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return returnVO;
		
	}
	
	
	
	
	
	
/*	public CadreAddressVO getMemberDataByMemberShip(String memberShipNo,String address)
	{
		
		CadreAddressVO cadreAddressVO = new CadreAddressVO();
		//List<Object[]> list = null;
		try{
			/*
			if(address.equalsIgnoreCase("true"))
				list = tdpCadreDAO.getMemberAddressByMembershipNo(memberShipNo);
			else if(address.equals(null) || address.equalsIgnoreCase("false") )
				list = tdpCadreDAO.getMemberDataByMembershipNo(memberShipNo);
			cadreAddressVO = setMemberData(list,address);
			
			 */			
		/*	TdpCadreVO tdpCadreVO = cadreDetailsService.searchTdpCadreDetailsBySearchCriteriaForCommitte(0L, 0L, "", memberShipNo, "", "", "", 0L, "",null,null,null,null);
			if(address != null && memberShipNo != null)
			{
				if(address.equalsIgnoreCase("true"))
				{
					if(tdpCadreVO != null && tdpCadreVO.getTdpCadreDetailsList() != null && tdpCadreVO.getTdpCadreDetailsList().size()>0)
					{
						for (TdpCadreVO cadreVO : tdpCadreVO.getTdpCadreDetailsList()) 
						{
							cadreAddressVO.setName(cadreVO.getCadreName());
							cadreAddressVO.setMobileNo(cadreVO.getMobileNo());
							cadreAddressVO.setAge(cadreVO.getAge());
							cadreAddressVO.setGender(cadreVO.getGender());
							cadreAddressVO.setMembershipNo(cadreVO.getMemberShipNo().toString());
							if(address.equalsIgnoreCase("true"))
							{
								
								String str = "";
								String district =  cadreVO.getDistrict().trim();
								String constituency =  cadreVO.getConstituency().trim();
								String tehsil =  cadreVO.getTehsil().trim();
								String panchayat =  cadreVO.getPanchayat().trim();
								String localbody =  cadreVO.getLocalElectionBody().trim();
								
								if(!district.isEmpty())
									str += "District : " + district;
								if(!constituency.isEmpty())
									str += " , Constituency : " +constituency;
					            if(!tehsil.isEmpty())
					            	str+=" , Mandal : " +tehsil;
								else if(!localbody.isEmpty())
									str+=" , Muncipality : " +localbody;
					            if(!panchayat.isEmpty())
					            	str+=" , Panchayat : " +panchayat;
					          
								cadreAddressVO.setAddress(str.toString());
							}
						}
					}
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return cadreAddressVO;
		
	}*/
	public CadreAddressVO setMemberData(List<Object[]> list,String address)
	{
		CadreAddressVO cadreAddressVO = new CadreAddressVO();
		try{
		if(list != null && list.size() > 0)
		{
			for(Object[] params : list)
			{
				cadreAddressVO.setName(params[0] != null ? params[0].toString() : "");
				cadreAddressVO.setMobileNo(params[1] != null ? params[1].toString() : "");
				cadreAddressVO.setAge(params[2] != null ? (Long)params[2] : null);
				cadreAddressVO.setGender(params[3] != null ? params[3].toString() : "");
				if(address.equalsIgnoreCase("true"))
				{
					
					String str = "";
					String district =  params[4] != null ? params[4].toString()  : "";
					String constituency =  params[5] != null ? params[5].toString() : "";
					String tehsil =  params[6] != null ? params[6].toString() : "";
					String panchayat =  params[7] != null ? params[7].toString() : "";
					String localbody =  params[8] != null ? params[8].toString(): "";
					if(!district.isEmpty())
					str += "District : " + district;
					if(!constituency.isEmpty())
		            str += " , Constituency : " +constituency;
		            if(!tehsil.isEmpty())
		            str+=" , Mandal : " +tehsil;
					else if(!localbody.isEmpty())
		            str+=" , Muncipality : " +localbody;
		            if(!panchayat.isEmpty())
		            str+=" , Panchayat : " +panchayat;
		          
					cadreAddressVO.setAddress(str.toString());
				}
					
			}
		}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return cadreAddressVO;
		
	}
	
	public List<CadreAddressVO> getMemberDataByMemberShip(List<String> addressTrueList , List<String> addressFalseList)
	{
		
		List<CadreAddressVO> cadreAddressVOList = new ArrayList<CadreAddressVO>();
	
		try{
			List<Object[]> list = new ArrayList<Object[]>();
			List<Object[]> list1 = new ArrayList<Object[]>();
			StringBuilder queryStr = new StringBuilder();
			if(addressTrueList != null &&  addressTrueList.size() > 0)
			{
				for(String memberShipNo :addressTrueList){
					String memberShipNumber = "AP14"+memberShipNo;
					String memberShipNumber1 = "TS14"+memberShipNo;
					String temp =  addressTrueList.get(addressTrueList.size() - 1);
					if(temp.equalsIgnoreCase(memberShipNo) || addressTrueList.size() == 1)
						queryStr.append("  (model.memberShipNo ='"+memberShipNumber.trim()+"' OR model.memberShipNo ='"+memberShipNumber1.trim()+"') ");
					else
						queryStr.append(" (model.memberShipNo ='"+memberShipNumber.trim()+"' OR model.memberShipNo ='"+memberShipNumber1.trim()+"')  OR ");
				}
			}
			
			StringBuilder queryStr1 = new StringBuilder();
			if(addressFalseList != null &&  addressFalseList.size() > 0)
			{
				for(String memberShipNo :addressFalseList){
					String temp =  addressFalseList.get(addressFalseList.size() - 1);
					if(temp.equalsIgnoreCase(memberShipNo) || addressFalseList.size() == 1)
						queryStr1.append("  (model.memberShipNo like '%"+memberShipNo.trim()+"')  ");
					else
						queryStr1.append("  (model.memberShipNo like '%"+memberShipNo.trim()+"')  OR ");
				}
			}
			if(addressTrueList != null &&  addressTrueList.size() > 0){
				 list = tdpCadreDAO.getMemberAddressDetlsByMembershipNo(queryStr.toString());				 
				setMemberDataList(cadreAddressVOList,list,"true");
			}
			if(addressFalseList != null &&  addressFalseList.size() > 0){					
				 list1 = tdpCadreDAO.getMemberDetlsByMembershipNo(queryStr1.toString());				
				setMemberDataList(cadreAddressVOList,list1,"false");			
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return cadreAddressVOList;
		
	}
	
	public List<CadreAddressVO> setMemberDataList(List<CadreAddressVO> cadreAddressVOList,List<Object[]> list,String address)
	{
		
		try{
		
		if(list != null && list.size() > 0)
		{
			for(Object[] params : list)
			{
				CadreAddressVO cadreAddressVO = new CadreAddressVO();
				cadreAddressVO.setMembershipNo(params[4] != null ? params[4].toString().substring(4) : "");
				cadreAddressVO.setName(params[0] != null ? params[0].toString() : "");
				cadreAddressVO.setMobileNo(params[1] != null ? params[1].toString() : "");
				cadreAddressVO.setAge(params[2] != null ? (Long)params[2] : null);
				cadreAddressVO.setGender(params[3] != null ? params[3].toString() : "");
				cadreAddressVO.setRefNo(params[10] != null ? params[10].toString() : "");
				cadreAddressVO.setPhoto(params[11] != null ? "http://mytdp.com/cadre_images/"+params[11].toString() : "");
				
				if(address.equalsIgnoreCase("true"))
				{
					
					String str = "";
					String district =  params[5] != null ? params[5].toString()  : "";
					String constituency =  params[6] != null ? params[6].toString() : "";
					String tehsil =  params[7] != null ? params[7].toString() : "";
					String panchayat =  params[8] != null ? params[8].toString() : "";
					String localbody =  params[9] != null ? params[9].toString(): "";
					if(!district.isEmpty())
					str += "District : " + district;
					if(!constituency.isEmpty())
		            str += " , Constituency : " +constituency;
		            if(!tehsil.isEmpty())
		            str+=" , Mandal : " +tehsil;
					else if(!localbody.isEmpty())
		            str+=" , Muncipality : " +localbody;
		            if(!panchayat.isEmpty())
		            str+=" , Panchayat : " +panchayat;
		            cadreAddressVO.setAddress(str.toString());
		           
				}				
				cadreAddressVOList.	add(cadreAddressVO);
			}
		}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return cadreAddressVOList;
		
	}
	
	
	public Object updateCadreTravelDiscountDetails(CadreTravelsVO inputVO){
		String status = cadreRegistrationService.updateCadreTravelDiscountDetails(inputVO);
		return status;
	}
	
	public Object cancellationOfTicketDetails(CadreTravelsVO inputVO){
		String status = cadreRegistrationService.cancellationOfTicketDetails(inputVO);
		return status;
	}
  public CadreAddressVO getMemberDataByRefNoAndMemberShipNo(String refNo,String memberShipNo)
	{
		CadreAddressVO returnVo = new CadreAddressVO();
		
		List<Object[]> list  = null;
		try{
			StringBuilder queryStr = new StringBuilder();
			String memberShipNumber = "AP14"+memberShipNo;
			String memberShipNumber1 = "TS14"+memberShipNo;
			if(memberShipNo != null && !memberShipNo.isEmpty())
			{
				queryStr = new StringBuilder();
				
			    //queryStr.append(" substring(model.memberShipNo, 5) ='"+memberShipNo.trim()+"'  ");
				queryStr.append(" (model.memberShipNo ='"+memberShipNumber.trim()+"' OR model.memberShipNo ='"+memberShipNumber1.trim()+"') ");
			    list = tdpCadreDAO.getMemberInfoyMembershipNo(queryStr.toString());
				setMemberInfo(returnVo,list);
			}
			
			else if((returnVo == null || returnVo.getMembershipNo() == null) && (refNo != null && !refNo.isEmpty()) )
			{
				queryStr = new StringBuilder();
				queryStr.append("  model.cardNumber = '"+refNo+"'  ");
			
				list = tdpCadreDAO.getMemberInfoyMembershipNo(queryStr.toString());	
				setMemberInfo(returnVo,list);
			}
			 else
			 {
				 queryStr = new StringBuilder();
					
				       if(memberShipNo != null && refNo != null)
				    	   queryStr.append("  ((model.memberShipNo ='"+memberShipNumber.trim()+"' OR  model.memberShipNo ='"+memberShipNumber1.trim()+"') and model.cardNumber = '"+refNo+"')  ");
							list = tdpCadreDAO.getMemberInfoyMembershipNo(queryStr.toString());	
						if(list !=  null && list.size() > 0)
							setMemberInfo(returnVo,list);
			 }
			
			
		}
		catch(Exception e)
		{
			Log.error("Exception Occured in getMemberDataByMemberShipAndRefNo()",e);
			e.printStackTrace();
		}
		
		return returnVo;
	}
	
	public CadreAddressVO setMemberInfoList(CadreAddressVO cadreAddressVO,List<Object[]> list,String refNo)
	{

		try{
		
		if(list != null && list.size() > 0)
		{
			int count = 0;
			for(Object[] params : list)
			{
				String cardNumber = params[3] != null ? params[3].toString() : "";
				if(refNo.equalsIgnoreCase(cardNumber))
				{
					count =1;
				cadreAddressVO.setMembershipNo(params[2] != null ? params[2].toString().substring(4) : "");
				cadreAddressVO.setName(params[0] != null ? params[0].toString() : "");
				cadreAddressVO.setMobileNo(params[1] != null ? params[1].toString() : "");
				cadreAddressVO.setRefNo(params[3] != null ? params[3].toString() : "");
				cadreAddressVO.setPhoto(params[4] != null ? "http://mytdp.com/images/cadre_images/"+params[4].toString() : "");
				}
				
			}
			if(count == 0){
				
				Object[] params = list.get(0);
				cadreAddressVO.setMembershipNo(params[2] != null ? params[2].toString().substring(4) : "");
				cadreAddressVO.setName(params[0] != null ? params[0].toString() : "");
				cadreAddressVO.setMobileNo(params[1] != null ? params[1].toString() : "");
				cadreAddressVO.setRefNo(params[3] != null ? params[3].toString() : "");
				cadreAddressVO.setPhoto(params[4] != null ? "http://mytdp.com/images/cadre_images/"+params[4].toString() : "");
			}
		}
		}
		catch(Exception e)
		{
			Log.error("Exception Occured in setMemberInfoList() method",e) ;
			e.printStackTrace();
		}
		return cadreAddressVO;
		
	}
	public CadreAddressVO setMemberInfo(CadreAddressVO cadreAddressVO,List<Object[]> list)
	{

		try{
		
		if(list != null && list.size() > 0)
		{
			
			for(Object[] params : list)
			{
				
				cadreAddressVO.setMembershipNo(params[2] != null ? params[2].toString().substring(4) : "");
				cadreAddressVO.setName(params[0] != null ? params[0].toString() : "");
				cadreAddressVO.setMobileNo(params[1] != null ? params[1].toString() : "");
				cadreAddressVO.setRefNo(params[3] != null ? params[3].toString() : "");
				cadreAddressVO.setPhoto(params[4] != null ? "http://mytdp.com/images/cadre_images/"+params[4].toString() : "");
			}
			
		}
		}
		catch(Exception e)
		{
			Log.error("Exception Occured in setMemberInfo() method",e) ;
			e.printStackTrace();
		}
		return cadreAddressVO;
		
	}

   public UserEventDetailsVO validateUserForEvent(UserEventDetailsVO inpuVo)
	{
		 List<UserEventDetailsVO> resultList = new ArrayList<UserEventDetailsVO>();
		 UserEventDetailsVO userEventDetailsVO = null;
		 DateUtilService date = new DateUtilService();
		 SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		 SimpleDateFormat timeFormat= new SimpleDateFormat("HH:mm:ss a");
		try{
			List<Object[]> list =  eventSurveyUserDAO.getUserDetailsByUnamePwd(inpuVo.getUserName(),inpuVo.getUserPassword());
			if(list != null && list.size() > 0)
			{
				for(Object[] params : list)
				{
					userEventDetailsVO = new UserEventDetailsVO();
					String fname = params[1] != null ? params[1].toString() : "" ;
					String lname = params[2] != null ? params[2].toString() : "";
					userEventDetailsVO.setUserName(fname +" "+lname);
					userEventDetailsVO.setId((Long)params[0]);
					
					List checkList = eventSurveyUserLoginDetailsDAO.checkUserExistence((Long)params[0],inpuVo.getIMEI());
					if(checkList == null || checkList.size() == 0)
					{
						
						EventSurveyUserLoginDetails eventSurveyUserLoginDetails = new EventSurveyUserLoginDetails();
						eventSurveyUserLoginDetails.setImei(inpuVo.getIMEI());
						if(inpuVo.getLoginTimeStamp() != null)
						eventSurveyUserLoginDetails.setLoginTime(formatter.parse(inpuVo.getLoginTimeStamp()));
						if(inpuVo.getSIMCardNumber() != null)
						eventSurveyUserLoginDetails.setSimno(inpuVo.getSIMCardNumber());
						eventSurveyUserLoginDetails.setEventSurveyUser(eventSurveyUserDAO.get((Long)params[0]));
						eventSurveyUserLoginDetailsDAO.save(eventSurveyUserLoginDetails);
					}
				}
				
				
				
				
				//eventSurveyUserLoginDetailsDAO.saveCadreFromAndroid(voterDetails)
				List<Long> parentIds = new ArrayList<Long>();
				List<Object[]> parentEvent = eventUserDAO.getParentEventByUser(userEventDetailsVO.getId(),date.getCurrentDateAndTime());
				if(parentEvent != null && parentEvent.size() > 0)
				{
					
					for(Object[] params : parentEvent)
					{
						UserEventDetailsVO eventVo = new UserEventDetailsVO();
						eventVo.setId((Long)params[0]);
						eventVo.setUserName(params[1] != null ? params[1].toString() : "");
						userEventDetailsVO.getSubList().add(eventVo);
						parentIds.add((Long)params[0]);
					}
					List<Object[]> events = eventUserDAO.getEventsByUserAndParentIds(userEventDetailsVO.getId(),date.getCurrentDateAndTime(),parentIds);
					if(events != null && events.size() > 0)
					{
						for(Object[] params : events)
						{
							UserEventDetailsVO parentVo = getMatchedEvent(userEventDetailsVO.getSubList(),(Long)params[2]);
							if(parentVo != null)
							{
								UserEventDetailsVO childEventVo = new UserEventDetailsVO();
								childEventVo.setUserId((Long)params[0]);
								childEventVo.setUserName(params[1] != null ? params[1].toString() : "");
								if(params[4]!= null)
								childEventVo.setStartTime(TimeForm(params[4].toString()));
								if(params[5]!= null)
								childEventVo.setEndTime(TimeForm(params[5]!= null?params[5].toString():""));
								parentVo.getSubList().add(childEventVo);
							}
						}	
					}
				}
					
			}
			
		}
		catch(Exception e)
		{
			Log.error("Exception Occured in validateUserForEvent() method",e) ;
			e.printStackTrace();
		}
		return userEventDetailsVO;
	}
	
   
   public String TimeForm(String time)
   {
	   String output = null;
	   try{
		   
		   String input = time;
		
		 //Date/time pattern of input date
	       DateFormat df = new SimpleDateFormat("HH:mm:ss");
	       //Date/time pattern of desired output date
	       DateFormat outputformat = new SimpleDateFormat(" hh:mm:ss aa");
	       Date date = null;
	      
	      
	          //Conversion of input String to date
	    	  date= df.parse(input);
	          //old date format to new date format
	    	  output = outputformat.format(date);
	    	  System.out.println(output);
	   }
	   catch(Exception e)
	   {
		   e.printStackTrace();
	   }
	return output;
   }
	public UserEventDetailsVO getMatchedEvent(List<UserEventDetailsVO> eventsList,Long id)
	{
		try{
			if(eventsList == null || eventsList.size() == 0)
				return null;
			for(UserEventDetailsVO vo : eventsList)
			{
				if(vo.getId().longValue() == id.longValue())
					return vo;
			}
			
		}
		catch(Exception e)
		{
			Log.error("Exception Occured in getMatchedEvent() method",e) ;
			e.printStackTrace();	
		}
		return null;
	}
	 public UserEventDetailsVO insertEventAttendeeInfo(UserEventDetailsVO inpuVo)
		{
		 UserEventDetailsVO returnVo = new UserEventDetailsVO();
		 try{
			 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 	String memberShipNumber = "AP14"+inpuVo.getMemberShipNo();
			String memberShipNumber1 = "TS14"+inpuVo.getMemberShipNo();
		 	StringBuilder queryStr = new StringBuilder();
		 	if(inpuVo.getMemberShipNo() != null && !inpuVo.getMemberShipNo().isEmpty())
		 	queryStr.append(" (model.memberShipNo ='"+memberShipNumber.trim()+"' OR model.memberShipNo ='"+memberShipNumber1.trim()+"') ");
		 	else
		 	{
		 		queryStr.append(" model.cardNumber = '"+inpuVo.getRFID()+"' ");
		 	}
		 	EventAttendee eventAttendee = new EventAttendee();
		 	Long cadreId = tdpCadreDAO.getTdpCadreIdByMembership(queryStr.toString());
		 	if(cadreId != null)
		 	eventAttendee.setTdpCadreId(cadreId);
		 	eventAttendee.setImei(inpuVo.getIMEI());
		 	if(inpuVo.getRFID() != null) 
		 	eventAttendee.setRfid(inpuVo.getRFID());
		 	
		 	eventAttendee.setInsertedBy(inpuVo.getId());
		 	eventAttendee.setEventId(inpuVo.getEventId());
		 	eventAttendee.setAttendedTime(formatter.parse(inpuVo.getLoginTimeStamp().toString()));
		 	eventAttendee.setInsertedTime(formatter.parse(inpuVo.getLoginTimeStamp().toString()));
		 	
		 	if(inpuVo.getStatus() != null)
		 	eventAttendee.setUniqueKey(inpuVo.getStatus());
		 	eventAttendee = eventAttendeeDAO.save(eventAttendee);
		 	voterDAO.flushAndclearSession();
		 	returnVo.setId(eventAttendee.getEventAttendeeId());
		 	returnVo.setStatus("success");
		 	returnVo.setUserId(inpuVo.getId());
		 	if(cadreId != null)
		 	returnVo.setMemberShipNo(cadreId.toString());
		 	/*resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
		 	resultStatus.setMessage("success");*/
		 }
		 catch(Exception e)
		 {
			 Log.error("Exception Occured in getMatchedEvent() method",e) ;
			 e.printStackTrace();
				returnVo.setStatus("fail");
		 }
		return returnVo;
	}
}

