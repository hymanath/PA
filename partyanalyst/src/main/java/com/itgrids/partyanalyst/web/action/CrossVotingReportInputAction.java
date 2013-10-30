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
import com.itgrids.partyanalyst.helper.EntitlementsHelper;
import com.itgrids.partyanalyst.service.ICrossVotingEstimationService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class CrossVotingReportInputAction extends ActionSupport implements ServletRequestAware {

	private static final long serialVersionUID = 1L;
	private List<SelectOptionVO> electionYearList,pConstituencyList,aConstituencyList,partysList;
	private List<SelectOptionVO> parliamentList;
	private HttpServletRequest request;
	private EntitlementsHelper entitlementsHelper;
	private ICrossVotingEstimationService crossVotingEstimationService;
	private String acId;
	private String pcId;
	private String electionYear;
	private String party;
	private Long year , pConstituency,aConstituency,partyId;
	private String task;
	JSONObject jObj;
	
	private static final Logger LOG = Logger.getLogger(CrossVotingReportInputAction.class);
	
	public ICrossVotingEstimationService getCrossVotingEstimationService() {
		return crossVotingEstimationService;
	}

	public void setCrossVotingEstimationService(
			ICrossVotingEstimationService crossVotingEstimationService) {
		this.crossVotingEstimationService = crossVotingEstimationService;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;	
	}
	
	public List<SelectOptionVO> getElectionYearList() {
		return electionYearList;
	}

	public void setElectionYearList(List<SelectOptionVO> electionYearList) {
		this.electionYearList = electionYearList;
	}

	public List<SelectOptionVO> getParliamentList() {
		return parliamentList;
	}

	public void setParliamentList(List<SelectOptionVO> parliamentList) {
		this.parliamentList = parliamentList;
	}
	
	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public EntitlementsHelper getEntitlementsHelper() {
		return entitlementsHelper;
	}

	public void setEntitlementsHelper(EntitlementsHelper entitlementsHelper) {
		this.entitlementsHelper = entitlementsHelper;
	}

	public String getAcId() {
		return acId;
	}

	public String getPcId() {
		return pcId;
	}

	public String getElectionYear() {
		return electionYear;
	}

	public String getParty() {
		return party;
	}

	public void setAcId(String acId) {
		this.acId = acId;
	}

	public void setPcId(String pcId) {
		this.pcId = pcId;
	}

	public void setElectionYear(String electionYear) {
		this.electionYear = electionYear;
	}

	public void setParty(String party) {
		this.party = party;
	}

	
	public Long getYear() {
		return year;
	}

	public void setYear(Long year) {
		this.year = year;
	}

	public Long getpConstituency() {
		return pConstituency;
	}

	public void setpConstituency(Long pConstituency) {
		this.pConstituency = pConstituency;
	}

	public Long getaConstituency() {
		return aConstituency;
	}

	public void setaConstituency(Long aConstituency) {
		this.aConstituency = aConstituency;
	}

	public Long getPartyId() {
		return partyId;
	}

	public void setPartyId(Long partyId) {
		this.partyId = partyId;
	}

	
	public List<SelectOptionVO> getpConstituencyList() {
		return pConstituencyList;
	}

	public void setpConstituencyList(List<SelectOptionVO> pConstituencyList) {
		this.pConstituencyList = pConstituencyList;
	}

	public List<SelectOptionVO> getaConstituencyList() {
		return aConstituencyList;
	}

	public void setaConstituencyList(List<SelectOptionVO> aConstituencyList) {
		this.aConstituencyList = aConstituencyList;
	}

	public List<SelectOptionVO> getPartysList() {
		return partysList;
	}

	public void setPartysList(List<SelectOptionVO> partysList) {
		this.partysList = partysList;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public String execute(){
		
		HttpSession session = request.getSession();
		if(session.getAttribute(IConstants.USER) == null && 
				!entitlementsHelper.checkForEntitlementToViewReport(null, IConstants.CROSS_VOTING_REPORT))
			return INPUT;
		if(!entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)session.getAttribute(IConstants.USER), IConstants.CROSS_VOTING_REPORT))
			return ERROR;
		
		
		/*
		electionYearList.add(new SelectOptionVO(2009l, "2009"));
		electionYearList.add(new SelectOptionVO(2004l, "2004"));
		*/
		
		//List<String> years = crossVotingEstimationService.getElectionYearsForBoothResult();
		RegistrationVO user = (RegistrationVO)session.getAttribute(IConstants.USER);
		List<SelectOptionVO> constituencyList = (List<SelectOptionVO>)session.getAttribute("assemblyConstis");
		
		if(constituencyList == null){
		  constituencyList = crossVotingEstimationService.getConstituenciesForElectionYearAndTypeWithUserAccess(user.getRegistrationID(),new Long(IConstants.PRESENT_ELECTION_YEAR),new Long(IConstants.ASSEMBLY_ELECTION_TYPE_ID));
		  constituencyList.add(0,new SelectOptionVO(0l,"Select Constituency"));
		  session.setAttribute("assemblyConstis",constituencyList);
		}
		
		List<Long> constituencyIds = new ArrayList<Long>();
		
		for(SelectOptionVO constituency : constituencyList){
			if(constituency.getId().longValue() != 0l)
			  constituencyIds.add(constituency.getId());
		}
		
		electionYearList = crossVotingEstimationService.getAllElectionYearsForCrossVoting(constituencyIds);
		
		if(year != null  && year > 0)
		{
			pConstituencyList = crossVotingEstimationService.getAllParliamentConstituenciesForCrossVoting(constituencyIds, String.valueOf(year));
		}
		else
		{
			pConstituencyList = new ArrayList<SelectOptionVO>();
		}
		if(year != null && pConstituency != null && pConstituency > 0)
		{
			aConstituencyList = crossVotingEstimationService.getAllAssemblyConstituenciesForCrossVoting(constituencyIds,pConstituency,String.valueOf(year));
		}
		else
		{
			aConstituencyList = new ArrayList<SelectOptionVO>();
		}
		if(year != null && aConstituency != null && aConstituency > 0)
		{
			partysList = crossVotingEstimationService.getPartiesForConstituencyAndElectionYearForBoothData(aConstituency, String.valueOf(year));
		}
		else
		{
			partysList = new ArrayList<SelectOptionVO>();
		}
		
		pConstituencyList.add(0, new SelectOptionVO(0L,"Select Constituency"));
		aConstituencyList.add(0, new SelectOptionVO(0L,"Select Constituency"));
		partysList.add(0, new SelectOptionVO(0L,"Select Party"));
		return Action.SUCCESS;
	}

	public String getCrossVotingReportDetails(){
		try{
			jObj = new JSONObject(getTask());
			HttpSession session = request.getSession();
			
			List<SelectOptionVO> constituencyList = (List<SelectOptionVO>)session.getAttribute("assemblyConstis");
			List<Long> constituencyIds = new ArrayList<Long>();
			
			for(SelectOptionVO constituency : constituencyList){
				if(constituency.getId().longValue() != 0l)
				  constituencyIds.add(constituency.getId());
			}
			
			if("getParliament".equalsIgnoreCase(jObj.getString("task"))){
				parliamentList = crossVotingEstimationService.getAllParliamentConstituenciesForCrossVoting(constituencyIds, jObj.getString("electionValue"));
			}else if("Assembly".equalsIgnoreCase(jObj.getString("task"))){
				parliamentList = crossVotingEstimationService.getAllAssemblyConstituenciesForCrossVoting(constituencyIds,jObj.getLong("parliamentValue"),jObj.getString("electionYear"));
			}
		}catch(Exception e){
			LOG.error("Exception rised in getCrossVotingReportDetails",e);
		}
		return Action.SUCCESS;
	}
}
