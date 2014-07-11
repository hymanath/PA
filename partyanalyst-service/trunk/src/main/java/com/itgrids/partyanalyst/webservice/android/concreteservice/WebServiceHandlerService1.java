package com.itgrids.partyanalyst.webservice.android.concreteservice;
       
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
import com.itgrids.partyanalyst.dao.IMobileAppPingingDAO;
import com.itgrids.partyanalyst.dao.IMobileAppUserAccessKeyDAO;
import com.itgrids.partyanalyst.dao.IMobileAppUserDAO;
import com.itgrids.partyanalyst.dao.IMobileAppUserProfileDAO;
import com.itgrids.partyanalyst.dao.IPingingTypeDAO;
import com.itgrids.partyanalyst.dao.ISurveyDetailsInfoDAO;
import com.itgrids.partyanalyst.dao.ISurveyUserBoothAssignDAO;
import com.itgrids.partyanalyst.dao.IUserDAO;
import com.itgrids.partyanalyst.dao.IUserSurveyBoothsDAO;
import com.itgrids.partyanalyst.dao.IUserVoterDetailsDAO;
import com.itgrids.partyanalyst.dao.IVoiceRecordingDetailsDAO;
import com.itgrids.partyanalyst.dao.IVoterBoothActivitiesDAO;
import com.itgrids.partyanalyst.dao.IVoterTagDAO;
import com.itgrids.partyanalyst.dao.IWebServiceBaseUrlDAO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SurveyResponceVO;
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
	public  WebServiceHandlerService1() {
	System.out.println("got instanciated===========");	

	}
	private static final Logger log = Logger.getLogger(WebServiceHandlerService1.class);

	private static final Long hamletId = null;
	
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
	@Autowired private IBoothPublicationVoterDAO boothPublicationVoterDAO;
	private IUserVoterDetailsDAO userVoterDetailsDAO;
	@Autowired private IBoothDAO boothDAO;
	
	private IInfluencingPeopleService influencingPeopleService;
	private IVoterReportService voterReportService;
	private IVoterTagDAO voterTagDAO;
	private IVoterBoothActivitiesDAO voterBoothActivitiesDAO;
	private DateUtilService dateUtilService = new DateUtilService();
	
	@Autowired IUserDAO userDAO;
	@Autowired IStrategyModelTargetingService strategyModelTargetingService;
    @Autowired   IUserSurveyBoothsDAO userSurveyBoothsDAO ;
    @Autowired public ISurveyDataDetailsService surveyDataDetailsService;
    
    @Autowired
	 public ISurveyUserBoothAssignDAO surveyUserBoothAssignDAO; 
    
    @Autowired
    public ISurveyDetailsInfoDAO  surveyDetailsInfoDAO;
    
    
	@Autowired
	private TransactionTemplate transactionTemplate;
	
	
	
    
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
     @Override
	public UserResponseVO checkForUserAuthentication(UserLoginVO inputvo)
	{/*
		StringBuilder buffer= new StringBuilder();
		
		buffer.append("{\"statusCode:\"");*/
		Object[] userId=null;
		UserResponseVO res=null;
		log.debug("Entered into the checkForUserAuthentication  method in WebServiceHandlerService");
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
			log.error("Exception raised in checkForUserAuthentication  method in WebServiceHandlerService");
			e.printStackTrace();
			
		}
		return res;
	}

	
	
	
	public UserResponseVO  buildDateForSurverUsers(Object[] inputs, UserLoginVO inputvo)
	{
		
		UserResponseVO res=null;
		
		long userId=(Long)inputs[0];
		int userTypeId=((Long)inputs[1]).intValue();

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
		case 3:
			
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
	public UserResponseVO buildCollectorData(long userId,long userTypeId)
	{
		//check data already available fo user
	          //need to confirm
		
		//get booths for collector
		List<Object[]> booths =(List<Object[]>) surveyUserBoothAssignDAO.getBoothsForUser(userId);
		
		
		List<Long> remainingDatBoothIds = new ArrayList<Long>();
		
		for(Object[] obj:booths)
		{
			if(obj[3].toString().equalsIgnoreCase("Y"))
				remainingDatBoothIds.add((Long)obj[0]);
		}
		
		  List<Long> voterIds=null;
		if(remainingDatBoothIds!=null && remainingDatBoothIds.size()>0)
         voterIds =   boothPublicationVoterDAO.getAllVoterIdsByBoothIdsAndPublicationDateId(remainingDatBoothIds,IConstants.VOTER_DATA_PUBLICATION_ID);
		if(voterIds!=null && voterIds.size()>0){
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
	
	
}

