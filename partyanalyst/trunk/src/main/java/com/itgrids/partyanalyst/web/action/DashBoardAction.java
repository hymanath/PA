package com.itgrids.partyanalyst.web.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.service.ICrossVotingEstimationService;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.itgrids.partyanalyst.service.IVotersAnalysisService;
import com.itgrids.partyanalyst.service.impl.CadreManagementService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.itgrids.partyanalyst.service.IConstituencySearchService;

@SuppressWarnings("serial")
public class DashBoardAction extends ActionSupport implements ServletRequestAware{

	
	private static final org.apache.log4j.Logger log = Logger.getLogger(DashBoardAction.class); 
	
	private HttpServletRequest request;
	private List<SelectOptionVO> statesList,statesListForLocalBodyElection;
	private IStaticDataService staticDataService;
	private HttpSession session;
	private List<SelectOptionVO> electionYearList,partysList;
	private List<SelectOptionVO> constituencyList, userAccessConstituencyList,assemblyConstis,parlConstis,parliaments;
	private ICrossVotingEstimationService crossVotingEstimationService;
	private IVotersAnalysisService votersAnalysisService;
	private CadreManagementService cadreManagementService;
	JSONObject jObj;
	private String task;
	private Long constituencyId;
	private Long districtId;
	private Long stateId;
	private List<SelectOptionVO> pConstituencyList,aConstituencyList;
	private IConstituencySearchService constituencySearchService;
	private List<SelectOptionVO> districtStateList;
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
	
	public JSONObject getjObj() {
		return jObj;
	}


	public void setjObj(JSONObject jObj) {
		this.jObj = jObj;
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


	public String execute()
	{	
		session = request.getSession();
		
		statesList = staticDataService.getParticipatedStatesForAnElectionType(new Long(2));
		
		statesListForLocalBodyElection = staticDataService.getParticipatedStatesForAnElectionType(new Long(5)); 
		
		if(statesListForLocalBodyElection == null || statesListForLocalBodyElection.size() == 0)
			statesListForLocalBodyElection.add(new SelectOptionVO(0L,"Select State"));
		RegistrationVO user=(RegistrationVO) session.getAttribute("USER");
		if(user == null)
		return INPUT;
		constituencyId = user.getConstituencyId();
		districtStateList = constituencySearchService.getDistrictAndStateId(constituencyId);
		SelectOptionVO selectOptionVO = new SelectOptionVO();
		selectOptionVO = districtStateList.get(0);
		districtId = selectOptionVO.getId();
		stateId = selectOptionVO.getOrderId();
		constituencyList = user.getUserAccessVoterConstituencies();
		assemblyConstis = (List<SelectOptionVO>)session.getAttribute("assemblyConstis");
		parlConstis = (List<SelectOptionVO>)session.getAttribute("parlConstis");
		SelectOptionVO initialVo = new SelectOptionVO(0L,"Select Constituency");
		Map<Long,List<SelectOptionVO>> assembliesForParl = (Map<Long,List<SelectOptionVO>>)session.getAttribute("assembliesForParl");
		parliaments = (List<SelectOptionVO>)session.getAttribute("parliaments");
		Long userID = user.getRegistrationID();
		Long electionYear = new Long(IConstants.PRESENT_ELECTION_YEAR);
		Long electionTypeId = new Long(IConstants.ASSEMBLY_ELECTION_TYPE_ID);
		
		if(constituencyList == null || constituencyList.isEmpty()){
			
			userAccessConstituencyList = crossVotingEstimationService.getConstituenciesForElectionYearAndTypeWithUserAccess(userID,electionYear,electionTypeId);
			assemblyConstis = userAccessConstituencyList ;
			constituencyList = votersAnalysisService.getConstituencyList(userAccessConstituencyList);
			constituencyList.add(0, initialVo);
			assemblyConstis.add(0, initialVo);
			user.setUserAccessVoterConstituencies(constituencyList);
		}
		
		if(assemblyConstis == null){
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
		
		if(assembliesForParl == null){
			List<Long> ids = new ArrayList<Long>();
			for(SelectOptionVO vo:assemblyConstis){
				ids.add(vo.getId());
			}
		  Object[] values = cadreManagementService.getConstituencies(ids);
		    session.setAttribute("assembliesForParl",values[1]);
		    assembliesForParl = (Map<Long,List<SelectOptionVO>>)values[1];
			session.setAttribute("parliaments",values[0]);
			parliaments = (List<SelectOptionVO>)values[0];
		}
		session.setAttribute("assemblyConstis",assemblyConstis);
		session.setAttribute("parlConstis",parlConstis);
		
		List<String> years = crossVotingEstimationService.getElectionYearsForBoothResult();
		electionYearList = new ArrayList<SelectOptionVO>();
		if(years != null && years.size() > 0)
		{  
			
			for(String year : years)
				electionYearList.add(new SelectOptionVO(Long.parseLong(year.trim()), year));
			
			
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
				log.error("Exception Occured in getRequestMessagesForUser() Method,Exception is- ",e);
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
			pConstituencyList = crossVotingEstimationService.getConstituenciesForElectionYearAndScopeForBoothData(String.valueOf(year), new Long(1));
		}catch(Exception e)
		{
			
		}
		return Action.SUCCESS;
	}
}
