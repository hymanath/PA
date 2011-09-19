package com.itgrids.partyanalyst.web.action;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
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
import com.itgrids.partyanalyst.utils.IConstants;
import com.itgrids.partyanalyst.utils.ISessionConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class AddNewProblemAction extends ActionSupport implements ServletRequestAware,Preparable,ModelDriven<ProblemBeanVO>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(AddNewProblemAction.class);
	private HttpServletRequest request;
	private HttpSession session;
	private String task ;
	JSONObject jObj;
	private List<SelectOptionVO> stateList;
	private List<SelectOptionVO> districtList;
	private List<SelectOptionVO> constituencyList;
	private List<SelectOptionVO> pConstituencyList;
	private List<SelectOptionVO> mandalList;
	private List<SelectOptionVO> wardsOrHamletsList;
	private List<SelectOptionVO> hamletList;
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
	private Long constituencyId = 0l;
	private Long localElectionBodyId = 0l;
	private Boolean isParliament = false;
	private List<SelectOptionVO> problemScopes;
	private Long problemType;
	private Long  problemLocation;
	private Long scope;
	private Long pConstituencyId;
	private RegistrationVO user = null;
	
	public void setUser(RegistrationVO user) {
		this.user = user;
	}

	public RegistrationVO getUser() {
		return user;
	}

	public void setProblemBeanVO(ProblemBeanVO problemBeanVO) {
		this.problemBeanVO = problemBeanVO;
	}

	public ProblemBeanVO getProblemBeanVO() {
		return problemBeanVO;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;		
	}	
	
	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public JSONObject getJObj() {
		return jObj;
	}

	public void setJObj(JSONObject obj) {
		jObj = obj;
	}

	public List<SelectOptionVO> getStateList() {
		return stateList;
	}

	public void setStateList(List<SelectOptionVO> stateList) {
		this.stateList = stateList;
	}

	public List<SelectOptionVO> getDistrictList() {
		return districtList;
	}

	public void setDistrictList(List<SelectOptionVO> districtList) {
		this.districtList = districtList;
	}

	public List<SelectOptionVO> getConstituencyList() {
		return constituencyList;
	}

	public void setConstituencyList(List<SelectOptionVO> constituencyList) {
		this.constituencyList = constituencyList;
	}
	
	public List<SelectOptionVO> getMandalList() {
		return mandalList;
	}

	public void setMandalList(List<SelectOptionVO> mandalList) {
		this.mandalList = mandalList;
	}
	
	public List<SelectOptionVO> getWardsOrHamletsList() {
		return wardsOrHamletsList;
	}

	public void setWardsOrHamletsList(List<SelectOptionVO> wardsOrHamletsList) {
		this.wardsOrHamletsList = wardsOrHamletsList;
	}

	public List<SelectOptionVO> getHamletList() {
		return hamletList;
	}

	public void setHamletList(List<SelectOptionVO> hamletList) {
		this.hamletList = hamletList;
	}
	
	public IRegionServiceData getRegionServiceDataImp() {
		return regionServiceDataImp;
	}

	public void setRegionServiceDataImp(IRegionServiceData regionServiceDataImp) {
		this.regionServiceDataImp = regionServiceDataImp;
	}

	public void setParliamentConstituencyList(
			List<SelectOptionVO> parliamentConstituencyList) {
		this.parliamentConstituencyList = parliamentConstituencyList;
	}

	public List<SelectOptionVO> getParliamentConstituencyList() {
		return parliamentConstituencyList;
	}
	
	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}	

	public String getRequestSrc() {
		return requestSrc;
	}

	public void setRequestSrc(String requestSrc) {
		this.requestSrc = requestSrc;
	}

	public Long getStateId() {
		return stateId;
	}

	public void setStateId(Long stateId) {
		this.stateId = stateId;
	}

	public Long getDistrictId() {
		return districtId;
	}

	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}

	public Long getConstituencyId() {
		return constituencyId;
	}

	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}

	public Long getLocalElectionBodyId() {
		return localElectionBodyId;
	}

	public void setLocalElectionBodyId(Long localElectionBodyId) {
		this.localElectionBodyId = localElectionBodyId;
	}

	public Boolean getIsParliament() {
		return isParliament;
	}

	public void setIsParliament(Boolean isParliament) {
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

	public void setStaticDataService(IStaticDataService staticDataService) {
		this.staticDataService = staticDataService;
	}
	
	public IProblemManagementService getProblemManagementService() {
		return problemManagementService;
	}

	public void setProblemManagementService(
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
	
	public void setProblemType(Long problemType) {
		this.problemType = problemType;
	}

	public Long getProblemType() {
		return problemType;
	}

	public void setProblemScopes(List<SelectOptionVO> problemScopes) {
		this.problemScopes = problemScopes;
	}

	public Long getProblemLocation() {
		return problemLocation;
	}

	public void setProblemLocation(Long problemLocation) {
		this.problemLocation = problemLocation;
	}	
	
	public List<SelectOptionVO> getPConstituencyList() {
		return pConstituencyList;
	}

	public void setPConstituencyList(List<SelectOptionVO> constituencyList) {
		pConstituencyList = constituencyList;
	}	

	public CadreManagementService getCadreManagementService() {
		return cadreManagementService;
	}

	public void setCadreManagementService(
			CadreManagementService cadreManagementService) {
		this.cadreManagementService = cadreManagementService;
	}	

	public Long getScope() {
		return scope;
	}

	public void setScope(Long scope) {
		this.scope = scope;
	}
	

	public Long getPConstituencyId() {
		return pConstituencyId;
	}

	public void setPConstituencyId(Long constituencyId) {
		pConstituencyId = constituencyId;
	}
	
	public Long getDefaultPConstituency()
	{
		return pConstituencyId; 
	}
	
	public List<SelectOptionVO> getProblemSourcesList() {
		return problemSourcesList;
	}

	public void setProblemSourcesList(List<SelectOptionVO> problemSourcesList) {
		this.problemSourcesList = problemSourcesList;
	}
	
	public ICallTrackingService getCallTrackingService() {
		return callTrackingService;
	}

	public void setCallTrackingService(ICallTrackingService callTrackingService) {
		this.callTrackingService = callTrackingService;
	}
	
	public void setProblemTypes(List<SelectOptionVO> problemTypes) {
		this.problemTypes = problemTypes;
	}

	public List<SelectOptionVO> getProblemTypes() {
		return problemTypes;
	}

	public String execute () throws Exception 
	{
		HttpSession session = request.getSession();
		session = request.getSession();
		stateList = new ArrayList<SelectOptionVO>();
		districtList = new ArrayList<SelectOptionVO>();
		constituencyList = new ArrayList<SelectOptionVO>();
		mandalList = new ArrayList<SelectOptionVO>();
		wardsOrHamletsList = new ArrayList<SelectOptionVO>();
		parliamentConstituencyList = new ArrayList<SelectOptionVO>();
		problemSourcesList = new ArrayList<SelectOptionVO>(0);
		
		RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
		if(user == null)
			return ERROR;
		
		/*
		session.setAttribute(ISessionConstants.STATES, new ArrayList<SelectOptionVO>());
		session.setAttribute(ISessionConstants.DISTRICTS, new ArrayList<SelectOptionVO>());
		session.setAttribute(ISessionConstants.CONSTITUENCIES, new ArrayList<SelectOptionVO>());
		session.setAttribute(ISessionConstants.MANDALS, new ArrayList<SelectOptionVO>());
		session.setAttribute(ISessionConstants.WARDS_OR_HAMLETS, new ArrayList<SelectOptionVO>());
		session.setAttribute(ISessionConstants.IMPACTED_REGIONS, problemScopes);
		*/
		
		problemSourcesList = staticDataService.getAllInformationSources();
		session.setAttribute(ISessionConstants.INFO_SOURCES,problemSourcesList);
		
		try{
		if("FreeUser".equals(session.getAttribute("UserType")))
		{	
			setScope(new Long(requestSrc));
			if("2".equalsIgnoreCase(requestSrc))	
			{
				stateList = regionServiceDataImp.getStatesByCountry(1l);
				 problemTypes = regionServiceDataImp.getProblemTypesByRegionScopeId(2l);
				setProblemLocation(stateId);				
					
			}else if("3".equalsIgnoreCase(requestSrc))
			{
				stateList = regionServiceDataImp.getStatesByCountry(1l);
				setProblemLocation(districtId);
				List<SelectOptionVO> locationHirarchies = staticDataService.getLocationsHirarchyByType(IConstants.DISTRICT, districtId);
				if(locationHirarchies != null){
					stateId = getLocationIdFromHirarchy(IConstants.STATE,locationHirarchies);
				}
				districtList = regionServiceDataImp.getDistrictsByStateID(stateId);
				 problemTypes = regionServiceDataImp.getProblemTypesByRegionScopeId(3l);
				
			} else if("4".equalsIgnoreCase(requestSrc))
			{
				stateList = regionServiceDataImp.getStatesByCountry(1l);
				 problemTypes = regionServiceDataImp.getProblemTypesByRegionScopeId(4l);
				setProblemLocation(constituencyId);
				List<SelectOptionVO> locationHirarchies = staticDataService.getLocationsHirarchyByType(IConstants.CONSTITUENCY, constituencyId);
				if(locationHirarchies != null){
					stateId = getLocationIdFromHirarchy(IConstants.STATE,locationHirarchies);
					if(locationHirarchies.size() == 2)
						isParliament = true;
					else if(locationHirarchies.size() == 3)
						isParliament = false;
				}
				
				if(isParliament)
				{
					setPConstituencyId(constituencyId); 
					ConstituencyInfoVO constituencyInfoVO = new ConstituencyInfoVO();
					pConstituencyList = staticDataService.getConstituenciesByElectionTypeAndStateId(1l,stateId).getConstituencies();
					constituencyInfoVO = staticDataService.getLatestAssemblyConstituenciesForParliament(constituencyId);
					constituencyList = constituencyInfoVO.getAssembyConstituencies();										
					
				} else 
				{
					districtId = getLocationIdFromHirarchy(IConstants.DISTRICT,locationHirarchies);
					districtList = regionServiceDataImp.getDistrictsByStateID(stateId);
					constituencyList = regionServiceDataImp.getConstituenciesByDistrictID(districtId);					
				}				
							
			} else if(IConstants.LOCAL_ELECTION_BODY.equalsIgnoreCase(requestSrc))
			{
				stateList = regionServiceDataImp.getStatesByCountry(1l);
				setProblemLocation(localElectionBodyId);
				List<SelectOptionVO> locationHirarchies = staticDataService.getLocationsHirarchyByType(IConstants.LOCAL_ELECTION_BODY, localElectionBodyId);
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
				stateList = regionServiceDataImp.getStatesByCountry(1l);
				setProblemLocation(0l);
			}		
		} else if("PartyAnalyst".equals(session.getAttribute("UserType")))
		{
			String accessType =user.getAccessType();
			Long accessValue= new Long(user.getAccessValue());
			
			if("MLA".equals(accessType))
			{
				log.debug("Access Type = MLA ****");
				List<SelectOptionVO> list = regionServiceDataImp.getStateDistrictByConstituencyID(accessValue);
				
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
				log.debug("Access Type = Country ****");
				stateList = cadreManagementService.findStatesByCountryID(accessValue.toString());
				stateList.add(0,new SelectOptionVO(0L, "Select State"));
				problemTypes = regionServiceDataImp.getProblemTypesByRegionScopeId(0l);
				setProblemLocation(0l);
				setScope(0l);
				
				
			}else if("STATE".equals(accessType)){
				log.debug("Access Type = State ****");
				
				String name = cadreManagementService.getStateName(accessValue);
				SelectOptionVO obj2 = new SelectOptionVO();
				obj2.setId(accessValue);
				obj2.setName(name);			
				stateList.add(obj2);
				districtList = staticDataService.getDistricts(accessValue);
				districtList.add(0,new SelectOptionVO(0l,"Select District"));
				stateId = accessValue;	
				setProblemLocation(stateId);
				 problemTypes = regionServiceDataImp.getProblemTypesByRegionScopeId(2l);
				setScope(2l);
				
			}else if("DISTRICT".equals(accessType)){
				log.debug("Access Type = District ****");			
				List<SelectOptionVO> list = regionServiceDataImp.getStateDistrictByDistrictID(accessValue);
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
				List<SelectOptionVO> list = regionServiceDataImp.getStateByParliamentConstituencyID(accessValue);
				stateId = list.get(0).getId();
				stateList = regionServiceDataImp.getStatesByCountry(1l);
				ConstituencyInfoVO constituencyInfoVO = new ConstituencyInfoVO();
				pConstituencyList = staticDataService.getConstituenciesByElectionTypeAndStateId(1l,stateId).getConstituencies();
				constituencyInfoVO = staticDataService.getLatestAssemblyConstituenciesForParliament(accessValue);
				constituencyList = constituencyInfoVO.getAssembyConstituencies();
				problemTypes = regionServiceDataImp.getProblemTypesByRegionScopeId(4l);
									
				/*
				log.debug("Access Type = MP ****");
				ConstituencyInfoVO constituencyInfoVO = new ConstituencyInfoVO();
				pConstituencyList = new ArrayList<SelectOptionVO>();
				
				constituencyInfoVO = staticDataService.getLatestAssemblyConstituenciesForParliament(accessValue);
				constituencyList = constituencyInfoVO.getAssembyConstituencies();
				constituencyList.add(0,new SelectOptionVO(0l,"Select Constituency"));
				pConstituencyList.add(new SelectOptionVO(constituencyInfoVO.getConstituencyId(),constituencyInfoVO.getConstituencyName()));
				*/
				setIsParliament(true);
				setPConstituencyId(accessValue);
				setScope(4l);
			}
		}
		problemScopes = regionServiceDataImp.getAllRegionScopesForModule(IConstants.ADD_NEW_PROBLEM,stateId);
		problemTypes = regionServiceDataImp.getProblemTypesByRegionScopeId(2l);
		session.setAttribute(ISessionConstants.PROBLEM_TYPES, problemTypes);
		session.setAttribute(ISessionConstants.STATES_AP, stateList);
		session.setAttribute(ISessionConstants.DISTRICTS_AP, districtList);
		session.setAttribute(ISessionConstants.CONSTITUENCIES_AP, constituencyList);
		session.setAttribute(ISessionConstants.P_CONSTITUENCIES_AP, pConstituencyList);
		session.setAttribute(ISessionConstants.MANDALS_AP, mandalList);
		session.setAttribute(ISessionConstants.WARDS_OR_HAMLETS_AP, wardsOrHamletsList);
		session.setAttribute(ISessionConstants.BOOTHS_AP,new ArrayList<SelectOptionVO>());
		session.setAttribute(ISessionConstants.IMPACTED_REGIONS, problemScopes);
        }catch(Exception ex){
			ex.printStackTrace();
			log.error("Exception Raised :" + ex);
			return Action.ERROR;
		}
	 return SUCCESS;
	}
	
	public Long getLocationIdFromHirarchy(String locationType,List<SelectOptionVO> resultList){
		if(resultList != null){
			for(SelectOptionVO option:resultList){
				if(option.getName().equals(locationType))
					return option.getId();
			}
		}
	 return null;
	}
	public String getProblemTypesBasedOnProblemScope(){
		
		try {
			jObj = new JSONObject(getTask());
			if(jObj.getString("task").equalsIgnoreCase("getProblemTypes")){
				Long regionScopeId = jObj.getLong("problemScopeId");
				problemTypes = regionServiceDataImp.getProblemTypesByRegionScopeId(regionScopeId);
				setProblemTypes(problemTypes);
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return SUCCESS;
	}
	
	@Override
	public void prepare() throws Exception {
			 
		String problemHistoryId =request.getParameter("problemHistoryId");
		if(problemHistoryId !=null){
		 problemBeanVO = new ProblemBeanVO();
			problemBeanVO = problemManagementService.getProblemCompleteInfoForAUserBasedOnHistoryId(new Long(problemHistoryId));
		}
		String callTrackingProblemId = request.getParameter("callTrackingProblemId");
		
		if(callTrackingProblemId !=null){
			problemBeanVO = callTrackingService.getCallTrackingProblemByProblemId(new Long(callTrackingProblemId));
		problemBeanVO.setProbSource("3");
		}
		
		if(request.getParameter("callTrackingName") !=null){
		problemBeanVO = new ProblemBeanVO();
		problemBeanVO.setProblem(request.getParameter("problemPurpose"));
		problemBeanVO.setName(request.getParameter("callTrackingName"));
		problemBeanVO.setMobile(request.getParameter("mobile"));
		problemBeanVO.setVillage(request.getParameter("villageTown"));
		problemBeanVO.setProbSource("3");
		request.setAttribute("callTracking", request.getParameter("callTrackProb"));
		}
	}

	@Override
	public ProblemBeanVO getModel() {
		// TODO Auto-generated method stub
			return problemBeanVO;
	}

}
