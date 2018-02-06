package com.itgrids.partyanalyst.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;
import org.jfree.util.Log;
import org.springframework.beans.factory.annotation.Autowired;

import com.itgrids.core.api.service.ILocationDashboardService;
import com.itgrids.partyanalyst.dao.IActivityTabRequestBackupDAO;
import com.itgrids.partyanalyst.dao.IAppointmentTimeSlotDAO;
import com.itgrids.partyanalyst.dao.IAssemblyLocalElectionBodyDAO;
import com.itgrids.partyanalyst.dao.IBoothDAO;
import com.itgrids.partyanalyst.dao.IBoothPublicationVoterDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyMandalDetailsDAO;
import com.itgrids.partyanalyst.dao.IEventAttendeeDAO;
import com.itgrids.partyanalyst.dao.IEventAttendeeErrorDAO;
import com.itgrids.partyanalyst.dao.IEventDAO;
import com.itgrids.partyanalyst.dao.IEventInviteeDAO;
import com.itgrids.partyanalyst.dao.IEventRfidDetailsDAO;
import com.itgrids.partyanalyst.dao.IEventSurveyUserDAO;
import com.itgrids.partyanalyst.dao.IEventSurveyUserLoginDetailsDAO;
import com.itgrids.partyanalyst.dao.IEventUserDAO;
import com.itgrids.partyanalyst.dao.IMessagingPropsDetailsDAO;
import com.itgrids.partyanalyst.dao.IMobileAppPingingDAO;
import com.itgrids.partyanalyst.dao.IMobileAppUserAccessKeyDAO;
import com.itgrids.partyanalyst.dao.IMobileAppUserAccessLocationDAO;
import com.itgrids.partyanalyst.dao.IMobileAppUserDAO;
import com.itgrids.partyanalyst.dao.IMobileAppUserProfileDAO;
import com.itgrids.partyanalyst.dao.IMobileAppUserSmsDetailsDAO;
import com.itgrids.partyanalyst.dao.IMobileAppUserSmsStatusDAO;
import com.itgrids.partyanalyst.dao.IMobileAppUserVoterDAO;
import com.itgrids.partyanalyst.dao.IPanchayatDAO;
import com.itgrids.partyanalyst.dao.IPingingTypeDAO;
import com.itgrids.partyanalyst.dao.ISurveyUserAuthDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreDAO;
import com.itgrids.partyanalyst.dao.ITdpMemberUnionTabUserRelationDAO;
import com.itgrids.partyanalyst.dao.IUnionTabUserDAO;
import com.itgrids.partyanalyst.dao.IUnionTypeDesignationDAO;
import com.itgrids.partyanalyst.dao.IUserDAO;
import com.itgrids.partyanalyst.dao.IUserSurveyBoothsDAO;
import com.itgrids.partyanalyst.dao.IUserVoterDetailsDAO;
import com.itgrids.partyanalyst.dao.IVoiceRecordingDetailsDAO;
import com.itgrids.partyanalyst.dao.IVoterBoothActivitiesDAO;
import com.itgrids.partyanalyst.dao.IVoterDAO;
import com.itgrids.partyanalyst.dao.IVoterTagDAO;
import com.itgrids.partyanalyst.dao.IWebServiceBaseUrlDAO;
import com.itgrids.partyanalyst.dao.IitdpAppUserDAO;
import com.itgrids.partyanalyst.dto.AccessLocationVO;
import com.itgrids.partyanalyst.dto.AccommodationVO;
import com.itgrids.partyanalyst.dto.ActionableVO;
import com.itgrids.partyanalyst.dto.ActivityAttendanceVO;
import com.itgrids.partyanalyst.dto.ActivityDetailsVO;
import com.itgrids.partyanalyst.dto.ActivityLoginVO;
import com.itgrids.partyanalyst.dto.ActivityWSVO;
import com.itgrids.partyanalyst.dto.AffiliatedMemberVO;
import com.itgrids.partyanalyst.dto.AlertCommentVO;
import com.itgrids.partyanalyst.dto.AlertCoreDashBoardVO;
import com.itgrids.partyanalyst.dto.AlertDataVO;
import com.itgrids.partyanalyst.dto.AlertOverviewVO;
import com.itgrids.partyanalyst.dto.AlertVO;
import com.itgrids.partyanalyst.dto.AlertVerificationVO;
import com.itgrids.partyanalyst.dto.AmsAppLoginVO;
import com.itgrids.partyanalyst.dto.AmsAppVO;
import com.itgrids.partyanalyst.dto.AmsDataVO;
import com.itgrids.partyanalyst.dto.AmsKeyValueVO;
import com.itgrids.partyanalyst.dto.AmsTrackingVO;
import com.itgrids.partyanalyst.dto.AmsVO;
import com.itgrids.partyanalyst.dto.AppointmentCountDetailsVO;
import com.itgrids.partyanalyst.dto.AttendanceQuestionnariWSVO;
import com.itgrids.partyanalyst.dto.BasicVO;
import com.itgrids.partyanalyst.dto.CadreAddressVO;
import com.itgrids.partyanalyst.dto.CadreCommitteeMemberVO;
import com.itgrids.partyanalyst.dto.CadreInfo;
import com.itgrids.partyanalyst.dto.CadreOverviewVO;
import com.itgrids.partyanalyst.dto.CadrePrintInputVO;
import com.itgrids.partyanalyst.dto.CadrePrintVO;
import com.itgrids.partyanalyst.dto.CadreTabRecordsStatusVO;
import com.itgrids.partyanalyst.dto.CadreTravelsVO;
import com.itgrids.partyanalyst.dto.CadreVoterVO;
import com.itgrids.partyanalyst.dto.CardNFCDetailsVO;
import com.itgrids.partyanalyst.dto.CardPrintUserVO;
import com.itgrids.partyanalyst.dto.CasteDetailsVO;
import com.itgrids.partyanalyst.dto.CommitteeBasicVO;
import com.itgrids.partyanalyst.dto.DistrictOfficeViewAlertVO;
import com.itgrids.partyanalyst.dto.EffectedBoothsResponse;
import com.itgrids.partyanalyst.dto.EventFileUploadVO;
import com.itgrids.partyanalyst.dto.FlagVO;
import com.itgrids.partyanalyst.dto.GISIssuesVO;
import com.itgrids.partyanalyst.dto.GISUserTrackingVO;
import com.itgrids.partyanalyst.dto.GISVisualizationDetailsVO;
import com.itgrids.partyanalyst.dto.GISVisualizationParameterVO;
import com.itgrids.partyanalyst.dto.GrievanceAlertVO;
import com.itgrids.partyanalyst.dto.IdAndNameVO;
import com.itgrids.partyanalyst.dto.IdNameVO;
import com.itgrids.partyanalyst.dto.ImageVO;
import com.itgrids.partyanalyst.dto.InviteesVO;
import com.itgrids.partyanalyst.dto.JalavaniVO;
import com.itgrids.partyanalyst.dto.KeyValueVO;
import com.itgrids.partyanalyst.dto.LoginResponceVO;
import com.itgrids.partyanalyst.dto.MessagePropertyVO;
import com.itgrids.partyanalyst.dto.MobileAppUserSmsStatusVO;
import com.itgrids.partyanalyst.dto.MobileAppUserVO;
import com.itgrids.partyanalyst.dto.MobileAppUserVoterVO;
import com.itgrids.partyanalyst.dto.MomDashbaordOverViewDtlsVO;
import com.itgrids.partyanalyst.dto.MomDetailsVO;
import com.itgrids.partyanalyst.dto.NotificationDeviceVO;
import com.itgrids.partyanalyst.dto.NtrTrustStudentVO;
import com.itgrids.partyanalyst.dto.PanchayatCountVo;
import com.itgrids.partyanalyst.dto.PartyMeetingMOMCreationDtlsvO;
import com.itgrids.partyanalyst.dto.PartyMeetingMOMDtlsVO;
import com.itgrids.partyanalyst.dto.PartyMeetingMOMPointsDtlsVO;
import com.itgrids.partyanalyst.dto.PartyMeetingVO;
import com.itgrids.partyanalyst.dto.PartyMeetingWSVO;
import com.itgrids.partyanalyst.dto.PashiAppNoCadreVO;
import com.itgrids.partyanalyst.dto.PeshiAppAppointmentVO;
import com.itgrids.partyanalyst.dto.PeshiAppGrievanceVO;
import com.itgrids.partyanalyst.dto.PeshiAppLoginVO;
import com.itgrids.partyanalyst.dto.PollManagementVO;
import com.itgrids.partyanalyst.dto.QuestionAnswerVO;
import com.itgrids.partyanalyst.dto.RegisteredMembershipCountVO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SurveyTrainingsVO;
import com.itgrids.partyanalyst.dto.TabDetailsVO;
import com.itgrids.partyanalyst.dto.TdpCadreVO;
import com.itgrids.partyanalyst.dto.TdpCadreWSVO;
import com.itgrids.partyanalyst.dto.UnionTabUserVO;
import com.itgrids.partyanalyst.dto.UserDetailsVO;
import com.itgrids.partyanalyst.dto.UserEventDetailsVO;
import com.itgrids.partyanalyst.dto.VerifierVO;
import com.itgrids.partyanalyst.dto.VoterDetailsVO;
import com.itgrids.partyanalyst.dto.WSResultVO;
import com.itgrids.partyanalyst.dto.WebServiceBaseVO;
import com.itgrids.partyanalyst.dto.WebServiceResultVO;
import com.itgrids.partyanalyst.model.ActivityTabRequestBackup;
import com.itgrids.partyanalyst.model.Booth;
import com.itgrids.partyanalyst.model.Event;
import com.itgrids.partyanalyst.model.EventAttendee;
import com.itgrids.partyanalyst.model.EventAttendeeError;
import com.itgrids.partyanalyst.model.EventSurveyUser;
import com.itgrids.partyanalyst.model.EventSurveyUserLoginDetails;
import com.itgrids.partyanalyst.model.ITdpAppUser;
import com.itgrids.partyanalyst.model.MessagingPropsDetails;
import com.itgrids.partyanalyst.model.MobileAppPinging;
import com.itgrids.partyanalyst.model.MobileAppUser;
import com.itgrids.partyanalyst.model.MobileAppUserAccessKey;
import com.itgrids.partyanalyst.model.MobileAppUserAccessLocation;
import com.itgrids.partyanalyst.model.MobileAppUserSmsDetails;
import com.itgrids.partyanalyst.model.MobileAppUserSmsStatus;
import com.itgrids.partyanalyst.model.MobileAppUserVoter;
import com.itgrids.partyanalyst.model.SurveyUserAuth;
import com.itgrids.partyanalyst.model.UnionTabUser;
import com.itgrids.partyanalyst.model.User;
import com.itgrids.partyanalyst.model.UserVoterDetails;
import com.itgrids.partyanalyst.model.VoterBoothActivities;
import com.itgrids.partyanalyst.model.VoterTag;
import com.itgrids.partyanalyst.model.WebServiceBaseUrl;
import com.itgrids.partyanalyst.security.PBKDF2;
import com.itgrids.partyanalyst.service.IActivityService;
import com.itgrids.partyanalyst.service.IAffiliatedMember;
import com.itgrids.partyanalyst.service.IAlertManagementSystemService;
import com.itgrids.partyanalyst.service.IAlertService;
import com.itgrids.partyanalyst.service.IAlertsNewsPortalService;
import com.itgrids.partyanalyst.service.IAttendanceService;
import com.itgrids.partyanalyst.service.ICadreCommitteeService;
import com.itgrids.partyanalyst.service.ICadreDetailsService;
import com.itgrids.partyanalyst.service.ICadreRegistrationService;
import com.itgrids.partyanalyst.service.ICccDashboardService;
import com.itgrids.partyanalyst.service.IFieldMonitoringService;
import com.itgrids.partyanalyst.service.IGISVisualizationService;
import com.itgrids.partyanalyst.service.IInfluencingPeopleService;
import com.itgrids.partyanalyst.service.IKaizalaInfoService;
import com.itgrids.partyanalyst.service.ILoginService;
import com.itgrids.partyanalyst.service.IMahaNaduService;
import com.itgrids.partyanalyst.service.IMailService;
import com.itgrids.partyanalyst.service.IMobileService;
import com.itgrids.partyanalyst.service.INotificationService;
import com.itgrids.partyanalyst.service.IPartyMeetingMOMService;
import com.itgrids.partyanalyst.service.IPartyMeetingService;
import com.itgrids.partyanalyst.service.ISmsGatewayService;
import com.itgrids.partyanalyst.service.ISmsService;
import com.itgrids.partyanalyst.service.IStrategyModelTargetingService;
import com.itgrids.partyanalyst.service.ITrainingCampService;
import com.itgrids.partyanalyst.service.IVoiceSmsService;
import com.itgrids.partyanalyst.service.IVoterReportService;
import com.itgrids.partyanalyst.service.IWebServiceHandlerService;
import com.itgrids.partyanalyst.utils.CommonMethodsUtilService;
import com.itgrids.partyanalyst.utils.DateUtilService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.itgrids.partyanalyst.utils.Util;
import com.itgrids.partyanalyst.webservice.utils.VoterTagVO;
import com.itgrids.partyanalyst.webserviceutils.android.utilvos.UserLocationTrackingVo;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;


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
    private IEventRfidDetailsDAO eventRfidDetailsDAO;
    private ISurveyUserAuthDAO surveyUserAuthDAO;
    private IMessagingPropsDetailsDAO messagingPropsDetailsDAO;
    private IEventAttendeeErrorDAO eventAttendeeErrorDAO;
    private IEventDAO eventDAO;
    private IPartyMeetingService partyMeetingService;
    private IMahaNaduService mahaNaduService;
    private ITrainingCampService trainingCampService;
    private ICadreCommitteeService cadreCommitteeService;
    private IEventInviteeDAO eventInviteeDAO;
    private IActivityService							activityService;
    private IAttendanceService attendanceService;
    private IActivityTabRequestBackupDAO activityTabRequestBackupDAO;
    private IMobileAppUserSmsDetailsDAO mobileAppUserSmsDetailsDAO;
    private IMobileAppUserVoterDAO mobileAppUserVoterDAO;
    
    private IMobileAppUserSmsStatusDAO mobileAppUserSmsStatusDAO;
    
    private IMobileAppUserAccessLocationDAO mobileAppUserAccessLocationDAO;
    private ISmsGatewayService smsGatewayService;
    private ILocationDashboardService locationDashboardService;
    
    
    private IUnionTabUserDAO unionTabUserDAO;
    private ITdpMemberUnionTabUserRelationDAO tdpMemberUnionTabUserRelationDAO;
    private CommonMethodsUtilService commonMethodsUtilService = new CommonMethodsUtilService();
    private IUnionTypeDesignationDAO unionTypeDesignationDAO;
    private INotificationService notificationService;
    private IGISVisualizationService gisVisualizationService;
    private IConstituencyDAO constituencyDAO;            
    private IDelimitationConstituencyMandalDetailsDAO delimitationConstituencyMandalDetailsDAO; 
    private IAssemblyLocalElectionBodyDAO assemblyLocalElectionBodyDAO;
    private IPanchayatDAO panchayatDAO;
    private IFieldMonitoringService fieldMonitoringService;
    
    private IAlertService alertService;
    private IAppointmentTimeSlotDAO appointmentTimeSlotDAO;
    private IAlertsNewsPortalService alertsNewsPortalService;
    private AppointmentService appointmentService;
    private IAlertManagementSystemService alertManagementSystemService;
    
    private ICccDashboardService cccDashboardService;
    private IKaizalaInfoService kaizalaInfoService;
    private IPartyMeetingMOMService partyMeetingMOMService;
    private IAffiliatedMember affiliatedMember;
    private IitdpAppUserDAO itdpAppUserDAO;
    
	public IitdpAppUserDAO getItdpAppUserDAO() {
		return itdpAppUserDAO;
	}

	public void setItdpAppUserDAO(IitdpAppUserDAO itdpAppUserDAO) {
		this.itdpAppUserDAO = itdpAppUserDAO;
	}

	public IAffiliatedMember getAffiliatedMember() {
		return affiliatedMember;
	}

	public void setAffiliatedMember(IAffiliatedMember affiliatedMember) {
		this.affiliatedMember = affiliatedMember;
	}

	public IKaizalaInfoService getKaizalaInfoService() {
		return kaizalaInfoService;
	}

	public void setKaizalaInfoService(IKaizalaInfoService kaizalaInfoService) {
		this.kaizalaInfoService = kaizalaInfoService;
	}

	public void setLocationDashboardService(ILocationDashboardService locationDashboardService) {
		this.locationDashboardService = locationDashboardService;
	}

	public void setCccDashboardService(ICccDashboardService cccDashboardService) {
		this.cccDashboardService = cccDashboardService;
	}

	public IAlertManagementSystemService getAlertManagementSystemService() {
		return alertManagementSystemService;
	}

	public void setAlertManagementSystemService(
			IAlertManagementSystemService alertManagementSystemService) {
		this.alertManagementSystemService = alertManagementSystemService;
	}

	public AppointmentService getAppointmentService() {
		return appointmentService;
	}

	public void setAppointmentService(AppointmentService appointmentService) {
		this.appointmentService = appointmentService;
	}

	public IAlertsNewsPortalService getAlertsNewsPortalService() {
		return alertsNewsPortalService;
	}

	public void setAlertsNewsPortalService(
			IAlertsNewsPortalService alertsNewsPortalService) {
		this.alertsNewsPortalService = alertsNewsPortalService;
	}

	public IAppointmentTimeSlotDAO getAppointmentTimeSlotDAO() {
		return appointmentTimeSlotDAO;
	}

	public void setAppointmentTimeSlotDAO(IAppointmentTimeSlotDAO appointmentTimeSlotDAO) {
		this.appointmentTimeSlotDAO = appointmentTimeSlotDAO;
	}

	public void setAlertService(IAlertService alertService) {
		this.alertService = alertService;
	}

	public IFieldMonitoringService getFieldMonitoringService() {
		return fieldMonitoringService;
	}

	public void setFieldMonitoringService(
			IFieldMonitoringService fieldMonitoringService) {
		this.fieldMonitoringService = fieldMonitoringService;
	}

	public IGISVisualizationService getGisVisualizationService() {
		return gisVisualizationService;
	}

	public void setGisVisualizationService(
			IGISVisualizationService gisVisualizationService) {
		this.gisVisualizationService = gisVisualizationService;
	}

	public INotificationService getNotificationService() {
		return notificationService;
	}

	public void setNotificationService(INotificationService notificationService) {
		this.notificationService = notificationService;
	}

	public IUnionTypeDesignationDAO getUnionTypeDesignationDAO() {
		return unionTypeDesignationDAO;
	}

	public void setUnionTypeDesignationDAO(
			IUnionTypeDesignationDAO unionTypeDesignationDAO) {
		this.unionTypeDesignationDAO = unionTypeDesignationDAO;
	}

	public CommonMethodsUtilService getCommonMethodsUtilService() {
		return commonMethodsUtilService;
	}

	public void setCommonMethodsUtilService(
			CommonMethodsUtilService commonMethodsUtilService) {
		this.commonMethodsUtilService = commonMethodsUtilService;
	}

	public ITdpMemberUnionTabUserRelationDAO getTdpMemberUnionTabUserRelationDAO() {
		return tdpMemberUnionTabUserRelationDAO;
	}

	public void setTdpMemberUnionTabUserRelationDAO(
			ITdpMemberUnionTabUserRelationDAO tdpMemberUnionTabUserRelationDAO) {
		this.tdpMemberUnionTabUserRelationDAO = tdpMemberUnionTabUserRelationDAO;
	}

	public IUnionTabUserDAO getUnionTabUserDAO() {
		return unionTabUserDAO;
	}

	public void setUnionTabUserDAO(IUnionTabUserDAO unionTabUserDAO) {
		this.unionTabUserDAO = unionTabUserDAO;
	}

	public ISmsGatewayService getSmsGatewayService() {
		return smsGatewayService;
	}

	public void setSmsGatewayService(ISmsGatewayService smsGatewayService) {
		this.smsGatewayService = smsGatewayService;
	}

	public IMobileAppUserAccessLocationDAO getMobileAppUserAccessLocationDAO() {
		return mobileAppUserAccessLocationDAO;
	}

	public void setMobileAppUserAccessLocationDAO(
			IMobileAppUserAccessLocationDAO mobileAppUserAccessLocationDAO) {
		this.mobileAppUserAccessLocationDAO = mobileAppUserAccessLocationDAO;
	}

	public IMobileAppUserSmsStatusDAO getMobileAppUserSmsStatusDAO() {
		return mobileAppUserSmsStatusDAO;
	}

	public void setMobileAppUserSmsStatusDAO(
			IMobileAppUserSmsStatusDAO mobileAppUserSmsStatusDAO) {
		this.mobileAppUserSmsStatusDAO = mobileAppUserSmsStatusDAO;
	}

	public IMobileAppUserVoterDAO getMobileAppUserVoterDAO() {
		return mobileAppUserVoterDAO;
	}

	public void setMobileAppUserVoterDAO(
			IMobileAppUserVoterDAO mobileAppUserVoterDAO) {
		this.mobileAppUserVoterDAO = mobileAppUserVoterDAO;
	}

	public IMobileAppUserSmsDetailsDAO getMobileAppUserSmsDetailsDAO() {
		return mobileAppUserSmsDetailsDAO;
	}

	public void setMobileAppUserSmsDetailsDAO(
			IMobileAppUserSmsDetailsDAO mobileAppUserSmsDetailsDAO) {
		this.mobileAppUserSmsDetailsDAO = mobileAppUserSmsDetailsDAO;
	}

	public IActivityTabRequestBackupDAO getActivityTabRequestBackupDAO() {
		return activityTabRequestBackupDAO;
	}

	public void setActivityTabRequestBackupDAO(
			IActivityTabRequestBackupDAO activityTabRequestBackupDAO) {
		this.activityTabRequestBackupDAO = activityTabRequestBackupDAO;
	}

	public IAttendanceService getAttendanceService() {
		return attendanceService;
	}

	public void setAttendanceService(IAttendanceService attendanceService) {
		this.attendanceService = attendanceService;
	}

	public IActivityService getActivityService() {
		return activityService;
	}

	public void setActivityService(IActivityService activityService) {
		this.activityService = activityService;
	}

	public void setEventInviteeDAO(IEventInviteeDAO eventInviteeDAO) {
		this.eventInviteeDAO = eventInviteeDAO;
	}

	public void setCadreCommitteeService(
			ICadreCommitteeService cadreCommitteeService) {
		this.cadreCommitteeService = cadreCommitteeService;
	}
	public ITrainingCampService getTrainingCampService() {
		return trainingCampService;
	}

	public void setTrainingCampService(ITrainingCampService trainingCampService) {
		this.trainingCampService = trainingCampService;
	}
	
	public IMahaNaduService getMahaNaduService() {
		return mahaNaduService;
	}

	public void setMahaNaduService(IMahaNaduService mahaNaduService) {
		this.mahaNaduService = mahaNaduService;
	}

	public IPartyMeetingService getPartyMeetingService() {
		return partyMeetingService;
	}

	public void setPartyMeetingService(IPartyMeetingService partyMeetingService) {
		this.partyMeetingService = partyMeetingService;
	}

	public void setEventDAO(IEventDAO eventDAO) {
		this.eventDAO = eventDAO;
	}

	public IEventAttendeeErrorDAO getEventAttendeeErrorDAO() {
		return eventAttendeeErrorDAO;
	}

	public void setEventAttendeeErrorDAO(
			IEventAttendeeErrorDAO eventAttendeeErrorDAO) {
		this.eventAttendeeErrorDAO = eventAttendeeErrorDAO;
	}

	public IMessagingPropsDetailsDAO getMessagingPropsDetailsDAO() {
		return messagingPropsDetailsDAO;
	}

	public void setMessagingPropsDetailsDAO(
			IMessagingPropsDetailsDAO messagingPropsDetailsDAO) {
		this.messagingPropsDetailsDAO = messagingPropsDetailsDAO;
	}

	public void setEventRfidDetailsDAO(IEventRfidDetailsDAO eventRfidDetailsDAO) {
		this.eventRfidDetailsDAO = eventRfidDetailsDAO;
	}

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

	public void setSurveyUserAuthDAO(ISurveyUserAuthDAO surveyUserAuthDAO) {
		this.surveyUserAuthDAO = surveyUserAuthDAO;
	}
	

	public IConstituencyDAO getConstituencyDAO() {
		return constituencyDAO;
	}

	public void setConstituencyDAO(IConstituencyDAO constituencyDAO) {
		this.constituencyDAO = constituencyDAO;
	}
	

	public IDelimitationConstituencyMandalDetailsDAO getDelimitationConstituencyMandalDetailsDAO() {
		return delimitationConstituencyMandalDetailsDAO;
	}

	public void setDelimitationConstituencyMandalDetailsDAO(
			IDelimitationConstituencyMandalDetailsDAO delimitationConstituencyMandalDetailsDAO) {
		this.delimitationConstituencyMandalDetailsDAO = delimitationConstituencyMandalDetailsDAO;
	}
	

	public IAssemblyLocalElectionBodyDAO getAssemblyLocalElectionBodyDAO() {
		return assemblyLocalElectionBodyDAO;
	}

	public void setAssemblyLocalElectionBodyDAO(
			IAssemblyLocalElectionBodyDAO assemblyLocalElectionBodyDAO) {
		this.assemblyLocalElectionBodyDAO = assemblyLocalElectionBodyDAO;
	}
	public IPanchayatDAO getPanchayatDAO() {
		return panchayatDAO;
	}

	public void setPanchayatDAO(IPanchayatDAO panchayatDAO) {
		this.panchayatDAO = panchayatDAO;
	}
	
	public void setPartyMeetingMOMService(
			IPartyMeetingMOMService partyMeetingMOMService) {
		this.partyMeetingMOMService = partyMeetingMOMService;
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
	
	public Object getTDPCadreDetailsForSearch(CadrePrintInputVO input){
		List<CadrePrintVO> status = cadreRegistrationService.getTDPCadreDetailsForSearch(input);
		return status;
	}
	public Object getTDPCadreDetailsByMemberShip(CadrePrintInputVO input){
		List<CadrePrintVO> status = cadreRegistrationService.getTDPCadreDetailsByMemberShip(input);
		return status;
	}
	
	public Object getCardPrintCountForAllUsers(CardPrintUserVO inputVO){
		List<CardPrintUserVO> returnVo = cadreRegistrationService.getCardPrintCountForAllUsers(inputVO);
		return returnVo;
	}
	public Object getCardPrintCountByUser(CardPrintUserVO inputVO){
		List<CardPrintUserVO> returnVo = cadreRegistrationService.getCardPrintCountByUser(inputVO);
		return returnVo;
	}
	public Object updatePrintedCardDetails(List<CardNFCDetailsVO> inputVOList){
		String status = cadreRegistrationService.updatePrintedCardDetails(inputVOList);
		return status;
	}
	
	// Print/Reprint
	public Object updatePrintedCardInfo(List<CardNFCDetailsVO> inputVOList){
		String status = cadreRegistrationService.updatePrintedCardInfo(inputVOList);
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
				/*for(String memberShipNo :memberShipNos){
					String memberShipNumber = "AP14"+memberShipNo;
					String memberShipNumber1 = "TS14"+memberShipNo;
					String temp =  memberShipNos.get(memberShipNos.size() - 1);
					
					if(temp.equalsIgnoreCase(memberShipNo) || memberShipNos.size() == 1)
						queryStr.append(" (model.memberShipNo ='"+memberShipNo.trim()+"' OR model.memberShipNo ='"+memberShipNumber.trim()+"' OR model.memberShipNo ='"+memberShipNumber1.trim()+"')  ");
					else
						queryStr.append(" (model.memberShipNo ='"+memberShipNo.trim()+"' OR model.memberShipNo ='"+memberShipNumber.trim()+"' OR model.memberShipNo ='"+memberShipNumber1.trim()+"')  OR ");
				}*/
				
				String memberShipNumber="";
				String memberShipNumber1 ="";
				String memberShipNo ="";
				int i=memberShipNos.size();
				do{
					if(i==memberShipNos.size())
					{
						memberShipNumber = "\'AP14"+memberShipNos.get(i-1)+"\'";
						memberShipNumber1 = "\'TS14"+memberShipNos.get(i-1)+"\'";
						memberShipNo = "\'"+memberShipNos.get(i-1)+"\'";
					}
					else
					{
						memberShipNumber = memberShipNumber+",\'AP14"+memberShipNos.get(i-1)+"\'";
						memberShipNumber1 = memberShipNumber1+",\'TS14"+memberShipNos.get(i-1)+"\'";
						memberShipNo = memberShipNo+",\'"+memberShipNos.get(i-1)+"\'";
					}
					
					i=--i;
				}while(i>0);
				queryStr.append(" model.memberShipNo in ("+memberShipNo.trim()+") OR model.memberShipNo in ("+memberShipNumber.trim()+") OR model.memberShipNo in ("+memberShipNumber1.trim()+") ");
			}
			List<Object[]>  mobileNos = tdpCadreDAO.getMobileNosByMemberShipId(queryStr.toString());
			
			if(mobileNos!= null && mobileNos.size() >0){
				for(Object[] params : mobileNos){
					CadreAddressVO vo = new CadreAddressVO();
					if(params[0] != null){
						if(params[0].toString().trim().length() > 8){
							vo.setMembershipNo(params[0].toString().trim().substring(params[0].toString().trim().length()-8));
						}else{
							vo.setMembershipNo(params[0].toString());
						}
					}else{
						vo.setMembershipNo("");
					}
					//vo.setMembershipNo(params[0].toString().substring(4));
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
				String memberShipNumber="";
				String memberShipNumber1 ="";
				String memberShipNo ="";
				int i=addressTrueList.size();
				do{
					if(i==addressTrueList.size())
					{
						memberShipNumber = "\'AP14"+addressTrueList.get(i-1)+"\'";
						memberShipNumber1 = "\'TS14"+addressTrueList.get(i-1)+"\'";
						memberShipNo = "\'"+addressTrueList.get(i-1)+"\'";
					}
					else
					{
						memberShipNumber = memberShipNumber+",\'AP14"+addressTrueList.get(i-1)+"\'";
						memberShipNumber1 = memberShipNumber1+",\'TS14"+addressTrueList.get(i-1)+"\'";
						memberShipNo = memberShipNo+",\'"+addressTrueList.get(i-1)+"\'";
					}
					
					i=--i;
				}while(i>0);
				
				queryStr.append(" model.memberShipNo in ("+memberShipNo.trim()+") OR model.memberShipNo in ("+memberShipNumber.trim()+") OR model.memberShipNo in ("+memberShipNumber1.trim()+") ");
				
				/*for(String memberShipNo :addressTrueList){
					 memberShipNumber = "AP14"+memberShipNo;
					 memberShipNumber1 = "TS14"+memberShipNo;
					
					String temp =  addressTrueList.get(addressTrueList.size() - 1);
					if(temp.equalsIgnoreCase(memberShipNo) || addressTrueList.size() == 1)
						queryStr.append("  (model.memberShipNo in ('"+memberShipNo.trim()+"') OR model.memberShipNo in ('"+memberShipNumber.trim()+"') OR model.memberShipNo in ('"+memberShipNumber1.trim()+"')) OR ");
					else
						queryStr.append(" (model.memberShipNo in ('"+memberShipNo.trim()+"') OR model.memberShipNo in ('"+memberShipNumber.trim()+"') OR model.memberShipNo in ('"+memberShipNumber1.trim()+"'))  OR ");
				}*/
			}
			
			StringBuilder queryStr1 = new StringBuilder();
			if(addressFalseList != null &&  addressFalseList.size() > 0)
			{
				
				String memberShipNumber="";
				String memberShipNumber1 ="";
				String memberShipNo ="";
				int i=addressFalseList.size();
				do{
					if(i==addressFalseList.size())
					{
						memberShipNumber = "\'AP14"+addressFalseList.get(i-1)+"\'";
						memberShipNumber1 = "\'TS14"+addressFalseList.get(i-1)+"\'";
						memberShipNo = "\'"+addressFalseList.get(i-1)+"\'";
					}
					else
					{
						memberShipNumber = memberShipNumber+",\'AP14"+addressFalseList.get(i-1)+"\'";
						memberShipNumber1 = memberShipNumber1+",\'TS14"+addressFalseList.get(i-1)+"\'";
						memberShipNo = memberShipNo+",\'"+addressFalseList.get(i-1)+"\'";
					}
					
					i=--i;
				}while(i>0);
				
				queryStr1.append(" model.memberShipNo in ("+memberShipNo.trim()+") OR model.memberShipNo in ("+memberShipNumber.trim()+") OR model.memberShipNo in ("+memberShipNumber1.trim()+") ");
				
				/*for(String memberShipNo :addressFalseList){
					String temp =  addressFalseList.get(addressFalseList.size() - 1);
					if(temp.equalsIgnoreCase(memberShipNo) || addressFalseList.size() == 1)
						queryStr1.append("  (model.memberShipNo in ('"+memberShipNo.trim()+"'))  ");
					else
						queryStr1.append("  (model.memberShipNo in  ('"+memberShipNo.trim()+"'))  OR ");
				}*/
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
				if(params[4] != null){
					if(params[4].toString().trim().length() > 8){
						cadreAddressVO.setMembershipNo(params[4].toString().trim().substring(params[4].toString().trim().length()-8));
					}else{
						cadreAddressVO.setMembershipNo(params[4].toString());
					}
				}else{
					cadreAddressVO.setMembershipNo("");
				}
				//cadreAddressVO.setMembershipNo(params[4] != null ? params[4].toString().substring(4) : "");
				cadreAddressVO.setName(params[0] != null ? params[0].toString() : "");
				cadreAddressVO.setMobileNo(params[1] != null ? params[1].toString() : "");
				cadreAddressVO.setAge(params[2] != null ? (Long)params[2] : null);
				cadreAddressVO.setGender(params[3] != null ? params[3].toString() : "");
				
				
				if(address.equalsIgnoreCase("true"))
				{
					cadreAddressVO.setRefNo(params[10] != null ? params[10].toString() : "");
					cadreAddressVO.setPhoto(params[11] != null ? "https://mytdp.com/cadre_images/"+params[11].toString() : "");
					
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
				if(address.equalsIgnoreCase("false"))
				{
					cadreAddressVO.setPhoto(params[5] != null ? "https://mytdp.com/cadre_images/"+params[5].toString() : "");
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
				queryStr.append(" (model.memberShipNo ='"+memberShipNo.trim()+"' OR  model.memberShipNo ='"+memberShipNumber.trim()+"' OR model.memberShipNo ='"+memberShipNumber1.trim()+"') ");
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
				    	   queryStr.append("  ((model.memberShipNo ='"+memberShipNo.trim()+"' OR model.memberShipNo ='"+memberShipNumber.trim()+"' OR  model.memberShipNo ='"+memberShipNumber1.trim()+"') and model.cardNumber = '"+refNo+"')  ");
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
					if(params[2] != null){
						if(params[2].toString().trim().length() > 8){
							cadreAddressVO.setMembershipNo(params[2].toString().trim().substring(params[2].toString().trim().length()-8));
						}else{
							cadreAddressVO.setMembershipNo(params[2].toString());
						}
					}else{
						cadreAddressVO.setMembershipNo("");
					}
				//cadreAddressVO.setMembershipNo(params[2] != null ? params[2].toString().substring(4) : "");
				cadreAddressVO.setName(params[0] != null ? params[0].toString() : "");
				cadreAddressVO.setMobileNo(params[1] != null ? params[1].toString() : "");
				cadreAddressVO.setRefNo(params[3] != null ? params[3].toString() : "");
				cadreAddressVO.setPhoto(params[4] != null ? "https://mytdp.com/images/cadre_images/"+params[4].toString() : "");
				}
				
			}
			if(count == 0){
				
				Object[] params = list.get(0);
				if(params[2] != null){
					if(params[2].toString().trim().length() > 8){
						cadreAddressVO.setMembershipNo(params[2].toString().trim().substring(params[2].toString().trim().length()-8));
					}else{
						cadreAddressVO.setMembershipNo(params[2].toString());
					}
				}else{
					cadreAddressVO.setMembershipNo("");
				}
				//cadreAddressVO.setMembershipNo(params[2] != null ? params[2].toString().substring(4) : "");
				cadreAddressVO.setName(params[0] != null ? params[0].toString() : "");
				cadreAddressVO.setMobileNo(params[1] != null ? params[1].toString() : "");
				cadreAddressVO.setRefNo(params[3] != null ? params[3].toString() : "");
				cadreAddressVO.setPhoto(params[4] != null ? "https://mytdp.com/images/cadre_images/"+params[4].toString() : "");
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
				if(params[2] != null){
					if(params[2].toString().trim().length() > 8){
						cadreAddressVO.setMembershipNo(params[2].toString().trim().substring(params[2].toString().trim().length()-8));
					}else{
						cadreAddressVO.setMembershipNo(params[2].toString());
					}
				}else{
					cadreAddressVO.setMembershipNo("");
				}
				//cadreAddressVO.setMembershipNo(params[2] != null ? params[2].toString().substring(4) : "");
				cadreAddressVO.setName(params[0] != null ? params[0].toString() : "");
				cadreAddressVO.setMobileNo(params[1] != null ? params[1].toString() : "");
				cadreAddressVO.setRefNo(params[3] != null ? params[3].toString() : "");
				cadreAddressVO.setPhoto(params[4] != null ? "https://mytdp.com/images/cadre_images/"+params[4].toString() : "");
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
		
		 UserEventDetailsVO userEventDetailsVO = null;
		 
		 DateUtilService date = new DateUtilService();
		 SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		 SimpleDateFormat dateFormate = new SimpleDateFormat("yyyy-MM-dd");
		try{
			//List<Object[]> list =  eventSurveyUserDAO.getUserDetailsByUnamePwd(inpuVo.getUserName(),inpuVo.getUserPassword());
			LoginResponceVO statusVO = checkValidLoginOrNot(inpuVo.getUserName(),inpuVo.getUserPassword(),inpuVo.getImei1(),inpuVo.getImei2());
			if(statusVO.getStatus().equalsIgnoreCase("logged")){
					userEventDetailsVO = new UserEventDetailsVO();
						List<WebServiceBaseUrl> webserviceUrls = webServiceBaseUrlDAO.getBaseUrlsForAnApp("EVENT_MGMT");
						if(webserviceUrls != null && webserviceUrls.size() > 0)
						{
							for(WebServiceBaseUrl vo : webserviceUrls)
							{
								WebServiceBaseVO webServiceBaseVO = new WebServiceBaseVO();
								webServiceBaseVO.setAppName(vo.getAppName() != null ? vo.getAppName() : "");
								webServiceBaseVO.setId(vo.getWebServiceBaseUrlId());
								webServiceBaseVO.setUrl(vo.getUrl() != null ? vo.getUrl() : "");
								webServiceBaseVO.setStatusSyncType(vo.getSyncType() != null ? vo.getSyncType() : "");
								userEventDetailsVO.getWebserviceurlsList().add(webServiceBaseVO);
							}
						}
						
						
						List<MessagingPropsDetails>  msgProperties = messagingPropsDetailsDAO.getMessagingPropsDetails("EVENT_MGMT");
						if(msgProperties != null && msgProperties.size() > 0)
						{
							for(MessagingPropsDetails params : msgProperties)
							{
								 MessagePropertyVO messagePropertyVO = new MessagePropertyVO();
								 messagePropertyVO.setAppName(params.getAppName() != null ? params.getAppName() : "");
								 messagePropertyVO.setExchangeName(params.getExchangeName() != null ? params.getExchangeName() : "");
								 messagePropertyVO.setHost(params.getHost() != null? params.getHost() : "");
								 messagePropertyVO.setPort(params.getPort() != null ? params.getPort() : "");
								 messagePropertyVO.setUserName(params.getUsername() != null ? params.getUsername() : "");
								 messagePropertyVO.setPassword(params.getPassword() != null ? params.getPassword() : "");
								 messagePropertyVO.setSecreatKey(params.getSecreatKey() != null ? params.getSecreatKey() : "");
								 messagePropertyVO.setVirtualHost(params.getVirtualHost() != null ? params.getVirtualHost() : "");
								 userEventDetailsVO.getMessagePropertiesList().add(messagePropertyVO);
							}
						}
						
						
					userEventDetailsVO.setUserName(statusVO.getConstituencyName());
					userEventDetailsVO.setId(statusVO.getUserId());
					userEventDetailsVO.setStatus(statusVO.getStatus());
					List checkList = eventSurveyUserLoginDetailsDAO.checkUserExistence(statusVO.getUserId(),inpuVo.getImei1());
					if(checkList == null || checkList.size() == 0)
					{
						
						EventSurveyUserLoginDetails eventSurveyUserLoginDetails = new EventSurveyUserLoginDetails();
						eventSurveyUserLoginDetails.setImei(inpuVo.getImei1());
						if(inpuVo.getLoginTimeStamp() != null)
						eventSurveyUserLoginDetails.setLoginTime(formatter.parse(inpuVo.getLoginTimeStamp()));
						if(inpuVo.getSIMCardNumber() != null)
						eventSurveyUserLoginDetails.setSimno(inpuVo.getSIMCardNumber());
						eventSurveyUserLoginDetails.setEventSurveyUser(eventSurveyUserDAO.get(statusVO.getUserId()));
						eventSurveyUserLoginDetailsDAO.save(eventSurveyUserLoginDetails);
					}
				
				//eventSurveyUserLoginDetailsDAO.saveCadreFromAndroid(voterDetails)
				List<Long> eventsIdsList = new ArrayList<Long>();
				List<Object[]> parentEvent = eventUserDAO.getParentEventByUser(userEventDetailsVO.getId(),date.getCurrentDateAndTime());
				if(parentEvent != null && parentEvent.size() > 0)
				{
					
					for(Object[] params : parentEvent)
					{
						UserEventDetailsVO eventVo = new UserEventDetailsVO();
						eventVo.setId((Long)params[0]);
						eventVo.setUserName(params[1] != null ? params[1].toString() : "");
						eventVo.setEventSyncType(params[6] != null ? params[6].toString() : "");
						eventVo.setIsInviteeExist(params[7] != null ? params[7].toString() : "");
						eventVo.setAcceptedEnrollmentYear(params[8] != null ? params[8].toString():"");
						userEventDetailsVO.getSubList().add(eventVo);
						eventsIdsList.add((Long)params[0]);
					}
					List<Object[]> events = eventUserDAO.getEventsByUserAndParentIds(userEventDetailsVO.getId(),date.getCurrentDateAndTime(),eventsIdsList);
					
					if(events != null && events.size() > 0)
					{
						for(Object[] params : events)
						{
							UserEventDetailsVO parentVo = getMatchedEvent(userEventDetailsVO.getSubList(),(Long)params[2]);
							if(parentVo != null)
							{
								UserEventDetailsVO childEventVo = new UserEventDetailsVO();
								childEventVo.setEventId((Long)params[0]);
								childEventVo.setUserName(params[1] != null ? params[1].toString() : "");
								if(params[4]!= null)
								childEventVo.setStartTime(TimeForm(params[4].toString()));
								if(params[5]!= null)
								childEventVo.setEndTime(TimeForm(params[5]!= null?params[5].toString():""));
								childEventVo.setIsInviteeExist(params[6] != null ? params[6].toString() : "");
								childEventVo.setEntryLimit(params[7] != null ? Long.valueOf(params[7].toString()) : 0L);
								childEventVo.setServerWorkMode(params[8] != null ? params[8].toString() : "");
								childEventVo.setTabWorkMode(params[9] != null ? params[9].toString() : "");
								childEventVo.setStartDate(params[10] != null ? dateFormate.format(params[10]) : "");
								childEventVo.setEndDate(params[11] != null ? dateFormate.format(params[11]) : "");
								childEventVo.setOrderId(params[12] != null ? Long.valueOf(params[12].toString().trim()) : null);
								childEventVo.setIsActive(params[13] != null ? params[13].toString() : "");
								childEventVo.setEventSyncType(params[14] != null ? params[14].toString() : "");
								childEventVo.setAcceptedEnrollmentYear(params[15] != null ? params[15].toString() : "");
								List<Long> eventIds = new ArrayList<Long>();
								eventIds.add(childEventVo.getEventId());
								List<Object[]> eventRfidDetailsList = eventRfidDetailsDAO.getEventRFIDDetailsByEventIds(eventIds);	
								
								if(eventRfidDetailsList != null && eventRfidDetailsList.size()>0){
									for (Object[] event : eventRfidDetailsList)
									{
										UserEventDetailsVO vo = new UserEventDetailsVO();
										vo.setRFID(event[1] != null ? event[1].toString() : "");
										vo.setRegText(event[2] != null ? event[2].toString() : "");
										vo.setSectorNo(event[3] != null ? Long.valueOf(event[3].toString().trim()) : 0L);
										vo.setBlockNo(event[4] != null ? Long.valueOf(event[4].toString().trim()) : 0L);
										vo.setOrderNo(event[5] != null ? Long.valueOf(event[5].toString().trim()) : 0L);
										childEventVo.getSubList().add(vo);
									}
								}
								parentVo.getSubList().add(childEventVo);
							}
						}	
					}
				}
					
			}else{
				userEventDetailsVO = new UserEventDetailsVO();
				userEventDetailsVO.setStatus(statusVO.getStatus());
			}
			
		}
		catch(Exception e)
		{
			Log.error("Exception Occured in validateUserForEvent() method",e) ;
			e.printStackTrace();
			userEventDetailsVO = new UserEventDetailsVO();
			userEventDetailsVO.setStatus("error");
		}
		return userEventDetailsVO;
	}
	public UserEventDetailsVO updateDatasyncurl(UserEventDetailsVO inpuVo)
	{
		 UserEventDetailsVO userEventDetailsVO = null;
		 DateUtilService date = new DateUtilService();
		try{
			userEventDetailsVO = new UserEventDetailsVO();
			if(inpuVo.getAppName() != null && !inpuVo.getAppName() .isEmpty())
			{
				List<WebServiceBaseUrl> webserviceUrls = webServiceBaseUrlDAO.getBaseUrlsForAnApp(inpuVo.getAppName().trim().toString());
				if(webserviceUrls != null && webserviceUrls.size() > 0)
				{
					for(WebServiceBaseUrl vo : webserviceUrls)
					{
						WebServiceBaseVO webServiceBaseVO = new WebServiceBaseVO();
						webServiceBaseVO.setAppName(vo.getAppName() != null ? vo.getAppName() : "");
						webServiceBaseVO.setId(vo.getWebServiceBaseUrlId());
						webServiceBaseVO.setUrl(vo.getUrl() != null ? vo.getUrl() : "");
						webServiceBaseVO.setStatusSyncType(vo.getSyncType() != null ? vo.getSyncType() : "");
						userEventDetailsVO.getWebserviceurlsList().add(webServiceBaseVO);
					}
				}
			}
				List<Long> eventsIdsList = new ArrayList<Long>();
				List<Object[]> parentEvent = eventUserDAO.getParentEventByUser(inpuVo.getUserId(),date.getCurrentDateAndTime());
				if(parentEvent != null && parentEvent.size() > 0)
				{
					
					for(Object[] params : parentEvent)
					{
						UserEventDetailsVO eventVo = new UserEventDetailsVO();
						eventVo.setId((Long)params[0]);
						eventVo.setEventName(params[1] != null ? params[1].toString() : "");
						eventVo.setEventSyncType(params[6] != null ? params[6].toString() : "");
						userEventDetailsVO.getSubList().add(eventVo);
						eventsIdsList.add((Long)params[0]);
					}
					
					List<Object[]> events = eventUserDAO.getEventsDataByUserAndParentIds(inpuVo.getUserId(),date.getCurrentDateAndTime(),eventsIdsList);
					
					if(events != null && events.size() > 0)
					{
						for(Object[] params : events)
						{
							UserEventDetailsVO parentVo = getMatchedEvent(userEventDetailsVO.getSubList(),(Long)params[2]);
							if(parentVo != null)
							{
								UserEventDetailsVO childEventVo = new UserEventDetailsVO();
								childEventVo.setEventId((Long)params[0]);
								childEventVo.setEventName(params[1] != null ? params[1].toString() : "");
								childEventVo.setEventSyncType(params[3] != null ? params[3].toString() : "");
								parentVo.getSubList().add(childEventVo);
							}
						}
					}
		  }
		}
		catch(Exception e)
		{
			Log.error("Exception Occured in updateDatasyncurl() method",e) ;
			e.printStackTrace();
			userEventDetailsVO = new UserEventDetailsVO();
			userEventDetailsVO.setStatus("error");	
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
	 public UserEventDetailsVO insertEventAttendeeInfo(UserEventDetailsVO inputVo)
		{
		  String errorDesc = "";
		 UserEventDetailsVO returnVo = new UserEventDetailsVO();
		 DateUtilService date = new DateUtilService();
		 try{
			 try{
				Long count = eventSurveyUserDAO.checkUserBlockedOrNot(inputVo.getUserId());
				if(count.longValue() > 0){
					returnVo.setStatus("blocked");
					returnVo.setUserId(inputVo.getUserId());
					return returnVo;
				}
				 count = eventSurveyUserDAO.checkUserBlockedOrNot(inputVo.getId());
				 if(count.longValue() > 0){
						returnVo.setStatus("record blocked");
						returnVo.setUserId(inputVo.getId());
						return returnVo;
					}
			 }catch(Exception ex){
				 Log.error(ex) ;
			 }
			 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 	String memberShipNumber = "AP14"+inputVo.getMemberShipNo();
			String memberShipNumber1 = "TS14"+inputVo.getMemberShipNo();
			
		 	StringBuilder queryStr = new StringBuilder();
		 	if(inputVo.getMemberShipNo() != null && !inputVo.getMemberShipNo().isEmpty()){
		 		queryStr.append(" (model.memberShipNo ='"+inputVo.getMemberShipNo().trim()+"' OR model.memberShipNo ='"+memberShipNumber.trim()+"' OR model.memberShipNo ='"+memberShipNumber1.trim()+"') ");
		 	}
		 	/*else
		 	{
		 		queryStr.append(" model.cardNumber = '"+inputVo.getRFID()+"' ");
		 	}*/
		 	
		 	EventAttendee eventAttendee = new EventAttendee();
		 	
		 	String queryString = queryStr.toString();
		 	
		 	if( queryString != null && queryString.trim().length() > 0){
		 		
		 	 	List cadreList= tdpCadreDAO.getTdpCadreIdByMembership(queryStr.toString());
			 	
			 	if(cadreList != null && cadreList.size() > 0){
			 		
				 	eventAttendee.setTdpCadreId((Long)cadreList.get(0));
				 	eventAttendee.setImei(inputVo.getIMEI());
				 	if(inputVo.getRFID() != null) 
				 	eventAttendee.setRfid(inputVo.getRFID());
				 	
				 	eventAttendee.setInsertedBy(inputVo.getId());
				 	eventAttendee.setEventId(inputVo.getEventId());
				 	eventAttendee.setAttendedTime(formatter.parse(inputVo.getLoginTimeStamp().toString()));
				 	eventAttendee.setInsertedTime(date.getCurrentDateAndTime());
				 	
				 	if(inputVo.getStatus() != null)
				 	eventAttendee.setUniqueKey(inputVo.getStatus());
				 	
				 	
				 	eventAttendee.setLatitude(inputVo.getLatituede());
				 	eventAttendee.setLongitude(inputVo.getLongitude());
				 	eventAttendee.setTabUserId(inputVo.getId());
				 	eventAttendee.setCurrentTabUserId(inputVo.getUserId());
				 	eventAttendee.setSyncSource(inputVo.getSyncSource() != null ? inputVo.getSyncSource() : "WS");
				 	
				
				 	eventAttendee = eventAttendeeDAO.save(eventAttendee);
				 	voterDAO.flushAndclearSession();
				 	returnVo.setId(eventAttendee.getEventAttendeeId());
				 	returnVo.setStatus("success");
				 	returnVo.setUserId(inputVo.getId());
				 
				 	returnVo.setTabPrimaryKey(inputVo.getTabPrimaryKey());
				 	if(cadreList != null){
				 		returnVo.setMemberShipNo(cadreList.get(0).toString());
				 	}
			 		
			 	}else{
			 		
			 		errorDesc = "Invalid Membership";
			 		returnVo.setErrorDesc(errorDesc);
			 		setEventErrorData(inputVo,errorDesc);
			 		returnVo.setStatus("fail");	
			 		
			 	}
		 	}
		 	else{
		 	
		 		errorDesc = "Invalid Membership";
		 		returnVo.setErrorDesc(errorDesc);
		 		setEventErrorData(inputVo,errorDesc);
		 		returnVo.setStatus("fail");	
		 	}
		 	
		 }
		 catch(Exception e)
		 {
			    Log.error("Exception Occured in insertEventAttendeeInfo() method",e) ;
			    errorDesc = "Exception";
			    returnVo.setErrorDesc(errorDesc);
		 		setEventErrorData(inputVo,e.toString());
		 		returnVo.setStatus("fail");
				
		 }
		return returnVo;
	}
	 
	 public void setEventErrorData(UserEventDetailsVO inputVo,String errorDesc)
	 {
		 try{
			 DateUtilService date = new DateUtilService();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			EventAttendeeError  eventAttendeeError = new EventAttendeeError();
			eventAttendeeError.setCurrentTabUserId(inputVo.getUserId());
			eventAttendeeError.setAttendedTime(formatter.parse(inputVo.getLoginTimeStamp().toString()));
			eventAttendeeError.setEventId(inputVo.getEventId());
			eventAttendeeError.setImei(inputVo.getIMEI());
			eventAttendeeError.setLatitude(inputVo.getLatituede());
			eventAttendeeError.setLongitude(inputVo.getLongitude());
			if(inputVo.getStatus() != null)
			eventAttendeeError.setUniqueKey(inputVo.getStatus());
			eventAttendeeError.setTabUserId(inputVo.getId());
			eventAttendeeError.setInsertedTime(date.getCurrentDateAndTime());
			eventAttendeeError.setInsertedBy(inputVo.getId());
			eventAttendeeError.setMemberShipId(inputVo.getMemberShipNo());
			if(inputVo.getRFID() != null)
			eventAttendeeError.setRfid(inputVo.getRFID());
			eventAttendeeError.setSyncSource(inputVo.getSyncSource() != null ? inputVo.getSyncSource() : "WS");
			eventAttendeeError.setErrorDescription(errorDesc);
			eventAttendeeErrorDAO.save(eventAttendeeError);
			
		 }
		 catch (Exception e) {
			 Log.error("Exception Occured in setEventErrorData() method",e) ;
			 
		}
	 }
	 
	 public LoginResponceVO checkValidLoginOrNot(String userName,String password,String imei1,String imei2){
	    	LoginResponceVO vo = new LoginResponceVO();
	    	//0id,1firstName,2lastName
	    	List<EventSurveyUser> list =  eventSurveyUserDAO.checkValidUserOrNot(userName,password);
		 //List<CadreSurveyUser> users = cadreSurveyUserDAO.getByUserNameAndPassword(userName, password);
	    	if(list != null && list.size() > 0 && list.get(0) != null){
	    		vo.setUserId(list.get(0).getEventSurveyUserId());
	    		String name = "";
	    		if(list.get(0).getFirstName() != null){
	    			name = list.get(0).getFirstName();
	    		}
	    		if(list.get(0).getLastName() != null){
	    			name = name+" "+list.get(0).getLastName();
	    		}
	    		vo.setConstituencyName(name);
	    	}
		 if(list == null || list.size() == 0 || list.get(0) == null){
			 vo.setStatus("login failure");
			 SurveyUserAuth surveyUserAuth = new SurveyUserAuth();
				 surveyUserAuth.setUserName(userName);
				 surveyUserAuth.setPassword(password);
				 surveyUserAuth.setImeiNo(imei1);
				 surveyUserAuth.setImeiNo2(imei2);
				 surveyUserAuth.setInsertedTime(new DateUtilService().getCurrentDateAndTime());
				 surveyUserAuth.setIsDeleted("Y");
				 surveyUserAuth.setStatus("failure");
				surveyUserAuthDAO.save(surveyUserAuth);
			 return vo;
		 }else if(list.get(0).getIsExcluded().equalsIgnoreCase("Y")){
			 vo.setStatus("logged");
			 SurveyUserAuth surveyUserAuth = new SurveyUserAuth();
			 surveyUserAuth.setEventSurveyUserId(list.get(0).getEventSurveyUserId());
			 surveyUserAuth.setImeiNo(imei1);
			 surveyUserAuth.setImeiNo2(imei2);
			 surveyUserAuth.setInsertedTime(new DateUtilService().getCurrentDateAndTime());
			 surveyUserAuth.setIsDeleted("Y");
			 surveyUserAuth.setStatus("success with exclude");
			 surveyUserAuthDAO.save(surveyUserAuth);
			return vo;
		 }
		 synchronized ("checkValidEventUserOrNot") {
			 if((imei1 != null && imei1.length() > 0) && (imei2 != null && imei2.length() > 0) ){
				  checkVaidLogInOrNot(list.get(0).getEventSurveyUserId(),imei1,imei2,vo);
				 
			 }else{
				 String reqImei = imei1;
				 if(imei2 != null && imei2.length() > 0){
					 reqImei = imei2;
				 }		 
				  checkVaidLogInOrNot(list.get(0).getEventSurveyUserId(),reqImei,vo);
				  
			 }
		 }
		 return vo;
		}
	 
	 public LoginResponceVO checkVaidLogInOrNot(Long userId,String imei,LoginResponceVO vo){
	    	Long count = surveyUserAuthDAO.checkRecordExistWithGivenDetailsOrNot(userId, imei);
	    	
	    	if(count.longValue() == 0){
	    		SurveyUserAuth surveyUserAuth = new SurveyUserAuth();
	    		surveyUserAuth.setEventSurveyUserId(userId);
	    		surveyUserAuth.setImeiNo(imei);
	    		surveyUserAuth.setInsertedTime(new DateUtilService().getCurrentDateAndTime());
	    		surveyUserAuth.setIsDeleted("N");
	    		surveyUserAuth.setStatus("success");
	    		surveyUserAuthDAO.save(surveyUserAuth);
	    		vo.setStatus("logged");
	    		return vo;
	    	}else{
	    		count = surveyUserAuthDAO.checkRecordBelongsToUserOrNot(userId, imei);
	    		if(count.longValue() > 0){
	    			surveyUserAuthDAO.updateRecordBelongsToUserOrNot(userId, imei);
	    			SurveyUserAuth surveyUserAuth = new SurveyUserAuth();
	    			surveyUserAuth.setEventSurveyUserId(userId);
	    			surveyUserAuth.setImeiNo(imei);
	    			surveyUserAuth.setInsertedTime(new DateUtilService().getCurrentDateAndTime());
	    			surveyUserAuth.setIsDeleted("N");
	    			surveyUserAuth.setStatus("success");
	    			surveyUserAuthDAO.save(surveyUserAuth);
	        		vo.setStatus("logged");
	        		return vo;
	    		}else{
	    			return getActualCaseForNotAllowingToLogIn(userId,imei,vo);
	    		}
	    	}
	    	
	    }
	 
	 private LoginResponceVO getActualCaseForNotAllowingToLogIn(Long userId,String imei,LoginResponceVO vo){
	    	
		 SurveyUserAuth surveyUserAuth = new SurveyUserAuth();
		 surveyUserAuth.setEventSurveyUserId(userId);
		 surveyUserAuth.setImeiNo(imei);
		 surveyUserAuth.setInsertedTime(new DateUtilService().getCurrentDateAndTime());
		 surveyUserAuth.setIsDeleted("Y");
			
	    	Long count = surveyUserAuthDAO.checkUserAlreadyLoggedInAnotherTab(userId, imei);
	    	
	    	if(count.longValue() > 0){
	    		surveyUserAuth.setStatus("same user");
	    		surveyUserAuthDAO.save(surveyUserAuth);
	    		vo.setStatus("same user");
	    		return vo;
	    	}else{
	    		surveyUserAuth.setStatus("same tab");
	    		surveyUserAuthDAO.save(surveyUserAuth);
	    		vo.setStatus("same tab");
	    		return vo;
	    	}
	    }
	 
	 public LoginResponceVO checkVaidLogInOrNot(Long userId,String imei1,String imei2,LoginResponceVO vo){
	    	Long count = surveyUserAuthDAO.checkRecordExistWithGivenDetailsOrNot(userId,imei1,imei2);
	    	
	    	if(count.longValue() == 0){
	    		SurveyUserAuth surveyUserAuth = new SurveyUserAuth();
	    		surveyUserAuth.setEventSurveyUserId(userId);
	    		surveyUserAuth.setImeiNo(imei1);
	    		surveyUserAuth.setImeiNo2(imei2);
	    		surveyUserAuth.setInsertedTime(new DateUtilService().getCurrentDateAndTime());
	    		surveyUserAuth.setIsDeleted("N");
	    		surveyUserAuth.setStatus("success");
	    		surveyUserAuthDAO.save(surveyUserAuth);
	    		vo.setStatus("logged");
	    		return vo;
	    	}else{
	    		count = surveyUserAuthDAO.checkRecordBelongsToUserOrNot(userId,imei1,imei2);
	    		if(count.longValue() > 0){
	    			surveyUserAuthDAO.updateRecordBelongsToUserOrNot(userId,imei1,imei2);
	    			SurveyUserAuth surveyUserAuth = new SurveyUserAuth();
	    			surveyUserAuth.setEventSurveyUserId(userId);
	    			surveyUserAuth.setImeiNo(imei1);
	    			surveyUserAuth.setImeiNo2(imei2);
	    			surveyUserAuth.setInsertedTime(new DateUtilService().getCurrentDateAndTime());
	    			surveyUserAuth.setIsDeleted("N");
	    			surveyUserAuth.setStatus("success");
	        		surveyUserAuthDAO.save(surveyUserAuth);
	        		vo.setStatus("logged");
	        		return vo;
	    		}else{
	    			return getActualCaseForNotAllowingToLogIn(userId,imei1,imei2,vo);
	    		}
	    	}
	    	
	    }
	 
	 private LoginResponceVO getActualCaseForNotAllowingToLogIn(Long userId,String imei1,String imei2,LoginResponceVO vo){
	    	
		 SurveyUserAuth surveyUserAuth = new SurveyUserAuth();
		 surveyUserAuth.setEventSurveyUserId(userId);
		 surveyUserAuth.setImeiNo(imei1);
		 surveyUserAuth.setImeiNo2(imei2);
		 surveyUserAuth.setInsertedTime(new DateUtilService().getCurrentDateAndTime());
		 surveyUserAuth.setIsDeleted("Y");
			
	    	Long count = surveyUserAuthDAO.checkUserAlreadyLoggedInAnotherTab(userId, imei1,imei2);
	    	
	    	if(count.longValue() > 0){
	    		surveyUserAuth.setStatus("same user");
	    		surveyUserAuthDAO.save(surveyUserAuth);
	    		vo.setStatus("same user");
	    		return vo;
	    	}else{
	    		surveyUserAuth.setStatus("same tab");
	    		surveyUserAuthDAO.save(surveyUserAuth);
	    		vo.setStatus("same tab");
	    		return vo;
	    	}
	    }
	 
	  public VerifierVO getTdpCadreSurveyDetails(Long tdpCadreId,Long surveyId,String searchTypeStr,Long boothId,String isPriority,String voterCardNo,
			  Long constituencyId,String constiTypeStr)
	  {
		  Long panchayatId=0L;
		  Long mandalId=0L;
		  Long  localelectionBodyId =0L;
		  List<Object[]> addressList = tdpCadreDAO.getCadrAddressDetailsByCadred(tdpCadreId);
		  if(commonMethodsUtilService.isListOrSetValid(addressList))
			  for (Object[] param : addressList) {
				  boothId= commonMethodsUtilService.getLongValueForObject(param[16]);
				  constituencyId =commonMethodsUtilService.getLongValueForObject(param[5]);
				  panchayatId=commonMethodsUtilService.getLongValueForObject(param[11]);
				  mandalId=commonMethodsUtilService.getLongValueForObject(param[7]);
				  localelectionBodyId=commonMethodsUtilService.getLongValueForObject(param[13]);
			}
		  VerifierVO verifierVO = null;
		  try {
			  List<VerifierVO> resultList = null;
			  Client client = Client.create();
			  client.addFilter(new HTTPBasicAuthFilter(IConstants.SURVEY_WEBSERVICE_USERNAME, IConstants.SURVEY_WEBSERVICE_PASSWORD));
			  WebResource webResource = client.resource("https://www.mytdp.com/Survey/WebService/getTdpCadreSurveyDetails/"+tdpCadreId+"/"+surveyId+"/"+searchTypeStr+"/"+boothId+"/"+isPriority+"/"+voterCardNo+"/"+constituencyId+"/"+constiTypeStr+"/"+panchayatId+"/"+mandalId+"/"+localelectionBodyId);
			  //WebResource webResource = client.resource("http://localhost:8080/Survey/WebService/getTdpCadreSurveyDetails/"+tdpCadreId+"/"+surveyId+"/"+searchTypeStr+"/"+boothId+"/"+isPriority+"/"+voterCardNo+"/"+constituencyId+"/"+constiTypeStr+"/"+panchayatId+"/"+mandalId+"/"+localelectionBodyId);
			  ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);
    	 	  if (response.getStatus() != 200) {
    	 		 verifierVO =null;
    	 		  //throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
    	 	 }
    	 	  else
    	 	  {
    	 		 String output = response.getEntity(String.class);
        	 	 if(output != null && !output.isEmpty())
    	   	 	  {
    	   	 		
    	   	 		 JSONObject surveyDetails = new JSONObject(output);
    	   	 		  if(surveyDetails != null)
    	   	 		  {
    	   	 			verifierVO = new VerifierVO();
    	   	 			  if(surveyId != null && surveyId.longValue()>0)
    	   	 			  {
    	   	 				JSONArray  questionsList = null;
    	   	 				  try{
    	   	 					questionsList = surveyDetails.getJSONArray("questionsList");
    	   	 					
		    	   	 				if(questionsList != null && questionsList.length()>0)
		   		   	 			 	{
			   		   	 				resultList = new ArrayList<VerifierVO>(0);
			   		   	 				 for(int i=0;i<questionsList.length();i++)
			   		   	 				 {
			   		   	 					VerifierVO vo =  new VerifierVO();
			   		   	 					JSONObject question = (JSONObject) questionsList.get(i);
			   		   	 					if(question.has("question"))
			   		   	 					{
			   		   	 						vo.setName(question.getString("question"));
			   		   	 					}
				   		   	 				if(question.has("isSampleVerified"))
		       		   	 					{
		       		   	 						vo.setIsSampleVerified(question.getString("isSampleVerified"));
		       		   	 					}
			   			   	 				if(question.has("option"))
			   		   	 					{
			   		   	 						vo.setOption(question.getString("option"));
			   		   	 					}
				    			   	 			if(question.has("verifierVOList"))
						   	 					{
				    			   	 				
				    			   	 			JSONArray  performanceList = question.getJSONArray("verifierVOList");
				    			   	 			if(performanceList != null && performanceList.length()>0)
				    			   	 			{
				    			   	 			List<VerifierVO> questionWiseList = new ArrayList<VerifierVO>(0);
				    			   	 				for(int j=0;j<performanceList.length();j++)
				    			   	 				{
					    			   	 				JSONObject optionObj = (JSONObject) performanceList.get(j);
						    			   	 			VerifierVO optionsVO = new VerifierVO();
						    			   	 			if(optionObj.has("name"))
				        		   	 					{
						    			   	 				optionsVO.setName(optionObj.getString("name"));
				        		   	 					}
							    			   	 		if(optionObj.has("isSampleVerified"))
					       		   	 					{
							    			   	 			optionsVO.setIsSampleVerified(optionObj.getString("isSampleVerified"));
					       		   	 					}
					    			   	 				  if(optionObj != null)
					    			   	 				  {
					    			   	 					    JSONArray  optionsPerformanceList = optionObj.getJSONArray("verifierVOList");
					    			   	 					List<VerifierVO> optionsList = new ArrayList<VerifierVO>(0);
						    			   	 					if(optionsPerformanceList != null && optionsPerformanceList.length()>0)
						    			   	 					{
						    			   	 						for (int k = 0; k < optionsPerformanceList.length(); k++)
						    			   	 						{
						    			   	 							VerifierVO optionVO = new VerifierVO();
						    			   	 							JSONObject optionPerformanceObj = (JSONObject) optionsPerformanceList.get(k);
							    			   	 						if(optionPerformanceObj.has("option"))
								        		   	 					{
								    			   	 						optionVO.setOption(optionPerformanceObj.getString("option"));
								        		   	 					}
									    			   	 				if(optionPerformanceObj.has("optionId") && optionPerformanceObj.getString("optionId") != null )
								        		   	 					{
									    			   	 					optionVO.setOptionId(Long.valueOf(optionPerformanceObj.getString("optionId")));
								        		   	 					}
										    			   	 			if(optionPerformanceObj.has("name"))
								        		   	 					{
										    			   	 				optionVO.setName(optionPerformanceObj.getString("name"));
								        		   	 					}
											    			   	 		if(optionPerformanceObj.has("percentage"))
								        		   	 					{
											    			   	 			optionVO.setPercentage(optionPerformanceObj.getString("percentage"));
								        		   	 					}
											    			   	 		optionsList.add(optionVO);
						    			   	 						}
						    			   	 					}
						    			   	 					
								    			   	 		optionsVO.setVerifierVOList(optionsList);
					    			   	 				  }
					    			   	 				  
					    			   	 				questionWiseList.add(optionsVO);
				    			   	 				}
				    			   	 				vo.setVerifierVOList(questionWiseList);
				    			   	 			}
						   	 						
						   	 					}
			   			   	 				
			   		   	 					resultList.add(vo);
			   		   	 				 }
		   		   	 			 }
		    	   	 				else{
		        	   	 				JSONArray  surveyList = surveyDetails.getJSONArray("verifierVOList");
		       		   	 			 if(surveyList != null && surveyList.length()>0)
		       		   	 			 {
		       		   	 				resultList = new ArrayList<VerifierVO>(0);
		       		   	 				
		       		   	 				 for(int i=0;i<surveyList.length();i++)
		       		   	 				 {
		       		   	 					VerifierVO vo =  new VerifierVO();
		       		   	 					JSONObject question = (JSONObject) surveyList.get(i);
		       		   	 					if(question.has("name"))
		       		   	 					{
		       		   	 						vo.setName(question.getString("name"));
		       		   	 					}
		       			   	 				if(question.has("id"))
		       		   	 					{
		       		   	 						vo.setId(question.getLong("id"));
		       		   	 					}
			       			   	 			if(question.has("isSampleVerified"))
		       		   	 					{
		       		   	 						vo.setIsSampleVerified(question.getString("isSampleVerified"));
		       		   	 					}
		       			   	 				if(question.has("verifierVOList") && !question.isNull("verifierVOList"))
		   				   	 					{
		   		    			   	 				
		   		    			   	 			JSONArray  performanceList = question.getJSONArray("verifierVOList");
		   		    			   	 			if(performanceList != null && performanceList.length()>0)
		   		    			   	 			{
		   		    			   	 			List<VerifierVO> questionWiseList = new ArrayList<VerifierVO>(0);
		   		    			   	 				for(int j=0;j<performanceList.length();j++)
		   		    			   	 				{
		   			    			   	 				JSONObject optionObj = (JSONObject) performanceList.get(j);
		   				    			   	 			VerifierVO optionsVO = new VerifierVO();
		   				    			   	 			if(optionObj.has("question"))
		   		        		   	 					{
		   				    			   	 				optionsVO.setName(optionObj.getString("question"));
		   		        		   	 					}
		   					    			   	 		if(optionObj.has("option"))
		   		        		   	 					{
		   				    			   	 					optionsVO.setOption(optionObj.getString("option"));
			   				    			   	 			if(optionObj.has("isSampleVerified"))
			   			       		   	 					{
			   				    			   	 				optionsVO.setIsSampleVerified(optionObj.getString("isSampleVerified"));
			   			       		   	 					}
		   		        		   	 					}
		   			    			   	 				  if(optionObj != null)
		   			    			   	 				  {
		   			    			   	 					    JSONArray  optionsPerformanceList = optionObj.getJSONArray("verifierVOList");
		   			    			   	 					List<VerifierVO> optionsList = new ArrayList<VerifierVO>(0);
		   				    			   	 					if(optionsPerformanceList != null && optionsPerformanceList.length()>0)
		   				    			   	 					{
		   				    			   	 						for (int k = 0; k < optionsPerformanceList.length(); k++)
		   				    			   	 						{
		   				    			   	 							VerifierVO locationVO = new VerifierVO();
		   				    			   	 							JSONObject optionPerformanceObj = (JSONObject) optionsPerformanceList.get(k);
		   				    			   	 							if(optionPerformanceObj != null)
		   				    			   	 							{
		   				    			   	 								
		   					    			   	 							if(optionPerformanceObj.has("name"))
		   							        		   	 					{
		   					    			   	 								locationVO.setName(optionPerformanceObj.getString("name"));
		   							        		   	 					}
		   				    			   	 							
		   				    			   	 								JSONArray  locationWiseOptionPerfObj = optionPerformanceObj.getJSONArray("verifierVOList");
		   				    			   	 								if(locationWiseOptionPerfObj != null && locationWiseOptionPerfObj.length()>0)
		   				    			   	 								{
		   				    			   	 									List<VerifierVO> optionsVOList = new ArrayList<VerifierVO>(0);
		   				    			   	 									for (int s = 0; s < locationWiseOptionPerfObj.length(); s++) {
		   				    			   	 										VerifierVO optionVO = new VerifierVO();
		   				    			   	 										JSONObject optiionObj =(JSONObject) locationWiseOptionPerfObj.get(s);
		   				    			   	 										if(optiionObj != null)
		   				    			   	 										{
		   					    			   	 										if(optiionObj.has("option"))
		   										        		   	 					{
		   										    			   	 						optionVO.setOption(optiionObj.getString("option"));
		   										        		   	 					}
		   											    			   	 				if(optiionObj.has("optionId") && optiionObj.getString("optionId") != null )
		   										        		   	 					{
		   											    			   	 					optionVO.setOptionId(Long.valueOf(optiionObj.getString("optionId")));
		   										        		   	 					}
		   												    			   	 			
		   													    			   	 		if(optiionObj.has("percentage"))
		   										        		   	 					{
		   													    			   	 			optionVO.setPercentage(optiionObj.getString("percentage"));
		   										        		   	 					}
		   				    			   	 										}
		   				    			   	 										optionsVOList.add(optionVO);
		   																		}
		   				    			   	 								locationVO.setVerifierVOList(optionsVOList);
		   				    			   	 								}
		   				    			   	 							}
		   					    			   	 						
		   									    			   	 		optionsList.add(locationVO);
		   				    			   	 						}
		   				    			   	 					}
		   				    			   	 					
		   						    			   	 		optionsVO.setVerifierVOList(optionsList);
		   			    			   	 				  }
		   			    			   	 				  
		   			    			   	 				questionWiseList.add(optionsVO);
		   		    			   	 				}
		   		    			   	 				vo.setVerifierVOList(questionWiseList);
		   		    			   	 			}
		   				   	 						
		   				   	 					}
		       		   	 					resultList.add(vo);
		       		   	 				 }
		       		   	 			 }
		       		       	   	 		verifierVO.setCount(surveyDetails.getLong("count"));
		       		       	   	 		verifierVO.setTotalCount(surveyDetails.getLong("totalCount"));
		       		       	   	 		verifierVO.setIsVerified(surveyDetails.getString("isVerified"));
		       	   	 			  }
    	   	 				  }
    	   	 				  catch(Exception e){
    	   	 					  
    	   	 					questionsList = surveyDetails.getJSONArray("verifierVOList");
		    	   	 				if(questionsList != null && questionsList.length()>0)
		    	   	 				{
		   		   	 				resultList = new ArrayList<VerifierVO>(0);
		   		   	 				 for(int i=0;i<questionsList.length();i++)
		   		   	 				 {
		   		   	 					VerifierVO vo =  new VerifierVO();
		   		   	 					JSONObject question = (JSONObject) questionsList.get(i);
		   		   	 					if(question.has("question"))
		   		   	 					{
		   		   	 						vo.setName(question.getString("question"));
		   		   	 					}
		   			   	 				if(question.has("option"))
		   		   	 					{
		   		   	 						vo.setOption(question.getString("option"));
		   		   	 					}
			   			   	 			if(question.has("isSampleVerified"))
	       		   	 					{
			   			   	 				vo.setIsSampleVerified(question.getString("isSampleVerified"));
	       		   	 					}
			    			   	 			if(question.has("verifierVOList"))
					   	 					{
			    			   	 				
			    			   	 			JSONArray  performanceList = question.getJSONArray("verifierVOList");
			    			   	 			if(performanceList != null && performanceList.length()>0)
			    			   	 			{
			    			   	 			List<VerifierVO> questionWiseList = new ArrayList<VerifierVO>(0);
			    			   	 				for(int j=0;j<performanceList.length();j++)
			    			   	 				{
				    			   	 				JSONObject optionObj = (JSONObject) performanceList.get(j);
					    			   	 			VerifierVO optionsVO = new VerifierVO();
					    			   	 			if(optionObj.has("question"))
			        		   	 					{
					    			   	 				optionsVO.setName(optionObj.getString("question"));
			        		   	 					}
						    			   	 		if(optionObj.has("option"))
			        		   	 					{
						    			   	 			optionsVO.setOption(optionObj.getString("option"));
			        		   	 					}
							    			   	 	if(optionObj.has("isSampleVerified"))
				       		   	 					{
						    			   	 			optionsVO.setIsSampleVerified(optionObj.getString("isSampleVerified"));
				       		   	 					}
				    			   	 				 		    			   	 				  
				    			   	 				questionWiseList.add(optionsVO);
			    			   	 				}
			    			   	 				vo.setVerifierVOList(questionWiseList);
			    			   	 			}
					   	 						
					   	 					}
		   			   	 				
		   		   	 					resultList.add(vo);
		   		   	 				 }
		   		   	 			 }
    	   	 				  }
    		   	 			 
    		   	 			 
    	   	 			  }
    	   	 			  else
    	   	 			  {
    	   	 				JSONArray  surveyList = surveyDetails.getJSONArray("verifierVOList");
    		   	 			 if(surveyList != null && surveyList.length()>0)
    		   	 			 {
    		   	 				resultList = new ArrayList<VerifierVO>(0);
    		   	 				
    		   	 				 for(int i=0;i<surveyList.length();i++)
    		   	 				 {
    		   	 					VerifierVO vo =  new VerifierVO();
    		   	 					JSONObject question = (JSONObject) surveyList.get(i);
    		   	 					if(question.has("name"))
    		   	 					{
    		   	 						vo.setName(question.getString("name"));
    		   	 					}
    			   	 				if(question.has("id"))
    		   	 					{
    		   	 						vo.setId(question.getLong("id"));
    		   	 					}
	    			   	 			if(question.has("isSampleVerified"))
	   		   	 					{
	    			   	 				vo.setIsSampleVerified(question.getString("isSampleVerified"));
	   		   	 					}
    			   	 				if(question.has("verifierVOList") && !question.isNull("verifierVOList"))
				   	 					{
		    			   	 				
		    			   	 			JSONArray  performanceList = question.getJSONArray("verifierVOList");
		    			   	 			if(performanceList != null && performanceList.length()>0)
		    			   	 			{
		    			   	 			List<VerifierVO> questionWiseList = new ArrayList<VerifierVO>(0);
		    			   	 				for(int j=0;j<performanceList.length();j++)
		    			   	 				{
			    			   	 				JSONObject optionObj = (JSONObject) performanceList.get(j);
				    			   	 			VerifierVO optionsVO = new VerifierVO();
				    			   	 			if(optionObj.has("question"))
		        		   	 					{
				    			   	 				optionsVO.setName(optionObj.getString("question"));
		        		   	 					}
					    			   	 		if(optionObj.has("option"))
		        		   	 					{
				    			   	 				optionsVO.setOption(optionObj.getString("option"));
		        		   	 					}
						    			   	 	if(optionObj.has("isSampleVerified"))
			       		   	 					{
					    			   	 			optionsVO.setIsSampleVerified(optionObj.getString("isSampleVerified"));
			       		   	 					}
			    			   	 				  if(optionObj != null)
			    			   	 				  {
			    			   	 					    JSONArray  optionsPerformanceList = optionObj.getJSONArray("verifierVOList");
			    			   	 					List<VerifierVO> optionsList = new ArrayList<VerifierVO>(0);
				    			   	 					if(optionsPerformanceList != null && optionsPerformanceList.length()>0)
				    			   	 					{
				    			   	 						for (int k = 0; k < optionsPerformanceList.length(); k++)
				    			   	 						{
				    			   	 							VerifierVO locationVO = new VerifierVO();
				    			   	 							JSONObject optionPerformanceObj = (JSONObject) optionsPerformanceList.get(k);
				    			   	 							if(optionPerformanceObj != null)
				    			   	 							{
				    			   	 								
					    			   	 							if(optionPerformanceObj.has("name"))
							        		   	 					{
					    			   	 								locationVO.setName(optionPerformanceObj.getString("name"));
							        		   	 					}
				    			   	 							
				    			   	 								JSONArray  locationWiseOptionPerfObj = optionPerformanceObj.getJSONArray("verifierVOList");
				    			   	 								if(locationWiseOptionPerfObj != null && locationWiseOptionPerfObj.length()>0)
				    			   	 								{
				    			   	 									List<VerifierVO> optionsVOList = new ArrayList<VerifierVO>(0);
				    			   	 									for (int s = 0; s < locationWiseOptionPerfObj.length(); s++) {
				    			   	 										VerifierVO optionVO = new VerifierVO();
				    			   	 										JSONObject optiionObj =(JSONObject) locationWiseOptionPerfObj.get(s);
				    			   	 										if(optiionObj != null)
				    			   	 										{
					    			   	 										if(optiionObj.has("option"))
										        		   	 					{
										    			   	 						optionVO.setOption(optiionObj.getString("option"));
										        		   	 					}
											    			   	 				if(optiionObj.has("optionId") && optiionObj.getString("optionId") != null )
										        		   	 					{
											    			   	 					optionVO.setOptionId(Long.valueOf(optiionObj.getString("optionId")));
										        		   	 					}
												    			   	 			
													    			   	 		if(optiionObj.has("percentage"))
										        		   	 					{
													    			   	 			optionVO.setPercentage(optiionObj.getString("percentage"));
										        		   	 					}
				    			   	 										}
				    			   	 										optionsVOList.add(optionVO);
																		}
				    			   	 								locationVO.setVerifierVOList(optionsVOList);
				    			   	 								}
				    			   	 							}
					    			   	 						
									    			   	 		optionsList.add(locationVO);
				    			   	 						}
				    			   	 					}
				    			   	 					
						    			   	 		optionsVO.setVerifierVOList(optionsList);
			    			   	 				  }
			    			   	 				  
			    			   	 				questionWiseList.add(optionsVO);
		    			   	 				}
		    			   	 				vo.setVerifierVOList(questionWiseList);
		    			   	 			}
				   	 						
				   	 					}
    		   	 					resultList.add(vo);
    		   	 				 }
    		   	 			 }
    		       	   	 		verifierVO.setCount(surveyDetails.getLong("count"));
    		       	   	 		verifierVO.setTotalCount(surveyDetails.getLong("totalCount"));
    		       	   	 		verifierVO.setIsVerified(surveyDetails.getString("isVerified"));
    	   	 			  }
    	   	 			  
    	   	 			if(resultList != null && resultList.size()>0)
	  	       	   	 	  {
	  	       	   	 		verifierVO.setVerifierVOList(resultList);
	  	       	   	 	  }
    	   	 		  }
    	   	 	  }
    	 	  }
    	 	 
    	 	 
		} catch (Exception e) {
			log.debug("Entered into the getTdpCadreSurveyDetails  method in WebServiceHandlerService");
		}
		  return verifierVO;
	  }
	  
	  public void segrigateSurveyQuestionwiseReport(JSONObject surveyDetails,List<VerifierVO> resultList){
		  try {
			  		JSONArray  questionsList = null;
					questionsList = surveyDetails.getJSONArray("verifierVOList");
 	 				if(questionsList != null && questionsList.length()>0)
 	 				{
	   	 				resultList = new ArrayList<VerifierVO>(0);
	   	 				 for(int i=0;i<questionsList.length();i++)
	   	 				 {
	   	 					VerifierVO vo =  new VerifierVO();
	   	 					JSONObject question = (JSONObject) questionsList.get(i);
	   	 					if(question.has("question"))
	   	 					{
	   	 						vo.setName(question.getString("question"));
	   	 					}
		   	 				if(question.has("option"))
	   	 					{
	   	 						vo.setOption(question.getString("option"));
	   	 					}
			   	 			if(question.has("verifierVOList"))
	   	 					{
			   	 				
			   	 			JSONArray  performanceList = question.getJSONArray("verifierVOList");
			   	 			if(performanceList != null && performanceList.length()>0)
			   	 			{
			   	 			List<VerifierVO> questionWiseList = new ArrayList<VerifierVO>(0);
			   	 				for(int j=0;j<performanceList.length();j++)
			   	 				{
  			   	 				JSONObject optionObj = (JSONObject) performanceList.get(j);
	    			   	 			VerifierVO optionsVO = new VerifierVO();
	    			   	 			if(optionObj.has("question"))
  		   	 					{
	    			   	 				optionsVO.setName(optionObj.getString("question"));
  		   	 					}
		    			   	 		if(optionObj.has("option"))
  		   	 					{
		    			   	 			optionsVO.setOption(optionObj.getString("option"));
  		   	 					}
  			   	 				 		    			   	 				  
  			   	 				questionWiseList.add(optionsVO);
			   	 				}
			   	 				vo.setVerifierVOList(questionWiseList);
			   	 			}
	   	 						
	   	 					}
		   	 				
	   	 					resultList.add(vo);
	   	 				 }
	   	 			 }
				  
		} catch (Exception e) {
			// TODO: handle exception
		}
	  }
	  public void segrigateSurveyDetails(JSONObject surveyDetails,List<VerifierVO> resultList){
		  try {

	 				JSONArray  surveyList = surveyDetails.getJSONArray("verifierVOList");
	 			 if(surveyList != null && surveyList.length()>0)
	 			 {
	 				resultList = new ArrayList<VerifierVO>(0);
	 				
	 				 for(int i=0;i<surveyList.length();i++)
	 				 {
	 					VerifierVO vo =  new VerifierVO();
	 					JSONObject question = (JSONObject) surveyList.get(i);
	 					if(question.has("name"))
	 					{
	 						vo.setName(question.getString("name"));
	 					}
	   	 				if(question.has("id"))
	 					{
	 						vo.setId(question.getLong("id"));
	 					}
	   	 				
	   	 				if(question.has("verifierVOList") && !question.isNull("verifierVOList"))
	   	 					{
			   	 				
			   	 			JSONArray  performanceList = question.getJSONArray("verifierVOList");
			   	 			if(performanceList != null && performanceList.length()>0)
			   	 			{
			   	 			List<VerifierVO> questionWiseList = new ArrayList<VerifierVO>(0);
			   	 				for(int j=0;j<performanceList.length();j++)
			   	 				{
 			   	 				JSONObject optionObj = (JSONObject) performanceList.get(j);
	    			   	 			VerifierVO optionsVO = new VerifierVO();
	    			   	 			if(optionObj.has("question"))
 		   	 					{
	    			   	 				optionsVO.setName(optionObj.getString("question"));
 		   	 					}
		    			   	 		if(optionObj.has("option"))
 		   	 					{
	    			   	 				optionsVO.setOption(optionObj.getString("option"));
 		   	 					}
 			   	 				  if(optionObj != null)
 			   	 				  {
 			   	 					    JSONArray  optionsPerformanceList = optionObj.getJSONArray("verifierVOList");
 			   	 					List<VerifierVO> optionsList = new ArrayList<VerifierVO>(0);
	    			   	 					if(optionsPerformanceList != null && optionsPerformanceList.length()>0)
	    			   	 					{
	    			   	 						for (int k = 0; k < optionsPerformanceList.length(); k++)
	    			   	 						{
	    			   	 							VerifierVO locationVO = new VerifierVO();
	    			   	 							JSONObject optionPerformanceObj = (JSONObject) optionsPerformanceList.get(k);
	    			   	 							if(optionPerformanceObj != null)
	    			   	 							{
	    			   	 								
		    			   	 							if(optionPerformanceObj.has("name"))
				        		   	 					{
		    			   	 								locationVO.setName(optionPerformanceObj.getString("name"));
				        		   	 					}
	    			   	 							
	    			   	 								JSONArray  locationWiseOptionPerfObj = optionPerformanceObj.getJSONArray("verifierVOList");
	    			   	 								if(locationWiseOptionPerfObj != null && locationWiseOptionPerfObj.length()>0)
	    			   	 								{
	    			   	 									List<VerifierVO> optionsVOList = new ArrayList<VerifierVO>(0);
	    			   	 									for (int s = 0; s < locationWiseOptionPerfObj.length(); s++) {
	    			   	 										VerifierVO optionVO = new VerifierVO();
	    			   	 										JSONObject optiionObj =(JSONObject) locationWiseOptionPerfObj.get(s);
	    			   	 										if(optiionObj != null)
	    			   	 										{
		    			   	 										if(optiionObj.has("option"))
							        		   	 					{
							    			   	 						optionVO.setOption(optiionObj.getString("option"));
							        		   	 					}
								    			   	 				if(optiionObj.has("optionId") && optiionObj.getString("optionId") != null )
							        		   	 					{
								    			   	 					optionVO.setOptionId(Long.valueOf(optiionObj.getString("optionId")));
							        		   	 					}
									    			   	 			
										    			   	 		if(optiionObj.has("percentage"))
							        		   	 					{
										    			   	 			optionVO.setPercentage(optiionObj.getString("percentage"));
							        		   	 					}
	    			   	 										}
	    			   	 										optionsVOList.add(optionVO);
															}
	    			   	 								locationVO.setVerifierVOList(optionsVOList);
	    			   	 								}
	    			   	 							}
		    			   	 						
						    			   	 		optionsList.add(locationVO);
	    			   	 						}
	    			   	 					}
	    			   	 					
			    			   	 		optionsVO.setVerifierVOList(optionsList);
 			   	 				  }
 			   	 				  
 			   	 				questionWiseList.add(optionsVO);
			   	 				}
			   	 				vo.setVerifierVOList(questionWiseList);
			   	 			}
	   	 						
	   	 					}
	 					resultList.add(vo);
	 				 }
	 			 }
 			  
		} catch (Exception e) {
			// TODO: handle exception
		}
	  }
	  public ResultStatus verifyEventSyncData(UserEventDetailsVO inputVO)
	  {
		  ResultStatus resultStatus = new ResultStatus();
		  List list = null;
		  try{
			 list = eventAttendeeErrorDAO.checkEventsyncDataInError(inputVO.getRFID(),inputVO.getIMEI(),inputVO.getEventId(),inputVO.getUniqueKey());
			 if(list == null || list.size() == 0)
				 list = eventAttendeeDAO.checkEventsyncData(inputVO.getRFID(),inputVO.getIMEI(),inputVO.getEventId(),inputVO.getUniqueKey());
			 if(list == null || list.size() == 0)
				 resultStatus.setMessage("Not Inserted");
			 else
				 resultStatus.setMessage("Inserted"); 
			  
		  }
		  catch (Exception e) {
			  log.debug("Entered into the verifyEventSyncData  method in WebServiceHandlerService");
			  resultStatus.setMessage("Exception"); 
		}
		return resultStatus;
	  }
	  public WebServiceResultVO getCandidateAndLocationSummaryNews(String startDate,String endDate,String locationType,Long locationId,Long candidateId){
		  
		  WebServiceResultVO webserviceResultVO=new WebServiceResultVO();
		  try {

				Client client = Client.create();

				//WebResource webResource = client.resource("https://localhost:8080/PartyAnalyst/WebService/getMobileAppAuthorizationURL");
				//WebResource webResource = client.resource("https://localhost:8080/CommunityNewsPortal/webservice/getCandidateAndLocationSummary/15-07-2015/15-07-2015/district/13/44");
				WebResource webResource = client.resource("https://mytdp.com/CommunityNewsPortal/webservice/getCandidateAndLocationSummary/"+startDate+"/"+endDate+"/"+locationType+"/"+locationId+"/"+candidateId+"");
				
				ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);

				if (response.getStatus() != 200) {
				/*	throw new RuntimeException("Failed : HTTP error code : "
							+ response.getStatus());*/
					webserviceResultVO=null;
				}

				String output = response.getEntity(String.class);
				JSONObject temp = new JSONObject(output);
				
				List<String> deptsList = new ArrayList<String>();
				
				//ITERATE DEPT SUMMARY
				JSONArray deptSummary = temp.getJSONArray("departmentSummary");
				//Integer totalCount = temp2.getInt("totalCount");
				JSONObject jObjFirst= (JSONObject)deptSummary.get(0);
				int totalCount = jObjFirst.getInt("totalCount");
				for (int i = 0; i < deptSummary.length(); i++){
					JSONObject jObj= (JSONObject)deptSummary.get(i);
					String partyName = jObj.getString("partyName");
					int count =jObj.getInt("count");
					deptsList.add(partyName);
					webserviceResultVO.setName(partyName);
				}
				
				
				//ITERATE CANDIDATE SUMMARY
				JSONObject temp2 = temp.getJSONObject("candidateSummary");
				int candiSummaryTtlCnt = temp2.getInt("totalCount");
				int candiSummaryPosCnt = temp2.getInt("positiveCount");
				int candiSummaryNegCnt = temp2.getInt("negativeCount");
				int candiSummarySmryCnt = temp2.getInt("neutralCount");
				
				

			/*	System.out.println("Output from Server .... \n");
				System.out.println(output);*/
				/*WebServiceInputVO inputVO = new WebServiceInputVO();
				inputVO.setPartyId(22l);
				getArticlesBasedOnSelection(inputVO);*/
 
				
			} catch (Exception e) {
				 log.debug("Entered into the getCandidateAndLocationSummaryNews  method in WebServiceHandlerService");
			}
		  	return webserviceResultVO;
	  }
	  
	  public CadreOverviewVO getTdpCadreOverViewDetails(CadreOverviewVO inputVO)
	  {
		  CadreOverviewVO returnVO = null;
		  try {
			  returnVO = cadreDetailsService.getTdpcadreDetailsByTdpCadreId(inputVO.getCadreId(),inputVO.getHouseNo(),inputVO.getVoterCardNo());
		} catch (Exception e) {
			 log.debug("Entered into the getTdpCadreOverViewDetails  method in WebServiceHandlerService");
		}
		  return returnVO;
	  }
	  
	  public PartyMeetingVO getPartyMeetingsForCadrePeople(Long tdpCadreId)
	  {
		  try {
			  
		//	return  partyMeetingService.getPartyMeetingsForCadrePeople(tdpCadreId);
			  return  partyMeetingService.getPartyMeetingDetailsBySearchType(tdpCadreId);
					
		} catch (Exception e) {
			log.debug("Entered into the getTdpCadreOverViewDetails  method in WebServiceHandlerService");
		}
		  return null;
	  }
	  
	  public PartyMeetingVO getPartyMeetingsForCadreOverview(Long tdpCadreId)
	  {
		  try {
			  
			return  partyMeetingService.getPartyMeetingsForCadrePeople(tdpCadreId);
					
		} catch (Exception e) {
			log.debug("Entered into the getTdpCadreOverViewDetails  method in WebServiceHandlerService");
		}
		  return null;
	  }
	 public PartyMeetingVO getParticipatedCandidateEventDetails(Long tdpCadreId)
	 {
		  try {
			  return  mahaNaduService.getParticipatedCandidateEventDetails(tdpCadreId);
		} catch (Exception e) {
			log.debug("Entered into the getTdpCadreOverViewDetails  method in WebServiceHandlerService");
		}
		  return null;
	  }
	 
	 public List<CadreCommitteeMemberVO> getEventDetailsOfCadre(Long tdpCadreId)
	 {
	  try {
		  return  cadreDetailsService.getEventDetailsOfCadre(tdpCadreId);
		} catch (Exception e) {
			log.debug("Entered into the getTdpCadreOverViewDetails  method in WebServiceHandlerService");
		}
		  return null;
	}
		 
	 public PartyMeetingVO getMeetingTypeWiseDescription(Long tdpCadreId,Long partyMeetingTypeId)
	 {
		  try {
			  return  partyMeetingService.getMeetingTypeWiseDescription(partyMeetingTypeId,tdpCadreId);
		} catch (Exception e) {
			log.debug("Entered into the getMeetingTypeWiseDescription  method in WebServiceHandlerService");
		}
		  return null;
	  }
	  
	 public List<RegisteredMembershipCountVO> getElectionPerformanceInCadreLocation(Long tdpCadreId,String voterCardNo){
		 try {
			  return  cadreDetailsService.getElectionPerformanceInCadreLocation(tdpCadreId,voterCardNo);
		} catch (Exception e) {
			log.debug("Entered into the getElectionPerformanceInCadreLocation  method in WebServiceHandlerService");
		}
		  return null;
	 }
	 public NtrTrustStudentVO getNtrTrustStudentDetailsInstitutionWise(List<Long> tdpCadreIds){
		 try {
			  return  cadreDetailsService.getNtrTrustStudentDetailsInstitutionWise(tdpCadreIds);
		} catch (Exception e) {
			log.debug("Entered into the getNtrTrustStudentDetailsInstitutionWise  method in WebServiceHandlerService");
		}
		  return null;
		 
	 }
	 public List<NtrTrustStudentVO> getStudentFormalDetailsByCadre(List<Long> tdpCadreids,Long institutionId){
		 try {
			  return  cadreDetailsService.getStudentFormalDetailsByCadre(tdpCadreids,institutionId);
		} catch (Exception e) {
			log.debug("Entered into the getStudentFormalDetailsByCadre  method in WebServiceHandlerService");
		}
		  return null;
		 
	 }

	 
	 public SurveyTrainingsVO getAllRecordsOfCampProgramScheduleAndBatch(Long campId, Long programId, Long scheduleId, Long batchId){
		 try {
			  return  trainingCampService.getAllRecordsOfCampProgramScheduleAndBatch( campId,  programId,  scheduleId,  batchId);
		} catch (Exception e) {
			log.debug("Entered into the getAllRecordsOfCampProgramScheduleAndBatch  method in WebServiceHandlerService");
		}
		  return null;		 
	 }
	 
	 public List<Long> getAllRemovedCadre(){
		 try {
			  return  cadreCommitteeService.getAllRemovedCadre();
		} catch (Exception e) {
			log.debug("Entered into the getAllRemovedCadre  method in WebServiceHandlerService");
		}
		 return null;
	 }
	 public List<Long> getTdpCadreMemberShipsIdsByEvent(Long eventId){
		 List<Long> membershipIdsList = new ArrayList<Long>(0);
		 try{
			 if(eventId != null && eventId.longValue()>0L)
			 {
				 Event event = eventDAO.get(eventId);
				 if(event != null && event.getParentEventId() != null && event.getParentEventId().longValue()>0L){
					 List<String> membershipNoList = eventInviteeDAO.getTdpCadreMemberShipsIdsByEvent(event.getParentEventId());
					 for (String membershipNo : membershipNoList) {
						 membershipIdsList.add(Long.valueOf(membershipNo));
					}
				 }
			 }
		 }catch (Exception e) {
		 log.debug("Entered into the getTdpCadreMemberShipsIdsByEvent method in WebServiceHandlerService");
		 }
		 return membershipIdsList;
		 }
	 
	   public List<TdpCadreVO>  getWebServiceEventInviteesList(Long userId,String accessLevel,String accessValue, Long stateId,List<InviteesVO> inviteesVOList,Long eventId,String actionType,String stateStr,String reportType, Integer startIndex,Integer maxIndex)
	    {
	    	List<TdpCadreVO> returnList = null;
	    	try {
	    		returnList = cadreCommitteeService.getEventInviteesList(userId, accessLevel, accessValue, stateId, inviteesVOList, eventId, actionType, stateStr, reportType, startIndex, maxIndex);
			} catch (Exception e) {
				Log.error("Exception raised in getWebServiceEventInviteesList  method in WebServiceHandlerService",e);
			}
	    	return returnList;
	    }
	   
	   public ActivityLoginVO checkActivityTabUserLogin(String userName, String password){
		   Log.debug("Entered into getActivityLoginChecker Webservice method");
		   ActivityLoginVO activityLoginVO = new ActivityLoginVO();
		   try{
			   activityLoginVO = activityService.checkActivityTabUserLogin(userName, password);
		   }catch (Exception e) {
			   Log.error("Exception in getActivityLoginChecker Webservice method");
		   }
		   return activityLoginVO;
	   }
	   
	   public ActivityWSVO getUserActivityDetailsByUserId(Long userId){
		   Log.debug("Entered into getUserActivityDetailsByUserId Webservice method");
		   ActivityWSVO activityVO = new ActivityWSVO();
		   try{
			   activityVO = activityService.getUserActivityDetailsByUserId(userId);
		   }catch (Exception e) {
			   Log.error("Exception in getUserActivityDetailsByUserId Webservice method");
		   }
		   return activityVO;
	   }
	   
	   public TdpCadreWSVO getActivityCadreDetails(String memberShipNo, String voterCardNo){
		   Log.debug("Entered into getActivityCadreDetails Webservice method");
		   TdpCadreWSVO tdpCadreWSVO = new TdpCadreWSVO();
		   try{
			   tdpCadreWSVO = activityService.getCadreDetailsByVoterIdorMemberShipNo(memberShipNo, voterCardNo);
		   }catch (Exception e) {
			   Log.error("Exception in getActivityCadreDetails Webservice method");
		   }
		   return tdpCadreWSVO;
	   }
	   public ImageVO saveActivitiesImages(ImageVO inputVO)
	   {
		   Log.debug("Entered into saveActivitiesImages Webservice method");
		   ImageVO imageVO = new ImageVO();
		   try{
			   if(inputVO != null && inputVO.getBase64ImageStr() != null && !inputVO.getBase64ImageStr().trim().isEmpty() && 
					   inputVO.getActivityLocationInfoId() != null && inputVO.getActivityLocationInfoId().longValue()>0L)
			   {
				   Date activityDate = null;
				   if(inputVO.getUploadTime() != null && !inputVO.getUploadTime().equals("undefined"))
					{
						//SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
					   SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					    try {
					    	 activityDate = dateFormat.parse(inputVO.getUploadTime());
						} catch (Exception e) {
							Log.error(" Exception Occured Date Formatting method, Exception - ",e);	
						}
					}
				   
				    TabDetailsVO tabDetals = new TabDetailsVO();
					
					tabDetals.setAttendedTime(activityDate);
					tabDetals.setImei(inputVO.getImei());
					tabDetals.setUniqueKey(inputVO.getUniqueKey());
					tabDetals.setInsertedTime(inputVO.getInsertedTime());
					tabDetals.setLatitude(inputVO.getLatitude());
					tabDetals.setLongitude(inputVO.getLongitude());
					tabDetals.setTabUserId(inputVO.getTabUserId());
					tabDetals.setSyncSource(inputVO.getSyncSource());
					tabDetals.setInsertedBy(inputVO.getInsertedBy());
					tabDetals.setTabPrimaryKey(inputVO.getTabPrimaryKey());
					
					Long tabDetailsId = activityService.savingTabDetails(tabDetals);
					
					if(tabDetailsId != null && tabDetailsId.longValue()>0L)
					{
						  EventFileUploadVO eventFileUploadVO = new EventFileUploadVO();
						  eventFileUploadVO.setInsertType("WS");
						  eventFileUploadVO.setImageBase64String(inputVO.getBase64ImageStr());
						  eventFileUploadVO.setLevelId(inputVO.getLevelId());
						  eventFileUploadVO.setLevelValue(inputVO.getLevelValue());
						  eventFileUploadVO.setActivityDateFormat(activityDate);
						  eventFileUploadVO.setActivityScopeId(inputVO.getActivityScopeId());
						  eventFileUploadVO.setActivityLocationInfoId(inputVO.getActivityLocationInfoId());
						  eventFileUploadVO.setTabDetailsId(tabDetailsId);
						  ResultStatus resultStatus = activityService.eventsUploadForm(eventFileUploadVO);
						  if(resultStatus != null){
							  if(resultStatus.getResultCode() ==0){
								  imageVO.setStatus("SUCCESS");
								  imageVO.setWebPrimaryKey(resultStatus.getResultState());
								  imageVO.setTabPrimaryKey(inputVO.getTabPrimaryKey());
							  }
							  else
								  imageVO.setStatus("FAILURE");
						  }
					}
					
					try {
						 ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
						   String json = ow.writeValueAsString(inputVO);
						   
						   ActivityTabRequestBackup activityTabRequestBackup = new ActivityTabRequestBackup();
						   activityTabRequestBackup.setUserId(inputVO.getTabUserId());
						   activityTabRequestBackup.setImeiNo(inputVO.getImei());
						   activityTabRequestBackup.setUniqueCode(inputVO.getUniqueKey());
						   activityTabRequestBackup.setInsertedTime(new DateUtilService().getCurrentDateAndTime());
						   activityTabRequestBackup.setJsonArr(json);
						   activityTabRequestBackup.setApkPrimaryKey(inputVO.getTabPrimaryKey());
						   
						   activityTabRequestBackupDAO.save(activityTabRequestBackup);
					} catch (Exception e) {
						Log.error("Exception in saveActivitiesImages Webservice method");
					}
			   }
			   else
			   {
				   imageVO.setStatus("FAILURE");
				   imageVO.setName(" Image is not available... ");
			   }
		   }catch (Exception e) {
			   Log.error("Exception in saveActivitiesImages Webservice method");
			   imageVO.setStatus("FAILURE");
		   }
		   return imageVO;
	   }
	   public ResultStatus savePublicActivityAttendance(ActivityAttendanceVO inputVO){
		   ResultStatus rs = new ResultStatus();
		   try {
			   Log.debug(" entered into savePublicActivityAttendance webservice");
			   rs = attendanceService.savePublicActivityAttendance(inputVO,inputVO.getTabUserId());
			   
			   try {

				   ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
				   String json = ow.writeValueAsString(inputVO);
				   
				   ActivityTabRequestBackup activityTabRequestBackup = new ActivityTabRequestBackup();
				   activityTabRequestBackup.setUserId(inputVO.getTabUserId());
				   activityTabRequestBackup.setImeiNo(inputVO.getImei());
				   activityTabRequestBackup.setUniqueCode(inputVO.getUnqueKey());
				   activityTabRequestBackup.setInsertedTime(new DateUtilService().getCurrentDateAndTime());
				   activityTabRequestBackup.setJsonArr(json);
				   activityTabRequestBackup.setApkPrimaryKey(inputVO.getTabPrimaryKey());
				   
				   activityTabRequestBackupDAO.save(activityTabRequestBackup);
				} catch (Exception e) {
					Log.error("Exception in saveActivitiesImages Webservice method");
				}
			   
		   } catch (Exception e) {
			
		   }
		   return rs;
	   }
	   
	   public ResultStatus saveActivityQuestionAnswer(AttendanceQuestionnariWSVO inputVO){
		   ResultStatus rs = new ResultStatus();
		   try {
				Log.info("entered into saveActivityQuestionAnswer");
				rs = attendanceService.saveActivityQuestionAnswer(inputVO);
				
				try {

					   ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
					   String json = ow.writeValueAsString(inputVO);
					   
					   ActivityTabRequestBackup activityTabRequestBackup = new ActivityTabRequestBackup();
					   activityTabRequestBackup.setUserId(inputVO.getTabUserId());
					   activityTabRequestBackup.setImeiNo(inputVO.getImei());
					   activityTabRequestBackup.setUniqueCode(inputVO.getUniqueKey());
					   activityTabRequestBackup.setInsertedTime(new DateUtilService().getCurrentDateAndTime());
					   activityTabRequestBackup.setJsonArr(json);
					   activityTabRequestBackup.setApkPrimaryKey(inputVO.getTabPrimaryKey());
					   
					   activityTabRequestBackupDAO.save(activityTabRequestBackup);
					} catch (Exception e) {
						Log.error("Exception in saveActivitiesImages Webservice method");
					}
				   
			} catch (Exception e) {
				Log.error("exception riased at saveActivityQuestionAnswer", e);
			}
		   return rs;
	   }
	   
	   public  List<MobileAppUserVO> checkMobileAppUser(MobileAppUserVO inputVO)
	   {
		   List<MobileAppUserVO> returnList = new ArrayList<MobileAppUserVO>();
		   try {
		   Log.info("Entered into checkMobileAppUser ");
		   DateUtilService date = new DateUtilService();
		  
		   List<MobileAppUser> list =  mobileAppUserDAO.checkMobileAppUser(inputVO.getUname(),inputVO.getPwd());
		   if(list == null || list.size() == 0)
		   {
			   MobileAppUserVO returnVO = new MobileAppUserVO();
			   returnVO.setMessage("Invalid");
			   returnList.add(returnVO);
			   return returnList;
		   }
		  
		   for(MobileAppUser user : list)
		   {
			   MobileAppUserVO Vo = new MobileAppUserVO();
			   Vo.setUniqueCode(user.getUniqueCode());
			   Vo.setMobileAppUserId(user.getMobileAppUserId());
			   Vo.setMobileNum(user.getMobileNo());
			   Vo.setUname(user.getUserName());
			   Vo.setPwd(user.getPassword());
			   returnList.add(Vo);
			   MobileAppUser mobileAppUser = mobileAppUserDAO.get(user.getMobileAppUserId());
			   mobileAppUser.setLastLoginTime(date.getCurrentDateAndTime());
			   mobileAppUserDAO.save(mobileAppUser);
		   }
		  
		   }
		   catch (Exception e) {
				Log.error("exception riased at checkMobileAppUser", e);
				 
			}
		   return returnList;
	   }
	   
	   public List<MobileAppUserVO> getMobileAppUserSmsDetails(MobileAppUserVO inputVO)
	   {
		   List<MobileAppUserVO> returnList = new ArrayList<MobileAppUserVO>();
		   try {
		   Log.info("Entered into getMobileAppUserSmsDetails ");
		   DateUtilService date = new DateUtilService();
		  
		   List<MobileAppUserSmsDetails> list =  mobileAppUserSmsDetailsDAO.getMobileAppUserSmsDetails(inputVO.getMobileAppUserId());
		   if(list != null && list.size() > 0)
		   for(MobileAppUserSmsDetails user : list)
		   {
			   MobileAppUserVO Vo = new MobileAppUserVO();
			   Vo.setMobileAppUserId(user.getMobileAppUserId());
			   Vo.setUname(user.getMobileAppUser().getUserName());
			   Vo.setPwd(user.getMobileAppUser().getPassword());
			   Vo.setAdvMessage(user.getAdvMessage());
			   Vo.setDr(user.getDr());
			   Vo.setmType(user.getMtype());
			   returnList.add(Vo);
		   }
		   List<MobileAppUserAccessLocation> list1 = mobileAppUserAccessLocationDAO.getMobileAppUserAccessLocations(inputVO.getMobileAppUserId());
		   if(list1 != null && list1.size() > 0)
		   {
			   List<Long> boothIdsList = new ArrayList<Long>(0); 
			   for(MobileAppUserAccessLocation location  : list1)
			   {
				   if(location.getLocationLevelId() != null && location.getLocationLevelId().longValue()==9L)
					   boothIdsList.add(location.getLocationValue());
			   }
			   List<Object[]> boothDetailsList = boothDAO.getConstityencysByBooths(boothIdsList);
			   Map<Long,Long> boothConstituencyMap = new HashMap<Long, Long>(0);
				if(commonMethodsUtilService.isListOrSetValid(boothDetailsList)){
				   for (Object[] param : boothDetailsList) {
					   boothConstituencyMap.put(commonMethodsUtilService.getLongValueForObject(param[2]), commonMethodsUtilService.getLongValueForObject(param[0]));
					}
			   }
				
			   for(MobileAppUserAccessLocation location  : list1)
			   {
				   MobileAppUserVO vo = getMatchedAppUser(returnList,location.getMobileAppUserId());
				   if(vo!= null)
				   {
					   AccessLocationVO subVo = new AccessLocationVO();
					   subVo.setLevelId(location.getLocationLevelId());
					   if(location.getLocationLevelId() != null && location.getLocationLevelId().longValue()==8L){
						   subVo.setLevelValue(location.getLocationValue());
					   }else{
						   subVo.setBoothId(location.getLocationValue());
						   subVo.setConstituencyId(boothConstituencyMap.get(location.getLocationValue()));
					   }
					   if(vo.getAccessLocations() == null)
					   {
						   vo.setAccessLocations(new ArrayList<AccessLocationVO>());
					   }
					   vo.getAccessLocations().add(subVo);
				   }
			   }
		   }
		 
		   }
		   catch (Exception e) {
				Log.error("exception riased at getMobileAppUserSmsDetails", e);
			}
		   return returnList;
	   }
	   public MobileAppUserVO getMatchedAppUser(List<MobileAppUserVO> returnList,Long mobileAppUserId)
	   {
		 if(returnList == null || returnList.size() == 0)
			 return null;
		 for(MobileAppUserVO vo : returnList)
			 if(vo.getMobileAppUserId().longValue() == mobileAppUserId.longValue())
				 return vo;
		return null;
	   }
	   
	   public ResultStatus saveMobileAppUserVoterData(MobileAppUserVoterVO inputVo)
	   {
		   DateUtilService date = new DateUtilService();
		   ResultStatus rs = new ResultStatus();
		   try {
			   Log.info("Entered into saveMobileAppUserVoterData ");
			   SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		   MobileAppUserVoter mobileAppUserVoter = new MobileAppUserVoter();
		   mobileAppUserVoter.setBoothId(inputVo.getBoothId());
		   mobileAppUserVoter.setLatitude(inputVo.getLatitude());
		   mobileAppUserVoter.setLongitude(inputVo.getLongitude());
		   mobileAppUserVoter.setMobileAppUserId(inputVo.getMobileAppUserId());
		   mobileAppUserVoter.setMobileNo(inputVo.getMobileNum());
		   mobileAppUserVoter.setRating(inputVo.getRating());
		   mobileAppUserVoter.setUniqueKey(inputVo.getUniqueKey());
		   if(inputVo.getWardId() != null && inputVo.getWardId().longValue()>0L)
			   mobileAppUserVoter.setWardId(inputVo.getWardId());
		   mobileAppUserVoter.setVoterId(inputVo.getVoterId());
		   mobileAppUserVoter.setInsertedTime(date.getCurrentDateAndTime());
		   if(inputVo.getSurveyTime() != null && !inputVo.getSurveyTime().isEmpty())
		   mobileAppUserVoter.setSurveyTime(format.parse(inputVo.getSurveyTime()));
		   if(inputVo.getImeiNo() != null && !inputVo.getImeiNo().isEmpty())
		   mobileAppUserVoter.setImeiNo(inputVo.getImeiNo());
		   if(inputVo.getVersionNo() != null && !inputVo.getVersionNo().isEmpty())
			   mobileAppUserVoter.setVersionNo(inputVo.getVersionNo());
		   if(inputVo.getVotedTime() != null && !inputVo.getVotedTime().isEmpty())
			   mobileAppUserVoter.setVotedTime(format.parse(inputVo.getVotedTime()));
		   if(inputVo.getTdpCadreId() != null && inputVo.getTdpCadreId() > 0)
			   mobileAppUserVoter.setTdpCadreId(inputVo.getTdpCadreId());
		   else
			   mobileAppUserVoter.setTdpCadreId(getTdpCadreIdForVoter(mobileAppUserVoter.getVoterId()));
		   
		   
		   String mobileNo = inputVo.getMobileNum();
		   String smsStatus = null;
		   
		   if(mobileNo != null && mobileNo.length() == 10 && checkForValidMobileNo(mobileNo))
			   smsStatus = sendSmsForGHMCVoter(mobileNo,inputVo.getVoterId(),inputVo.getBoothId());
		   else
			   smsStatus = "Invalid Mobile";
		   
		   mobileAppUserVoter.setSmsStatus(smsStatus);
		   mobileAppUserVoterDAO.save(mobileAppUserVoter);
		   rs.setMessage("success");
		   
		   }
		   catch (Exception e) {
				Log.error("exception riased at saveMobileAppUserVoterData", e);
				rs.setMessage("fail");
			}
		   return rs;
		   
	   }
	   
	   public Long getTdpCadreIdForVoter(Long voterId)
	   {
		   try {
			return tdpCadreDAO.getTdpCadreIdByVoterId(voterId);
		} catch (Exception e) {
			// TODO: handle exception
		}
		   return null;
	   }
	   public String sendSmsForGHMCVoter(String mobileNo,Long voterId,Long boothId)
	   {
		   String result = null;
		   try{
			   List<Object[]> list = boothPublicationVoterDAO.getBoothVoterDetails(boothId,voterId);
			   
			   if(list == null || list.size() == 0)
				   list = boothPublicationVoterDAO.getBoothVoterDetails(voterId);
			   
			   if(list != null && list.size() > 0)
			   {
				   Object[] params = list.get(0);
				   StringBuilder sb = new StringBuilder();
				   
				   String voterName = params[1] != null ? params[1].toString() : "";
				   String voterIdCardNo = params[2] != null ? params[2].toString() : null;
				   String relativeName = params[4] != null ? params[4].toString() : null;
				   String relation = params[5] != null ? params[5].toString() : "";
				   String gender = params[3].toString();
				   String serialNo = params[0] != null ? params[0].toString() : null;
				   String latitude = params[8] != null ? params[8].toString() : null;
				   String longitude = params[9] != null ? params[9].toString() : null;
				   String location = params[7] != null ? params[7].toString() : null;
				   String relationStr = "C/O";
						   
				   sb.append("Name : "+voterName+"\n");
				   
				   if(relation.equalsIgnoreCase("Father") || relation.equalsIgnoreCase("Mother"))
				   {
					   if(gender.equalsIgnoreCase("M"))
						   relationStr = "S/O";
					   else
						   relationStr = "D/O";
				   }
				   else if(relation.equalsIgnoreCase("Husband") && gender.equalsIgnoreCase("F"))
					   relationStr = "W/O";
				   
				   if(relativeName != null)
					   sb.append(relationStr+" : "+relativeName+"\n");
				   
				   if(voterIdCardNo != null)
					   sb.append("EPIC ID : "+voterIdCardNo+"\n");
				   
				   if(serialNo != null)
					   sb.append("Serial No : "+serialNo+"\n");
				   
				   sb.append("Booth No : "+params[6].toString()+"\n");
				   
				   if(location != null)
				   sb.append("Location : "+location+"\n");
				   sb.append("Polling Date : 23-AUGUST-2017 07:00 AM - 05:00 PM.\n");
				   
				   if(latitude != null && longitude != null)
				   {
					   String url = "https://maps.google.com/maps?saddr=Current+Location&daddr="+latitude+","+longitude;
					   sb.append("Route to Polling Station\n");
					   sb.append(url);
				   }
				   result = smsGatewayService.sendSMS(mobileNo,sb.toString(),IConstants.ADMIN_USERNAME_FOR_SMS_FOR_OTP,IConstants.ADMIN_PASSWORD_FOR_SMS_FOR_OTP);		 
			   }
			      
		   }catch(Exception e)
		   {
			   Log.error("Exception Occured in sendSmsForGHMCVoter Method - ",e);
			   result = "Error";
		   }
		   return result;
	   }
	   
	   public boolean checkForValidMobileNo(String mobileNo)
	   {
		  try{
			  List<String> list = new ArrayList<String>(0);
			  list.add("1111111111");
			  list.add("2222222222");
			  list.add("3333333333");
			  list.add("4444444444");
			  list.add("5555555555");
			  list.add("6666666666");
			  list.add("7777777777");
			  list.add("8888888888");
			  list.add("9999999999");
			  list.add("0000000000");
			  
			  if(list.contains(mobileNo) || mobileNo.length()<10)
				  return false;
			  
		  }catch(Exception e)
		  {
			  Log.error(e);
		  }
		  return true;
	   }
	   public ResultStatus saveMobileAppUserSmsStatusData(MobileAppUserSmsStatusVO inputVo)
	   {
		   ResultStatus rs = new ResultStatus();
		   DateUtilService date = new DateUtilService();
		   try {
			   Log.info("Entered into saveMobileAppUserVoterData ");
		   MobileAppUserSmsStatus mobileAppUserSmsStatus = new MobileAppUserSmsStatus();
		   mobileAppUserSmsStatus.setMobileAppUserId(inputVo.getMobileAppUserId());
		   mobileAppUserSmsStatus.setOnlineSent(inputVo.getOnlineSent());
		   mobileAppUserSmsStatus.setPendingSms(inputVo.getPendingSms());
		   mobileAppUserSmsStatus.setSentSms(inputVo.getSentSms());
		   mobileAppUserSmsStatus.setSimCardSent(inputVo.getSimCardSent());
		   mobileAppUserSmsStatus.setTotalSms(inputVo.getTotalSms());
		  // mobileAppUserSmsStatus.setImeiNo(inputVo.getImeiNo());
		   mobileAppUserSmsStatus.setStatusDate(date.getCurrentDateAndTime());
		   mobileAppUserSmsStatus.setInsertedTime(date.getCurrentDateAndTime());
		   mobileAppUserSmsStatusDAO.save(mobileAppUserSmsStatus);
		   rs.setMessage("success");
		   }
		   catch (Exception e) {
				Log.error("exception riased at saveMobileAppUserSmsStatusData", e);
				rs.setMessage("fail");
			}
		   return rs;
	   }
	   
	   public List<MobileAppUserVoterVO> updateBoothVoter(MobileAppUserVoterVO inputVO)
	   {
		   List<MobileAppUserVoterVO> returnList = new ArrayList<MobileAppUserVoterVO>();
		   try {
			   Log.info("Entered into updateBoothVoter ");
		  
		    List<MobileAppUserVoter> list = mobileAppUserVoterDAO.getVoterDataForBooth(inputVO.getVoterIds(),inputVO.getBoothId());
		    if(list != null && list.size() > 0)
		    {
		    	for(MobileAppUserVoter voter : list)
		    	{
		    		MobileAppUserVoterVO vo = new MobileAppUserVoterVO();
		    		vo.setMobileAppUserId(voter.getMobileAppUserId());
		    		vo.setWardId(voter.getVoterId());
		    		vo.setBoothId(voter.getBoothId());
		    		vo.setVoterId(voter.getVoterId());
		    		vo.setMobileNum(voter.getMobileNo());
		    		vo.setRating(voter.getRating());
		    		vo.setUniqueKey(voter.getUniqueKey());
		    		vo.setLatitude(voter.getLatitude());
		    		vo.setLongitude(voter.getLongitude());
		    		vo.setSurveyTime(voter.getSurveyTime()!= null ? voter.getSurveyTime().toString() : "");
		    		vo.setImeiNo(voter.getImeiNo() != null ? voter.getImeiNo().toString():"");
		    		vo.setVersionNo(voter.getVersionNo() != null ? voter.getVersionNo() : "");
		    		vo.setIsVoted(voter.getIsVoted() != null ? voter.getIsVoted().toString() : "N");
		    		vo.setVotedTime(voter.getVotedTime() != null ? voter.getVotedTime().toString() : "");
		    		vo.setTdpCadreId(voter.getTdpCadreId()!= null ? voter.getTdpCadreId() : 0l);
		    		returnList.add(vo);
		    	}
		    }
		   }
		   catch (Exception e) {
				Log.error("exception riased at updateBoothVoter", e);
				
			}
		return returnList;
		   
	   }
	   
	   public Long returnNullForZero(Long value)
		{
		   Log.debug("Entered into returnNullForZero service method");
			try
			{
				if(value == 0)
					return null;
				return value;
				
			}catch(Exception e)
			{
				Log.error("Exception raised in  returnNullForZero service method");
				e.printStackTrace();
				return null;
			}
		}
	   
	   public String saveUserLocationData(UserLocationTrackingVo inputVO)
		{
			try
			{/*
				net.sf.json.JSONObject mainObj = (net.sf.json.JSONObject) net.sf.json.JSONObject
						.fromObject(userLocationDetails);

				net.sf.json.JSONArray userLctnDetails = (net.sf.json.JSONArray)mainObj.get("locations");
				for(int i=0;i<userLctnDetails.size();i++)
				{
	                   net.sf.json.JSONObject userLctn = (net.sf.json.JSONObject) userLctnDetails.get(i);
	                   UserLocationTrackingVO mainVO = new UserLocationTrackingVO();
					if(userLctn.has("userId"))
						mainVO.setUserId(returnNullForZero(Long.parseLong(userLctn.getString("userId"))));
					
					if(userLctn.has("latitude"))
						mainVO.setLatitude(userLctn.getString("latitude"));
					
					if(userLctn.has("longitude"))
						mainVO.setLongitude(userLctn.getString("longitude"));
					
					if(userLctn.has("uniqueId"))
						mainVO.setUniqueId(userLctn.getString("uniqueId"));
					
					if(userLctn.has("dateTime"))
					{
						mainVO.setDate(userLctn.getString("dateTime"));
						mainVO.setDate(mainVO.getDate().replaceAll(Pattern.quote("+"), " "));
					}
					userLocations.add(mainVO);
				}
				*/
				if(inputVO != null)
				{
					List<UserLocationTrackingVo> userLocations = new ArrayList<UserLocationTrackingVo>(0);
					userLocations.add(inputVO);
					mobileService.saveUserLocationData(userLocations);
				}
				
				
				
			}catch(Exception e)
			{
				e.printStackTrace();
				return "error";
			}
			return "success";
			
		}
	   
	   public String updateVoterVotedData(MobileAppUserVoterVO inputVO)
	   {
		   String result ="";
		   Log.debug("Entered into updateVoterVotedData service method");
			try
			{
				 SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
				 List<Object[]> list = mobileAppUserVoterDAO.mobileAppUserVoterId(inputVO.getVoterIds());
				 if(list != null && list.size() > 0)
				 {
					for(Object[] obj : list)	
					{
						MobileAppUserVoter mobileAppUserVoter = mobileAppUserVoterDAO.get((Long) obj[0]);
						mobileAppUserVoter.setIsVoted(inputVO.getIsVoted());
						  if(inputVO.getVotedTime() != null && !inputVO.getVotedTime().isEmpty())
					    mobileAppUserVoter.setVotedTime(format.parse(inputVO.getVotedTime()));
						mobileAppUserVoterDAO.save(mobileAppUserVoter);
						result = "success";
					}
				 }
				 
			}catch(Exception e)
			{
				Log.error("Exception raised in  updateVoterVotedData service method");
				e.printStackTrace();
				result = "fail";
			}
			return result;
	   }
	 public PollManagementVO getDivisonWiseOverview(MobileAppUserVoterVO inputVO)
	 {
		 return mobileService.getDivisonOverView(inputVO);
		 
	 }
	 public List<CadreVoterVO> getVoterInfoForBooth(MobileAppUserVoterVO inputVO)
	 {
		 return mobileService.getVoterInfoForBooth(inputVO); 
	 }
	 
	  public CadreOverviewVO getVoterDetailsByVoterIdCardNum(CadreOverviewVO inputVO)
	  {
		  CadreOverviewVO returnVO = null;
		  try {
			  returnVO = cadreDetailsService.getVoterDetailsByVoterIdCardNum(inputVO.getVoterCardNo(),inputVO.getFamilyVoterCardNum(),inputVO.getMemberType(),inputVO.getMemberTypeId());
		} catch (Exception e) {
			 log.debug("Entered into the getVoterDetailsByVoterIdCardNum  method in WebServiceHandlerService");
		}
		  return returnVO;
	  }
	  
	  public UnionTabUserVO checkLoginUnionTabUser(UnionTabUserVO inputVo)
	  {
		  UnionTabUserVO vo = new UnionTabUserVO();
		   List<UnionTabUser> list = unionTabUserDAO.checkUserExists(inputVo.getUname(),inputVo.getPwd());
		   if(list  == null || list.size() == 0)
		   {
			   vo.setMsg("Invalid");
			   return vo;
		   }
		   vo.setMsg("valid");
		   for(UnionTabUser params : list)
		   {
			   vo.setMobileNum(params.getMobileNo() != null ? params.getMobileNo() : null);
			   vo.setUname(params.getUserName() != null ? params.getUserName() : null);
			  // vo.setPwd(params.getPassWord() != null ? params.getPassWord() : null);
			   vo.setUnionTabUserId(params.getUnionTabUserId() != null ? params.getUnionTabUserId() : null);
			   vo.setName(params.getName() != null ? params.getName() : null);
			   
			   List<Object[]> membersList = tdpMemberUnionTabUserRelationDAO.getAccessMemberTypesBtTabUserId(vo.getUnionTabUserId());
			   Map<Long,UnionTabUserVO> tdpMemberTypesMap = new LinkedHashMap<Long,UnionTabUserVO>(0);
			   if(membersList != null && membersList.size()>0)
			   {
				   for (Object[] member : membersList) {
					   Long id = commonMethodsUtilService.getLongValueForObject(member[0]);
					   String name = commonMethodsUtilService.getStringValueForObject(member[1]);
					   
					   UnionTabUserVO membervo = new UnionTabUserVO();
					   membervo.setMemberTypeId(id);
					   membervo.setMemberTypeName(name);
					   tdpMemberTypesMap.put(id, membervo);
					   
				   }
				   
				   if(tdpMemberTypesMap != null && tdpMemberTypesMap.size()>0)
				   {
					   List<Object[]> designationsList = unionTypeDesignationDAO.getDesignationsOfTdpMemberTypeIdsList(new ArrayList<Long>(tdpMemberTypesMap.keySet()));
					   if(designationsList != null && designationsList.size()>0)
					   {
						   for (Object[] designation : designationsList) {
							   Long memberId = commonMethodsUtilService.getLongValueForObject(designation[0]);
							   Long id = commonMethodsUtilService.getLongValueForObject(designation[1]);
							   String name = commonMethodsUtilService.getStringValueForObject(designation[2]);
							   
							   UnionTabUserVO memberVO = tdpMemberTypesMap.get(memberId);
							  
							   if(memberVO != null)
							   {
								   UnionTabUserVO designationvo = new UnionTabUserVO();
								   designationvo.setId(id);
								   designationvo.setName(name);
								   
								   memberVO.getMemberTypesList().add(designationvo);
							   }
						   }
					   }
				   }
			   }
			   
			   if(tdpMemberTypesMap != null && tdpMemberTypesMap.size()>0)
			   {
				   for (Long memberId : tdpMemberTypesMap.keySet())
					   vo.getMemberTypesList().add(tdpMemberTypesMap.get(memberId));
			   }
		   }
		return vo;
	  }
	  
	  public PartyMeetingWSVO getAttendedDetailsForPartyMeeting(Long partyMeetingId)
	  {
		  PartyMeetingWSVO returnVO = null;
		  try {
			  returnVO = partyMeetingService.getAttendedDetailsForPartyMeeting(partyMeetingId);
		} catch (Exception e) {
			 log.debug("Entered into the getAttendedDetailsForPartyMeeting  method in WebServiceHandlerService");
		}
		  return returnVO;
	  }
	  
	  public PartyMeetingWSVO getTdpCadreDetailsForPartyMeeting(Long partyMeetingId,String searchType)
	  {
		  PartyMeetingWSVO returnvo = null;
		  try {
			  returnvo = partyMeetingService.getTdpCadreDetailsForPartyMeeting(partyMeetingId,searchType,null);
		} catch (Exception e) {
			 log.debug("Entered into the getTdpCadreDetailsForPartyMeeting  method in WebServiceHandlerService");
		}
		  return returnvo;
	  }
	  
	  public List<AccommodationVO> getAccommodationTrackingInfoByNotificationType(AccommodationVO inputvo)
	  {
		  List<AccommodationVO> returnList = null;
		  try {
			  returnList = notificationService.getAccommodationTrackingInfoByNotificationType(inputvo);
		} catch (Exception e) {
			 log.debug("Entered into the getAccommodationTrackingInfoByNotificationType  method in WebServiceHandlerService");
		}
		  return returnList;
	  }
	  
	  public List<GISVisualizationDetailsVO> getMembershipDriveVisualizationDetails(GISVisualizationParameterVO inputVO){
		  List<GISVisualizationDetailsVO> returnList = new ArrayList<GISVisualizationDetailsVO>(0);
		  try {
			  GISVisualizationDetailsVO  vo =  gisVisualizationService.getMembershipDriveVisualizationDetails(inputVO);
			  if(vo != null)
				  returnList.add(vo);
		} catch (Exception e) {
			log.debug("Entered into the getMembershipDriveVisualizationDetails  method in WebServiceHandlerService");
		}
		  return returnList;
	  }
	  
	  public List<IdAndNameVO> getStateWiseConstituency() {
			List<IdAndNameVO> consList = new ArrayList<IdAndNameVO>();
			try {
				List<Object[]> allConslist = constituencyDAO
						.getStateWiseConstituency();
				if (allConslist != null && allConslist.size() > 0) {
					for (Object[] objects : allConslist) {
						IdAndNameVO vo = new IdAndNameVO();
						vo.setId(objects[0] != null ? (Long) objects[0] : 0l);
						vo.setName(objects[1] != null ? objects[1].toString() : "");
						consList.add(vo);
					}
				}

			} catch (Exception e) {
				log.error(
						"Exception raised in getStateWiseConstituency() in WebServiceHandlerService class",
						e);
			}
			return consList;
		}

		public List<IdAndNameVO> getConstitencyWiseTehsil(Long constituencyId) {
			List<IdAndNameVO> returnList = null;
			try {
				returnList = cadreRegistrationService
						.getConstitencyWiseTehsil(constituencyId);
			} catch (Exception e) {
				log.debug("Entered into the getConstitencyWiseTehsil  method in WebServiceHandlerService");
			}
			return returnList;
		}
	  
	  public List<IdAndNameVO> getPanchayatOrConsList(Long mandalOrMunpaId)
	  {
		  List<IdAndNameVO> returnList=null;
		  try{
			  returnList=cadreRegistrationService.getPanchayatOrConsList(mandalOrMunpaId,"1");
		  }catch(Exception e){
			  log.error("Error occured at getPanchayatOrConsList() in WebServiceHandlerService {}",e);
			  
		  }
		  return returnList;
	  }
	 
	  public List<IdAndNameVO> getBoothsList(Long panchayatId){
		  List<IdAndNameVO> returnList=null;
		  try{
			  returnList=cadreRegistrationService.getBoothsList(panchayatId);
			  
		  }catch(Exception e){
			  log.error("Error occured at getBoothsList() in WebServiceHandlerService {}",e);
		  }
		  return returnList;
	  }
	  
	  public List<KeyValueVO> getStateWiseAssemblyConstituency(Long stateId){
		  try {			  
			  return cadreRegistrationService.getStateWiseAssemblyConstituency(stateId);			
		} catch (Exception e) {
			log.error("Error occured at getStateWiseAssemblyConstituency() in WebServiceHandlerService {}",e);
		}
		  return null;
	  }
	  
	  public List<GISUserTrackingVO> getLatestLattitudeLangitudeOfTabUser(GISUserTrackingVO VO){
		  try {			  
			  return cadreRegistrationService.getLatestLattitudeLangitudeOfTabUser(VO);			
		} catch (Exception e) {
			log.error("Error occured at getLatestLattitudeLangitudeOfTabUser() in WebServiceHandlerService {}",e);
		}
		  return null;
	  }
	  
	  public ResultStatus syncCadreTabRecordsStatus(List<CadreTabRecordsStatusVO> cadreTabRecordsStatusList){
		  try {
			  return cadreRegistrationService.syncCadreTabRecordsStatus(cadreTabRecordsStatusList);	
		} catch (Exception e) {
			log.error("Error occured at syncCadreTabRecordsStatus() in WebServiceHandlerService {}",e);
		}
		  return null;
	  }
	  public List<IdAndNameVO> getStateWiseDistrict(Long stateId) {
			List<IdAndNameVO> returnList = null;
			try {
				returnList = cadreRegistrationService
						.getStateWiseDistrict(stateId);
			} catch (Exception e) {
				log.debug("Entered into the getStateWiseDistrict  method in WebServiceHandlerService");
			}
			return returnList;
		}
	  public List<IdAndNameVO> getDistrictWiseConstituency(Long districtId) {
			List<IdAndNameVO> returnList = null;
			try {
				returnList = cadreRegistrationService
						.getDistrictWiseConstituency(districtId);
			} catch (Exception e) {
				log.debug("Entered into the getStateWiseDistrict  method in WebServiceHandlerService");
			}
			return returnList;
		}
	  
	  public List<GISVisualizationDetailsVO> getMembershipDriveDayWiseVisualizationDetails(GISVisualizationParameterVO inputVO){
		  List<GISVisualizationDetailsVO> returnList = new ArrayList<GISVisualizationDetailsVO>(0);
		  try {
			  GISVisualizationDetailsVO  vo =  gisVisualizationService.getMembershipDriveDayWiseVisualizationDetails(inputVO);
			  if(vo != null)
				  returnList.add(vo);
		} catch (Exception e) {
			log.debug("Entered into the getMembershipDriveDayWiseVisualizationDetails  method in WebServiceHandlerService");
		}
		  return returnList;
	  }
	  
	  public GISUserTrackingVO getLocationWiseTabUserTrackingDetails(GISVisualizationParameterVO inputVO){
		  GISUserTrackingVO returnVO = new GISUserTrackingVO();
		  try {
			  if(inputVO.getChildLocationType() == null || inputVO.getChildLocationType().isEmpty())
				  inputVO.setChildLocationType("old");
			  returnVO =  gisVisualizationService.getLocationWiseTabUserTrackingDetails(inputVO);
			 
		} catch (Exception e) {
			log.error("exception occured in  the getLocationWiseTabUserTrackingDetails  method in WebServiceHandlerService");
		}
		  return returnVO; 
	  }
	 
	  public GISIssuesVO getCadreRegistrationIssuesStatusDetails(GISVisualizationParameterVO inputVO){
		  log.error("Entered into the getCadreRegistrationIssuesStatusDetails  method in WebServiceHandlerService");
		  GISIssuesVO returnVO = new GISIssuesVO();
		  try {
			  List<GISIssuesVO> returnList = fieldMonitoringService.getLocationWiseIssueStatus(inputVO);
			  if(commonMethodsUtilService.isListOrSetValid(returnList)){
				  returnVO.setSubList(returnList);
			  }
			} catch (Exception e) {
				e.printStackTrace();
				log.error("exception occured in  the getCadreRegistrationIssuesStatusDetails  method in WebServiceHandlerService");
			}
		  
		 return returnVO;
	  }
	  
	  public String setArticleDetailsIntoAlert(ActionableVO VO){
		  String result = null;
		  try{			  
			  result = alertService.setArticleDetailsIntoAlert(VO);			  
		  }catch(Exception e){
			  e.printStackTrace();
				log.error("exception occured in  the setArticleDetailsIntoAlert  method in WebServiceHandlerService");
		  }
		  return result;
	  }
	  
	  public String getAlertStatusOfArticle(Long articleId){
		  String result = null;
		  try{			  
			  result = alertService.getAlertStatusOfArticle(articleId);			  
		  }catch(Exception e){
			  e.printStackTrace();
				log.error("exception occured in  the getAlertStatusOfArticle  method in WebServiceHandlerService");
		  }
		  return result;
	  }
	  
	  public PeshiAppLoginVO getPeshiAppValidateLoginDetails(String userName,String password){
		  PeshiAppLoginVO peshiAppLoginVO = new PeshiAppLoginVO();
		  try{
			  
			  peshiAppLoginVO = loginService.getPeshiAppValidateLoginDetails(userName,password);
			  
		  }catch(Exception e){
			  log.error("exception occured in  the getPeshiAppLoginDataUser  method in WebServiceHandlerService");
		  }
		 return peshiAppLoginVO;
	  }

	 public PeshiAppGrievanceVO getPeshiAppGrievanceDetails(String fromDateStr,String toDateStr,String membershipId){
		  	PeshiAppGrievanceVO returnVO = new PeshiAppGrievanceVO();
		  		try{
		  			SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
		  			Date fromDate = null;	
		  			Date toDate = null;
		  			if(fromDateStr != null && toDateStr != null){
		  				fromDate = sdf.parse(fromDateStr);
		  				toDate = sdf.parse(toDateStr);
		  			}
		  				List<PeshiAppGrievanceVO> returnList = new ArrayList<PeshiAppGrievanceVO>();
		  				List<Long> complaintIdsList = new ArrayList<Long>();
		  				Map<Long,List<String>> filePathMap = new LinkedHashMap<Long,List<String>>();
		  				List<Object[]> list = eventSurveyUserDAO.getPeshiAppForGrievance(fromDate,toDate,membershipId);
		  				Long count = Long.valueOf(list.size());
		  				if(list !=null && list.size() > 0l){
		  					for (Object[] objects : list) {
		  						PeshiAppGrievanceVO vo = new PeshiAppGrievanceVO();
		  						vo.setComplaintId(commonMethodsUtilService.getLongValueForObject(objects[0]));
		  						vo.setSubject(commonMethodsUtilService.getStringValueForObject(objects[1]));
		  						vo.setDescription(commonMethodsUtilService.getStringValueForObject(objects[2]));
		  						vo.setRaisedDateTime(commonMethodsUtilService.getStringValueForObject(objects[3]));
		  						vo.setLastUpdatedDateTime(commonMethodsUtilService.getStringValueForObject(objects[4]));
		  						vo.setPresentStatus(commonMethodsUtilService.getStringValueForObject(objects[5]));
		  						vo.setTypeOfIssue(commonMethodsUtilService.getStringValueForObject(objects[6]));
		  						vo.setTotalGrivanceCount(count);
		  						if(objects[7] != null){
		  							String pathStr = commonMethodsUtilService.getStringValueForObject(objects[7]);
		  							vo.getFilePthList().add("mytdp.com/Grievance/complaintScannedCopy/"+pathStr);
		  						}
		  							
		  						vo.setMembershipId(commonMethodsUtilService.getStringValueForObject(objects[8]));
		  						vo.setMobileNo(commonMethodsUtilService.getStringValueForObject(objects[9]));
		  						vo.setName(commonMethodsUtilService.getStringValueForObject(objects[10]));
		  						vo.setImage(commonMethodsUtilService.getStringValueForObject(objects[11]));
		  						if(vo.getImage() != null && vo.getImage().trim().length() > 0)
		  							vo.setImage("mytdp.com/images/cadre_images/"+vo.getImage());
		  						if(complaintIdsList == null || complaintIdsList.isEmpty()){
		  							returnVO.setMembershipId(commonMethodsUtilService.getStringValueForObject(objects[8]));
		  							returnVO.setMobileNo(commonMethodsUtilService.getStringValueForObject(objects[9]));
		  							returnVO.setName(commonMethodsUtilService.getStringValueForObject(objects[10]));
		  							returnVO.setImage(commonMethodsUtilService.getStringValueForObject(objects[11]));
			  						if(returnVO.getImage() != null && returnVO.getImage().trim().length() > 0)
			  							returnVO.setImage("mytdp.com/images/cadre_images/"+returnVO.getImage());
		  						}
		  						complaintIdsList.add(vo.getComplaintId());
		  						returnList.add(vo);
		  					}
		  				}
		  				if(complaintIdsList != null && !complaintIdsList.isEmpty()){
		  					List<Object[]> filePathList = eventSurveyUserDAO.getFilePathUrlForComplaintIds(complaintIdsList);
			  				if(filePathList != null && filePathList.size() > 0l){
			  					for (Object[] objects : filePathList) {
			  						Long complaintId = Long.valueOf(objects[0] != null ? objects[0].toString() :"0");
			  						String filePath = objects[1] != null ? objects[1].toString():"";
			  						List<String> filesList = filePathMap.get(complaintId);
			  						if(filesList == null || filesList.isEmpty()){
			  							filesList = new ArrayList<String>();
			  							filesList.add("mytdp.com/Grievance/complaintScannedCopy/"+filePath);
			  							filePathMap.put(complaintId,filesList);
			  						}
			  						else
			  							filesList.add("mytdp.com/Grievance/complaintScannedCopy/"+filePath);
								}
			  				}
		  				}
		  				
		  				if(returnList != null && !returnList.isEmpty()){
		  					for (PeshiAppGrievanceVO vo : returnList) {
								vo.getFilePthList().addAll(filePathMap.get(vo.getComplaintId()));
							}
		  				}
		  				
		  				returnVO.setSubList(returnList);
		  		}catch(Exception e){
		  			log.error("exception occured in  the getPeshiAppGrievanceDetails  method in WebServiceHandlerService",e);
		  		}
		  		return returnVO;
	  }
	 public PeshiAppAppointmentVO getAppointmentDetails(String fromDateStr,String toDateStr,String membershipIdStr,String cadreType,String voterId,String mobileNoStr){
		 PeshiAppAppointmentVO returnVO = new PeshiAppAppointmentVO();
		 try{
			 SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
			 Date fromDate = null;
			 Date toDate = null;
			 if(fromDateStr != null && toDateStr != null){
				 fromDate = sdf.parse(fromDateStr);
				 toDate = sdf.parse(toDateStr);
			 }
			 List<Long> appointmnetIds = new ArrayList<Long>();
			 Long tdpCadreId;
			 String membershipId = membershipIdStr;
			 if(membershipId == null || membershipId.isEmpty() || membershipId.trim().equalsIgnoreCase("0")){
				 /*if(voterId != null && !voterId.isEmpty()){
					CadreCommitteeVO  cadreCommitteeVO = cadreCommitteeService.searchTdpCadreDetailsBySearchCriteriaForCadreCommitte(null,null,null,null,voterId,null,null,null,null,null,null,null,null,0,10,false,null);
					 membershipId = cadreCommitteeVO.getMemberShipCardId();
				 }else if(mobileNoStr != null && !mobileNoStr.isEmpty()){
					 CadreCommitteeVO  cadreCommitteeVO = cadreCommitteeService.searchTdpCadreDetailsBySearchCriteriaForCadreCommitte(null,null,null,null,null,mobileNoStr,null,null,null,null,null,null,null,0,10,false,null);
					 membershipId = cadreCommitteeVO.getMemberShipCardId();
				 }*/
				 membershipId = cadreDetailsService.getMemberShipNumberByVoterNumberOrMobileNo(voterId, mobileNoStr);
			 }
			 
			 List<Long> tdpCadreIds = tdpCadreDAO.getTdpCadreIdByMembershipId(membershipId);
			 if(tdpCadreIds != null && !tdpCadreIds.isEmpty()){
				 tdpCadreId = tdpCadreIds.get(0);
				 if(cadreType != null && cadreType.trim().equalsIgnoreCase("leader")){
					 List<Object[]> list = appointmentTimeSlotDAO.getLeaderAppointmentDetails(fromDate, toDate, tdpCadreId);
					 if(list != null && !list.isEmpty()){
						 for (Object[] objects : list) {
							 PeshiAppAppointmentVO vo = new PeshiAppAppointmentVO();
							vo.setAppointmentStatusId(commonMethodsUtilService.getLongValueForObject(objects[0]));
							vo.setAppoinmentId(commonMethodsUtilService.getLongValueForObject(objects[1]));
							vo.setAppDescription(commonMethodsUtilService.getStringValueForObject(objects[2]));
							vo.setAppDate(commonMethodsUtilService.getStringValueForObject(objects[3]));
							vo.setName(commonMethodsUtilService.getStringValueForObject(objects[4]));
							vo.setMemberShipId(commonMethodsUtilService.getStringValueForObject(objects[5]));
							vo.setMobileNo(commonMethodsUtilService.getStringValueForObject(objects[6]));
							vo.setImage(commonMethodsUtilService.getStringValueForObject(objects[7]));
							if(vo.getImage() != null && vo.getImage().trim().length() > 0)
	  							vo.setImage("mytdp.com/"+vo.getImage());
							vo.setPresentStatus(commonMethodsUtilService.getStringValueForObject(objects[8]));
							if(appointmnetIds == null || appointmnetIds.size() == 0){
								returnVO.setId(commonMethodsUtilService.getLongValueForObject(objects[9]));
								returnVO.setName(commonMethodsUtilService.getStringValueForObject(objects[10]));
								returnVO.setMobileNo(commonMethodsUtilService.getStringValueForObject(objects[11]));
								returnVO.setImage(commonMethodsUtilService.getStringValueForObject(objects[12]));
								if(returnVO.getImage() != null && returnVO.getImage().trim().length() > 0l)
									returnVO.setImage("mytdp.com/images/cadre_images/"+returnVO.getImage());
							}
							if(vo.getAppointmentStatusId() == 3l)
								returnVO.getScheduledList().add(vo);
							else if(vo.getAppointmentStatusId() == 4l)
								returnVO.getCompletedList().add(vo);
							else if(vo.getAppointmentStatusId() == 10l)
								returnVO.getNotAttendedList().add(vo);
							if(vo.getAppointmentStatusId() == 3l || vo.getAppointmentStatusId() == 4l || vo.getAppointmentStatusId() == 10l)	
								returnVO.getTotalAppoinmentsList().add(vo);
							
							appointmnetIds.add(vo.getAppoinmentId());
						}
					 }
				 }
				 else if(cadreType != null && cadreType.trim().equalsIgnoreCase("cadre")){
					 List<Object[]> list = appointmentTimeSlotDAO.getAppointmentDetails(fromDate, toDate, tdpCadreId);
					 if(list != null && list.size()  > 0l){
						 for (Object[] objects : list) {
							PeshiAppAppointmentVO vo = new PeshiAppAppointmentVO();
							vo.setAppointmentStatusId(commonMethodsUtilService.getLongValueForObject(objects[0]));
							vo.setAppoinmentId(commonMethodsUtilService.getLongValueForObject(objects[1]));
							vo.setAppDescription(commonMethodsUtilService.getStringValueForObject(objects[2]));
							vo.setAppDate(commonMethodsUtilService.getStringValueForObject(objects[3]));
							if(appointmnetIds == null || appointmnetIds.size() == 0){
								returnVO.setName(commonMethodsUtilService.getStringValueForObject(objects[4]));
								returnVO.setMemberShipId(commonMethodsUtilService.getStringValueForObject(objects[5]));
								returnVO.setMobileNo(commonMethodsUtilService.getStringValueForObject(objects[6]));
								returnVO.setImage(commonMethodsUtilService.getStringValueForObject(objects[7]));
								if(returnVO.getImage() != null && returnVO.getImage().trim().length() > 0l)
									returnVO.setImage("mytdp.com/"+returnVO.getImage());
							}
							vo.setPresentStatus(commonMethodsUtilService.getStringValueForObject(objects[8]));
							if(vo.getAppointmentStatusId() == 3l)
								returnVO.getScheduledList().add(vo);
							else if(vo.getAppointmentStatusId() == 4l)
								returnVO.getCompletedList().add(vo);
							else if(vo.getAppointmentStatusId() == 10l)
								returnVO.getNotAttendedList().add(vo);
							if(vo.getAppointmentStatusId() == 3l || vo.getAppointmentStatusId() == 4l || vo.getAppointmentStatusId() == 10l)	
								returnVO.getTotalAppoinmentsList().add(vo);
							
							appointmnetIds.add(vo.getAppoinmentId());
						}
					 }
				 }
			 }
			 
			 Map<Long,List<PeshiAppAppointmentVO>> apntmentMembersMap = new HashMap<Long,List<PeshiAppAppointmentVO>>();
			 if(appointmnetIds != null && !appointmnetIds.isEmpty()){
				 List<Object[]> appntmentMembersList = appointmentTimeSlotDAO.getAppointmentList(appointmnetIds);
				 if(appntmentMembersList != null && appntmentMembersList.size() > 0l){
					 for (Object[] objects : appntmentMembersList) {
						 Long apmnetId = Long.valueOf(objects[0] != null ? objects[0].toString():"0");
						 String name = objects[1] != null ? objects[1].toString() : "";
						 String mobileNo = objects[2] != null ? objects[2].toString() :"";
						 String membershipNo = objects[3] != null ? objects[3].toString() : "";
						 String image = objects[4] != null ? objects[4].toString() : "";
						 List<PeshiAppAppointmentVO> memberList = apntmentMembersMap.get(apmnetId);
						 if(memberList == null || memberList.isEmpty()){
							 memberList = new ArrayList<PeshiAppAppointmentVO>();
							 PeshiAppAppointmentVO vo = new PeshiAppAppointmentVO();
							 vo.setName(name);
							 vo.setMobileNo(mobileNo);
							 vo.setMemberShipId(membershipNo);
							 vo.setImage(image);
							 if(vo.getImage() != null && vo.getImage().trim().length() > 0l)
								 vo.setImage("mytdp.com/"+vo.getImage());
							 memberList.add(vo);
							 apntmentMembersMap.put(apmnetId, memberList);
						 }else{
							 PeshiAppAppointmentVO vo = new PeshiAppAppointmentVO();
							 vo.setName(name);
							 vo.setMobileNo(mobileNo);
							 vo.setMemberShipId(membershipNo);
							 vo.setImage(image);
							 if(vo.getImage() != null && vo.getImage().trim().length() > 0l)
								 vo.setImage("mytdp.com/"+vo.getImage());
							 memberList.add(vo);
						 }
					}
				 }
			 }
			 
		 	if(returnVO != null){
				if(returnVO.getTotalAppoinmentsList() != null && !returnVO.getTotalAppoinmentsList().isEmpty()){
					for (PeshiAppAppointmentVO vo1 : returnVO.getTotalAppoinmentsList()) {
						Long aptId = vo1.getAppoinmentId();
						vo1.setTotalAppoinmentsList(apntmentMembersMap.get(aptId));
					}
				}
				if(returnVO.getScheduledList() != null && !returnVO.getScheduledList().isEmpty()){
					for (PeshiAppAppointmentVO vo1 : returnVO.getScheduledList()) {
						Long aptId = vo1.getAppoinmentId();
						vo1.setTotalAppoinmentsList(apntmentMembersMap.get(aptId));
					}
				}
				if(returnVO.getCompletedList() != null && !returnVO.getCompletedList().isEmpty()){
					for (PeshiAppAppointmentVO vo1 : returnVO.getCompletedList()) {
						Long aptId = vo1.getAppoinmentId();
						vo1.setTotalAppoinmentsList(apntmentMembersMap.get(aptId));
					}
				}
				if(returnVO.getNotAttendedList() != null && !returnVO.getNotAttendedList().isEmpty()){
					for (PeshiAppAppointmentVO vo1 : returnVO.getNotAttendedList()) {
						Long aptId = vo1.getAppoinmentId();
						vo1.setTotalAppoinmentsList(apntmentMembersMap.get(aptId));
					}
				}
			}
			 
			 returnVO.setSecheduledCount(Long.valueOf(returnVO.getScheduledList().size()));
			 returnVO.setCompletedCount(Long.valueOf(returnVO.getCompletedList().size()));
			 returnVO.setNotCompletedCount(Long.valueOf(returnVO.getNotAttendedList().size()));
			 returnVO.setTotalAppoinmentsCount(Long.valueOf(returnVO.getTotalAppoinmentsList().size()));
			 returnVO.setGeneralVisitorsCount(0l);
			 
		 }catch(Exception e){
			 log.error("exception occured in  the getAppointmentDetails  method in WebServiceHandlerService",e);
		 }
		 return returnVO;
	 }
	 
	 public String savingNewMembersForAppointment(PashiAppNoCadreVO inputvo){
		  String result = null;
		  try{			  
			  result = appointmentService.savingNewMembersForAppointment(inputvo);	
		  }catch(Exception e){
			  e.printStackTrace();
				log.error("exception occured in  the savingNewMembersForAppointment  method in WebServiceHandlerService");
		  }
		  return result;
	  }
	 
	 public AlertOverviewVO getAlertOverviewDetails(Long activityMemberId,Long stateId,String fromDateStr,String toDateStr, Long alertType,Long editionType){
		 AlertOverviewVO returnvo = null;
		  try{			  
			  returnvo = alertsNewsPortalService.getAlertOverviewDetails(activityMemberId, stateId, fromDateStr, toDateStr, alertType, editionType);			  
		  }catch(Exception e){
			  e.printStackTrace();
				log.error("exception occured in  the getAlertOverviewDetails  method in WebServiceHandlerService");
		  }
		  return returnvo;
	  }
	 
	 public List<AlertOverviewVO> getAlertCategoryDtlsLocationWise(Long activityMemberId,Long stateId,String fromDateStr,String toDateStr, Long alertType,Long editionType){
		 List<AlertOverviewVO> returnList = null;
		  try{			  
			  returnList = alertsNewsPortalService.getAlertCategoryDtlsLocationWise(activityMemberId, stateId, fromDateStr, toDateStr, alertType, editionType);			  
		  }catch(Exception e){
			  e.printStackTrace();
				log.error("exception occured in  the getAlertCategoryDtlsLocationWise  method in WebServiceHandlerService");
		  }
		  return returnList;
	  }
	 
	 public List<AlertCoreDashBoardVO> getDistrictAndStateImpactLevelWiseAlertDtls(String fromDateStr, String toDateStr, Long stateId,String impactLevelIdStr, Long activityMemberId,Long districtId,Long catId, Long alertTypeId, Long editionId){
		 List<AlertCoreDashBoardVO> returnList = null;
		  try{			  
			  returnList = alertsNewsPortalService.getDistrictAndStateImpactLevelWiseAlertDtls(fromDateStr, toDateStr, stateId, impactLevelIdStr, activityMemberId, districtId, catId, alertTypeId, editionId);			  
		  }catch(Exception e){
			  e.printStackTrace();
				log.error("exception occured in  the getDistrictAndStateImpactLevelWiseAlertDtls  method in WebServiceHandlerService");
		  }
		  return returnList;
	  }
	 
	 public List<AlertCoreDashBoardVO> getAlertDtls(String fromDateStr, String toDateStr, Long stateId, Long alertTypeId, Long alertStatusId, Long alertCategoryId, Long activityMemberId, Long editionId,String isActionType,Long alertActionTypeId){
		 List<AlertCoreDashBoardVO> returnList = null;
		  try{			  
			  returnList = alertsNewsPortalService.getAlertDtls(fromDateStr, toDateStr, stateId, alertTypeId, alertStatusId, alertCategoryId, activityMemberId, editionId, isActionType, alertActionTypeId);			  
		  }catch(Exception e){
			  e.printStackTrace();
				log.error("exception occured in  the getAlertDtls  method in WebServiceHandlerService");
		  }
		  return returnList;
	  }
	 
	 
	 public AlertOverviewVO getStateImpactLevelAlertDtlsCnt(Long activityMemberId,Long stateId,String fromDateStr,String toDateStr,String impactLevelIds,Long alertTypeId, Long editionId){
		 AlertOverviewVO returnvo = null;
		  try{			  
			  returnvo = alertsNewsPortalService.getStateImpactLevelAlertDtlsCnt(activityMemberId, stateId, fromDateStr, toDateStr, impactLevelIds, alertTypeId, editionId);			  
		  }catch(Exception e){
			  e.printStackTrace();
				log.error("exception occured in  the getStateImpactLevelAlertDtlsCnt  method in WebServiceHandlerService");
		  }
		  return returnvo;
	  }
	 
	 public List<AlertVO> getTotalAlertGroupByLocationThenCategory(String fromDateStr, String toDateStr, Long stateId,String scopeIdList, Long activityMemberId, String group,Long alertTypeId, Long editionId){
		 List<AlertVO> returnList = null;
		  try{			  
			  returnList = alertsNewsPortalService.getTotalAlertGroupByLocationThenCategory(fromDateStr, toDateStr, stateId, scopeIdList, activityMemberId, group, alertTypeId, editionId);			  
		  }catch(Exception e){
			  e.printStackTrace();
				log.error("exception occured in  the getTotalAlertGroupByLocationThenCategory  method in WebServiceHandlerService");
		  }
		  return returnList;
	  }
	 
	 public List<AlertVO> getTotalAlertGroupByLocationThenStatus(String fromDateStr, String toDateStr, Long stateId,String scopeIdList, Long activityMemberId, String group,Long alertTypeId, Long editionId){
		 List<AlertVO> returnList = null;
		  try{			  
			  returnList = alertsNewsPortalService.getTotalAlertGroupByLocationThenStatus(fromDateStr, toDateStr, stateId, scopeIdList, activityMemberId, group, alertTypeId, editionId);			  
		  }catch(Exception e){
			  e.printStackTrace();
				log.error("exception occured in  the getTotalAlertGroupByLocationThenStatus  method in WebServiceHandlerService");
		  }
		  return returnList;
	  }
	 
	 public List<AlertOverviewVO> getAssignGroupTypeAlertDtlsByImpactLevelWise(Long activityMemberId,Long stateId,String fromDateStr,String toDateStr,String impactLevelIds,Long alertTypeId,Long editionTypeId){
		 List<AlertOverviewVO> returnList = null;
		  try{			  
			  returnList = alertsNewsPortalService.getAssignGroupTypeAlertDtlsByImpactLevelWise(activityMemberId, stateId, fromDateStr, toDateStr, impactLevelIds, alertTypeId, editionTypeId);			  
		  }catch(Exception e){
			  e.printStackTrace();
				log.error("exception occured in  the getAssignGroupTypeAlertDtlsByImpactLevelWise  method in WebServiceHandlerService");
		  }
		  return returnList;
	  }
	 
	 public List<AlertCommentVO> getTotalAlertGroupByDist(String fromDateStr, String toDateStr, Long stateId,String scopeIdList, Long activityMemberId,Long alertTypeId, Long editionId){
		 List<AlertCommentVO> returnList = null;
		  try{			  
			  returnList = alertsNewsPortalService.getTotalAlertGroupByDist(fromDateStr, toDateStr, stateId, scopeIdList, activityMemberId, alertTypeId, editionId);			  
		  }catch(Exception e){
			  e.printStackTrace();
				log.error("exception occured in  the getTotalAlertGroupByDist  method in WebServiceHandlerService");
		  }
		  return returnList;
	  }
	 
	 public List<AlertCommentVO> getDepartmentWiseStatusWiseCounts(String fromDateStr, String toDateStr, Long stateId,String scopeIdList){
		 List<AlertCommentVO> returnList = null;
		  try{			  
			  returnList = alertsNewsPortalService.getDepartmentWiseStatusWiseCounts(fromDateStr, toDateStr, stateId, scopeIdList);			  
		  }catch(Exception e){
			  e.printStackTrace();
				log.error("exception occured in  the getDepartmentWiseStatusWiseCounts  method in WebServiceHandlerService");
		  }
		  return returnList;
	  }
	 
	 
	 public List<AlertDataVO> getAlertsData(Long alertId){
		 List<AlertDataVO> returnList = null;
		  try{			  
			  returnList = alertsNewsPortalService.getAlertsData(alertId);			  
		  }catch(Exception e){
			  e.printStackTrace();
				log.error("exception occured in  the getAlertsData  method in WebServiceHandlerService");
		  }
		  return returnList;
	  }
	 
	 public List<AlertDataVO> getAlertAssignedCandidates(Long alertId){
		 List<AlertDataVO> returnList = null;
		  try{			  
			  returnList = alertsNewsPortalService.getAlertAssignedCandidates(alertId);			  
		  }catch(Exception e){
			  e.printStackTrace();
				log.error("exception occured in  the getAlertAssignedCandidates  method in WebServiceHandlerService");
		  }
		  return returnList;
	  }
	 
	 public List<AlertCommentVO> getAlertStatusCommentsTrackingDetails(Long alertId){
		 List<AlertCommentVO> returnList = null;
		  try{			  
			  returnList = alertsNewsPortalService.getAlertStatusCommentsTrackingDetails(alertId);			  
		  }catch(Exception e){
			  e.printStackTrace();
				log.error("exception occured in  the getAlertStatusCommentsTrackingDetails  method in WebServiceHandlerService");
		  }
		  return returnList;
	  }
	 
	 public AlertVerificationVO getAlertVerificationDtls(Long alertId){
		 AlertVerificationVO returnList = null;
		  try{			  
			  returnList = alertsNewsPortalService.getAlertVerificationDtls(alertId);			  
		  }catch(Exception e){
			  e.printStackTrace();
				log.error("exception occured in  the getAlertVerificationDtls  method in WebServiceHandlerService");
		  }
		  return returnList;
	  }
	 
	 public List<AlertCoreDashBoardVO> getDeptWiseStatusWiseAlertDetails(String fromDateStr,String toDateStr,Long stateId,Long deptId,Long statusId){
		 List<AlertCoreDashBoardVO> returnList = null;
		  try{			  
			  returnList = alertsNewsPortalService.getDeptWiseStatusWiseAlertDetails(fromDateStr, toDateStr, stateId, deptId, statusId);			  
		  }catch(Exception e){
			  e.printStackTrace();
				log.error("exception occured in  the getDeptWiseStatusWiseAlertDetails  method in WebServiceHandlerService");
		  }
		  return returnList;
	  }
	 
	 public List<GrievanceAlertVO> getAllGrievancesForCaller(String mobileNo){
		 List<GrievanceAlertVO> returnList = null;
		 try {
			 returnList = alertManagementSystemService.getGovtGrievanceAlertDetails(mobileNo,"",0l,"","",0l,0l);
		} catch (Exception e) {
			log.error("exception occured in  the getAllGrievancesForCaller  method in WebServiceHandlerService");
		}
		 return returnList;
	 }
	 
	 public NotificationDeviceVO getAccommodationDetails(String notificationTypeId,Long constId){
		 NotificationDeviceVO vo = new NotificationDeviceVO();
		 try {
			 String s[] = notificationTypeId.split(",");
			 List<Long> list = new ArrayList<Long>(0);
			 if(s != null && s.length > 0){
				 for (int i = 0; i < s.length; i++) {
					 list.add(Long.parseLong(s[i]));
				}
			 }
			 vo = notificationService.getEventAccommodationParkingDetails(list,constId);
		} catch (Exception e) {
			Log.error("Exception raised at getAccommodationDetails", e);
		}
		 return vo;
	 }
	
	 public String getYouTubeUrls(){
		  String url = null;
		  try{			  
			   url = mahaNaduService.getYouTubeUrls();	
		  }catch(Exception e){
			  e.printStackTrace();
				log.error("exception occured in  the getYouTubeUrls()  method in WebServiceHandlerService");
		  }
		  return url;
	  }
	 
	 public AmsAppLoginVO getAmsAppValidateLoginDetails(String userName,String password){
		 AmsAppLoginVO amsAppLoginVO = new AmsAppLoginVO();
		  try{
			  
			  amsAppLoginVO = loginService.getAmsAppValidateLoginDetails(userName,password);
			  
		  }catch(Exception e){
			  log.error("exception occured in  the getAmsAppValidateLoginDetails  method in WebServiceHandlerService");
		  }
		 return amsAppLoginVO;
	  }
	 
	 public String getOfficerOtpStatus(Long userId,String otpStr){
		 String status = null;
		 try {
			
			 status= alertManagementSystemService.getOfficerOtpStatus(userId,otpStr);
			 
		} catch (Exception e) {
			log.error("exception occured in  the validateOfficerOTP  method in WebServiceHandlerService");
		}
		 return status;
	 }
	 
	 public AmsDataVO getAmsFilterCategoryTypes(Long userId){
		 try {			 
			 
			 AmsDataVO amsDataVO = new AmsDataVO();
			 
			 amsDataVO.setId(userId);
			 amsDataVO.setNewsPaperList(cccDashboardService.getNewsPapaerListForAms());
			 //amsDataVO.setChanelList(cccDashboardService.getChannelListForUserForAms(userId));
			 amsDataVO.setChanelListNew(cccDashboardService.getChannelListForAms());			 
			 //amsDataVO.setDeptListNew(cccDashboardService.getDeptListForAms());
			 //amsDataVO.setDeptList(alertManagementSystemService.getDeptListForUserForAms(userId));
			 
			 amsDataVO.setSocailMediaTypeList(alertManagementSystemService.getSocialMediaTypeListForAms());
			 amsDataVO.setAlertCallCenterTypeIdList(alertManagementSystemService.getAlertCallCenterTypeForAms());
			 amsDataVO.setMondayGrievanceTypeList(alertManagementSystemService.getMondayGrievanceTypeListForAms());
			 amsDataVO.setJanmabhoomiTypeList(alertManagementSystemService.getJanmabhoomiTypeListForAms());
			 amsDataVO.setSpecialGrievanceTypeList(alertManagementSystemService.getSpecialGrievanceTypeListForAms());
			 amsDataVO.setGeneralGrievanceTypeList(alertManagementSystemService.getGeneralGrievanceTypeListForAms());			
			 amsDataVO.setAlertSeverityList(alertManagementSystemService.getAlertSeverityForAms());
			 amsDataVO.setAlertStatusList(alertManagementSystemService.getAlertStatusForAms());
			 amsDataVO.setGovtAlertSubTaksStatusList(alertManagementSystemService.getSubTaskAlertStatusForAms());
			 
			 return amsDataVO;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("exception occured in  the getAmsFilterCategoryTypes  method in WebServiceHandlerService");
		}
		 return null;
	 }
	 
	 public DistrictOfficeViewAlertVO getAmsAppAlertsBasicCounts(AmsDataVO amsDataVO){
		 DistrictOfficeViewAlertVO returnVO = null;
		 try {
			 returnVO = alertManagementSystemService.getAmsAppAlertsBasicCounts(amsDataVO.getId(),amsDataVO.getFromDate(),amsDataVO.getToDate(),amsDataVO.getNewsPaperIdsList(),amsDataVO.getChanelIdsListNew(),
					 amsDataVO.getAlertCallCenterTypeIdsList(),amsDataVO.getSocailMediaTypeIdsList(),amsDataVO.getAlertSeverityIdsList(),amsDataVO.getAlertStatusIdsList(),amsDataVO.getMondayGrievanceTypeIdsList(),
					 amsDataVO.getJanmabhoomiTypeIdsList(),amsDataVO.getSpecialGrievanceTypeIdsList(),amsDataVO.getGeneralGrievanceTypeIdsList(),
					 amsDataVO.getGovtAlertSubTaksStatusIdsList());
			 
		} catch (Exception e) {
			log.error("Exception raised at getAmsAppAlertsBasicCounts", e);
		}
		 return returnVO;
	 }

	 public List<AmsDataVO> getOfficerAlertDetails(AmsDataVO amsDataVO){
		 List<AmsDataVO> finalList = new ArrayList<AmsDataVO>();
		 try {
			
			// Long userId,String countType,String alertType,
				//List<Long> printIdsList, List<Long> electronicIdsList,List<Long> calCntrIdList,String fromDateStr,String toDateStr,List<Long>
				//socialMediaTypeIds,List<Long> alertSeverityIds, List<Long> alertStatusIds,List<Long> subTaskAlertStatusIds,
				//List<Long> mondayGrievanceTypeIds,List<Long> janmabhoomiTypeIds,List<Long> specialGrievanceTypeIds,List<Long> generalGrievanceTypeIds
				
			 finalList = alertManagementSystemService.getOfficerAlertDetails(amsDataVO.getId(),amsDataVO.getCountType(),amsDataVO.getAlertType(),
					 amsDataVO.getNewsPaperIdsList(),amsDataVO.getChanelIdsListNew(),amsDataVO.getAlertCallCenterTypeIdsList(),amsDataVO.getFromDate(),
					 amsDataVO.getToDate(),amsDataVO.getSocailMediaTypeIdsList(),amsDataVO.getAlertSeverityIdsList(),amsDataVO.getAlertStatusIdsList(),
					 amsDataVO.getGovtAlertSubTaksStatusIdsList(),amsDataVO.getMondayGrievanceTypeIdsList(),
					 amsDataVO.getJanmabhoomiTypeIdsList(),amsDataVO.getSpecialGrievanceTypeIdsList(),amsDataVO.getGeneralGrievanceTypeIdsList());
			 
		} catch (Exception e){
			e.printStackTrace();
			log.error("Exception raised at getOfficerAlertDetails", e);
		}
		 return finalList;
	 }
	 public AmsVO getAlertDetailsInfoForAms(AmsAppLoginVO keyVo){
		 AmsVO finalvo = new AmsVO();
		 try {
			
			 finalvo = alertManagementSystemService.getAlertDetailsInfoForAms(keyVo);
			 
		} catch (Exception e) {
			log.error("Exception raised at getAllAlertDetails", e);
		}
		 return finalvo;
	 }
	 public List<AmsTrackingVO> getSubTaskAlertDetails(AmsAppLoginVO keyVo){
		 List<AmsTrackingVO> finalVOLst = new ArrayList<AmsTrackingVO>(0);
		 try {
			
			 finalVOLst = alertManagementSystemService.getSubTaskInfoForAlertForAms(keyVo);
			 
		} catch (Exception e) {
			log.error("Exception raised at getSubTaskAlertDetails", e);
		}
		 return finalVOLst;
	 }
	 public List<AmsAppVO> getSubTaskAlertDetailedInfo(AmsAppLoginVO keyVo){
		 List<AmsAppVO> finalVOLst = new ArrayList<AmsAppVO>(0);
		 try {
			
			 finalVOLst = alertManagementSystemService.getStatusCompletionInfoForSubTaskForAms(keyVo);
			 
		} catch (Exception e) {
			log.error("Exception raised at getSubTaskAlertDetails", e);
		}
		 return finalVOLst;
	 }
	 
	
	 public List<AmsKeyValueVO> getGovtAllDepartmentDetailsForAms(){
		 List<AmsKeyValueVO> finalVOLst = new ArrayList<AmsKeyValueVO>(0);
		 try {
			
			 finalVOLst = alertManagementSystemService.getGovtAllDepartmentDetailsForAms();
			 
		} catch (Exception e) {
			log.error("Exception raised at getGovtAllDepartmentDetailsForAms", e);
		}
		 return finalVOLst;
	 }
	 public List<AmsKeyValueVO> getSubDeptsFrParentDeptForAms(AmsAppLoginVO keyVo){
		 List<AmsKeyValueVO> finalVOLst = new ArrayList<AmsKeyValueVO>(0);
		 try {
			
			 finalVOLst = alertManagementSystemService.getSubDeptsFrParentDeptForAms(keyVo);
			 
		} catch (Exception e) {
			log.error("Exception raised at getSubDeptsFrParentDeptForAms", e);
		}
		 return finalVOLst;
	 }
	 public List<AmsKeyValueVO> getDepartmentLevelsForAms(AmsAppLoginVO keyVo){
		 List<AmsKeyValueVO> finalVOLst = new ArrayList<AmsKeyValueVO>(0);
		 try {
			
			 finalVOLst = alertManagementSystemService.getDepartmentLevelsForAms(keyVo);
			 
		} catch (Exception e) {
			log.error("Exception raised at getDepartmentLevelsForAms", e);
		}
		 return finalVOLst;
	 }
	 public List<AmsKeyValueVO> getParentLevelsOfLevelForAms(AmsAppLoginVO keyVo){
		 List<AmsKeyValueVO> finalVOLst = new ArrayList<AmsKeyValueVO>(0);
		 try {
			
			 finalVOLst = alertManagementSystemService.getParentLevelsOfLevelForAms(keyVo);
			 
		} catch (Exception e) {
			log.error("Exception raised at getParentLevelsOfLevelForAms", e);
		}
		 return finalVOLst;
	 }
	 public List<AmsKeyValueVO> getDesignationsByDepartmentForAms(AmsAppLoginVO keyVo){
		 List<AmsKeyValueVO> finalVOLst = new ArrayList<AmsKeyValueVO>(0);
		 try {
			
			 finalVOLst = alertManagementSystemService.getDesignationsByDepartmentForAms(keyVo);
			 
		} catch (Exception e) {
			log.error("Exception raised at getDesignationsByDepartmentForAms", e);
		}
		 return finalVOLst;
	 }
	 public List<AmsKeyValueVO> getOfficersByDesignationAndLevelForAms(AmsAppLoginVO keyVo){
		 List<AmsKeyValueVO> finalVOLst = new ArrayList<AmsKeyValueVO>(0);
		 try {
			
			 finalVOLst = alertManagementSystemService.getOfficersByDesignationAndLevelForAms(keyVo);
			 
		} catch (Exception e) {
			log.error("Exception raised at getOfficersByDesignationAndLevelForAms", e);
		}
		 return finalVOLst;
	 }
	 public ResultStatus updateAlertStatusCommentForAms(AmsAppLoginVO keyVo){
		 ResultStatus resultStatus = new ResultStatus();
		 try {
			
			 resultStatus = alertManagementSystemService.updateAlertStatusCommentForAms(keyVo);
			 
		} catch (Exception e) {
			log.error("Exception raised at updateAlertStatusCommentForAms", e);
		}
		 return resultStatus;
	 }
	 public List<DistrictOfficeViewAlertVO> getLocationWiseDepartmentOverviewAlertCountForAms(AmsDataVO amsDataVO){
		 List<DistrictOfficeViewAlertVO> returnVOList = new ArrayList<DistrictOfficeViewAlertVO>(0);
		 try {
			 returnVOList = alertManagementSystemService.getLocationWiseDepartmentOverviewAlertCountForAms(amsDataVO.getFromDate(),amsDataVO.getToDate(),amsDataVO.getStateId(),
					 amsDataVO.getPrintIdsLst(),amsDataVO.getElectronicIdsLst(),amsDataVO.getUserId(),amsDataVO.getDepartmentId()
					 ,amsDataVO.getParentGovtDepartmentScopeId(),amsDataVO.getSortingType(),amsDataVO.getOrder(),amsDataVO.getAlertType(),
					 amsDataVO.getDistrictWorkLocationId(),amsDataVO.getDivisionWorkLocationId(),amsDataVO.getSubDivisionWorkLocationId(),
					 amsDataVO.getGroup(),amsDataVO.getCalCntrIdList(),amsDataVO.getSublevels(),amsDataVO.getFilterParentScopeId(),
					 amsDataVO.getFilterScopeValue(),amsDataVO.getSearchType(),amsDataVO.getSocailMediaTypeIdsList(),amsDataVO.getAlertSeverityIdsList(),
					 amsDataVO.getAlertStatusIdsList(),amsDataVO.getSubTaskAlertStatusIds(),amsDataVO.getMondayGrievanceTypeIdsList(),
					 amsDataVO.getJanmabhoomiTypeIdsList(),amsDataVO.getSpecialGrievanceTypeIdsList(),amsDataVO.getGeneralGrievanceTypeIdsList());
		} catch (Exception e) {
			log.error("Exception raised at getLocationWiseDepartmentOverviewAlertCountForAms", e);
		}
		 return returnVOList;
	 }
	 public ResultStatus saveDocumentsForAlertForAms(AmsAppLoginVO keyVo){
		 ResultStatus resultStatus = new ResultStatus();
		 try {
			
			 resultStatus = alertManagementSystemService.saveDocumentsForAlertForAms(keyVo);
			 
		} catch (Exception e) {
			log.error("Exception raised at saveDocumentsForAlertForAms", e);
		}
		 return resultStatus;
	 }
	 
	 public List<IdNameVO> getAlertDetailsOfCategoryByStatusWise(JalavaniVO VO){
		 try {
			 
			return alertManagementSystemService.getAlertDetailsOfCategoryByStatusWise(VO);
			
		} catch (Exception e) {
			log.error("Exception raised at getAlertDetailsOfCategoryByStatusWise", e);
		}
		 return null;
	 }
	 public List<IdNameVO> getAlertFeedbackStatusDetails(JalavaniVO VO){
		 try {
			 
			return alertManagementSystemService.getAlertFeedbackStatusDetails(VO);
			
		} catch (Exception e) {
			log.error("Exception raised at getAlertFeedbackStatusDetails", e);
		}
		 return null;
	 }
	 public List<AmsDataVO> getAlertsOfCategoryByStatusWise(JSONObject obj){
		 try {
			 JalavaniVO jalavani = new JalavaniVO();
			 
 			 
 			jalavani.setFromDate(obj.getString("fromDate") !=null && !obj.getString("fromDate").trim().isEmpty() ? obj.getString("fromDate").trim():null);
 			jalavani.setToDate(obj.getString("toDate") !=null && !obj.getString("toDate").trim().isEmpty() ? obj.getString("toDate").trim():null);
 			jalavani.setStartIndex(obj.getInt("startIndex"));
 			jalavani.setEndIndex(obj.getInt("endIndex"));	
 			//jalavani.setType(obj.getString("type") !=null && !obj.getString("type").trim().isEmpty() ? obj.getString("type").trim():null);//need to remove
 			jalavani.setYear(obj.getString("year") !=null && !obj.getString("year").trim().isEmpty() ? obj.getString("year").trim():null);
 			jalavani.setDeptId(obj.getLong("deptId"));
 			jalavani.setLocationId(obj.getLong("locationTypeId"));//locationValues
 			
 			JSONArray locationArr = obj.getJSONArray("locationValues");
 			
 			if(locationArr != null && locationArr.length() > 0){
					for (int j = 0; j < locationArr.length(); j++) {						
						jalavani.getLevelValues().add(Long.parseLong(locationArr.get(j).toString()));					
				}
			}
 			
 			JSONArray statusListArray = obj.getJSONArray("statusIds");
 			
 			if(statusListArray != null && statusListArray.length() > 0){
					for (int j = 0; j < statusListArray.length(); j++) {						
						jalavani.getStatusIds().add(Long.parseLong(statusListArray.get(j).toString()));					
				}
			}
			
			return alertManagementSystemService.getAlertsOfCategoryByStatusWise(jalavani);
			
		} catch (Exception e) {
			log.error("Exception raised at getAlertsOfCategoryByStatusWise", e);
		}
		 return null;
	 }
	 
	 public List<KeyValueVO> getLocationWiseAlertStatusCounts(Long departmentId,String fromDate,String toDate,String year,Long locationTypeId,List<Long> locationValues,
			 Long searchLevelId,List<Long> searchLevelValues){
		 try {
			return alertManagementSystemService.getLocationWiseAlertStatusCounts(departmentId,fromDate,toDate,year,locationTypeId,locationValues,
					searchLevelId,searchLevelValues);
		} catch (Exception e) {
			log.error("Exception raised at getAlertsOfCategoryByStatusWise", e);
		}
		 return null;
	 }
	 
	 public List<AlertVO> getHamletWiseIvrStatusCounts(String fromDate,String toDate,String year,List<Long> locationValues,Long locationTypeId,
			 Long searchLevelId,List<Long> searchLevelValues){
		 try {
			return alertManagementSystemService.getHamletWiseIvrStatusCounts(fromDate,toDate,year,locationValues,locationTypeId,searchLevelId,searchLevelValues);
		} catch (Exception e) {
			log.error("Exception raised at getHamletWiseIvrStatusCounts", e);
		}
		 return null;
	 }
	 public AmsKeyValueVO getDistrictWiseInfoForAms(AmsAppLoginVO keyVo){
		 AmsKeyValueVO finalVO = new AmsKeyValueVO();
		 try {
			
			 finalVO = alertManagementSystemService.getDistrictWiseInfoForAms(keyVo.getDepartmentId(),keyVo.getLevelId(),keyVo.getLevelValue());
			 
		} catch (Exception e) {
			log.error("Exception raised at getDistrictWiseInfoForAms", e);
		}
		 return finalVO;
	 }
	 public List<DistrictOfficeViewAlertVO> getTotalAlertByOtherStatusForAMS(AmsDataVO keyVo){
		 List<DistrictOfficeViewAlertVO> returnVOList = new ArrayList<DistrictOfficeViewAlertVO>(0);
		 try {
			 returnVOList = alertManagementSystemService.getTotalAlertByOtherStatusForAMS(keyVo.getFromDate(),keyVo.getToDate(),
					 keyVo.getStateId(),keyVo.getPrintIdsLst(),keyVo.getElectronicIdsLst(),keyVo.getDeptIdsList(),keyVo.getStatusId(),
					 keyVo.getUserId(),keyVo.getParentGovtDepartmentScopeId(),keyVo.getDepartmentId(),keyVo.getCalCntrIdList(),keyVo.getAlertSeverityIdsList(),
					 keyVo.getAlertStatusIdsList(),keyVo.getAlertCallCenterTypeIdsList(),keyVo.getNewsPaperIdsList(),keyVo.getChanelIdsList(),
					 keyVo.getSocailMediaTypeIdsList(),keyVo.getMondayGrievanceTypeIdsList(),keyVo.getJanmabhoomiTypeIdsList(),keyVo.getSpecialGrievanceTypeIdsList(),
					 keyVo.getGeneralGrievanceTypeIdsList());
		} catch (Exception e) {
			log.error("Exception raised at getTotalAlertByOtherStatusForAMS", e);
		}
		 return returnVOList;
	 }
	 public CommitteeBasicVO getLocationWiseCommitteesCount(String locationType, Long locationId,Long enrollmentId){
		 try{
			 return locationDashboardService.getLocationWiseCommitteesCount(locationType, locationId, enrollmentId);
		 }catch(Exception e){
			 log.error("Exception raised at getLocationWiseCommitteesCount", e);
		 }
		 return null;
	 }
	 
	 public List<BasicVO> getEnrollments(List<Long> publicationDatesIds){
		 try{
			 return locationDashboardService.getEnrollmentIds(publicationDatesIds);
		 }catch(Exception e){
			 log.error("Exception raised at getEnrollments", e);
		 }
		return null;
	 }
	 
	 public AppointmentCountDetailsVO getAppointmentCandidateCountDeatils(Long userId){
		 AppointmentCountDetailsVO finalVO = new AppointmentCountDetailsVO();
		 try {
			
			 finalVO = appointmentService.getAppointmentCandidateCountDeatils(userId);
			 
		} catch (Exception e) {
			log.error("Exception raised at getAppointmentCandidateCountDeatils", e);
		}
		 return finalVO;
	 }
	 public List<AppointmentCountDetailsVO> getAppointmentCandidateDetails(String fromDateStr,String toDateStr,Long userId){
		 List<AppointmentCountDetailsVO> returnVOList = new ArrayList<AppointmentCountDetailsVO>(0);
		 try {
			 returnVOList = appointmentService.getAppointmentCandidateDetails(fromDateStr,toDateStr,userId);
		} catch (Exception e) {
			log.error("Exception raised at getAppointmentCandidateDetails", e);
		}
		 return returnVOList;
	 } 
	public ResultStatus checkMemberWalkInForToday( String memberShipId, String date ,  String uniqueId, Long loginUserId,Long tabPrimaryKey,String isCheckedStatus){
		 try{
			 return appointmentService.checkMemberWalkInForToday(memberShipId, date, uniqueId,loginUserId,tabPrimaryKey, isCheckedStatus);
		 }catch(Exception e){
			 log.error("Exception raised at checkMemberWalkInForToday", e);
		 }
		 return null;
	 }
	

	public List<AlertVO> getHamletWiseIvrStatusList(String fromDateStr, String toDateStr, String year,
			List<Long> locationValues, Long locationTypeId, String statusType) {
		try{
			return alertManagementSystemService.getHamletWiseIvrStatusList(fromDateStr, toDateStr, year, locationValues, locationTypeId, statusType);
		}catch(Exception e){
			log.error("Exception raised at getHamletWiseIvrStatusList", e);
		}
		return null;
	}
	public List<AlertVO> getDrainsIvrStatusCounts(String fromDateStr,String toDateStr,List<Long> locationValues,Long locationTypeId,
			 Long searchlevelId,List<Long> searchLevelValues,Long entityType,List<Long> questionsList,List<String> selectedDatesStr){
		try {
			
			return alertManagementSystemService.getDrainsIvrStatusCounts(fromDateStr, toDateStr, locationValues, locationTypeId, searchlevelId,
					searchLevelValues,entityType,questionsList,selectedDatesStr);
			
		} catch (Exception e) {
			log.error("Exception raised at getDrainsIvrStatusCounts in WebServiceHandlerService class  ", e);
		}
		return null;
	}
	
	public List<AlertVO> getOverAllIvrDetails(String fromDateStr,String toDateStr,Long entityType,List<Long> questionsList,String type){
		try {
			
			return alertManagementSystemService.getOverAllIvrDetails(fromDateStr,toDateStr,entityType,questionsList,type);
			
		} catch (Exception e) {
			log.error("Exception raised at getOverAllIvrDetails in WebServiceHandlerService class  ", e);
		}
		return null;
	}
	
	public List<IdNameVO> getIvrSurveyDates(String fromDateStr,String toDateStr,Long entityType){
		try {
			return alertManagementSystemService.getIvrSurveyDates(fromDateStr,toDateStr,entityType);
		} catch (Exception e) {
			log.error("Exception raised at getIvrSurveyDates in WebServiceHandlerService class  ", e);
		}
		return null;
	}
	
	public List<IdNameVO> getIvrSurveyQuestions(String fromDateStr,String toDateStr,Long entityType){
		try {
			return alertManagementSystemService.getIvrSurveyQuestions(fromDateStr,toDateStr,entityType);
		} catch (Exception e) {
			log.error("Exception raised at getIvrSurveyQuestions in WebServiceHandlerService class  ", e);
		}
		return null;
	}
	
	public List<BasicVO> getElectionTypes(){
		 try{
			 return locationDashboardService.getElectionTypes();
		 }catch(Exception e){
			 log.error("Exception raised at getElectionTypes", e);
		 }
		return null;
	 }
	
	public List<BasicVO> getPublications(){
		 try{
			 return locationDashboardService.getPublications();
		 }catch(Exception e){
			 log.error("Exception raised at getPublications", e);
		 }
		return null;
	}
	
	public void saveEventResponses(String anserObjStr){
		try {
			log.error(" Entered into saveEventResponses method in WebServiceHandleService Class ");
			kaizalaInfoService.saveEventResponses(anserObjStr);
		} catch (Exception e) {
			 log.error("Exception raised at saveKaizalAnswerInfo", e);
		}
	}
	
	/*public Long getAddressId(Long locationScopeId, Long locationValue){
		try{
			return cadreDetailsService.saveUserAddressDetails(locationScopeId, locationValue);
		}catch (Exception e) {
			log.error("Exception raised at getAddressId for kaizala user addressId", e);
		}
		return null;
	}*/
	
	
	public Long getKaizalaAddressId(Long locationScopeId, Long locationValue){
		try{
			return cadreDetailsService.kaizalaLocationAddressIdSaving(locationScopeId, locationValue);
		}catch (Exception e) {
			log.error("Exception raised at getAddressId for kaizala user addressId", e);
		}
		return null;
	}
	public Long kaizalaCommitteeLevelAddressSaving(Long locationScopeId, Long locationValue){
		try{
			return cadreDetailsService.kaizalaCommitteeLevelAddressSaving(locationScopeId, locationValue);
		}catch (Exception e) {
			log.error("Exception raised at kaizalaCommitteeLevelAddressSaving method in WebServiceHandlerService Class", e);
		}
		return null;
	}
	 public List<QuestionAnswerVO> getSurveyQuestionWithMarksDetailsByTDpCadreId(Long tdpCadreId){
		 List<QuestionAnswerVO> finalList = new ArrayList<QuestionAnswerVO>(0);
		  try {
			  Client client = Client.create();
			  client.addFilter(new HTTPBasicAuthFilter(IConstants.SURVEY_WEBSERVICE_USERNAME, IConstants.SURVEY_WEBSERVICE_PASSWORD));
			  WebResource webResource = client.resource("https://www.mytdp.com/Survey/WebService/getSurveyQuestionWithMarksDetailsByTDpCadreId/"+tdpCadreId);
				//WebResource webResource = client.resource("http://192.168.11.173:8080/Survey/WebService/getSurveyQuestionWithMarksDetailsByTDpCadreId/"+tdpCadreId);
			  ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);
   	 	  if (response.getStatus() != 200) {
   	 		finalList =null;
   	 		//throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
   	 	 }else{
 	    	 String output = response.getEntity(String.class);
 	    	if(output != null && !output.isEmpty()){
 	    		JSONArray finalArray = new JSONArray(output);
 	    		if(finalArray!=null && finalArray.length()>0){
 	    			for(int i=0;i<finalArray.length();i++){
 	    				QuestionAnswerVO vo =  new QuestionAnswerVO();
	   	 					JSONObject question = (JSONObject) finalArray.get(i);
		   	 				
	   	 				if(question.has("questionId"))
   	 					{
   	 						vo.setSurveyId(question.getLong("questionId"));
   	 					}
	   	 			    if(question.has("surveyName"))
	 					{
	 						vo.setSurveyName(question.getString("surveyName"));
	 					}	
	   	 			    if(question.has("surveyTypeId"))
	 					{
	 						vo.setSurveyTypeId(question.getLong("surveyTypeId"));
	 					}	
		   	 			if(question.has("designation"))
	 					{
	 						vo.setPercentage(question.getString("designation"));
	 					}	
	   	 			JSONArray  questionsList = question.getJSONArray("subList");
	   	 			if(questionsList != null && questionsList.length()>0)
	   	 			{
	   	 			List<QuestionAnswerVO> questionWiseList = new ArrayList<QuestionAnswerVO>(0);
	   	 				for(int j=0;j<questionsList.length();j++)
	   	 				{
	   	 				JSONObject questionObj = (JSONObject) questionsList.get(j);
	   	 					QuestionAnswerVO questionVO = new QuestionAnswerVO();
		   	 				if(question.has("questionId"))
	   	 					{
		   	 					questionVO.setQuestionId(questionObj.getLong("questionId"));
	   	 					}

	   	 					if(question.has("question"))
	   	 					{
	   	 						questionVO.setQuestion(questionObj.getString("question"));
	   	 					}
		   	 				if(question.has("name"))
	   	 					{
		   	 					questionVO.setName(questionObj.getString("name"));//correct answer
	   	 					}
			   	 			if(question.has("marks"))
	   	 					{
			   	 				questionVO.setMarks(questionObj.getString("marks"));
	   	 					}
				   	 		if(question.has("voterName"))
	   	 					{
			   	 				questionVO.setCandidateName(questionObj.getString("voterName"));//Given option
	   	 					}
			   	 			questionWiseList.add(questionVO);
	   	 				}
	   	 			vo.setSubList(questionWiseList);
	   	 			}
			   	 		finalList.add(vo);
 	    			}
 	    		}
 	    	}
   	 	 }
	  }catch(Exception e){
		  log.error("Exception raised at getSurveyQuestionWithMarksDetailsByTDpCadreId method in WebServiceHandlerService Class", e);
	  }
		return finalList;
	  }
	  public List<QuestionAnswerVO> getSurveyQuestionDetails(Long tdpCadreId){
			 List<QuestionAnswerVO> finalList = new ArrayList<QuestionAnswerVO>(0);
		  try {
			  Client client = Client.create();
			  client.addFilter(new HTTPBasicAuthFilter(IConstants.SURVEY_WEBSERVICE_USERNAME, IConstants.SURVEY_WEBSERVICE_PASSWORD));
			  WebResource webResource = client.resource("https://www.mytdp.com/Survey/WebService/getSurveyQuestionsDetails/"+tdpCadreId);
			  //WebResource webResource = client.resource("http://192.168.11.173:8080/Survey/WebService/getSurveyQuestionsDetails/"+tdpCadreId);
			  ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);
	 	  if (response.getStatus() != 200) {
	 		   finalList =null;
	 		//throw new RuntimeException("Failed : HTTP error code : "+ response.getStatus());
	 	 }else{

	    	 String output = response.getEntity(String.class);
	    	if(output != null && !output.isEmpty()){
	    		JSONArray finalArray = new JSONArray(output);
	    		if(finalArray!=null && finalArray.length()>0){
	    			for(int i=0;i<finalArray.length();i++){
	    				QuestionAnswerVO vo =  new QuestionAnswerVO();
	   	 					JSONObject question = (JSONObject) finalArray.get(i);
	   	 			    if(question.has("surveyName"))
	 					{
	 						vo.setSurveyName(question.getString("surveyName"));
	 					}	
	   	 			    if(question.has("surveyTypeId"))
	 					{
	 						vo.setSurveyTypeId(question.getLong("surveyTypeId"));
	 					}	
	   	 			    if(question.has("questionId"))
	 					{
	 						vo.setQuestionId(question.getLong("questionId"));
	 					}
	   	 			    if(question.has("question"))
	 					{
	 						vo.setQuestion(question.getString("question"));
	 					}
	   	 			    if(question.has("id"))
	 					{
	 						vo.setId(question.getLong("id"));//givedOptionId
	 					}
	   	 			    if(question.has("name"))
	 					{
	 						vo.setName(question.getString("name"));
	 					}
		   	 			if(question.has("cadreId"))
	 					{
	 						vo.setUserId(question.getLong("cadreId"));//actual optionId
	 					}
			   	 		if(question.has("voterName"))
	 					{
	 						vo.setCandidateName(question.getString("voterName"));
	 					}
			   	 		finalList.add(vo);
	    			}
	    		}
	    	}
	 	 
	 		 
	 	 }
	  }catch(Exception e){
		  log.error("Exception raised at getSurveyQuestionDetails method in WebServiceHandlerService Class", e);
	  }
		return finalList;
	  }
	  
	 //MOM API
	 public PartyMeetingMOMDtlsVO getPartyMeetingMOMDetails(Long userAccessLevel,List<Long> accessValues,String monthYear){
			try{
				return partyMeetingMOMService.getPartyMeetingMOMDetails(userAccessLevel, accessValues,monthYear);
			}catch (Exception e) {
				log.error("Exception raised at getPartyMeetingMOMDetails method in WebServiceHandlerService Class", e);
			}
			return null;
	 }
	 public PartyMeetingMOMPointsDtlsVO getPartyMeetingMOMPointsDocumentDetails(Long userAccessLevel,List<Long> accessValues,String monthYear,Long parytMeetingId){
			try{
				return partyMeetingMOMService.getPartyMeetingMOMPointsDocumentDetails(userAccessLevel, accessValues,monthYear,parytMeetingId);
			}catch (Exception e) {
				log.error("Exception raised at getPartyMeetingMOMPointsDocumentDetails method in WebServiceHandlerService Class", e);
			}
			return null;
	 }
	 public String updateMOMMeetingDetails(Long meetingId, String conductedDate,String isConducted,String remarks,Long loginUserId){
			try{
				return partyMeetingMOMService.updateMOMMeetingDetails(meetingId, conductedDate, isConducted,remarks,loginUserId);
			}catch (Exception e) {
				log.error("Exception raised at updateMOMMeetingDetails method in WebServiceHandlerService Class", e);
			}
			return null;
	 }
	 public String deleteMOMMeetingDetails(Long id, String deletedType,Long loginUserId){
			try{
				return partyMeetingMOMService.deleteMOMMeetingDetails(id, deletedType, loginUserId);
			}catch (Exception e) {
				log.error("Exception raised at deleteMOMMeetingDetails method in WebServiceHandlerService Class", e);
			}
			return null;
	 }
	 public ResultStatus savePartyMeetingMOMDetails(PartyMeetingMOMCreationDtlsvO inputVO){
			try{
				return partyMeetingMOMService.savePartyMeetingMOMDetails(inputVO);
			}catch (Exception e) {
				log.error("Exception raised at savePartyMeetingMOMDetails method in WebServiceHandlerService Class", e);
			}
			return null;
	 }
	 public ResultStatus updateMomDetails(PartyMeetingMOMCreationDtlsvO inputVO){
			try{
				return partyMeetingMOMService.updateMomDetails(inputVO);
			}catch (Exception e) {
				log.error("Exception raised at updateMomDetails method in WebServiceHandlerService Class", e);
			}
			return null;
	 }
	 public MomDetailsVO getMomCompletedDetails(Long partyMeetingMinuteId){
			try{
				return partyMeetingMOMService.getMomCompletedDetails(partyMeetingMinuteId);
			}catch (Exception e) {
				log.error("Exception raised at getMomCompletedDetails method in WebServiceHandlerService Class", e);
			}
			return null;
	 }
	 public MomDashbaordOverViewDtlsVO getMomDashboardOverviewDtls(Long userAccessLevel,List<Long> accessValues,String monthYear){
			try{
				return partyMeetingMOMService.getMomDashboardOverviewDtls(userAccessLevel, accessValues,monthYear);
			}catch (Exception e) {
				log.error("Exception raised at getMomDashboardOverviewDtls method in WebServiceHandlerService Class", e);
			}
			return null;
	 }
	 public List<MomDetailsVO> getMomDetailsBySelectedType(Long userAccessLevel,List<Long> accessValues,String monthYear,String type){
			try{
				return partyMeetingMOMService.getMomDetailsBySelectedType(userAccessLevel, accessValues,monthYear,type);
			}catch (Exception e) {
				log.error("Exception raised at getMomDetailsBySelectedType method in WebServiceHandlerService Class", e);
			}
			return null;
	 }
	 //JANMABHOOMI API
	 public List<ActivityDetailsVO> getActivityDetailsBasedOnLocation(String locationType,List<Long> locationValues,Long activityScopeId,Long constituencyId){
			try{
				return activityService.getActivityDetailsBasedOnLocation(locationType,locationValues,activityScopeId,constituencyId);
			}catch (Exception e) {
				log.error("Exception raised at getActivityDetailsBasedOnLocation method in WebServiceHandlerService Class", e);
			}
			return null;
	 }
	 public ResultStatus updateActivityInfo(ActivityDetailsVO tabDetaislVO,String locationType, Long activityScopeId,Long locationValue, String conductedDate,String updateStatus){
			try{
				return activityService.updateActivityInfo(tabDetaislVO,locationType, activityScopeId, locationValue, conductedDate, updateStatus);
			}catch (Exception e) {
				log.error("Exception raised at updateActivityInfo method in WebServiceHandlerService Class", e);
			}
			return null;
	 }
	 public List<ActivityDetailsVO> getActivityQuestionOptionDtls(Long activityScopeId,Long activiyLocationInfoId){
			try{
				return activityService.getActivityQuestionOptionDtls(activityScopeId,activiyLocationInfoId);
			}catch (Exception e) {
				log.error("Exception raised at getActivityQuestionOptionDtls method in WebServiceHandlerService Class", e);
			}
			return null;
	 }
	 public ResultStatus saveActivityAnswerDetails(ActivityDetailsVO inputVO){
			try{
				return activityService.saveActivityAnswerDetails(inputVO);
			}catch (Exception e) {
				log.error("Exception raised at saveActivityAnswerDetails method in WebServiceHandlerService Class", e);
			}
			return null;
	 }
	 public ResultStatus uploadDocumentImage(ActivityDetailsVO inputVO,List<String> docuemntBase64List){
			try{
				return activityService.uploadDocumentImage(inputVO,docuemntBase64List);
			}catch (Exception e) {
				log.error("Exception raised at uploadDocumentImage method in WebServiceHandlerService Class", e);
			}
			return null;
	 }
	 public List<ActivityDetailsVO> getDocumentDtlsByLocation(Long activityScopeId, Long activityLocationInfoId){
			try{
				return activityService.getDocumentDtlsByLocation(activityScopeId,activityLocationInfoId);
			}catch (Exception e) {
				log.error("Exception raised at getDocumentDtlsByLocation method in WebServiceHandlerService Class", e);
			}
			return null;
	 }
	 
	public List<AffiliatedMemberVO> searchAffiliatedMemberDetails(String searchType,String searchValue,String locationType, Long locationValue) {
		List<AffiliatedMemberVO> affiliatedMemberList=new ArrayList<AffiliatedMemberVO>();
		try{
			affiliatedMemberList = affiliatedMember.searchAffiliatedMemberDetails(searchType,searchValue, locationType,locationValue);
    	}catch(Exception e){
    		log.error("Exception raised in searchAffiliatedMemberDetails  method in WebServiceHandlerService1",e);
    	}
    	return affiliatedMemberList;
	}

	@Override
	public AffiliatedMemberVO saveAffiliatedMemberDetails(JSONObject jobj) {
		AffiliatedMemberVO vo = null;
		try{
			ITdpAppUser appuser= itdpAppUserDAO.get(jobj.getLong("appuserId"));
			vo = affiliatedMember.saveAffiliatedMemberDetails(jobj,appuser.getIsDeleted());
		}catch(Exception e){
			log.error("Exception raised in saveAffiliatedMemberDetails  method in WebServiceHandlerService1",e);

		}
		return vo;
	}
}