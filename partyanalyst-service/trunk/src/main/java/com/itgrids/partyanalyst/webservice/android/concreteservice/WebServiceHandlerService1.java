package com.itgrids.partyanalyst.webservice.android.concreteservice;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.itgrids.partyanalyst.dao.IBoothDAO;
import com.itgrids.partyanalyst.dao.IBoothPublicationVoterDAO;
import com.itgrids.partyanalyst.dao.IMobileAppPingingDAO;
import com.itgrids.partyanalyst.dao.IMobileAppUserAccessKeyDAO;
import com.itgrids.partyanalyst.dao.IMobileAppUserDAO;
import com.itgrids.partyanalyst.dao.IMobileAppUserProfileDAO;
import com.itgrids.partyanalyst.dao.IPingingTypeDAO;
import com.itgrids.partyanalyst.dao.IUserDAO;
import com.itgrids.partyanalyst.dao.IUserSurveyBoothsDAO;
import com.itgrids.partyanalyst.dao.IUserVoterDetailsDAO;
import com.itgrids.partyanalyst.dao.IVoiceRecordingDetailsDAO;
import com.itgrids.partyanalyst.dao.IVoterBoothActivitiesDAO;
import com.itgrids.partyanalyst.dao.IVoterTagDAO;
import com.itgrids.partyanalyst.dao.IWebServiceBaseUrlDAO;
import com.itgrids.partyanalyst.dto.CadreInfo;
import com.itgrids.partyanalyst.dto.EffectedBoothsResponse;
import com.itgrids.partyanalyst.dto.FlagVO;
import com.itgrids.partyanalyst.dto.PanchayatCountVo;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.VoterDetailsVO;
import com.itgrids.partyanalyst.dto.WSResultVO;
import com.itgrids.partyanalyst.model.Booth;
import com.itgrids.partyanalyst.model.MobileAppPinging;
import com.itgrids.partyanalyst.model.MobileAppUser;
import com.itgrids.partyanalyst.model.MobileAppUserAccessKey;
import com.itgrids.partyanalyst.model.User;
import com.itgrids.partyanalyst.model.UserVoterDetails;
import com.itgrids.partyanalyst.model.VoterBoothActivities;
import com.itgrids.partyanalyst.model.VoterTag;
import com.itgrids.partyanalyst.security.PBKDF2;
import com.itgrids.partyanalyst.service.IInfluencingPeopleService;
import com.itgrids.partyanalyst.service.ILoginService;
import com.itgrids.partyanalyst.service.IMailService;
import com.itgrids.partyanalyst.service.IMobileService;
import com.itgrids.partyanalyst.service.ISmsService;
import com.itgrids.partyanalyst.service.IStrategyModelTargetingService;
import com.itgrids.partyanalyst.service.ISurveyDataDetailsService;
import com.itgrids.partyanalyst.service.IVoiceSmsService;
import com.itgrids.partyanalyst.service.IVoterReportService;
import com.itgrids.partyanalyst.utils.DateUtilService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.itgrids.partyanalyst.utils.Util;
import com.itgrids.partyanalyst.webservice.android.abstractservice.IWebServiceHandlerService1;
import com.itgrids.partyanalyst.webservice.utils.VoterTagVO;
import com.itgrids.partyanalyst.webserviceutils.android.utilvos.UserLoginVO;


public class WebServiceHandlerService1 implements IWebServiceHandlerService1 {
	
	private static final Logger log = Logger.getLogger(WebServiceHandlerService1.class);
	
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
	
	private IInfluencingPeopleService influencingPeopleService;
	private IVoterReportService voterReportService;
	private IVoterTagDAO voterTagDAO;
	private IVoterBoothActivitiesDAO voterBoothActivitiesDAO;
	private DateUtilService dateUtilService = new DateUtilService();
	
	@Autowired IUserDAO userDAO;
	@Autowired IStrategyModelTargetingService strategyModelTargetingService;
    @Autowired IUserSurveyBoothsDAO userSurveyBoothsDAO ;
    @Autowired ISurveyDataDetailsService surveyDataDetailsService;
    
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

	public String checkForUserAuthentication(UserLoginVO inputvo)
	{
		StringBuilder buffer= new StringBuilder();
		
		buffer.append("{\"statusCode:\"");
		Long userId=null;
		log.debug("Entered into the checkForUserAuthentication  method in WebServiceHandlerService");
		try
		{
			 userId=surveyDataDetailsService.getUserDetailsForCheck(inputvo.getUserName(), inputvo.getPassWord());
		
		
		
		}catch(Exception e)
		{
			log.error("Exception raised in checkForUserAuthentication  method in WebServiceHandlerService");
			e.printStackTrace();
			
		}
		return userId!=null?userId.toString():"";
	}
	
/*	public ResultStatus checkUserAuthenticationAndUpdateAuthorisedTime(String userId,String macAdressId)
	{
		try{
		List<Object> mobileAppUserId = mobileAppUserDAO.checkUniqueCode(userId);
		if(mobileAppUserId != null)
		{
		List<Object> pingingTypeId = pingingTypeDAO.getPingingTypeIdByType(IConstants.App_Authorization);
		//saveMobileAppPingIngDetails((Long) mobileAppUserId.get(0),(Long)pingingTypeId.get(0),null,null);
		}
		return mobileService.checkAuthenticateUserAndUpdateLastAuthorisedTime(userId, macAdressId);
		}
		catch(Exception e)
		{
			log.error("Exception raised in checkUserAuthenticationAndUpdateAuthorisedTime  method in WebServiceHandlerService");
			return null;
		}
		
	}
	*/
	
	
	
}

