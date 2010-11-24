package com.itgrids.partyanalyst.web.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.service.IProblemManagementService;
import com.itgrids.partyanalyst.service.IRegionServiceData;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.itgrids.partyanalyst.service.impl.ProblemManagementService;
import com.itgrids.partyanalyst.service.impl.StaticDataService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.itgrids.partyanalyst.utils.ISessionConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class AddNewProblemAction extends ActionSupport implements ServletRequestAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(AddNewProblemAction.class);
	private HttpServletRequest request;
	private HttpSession session;
	private String task = null;
	JSONObject jObj = null;
	private List<SelectOptionVO> stateList;
	private List<SelectOptionVO> districtList;
	private List<SelectOptionVO> constituencyList;
	private List<SelectOptionVO> pConstituencyList;
	private List<SelectOptionVO> mandalList;
	private List<SelectOptionVO> wardsOrHamletsList;
	private List<SelectOptionVO> hamletList;
	private List<SelectOptionVO> parliamentConstituencyList;
	private IRegionServiceData regionServiceDataImp;
	private IStaticDataService staticDataService;
	private IProblemManagementService problemManagementService;
	
	private String requestSrc;
	private Long stateId;
	private Long districtId;
	private Long constituencyId;
	private Long localElectionBodyId;
	private Boolean isParliament;
	private List<SelectOptionVO> problemScopes;
	private Long  problemLocation;
	
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
		return new Long(requestSrc);
	}	

	public List<SelectOptionVO> getProblemScopes() {
		return problemScopes;
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

	public String execute () throws Exception 
	{
		/*constituencyId = 232l;
		stateId= 1l;
		districtId = 19l;
		isParliament =false;
		requestSrc = "3";*/
		HttpSession session = request.getSession();
		session = request.getSession();
		stateList = new ArrayList<SelectOptionVO>();
		districtList = new ArrayList<SelectOptionVO>();
		constituencyList = new ArrayList<SelectOptionVO>();
		mandalList = new ArrayList<SelectOptionVO>();
		wardsOrHamletsList = new ArrayList<SelectOptionVO>();
		parliamentConstituencyList = new ArrayList<SelectOptionVO>();
		problemScopes = problemManagementService.getAllProblemImpactLevel();
		RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
		if(user == null)
			return ERROR;
		session.setAttribute(ISessionConstants.STATES, new ArrayList<SelectOptionVO>());
		session.setAttribute(ISessionConstants.DISTRICTS, new ArrayList<SelectOptionVO>());
		session.setAttribute(ISessionConstants.CONSTITUENCIES, new ArrayList<SelectOptionVO>());
		session.setAttribute(ISessionConstants.MANDALS, new ArrayList<SelectOptionVO>());
		session.setAttribute(ISessionConstants.WARDS_OR_HAMLETS, new ArrayList<SelectOptionVO>());
		session.setAttribute(ISessionConstants.IMPACTED_REGIONS, problemScopes);
		
		try{
			if("1".equalsIgnoreCase(requestSrc))	
			{
				stateList = regionServiceDataImp.getStatesByCountry(1l);
				session.setAttribute(ISessionConstants.STATES, stateList);
				setProblemLocation(stateId);
				
					
			}else if("2".equalsIgnoreCase(requestSrc))
			{
				stateList = regionServiceDataImp.getStatesByCountry(1l);
				setProblemLocation(districtId);
				List<SelectOptionVO> locationHirarchies = staticDataService.getLocationsHirarchyByType(IConstants.DISTRICT, districtId);
				if(locationHirarchies != null){
					stateId = getLocationIdFromHirarchy(IConstants.STATE,locationHirarchies);
				}
				districtList = regionServiceDataImp.getDistrictsByStateID(stateId);
				
				session.setAttribute(ISessionConstants.STATES, stateList);
				session.setAttribute(ISessionConstants.DISTRICTS, districtList);	
				
				
			} else if("3".equalsIgnoreCase(requestSrc))
			{
				stateList = regionServiceDataImp.getStatesByCountry(1l);
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
					pConstituencyList = staticDataService.getConstituenciesByElectionTypeAndStateId(1l,stateId).getConstituencies();
					session.setAttribute(ISessionConstants.P_CONSTITUENCIES, pConstituencyList);
					session.setAttribute(ISessionConstants.CONSTITUENCIES,new ArrayList<SelectOptionVO>());
					
				} else 
				{
					districtId = getLocationIdFromHirarchy(IConstants.DISTRICT,locationHirarchies);
					districtList = regionServiceDataImp.getDistrictsByStateID(stateId);
					constituencyList = regionServiceDataImp.getConstituenciesByDistrictID(districtId);
					session.setAttribute(ISessionConstants.DISTRICTS, districtList);
					session.setAttribute(ISessionConstants.P_CONSTITUENCIES, new ArrayList<SelectOptionVO>());
					session.setAttribute(ISessionConstants.CONSTITUENCIES, constituencyList);
					
				}				
				session.setAttribute(ISessionConstants.STATES, stateList);			
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
							
				session.setAttribute(ISessionConstants.STATES, stateList);
				session.setAttribute(ISessionConstants.DISTRICTS, districtList);
				session.setAttribute(ISessionConstants.CONSTITUENCIES, constituencyList);
				session.setAttribute(ISessionConstants.MANDALS, mandalList);
			} else
			{
				stateList = regionServiceDataImp.getStatesByCountry(1l);
				session.setAttribute(ISessionConstants.STATES, stateList);
				setProblemLocation(0l);
			}		
		
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
	

}
