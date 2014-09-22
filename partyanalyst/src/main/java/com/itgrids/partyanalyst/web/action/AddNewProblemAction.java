package com.itgrids.partyanalyst.web.action;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.ServletContextAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.ConstituencyInfoVO;
import com.itgrids.partyanalyst.dto.ProblemBeanVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.service.ICallTrackingService;
import com.itgrids.partyanalyst.service.IProblemManagementService;
import com.itgrids.partyanalyst.service.IRegionServiceData;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.itgrids.partyanalyst.service.impl.CadreManagementService;
import com.itgrids.partyanalyst.util.IWebConstants;
import com.itgrids.partyanalyst.utils.IConstants;
import com.itgrids.partyanalyst.utils.ISessionConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class AddNewProblemAction extends ActionSupport implements ServletRequestAware,ServletContextAware,Preparable,ModelDriven<ProblemBeanVO>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(AddNewProblemAction.class);
	private HttpServletRequest request;
	private HttpSession session;
	private String task ;
	private JSONObject jObj;
	private List<SelectOptionVO> stateList;
	private List<SelectOptionVO> districtList;
	private List<SelectOptionVO> constituencyList;
	private List<SelectOptionVO> pConstituencyList;
	private List<SelectOptionVO> mandalList;
	private List<SelectOptionVO> hamletsOrWards;
	private List<SelectOptionVO> wardsOrHamletsList;
	private List<SelectOptionVO> hamletList;
	private List<SelectOptionVO> boothList;
	private List<SelectOptionVO> parliamentConstituencyList;
	private List<SelectOptionVO> problemTypes;
	private IRegionServiceData regionServiceDataImp;
	private IStaticDataService staticDataService;
	private IProblemManagementService problemManagementService;
	private CadreManagementService cadreManagementService;
	private List<SelectOptionVO> problemSourcesList;
	private ProblemBeanVO problemBeanVO ;
	private ICallTrackingService callTrackingService;
	
	private String requestSrc;
	private Long stateId = 0l;
	private Long districtId = 0l;
	private Long localElectionBodyId = 0l;
	private Boolean isParliament = false;
	private List<SelectOptionVO> problemScopes;
	private Long problemType;
	private Long  problemLocation;
	private Long scope;
	private Long pConstituencyId;
	private RegistrationVO user = null;
	private String windowTask = null;
	private Long constituencyId;
	transient private ServletContext context;
	private ProblemBeanVO problemBeanFromDB;

	public List<SelectOptionVO> getBoothList() {
		return boothList;
	}

	public void setBoothList(final List<SelectOptionVO> boothList) {
		this.boothList = boothList;
	}

	public List<SelectOptionVO> getHamletsOrWards() {
		return hamletsOrWards;
	}

	public void setHamletsOrWards(final List<SelectOptionVO> hamletsOrWards) {
		this.hamletsOrWards = hamletsOrWards;
	}

	public ProblemBeanVO getProblemBeanFromDB() {
		return problemBeanFromDB;
	}

	public void setProblemBeanFromDB(final ProblemBeanVO problemBeanFromDB) {
		this.problemBeanFromDB = problemBeanFromDB;
	}
	
	public void setServletContext(final ServletContext context) {
		this.context = context;
		
	}
	
	public ServletContext getContext() {
		return context;
	}

	public String getWindowTask() {
		return windowTask;
	}

	public void setWindowTask(final String windowTask) {
		this.windowTask = windowTask;
	}

	public void setUser(final RegistrationVO user) {
		this.user = user;
	}

	public RegistrationVO getUser() {
		return user;
	}

	public void setProblemBeanVO(final ProblemBeanVO problemBeanVO) {
		this.problemBeanVO = problemBeanVO;
	}

	public ProblemBeanVO getProblemBeanVO() {
		return problemBeanVO;
	}

	public void setServletRequest(final HttpServletRequest request) {
		this.request = request;		
	}	
	
	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(final HttpServletRequest request) {
		this.request = request;
	}

	public String getTask() {
		return task;
	}

	public void setTask(final String task) {
		this.task = task;
	}

	public JSONObject getJObj() {
		return jObj;
	}

	public void setJObj(final JSONObject obj) {
		jObj = obj;
	}

	public List<SelectOptionVO> getStateList() {
		return stateList;
	}

	public void setStateList(final List<SelectOptionVO> stateList) {
		this.stateList = stateList;
	}

	public List<SelectOptionVO> getDistrictList() {
		return districtList;
	}

	public void setDistrictList(final List<SelectOptionVO> districtList) {
		this.districtList = districtList;
	}

	public List<SelectOptionVO> getConstituencyList() {
		return constituencyList;
	}

	public void setConstituencyList(final List<SelectOptionVO> constituencyList) {
		this.constituencyList = constituencyList;
	}
	
	public List<SelectOptionVO> getMandalList() {
		return mandalList;
	}

	public void setMandalList(final List<SelectOptionVO> mandalList) {
		this.mandalList = mandalList;
	}
	
	public List<SelectOptionVO> getWardsOrHamletsList() {
		return wardsOrHamletsList;
	}

	public void setWardsOrHamletsList(final List<SelectOptionVO> wardsOrHamletsList) {
		this.wardsOrHamletsList = wardsOrHamletsList;
	}

	public List<SelectOptionVO> getHamletList() {
		return hamletList;
	}

	public void setHamletList(final List<SelectOptionVO> hamletList) {
		this.hamletList = hamletList;
	}
	
	public IRegionServiceData getRegionServiceDataImp() {
		return regionServiceDataImp;
	}

	public void setRegionServiceDataImp(final IRegionServiceData regionServiceDataImp) {
		this.regionServiceDataImp = regionServiceDataImp;
	}

	public void setParliamentConstituencyList(final 
			List<SelectOptionVO> parliamentConstituencyList) {
		this.parliamentConstituencyList = parliamentConstituencyList;
	}

	public List<SelectOptionVO> getParliamentConstituencyList() {
		return parliamentConstituencyList;
	}
	
	public HttpSession getSession() {
		return session;
	}

	public void setSession(final HttpSession session) {
		this.session = session;
	}	

	public String getRequestSrc() {
		return requestSrc;
	}

	public void setRequestSrc(final String requestSrc) {
		this.requestSrc = requestSrc;
	}

	public Long getStateId() {
		return stateId;
	}

	public void setStateId(final Long stateId) {
		this.stateId = stateId;
	}

	public Long getDistrictId() {
		return districtId;
	}

	public void setDistrictId(final Long districtId) {
		this.districtId = districtId;
	}

	public Long getConstituencyId() {
		return constituencyId;
	}

	public void setConstituencyId(final Long constituencyId) {
		this.constituencyId = constituencyId;
	}

	public Long getLocalElectionBodyId() {
		return localElectionBodyId;
	}

	public void setLocalElectionBodyId(final Long localElectionBodyId) {
		this.localElectionBodyId = localElectionBodyId;
	}

	public Boolean getIsParliament() {
		return isParliament;
	}

	public void setIsParliament(final Boolean isParliament) {
		this.isParliament = isParliament;
	}

	public Long getDefaultState() {
		return this.stateId;
	}
	
	public Long getDefaultDistrict() {
		return this.districtId;
	}	

	public Long getDefaultConstituency() {
		return this.constituencyId;
	}	

	public Long getDefaultLocalElectionBody() {
		return this.localElectionBodyId;
	}	

	public IStaticDataService getStaticDataService() {
		return staticDataService;
	}

	public void setStaticDataService(final IStaticDataService staticDataService) {
		this.staticDataService = staticDataService;
	}
	
	public IProblemManagementService getProblemManagementService() {
		return problemManagementService;
	}

	public void setProblemManagementService(final 
			IProblemManagementService problemManagementService) {
		this.problemManagementService = problemManagementService;
	}
	
	public Long  getDefaultScope()
	{
		return scope;
	}	

	public List<SelectOptionVO> getProblemScopes() {
		return problemScopes;
	}
	
	public void setProblemType(final Long problemType) {
		this.problemType = problemType;
	}

	public Long getProblemType() {
		return problemType;
	}

	public void setProblemScopes(final List<SelectOptionVO> problemScopes) {
		this.problemScopes = problemScopes;
	}

	public Long getProblemLocation() {
		return problemLocation;
	}

	public void setProblemLocation(final Long problemLocation) {
		this.problemLocation = problemLocation;
	}	
	
	public List<SelectOptionVO> getPConstituencyList() {
		return pConstituencyList;
	}

	public void setPConstituencyList(final List<SelectOptionVO> constituencyList) {
		pConstituencyList = constituencyList;
	}	

	public CadreManagementService getCadreManagementService() {
		return cadreManagementService;
	}

	public void setCadreManagementService(final 
			CadreManagementService cadreManagementService) {
		this.cadreManagementService = cadreManagementService;
	}	

	public Long getScope() {
		return scope;
	}

	public void setScope(final Long scope) {
		this.scope = scope;
	}
	

	public Long getPConstituencyId() {
		return pConstituencyId;
	}

	public void setPConstituencyId(final Long constituencyId) {
		pConstituencyId = constituencyId;
	}
	
	public Long getDefaultPConstituency()
	{
		return pConstituencyId; 
	}
	
	public List<SelectOptionVO> getProblemSourcesList() {
		return problemSourcesList;
	}

	public void setProblemSourcesList(final List<SelectOptionVO> problemSourcesList) {
		this.problemSourcesList = problemSourcesList;
	}
	
     public ICallTrackingService getCallTrackingService() {
		return callTrackingService;
	}

	public void setCallTrackingService(final ICallTrackingService callTrackingService) {
		this.callTrackingService = callTrackingService;
	}
	
	public void setProblemTypes(final List<SelectOptionVO> problemTypes) {
		this.problemTypes = problemTypes;
	}

	public List<SelectOptionVO> getProblemTypes() {
		return problemTypes;
	}

	public String execute ()
	{
		final HttpSession session = request.getSession();
		stateList = new ArrayList<SelectOptionVO>();
		districtList = new ArrayList<SelectOptionVO>();
		constituencyList = new ArrayList<SelectOptionVO>();
		mandalList = new ArrayList<SelectOptionVO>();
		wardsOrHamletsList = new ArrayList<SelectOptionVO>();
		parliamentConstituencyList = new ArrayList<SelectOptionVO>();
		problemSourcesList = new ArrayList<SelectOptionVO>(0);
		boothList = new ArrayList<SelectOptionVO>(0);
		
		final RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
		if(user == null || user.getRegistrationID() == null){
			return ERROR;
		}
		problemSourcesList = staticDataService.getAllInformationSources();
		session.setAttribute(ISessionConstants.INFO_SOURCES,problemSourcesList);
		
		try{
			if(session.getAttribute(IWebConstants.PARTY_ANALYST_USER_ROLE) !=null && session.getAttribute(IWebConstants.PARTY_ANALYST_USER_ROLE).equals(true)){
				getDataForPAUser();			
			}
			else if(session.getAttribute(IWebConstants.FREE_USER_ROLE) !=null && session.getAttribute(IWebConstants.FREE_USER_ROLE).equals(true)){
				
				getDataForFreeUser();
		    } 
			problemScopes = regionServiceDataImp.getAllRegionScopesForModule(IConstants.ADD_NEW_PROBLEM,stateId);
			session.setAttribute(ISessionConstants.PROBLEM_TYPES, problemTypes);
			session.setAttribute(ISessionConstants.STATES_AP, stateList);
			session.setAttribute(ISessionConstants.DISTRICTS_AP, districtList);
			session.setAttribute(ISessionConstants.CONSTITUENCIES_AP, constituencyList);
			session.setAttribute(ISessionConstants.P_CONSTITUENCIES_AP, pConstituencyList);
			session.setAttribute(ISessionConstants.MANDALS_AP, mandalList);
			session.setAttribute(ISessionConstants.WARDS_OR_HAMLETS_AP, wardsOrHamletsList);
			session.setAttribute(ISessionConstants.BOOTHS_AP,boothList);
			session.setAttribute(ISessionConstants.IMPACTED_REGIONS, problemScopes);
			session.setAttribute(ISessionConstants.WINDOW_TASK,windowTask);
        }catch(Exception ex){
			LOG.error("Exception Raised in execute method :", ex);
			return Action.ERROR;
		}
	 return SUCCESS;
	}
	
	private void getDataForFreeUser(){
		stateList = regionServiceDataImp.getStatesByCountry(1l);
		setScope(Long.valueOf(requestSrc));
		if("2".equalsIgnoreCase(requestSrc))	
		{
			 problemTypes = regionServiceDataImp.getProblemTypesByRegionScopeId(2l);
			setProblemLocation(stateId);				
				
		}else if("3".equalsIgnoreCase(requestSrc))
		{
			setProblemLocation(districtId);
			final List<SelectOptionVO> locationHirarchies = staticDataService.getLocationsHirarchyByType(IConstants.DISTRICT, districtId);
			if(locationHirarchies != null){
				stateId = getLocationIdFromHirarchy(IConstants.STATE,locationHirarchies);
			}
			districtList = regionServiceDataImp.getDistrictsByStateID(stateId);
			 problemTypes = regionServiceDataImp.getProblemTypesByRegionScopeId(3l);
			
		} else if("4".equalsIgnoreCase(requestSrc))
		{
			getConstituencyLvlDetails();
		} else if(IConstants.LOCAL_ELECTION_BODY.equalsIgnoreCase(requestSrc))
		{
			setProblemLocation(localElectionBodyId);
			final List<SelectOptionVO> locationHirarchies = staticDataService.getLocationsHirarchyByType(IConstants.LOCAL_ELECTION_BODY, localElectionBodyId);
			if(locationHirarchies != null){
				stateId = getLocationIdFromHirarchy(IConstants.STATE,locationHirarchies);
				districtId = getLocationIdFromHirarchy(IConstants.DISTRICT,locationHirarchies);
				constituencyId = getLocationIdFromHirarchy(IConstants.CONSTITUENCY,locationHirarchies);
			}
			districtList = regionServiceDataImp.getDistrictsByStateID(stateId);
			constituencyList = regionServiceDataImp.getConstituenciesByDistrictID(districtId);
			mandalList = regionServiceDataImp.getSubRegionsInConstituency(constituencyId, IConstants.PRESENT_YEAR, null);
			 problemTypes = regionServiceDataImp.getProblemTypesByRegionScopeId(8l);
		} else
		{
			
			setProblemLocation(0l);
		}		
	
	}
	
	private void getConstituencyLvlDetails(){
		problemTypes = regionServiceDataImp.getProblemTypesByRegionScopeId(4l);
		
		if(request.getParameter("constituencyId")!= null){
			constituencyId = Long.valueOf(request.getParameter("constituencyId"));
		}
		
		setProblemLocation(constituencyId);
		final List<SelectOptionVO> locationHirarchies = staticDataService.getLocationsHirarchyByType(IConstants.CONSTITUENCY, constituencyId);
		if(locationHirarchies != null){
			stateId = getLocationIdFromHirarchy(IConstants.STATE,locationHirarchies);
			if(locationHirarchies.size() == 2){
				isParliament = true;
			}else if(locationHirarchies.size() == 3){
				isParliament = false;
			}
		}
		
		if(isParliament)
		{
			setPConstituencyId(Long.valueOf(constituencyId)); 
			pConstituencyList = staticDataService.getConstituenciesByElectionTypeAndStateId(1l,stateId).getConstituencies();
			constituencyList = staticDataService.getLatestAssemblyConstituenciesForParliament(constituencyId).getAssembyConstituencies();								
			
		} else 
		{
			districtId = getLocationIdFromHirarchy(IConstants.DISTRICT,locationHirarchies);
			districtList = regionServiceDataImp.getDistrictsByStateID(stateId);
			constituencyList = regionServiceDataImp.getConstituenciesByDistrictID(districtId);					
		}				
					
	
  }
	private void getDataForPAUser(){
		final HttpSession session = request.getSession();
		RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
		final String accessType =user.getAccessType();
		final Long accessValue= Long.valueOf(user.getAccessValue());
		final Long problemScopeId=problemBeanVO.getProblemScopeId();
		Long districtId=problemBeanVO.getDistrictId();
		Long constituencyId=problemBeanVO.getPConstituencyId();
		final Long locationId = problemBeanVO.getTehsilId();
		
		//Long locationId = jObj.getLong("id");
		if("MLA".equals(accessType))
		{
			LOG.debug("Access Type = MLA ****");
			final List<SelectOptionVO> list = regionServiceDataImp.getStateDistrictByConstituencyID(accessValue);
			
			stateList = regionServiceDataImp.getStatesByCountry(1l);			
			districtList = regionServiceDataImp.getDistrictsByStateID(list.get(0).getId());  			
			constituencyList = regionServiceDataImp.getConstituenciesByDistrictID(list.get(1).getId());
			mandalList = regionServiceDataImp.getSubRegionsInConstituency(list.get(2).getId(), IConstants.PRESENT_YEAR, null);
			mandalList.add(0,new SelectOptionVO(0L,"Select Mandal"));
			stateId = list.get(0).getId();
			districtId = list.get(1).getId();
			constituencyId = list.get(2).getId();
			 problemTypes = regionServiceDataImp.getProblemTypesByRegionScopeId(4l);
			setProblemLocation(constituencyId);
			setScope(4l);
						
		}else if("COUNTRY".equals(accessType))
		{
			LOG.debug("Access Type = Country ****");
			stateList = cadreManagementService.findStatesByCountryID(accessValue.toString());
			stateList.add(0,new SelectOptionVO(0L, "Select State"));
			problemTypes = regionServiceDataImp.getProblemTypesByRegionScopeId(0l);
			setProblemLocation(0l);
			setScope(0l);
			
			
		}else if("STATE".equals(accessType)){
			
			getStateLevelDetails(accessValue,problemScopeId,locationId);
			
		}else if("DISTRICT".equals(accessType)){
			LOG.debug("Access Type = District ****");			
			final List<SelectOptionVO> list = regionServiceDataImp.getStateDistrictByDistrictID(accessValue);
			stateList = regionServiceDataImp.getStatesByCountry(1l);			
			districtList = regionServiceDataImp.getDistrictsByStateID(list.get(0).getId());
			constituencyList = regionServiceDataImp.getConstituenciesByDistrictID(accessValue);
			constituencyList.add(0, new SelectOptionVO(0l,"Select Constituency"));
			stateId = list.get(0).getId();
			districtId = accessValue;	
			 problemTypes = regionServiceDataImp.getProblemTypesByRegionScopeId(3l);
			setProblemLocation(districtId);
			setScope(3l);
			
		} else if("MP".equals(accessType)){
			final List<SelectOptionVO> list = regionServiceDataImp.getStateByParliamentConstituencyID(accessValue);
			stateId = list.get(0).getId();
			stateList = regionServiceDataImp.getStatesByCountry(1l);
			pConstituencyList = staticDataService.getConstituenciesByElectionTypeAndStateId(1l,stateId).getConstituencies();
			final ConstituencyInfoVO constituencyInfoVO = staticDataService.getLatestAssemblyConstituenciesForParliament(accessValue);
			constituencyList = constituencyInfoVO.getAssembyConstituencies();
			problemTypes = regionServiceDataImp.getProblemTypesByRegionScopeId(4l);
								
			setIsParliament(true);
			setPConstituencyId(accessValue);
			setScope(4l);
		}
	
	}
	
	private void getStateLevelDetails(final Long accessValue,final Long problemScopeId,final Long locationId){

		LOG.debug("Access Type = State ****");
		
		final String name = cadreManagementService.getStateName(accessValue);
		final SelectOptionVO obj2 = new SelectOptionVO();
		obj2.setId(accessValue);
		obj2.setName(name);			
		stateList.add(obj2);
		districtList = staticDataService.getDistricts(accessValue);
		districtList.add(0,new SelectOptionVO(0l,"Select District"));
		stateId = accessValue;	
		setProblemLocation(stateId);
		 problemTypes = regionServiceDataImp.getProblemTypesByRegionScopeId(2l);
		 if(problemScopeId == 4){
			constituencyList = regionServiceDataImp.getConstituenciesByDistrictID(districtId);
		 }
		 else if(problemScopeId == 5 || problemScopeId == 7){
				constituencyList = regionServiceDataImp.getConstituenciesByDistrictID(districtId);
		 		mandalList = regionServiceDataImp.getSubRegionsInConstituency(constituencyId, IConstants.PRESENT_YEAR, null);
		 }
		 else if(problemScopeId == 6){ 
			 if(constituencyId != null){
			    mandalList = regionServiceDataImp.getSubRegionsInConstituency(constituencyId, IConstants.PRESENT_YEAR, "RURAL");
			 }
			 constituencyList = regionServiceDataImp.getConstituenciesByDistrictID(districtId);
			 wardsOrHamletsList = regionServiceDataImp.getHamletsOrWards(Long.valueOf(locationId.toString()), IConstants.PRESENT_YEAR);
		 }
		 else if(problemScopeId == 8){
			 if(constituencyId != null){
			    mandalList = regionServiceDataImp.getSubRegionsInConstituency(constituencyId, IConstants.PRESENT_YEAR, "URBAN");
			 }
			 constituencyList = regionServiceDataImp.getConstituenciesByDistrictID(districtId);
			 wardsOrHamletsList = regionServiceDataImp.getHamletsOrWards(Long.valueOf(locationId.toString()), IConstants.PRESENT_YEAR);
		 }
		 else if(problemScopeId == 9){
				constituencyList = regionServiceDataImp.getConstituenciesByDistrictID(districtId);
		 		mandalList = regionServiceDataImp.getSubRegionsInConstituency(constituencyId, IConstants.PRESENT_YEAR, null);
		 		boothList = regionServiceDataImp.getBoothsInTehsilOrMunicipality(locationId, Long.valueOf(IConstants.PRESENT_ELECTION_YEAR), constituencyId);
		 }
		 
		setScope(2l);
		
	
	}
	public Long getLocationIdFromHirarchy(final String locationType,final List<SelectOptionVO> resultList){
		if(resultList != null){
			for(SelectOptionVO option:resultList){
				if(option.getName().equals(locationType)){
					return option.getId();
				}
			}
		}
	 return null;
	}
	public String getProblemTypesBasedOnProblemScope(){
		
		try {
			jObj = new JSONObject(getTask());
			if(jObj.getString("task").equalsIgnoreCase("getProblemTypes")){
				final Long regionScopeId = jObj.getLong("problemScopeId");
				problemTypes = regionServiceDataImp.getProblemTypesByRegionScopeId(regionScopeId);
				setProblemTypes(problemTypes);
			}
		} catch (ParseException e) {
			LOG.error("Exception Raised in getProblemTypesBasedOnProblemScope method :", e);
		}
		
		
		return SUCCESS;
	}
	
	public void prepare() {
			 
		final String problemId = request.getParameter("problemId");
		
		windowTask = request.getParameter("windowTask");
		if(problemId ==null){
			problemBeanVO = new ProblemBeanVO();
		}else {
			
			problemBeanVO = problemManagementService.getProblemCompleteInfoForAUserBasedOnProblemId(Long.valueOf(problemId));
		}
		final String callTrackingProblemId = request.getParameter("callTrackingProblemId");
		
		if(callTrackingProblemId !=null){
			problemBeanVO = callTrackingService.getCallTrackingProblemByProblemId(Long.valueOf(callTrackingProblemId));
		problemBeanVO.setProbSource("3");
		}
		
		if(request.getParameter("callTrackingName") !=null){
		problemBeanVO = new ProblemBeanVO();
		problemBeanVO.setName(request.getParameter("callTrackingName"));
		problemBeanVO.setMobile(request.getParameter("mobile"));
		problemBeanVO.setProbSource("3");
		request.setAttribute("callTracking", request.getParameter("callTrackProb"));
		}
	}

	public ProblemBeanVO getModel() {
	
			return problemBeanVO;
	}
	
	
	public String convertNewsToProblem(){
		
		LOG.debug("Entered into the convertNewsToProblem method");
		
		try{
		
			session = request.getSession();
			final RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
			
			if(user==null){
				return ERROR;
			}
			problemBeanVO.setHasFreeUserRole((Boolean)session.getAttribute("hasFreeUserRole"));
			problemBeanVO.setHasPartyAnalystUserRole((Boolean)session.getAttribute("hasPartyAnalystUserRole"));
			
			if(user.getParentUserId() == null || user.getParentUserId() == 0)
			{
				problemBeanVO.setUserID(user.getRegistrationID());
				problemBeanVO.setSubUserId(user.getRegistrationID());
			}
			else
			{
				problemBeanVO.setUserID(user.getMainAccountId());
				problemBeanVO.setSubUserId(user.getRegistrationID());
			}
			
			problemBeanVO.setProblemPostedBy(IConstants.PARTY_ANALYST_USER);
			
			if("MP".equals(user.getAccessType())){
				problemBeanVO.setIsParliament(true);
			}else{
				problemBeanVO.setIsParliament(false);
			}
					
			  jObj = new JSONObject(getTask());
			
			problemBeanVO.setContentId(jObj.getLong("contentId"));
			problemBeanVO.setExistingFrom(jObj.getString("existingFrom"));
			problemBeanVO.setProblemVisibility(jObj.getString("visibility"));
			
			final String pathSeperator = System.getProperty(IConstants.FILE_SEPARATOR);
			String sourceFilePath = null;
			String destFilePath = null;
			
			if(request.getRequestURL().toString().contains(IConstants.PARTYANALYST_SITE)){
				destFilePath = IWebConstants.STATIC_CONTENT_FOLDER_URL + IConstants.UPLOADED_FILES +pathSeperator+"Problem_Files"+pathSeperator;
			}else{
				destFilePath = context.getRealPath("/")+IConstants.UPLOADED_FILES+pathSeperator+"Problem_Files"+ pathSeperator;
			}
			
			if(request.getRequestURL().toString().contains(IConstants.PARTYANALYST_SITE)){
				sourceFilePath = IWebConstants.STATIC_CONTENT_FOLDER_URL ;
			}else{
				sourceFilePath = context.getRealPath("/");
			}
			problemBeanVO.setSourceFilePath(sourceFilePath);
			problemBeanVO.setDestinationFilePath(destFilePath);
			problemBeanVO.setPathSepecrator(pathSeperator);
			
			problemBeanVO.setYear(IConstants.PRESENT_YEAR);
	        problemBeanVO.setWindowTask(IConstants.NEW);
	            
	        problemBeanFromDB =  problemManagementService.saveProblemDataForNews(problemBeanVO);
			
			
			
		}catch(Exception e){
			LOG.error("Exception raised in the convertNewsToProblem method ",e);
		}
		
		return Action.SUCCESS;
		
	}
}
