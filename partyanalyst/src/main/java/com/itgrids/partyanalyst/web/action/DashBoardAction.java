package com.itgrids.partyanalyst.web.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.BirthDayDetailsVO;
import com.itgrids.partyanalyst.dto.CrossVotingVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.helper.EntitlementsHelper;
import com.itgrids.partyanalyst.service.IAnanymousUserService;
import com.itgrids.partyanalyst.service.IBirthDayDetailsService;
import com.itgrids.partyanalyst.service.IConstituencySearchService;
import com.itgrids.partyanalyst.service.ICrossVotingEstimationService;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.itgrids.partyanalyst.service.IUserProfileService;
import com.itgrids.partyanalyst.service.IVotersAnalysisService;
import com.itgrids.partyanalyst.service.impl.CadreManagementService;
import com.itgrids.partyanalyst.util.IWebConstants;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class DashBoardAction extends ActionSupport implements ServletRequestAware{

	
	private static final org.apache.log4j.Logger LOG = Logger.getLogger(DashBoardAction.class); 
	
	private HttpServletRequest request;
	private List<SelectOptionVO> statesList,statesListForLocalBodyElection;
	private IStaticDataService staticDataService;
	private HttpSession session;
	private List<SelectOptionVO> electionYearList,partysList;
	private List<SelectOptionVO> constituencyList, userAccessConstituencyList,assemblyConstis,parlConstis,parliaments;
	private ICrossVotingEstimationService crossVotingEstimationService;
	private IVotersAnalysisService votersAnalysisService;
	private CadreManagementService cadreManagementService;
	private SelectOptionVO boothAnalysisData;
	private EntitlementsHelper entitlementsHelper;
	private boolean party;
	JSONObject jObj;
	private String task;
	private List<SelectOptionVO> electionYearsList,elecYears,partiesList;
	private Long constituencyId;
	private Long districtId;
	private Long stateId;
	private List<SelectOptionVO> pConstituencyList,aConstituencyList;
	private IConstituencySearchService constituencySearchService;
	private List<SelectOptionVO> districtStateList;
	private List<SelectOptionVO> mandalList;
	private List<SelectOptionVO> panchayatsList;
	private List<SelectOptionVO> boothsList;
	private String loginUserProfilePic;
	private IAnanymousUserService ananymousUserService;
	private String loginUserName;
	private String profileUserName;
	private CrossVotingVO crossVotingVO;
	private Long crossVotingYear;
	private Long crossVotingPConsti;
	private Long crossVotingAConsti;
	private Long crossVotingParty;
	private List<SelectOptionVO> mandalsList;
	private boolean politician;
	private boolean hhEntitled;
	private List<SelectOptionVO> districtsList;
	private List<SelectOptionVO> publicationDatesList;
	private boolean infoManager;
	private List<SelectOptionVO>				selectOptionVOList;
	private List<BirthDayDetailsVO> birthDaysList;
	private IBirthDayDetailsService birthDayDetailsService;
	private IUserProfileService userProfileService;
	private String redirectUrl;

	public String getRedirectUrl() {
		return redirectUrl;
	}

	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}

	public void setUserProfileService(IUserProfileService userProfileService) {
		this.userProfileService = userProfileService;
	}

	public IBirthDayDetailsService getBirthDayDetailsService() {
		return birthDayDetailsService;
	}

	public void setBirthDayDetailsService(
			IBirthDayDetailsService birthDayDetailsService) {
		this.birthDayDetailsService = birthDayDetailsService;
	}

	public List<BirthDayDetailsVO> getBirthDaysList() {
		return birthDaysList;
	}

	public void setBirthDaysList(List<BirthDayDetailsVO> birthDaysList) {
		this.birthDaysList = birthDaysList;
	}

	
	public List<SelectOptionVO> getSelectOptionVOList() {
		return selectOptionVOList;
	}


	public void setSelectOptionVOList(List<SelectOptionVO> selectOptionVOList) {
		this.selectOptionVOList = selectOptionVOList;
	}


	public EntitlementsHelper getEntitlementsHelper() {
		return entitlementsHelper;
	}


	public void setEntitlementsHelper(EntitlementsHelper entitlementsHelper) {
		this.entitlementsHelper = entitlementsHelper;
	}


	public String getProfileUserName() {
		return profileUserName;
	}


	public void setProfileUserName(String profileUserName) {
		this.profileUserName = profileUserName;
	}


	public List<SelectOptionVO> getDistrictsList() {
		return districtsList;
	}


	public void setDistrictsList(List<SelectOptionVO> districtsList) {
		this.districtsList = districtsList;
	}


	public List<SelectOptionVO> getPublicationDatesList() {
		return publicationDatesList;
	}


	public void setPublicationDatesList(List<SelectOptionVO> publicationDatesList) {
		this.publicationDatesList = publicationDatesList;
	}


	public boolean isHhEntitled() {
		return hhEntitled;
	}


	public void setHhEntitled(boolean hhEntitled) {
		this.hhEntitled = hhEntitled;
	}


	public List<SelectOptionVO> getMandalsList() {
		return mandalsList;
	}


	public void setMandalsList(List<SelectOptionVO> mandalsList) {
		this.mandalsList = mandalsList;
	}


	public boolean isPolitician() {
		return politician;
	}


	public void setPolitician(boolean politician) {
		this.politician = politician;
	}


	public List<SelectOptionVO> getElectionYearsList() {
		return electionYearsList;
	}


	public void setElectionYearsList(List<SelectOptionVO> electionYearsList) {
		this.electionYearsList = electionYearsList;
	}


	public List<SelectOptionVO> getElecYears() {
		return elecYears;
	}


	public void setElecYears(List<SelectOptionVO> elecYears) {
		this.elecYears = elecYears;
	}


	public List<SelectOptionVO> getPartiesList() {
		return partiesList;
	}


	public void setPartiesList(List<SelectOptionVO> partiesList) {
		this.partiesList = partiesList;
	}


	public List<SelectOptionVO> getStatesList() {
		return statesList;
	}


	public void setStatesList(List<SelectOptionVO> statesList) {
		this.statesList = statesList;
	}


	public List<SelectOptionVO> getStatesListForLocalBodyElection() {
		return statesListForLocalBodyElection;
	}


	public void setStatesListForLocalBodyElection(
			List<SelectOptionVO> statesListForLocalBodyElection) {
		this.statesListForLocalBodyElection = statesListForLocalBodyElection;
	}


	public IStaticDataService getStaticDataService() {
		return staticDataService;
	}


	public void setStaticDataService(IStaticDataService staticDataService) {
		this.staticDataService = staticDataService;
	}
	

	public List<SelectOptionVO> getElectionYearList() {
		return electionYearList;
	}


	public void setElectionYearList(List<SelectOptionVO> electionYearList) {
		this.electionYearList = electionYearList;
	}
	
	public List<SelectOptionVO> getPartysList() {
		return partysList;
	}


	public void setPartysList(List<SelectOptionVO> partysList) {
		this.partysList = partysList;
	}


	public List<SelectOptionVO> getConstituencyList() {
		return constituencyList;
	}


	public void setConstituencyList(List<SelectOptionVO> constituencyList) {
		this.constituencyList = constituencyList;
	}


	public List<SelectOptionVO> getUserAccessConstituencyList() {
		return userAccessConstituencyList;
	}


	public void setUserAccessConstituencyList(
			List<SelectOptionVO> userAccessConstituencyList) {
		this.userAccessConstituencyList = userAccessConstituencyList;
	}

	public ICrossVotingEstimationService getCrossVotingEstimationService() {
		return crossVotingEstimationService;
	}

	public void setCrossVotingEstimationService(
			ICrossVotingEstimationService crossVotingEstimationService) {
		this.crossVotingEstimationService = crossVotingEstimationService;
	}

	public IVotersAnalysisService getVotersAnalysisService() {
		return votersAnalysisService;
	}

	public void setVotersAnalysisService(
			IVotersAnalysisService votersAnalysisService) {
		this.votersAnalysisService = votersAnalysisService;
	}

	public CadreManagementService getCadreManagementService() {
		return cadreManagementService;
	}

	public void setCadreManagementService(
			CadreManagementService cadreManagementService) {
		this.cadreManagementService = cadreManagementService;
	}

	public List<SelectOptionVO> getAssemblyConstis() {
		return assemblyConstis;
	}

	public void setAssemblyConstis(List<SelectOptionVO> assemblyConstis) {
		this.assemblyConstis = assemblyConstis;
	}

	public List<SelectOptionVO> getParlConstis() {
		return parlConstis;
	}

	public void setParlConstis(List<SelectOptionVO> parlConstis) {
		this.parlConstis = parlConstis;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	
	public List<SelectOptionVO> getpConstituencyList() {
		return pConstituencyList;
	}


	public void setpConstituencyList(List<SelectOptionVO> pConstituencyList) {
		this.pConstituencyList = pConstituencyList;
	}
	

	public List<SelectOptionVO> getParliaments() {
		return parliaments;
	}


	public void setParliaments(List<SelectOptionVO> parliaments) {
		this.parliaments = parliaments;
	}

	
	public List<SelectOptionVO> getaConstituencyList() {
		return aConstituencyList;
	}


	public void setaConstituencyList(List<SelectOptionVO> aConstituencyList) {
		this.aConstituencyList = aConstituencyList;
	}


	
	public IConstituencySearchService getConstituencySearchService() {
		return constituencySearchService;
	}


	public void setConstituencySearchService(
			IConstituencySearchService constituencySearchService) {
		this.constituencySearchService = constituencySearchService;
	}

	
	public List<SelectOptionVO> getDistrictStateList() {
		return districtStateList;
	}


	public void setDistrictStateList(List<SelectOptionVO> districtStateList) {
		this.districtStateList = districtStateList;
	}


	public Long getConstituencyId() {
		return constituencyId;
	}


	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}


	public Long getDistrictId() {
		return districtId;
	}


	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}


	public Long getStateId() {
		return stateId;
	}


	public void setStateId(Long stateId) {
		this.stateId = stateId;
	}


	public boolean isParty() {
		return party;
	}


	public void setParty(boolean party) {
		this.party = party;
	}


	public SelectOptionVO getBoothAnalysisData() {
		return boothAnalysisData;
	}


	public void setBoothAnalysisData(SelectOptionVO boothAnalysisData) {
		this.boothAnalysisData = boothAnalysisData;
	}

	
	public List<SelectOptionVO> getMandalList() {
		return mandalList;
	}


	public void setMandalList(List<SelectOptionVO> mandalList) {
		this.mandalList = mandalList;
	}


	public List<SelectOptionVO> getPanchayatsList() {
		return panchayatsList;
	}


	public void setPanchayatsList(List<SelectOptionVO> panchayatsList) {
		this.panchayatsList = panchayatsList;
	}


	public List<SelectOptionVO> getBoothsList() {
		return boothsList;
	}


	public void setBoothsList(List<SelectOptionVO> boothsList) {
		this.boothsList = boothsList;
	}

	
	public String getLoginUserProfilePic() {
		return loginUserProfilePic;
	}


	public void setLoginUserProfilePic(String loginUserProfilePic) {
		this.loginUserProfilePic = loginUserProfilePic;
	}

	
	public IAnanymousUserService getAnanymousUserService() {
		return ananymousUserService;
	}


	public void setAnanymousUserService(IAnanymousUserService ananymousUserService) {
		this.ananymousUserService = ananymousUserService;
	}
	

	public String getLoginUserName() {
		return loginUserName;
	}


	public void setLoginUserName(String loginUserName) {
		this.loginUserName = loginUserName;
	}

	public CrossVotingVO getCrossVotingVO() {
		return crossVotingVO;
	}


	public void setCrossVotingVO(CrossVotingVO crossVotingVO) {
		this.crossVotingVO = crossVotingVO;
	}

	public Long getCrossVotingYear() {
		return crossVotingYear;
	}


	public void setCrossVotingYear(Long crossVotingYear) {
		this.crossVotingYear = crossVotingYear;
	}


	public Long getCrossVotingPConsti() {
		return crossVotingPConsti;
	}


	public void setCrossVotingPConsti(Long crossVotingPConsti) {
		this.crossVotingPConsti = crossVotingPConsti;
	}


	public Long getCrossVotingAConsti() {
		return crossVotingAConsti;
	}


	public void setCrossVotingAConsti(Long crossVotingAConsti) {
		this.crossVotingAConsti = crossVotingAConsti;
	}


	public Long getCrossVotingParty() {
		return crossVotingParty;
	}


	public void setCrossVotingParty(Long crossVotingParty) {
		this.crossVotingParty = crossVotingParty;
	}

	public HttpSession getSession() {
		return session;
	}


	public void setSession(HttpSession session) {
		this.session = session;
	}

	public boolean isInfoManager() {
		return infoManager;
	}


	public void setInfoManager(boolean infoManager) {
		this.infoManager = infoManager;
	}


	public String execute()  
	{	
	  try{
		session = request.getSession();
		RegistrationVO user = (RegistrationVO)session.getAttribute(IConstants.USER);
		
		redirectUrl = userProfileService.getUserRedirectedUrl(user.getRegistrationID());
		
		if(redirectUrl != null && redirectUrl.trim().length() > 0)
			return "URL_REDIRECT";
		
		List<String> entitlements = null;
		if(user != null && user.getEntitlements() != null && user.getEntitlements().size()>0){
			entitlements = user.getEntitlements();
			
			if(entitlements.contains("CONSTITUENCY_PAGE_USER_ENTITLEMENT")){
				return "constituencyPageAction";
			}
			if(entitlements.contains("GOVT_DEPARTMENT_ADMIN_ENTITLEMENT_NEW")){
				return "alertManagementAction";
			}
			if(entitlements.contains("GOVT_DEPARTMENT_ENTITLEMENT_NEW")){
				return "alertUserManagementAction";
			}
			if(entitlements.contains("GOVT_DEPARTMENT_DISTRICT_OFFICER_ENTITLEMENT_NEW")){
				return "alertDistManagement";
			}
			if(entitlements.contains("GOVT_DEPARTMENT_DISTRICT_OFFICE_ENTITLEMENT_NEW")){
				return "alertDistOfficeManagement";
			}
			
			
			if(entitlements.contains("GOVT_DEPARTMENT_ADMIN_ENTITLEMENT")){
				return "cccDashboardAction";
			}
			
			if(entitlements.contains("GOVT_DEPARTMENT_ENTITLEMENT") || entitlements.contains("GOVT_DEPARTMENT_DISTRICT_OFFICER_ENTITLEMENT")
					 || entitlements.contains("GOVT_DEPARTMENT_DISTRICT_OFFICE_ENTITLEMENT")){
				return "departmentDetailsAction";
			}
			
			
			if(entitlements.contains("ALERT_CLARIFICATION_DASHBOARD_ENTITLEMENT"))    
			{
				return "alertClarificationDashboard";
			}
			if(entitlements.contains("ALERT_DASHBOARD_USER_ENTITLEMENT"))    
			{
				return "ALERTDASHBOARDUSERENTITLEMENT";
			}
			if(entitlements.contains("CADRE_TAB_LOCKING_USER_ENTITLEMENT"))
			{
				return "cadresurveyuserassign";
			}
			if(entitlements.contains("CORE_DASHBOARD_USER"))
			{
				return "coredashboarduser";
			}
			if(entitlements.contains("CADRE_WEB_REGISTRATION_2016"))
			{
				return "cadrewebregistration2016";
			}
			if(entitlements.contains("TOUR_USER_ENTITLEMENT")){
				return "tourUserEntitlement";        
			}
			if(entitlements.contains("CADRE_REGISTRATION_2016_DASHBOARD_REDIRECT")){
				return "cadre2016dashboard";
			}
			if(entitlements.contains("MEMBERSHIP_DRIVE_CONSTITUENCY_OVERVIEW_ENTITLEMENT".trim())){
				return "membershipDriveConstituencyOverviewEntitlement";
			}
			if(entitlements.contains("LEADER_OCCASIONS_ENTITLEMENT".trim())){
				
				birthDaysList = birthDayDetailsService.getLeaderOccasionDetails(null,null,null,null);
				if(birthDaysList != null && birthDaysList.size()>0){
					for (BirthDayDetailsVO vo : birthDaysList) {
						if(vo.getName() != null && vo.getName().trim().equalsIgnoreCase("Today"))
							session.setAttribute("birthDayCount",vo.getTotalCount());
					}
				}
				else
					session.setAttribute("birthDayCount", 0L);
			}
			if(entitlements.contains("ACCESS_USERS_CADRE_REGISTRATION_2016_DASHBOARD".trim())){
				return "accessUsersCadreDashboard";
			}
			if(entitlements.contains("CREATE_NOMINATED_POST_ENTITLEMENT".trim())){
				return "nominatedPostProfileAction";
			}
			else if(entitlements.contains("DEBATE_CREATE_ENTITLEMENT".trim())){
				return "debate";
			}
			else if(entitlements.contains("DEBATE_REPORT_ENTITLEMENT".trim())){
				return "debate";
			}
			else if(entitlements.contains("DEBATE_ENTITLEMENT".trim())){
				return "debate";
			}
			else if(entitlements.contains("BLOOD_BANK_REGISTRATION_ENTITLEMENT".trim())){
				return "bloodBankRegistration";
			}
			/*else if(entitlements.contains("BLOOD_BANK_REGISTRATION_ENTITLEMENT".trim())){
				return "bloodBankRegistration";
			}*/
			else if(entitlements.contains("BLOOD_BANK_DASHBOARD_ENTITLEMENT".trim())){
				return "bloodBankDashboard";
			}
			else if(entitlements.contains("BLOOD_BANK_BLEEDING_ENTITLEMENT".trim())){
				return "bloodBankBleeding";
			}
			else if(entitlements.contains("EVENTS_DASHBOARD_ENTITLEMENT".trim())){
				return "eventDashboard";
			}
			
			else if(entitlements.contains("CADRE_FAMILY_DETAILS_UPDATION".trim())){
				return "cadreFamilyDetailsUpdation";
			}		
			else if(entitlements.contains(IConstants.TDP_CADRE_SEARCH.trim())){
				return "tdpCadreSearch";
			}
			else if(entitlements.contains("MAHANADU".trim())){
				return "mahanadu";
			}
			else if(entitlements.contains("CADREDASHBOARD".trim())){
				return "cadreDashBoard";
			}
			else if(entitlements.contains(IConstants.CASTE_SURVEY_CALL_CENTER.trim())){
				return "webuser";
			}
			else if(entitlements.contains(IConstants.VIZAG_WM.trim())){
				return "webuser";
			}
			else if(entitlements.contains(IConstants.SURVEY_USER_CREATION.trim())){
				return "surveyUser";
			}
			else if(entitlements.contains(IConstants.PARTY_CADRE_SEARCH.trim())){
				return "partyCadre";
			}
			else if(entitlements.contains( IConstants.IVR_MOBILE_NUMBERS_RETRIVAL_REDIRECT.trim()))
			{
				return "mobileNumbersRetrivalPage";
			}
			else if(entitlements.contains("CADRE_REGISTRATION_2014".trim())){
				return "cadreRegistration";
			}
			else if(entitlements.contains(IConstants.CADRE_MEMBERSHIPCARD_DISPATCHER.trim())){
				return "cadreMemberShipCardDispatcher";
			}
			else if(entitlements.contains(IConstants.GHMC_CADRE_MEGA_DRIVE_USER.trim())){
				return "ghmcMegaDriveUser";
			}
			else if(entitlements.contains("CADRE_2014_CARD_SAMPLE".trim())){
				return "cadre2014CardSample";
			}
			else if(entitlements.contains("TABDEALLOCATIONALTER".trim())){
				return "tabAllocationDetailsAction";
			}
			else if(entitlements.contains("MISREPORT".trim())){
				return "misReportAction";
			}
			else if(entitlements.contains("PARTYCADREDASHBOARD".trim())){
				return "partyDashBoard";
			}
			else if(entitlements.contains("CADREIVRDASHBOARD".trim())){
				return "cadreIVrDashBoard";
			}
			else if(entitlements.contains("CADRE_COMMITTEE_MANAGEMENT".trim())){
				return "CADRE_COMMITTEE_MANAGEMENT";
			}
			else if(entitlements.contains("TDP_COMMITTEE_ADMIN".trim()) || 
						entitlements.contains("TDP_COMMITTEE_AREAWISE_ACCESS".trim())){
				return "tdpCommitteeAdmin";
			}
			else if(entitlements.contains("TIRUPATHI_BYEELECTION".trim())){
				return "tirupatiByeElection";
			}
			else if(entitlements.contains("TDP_COMMITTEE_AREAWISE_ACCESS".trim())){
				return "cadreCommitteeDashBoard";
			}
			else if(entitlements.contains("TDP_COMMITTEE_STATE_DISTRICT_ACCESS".trim())){
				return "tdpCommitteeStateDistrictAdmin";
			}
			else if(entitlements.contains("CADRE_REGISTRATIONFOR_OTHERSTATES".trim())){
				return "cadreRegistrationForOtherStates";
			}
			else if(entitlements.contains("MAHANADUTABALLOCATION".trim())){
				return "mahanaduTabUsersManageAction";
			}
			else if(entitlements.contains("OTHER_STATE_DELEGATE_REG".trim())){
				return "otherStateTempararyCardsPrinting";
			}
			else if(entitlements.contains("COMMITTEE_MGT".trim()) || entitlements.contains("COMMITTEE_MANAGEMENT_MULTIPLE_AREAS_ENTITLEMENT".trim())){
				return "committeemgt";
			}
			else if(entitlements.contains("TRAINING_CAMP_CALLER_ADMIN".trim())){
				return "callCenterTrainingAdmin";
			}	
			else if(entitlements.contains("TRAINING_CAMP_CALLER".trim())){
				return "callCenterTrainingAgent";
			}
			else if(entitlements.contains("TRAINING_CAMP_FEEDBACK_UPDATE_ENTITLEMENT".trim())){
				return "trainingCampFeedbackUpdate";
			}
			else if(entitlements.contains("TRAINING_CAMP_ADMIN".trim())){
				return "trainingCampAdminPage";
			}
			else if(entitlements.contains("ACTIVITY_ENTRY_ENTITLEMENT".trim())){
				return "activitiesUpdatedPage";
			}
			else if(entitlements.contains("POLLING_MANAGEMENT_ENTITLEMENT".trim())){
				return "pollingManagmentPage";
			}
			else if(entitlements.contains("APPOINTMENTS_MANAGE_ENTITLEMENT".trim())){
				return "appointmentsManagmentPage";
			}
			else if(entitlements.contains("AFFILIATED_UNION_REGISTRATION_REDIRECT_ENTITLEMENT"))
			{
				return "affiliatedUnionRegistrationPage";
			}
			
			if(entitlements.contains("CADRE_WEB_MONITORING_DASHBOARD_REDIRECTION"))
			{
				return "cadreWebMonitoringDashboard";
			}
			else if(entitlements.contains("CADRE_FIELD_MONITORING_DASHBOARD_REDIRECTION"))
			{
				return "cadreFiledMonitoringDashboard";
			}
			else if(entitlements.contains("CADRE_FIELD_MONITORING_REDIRECTION"))
			{
				return "cadreFiledMonitoring";
			}
			else if(entitlements.contains("CADRE_DATA_MONITORING_DASHBOARD_REDIRECTION"))
			{
				return "cadreDataMonitoringDashboard";
			}
			else if(entitlements.contains("CADRE_DATA_MONITORING_REDIRECTION"))
			{
				return "cadreDataMonitoring";
			}
			else if(entitlements.contains("TDP_CADRE_LOGIN_ENTITLEMENT"))
			{
				return "centralMembersAlertDashboard";
			}
		}
		
		/*
		
		if(entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)session.getAttribute(IConstants.USER),"BLOOD_BANK_REGISTRATION_ENTITLEMENT")){
			return "bloodBankRegistration";
		}
		if(entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)session.getAttribute(IConstants.USER),"BLOOD_BANK_DASHBOARD_ENTITLEMENT")){
			return "bloodBankDashboard";
		}
		if(entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)session.getAttribute(IConstants.USER),"BLOOD_BANK_BLEEDING_ENTITLEMENT")){
			return "bloodBankBleeding";
		}
		if(entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)session.getAttribute(IConstants.USER),"EVENTS_DASHBOARD_ENTITLEMENT")){
			return "eventDashboard";
		}
		
		if(entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)session.getAttribute(IConstants.USER),"CADRE_FAMILY_DETAILS_UPDATION")){
			return "cadreFamilyDetailsUpdation";
		}		
		if(entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)session.getAttribute(IConstants.USER),IConstants.TDP_CADRE_SEARCH)){
			return "tdpCadreSearch";
		}
		if(entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)session.getAttribute(IConstants.USER),"MAHANADU")){
			return "mahanadu";
		}
		if(entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)session.getAttribute(IConstants.USER),"CADREDASHBOARD")){
			return "cadreDashBoard";
		}
		if(entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)session.getAttribute(IConstants.USER),IConstants.CASTE_SURVEY_CALL_CENTER)){
			return "webuser";
		}
		if(entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)session.getAttribute(IConstants.USER),IConstants.VIZAG_WM)){
			return "webuser";
		}
		if(entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)session.getAttribute(IConstants.USER),IConstants.SURVEY_USER_CREATION)){
			return "surveyUser";
		}
		if(entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)session.getAttribute(IConstants.USER),IConstants.PARTY_CADRE_SEARCH)){
			return "partyCadre";
		}
		if(entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)session.getAttribute(IConstants.USER), IConstants.IVR_MOBILE_NUMBERS_RETRIVAL_REDIRECT))
		{
			return "mobileNumbersRetrivalPage";
		}
		if(entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)session.getAttribute(IConstants.USER),"CADRE_REGISTRATION_2014")){
			return "cadreRegistration";
		}
		if(entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)session.getAttribute(IConstants.USER),IConstants.CADRE_MEMBERSHIPCARD_DISPATCHER)){
			return "cadreMemberShipCardDispatcher";
		}
		if(entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)session.getAttribute(IConstants.USER),IConstants.GHMC_CADRE_MEGA_DRIVE_USER)){
			return "ghmcMegaDriveUser";
		}
		if(entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)session.getAttribute(IConstants.USER),"CADRE_2014_CARD_SAMPLE")){
			return "cadre2014CardSample";
		}
		if(entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)session.getAttribute(IConstants.USER),"TABDEALLOCATIONALTER")){
			return "tabAllocationDetailsAction";
		}
		if(entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)session.getAttribute(IConstants.USER),"MISREPORT")){
			return "misReportAction";
		}
		if(entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)session.getAttribute(IConstants.USER),"PARTYCADREDASHBOARD")){
			return "partyDashBoard";
		}
		if(entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)session.getAttribute(IConstants.USER),"CADREIVRDASHBOARD")){
			return "cadreIVrDashBoard";
		}
		if(entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)session.getAttribute(IConstants.USER),"CADRE_COMMITTEE_MANAGEMENT")){
			return "CADRE_COMMITTEE_MANAGEMENT";
		}
		if(entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)session.getAttribute(IConstants.USER),"TDP_COMMITTEE_ADMIN") || 
					entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)session.getAttribute(IConstants.USER),"TDP_COMMITTEE_AREAWISE_ACCESS")){
			return "tdpCommitteeAdmin";
		}
		if(entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)session.getAttribute(IConstants.USER),"TIRUPATHI_BYEELECTION")){
			return "tirupatiByeElection";
		}
		if(entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)session.getAttribute(IConstants.USER),"TDP_COMMITTEE_AREAWISE_ACCESS")){
			return "cadreCommitteeDashBoard";
		}
		if(entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)session.getAttribute(IConstants.USER),"TDP_COMMITTEE_STATE_DISTRICT_ACCESS")){
		return "tdpCommitteeStateDistrictAdmin";
		}
		if(entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)session.getAttribute(IConstants.USER),"CADRE_REGISTRATIONFOR_OTHERSTATES")){
			return "cadreRegistrationForOtherStates";
		}
		if(entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)session.getAttribute(IConstants.USER),"MAHANADUTABALLOCATION")){
			return "mahanaduTabUsersManageAction";
		}
		if(entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)session.getAttribute(IConstants.USER),"OTHER_STATE_DELEGATE_REG")){
			return "otherStateTempararyCardsPrinting";
		}
		if(entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)session.getAttribute(IConstants.USER),"COMMITTEE_MGT")){
			return "committeemgt";
		}
		if(entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)session.getAttribute(IConstants.USER),"TRAINING_CAMP_CALLER_ADMIN")){
			return "callCenterTrainingAdmin";
		}	
		if(entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)session.getAttribute(IConstants.USER),"TRAINING_CAMP_CALLER")){
			return "callCenterTrainingAgent";  
		}
		if(entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)session.getAttribute(IConstants.USER),"TRAINING_CAMP_FEEDBACK_UPDATE_ENTITLEMENT")){
			return "trainingCampFeedbackUpdate";
		}
		if(entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)session.getAttribute(IConstants.USER),"TRAINING_CAMP_ADMIN")){
			return "trainingCampAdminPage";
		}
		if(entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)session.getAttribute(IConstants.USER),"ACTIVITY_ENTRY_ENTITLEMENT")){
			return "activitiesUpdatedPage";
		}
		if(entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)session.getAttribute(IConstants.USER),"POLLING_MANAGEMENT_ENTITLEMENT")){
			return "pollingManagmentPage";
		}
		if(entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)session.getAttribute(IConstants.USER),"APPOINTMENTS_MANAGE_ENTITLEMENT")){
			return "appointmentsManagmentPage";
		}
		*/
		
		statesList = staticDataService.getParticipatedStatesForAnElectionType(Long.valueOf(2));
		
		//electionYearsList=staticDataService.getElectionYearsForBooths(1l,2l);
		
		statesListForLocalBodyElection = staticDataService.getParticipatedStatesForAnElectionType(Long.valueOf(5)); 
		
		if(statesListForLocalBodyElection == null || statesListForLocalBodyElection.size() == 0)
			statesListForLocalBodyElection.add(new SelectOptionVO(0L,"Select State"));
	//	RegistrationVO user=(RegistrationVO) session.getAttribute("USER");
		if(user == null)
		return INPUT;
		//infoManager = ananymousUserService.checkInfoManagerOrNot(user.getRegistrationID());
		if(!(Boolean)session.getAttribute(IWebConstants.PARTY_ANALYST_USER_ROLE))
		 return "userProfile";
		//districtsList = votersAnalysisService.getDistrictsList(1l);
		//publicationDatesList = votersAnalysisService.getPublicationList();
		//FOR CHECKING ENTITLEMENTS CONSISTS HOUSEHOLDS SURVEY ENTITLEMENT
		//List<String> entitlements=user.getEntitlements();
		hhEntitled = entitlements.contains("HOUSEHOLDS_SURVEY_ENTITLEMENT");
		
		
		constituencyId = user.getConstituencyId();
		loginUserName = user.getFirstName()+" "+user.getLastName();
		profileUserName= user.getUserName();
		loginUserProfilePic = ananymousUserService.getUserProfileImageByUserId(user.getRegistrationID());
		if(constituencyId != null){
			districtStateList = constituencySearchService.getDistrictAndStateId(constituencyId);
			SelectOptionVO selectOptionVO = new SelectOptionVO();
			selectOptionVO = districtStateList.get(0);
			districtId = selectOptionVO.getId();
			stateId = selectOptionVO.getOrderId();
		}
		if("Party".equalsIgnoreCase(user.getUserType())){
			party = true;
		}
		constituencyList = user.getUserAccessVoterConstituencies();
		assemblyConstis = (List<SelectOptionVO>)session.getAttribute("assemblyConstis");
		parlConstis = (List<SelectOptionVO>)session.getAttribute("parlConstis");
		SelectOptionVO initialVo = new SelectOptionVO(0L,"Select Constituency");
		Map<Long,List<SelectOptionVO>> assembliesForParl = (Map<Long,List<SelectOptionVO>>)session.getAttribute("assembliesForParl");
		parliaments = (List<SelectOptionVO>)session.getAttribute("parliaments");
		Long userID = user.getRegistrationID();
		Long electionYear = Long.valueOf(IConstants.PRESENT_ELECTION_YEAR);
		Long electionTypeId = Long.valueOf(IConstants.ASSEMBLY_ELECTION_TYPE_ID);
		
		if(constituencyList == null || constituencyList.isEmpty()){
			
			userAccessConstituencyList = crossVotingEstimationService.getConstituenciesForElectionYearAndTypeWithUserAccess(userID,electionYear,electionTypeId);
			assemblyConstis = userAccessConstituencyList ;
			constituencyList = votersAnalysisService.getConstituencyList(userAccessConstituencyList);
			constituencyList.add(0, initialVo);
			assemblyConstis.add(0, initialVo);
			user.setUserAccessVoterConstituencies(constituencyList);
		}
		if("Politician".equalsIgnoreCase(user.getUserType()) && assemblyConstis != null){
			mandalsList = votersAnalysisService.getMandalsInConstituencys(assemblyConstis);
			politician = true;
			}
		List<Long> constituencyIds = new ArrayList<Long>();
		if(constituencyList != null && constituencyList.size() > 0)
		{  
			for (SelectOptionVO constituency : constituencyList) {
				
				Long constituencyId = constituency.getId();
				constituencyIds.add(constituencyId);
			}
		}
		/*List<Long> mandalIds = new ArrayList<Long>();
		mandalList = crossVotingEstimationService.getTehsilsForConstituencies(constituencyIds);
		if(mandalList != null && mandalList.size() > 0)
		{
			for (SelectOptionVO mandal : mandalList) {
				Long mandalId = mandal.getId();
				mandalIds.add(mandalId);
			}
		}*/
		//boothsList = crossVotingEstimationService.getBoothsForConstituencyList(constituencyIds);
		//panchayatsList = crossVotingEstimationService.getPanchayatsForConstituencyList(mandalIds);
		/*if(assemblyConstis == null){
			assemblyConstis = crossVotingEstimationService.getConstituenciesForElectionYearAndTypeWithUserAccess(userID,electionYear,electionTypeId);
			if(assemblyConstis != null)
				assemblyConstis.add(0, initialVo);
		}
		
		if(parlConstis == null){
			parlConstis = crossVotingEstimationService.getConstituenciesForElectionYearAndTypeWithUserAccess(userID,electionYear,1l);
			if(parlConstis != null)
				parlConstis.add(0, initialVo);
		}
		if(assemblyConstis == null){
			assemblyConstis = new ArrayList<SelectOptionVO>();
			assemblyConstis.add(0, initialVo);
		}
		
		if(parlConstis == null){
			parlConstis = new ArrayList<SelectOptionVO>();
			parlConstis.add(0, initialVo);
		}
		*/
		/*if(assembliesForParl == null){
			List<Long> ids = new ArrayList<Long>();
			for(SelectOptionVO vo:assemblyConstis){
				ids.add(vo.getId());
			}
		  Object[] values = cadreManagementService.getConstituencies(ids);
		    session.setAttribute("assembliesForParl",values[1]);
		    assembliesForParl = (Map<Long,List<SelectOptionVO>>)values[1];
			session.setAttribute("parliaments",values[0]);
			parliaments = (List<SelectOptionVO>)values[0];
		}*/
		//session.setAttribute("assemblyConstis",assemblyConstis);
		//session.setAttribute("parlConstis",parlConstis);
		
		/*List<String> years = crossVotingEstimationService.getElectionYearsForBoothResult();
		electionYearList = new ArrayList<SelectOptionVO>();
		if(years != null && years.size() > 0)
		{  
			
			for(String year : years)
				electionYearList.add(new SelectOptionVO(Long.parseLong(year.trim()), year));
			
			
		}*/
		
		if(user.getAccessType() != null){
			if("MLA".equalsIgnoreCase(user.getAccessType())){
				List<Long> assIds = new ArrayList<Long>();
				for(SelectOptionVO vo:assemblyConstis){
					if(vo.getId().longValue() != 0l)
					   assIds.add(vo.getId());
				}
				if(assIds.size() > 0){
					boothAnalysisData = votersAnalysisService.getConstiInfo(assIds);
					if(boothAnalysisData != null){
						boothAnalysisData.setValue("AC");
					}
				}
				
				//if(assemblyConstis != null && assemblyConstis.size() >0)
				//constituencyId = assemblyConstis.get(1).getId();
				
			}else if("MP".equalsIgnoreCase(user.getAccessType())){
				List<Long> assIds = new ArrayList<Long>();
				for(SelectOptionVO vo:parlConstis){
					if(vo.getId().longValue() != 0l)
					   assIds.add(vo.getId());
					if(assIds.size() > 0){
						boothAnalysisData = votersAnalysisService.getConstiInfo(assIds);
						if(boothAnalysisData != null){
							boothAnalysisData.setValue("PC");
						}
					}
				}
			}
			
			
			//if(parlConstis != null && parlConstis.size() >1)
			//constituencyId = parlConstis.get(1).getId();
			if(assemblyConstis != null && assemblyConstis.size() >0){
				constituencyId = assemblyConstis.get(1).getId();
				districtId = staticDataService.getdistrictForAConstituency(constituencyId);
			}
		}
		
		/*List<Long> assemblyIds = new ArrayList<Long>();
		for(SelectOptionVO vo:assemblyConstis)
			assemblyIds.add(vo.getId());
		
		
		
		electionYearsList=staticDataService.getElectionYearsByConstituencyIds(assemblyIds);
		*/
		//assembliesForParl
		//parliaments
		/*crossVotingVO = new CrossVotingVO();
		try{
			List<Long> assIds = new ArrayList<Long>();
			for(SelectOptionVO vo:assemblyConstis){
				if(vo.getId().longValue() != 0l)
				   assIds.add(vo.getId());
			}
			crossVotingVO = crossVotingEstimationService.getElectionYearsForCrossVotingAnalysis(assIds);
			if(crossVotingVO.getYearsList().size() > 1)
			  crossVotingYear = crossVotingVO.getYearsList().get(1).getId();
			if(crossVotingVO.getParliamentLists().size() > 1)
			  crossVotingPConsti = crossVotingVO.getParliamentLists().get(1).getId();
			if(crossVotingVO.getAssemblyList().size() > 1)
			  crossVotingAConsti = crossVotingVO.getAssemblyList().get(1).getId();
			  crossVotingParty = 872l;

		}catch(Exception e){
			LOG.error("Exception is ",e);
		}*/
	  }catch(Exception e){
		  LOG.error("Exception rised in execute method ",e);
		  return "userProfile";
	  }
		return Action.SUCCESS;
	}
	
	public String getOptions(){
		try {
			jObj = new JSONObject(getTask());
		} catch (Exception e) {
			e.printStackTrace();
		}
		session = request.getSession();
		String module=jObj.getString("task");
		if(module.equalsIgnoreCase("forElectionYears")){
			elecYears=new ArrayList<SelectOptionVO>();
			Long etype=jObj.getLong("electionType");
			Long stateId=jObj.getLong("stateId");
			
			if(etype == 2L){
				
				List<Long> assemblyIds = new ArrayList<Long>();
				
			  assemblyConstis = (List<SelectOptionVO>)session.getAttribute("assemblyConstis");	
			  
			  for(SelectOptionVO vo:assemblyConstis)
				  assemblyIds.add(vo.getId());
			  
			  elecYears=staticDataService.getElectionYearsByConstituencyIds(assemblyIds);
			}
			else if(etype == 1L)
			{
				List<Long> parlimentIds = new ArrayList<Long>();
				
				parlConstis = (List<SelectOptionVO>)session.getAttribute("parlConstis");
				
				 for(SelectOptionVO vo:parlConstis)
					 parlimentIds.add(vo.getId());
				  
				  elecYears=staticDataService.getElectionYearsByConstituencyIds(parlimentIds);
			}

			
			return "years";
		}
		if(module.equalsIgnoreCase("forParty")){
			partiesList=new ArrayList<SelectOptionVO>();
			String elecYear=jObj.getString("electionYear");
			Long consId=jObj.getLong("constituencyId");
			partiesList=staticDataService.getPartiesForBooths(elecYear,consId);
			
			return "parties";
		}
		if(module.equalsIgnoreCase("forConstituencies")){
			List<SelectOptionVO> constituencyList1=new ArrayList<SelectOptionVO>();
			long stateId=jObj.getLong("stateId");
			long etype=jObj.getLong("electionType");
			long elecId=jObj.getLong("electionId");
			constituencyList1=crossVotingEstimationService.getAllOptions("constituencies",stateId,etype,elecId);
			
			List<Long> constlist=new ArrayList<Long>();
			
			
			if(etype==2){
				assemblyConstis = (List<SelectOptionVO>)session.getAttribute("assemblyConstis");
				for(SelectOptionVO list:assemblyConstis){constlist.add(list.getId());Collections.sort(constlist);}
			}
			if(etype==1){
				parlConstis = (List<SelectOptionVO>)session.getAttribute("parlConstis");
				for(SelectOptionVO list:parlConstis){constlist.add(list.getId());Collections.sort(constlist);}
			}
			
			constituencyList=new ArrayList<SelectOptionVO>(); 
			for (int i=0; i<constituencyList1.size(); i++) {
			    if (constlist.contains(constituencyList1.get(i).getId())) {
			        //LOG.info("Equals..: " + Alist.get(i));
			    	constituencyList.add(constituencyList1.get(i));
			    }
			}
			
			
			return "constituencies";
		}
		return Action.SUCCESS;
	}


	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
		
	}
   
	public String getAssemblyConstisForParl(){
		/*session = request.getSession();
		Map<Long,List<SelectOptionVO>> assembliesForParl = (Map<Long,List<SelectOptionVO>>)session.getAttribute("assembliesForParl");
		
		if(assembliesForParl != null){
			try{
				jObj = new JSONObject(getTask());	
				
			}catch (Exception e) {
				e.printStackTrace();
				LOG.error("Exception Occured in getRequestMessagesForUser() Method,Exception is- ",e);
			}
			assemblyConstis = assembliesForParl.get(jObj.getLong("constituencyId"));
		}
		if(assemblyConstis == null){
			assemblyConstis = new ArrayList<SelectOptionVO>();
			assemblyConstis.add(new SelectOptionVO(0l,"Select Location"));
		}
		return Action.SUCCESS;
		*/
		try
		{
			String param;
			param = getTask();
			jObj = new JSONObject(param);
			Long constituencyId  = jObj.getLong("constituencyId");
			Long year            = jObj.getLong("year");
			aConstituencyList = crossVotingEstimationService.getAssembliesForParliament(constituencyId,year);
		}catch(Exception e)
		{
			
		}
		return Action.SUCCESS;
	}
	public String ajaxHandler()
	{
		try
		{
			String param;
			param = getTask();
			jObj = new JSONObject(param);
			Long constituencyId  = jObj.getLong("constituencyId");
			Long year            = jObj.getLong("year");
			partysList = crossVotingEstimationService.getPartiesForConstituencyAndElectionYearForBoothData(constituencyId, String.valueOf(year));
		}catch(Exception e)
		{
			
		}
		return Action.SUCCESS;
	}
	
	public String getparlementByElection()
	{
		try
		{
			String param;
			param = getTask();
			jObj = new JSONObject(param);
			Long year  = jObj.getLong("electionYear");
			pConstituencyList = crossVotingEstimationService.getConstituenciesForElectionYearAndScopeForBoothData(String.valueOf(year), Long.valueOf(1));
		}catch(Exception e)
		{
			
		}
		return Action.SUCCESS;
	}
	
	public String getConstituenciesAndParties(){
		try
		{
			session = request.getSession();
			String param;
			param = getTask();
			jObj = new JSONObject(param);
			assemblyConstis = (List<SelectOptionVO>)session.getAttribute("assemblyConstis");;
			List<Long> assemblyIds = new ArrayList<Long>();
			for(SelectOptionVO vo:assemblyConstis){
				if(vo.getId().longValue() != 0l)
					assemblyIds.add(vo.getId());
			}
			if("getParlements".equalsIgnoreCase(jObj.getString("task"))){
				pConstituencyList = crossVotingEstimationService.getAllParliamentConstituenciesForCrossVoting(assemblyIds,jObj.getString("year"));
			}
			else if("getAssemblysForParliment".equalsIgnoreCase(jObj.getString("task"))){
				pConstituencyList = crossVotingEstimationService.getAllAssemblyConstituenciesForCrossVoting(assemblyIds,jObj.getLong("parliamentId"),jObj.getString("year"));
			}
			else if("getPariesForAssemply".equalsIgnoreCase(jObj.getString("task"))){
				//pConstituencyList = crossVotingEstimationService.getPartiesForConstituencyAndElectionYearForBoothData(jObj.getLong("assemblyId"),jObj.getString("year"));
				pConstituencyList = crossVotingEstimationService.getPartiesForAcAndPcElections(jObj.getLong("assemblyId"),jObj.getString("year"), jObj.getLong("parliamentId"));
			}			
			
		}catch(Exception e)
		{
			LOG.error("Exception rised in getConstituenciesAndParties", e);
		}
		
		return Action.SUCCESS;
	}
	
	public String getConstitunciesByElectionIdAndConstituenciyIds()
	{
		try
		{
				jObj = new JSONObject(getTask());
				
			     session = request.getSession();
			     
			     assemblyConstis = (List<SelectOptionVO>)session.getAttribute("assemblyConstis");
			     
			     List<Long> assemblyIds = new ArrayList<Long>();
			     
			     for(SelectOptionVO vo:assemblyConstis)
			    	 assemblyIds.add(vo.getId());
			    	 
			
			     constituencyList = staticDataService.getConstitunciesByElectionIdAndConstituenciyIds(assemblyIds,jObj.getLong("electionId"));
			     
			     
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}
	
	public String boothwiseResults()
	{
		try {
			session = request.getSession();
			RegistrationVO registrationVO = (RegistrationVO)session.getAttribute(IConstants.USER);
			
			if(registrationVO.getEntitlements() != null && registrationVO.getEntitlements().size()>0){
				List<String> entitlements = registrationVO.getEntitlements();
				if(entitlements.contains("MAHANADU")){
					return "mahanadu";
				}
				if(entitlements.contains(IConstants.CASTE_SURVEY_CALL_CENTER)){
					return "webuser";
				}
				if(entitlements.contains(IConstants.VIZAG_WM)){
					return "webuser";
				}
				if(entitlements.contains(IConstants.SURVEY_USER_CREATION)){
					return "surveyUser";
				}
				if(entitlements.contains(IConstants.PARTY_CADRE_SEARCH)){
					return "partyCadre";
				}
				if(entitlements.contains( IConstants.IVR_MOBILE_NUMBERS_RETRIVAL))
				{
					return "mobileNumbersRetrivalPage";
				}
			}
		 }catch(Exception e){
			  LOG.error("Exception rised in execute method ",e);
			  return "userProfile";
		  }
			return Action.SUCCESS;
		
		
	}
	
	public String newCadreSearch(){
		
		selectOptionVOList = staticDataService.getConstituencies(1l,1L);
		
		return Action.SUCCESS;
	}
}
