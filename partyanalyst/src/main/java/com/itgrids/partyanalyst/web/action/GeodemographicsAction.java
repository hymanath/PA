package com.itgrids.partyanalyst.web.action;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.core.api.service.IGeodemographicService;
import com.itgrids.partyanalyst.dto.CandidateDetailsForConstituencyTypesVO;
import com.itgrids.partyanalyst.dto.ConstituencyElectionResultsVO;
import com.itgrids.partyanalyst.dto.ConstituencyInfoVO;
import com.itgrids.partyanalyst.dto.ConstituencyVO;
import com.itgrids.partyanalyst.dto.DataTransferVO;
import com.itgrids.partyanalyst.dto.DelimitationConstituencyMandalResultVO;
import com.itgrids.partyanalyst.dto.ElectionBasicInfoVO;
import com.itgrids.partyanalyst.dto.ElectionTrendzReportVO;
import com.itgrids.partyanalyst.dto.NavigationVO;
import com.itgrids.partyanalyst.dto.ProblemBeanVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.helper.EntitlementsHelper;
import com.itgrids.partyanalyst.service.IAnanymousUserService;
import com.itgrids.partyanalyst.service.IConstituencyManagementService;
import com.itgrids.partyanalyst.service.IConstituencyPageService;
import com.itgrids.partyanalyst.service.IDelimitationConstituencyMandalService;
import com.itgrids.partyanalyst.service.IElectionTrendzService;
import com.itgrids.partyanalyst.service.ILocalBodyElectionService;
import com.itgrids.partyanalyst.service.IProblemManagementReportService;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.itgrids.partyanalyst.util.IWebConstants;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class GeodemographicsAction extends ActionSupport implements ServletRequestAware {

	private final static Logger LOG = Logger.getLogger(GeodemographicsAction.class);
	private HttpServletRequest request;
	private HttpSession session;
	private JSONObject jObj;
	private String task;
	private IGeodemographicService geodemographicService;
	private List<SelectOptionVO> result;
	private Boolean pollWidget;
	private String mapKey;
	private String mptcElectionType,zptcElectionType,muncipalityElectionType,corporationElectionType;
	private List<SelectOptionVO> electionTypes;
	private IStaticDataService staticDataService;
	private Long zptcElectionId; 
	private Long mptcElectionId;
	private Long mptcElectionTypeId,zptcElectionTypeId,muncipalityElectionTypeId,corporationElectionTypeId;
	private List<SelectOptionVO> zptcElectionYears;
	private List<SelectOptionVO> mptcElectionYears;  
	private IConstituencyPageService constituencyPageService;
	private ConstituencyInfoVO constituencyDetails;
	private Long constituencyId;   
	private EntitlementsHelper entitlementsHelper;
	private String constituencyName;
	private List<ConstituencyElectionResultsVO> constituencyElectionResultsVO;
	private DelimitationConstituencyMandalResultVO delimitationConstituencyMandalResultVO;	
	private ConstituencyVO constituencyVO;
	private CandidateDetailsForConstituencyTypesVO candidateDetailsForConstituency;
	private IProblemManagementReportService problemManagementReportService;
	private List<ProblemBeanVO> problemBean;
	private ElectionTrendzReportVO electionTrendzReportVO;
	private IElectionTrendzService electionTrendzService;
	private ElectionBasicInfoVO electionBasicInfoVO;
	private ILocalBodyElectionService localBodyElectionService;
	private List<SelectOptionVO> municipalElections;
	private List<SelectOptionVO> greaterElections;
	private List<SelectOptionVO> corporateElections;
	private List<SelectOptionVO> allLocalBodyIds,localBodyId;
	private ConstituencyVO greaterInfo;
	private NavigationVO navigationVO;
	private Long parliamentCinstiId;
	private IAnanymousUserService ananymousUserService;
	private DataTransferVO userDetails;
	private NavigationVO messageTypes;
	private IDelimitationConstituencyMandalService delimitationConstituencyMandalService;
	private Boolean isSubscribed;
	private IConstituencyManagementService constituencyManagementService;
	
	
	
	public IGeodemographicService getGeodemographicService() {
		return geodemographicService;
	}
	public void setGeodemographicService(
			IGeodemographicService geodemographicService) {
		this.geodemographicService = geodemographicService;
	}
	public Boolean getPollWidget() {
		return pollWidget;
	}
	public void setPollWidget(Boolean pollWidget) {
		this.pollWidget = pollWidget;
	}
	public String getMapKey() {
		return mapKey;
	}
	public void setMapKey(String mapKey) {
		this.mapKey = mapKey;
	}
	public String getMptcElectionType() {
		return mptcElectionType;
	}
	public void setMptcElectionType(String mptcElectionType) {
		this.mptcElectionType = mptcElectionType;
	}
	public String getZptcElectionType() {
		return zptcElectionType;
	}
	public void setZptcElectionType(String zptcElectionType) {
		this.zptcElectionType = zptcElectionType;
	}
	public String getMuncipalityElectionType() {
		return muncipalityElectionType;
	}
	public void setMuncipalityElectionType(String muncipalityElectionType) {
		this.muncipalityElectionType = muncipalityElectionType;
	}
	public String getCorporationElectionType() {
		return corporationElectionType;
	}
	public void setCorporationElectionType(String corporationElectionType) {
		this.corporationElectionType = corporationElectionType;
	}
	public List<SelectOptionVO> getElectionTypes() {
		return electionTypes;
	}
	public void setElectionTypes(List<SelectOptionVO> electionTypes) {
		this.electionTypes = electionTypes;
	}
	public IStaticDataService getStaticDataService() {
		return staticDataService;
	}
	public void setStaticDataService(IStaticDataService staticDataService) {
		this.staticDataService = staticDataService;
	}
	public Long getZptcElectionId() {
		return zptcElectionId;
	}
	public void setZptcElectionId(Long zptcElectionId) {
		this.zptcElectionId = zptcElectionId;
	}
	public Long getMptcElectionId() {
		return mptcElectionId;
	}
	public void setMptcElectionId(Long mptcElectionId) {
		this.mptcElectionId = mptcElectionId;
	}
	public Long getMptcElectionTypeId() {
		return mptcElectionTypeId;
	}
	public void setMptcElectionTypeId(Long mptcElectionTypeId) {
		this.mptcElectionTypeId = mptcElectionTypeId;
	}
	public Long getZptcElectionTypeId() {
		return zptcElectionTypeId;
	}
	public void setZptcElectionTypeId(Long zptcElectionTypeId) {
		this.zptcElectionTypeId = zptcElectionTypeId;
	}
	public Long getMuncipalityElectionTypeId() {
		return muncipalityElectionTypeId;
	}
	public void setMuncipalityElectionTypeId(Long muncipalityElectionTypeId) {
		this.muncipalityElectionTypeId = muncipalityElectionTypeId;
	}
	public Long getCorporationElectionTypeId() {
		return corporationElectionTypeId;
	}
	public void setCorporationElectionTypeId(Long corporationElectionTypeId) {
		this.corporationElectionTypeId = corporationElectionTypeId;
	}
	public List<SelectOptionVO> getZptcElectionYears() {
		return zptcElectionYears;
	}
	public void setZptcElectionYears(List<SelectOptionVO> zptcElectionYears) {
		this.zptcElectionYears = zptcElectionYears;
	}
	public List<SelectOptionVO> getMptcElectionYears() {
		return mptcElectionYears;
	}
	public void setMptcElectionYears(List<SelectOptionVO> mptcElectionYears) {
		this.mptcElectionYears = mptcElectionYears;
	}
	public IConstituencyPageService getConstituencyPageService() {
		return constituencyPageService;
	}
	public void setConstituencyPageService(
			IConstituencyPageService constituencyPageService) {
		this.constituencyPageService = constituencyPageService;
	}
	public ConstituencyInfoVO getConstituencyDetails() {
		return constituencyDetails;
	}
	public void setConstituencyDetails(ConstituencyInfoVO constituencyDetails) {
		this.constituencyDetails = constituencyDetails;
	}
	public Long getConstituencyId() {
		return constituencyId;
	}
	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}
	public EntitlementsHelper getEntitlementsHelper() {
		return entitlementsHelper;
	}
	public void setEntitlementsHelper(EntitlementsHelper entitlementsHelper) {
		this.entitlementsHelper = entitlementsHelper;
	}
	public String getConstituencyName() {
		return constituencyName;
	}
	public void setConstituencyName(String constituencyName) {
		this.constituencyName = constituencyName;
	}
	public List<ConstituencyElectionResultsVO> getConstituencyElectionResultsVO() {
		return constituencyElectionResultsVO;
	}
	public void setConstituencyElectionResultsVO(
			List<ConstituencyElectionResultsVO> constituencyElectionResultsVO) {
		this.constituencyElectionResultsVO = constituencyElectionResultsVO;
	}
	public DelimitationConstituencyMandalResultVO getDelimitationConstituencyMandalResultVO() {
		return delimitationConstituencyMandalResultVO;
	}
	public void setDelimitationConstituencyMandalResultVO(
			DelimitationConstituencyMandalResultVO delimitationConstituencyMandalResultVO) {
		this.delimitationConstituencyMandalResultVO = delimitationConstituencyMandalResultVO;
	}
	public ConstituencyVO getConstituencyVO() {
		return constituencyVO;
	}
	public void setConstituencyVO(ConstituencyVO constituencyVO) {
		this.constituencyVO = constituencyVO;
	}
	public CandidateDetailsForConstituencyTypesVO getCandidateDetailsForConstituency() {
		return candidateDetailsForConstituency;
	}
	public void setCandidateDetailsForConstituency(
			CandidateDetailsForConstituencyTypesVO candidateDetailsForConstituency) {
		this.candidateDetailsForConstituency = candidateDetailsForConstituency;
	}
	public IProblemManagementReportService getProblemManagementReportService() {
		return problemManagementReportService;
	}
	public void setProblemManagementReportService(
			IProblemManagementReportService problemManagementReportService) {
		this.problemManagementReportService = problemManagementReportService;
	}
	public List<ProblemBeanVO> getProblemBean() {
		return problemBean;
	}
	public void setProblemBean(List<ProblemBeanVO> problemBean) {
		this.problemBean = problemBean;
	}
	public ElectionTrendzReportVO getElectionTrendzReportVO() {
		return electionTrendzReportVO;
	}
	public void setElectionTrendzReportVO(
			ElectionTrendzReportVO electionTrendzReportVO) {
		this.electionTrendzReportVO = electionTrendzReportVO;
	}
	public IElectionTrendzService getElectionTrendzService() {
		return electionTrendzService;
	}
	public void setElectionTrendzService(
			IElectionTrendzService electionTrendzService) {
		this.electionTrendzService = electionTrendzService;
	}
	public ElectionBasicInfoVO getElectionBasicInfoVO() {
		return electionBasicInfoVO;
	}
	public void setElectionBasicInfoVO(ElectionBasicInfoVO electionBasicInfoVO) {
		this.electionBasicInfoVO = electionBasicInfoVO;
	}
	public ILocalBodyElectionService getLocalBodyElectionService() {
		return localBodyElectionService;
	}
	public void setLocalBodyElectionService(
			ILocalBodyElectionService localBodyElectionService) {
		this.localBodyElectionService = localBodyElectionService;
	}
	public List<SelectOptionVO> getMunicipalElections() {
		return municipalElections;
	}
	public void setMunicipalElections(List<SelectOptionVO> municipalElections) {
		this.municipalElections = municipalElections;
	}
	public List<SelectOptionVO> getGreaterElections() {
		return greaterElections;
	}
	public void setGreaterElections(List<SelectOptionVO> greaterElections) {
		this.greaterElections = greaterElections;
	}
	public List<SelectOptionVO> getCorporateElections() {
		return corporateElections;
	}
	public void setCorporateElections(List<SelectOptionVO> corporateElections) {
		this.corporateElections = corporateElections;
	}
	public List<SelectOptionVO> getAllLocalBodyIds() {
		return allLocalBodyIds;
	}
	public void setAllLocalBodyIds(List<SelectOptionVO> allLocalBodyIds) {
		this.allLocalBodyIds = allLocalBodyIds;
	}
	public List<SelectOptionVO> getLocalBodyId() {
		return localBodyId;
	}
	public void setLocalBodyId(List<SelectOptionVO> localBodyId) {
		this.localBodyId = localBodyId;
	}
	public ConstituencyVO getGreaterInfo() {
		return greaterInfo;
	}
	public void setGreaterInfo(ConstituencyVO greaterInfo) {
		this.greaterInfo = greaterInfo;
	}
	public NavigationVO getNavigationVO() {
		return navigationVO;
	}
	public void setNavigationVO(NavigationVO navigationVO) {
		this.navigationVO = navigationVO;
	}
	public Long getParliamentCinstiId() {
		return parliamentCinstiId;
	}
	public void setParliamentCinstiId(Long parliamentCinstiId) {
		this.parliamentCinstiId = parliamentCinstiId;
	}
	public IAnanymousUserService getAnanymousUserService() {
		return ananymousUserService;
	}
	public void setAnanymousUserService(IAnanymousUserService ananymousUserService) {
		this.ananymousUserService = ananymousUserService;
	}
	public DataTransferVO getUserDetails() {
		return userDetails;
	}
	public void setUserDetails(DataTransferVO userDetails) {
		this.userDetails = userDetails;
	}
	public NavigationVO getMessageTypes() {
		return messageTypes;
	}
	public void setMessageTypes(NavigationVO messageTypes) {
		this.messageTypes = messageTypes;
	}
	public IDelimitationConstituencyMandalService getDelimitationConstituencyMandalService() {
		return delimitationConstituencyMandalService;
	}
	public void setDelimitationConstituencyMandalService(
			IDelimitationConstituencyMandalService delimitationConstituencyMandalService) {
		this.delimitationConstituencyMandalService = delimitationConstituencyMandalService;
	}
	public Boolean getIsSubscribed() {
		return isSubscribed;
	}
	public void setIsSubscribed(Boolean isSubscribed) {
		this.isSubscribed = isSubscribed;
	}
	public IConstituencyManagementService getConstituencyManagementService() {
		return constituencyManagementService;
	}
	public void setConstituencyManagementService(
			IConstituencyManagementService constituencyManagementService) {
		this.constituencyManagementService = constituencyManagementService;
	}
	public List<SelectOptionVO> getResult() {
		return result;
	}
	public void setResult(List<SelectOptionVO> result) {
		this.result = result;
	}
	public JSONObject getjObj() {
		return jObj;
	}
	public void setjObj(JSONObject jObj) {
		this.jObj = jObj;
	}
	public String getTask() {
		return task;
	}
	public void setTask(String task) {
		this.task = task;
	}
	public HttpSession getSession() {
		return session;
	}
	public void setSession(HttpSession session) {
		this.session = session;
	}
	public HttpServletRequest getRequest() {
		return request;
	}
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	
	public String execute(){
		return Action.SUCCESS;
	}
	public String newConstituencyPage()
	{
		constituencyId = 232l;
		List<Long> electionScopeIds = new ArrayList<Long>();
		electionScopeIds.add(2l);
		String url = request.getRequestURL().toString();
		String substr = url.substring(7);
		String path = substr.substring(0, substr.indexOf('/')) ;
		String userStatusType = null;
		session = request.getSession();
		
		request.setAttribute("host", IConstants.DEPLOYED_HOST);
		if(IConstants.DEPLOYED_HOST.equalsIgnoreCase("tdpserver"))
			pollWidget = true;
		if(path.equalsIgnoreCase("partyanalyst.com"))
			mapKey = "https://maps.google.com/maps?file=api&amp;v=2&amp;key=ABQIAAAAmy8d-PXO6ktmh6sCNFXdwRScRx3TrvnxStTkM4udVhaLbRJhbBQtQ6p3f6vU6rRwFFw_2yEXM9Af3g&sensor=true";
		else 
			mapKey = "https://maps.google.com/maps?file=api&amp;v=2&amp;key=ABQIAAAAmy8d-PXO6ktmh6sCNFXdwRScRx3TrvnxStTkM4udVhaLbRJhbBQtQ6p3f6vU6rRwFFw_2yEXM9Af3g&sensor=true";
		
		if("tdpserver".equalsIgnoreCase(IConstants.DEPLOYED_HOST)){
			mapKey = "https://maps.google.com/maps?file=api&amp;v=2&amp;key=AIzaSyCSbtF4m2Eeh77OFbfIEJiN6hs-yPa7Qg4&sensor=true";
		}
		mptcElectionType = IConstants.MPTC_ELECTION_TYPE;
		zptcElectionType = IConstants.ZPTC_ELECTION_TYPE;
		muncipalityElectionType = IConstants.MUNCIPLE_ELECTION_TYPE;
		corporationElectionType = IConstants.CORPORATION_ELECTION_TYPE;
		electionTypes = staticDataService.getAllElectionTypes();
		for(SelectOptionVO eleTypes : electionTypes){
			if(eleTypes.getName().equalsIgnoreCase(mptcElectionType)){
				mptcElectionId = eleTypes.getId();
			}else if(eleTypes.getName().equalsIgnoreCase(zptcElectionType)){
				zptcElectionId = eleTypes.getId();
			}else if(eleTypes.getName().equalsIgnoreCase(muncipalityElectionType)){
				muncipalityElectionTypeId = eleTypes.getId();
			}else if(eleTypes.getName().equalsIgnoreCase(corporationElectionType)){
				corporationElectionTypeId = eleTypes.getId();
			}
		}	
		
		zptcElectionYears = staticDataService.getAllElectionYearsForATeshil(zptcElectionId);
		
		mptcElectionYears = staticDataService.getAllElectionYearsForATeshil(mptcElectionId);
		
		constituencyDetails = constituencyPageService.getConstituencyDetails(constituencyId);
		
		HttpSession session = request.getSession();
		RegistrationVO regVO = session.getAttribute(IConstants.USER) != null?(RegistrationVO)session.getAttribute(IConstants.USER):null;
		
		//Entilements Check
		checkForRegionAndReportLevelEntitlements(regVO);
		
		constituencyName = constituencyDetails.getConstituencyName();
		
		constituencyElectionResultsVO = constituencyPageService.getConstituencyElectionResults(constituencyId,electionScopeIds); //for building graph use this.
		
		DelimitationConstituencyMandalResultVO delimitationConstituencyMandalResultVO = delimitationConstituencyMandalService.getMandalsForDelConstituency(constituencyId);
		
		Throwable ex = delimitationConstituencyMandalResultVO.getExceptionEncountered();
		if(ex!=null){
			LOG.error("exception raised while retrieving mandal details ", ex);
		}
		
		LOG.info("delimitationConstituencyMandalResultVO.getMandals().size()::::"+delimitationConstituencyMandalResultVO.getPresentMandals().size());
		LOG.info("delimitationConstituencyMandalResultVO..getConstituencyType()::::"+delimitationConstituencyMandalResultVO.getConstituencyType());
		setDelimitationConstituencyMandalResultVO(delimitationConstituencyMandalResultVO);
		constituencyVO = constituencyPageService.getVotersInfoInMandalsForConstituency(constituencyId,true);
		
		candidateDetailsForConstituency = constituencyPageService.getCandidateAndPartyInfoForConstituency(constituencyId);
		
		List<Long> listOfConstituencies = new ArrayList<Long>();
		listOfConstituencies.add(constituencyId);
		problemBean = problemManagementReportService.getAllProblemsForGivenLocation(listOfConstituencies,IConstants.CONSTITUENCY_LEVEL).getApprovalProblems();
		
	
		LOG.info("electionTrendzReportVO ============ "+electionTrendzReportVO);
		
		if(problemBean != null)
			LOG.info("problemBean === "+problemBean.size());
		
		electionBasicInfoVO = electionTrendzService.getBasicElectionInfoFromConstituencyId(constituencyId);
		
           if(electionBasicInfoVO.getElectionId() != null){
			
			LOG.info("Inside trendz service call ....");
			try {
				electionTrendzReportVO = electionTrendzService.getVotingTrendzForAConstituency(electionBasicInfoVO.getElectionId(),electionBasicInfoVO.
						getElectionTypeId(),electionBasicInfoVO.getElectionYear(),constituencyId,IConstants.MALETRENDZ,IConstants.FEMALETRENDZ);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			/*if(electionTrendzReportVO != null)
			getMapsForVotingTrendz(electionTrendzReportVO);*/
			electionTrendzReportVO.setPrevElectionYearsInfo(electionTrendzService.getPreviousElectionsInfoForAConstituency(electionBasicInfoVO.
					getElectionYear(), constituencyId));
           }
   			List<Long> constituencyIds = new ArrayList<Long>();
   			
   			if(constituencyDetails.getConstituencyType().equalsIgnoreCase(IConstants.ASSEMBLY_ELECTION_TYPE)){
   			constituencyIds.add(constituencyId);
   		}else{
   			ConstituencyInfoVO  constituencyInfoVO = staticDataService.getLatestAssemblyConstituenciesForParliament(constituencyId);
   			if(constituencyInfoVO.getAssembyConstituencies() != null && constituencyInfoVO.getAssembyConstituencies().size() >0){
   				for(SelectOptionVO option:constituencyInfoVO.getAssembyConstituencies())
   					constituencyIds.add(option.getId());
   			}
   		}
		if(constituencyIds.size() >0){
   		    try {
				municipalElections = localBodyElectionService.getLocalBodyElectionsList(IConstants.MUNCIPLE_ELECTION_TYPE, 1l,constituencyIds);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
   		    try {
				corporateElections = localBodyElectionService.getLocalBodyElectionsList(IConstants.CORPORATION_ELECTION_TYPE, 1l,constituencyIds);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
   		    try {
				greaterElections = localBodyElectionService.getLocalBodyElectionsList(IConstants.GREATER_ELECTION_TYPE, 1l,constituencyIds);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		SelectOptionVO defaultData = new SelectOptionVO(0l, "");
		if(municipalElections == null || municipalElections.size() == 0){
			municipalElections = new ArrayList<SelectOptionVO>();
			municipalElections.add(defaultData);
		}
		if(corporateElections == null || corporateElections.size() == 0){
			corporateElections = new ArrayList<SelectOptionVO>();
			corporateElections.add(defaultData);
		}
		if(greaterElections == null || greaterElections.size() == 0){
			greaterElections = new ArrayList<SelectOptionVO>();
			greaterElections.add(defaultData);
		}
   		allLocalBodyIds = localBodyElectionService.getLatestGHMCElectionIdAndLatestElectionYear(IConstants.GREATER_ELECTION_TYPE).getMessageTypes();
   		localBodyId = localBodyElectionService.getLocalBodyElectionIdsForAConstituency(constituencyId,IConstants.GREATER_ELECTION_TYPE).getMessageTypes();
   		if(localBodyId!=null && localBodyId.size()!=0){
   			greaterInfo = localBodyElectionService.findConstituencywiseGreaterElectionResults(allLocalBodyIds.get(0).getId(),constituencyId,0l,0l);
   			if(greaterInfo.getListOfParties()==null){
   				greaterInfo.setListOfParties(getSelectOptionData());
   			}
   			if(greaterInfo.getListOfWards()==null){
   				greaterInfo.setListOfWards(getSelectOptionData());
   			}
   		}else{  
   			greaterInfo = new ConstituencyVO();
   			greaterInfo.setListOfParties(getSelectOptionData());
   			greaterInfo.setListOfWards(getSelectOptionData());
   		}
   		
   		navigationVO = staticDataService.findHirarchiForNavigation(constituencyId, IConstants.CONSTITUENCY_LEVEL);
   		if(navigationVO.getPcsInfo()!= null && navigationVO.getPcsInfo().size() > 0)
   		   parliamentCinstiId = navigationVO.getPcsInfo().get(0).getId();
   		Long startIndex = 0L;
   		String nameString = "";
   		RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
   		listOfConstituencies = ananymousUserService.getParliamentConstituencies(listOfConstituencies,IConstants.CONSTITUENCY);
   		Long connectedUsers = ananymousUserService.getAllUsersCountInSelectedLocationsInFilterView(0L, listOfConstituencies, IConstants.CONSTITUENCY_LEVEL, IConstants.ALL, "", null);
   		
   		if(!("tdpserver".equalsIgnoreCase(IConstants.DEPLOYED_HOST))){
   	if(user==null){
   			userDetails = ananymousUserService.getAllRegisteredAnonymousUserBasedOnLocation(listOfConstituencies,IConstants.CONSTITUENCY_LEVEL,connectedUsers,0l,IConstants.ALL,startIndex,nameString);	
   		}else{
   			userDetails = ananymousUserService.getAllRegisteredAnonymousUserBasedOnLocation(listOfConstituencies,IConstants.CONSTITUENCY_LEVEL,connectedUsers,user.getRegistrationID(),IConstants.ALL,startIndex,nameString);
   		}
   		}
   		//	Free User
   		if(user !=null)
   		{   if(userDetails!=null)
   		    {
   		
   			 Long userId=user.getRegistrationID();
   			 isSubscribed=constituencyManagementService.getIsSubscribed(userId,constituencyId);
	   		if(session.getAttribute(IWebConstants.FREE_USER_ROLE) !=null && session.getAttribute(IWebConstants.FREE_USER_ROLE).equals(true))
	   		{
				userDetails.setLoginStatus("true");
				userDetails.setUserId(user.getRegistrationID());
				userDetails.setConstituencyId(user.getConstituencyId());
			}else if(session.getAttribute(IWebConstants.PARTY_ANALYST_USER_ROLE) !=null && session.getAttribute(IWebConstants.PARTY_ANALYST_USER_ROLE).equals(true)){
				userDetails.setLoginStatus("true");
				userDetails.setUserId(user.getRegistrationID());
				userDetails.setConstituencyId(user.getConstituencyId());
			}else{
				userDetails.setLoginStatus("false");
			}
   		    }
   		}
   	//For Both roles
   		if(userDetails != null)
   		if(user !=null)
   		{
   		 if(session.getAttribute(IWebConstants.PARTY_ANALYST_USER_ROLE) != null && (Boolean)session.getAttribute(IWebConstants.PARTY_ANALYST_USER_ROLE))
		userStatusType = IConstants.PARTY_ANALYST_USER;
   	 userDetails.setUserStatusType(userStatusType);
	 if(session.getAttribute(IWebConstants.FREE_USER_ROLE) != null && (Boolean)session.getAttribute(IWebConstants.FREE_USER_ROLE))
		userStatusType = IConstants.FREE_USER;
	 userDetails.setUserStatusType(userStatusType);
	 if(session.getAttribute(IWebConstants.FREE_USER_ROLE) != null && session.getAttribute(IWebConstants.PARTY_ANALYST_USER_ROLE) != null && (Boolean)session.getAttribute(IWebConstants.FREE_USER_ROLE) && (Boolean)session.getAttribute(IWebConstants.PARTY_ANALYST_USER_ROLE))
		userStatusType = IConstants.BOTH;
	 userDetails.setUserStatusType(userStatusType);
   		}
      else{
        userStatusType = IConstants.NOT_LOGGED_IN;
        userDetails.setUserStatusType(userStatusType);
		}
   		
	 messageTypes = ananymousUserService.getAllMessageTypes();
		
	
				
   		if(constituencyElectionResultsVO != null || constituencyDetails != null){
			return Action.SUCCESS;
		}
		else
			return Action.ERROR;   	
	}
	public String getDistrictsDetails(){
			try{
				jObj = new JSONObject(getTask());
		
				result = geodemographicService.getDistricts(Long.valueOf(jObj.getString("locationId")));
				result.add(0,new SelectOptionVO(0l,"Select District"));
		}catch(Exception e){
			LOG.error("Exception Occured in getDistrictsDetails() in GeodemographicsAction ",e);
		}
		return SUCCESS;
	}
	public String getConstituenciesForADistrict()
	{
		try {
			jObj = new JSONObject(getTask());
			result = geodemographicService.getConstituenciesByDistrictID(Long.valueOf(jObj.getString("districtId")));
			 
			if(result == null || result.size() == 0)
				result.add(0, new SelectOptionVO(0L,"Select Constituency"));
			
			if(result != null && result.size() > 1)
				result.add(0, new SelectOptionVO(0L,"Select Constituency"));
		} catch (ParseException e) {
			LOG.error("Exception Occured in getConstituenciesForADistrict() in GeodemographicsAction ",e);
		}
		return Action.SUCCESS;
	}
	public String getMandalsForAConstituency(){
		try {
			jObj = new JSONObject(getTask());
			Long constituencyId = jObj.getLong("constituencyId");
			result = geodemographicService.getMandalsByConstituencyIDFromBooth(constituencyId);
		} catch (ParseException e) {
			LOG.error("Exception Occured in getMandalsForAConstituency() in GeodemographicsAction ",e);
		}
		return Action.SUCCESS;
	}
	public String getVillagesForMandalId()
	{
		try
		{
		jObj = new JSONObject(getTask());
		Long mandalId = jObj.getLong("mandalId");
		result = geodemographicService.getVillagesForMandalId(mandalId);
		}catch(Exception e)
		{
			LOG.error("Exception Occured in getVillagesForMandalId() in GeodemographicsAction ",e);
		}
	 return Action.SUCCESS;
	}
	  public String getAllBoothsForPanchayat(){
		  try{
			  jObj=new JSONObject(getTask());
			  Long panchayatId = jObj.getLong("panchayatId");
			  result = geodemographicService.getBoothsList(panchayatId);
			  
		  }catch(Exception e){
			  LOG.error("Entered into getAllBoothsForPanchayat method in GeodemographicsAction....");
		  }
		  return Action.SUCCESS;
	  }
		private void checkForRegionAndReportLevelEntitlements(RegistrationVO regVO){
			
			if(!entitlementsHelper.checkForRegionToViewReport(regVO, IConstants.CONSTITUENCY_LEVEL, constituencyId)){
				constituencyDetails.setHasAnalize(false);
				constituencyDetails.setViewCompletePage(false);
				constituencyDetails.setVotingTrendz(false);
				constituencyDetails.setAnalyzeComments(false);
				constituencyDetails.setPostComments(false);
				return;
			}
			RegistrationVO user=(RegistrationVO) session.getAttribute("USER");
			List<String> entitlements = null;
			if(user.getEntitlements() != null && user.getEntitlements().size()>0){
				entitlements = user.getEntitlements();
				if(user == null && !(entitlements.contains(IConstants.CONSTITUENCY_ANALYSIS) || entitlements.contains(IConstants.CONSTITUENCY_ANALYSIS))){
					constituencyDetails.setHasAnalize(false);
				}
				if(user == null && (entitlements.contains( IConstants.MANDAL_VOTING_TRENDZ) || entitlements.contains( IConstants.MANDAL_VOTING_TRENDZ)))
					constituencyDetails.setViewCompletePage(true);
				if(user == null && (entitlements.contains(IConstants.VOTING_TRENDZ) || entitlements.contains( IConstants.VOTING_TRENDZ)))
					constituencyDetails.setVotingTrendz(true);
				if(user == null && (entitlements.contains( IConstants.COMMETNS_ANALYZE) || entitlements.contains(IConstants.COMMETNS_ANALYZE)))
					constituencyDetails.setAnalyzeComments(true);
				if(user == null && (entitlements.contains(  IConstants.COMMENTS_POST) || entitlements.contains( IConstants.COMMENTS_POST)))
					constituencyDetails.setPostComments(true);
				
			/*if(((regVO != null && !entitlementsHelper.checkForEntitlementToViewReport(regVO, IConstants.CONSTITUENCY_ANALYSIS)) ||
				(regVO == null && !entitlementsHelper.checkForEntitlementToViewReport(null,  IConstants.CONSTITUENCY_ANALYSIS))))
				constituencyDetails.setHasAnalize(false);
			
			if((regVO != null && entitlementsHelper.checkForEntitlementToViewReport(regVO, IConstants.MANDAL_VOTING_TRENDZ)) ||
					(regVO == null && entitlementsHelper.checkForEntitlementToViewReport(null,  IConstants.MANDAL_VOTING_TRENDZ)))
					constituencyDetails.setViewCompletePage(true);
			
			if((regVO != null && entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)regVO, IConstants.VOTING_TRENDZ)) ||
					(regVO == null && entitlementsHelper.checkForEntitlementToViewReport(null,  IConstants.VOTING_TRENDZ)))
					constituencyDetails.setVotingTrendz(true);
			
			if((regVO != null && entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)regVO, IConstants.COMMETNS_ANALYZE)) ||
					(regVO == null && entitlementsHelper.checkForEntitlementToViewReport(null,  IConstants.COMMETNS_ANALYZE)))
					constituencyDetails.setAnalyzeComments(true);
			
			if((regVO != null && entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)regVO, IConstants.COMMENTS_POST)) ||
					(regVO == null && entitlementsHelper.checkForEntitlementToViewReport(null,  IConstants.COMMENTS_POST)))
					constituencyDetails.setPostComments(true);*/
		}
	}
	public List<SelectOptionVO> getSelectOptionData(){
		List<SelectOptionVO> list = new ArrayList<SelectOptionVO>();
		SelectOptionVO result = new SelectOptionVO();
		result.setId(0l);
		result.setName("");
		list.add(result);
		return list;
	}
}
