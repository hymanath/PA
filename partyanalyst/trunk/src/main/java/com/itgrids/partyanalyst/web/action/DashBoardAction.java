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

import com.itgrids.partyanalyst.dto.CrossVotingVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.service.IAnanymousUserService;
import com.itgrids.partyanalyst.service.IConstituencySearchService;
import com.itgrids.partyanalyst.service.ICrossVotingEstimationService;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.itgrids.partyanalyst.service.IVotersAnalysisService;
import com.itgrids.partyanalyst.service.impl.CadreManagementService;
import com.itgrids.partyanalyst.util.IWebConstants;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

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
	private SelectOptionVO boothAnalysisData;
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
	private CrossVotingVO crossVotingVO;
	private Long crossVotingYear;
	private Long crossVotingPConsti;
	private Long crossVotingAConsti;
	private Long crossVotingParty;
	private List<SelectOptionVO> mandalsList;
	private boolean politician;
	
	
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


	public String execute()
	{	
	  try{
		session = request.getSession();
		
		statesList = staticDataService.getParticipatedStatesForAnElectionType(new Long(2));
		
		//electionYearsList=staticDataService.getElectionYearsForBooths(1l,2l);
		
		statesListForLocalBodyElection = staticDataService.getParticipatedStatesForAnElectionType(new Long(5)); 
		
		if(statesListForLocalBodyElection == null || statesListForLocalBodyElection.size() == 0)
			statesListForLocalBodyElection.add(new SelectOptionVO(0L,"Select State"));
		RegistrationVO user=(RegistrationVO) session.getAttribute("USER");
		if(user == null)
		return INPUT;
		
		if(!(Boolean)session.getAttribute(IWebConstants.PARTY_ANALYST_USER_ROLE))
		 return "userProfile";
		
		
		constituencyId = user.getConstituencyId();
		loginUserName = user.getFirstName()+" "+user.getLastName();
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
		if("Politician".equalsIgnoreCase(user.getUserType())){
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
		List<Long> mandalIds = new ArrayList<Long>();
		mandalList = crossVotingEstimationService.getTehsilsForConstituencies(constituencyIds);
		if(mandalList != null && mandalList.size() > 0)
		{
			for (SelectOptionVO mandal : mandalList) {
				Long mandalId = mandal.getId();
				mandalIds.add(mandalId);
			}
		}
		//boothsList = crossVotingEstimationService.getBoothsForConstituencyList(constituencyIds);
		panchayatsList = crossVotingEstimationService.getPanchayatsForConstituencyList(mandalIds);
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
				
				/*if(assemblyConstis != null && assemblyConstis.size() >0)
				constituencyId = assemblyConstis.get(1).getId();*/
				
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
			
			/*
			if(parlConstis != null && parlConstis.size() >1)
			constituencyId = parlConstis.get(1).getId();*/
			if(assemblyConstis != null && assemblyConstis.size() >0){
				constituencyId = assemblyConstis.get(1).getId();
				districtId = staticDataService.getdistrictForAConstituency(constituencyId);
			}
		}
		
		List<Long> assemblyIds = new ArrayList<Long>();
		for(SelectOptionVO vo:assemblyConstis)
			assemblyIds.add(vo.getId());
		
		
		
		electionYearsList=staticDataService.getElectionYearsByConstituencyIds(assemblyIds);
		
		//assembliesForParl
		//parliaments
		crossVotingVO = new CrossVotingVO();
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
			log.error("Exception is ",e);
		}
	  }catch(Exception e){
		  log.error("Exception rised in execute method ",e);
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
			        //System.out.println("Equals..: " + Alist.get(i));
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
				pConstituencyList = crossVotingEstimationService.getPartiesForConstituencyAndElectionYearForBoothData(jObj.getLong("assemblyId"),jObj.getString("year"));
			}			
			
		}catch(Exception e)
		{
			log.error("Exception rised in getConstituenciesAndParties", e);
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
}
