package com.itgrids.partyanalyst.webservice.android.concreteservice;
       
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.itgrids.partyanalyst.dao.IBoothDAO;
import com.itgrids.partyanalyst.dao.IBoothPublicationVoterDAO;
import com.itgrids.partyanalyst.dao.ICadreSurveyUserAssignDetailsDAO;
import com.itgrids.partyanalyst.dao.ICadreSurveyUserDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyDAO;
import com.itgrids.partyanalyst.dao.ILoginDetailsByTabDAO;
import com.itgrids.partyanalyst.dao.IMobileAppPingingDAO;
import com.itgrids.partyanalyst.dao.IMobileAppUserAccessKeyDAO;
import com.itgrids.partyanalyst.dao.IMobileAppUserDAO;
import com.itgrids.partyanalyst.dao.IMobileAppUserProfileDAO;
import com.itgrids.partyanalyst.dao.IPingingTypeDAO;
import com.itgrids.partyanalyst.dao.ISurveyDetailsInfoDAO;
import com.itgrids.partyanalyst.dao.ISurveyUserBoothAssignDAO;
import com.itgrids.partyanalyst.dao.ITabLogInAuthDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreImageSinkDataDAO;
import com.itgrids.partyanalyst.dao.IUserSurveyBoothsDAO;
import com.itgrids.partyanalyst.dao.IUserVoterDetailsDAO;
import com.itgrids.partyanalyst.dao.IVoiceRecordingDetailsDAO;
import com.itgrids.partyanalyst.dao.IVoterBoothActivitiesDAO;
import com.itgrids.partyanalyst.dao.IVoterDAO;
import com.itgrids.partyanalyst.dao.IVoterTagDAO;
import com.itgrids.partyanalyst.dao.IWebServiceBaseUrlDAO;
import com.itgrids.partyanalyst.dto.AppDbDataVO;
import com.itgrids.partyanalyst.dto.CadreImageVO;
import com.itgrids.partyanalyst.dto.CadreInfo;
import com.itgrids.partyanalyst.dto.CadrePreviousRollesVO;
import com.itgrids.partyanalyst.dto.CadrePrintVO;
import com.itgrids.partyanalyst.dto.CadreRegistrationVO;
import com.itgrids.partyanalyst.dto.LoginResponceVO;
import com.itgrids.partyanalyst.dto.LoginStatusVO;
import com.itgrids.partyanalyst.dto.MissedCallCampaignVO;
import com.itgrids.partyanalyst.dto.NewCadreRegistrationVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SinkVO;
import com.itgrids.partyanalyst.dto.SurveyCadreResponceVO;
import com.itgrids.partyanalyst.dto.SurveyResponceVO;
import com.itgrids.partyanalyst.dto.TabRecordsStatusVO;
import com.itgrids.partyanalyst.dto.TdpCadreVO;
import com.itgrids.partyanalyst.dto.VoterInfoVO;
import com.itgrids.partyanalyst.dto.VoterWebServiceDataVO;
import com.itgrids.partyanalyst.model.CadreSurveyUser;
import com.itgrids.partyanalyst.model.CadreSurveyUserAssignDetails;
import com.itgrids.partyanalyst.model.LoginDetailsByTab;
import com.itgrids.partyanalyst.model.TabLogInAuth;
import com.itgrids.partyanalyst.model.TdpCadre;
import com.itgrids.partyanalyst.model.TdpCadreImageSinkData;
import com.itgrids.partyanalyst.service.ICadreDashBoardService;
import com.itgrids.partyanalyst.service.ICadreDetailsService;
import com.itgrids.partyanalyst.service.ICadreRegistrationService;
import com.itgrids.partyanalyst.service.ICoreDashboardCadreRegistrationService;
import com.itgrids.partyanalyst.service.IInfluencingPeopleService;
import com.itgrids.partyanalyst.service.ILoginService;
import com.itgrids.partyanalyst.service.IMailService;
import com.itgrids.partyanalyst.service.IMobileService;
import com.itgrids.partyanalyst.service.ISmsService;
import com.itgrids.partyanalyst.service.IStrategyModelTargetingService;
import com.itgrids.partyanalyst.service.ISurveyDashBoardService;
import com.itgrids.partyanalyst.service.ISurveyDataDetailsService;
import com.itgrids.partyanalyst.service.ISurveyDetailsService;
import com.itgrids.partyanalyst.service.IVoiceSmsService;
import com.itgrids.partyanalyst.service.IVoterReportService;
import com.itgrids.partyanalyst.utils.CommonMethodsUtilService;
import com.itgrids.partyanalyst.utils.CommonUtilsService;
import com.itgrids.partyanalyst.utils.DateUtilService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.itgrids.partyanalyst.utils.ImageAndStringConverter;
import com.itgrids.partyanalyst.webservice.android.abstractservice.IWebServiceHandlerService1;
import com.itgrids.partyanalyst.webserviceutils.android.utilvos.BoothVoterVO;
import com.itgrids.partyanalyst.webserviceutils.android.utilvos.UserLocationTrackingVo;
import com.itgrids.partyanalyst.webserviceutils.android.utilvos.UserLoginUtils;
import com.itgrids.partyanalyst.webserviceutils.android.utilvos.UserLoginVO;
import com.itgrids.partyanalyst.webserviceutils.android.utilvos.UserResponseSub;
import com.itgrids.partyanalyst.webserviceutils.android.utilvos.UserResponseVO;
@Service
@Qualifier("web1")
public class WebServiceHandlerService1 implements IWebServiceHandlerService1 {
	
	
	/**
	 * @author Anilkumar Ravula
	 * Jul 3, 2014
	 * @param 
	 */
	private static final Logger LOG = Logger.getLogger(WebServiceHandlerService1.class);

	public  WebServiceHandlerService1() {
		LOG.info("got instanciated===========");	

	}


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
	@Autowired private ILoginDetailsByTabDAO loginDetailsByTabDAO;
	@Autowired private IBoothPublicationVoterDAO boothPublicationVoterDAO;
	private IUserVoterDetailsDAO userVoterDetailsDAO;
	@Autowired private IBoothDAO boothDAO;
	
	private IInfluencingPeopleService influencingPeopleService;
	private IVoterReportService voterReportService;
	private IVoterTagDAO voterTagDAO;
	private IVoterBoothActivitiesDAO voterBoothActivitiesDAO;
	
	@Autowired
	private ITdpCadreDAO tdpCadreDAO;
	private ImageAndStringConverter imageAndStringConverter = new ImageAndStringConverter();
	
	@Autowired private IStrategyModelTargetingService strategyModelTargetingService;
    @Autowired private IUserSurveyBoothsDAO userSurveyBoothsDAO ;
    @Autowired private ISurveyDataDetailsService surveyDataDetailsService;
    
    @Autowired
	 public ISurveyUserBoothAssignDAO surveyUserBoothAssignDAO; 
    
    @Autowired
    public ISurveyDetailsInfoDAO  surveyDetailsInfoDAO;
    
    
	@Autowired
	private TransactionTemplate transactionTemplate;
	
	@Autowired
	private ISurveyDetailsService surveyDetailsService;
	
	@Autowired
	private ISurveyDashBoardService  surveyDashBoardService;
	
	@Autowired
	private ICadreSurveyUserDAO cadreSurveyUserDAO ;
	
	@Autowired
	private ICadreSurveyUserAssignDetailsDAO cadreSurveyUserAssignDetailsDAO;
	
	@Autowired ICadreRegistrationService cadreRegistrationService;
	
	@Autowired IDelimitationConstituencyDAO  delimitationConstituencyDAO;
	@Autowired ICadreDashBoardService cadreDashBoardService;
    
	@Autowired ITabLogInAuthDAO tabLogInAuthDAO;
	
	@Autowired CommonUtilsService commonUtilsService;
	
	@Autowired ITdpCadreImageSinkDataDAO tdpCadreImageSinkDataDAO;
	@Autowired
	private ICadreDetailsService cadreDetailsService;
	@Autowired 
	CommonMethodsUtilService commonMethodsUtilService;
	@Autowired 
	private ICoreDashboardCadreRegistrationService coreDashboardCadreRegistrationService;
	@Autowired 
	private IVoterDAO voterDAO;
	
	public IVoterBoothActivitiesDAO getVoterBoothActivitiesDAO() {
		return voterBoothActivitiesDAO;
	}

	public void setVoterBoothActivitiesDAO(
			IVoterBoothActivitiesDAO voterBoothActivitiesDAO) {
		this.voterBoothActivitiesDAO = voterBoothActivitiesDAO;
	}

	public void setTdpCadreDAO(ITdpCadreDAO tdpCadreDAO) {
		this.tdpCadreDAO = tdpCadreDAO;
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
	
	
	

	/*public IBoothDAO getBoothDAO() {
		return boothDAO;
	}

	public void setBoothDAO(IBoothDAO boothDAO) {
		this.boothDAO = boothDAO;
	}*/

	public IUserVoterDetailsDAO getUserVoterDetailsDAO() {
		return userVoterDetailsDAO;
	}

	public void setUserVoterDetailsDAO(IUserVoterDetailsDAO userVoterDetailsDAO) {
		this.userVoterDetailsDAO = userVoterDetailsDAO;
	}

/*	public IBoothPublicationVoterDAO getBoothPublicationVoterDAO() {
		return boothPublicationVoterDAO;
	}

	public void setBoothPublicationVoterDAO(
			IBoothPublicationVoterDAO boothPublicationVoterDAO) {
		this.boothPublicationVoterDAO = boothPublicationVoterDAO;
	}
*/
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
    public ILoginDetailsByTabDAO getLoginDetailsByTabDAO() {
		return loginDetailsByTabDAO;
	}

	public void setLoginDetailsByTabDAO(ILoginDetailsByTabDAO loginDetailsByTabDAO) {
		this.loginDetailsByTabDAO = loginDetailsByTabDAO;
	}

	// @Override
	public UserResponseVO checkForUserAuthentication(UserLoginVO inputvo)
	{/*
		StringBuilder buffer= new StringBuilder();
		
		buffer.append("{\"statusCode:\"");*/
		Object[] userId=null;
		UserResponseVO res=null;
		LOG.debug("Entered into the checkForUserAuthentication  method in WebServiceHandlerService");
		try
		{
			//check user availability
			 userId=surveyDataDetailsService.auhenticateUserandGetUserType(inputvo.getUserName(), inputvo.getPassWord());
  
			 //if user not available 
			 
			
			 if(userId==null)
				 return null;
			 
			    //stop here and return
			  res= buildDateForSurverUsers(userId,inputvo);
			 
			 // bulid response based on userType
		
		
		}catch(Exception e)
		{
			LOG.error("Exception raised in checkForUserAuthentication  method in WebServiceHandlerService");
			
			
		}
		return res;
	}

	
	
	
	public UserResponseVO  buildDateForSurverUsers(Object[] inputs, UserLoginVO inputvo)
	{
		
		UserResponseVO res=null;
		
		final long userId=(Long)inputs[0];
		final int userTypeId=((Long)inputs[1]).intValue();

	//check  usertypes
		
		switch(userTypeId)
		{
		         //collector
		case 1:
			res=buildCollectorData(userId, userTypeId);
			
			break;
			     //verifier
		case 4:
			res=buildverifierData(userId, userTypeId,inputvo);
			break;
		case 10:
			res=buildThirdPartyVerifierData(userId, userTypeId,inputvo);
			break;
		
		default:break;
		
		}
		return res;
		
	}
	/**
	 * 
	 * @author Anilkumar Ravula
	 * Jul 2, 2014
	 * @param
	 */
	@SuppressWarnings("unchecked")
	public UserResponseVO buildCollectorData(final long userId,final long userTypeId)
	{
		//check data already available fo user
	          //need to confirm
		
		//get booths for collector
		 List<Object[]> booths =(List<Object[]>) surveyUserBoothAssignDAO.getBoothsForUser(userId);
		
		
		 List<Long> remainingDatBoothIds = new ArrayList<Long>();
		
		for(Object[] obj:booths)
		{
			if(obj[3].toString().equalsIgnoreCase("Y"))
			{
				remainingDatBoothIds.add((Long)obj[0]);
			}
		}
		
		  List<Long> voterIds=null;
		if(remainingDatBoothIds!=null && !remainingDatBoothIds.isEmpty())
		{
         voterIds =   boothPublicationVoterDAO.getAllVoterIdsByBoothIdsAndPublicationDateId(remainingDatBoothIds,IConstants.VOTER_DATA_PUBLICATION_ID);
		}
         if(voterIds!=null && !voterIds.isEmpty()){
         List<Long> existVoterIds = surveyDetailsInfoDAO.getDataCollectedVoterIdsByBoothIds(remainingDatBoothIds); 
         
         voterIds.removeAll(existVoterIds);
		}
		UserResponseVO res=buildResponseVo(booths, userTypeId, userId,remainingDatBoothIds,voterIds);
		//process list and convert
	   return res;
	}
	
	 public UserResponseVO buildResponseVo(List<Object[]> booths,long userTypeId,long userId,List<Long> remainingDatBoothIds,List<Long> voterIds)
     {
             //UserResponseVO res= new UserResponseVO();
             UserResponseSub subRes= new UserResponseSub();
             List<String> partNumbers=null;
             if(remainingDatBoothIds!=null && remainingDatBoothIds.size()>0){
            	 partNumbers = boothDAO.getPartNosForBooths(remainingDatBoothIds);
             }
             List<Object[]>   votersBoothsDetails=null;
             if(voterIds!=null && voterIds.size()>0)
            	 votersBoothsDetails =   boothPublicationVoterDAO.getBoothIdsDetailsOfVoterIds(voterIds, IConstants.VOTER_DATA_PUBLICATION_ID);
             
/*           if(votersBoothsDetails!=null && votersBoothsDetails.size()>0)
             List<String> partNumbers = boothDAO.getPartNosForBooths(remainingDatBoothIds);
             List<Object[]>   votersBoothsDetails =   boothPublicationVoterDAO.getBoothIdsDetailsOfVoterIds(voterIds, 10L);*/
             
		      List<BoothVoterVO> boothVotersVOList = new ArrayList<BoothVoterVO>();   
		      
		      Set<Long> boothIds1 = new HashSet<Long>();
		      if(votersBoothsDetails!=null && votersBoothsDetails.size()>0)
		      for(Object[] obj:votersBoothsDetails)
		    	  boothIds1.add((Long)obj[2]);
		      if(boothIds1!=null && boothIds1.size()>0)
		      for(Long boothId:boothIds1)
		      {
		    	  BoothVoterVO vo = new BoothVoterVO();
		    	  vo.setBoothId(boothId);
		    	  boothVotersVOList.add(vo);
		      }
		      if(votersBoothsDetails!=null && votersBoothsDetails.size()>0)
               for(Object[] obj:votersBoothsDetails)
                {                     
                     BoothVoterVO boothVoterVO = getMatchedBoothVO(boothVotersVOList,(Long)obj[2]);
                     
                     if(boothVoterVO != null)
                     {
                             boothVoterVO.getVoterIds().add((Long)obj[1]);
                             boothVoterVO.setPartNo(obj[0].toString());
                             
                     }else
                     {
                             BoothVoterVO boothVoter  = new BoothVoterVO();
                             
                             boothVoter.setPartNo(obj[0].toString());
                             
                             List<Long> votersList = new ArrayList<Long>();
                             votersList.add((Long)obj[1]);
                             
                             boothVoter.setVoterIds(votersList);
                             
                     }
            
             }
             
             
             if(remainingDatBoothIds != null)
             {
              subRes.setRemainingDataBoothIds(partNumbers);
              subRes.setVoterIds(boothVotersVOList);
             }
             
             
             int count=0;
             
             for (Object[] objects : booths) {
                     if(count==0) {
                             subRes.setConstituencyId(objects[2].toString());
                             subRes.setUserId(String.valueOf(userId));
                         subRes.setUserTypeId(String.valueOf(userTypeId));
                         ArrayList<String> partNos=new ArrayList<String>();
                         partNos.add(objects[1].toString());
                         ArrayList<String> boothIds=new ArrayList<String>();
                         boothIds.add(objects[0].toString());
                         
                         subRes.setPartNos(partNos);
                         subRes.setBoothIds(boothIds);
                     }else {
                     subRes.getBoothIds().add(objects[0].toString());
                     subRes.getPartNos().add(objects[1].toString());
                     }
                     count++;
             }
             
             return subRes;
     }
	

	/**
	 * 
	 * @author Anilkumar Ravula
	 * Jul 3, 2014
	 * @param userId
	 * @param userTypeId
	 * @param inputvo 
	 */
	public UserResponseVO  buildverifierData(long userId,long userTypeId, UserLoginVO inputvo)
	{
		//check assigned booth for verifier
		
		List<Object[]> booths =(List<Object[]>) surveyUserBoothAssignDAO.getBoothsForUser(userId);
	
		UserResponseSub res=(UserResponseSub) buildResponseVo(booths, userTypeId, userId,null,null);
		
		SurveyResponceVO responseVo= new SurveyResponceVO();
		responseVo.setConstituencyId(res.getConstituencyId());
		responseVo.setUserId(res.getUserId());
		responseVo.setUserTypeId(res.getUserTypeId());
		responseVo.setBoothIds(res.getBoothIds()); 
		responseVo.setPartNos(res.getPartNos());
		
		UserLoginUtils subVo=(UserLoginUtils) inputvo;
		Long boothId=subVo.getBoothId()!=null?Long.valueOf(subVo.getBoothId()):0;
		
		if((res.getBoothIds().size()>=1 && boothId!=0) || (res.getBoothIds().size()==1 && boothId==0))
		{
		//for (String boothId :res.getBoothIds() ) {
			
			if(boothId==0)
				boothId=Long.valueOf(res.getBoothIds().get(0));
				
			List<SurveyResponceVO>  verifiers=surveyDataDetailsService.getDetailsForVerifier(userId, Long.valueOf(boothId));
			if(verifiers!=null&&verifiers.size()>0)
			{
				List<SurveyResponceVO> verifiersList=responseVo.getVerifiersData();				
				if(verifiersList==null)				
					verifiersList=new ArrayList<SurveyResponceVO>();				
				verifiersList.addAll(verifiers);
				responseVo.setVerifiersData(verifiersList);
				responseVo.setVotersSize(verifiersList.size());
			}
		//} // for
			
		}//if
		// check whether data available  for all those booths or not 6782934
	  //	List<Object[]> votersData =(List<Object[]>) surveyUserBoothAssignDAO.getVoterDataForUser(1l);

		//process data 
		return responseVo;
	}

	public Object saveSurveyFieldUsers(SurveyResponceVO inputResponse)
	{
		
		List<SurveyResponceVO> surveyResponceList=inputResponse.getVerifiersData();
		ResultStatus  rs=surveyDataDetailsService.saveSurveyDataDetailsInfo(inputResponse)	;	
		return rs;
	}
	//saveSurveyUserTrackingDetails
	
	public ResultStatus saveUserTrackingLocation(final UserLocationTrackingVo userLocationTrackingVo)
	{    final ResultStatus  status=  new ResultStatus();;
	    transactionTemplate.execute(new TransactionCallbackWithoutResult() {

		@Override
		protected void doInTransactionWithoutResult(
				TransactionStatus arg0) {
			for ( UserLocationTrackingVo trackPoints: userLocationTrackingVo.getUserLocations()) {
				
				trackPoints.setImeiNo(userLocationTrackingVo.getImeiNo());
				trackPoints.setSurveyUserId(userLocationTrackingVo.getSurveyUserId());			
				surveyDataDetailsService.saveSurveyUserTrackingDetails(trackPoints);
		}
			
			status.setResultCode(0);
			status.setMessage("Success");

		}
		});
		
		
	//	ResultStatus  status=	surveyDataDetailsService.saveSurveyUserTrackingDetails(userLocationTrackingVo);
		
		
		return status;		
	}
	private BoothVoterVO getMatchedBoothVO(List<BoothVoterVO> boothVoters , Long boothId)
    {
            for(BoothVoterVO boothVoter:boothVoters)
                    if(boothVoter.getBoothId().equals(boothId))
                            return boothVoter;
            return null;
            
    }
	public UserResponseVO  buildThirdPartyVerifierData(long userId,long userTypeId, UserLoginVO inputvo)
	{
		//check assigned booth for verifier
		
		List<Object[]> booths =(List<Object[]>) surveyUserBoothAssignDAO.getBoothsForUser(userId);
	
		UserResponseSub res=(UserResponseSub) buildResponseVo(booths, userTypeId, userId,null,null);
		
		SurveyResponceVO responseVo= new SurveyResponceVO();
		responseVo.setConstituencyId(res.getConstituencyId());
		responseVo.setUserId(res.getUserId());
		responseVo.setUserTypeId(res.getUserTypeId());
		responseVo.setBoothIds(res.getBoothIds()); 
		responseVo.setPartNos(res.getPartNos());
		
		UserLoginUtils subVo=(UserLoginUtils) inputvo;
		Long boothId=subVo.getBoothId()!=null?Long.valueOf(subVo.getBoothId()):0;
		
		if((res.getBoothIds().size()>=1 && boothId!=0) || (res.getBoothIds().size()==1 && boothId==0))
		{
		//for (String boothId :res.getBoothIds() ) {
			
			if(boothId==0)
				boothId=Long.valueOf(res.getBoothIds().get(0));
			//public List<SurveyResponceVO> getThirdPartyVerificationDetails(Long boothId)

		//	List<SurveyResponceVO>  verifiers=surveyDetailsService.getThirdPartyVerificationDetails(Long.valueOf(boothId),userId);
			List<SurveyResponceVO>  verifiers=surveyDashBoardService.getThirdPartyFinalDetails(boothId);
			if(verifiers!=null&&verifiers.size()>0)
			{
				List<SurveyResponceVO> verifiersList=responseVo.getVerifiersData();				
				if(verifiersList==null)				
					verifiersList=new ArrayList<SurveyResponceVO>();				
				verifiersList.addAll(verifiers);
				responseVo.setVerifiersData(verifiersList);
				responseVo.setVotersSize(verifiersList.size());
			}
		//} // for
			
		}//if
		// check whether data available  for all those booths or not 6782934
	  //	List<Object[]> votersData =(List<Object[]>) surveyUserBoothAssignDAO.getVoterDataForUser(1l);

		//process data 
		return responseVo;
	}
	
	
	
	
	public LoginResponceVO checkForUserAuthenticationForCadre(UserLoginVO inputvo)
	{
		 LoginResponceVO returnVO = null;
		LOG.debug("Entered into the checkForUserAuthenticationForCadre  method in WebServiceHandlerService");
		try
		{
			List<Long> userIds=cadreSurveyUserDAO.getUserByUserNameAndPassword(inputvo.getUserName(), inputvo.getPassWord());
			 if(userIds != null && userIds.size() > 0)
			 {
				 returnVO = new LoginResponceVO();
				 prepareResponce(userIds.get(0),returnVO);
			 }
			
		
		}catch(Exception e)
		{
			LOG.error("Exception raised in checkForUserAuthenticationForCadre  method in WebServiceHandlerService",e);

		}
		
		return returnVO;
	}
	public void savingUserDetailsWhoLoggedIn( UserLoginUtils inputs, LoginResponceVO out)
	{
		try{
			
			
				LoginDetailsByTab loginDetailsByTab = new LoginDetailsByTab();
				
				loginDetailsByTab.setLoginDateTime(new DateUtilService().getCurrentDateAndTime());
				if(inputs!=null)
				{
				  loginDetailsByTab.setUserName(inputs.getUserName());
				  loginDetailsByTab.setPassWord(inputs.getPassWord());
				}
				
				if(out!=null)
				  loginDetailsByTab.setLoginStatus("Success");
				else
				  loginDetailsByTab.setLoginStatus("Failure");
				
				loginDetailsByTabDAO.save(loginDetailsByTab);
				
			
		
	     }
		 catch(Exception e){
			
			LOG.error("Exception raised in savingUserDetailsWhoLoggedIn  method in WebServiceHandlerService1"+e);
			e.printStackTrace();
		}
	
	}
	
	public LoginResponceVO databaseCheckForCadreUser(UserLoginVO inputvo)
	{
		 LoginResponceVO returnVO = null;
		 LOG.debug("Entered into the databaseCheckForCadreUser  method in WebServiceHandlerService");
		try {
			List<Long> userIds=cadreSurveyUserDAO.getUserByUserNameAndPassword(inputvo.getUserName(), inputvo.getPassWord());
			if(userIds != null && userIds.size() > 0)
			{
				List<CadreSurveyUserAssignDetails> resultList = cadreSurveyUserAssignDetailsDAO.getCadreAssinedDetails(userIds.get(0));
				if(resultList != null && resultList.size() > 0)
				{
					returnVO = new LoginResponceVO();
					returnVO.setStatusMsg("DBINITIALCHECK");
					
					returnVO.setConstituencyName(resultList.get(0).getConstituency() != null ? resultList.get(0).getConstituency().getName() : null);
					returnVO.setAcNo(delimitationConstituencyDAO.getConstituencyNo(resultList.get(0).getConstituency().getConstituencyId(),2009l));
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnVO;
	}
	
	public void prepareResponce(Long userId,LoginResponceVO loginResponceVO)
	{
		try {
			LOG.debug("Entered into the prepareResponce  method in WebServiceHandlerService");
			List<CadreSurveyUserAssignDetails> resultList = cadreSurveyUserAssignDetailsDAO.getCadreAssinedDetails(userId);
			if(resultList != null && resultList.size() > 0)
			{
				loginResponceVO.setStatusMsg("SUCCUESS");
				loginResponceVO.setConstituencyId(resultList.get(0).getConstituencyId());
				loginResponceVO.setConstituencyName(resultList.get(0).getConstituency() != null ? resultList.get(0).getConstituency().getName() : null);
				loginResponceVO.setUserId(userId);
				loginResponceVO.setTabNo(resultList.get(0).getTabNo());
				List<LoginStatusVO> subList = new ArrayList<LoginStatusVO>();
				Map<Long, List<Long>> subListMap = new HashMap<Long, List<Long>>();
				loginResponceVO.setAcNo(delimitationConstituencyDAO.getConstituencyNo(loginResponceVO.getConstituencyId(),2009l));
				for (CadreSurveyUserAssignDetails cadreSurveyUserAssignDetails : resultList) 
				{
					
					List<Long> ids = subListMap.get(cadreSurveyUserAssignDetails.getLevelId());
					if(ids == null)
					{
						ids = new ArrayList<Long>();
						subListMap.put(cadreSurveyUserAssignDetails.getLevelId(), ids);
					}
					ids.add(cadreSurveyUserAssignDetails.getLevelValue());
					
				}
				if(subListMap != null && subListMap.size() > 0)
				{
					for(Long statusId : subListMap.keySet())
					{
						LoginStatusVO loginStatusVO = new LoginStatusVO();
						loginStatusVO.setStatusId(statusId);
						loginStatusVO.setStatusRelatedValues(subListMap.get(statusId));
						subList.add(loginStatusVO);
					}
					loginResponceVO.setStatusList(subList);
				}
				
			}
		} catch (Exception e) {
			LOG.error("Exception raised in prepareResponce  method in WebServiceHandlerService",e);
		}
	}

	
	public Object saveSurveyFieldUsersForCadre(List<CadreRegistrationVO> inputResponseList)
	{
		SurveyCadreResponceVO  surveyCadreResponceVO = null;
		if(inputResponseList != null && inputResponseList.size() > 0)
		{
			if(IConstants.ENABLE_CADRE_LOGS)
			for (CadreRegistrationVO inputResponse : inputResponseList)
			{
				LOG.error(inputResponse.toString());
				
				LOG.error("Voter Name  " + "-" + inputResponse.getVoterName() + "-" +"Date Of Birth "+  "-" + inputResponse.getDob() +"-"+ "Gender " +inputResponse.getGender()+  "-" +"Relative Name"+ inputResponse.getRelativeName() +"-" +"VoterCardNumber"+  inputResponse.getVoterCardNo() + "-" + "H.NO" + inputResponse.getHouseNo() + "-" +"Party Member Since" +inputResponse.getPartyMemberSince()  + "-" + "Blood Group " + inputResponse.getBloodGroupId() + "-" + "Street/hamle" +inputResponse.getStreet() +"-" +"Caste" + inputResponse.getCasteId() + "-" + "Mobile No" + inputResponse.getMobileNumber() + "-" + "Education" +inputResponse.getEducationId() + "-" + "Occupation " +inputResponse.getOccupationId() + "-" + "Previous Enroll Ment No " + inputResponse.getPreviousEnrollmentNumber());
				if(inputResponse.getPreviousParicaptedElectionsList() != null && inputResponse.getPreviousParicaptedElectionsList().size() > 0)
				{
					for (CadrePreviousRollesVO electionVO : inputResponse.getPreviousParicaptedElectionsList())
					{
						LOG.error("Designation Level" +electionVO.getDesignationLevelId() + "-" +  "Designation Level Value" + electionVO.getDesignationLevelValue() + "-" + "From Date" + electionVO.getFromDate() + "-" + "To Date" + electionVO.getToDate()  );
					}
				}
				if(inputResponse.getPreviousRollesList() != null && inputResponse.getPreviousRollesList().size() > 0)
				{
					for (CadrePreviousRollesVO electionVO : inputResponse.getPreviousRollesList())
					{
						LOG.error("Election Id" +electionVO.getElectionTypeId() + "-" +  "Constituency Id" + electionVO.getConstituencyId() );
					}
				}
				
			}
				surveyCadreResponceVO=cadreRegistrationService.saveCadreRegistration(inputResponseList,"TAB");	
		}
		
		return surveyCadreResponceVO;
		
	}
	
	public Object saveSurveyFieldUsersForCadreOnline(List<CadreRegistrationVO> inputResponseList)
	{
		SurveyCadreResponceVO  surveyCadreResponceVO = null;
		if(inputResponseList != null && inputResponseList.size() > 0)
		{
			for (CadreRegistrationVO inputResponse : inputResponseList)
			{
				LOG.debug(inputResponse.toString());
				
				LOG.error("Voter Name  " + "-" + inputResponse.getVoterName() + "-" +"Date Of Birth "+  "-" + inputResponse.getDob() +"-"+ "Gender " +inputResponse.getGender()+  "-" +"Relative Name"+ inputResponse.getRelativeName() +"-" +"VoterCardNumber"+  inputResponse.getVoterCardNo() + "-" + "H.NO" + inputResponse.getHouseNo() + "-" +"Party Member Since" +inputResponse.getPartyMemberSince()  + "-" + "Blood Group " + inputResponse.getBloodGroupId() + "-" + "Street/hamle" +inputResponse.getStreet() +"-" +"Caste" + inputResponse.getCasteId() + "-" + "Mobile No" + inputResponse.getMobileNumber() + "-" + "Education" +inputResponse.getEducationId() + "-" + "Occupation " +inputResponse.getOccupationId() + "-" + "Previous Enroll Ment No " + inputResponse.getPreviousEnrollmentNumber());
				if(inputResponse.getPreviousParicaptedElectionsList() != null && inputResponse.getPreviousParicaptedElectionsList().size() > 0)
				{
					for (CadrePreviousRollesVO electionVO : inputResponse.getPreviousParicaptedElectionsList())
					{
						LOG.error("Designation Level" +electionVO.getDesignationLevelId() + "-" +  "Designation Level Value" + electionVO.getDesignationLevelValue() + "-" + "From Date" + electionVO.getFromDate() + "-" + "To Date" + electionVO.getToDate()  );
					}
				}
				if(inputResponse.getPreviousRollesList() != null && inputResponse.getPreviousRollesList().size() > 0)
				{
					for (CadrePreviousRollesVO electionVO : inputResponse.getPreviousRollesList())
					{
						LOG.error("Election Id" +electionVO.getElectionTypeId() + "-" +  "Constituency Id" + electionVO.getConstituencyId() );
					}
				}
				
			}
				surveyCadreResponceVO=cadreRegistrationService.saveCadreRegistration(inputResponseList,"ONLINE");	
		}
		
		return surveyCadreResponceVO;
		
	}
	
	public Object getVCadreDataByPanchayatId(Long panchayatId)
	{
		List<CadrePrintVO> list = cadreRegistrationService.getSelectedLevelCadreDetails(panchayatId);
		return list;
	}
	
	public AppDbDataVO getAllVersionsOfAnApp(String appName,Double currentVerson,boolean includeTest){
		return cadreDashBoardService.getAllVersionsOfAnApp(appName, currentVerson, includeTest);
	}
	
	public AppDbDataVO getAllUpdatesByVersion(String appName,Double version){
		return cadreDashBoardService.getAllUpdatesByVersion(appName,version);
	}
	
	
    public LoginResponceVO checkValidLoginOrNot(String userName,String password,String imei1,String imei2,String version){
    	LoginResponceVO vo = new LoginResponceVO();
    	
	 List<CadreSurveyUser> users = cadreSurveyUserDAO.getByUserNameAndPassword(userName, password);
	 if(users == null || users.size() == 0 || users.get(0) == null){
		 vo.setStatus("login failure");
		 TabLogInAuth tabLogInAuth = new TabLogInAuth();
			tabLogInAuth.setUserName(userName);
			tabLogInAuth.setPassword(password);
			tabLogInAuth.setImeiNo(imei1);
			tabLogInAuth.setImeiNo2(imei2);
			tabLogInAuth.setInsertedTime(new DateUtilService().getCurrentDateAndTime());
			tabLogInAuth.setVersion(version);
			tabLogInAuth.setIsDeleted("Y");
			tabLogInAuth.setStatus("failure");
			tabLogInAuthDAO.save(tabLogInAuth);
		 return vo;
	 }else if(users.get(0).getIsExcluded().equalsIgnoreCase("Y")){
		 vo.setStatus("logged");
		 prepareResponceNew(users.get(0).getCadreSurveyUserId(), vo);
		TabLogInAuth tabLogInAuth = new TabLogInAuth();
		tabLogInAuth.setCadreSurveyUserId(users.get(0).getCadreSurveyUserId());
		tabLogInAuth.setImeiNo(imei1);
		tabLogInAuth.setImeiNo2(imei2);
		tabLogInAuth.setInsertedTime(new DateUtilService().getCurrentDateAndTime());
		tabLogInAuth.setVersion(version);
		tabLogInAuth.setIsDeleted("Y");
		tabLogInAuth.setStatus("success with exclude");
		tabLogInAuthDAO.save(tabLogInAuth);
		return vo;
	 }
	 synchronized ("checkValidCadreUserOrNot") {
		 if((imei1 != null && imei1.length() > 0) && (imei2 != null && imei2.length() > 0) ){
			  checkVaidLogInOrNot(users.get(0).getCadreSurveyUserId(),imei1,imei2,version,vo);
			 
		 }else{
			 String reqImei = imei1;
			 if(imei2 != null && imei2.length() > 0){
				 reqImei = imei2;
			 }		 
			  checkVaidLogInOrNot(users.get(0).getCadreSurveyUserId(),reqImei,version,vo);
			  
		 }
	 }
	 if(vo.getStatus().equalsIgnoreCase("logged")){
		 prepareResponceNew(users.get(0).getCadreSurveyUserId(), vo);
	 }
	 return vo;
	}
    
    public LoginResponceVO checkVaidLogInOrNot(Long userId,String imei,String version,LoginResponceVO vo){
    	Long count = tabLogInAuthDAO.checkRecordExistWithGivenDetailsOrNot(userId, imei);
    	
    	if(count.longValue() == 0){
    		TabLogInAuth tabLogInAuth = new TabLogInAuth();
    		tabLogInAuth.setCadreSurveyUserId(userId);
    		tabLogInAuth.setImeiNo(imei);
    		tabLogInAuth.setInsertedTime(new DateUtilService().getCurrentDateAndTime());
    		tabLogInAuth.setVersion(version);
    		tabLogInAuth.setIsDeleted("N");
    		tabLogInAuth.setStatus("success");
    		tabLogInAuthDAO.save(tabLogInAuth);
    		vo.setStatus("logged");
    		return vo;
    	}else{
    		count = tabLogInAuthDAO.checkRecordBelongsToUserOrNot(userId, imei);
    		if(count.longValue() > 0){
    			tabLogInAuthDAO.updateRecordBelongsToUserOrNot(userId, imei);
    			TabLogInAuth tabLogInAuth = new TabLogInAuth();
        		tabLogInAuth.setCadreSurveyUserId(userId);
        		tabLogInAuth.setImeiNo(imei);
        		tabLogInAuth.setInsertedTime(new DateUtilService().getCurrentDateAndTime());
        		tabLogInAuth.setVersion(version);
        		tabLogInAuth.setIsDeleted("N");
        		tabLogInAuth.setStatus("success");
        		tabLogInAuthDAO.save(tabLogInAuth);
        		vo.setStatus("logged");
        		return vo;
    		}else{
    			return getActualCaseForNotAllowingToLogIn(userId,imei,version,vo);
    		}
    	}
    	
    }
    
    private LoginResponceVO getActualCaseForNotAllowingToLogIn(Long userId,String imei,String version,LoginResponceVO vo){
    	
    	TabLogInAuth tabLogInAuth = new TabLogInAuth();
		tabLogInAuth.setCadreSurveyUserId(userId);
		tabLogInAuth.setImeiNo(imei);
		tabLogInAuth.setInsertedTime(new DateUtilService().getCurrentDateAndTime());
		tabLogInAuth.setVersion(version);
		tabLogInAuth.setIsDeleted("Y");
		
    	Long count = tabLogInAuthDAO.checkUserAlreadyLoggedInAnotherTab(userId, imei);
    	
    	if(count.longValue() > 0){
    		tabLogInAuth.setStatus("same user");
    		tabLogInAuthDAO.save(tabLogInAuth);
    		vo.setStatus("same user");
    		return vo;
    	}else{
    		tabLogInAuth.setStatus("same tab");
    		tabLogInAuthDAO.save(tabLogInAuth);
    		vo.setStatus("same tab");
    		return vo;
    	}
    }
    
    public LoginResponceVO checkVaidLogInOrNot(Long userId,String imei1,String imei2,String version,LoginResponceVO vo){
    	Long count = tabLogInAuthDAO.checkRecordExistWithGivenDetailsOrNot(userId,imei1,imei2);
    	
    	if(count.longValue() == 0){
    		TabLogInAuth tabLogInAuth = new TabLogInAuth();
    		tabLogInAuth.setCadreSurveyUserId(userId);
    		tabLogInAuth.setImeiNo(imei1);
    		tabLogInAuth.setImeiNo2(imei2);
    		tabLogInAuth.setInsertedTime(new DateUtilService().getCurrentDateAndTime());
    		tabLogInAuth.setVersion(version);
    		tabLogInAuth.setIsDeleted("N");
    		tabLogInAuth.setStatus("success");
    		tabLogInAuthDAO.save(tabLogInAuth);
    		vo.setStatus("logged");
    		return vo;
    	}else{
    		count = tabLogInAuthDAO.checkRecordBelongsToUserOrNot(userId,imei1,imei2);
    		if(count.longValue() > 0){
    			tabLogInAuthDAO.updateRecordBelongsToUserOrNot(userId,imei1,imei2);
    			TabLogInAuth tabLogInAuth = new TabLogInAuth();
        		tabLogInAuth.setCadreSurveyUserId(userId);
        		tabLogInAuth.setImeiNo(imei1);
        		tabLogInAuth.setImeiNo2(imei2);
        		tabLogInAuth.setInsertedTime(new DateUtilService().getCurrentDateAndTime());
        		tabLogInAuth.setVersion(version);
        		tabLogInAuth.setIsDeleted("N");
        		tabLogInAuth.setStatus("success");
        		tabLogInAuthDAO.save(tabLogInAuth);
        		vo.setStatus("logged");
        		return vo;
    		}else{
    			return getActualCaseForNotAllowingToLogIn(userId,imei1,imei2,version,vo);
    		}
    	}
    	
    }
    
    public String getTabUsersRecordsDetails(List<TabRecordsStatusVO> inputVoList)
	{
		return  cadreRegistrationService.updateTabUserDetails(inputVoList);
		
	}
	
	public String getTabUsersLoginDetails(TabRecordsStatusVO inputVo)
	{
		return  cadreRegistrationService.updateTabLoginUserDetails(inputVo);
		
	}
	
    private LoginResponceVO getActualCaseForNotAllowingToLogIn(Long userId,String imei1,String imei2,String version,LoginResponceVO vo){
    	
    	TabLogInAuth tabLogInAuth = new TabLogInAuth();
		tabLogInAuth.setCadreSurveyUserId(userId);
		tabLogInAuth.setImeiNo(imei1);
		tabLogInAuth.setImeiNo2(imei2);
		tabLogInAuth.setInsertedTime(new DateUtilService().getCurrentDateAndTime());
		tabLogInAuth.setVersion(version);
		tabLogInAuth.setIsDeleted("Y");
		
    	Long count = tabLogInAuthDAO.checkUserAlreadyLoggedInAnotherTab(userId, imei1,imei2);
    	
    	if(count.longValue() > 0){
    		tabLogInAuth.setStatus("same user");
    		tabLogInAuthDAO.save(tabLogInAuth);
    		vo.setStatus("same user");
    		return vo;
    	}else{
    		tabLogInAuth.setStatus("same tab");
    		tabLogInAuthDAO.save(tabLogInAuth);
    		vo.setStatus("same tab");
    		return vo;
    	}
    }
    
    public void prepareResponceNew(Long userId,LoginResponceVO loginResponceVO)
	{
		try {
			LOG.debug("Entered into the prepareResponceNew  method in WebServiceHandlerService");
			List<CadreSurveyUserAssignDetails> resultList = cadreSurveyUserAssignDetailsDAO.getCadreAssinedDetails(userId);
			if(resultList != null && resultList.size() > 0)
			{
				loginResponceVO.setStatusMsg("DBINITIALCHECK");
				loginResponceVO.setConstituencyId(resultList.get(0).getConstituencyId());
				loginResponceVO.setConstituencyName(resultList.get(0).getConstituency() != null ? resultList.get(0).getConstituency().getName() : null);
				loginResponceVO.setUserId(userId);
				loginResponceVO.setTabNo(resultList.get(0).getTabNo());
				List<LoginStatusVO> subList = new ArrayList<LoginStatusVO>();
				Map<Long, List<Long>> subListMap = new HashMap<Long, List<Long>>();
				loginResponceVO.setAcNo(delimitationConstituencyDAO.getConstituencyNo(loginResponceVO.getConstituencyId(),2009l));
				for (CadreSurveyUserAssignDetails cadreSurveyUserAssignDetails : resultList) 
				{
					
					List<Long> ids = subListMap.get(cadreSurveyUserAssignDetails.getLevelId());
					if(ids == null)
					{
						ids = new ArrayList<Long>();
						subListMap.put(cadreSurveyUserAssignDetails.getLevelId(), ids);
					}
					ids.add(cadreSurveyUserAssignDetails.getLevelValue());
					
				}
				if(subListMap != null && subListMap.size() > 0)
				{
					for(Long statusId : subListMap.keySet())
					{
						LoginStatusVO loginStatusVO = new LoginStatusVO();
						loginStatusVO.setStatusId(statusId);
						loginStatusVO.setStatusRelatedValues(subListMap.get(statusId));
						subList.add(loginStatusVO);
					}
					loginResponceVO.setStatusList(subList);
				}
				
			}
		} catch (Exception e) {
			LOG.error("Exception raised in prepareResponceNew  method in WebServiceHandlerService",e);
		}
	}
    
    public List<VoterWebServiceDataVO> voterSearchDetails(Long constituencyId,String candidateName,String voterCardId,Integer startIndex){
    	List<VoterWebServiceDataVO> returnList = new ArrayList<VoterWebServiceDataVO>();
    	List<VoterInfoVO> votersList = cadreRegistrationService.getSearchDetailsCadreRegistration(constituencyId,"voter", candidateName, voterCardId,null, 0l, 0l, null, startIndex, 30);
    	if(votersList != null){
	    	for(VoterInfoVO voter:votersList){
	    		VoterWebServiceDataVO voterVo = new VoterWebServiceDataVO();
	    		voterVo.setVoterCardNo(voter.getVoterCardNo());
	    		voterVo.setName(voter.getName());
	    		voterVo.setRelativeName(voter.getRelativeName());
	    		voterVo.setRelation(voter.getRelationType());
	    		voterVo.setAge(voter.getAge());
	    		voterVo.setDoorNo(voter.getHouseNo());
	    		if(voter.getCount() != null){
	    		  voterVo.setPageNo(voter.getCount().intValue());
	    		}
	    		returnList.add(voterVo);
	    	}
    	}
    	return returnList;
    }
    
    /**
     * THIS SERVICE IS USED FOR SINKING MISSING DATA FROM TAB
     * @param inputs
     * @return returnVO
     */
    public List<SinkVO> sinkMissingData(List<SinkVO> inputs)
    {
    	List<SinkVO> returnList = null;
    	try 
    	{
    		returnList = cadreRegistrationService.sinkMissingData(inputs);
		} 
    	catch (Exception e)
    	{
    		returnList = null;
			LOG.error("Exception raised in sinkPendingData  method in WebServiceHandlerService",e);
		}
    	return returnList;
    }
    public TdpCadreImageSinkData SaveImageSinkData(CadreImageVO cadreImageVO)
    {
    	TdpCadreImageSinkData tdpCadreImageSinkData = new TdpCadreImageSinkData() ;
    try{
    	
    	TdpCadre tdpCadre = tdpCadreDAO.get(cadreImageVO.getTdpCadreId());
		String membershipId = tdpCadre.getMemberShipNo();
		tdpCadreImageSinkData = new TdpCadreImageSinkData();
		tdpCadreImageSinkData.setUuid(cadreImageVO.getUuid());
		if(membershipId != null && membershipId.length() > 0)
		{
			String pathSeparator = System.getProperty(IConstants.FILE_SEPARATOR);
			boolean flag = imageAndStringConverter.convertBase64StringToImage(cadreImageVO.getImageStr(),
					IConstants.STATIC_CONTENT_FOLDER_URL+"images"+pathSeparator+"cadre_images"+pathSeparator+membershipId+".jpg");
			if(flag)
			{
				tdpCadreImageSinkData.setImageStr(membershipId+".jpg");
			}
		}
		tdpCadreImageSinkData.setImeiNo(cadreImageVO.getImeiNo());
		tdpCadreImageSinkData.setTdpCadreId(cadreImageVO.getTdpCadreId());
		tdpCadreImageSinkData.setUserId(cadreImageVO.getUserId());
		tdpCadreImageSinkData.setStatus("Success");
		tdpCadreImageSinkData = tdpCadreImageSinkDataDAO.save(tdpCadreImageSinkData);
		
		
    }
    catch(Exception e)
    {
    	
    }

	return tdpCadreImageSinkData;
	
    }
    public CadreImageVO sinkImageMissingData(CadreImageVO cadreImageVO)
    {
    	CadreImageVO result = new CadreImageVO();
    	TdpCadreImageSinkData tdpCadreImageSinkData = null;
    	try{
    		tdpCadreImageSinkData = SaveImageSinkData(cadreImageVO);
    		TdpCadre tdpCadre = tdpCadreDAO.get(cadreImageVO.getTdpCadreId());
    		result.setTdpCadreId(cadreImageVO.getTdpCadreId());
    		String membershipId = tdpCadre.getMemberShipNo();
    		if(membershipId != null && membershipId.length() > 0)
    		{
    			String pathSeparator = System.getProperty(IConstants.FILE_SEPARATOR);
    			boolean flag = imageAndStringConverter.convertBase64StringToImage(cadreImageVO.getImageStr(),
    					IConstants.STATIC_CONTENT_FOLDER_URL+"images"+pathSeparator+"cadre_images"+pathSeparator+membershipId+".jpg");
    			if(flag)
    			{
    				tdpCadre.setImage(membershipId+".jpg");
    				tdpCadre.setUpdatedUserId(cadreImageVO.getUserId());
    				tdpCadre.setUpdatedTime(new DateUtilService().getCurrentDateAndTime());
    				tdpCadreDAO.save(tdpCadre);
    				result.setStatus("SUCCESS");
    				tdpCadreImageSinkData.setStatus("SUCCESS");
    			}
    			else
    			{
    				result.setStatus("FAILURE");
    			tdpCadreImageSinkData.setStatus("FAILURE");
    			}
    		}
    		else
    			result.setStatus("FAILURE");
    	}catch(Exception e)
    	{
    		LOG.error("Exception raised in sinkImageMissingData() method",e);
    		result.setStatus("FAILURE");
    		tdpCadreImageSinkData.setStatus("FAILURE");
    		tdpCadreImageSinkData.setExceptionStatus(e.toString());
    	}
    	tdpCadreImageSinkDataDAO.save(tdpCadreImageSinkData);
    	return result;
    }
    
    public String getTabUsersLoginKeyDetails(TabRecordsStatusVO inputVo)
	{
		return  cadreRegistrationService.saveTabUsersLoginKeyDetails(inputVo);
		
	}
    
    /**
     * THIS SERVICE IS USED FOR Search TdpCadreDetails By SearchCriteria
     * @param tdpCadreVO
     * @return TdpCadreVO
     */
   
    public TdpCadreVO searchTdpCadreDetailsBySearchCriteria(String constituencyId,String name,String memberShipCardNo, String voterCardNo, String refNo, String mobileNo)
    {
    	TdpCadreVO returnVO = new TdpCadreVO(); // TdpCadreVO
    	try {
    		//returnVO = cadreRegistrationService.searchTdpCadreDetailsBySearchCriteria(Long.valueOf(constituencyId),name,memberShipCardNo, voterCardNo, refNo, mobileNo);
    		 // returnVO = cadreDetailsService.searchTdpCadreDetailsBySearchCriteriaForCommitte(0L,Long.valueOf(constituencyId),name,memberShipCardNo, voterCardNo, refNo, mobileNo,0L,"",null,null,null, null,0,0,false,3l);
    		returnVO = cadreDetailsService.searchTdpCadreDetailsBySearchCriteriaForCommitteForWebServiceCalls(0L,Long.valueOf(constituencyId),name,memberShipCardNo, voterCardNo, refNo, mobileNo,0L,"",null,null,null, null,0,0,false,0L);// search in all enrollment years 2011,2012,2014,2016
		} catch (Exception e) {
			LOG.error("Exception raised in searchTdpCadreDetailsBySearchCriteria  method in WebServiceHandlerService",e);
		}
    	
    	return returnVO;
    }
    
    public String getDynamicValueOfAKey()
	{

		return commonUtilsService.getDynamicValueOfAKey(IConstants.RESTRICT_TAB_DATA_SYNC);

	}
    public boolean checkHasAccess(Long userId){
    	return cadreRegistrationService.checkHasAccess(userId);
    }
    /**
     * THIS SERVICE IS USED FOR Search TdpCadreDetails By VoterIDCardNo
     * @param tdpCadreVO
     * @return TdpCadreVO
     */
   
    public TdpCadreVO searchTdpCadreDetailsBySVoterIdCardNo(String voterCardNo, String isFamilyVoter)
    {
    	TdpCadreVO returnVO = new TdpCadreVO(); // TdpCadreVO
    	try {
    		returnVO = cadreRegistrationService.searchTdpCadreDetailsByVoterCardNo(voterCardNo, isFamilyVoter);
		} catch (Exception e) {
			LOG.error("Exception raised in searchTdpCadreDetailsBySVoterIdCardNo  method in WebServiceHandlerService",e);
		}
    	
    	return returnVO;
    }
    public String saveStatus(CadreRegistrationVO input)
	{
		LOG.debug("Entered into the saveStatus  method in WebServiceHandlerService");
		try
		{
			cadreRegistrationService.saveRegistrationStatus(input);
		
		}catch(Exception e)
		{
			LOG.error("Exception raised in saveStatus  method in WebServiceHandlerService",e);
			
			return "failure";
		}
		return "success";
	}
    
    public String saveMissedCallDetails(MissedCallCampaignVO input)
   	{
   		LOG.debug("Entered into the saveMissedCallDetails  method in WebServiceHandlerService");
   		try
   		{
   			cadreRegistrationService.saveMissedCallDetails(input);
   		
   		}catch(Exception e)
   		{
   			LOG.error("Exception raised in saveMissedCallDetails  method in WebServiceHandlerService",e);
   			
   			return "failure";
   		}
   		return "success";
   	}
    
    public Object saveSurveyFieldUsersForAffliatedCadre(List<CadreRegistrationVO> inputResponseList){
		SurveyCadreResponceVO  surveyCadreResponceVO = null;
		if(inputResponseList != null && inputResponseList.size() > 0)
		{
			for (CadreRegistrationVO inputResponse : inputResponseList){
				//setting address to corresponding fields.
				if(inputResponse.getDistrictId()!=null && inputResponse.getDistrictId().trim().length()>0){
					inputResponse.setPerAddrsDistId(Long.valueOf(inputResponse.getDistrictId()));
				}
				if(inputResponse.getConstituencyId()!=null && inputResponse.getConstituencyId().trim().length()>0){
					inputResponse.setPerAddrsConstId((Long.valueOf(inputResponse.getConstituencyId())));
				}
				if(inputResponse.getMandalId()!=null && inputResponse.getMandalId().trim().length()>0){
					inputResponse.setPerAddrsMandalId( Long.valueOf(inputResponse.getMandalId()) );
				}
				if(inputResponse.getMuncipalityId()!=null && inputResponse.getMuncipalityId().trim().length()>0){
					inputResponse.setPerAddrsLebId( Long.valueOf(inputResponse.getMuncipalityId()) );
				}
				if(inputResponse.getPanchayatId()!=null && inputResponse.getPanchayatId().trim().length()>0){
					inputResponse.setPerAddrsVillId( Long.valueOf(inputResponse.getPanchayatId()) );
				}
				if(inputResponse.getWardId()!=null && inputResponse.getWardId().trim().length()>0){
					inputResponse.setPerAddrsWardId( Long.valueOf(inputResponse.getWardId()) );
				}
			}
			
			if(IConstants.ENABLE_CADRE_LOGS){
				for (CadreRegistrationVO inputResponse : inputResponseList)
				{
					LOG.error(inputResponse.toString());
					
					LOG.error("Voter Name  " + "-" + inputResponse.getVoterName() + "-" 
					+ "Date Of Birth "+  "-" + inputResponse.getDob() +"-"
					+ "Gender " +inputResponse.getGender()+  "-" 
					+ "Relative Name"+ inputResponse.getRelativeName() +"-" 
					+ "VoterCardNumber"+  inputResponse.getVoterCardNo() + "-" 
					+ "H.NO" + inputResponse.getHouseNo() + "-" 
					//+ "Party Member Since" +inputResponse.getPartyMemberSince()  + "-" 
					+ "Blood Group " + inputResponse.getBloodGroupId() + "-" 
					+ "Street/hamle" +inputResponse.getStreet() +"-" 
					+ "Caste" + inputResponse.getCasteId() + "-" 
					+ "Mobile No" + inputResponse.getMobileNumber() + "-" 
					+ "Education" +inputResponse.getEducationId() + "-" 
					+ "Designation" + inputResponse.getDesignationId() + "-"
					+ "UserMode" + inputResponse.getMode() + "-"
					+ "Depot Id" + inputResponse.getDepotId() + "-"
					+ "Region Id" + inputResponse.getRegionId() + "-"
					+ "Zone Id" + inputResponse.getZoneId() + "-"
					//+ "Occupation " +inputResponse.getOccupationId() + "-" 
					//+ "Previous Enroll Ment No " + inputResponse.getPreviousEnrollmentNumber()
					);
				}
			}
			
				surveyCadreResponceVO=cadreRegistrationService.saveAfflicatedCadreRegistration(inputResponseList,"TAB");	
		}
		
		return surveyCadreResponceVO;
		
	}
    public Object getMemberDetailsByMembershipId(String membershipId){
    	try{
    		List<Object[]> memberDate = null;
    		if(membershipId != null && membershipId.trim().length() > 0){
    			if(membershipId.trim().length() > 7){
    				memberDate = tdpCadreDAO.getMemberDetailsByMembershipId(membershipId);
    			}else{
    				membershipId = "0"+membershipId;
    				memberDate = tdpCadreDAO.getMemberDetailsByMembershipId(membershipId);
    			}
    		}
    		CadreInfo cadreInfo = new CadreInfo();
    		Set<Long> yearIdSet = new HashSet<Long>();
    		Set<String> desigSet = new HashSet<String>();
    		if(memberDate != null && memberDate.size() > 0){
    			cadreInfo.setCadreID(commonMethodsUtilService.getLongValueForObject(memberDate.get(0)[0]));
    			cadreInfo.setMemberShipNo(commonMethodsUtilService.getStringValueForObject(memberDate.get(0)[1]));
    			cadreInfo.setFirstName(commonMethodsUtilService.getStringValueForObject(memberDate.get(0)[2]));
    			cadreInfo.setMobile(commonMethodsUtilService.getStringValueForObject(memberDate.get(0)[4]));
    			cadreInfo.setImage(commonMethodsUtilService.getStringValueForObject(memberDate.get(0)[5]));
    			cadreInfo.setDistrictName(commonMethodsUtilService.getStringValueForObject(memberDate.get(0)[6]));
    			cadreInfo.setConstituencyName(commonMethodsUtilService.getStringValueForObject(memberDate.get(0)[7]));
    			cadreInfo.setMandalName(commonMethodsUtilService.getStringValueForObject(memberDate.get(0)[8]));
    			cadreInfo.setVillage(commonMethodsUtilService.getStringValueForObject(memberDate.get(0)[9]));
    			cadreInfo.setLocalElectionBody(commonMethodsUtilService.getStringValueForObject(memberDate.get(0)[10]));
    			cadreInfo.setWard(commonMethodsUtilService.getStringValueForObject(memberDate.get(0)[11]));
    			for(Object[] param : memberDate){
    				yearIdSet.add(commonMethodsUtilService.getLongValueForObject(param[12]));
    				desigSet.add(commonMethodsUtilService.getStringValueForObject(param[3]).trim());
    			}
    		}
    		if(yearIdSet.contains(4L)){
    			cadreInfo.setIsValid("valid");
    		}else{
    			cadreInfo.setIsValid("inValid");
    		}
    		if(desigSet != null && desigSet.size() > 0){
    			cadreInfo.setDesignationSet(desigSet);
    		}
    		return cadreInfo;
    	}catch(Exception e){
    		LOG.error("Exception raised in saveMissedCallDetails  method in WebServiceHandlerService1",e);
   			
   			return "failure";
    	}
    }
    public NewCadreRegistrationVO getRegistrationPersonDetails(String votercardNo,Long familyVoterId,Long tdpCadreId,String status){
    	NewCadreRegistrationVO cadreRegVo=null;
    	try{
    		List<Long> voterIds = voterDAO.getVoterIdByVoterIDCardNumber(votercardNo);
    		if(voterIds != null && voterIds.size() > 0){
    			cadreRegVo = coreDashboardCadreRegistrationService.getRegistrationPersonDetails(voterIds.get(0),familyVoterId,tdpCadreId,status);
    		}
    	}catch(Exception e){
    		LOG.error("Exception raised in getRegistrationPersonDetails  method in WebServiceHandlerService1",e);
    	}
    	return cadreRegVo;
    }
}

