package com.itgrids.partyanalyst.web.action;

import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dao.IEntitlementDAO;
import com.itgrids.partyanalyst.dto.ConstituencyElectionResultVO;
import com.itgrids.partyanalyst.dto.ElectionLiveResultVO;
import com.itgrids.partyanalyst.dto.PartyElectionResultVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.helper.EntitlementsHelper;
import com.itgrids.partyanalyst.service.IElectionLiveResultsAnalysisService;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class ElectionLiveResultsAnalysisAction extends ActionSupport implements ServletRequestAware,ServletResponseAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3569298270801964639L;
	
	private HttpServletRequest request;
	private HttpServletResponse response;
	private IElectionLiveResultsAnalysisService electionLiveResultsAnalysisService;
	private IStaticDataService staticDataService;
	private List<SelectOptionVO> statesList;
	private ElectionLiveResultVO electionLiveResultVO;
	private List<ElectionLiveResultVO> electionLiveResultVOList;
	private EntitlementsHelper entitlementsHelper;
	private JSONObject jObj;
	private String task;
	private List<ConstituencyElectionResultVO> candidatesList;
	private List<PartyElectionResultVO> partyElectionResultVOList;
	private List<PartyElectionResultVO> districtWiseResults;
	private Long electionId;
	private Long electionType;
	private Long stateId;
	private String host = IConstants.DEPLOYED_HOST;

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public List<PartyElectionResultVO> getPartyElectionResultVOList() {
		return partyElectionResultVOList;
	}

	public void setPartyElectionResultVOList(
			List<PartyElectionResultVO> partyElectionResultVOList) {
		this.partyElectionResultVOList = partyElectionResultVOList;
	}

	public JSONObject getJObj() {
		return jObj;
	}

	public void setJObj(JSONObject obj) {
		jObj = obj;
	}

	public IStaticDataService getStaticDataService() {
		return staticDataService;
	}

	public List<SelectOptionVO> getStatesList() {
		return statesList;
	}

	public void setStaticDataService(IStaticDataService staticDataService) {
		this.staticDataService = staticDataService;
	}

	public void setStatesList(List<SelectOptionVO> statesList) {
		this.statesList = statesList;
	}

	public IElectionLiveResultsAnalysisService getElectionLiveResultsAnalysisService() {
		return electionLiveResultsAnalysisService;
	}

	public void setElectionLiveResultsAnalysisService(
			IElectionLiveResultsAnalysisService electionLiveResultsAnalysisService) {
		this.electionLiveResultsAnalysisService = electionLiveResultsAnalysisService;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}
	 public void setElectionLiveResultVO(ElectionLiveResultVO electionLiveResultVO) {
		this.electionLiveResultVO = electionLiveResultVO;
	}

	public ElectionLiveResultVO getElectionLiveResultVO() {
		return electionLiveResultVO;
	}

	public void setElectionLiveResultVOList(List<ElectionLiveResultVO> electionLiveResultVOList) {
		this.electionLiveResultVOList = electionLiveResultVOList;
	}

	public List<ElectionLiveResultVO> getElectionLiveResultVOList() {
		return electionLiveResultVOList;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public String getTask() {
		return task;
	}

	public void setEntitlementsHelper(EntitlementsHelper entitlementsHelper) {
		this.entitlementsHelper = entitlementsHelper;
	}

	public EntitlementsHelper getEntitlementsHelper() {
		return entitlementsHelper;
	}

	public void setCandidatesList(List<ConstituencyElectionResultVO> candidatesList) {
		this.candidatesList = candidatesList;
	}

	public List<ConstituencyElectionResultVO> getCandidatesList() {
		return candidatesList;
	}

	public List<PartyElectionResultVO> getDistrictWiseResults() {
		return districtWiseResults;
	}

	public void setDistrictWiseResults(
			List<PartyElectionResultVO> districtWiseResults) {
		this.districtWiseResults = districtWiseResults;
	}

	public Long getElectionId() {
		return electionId;
	}

	public void setElectionId(Long electionId) {
		this.electionId = electionId;
	}

	public Long getElectionType() {
		return electionType;
	}

	public void setElectionType(Long electionType) {
		this.electionType = electionType;
	}

	public Long getStateId() {
		return stateId;
	}

	public void setStateId(Long stateId) {
		this.stateId = stateId;
	}

	public String execute(){
		
		HttpSession session = request.getSession();
		
		/*if(session.getAttribute(IConstants.USER) == null)
			return IConstants.NOT_LOGGED_IN;*/		
		 return SUCCESS;
	 }
	 
	public String ajaxCallHandlerElectionLiveResults(){
		
		try {
			jObj = new JSONObject(getTask());
			if(jObj.getString("task").equalsIgnoreCase("getConstituenciesCount"))
			{
				Long electionId = Long.valueOf (jObj.getString("electionId"));
			//	electionLiveResultVO = electionLiveResultsAnalysisService.getCountOfConstituenciesForAElection(electionId);
			}
			else if(jObj.getString("task").equalsIgnoreCase("getOverViewCount"))
			{
				Long electionId = Long.valueOf (jObj.getString("electionId"));
				electionLiveResultVO = electionLiveResultsAnalysisService.getOverViewCount(electionId);
			}
			else if(jObj.getString("task").equalsIgnoreCase("getGenderAnalysisInElection"))
			{
				partyElectionResultVOList = electionLiveResultsAnalysisService.getGenderAnalysisInElection(jObj.getLong("electionId"));
			}
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		
		return Action.SUCCESS;
	}
	
	public String getPartyWinningOrLeadingConstituenciesCount(){
		
		try {
			jObj = new JSONObject(getTask());
			if(jObj.getString("task").equalsIgnoreCase("getPartyWonOrLeadConstituenciesCount")){
				
				Long electionId = Long.valueOf(jObj.getString("electionId"));
				electionLiveResultVOList = electionLiveResultsAnalysisService.getLeadingOrWinningContituenciesForAParty(electionId);
			}
			else if(jObj.getString("task").equalsIgnoreCase("getPartiesGainAndLossInfo"))
			{
				Long electionId = Long.valueOf(jObj.getString("electionId"));
				electionLiveResultVOList = electionLiveResultsAnalysisService.getPartiesGainAndLossInfo(electionId);
			}
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return Action.SUCCESS;
	}
	
	public String getConstituencyWiseCandidatesStatus(){
		
		
		try 
		{
			jObj = new JSONObject(getTask());
			if(jObj.getString("task").equalsIgnoreCase("getCandidatesStatus"))
			{
				Long electionId = Long.valueOf(jObj.getString("electionId"));
				candidatesList = electionLiveResultsAnalysisService.getConstituencyWiseCandidatesStates(electionId);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return Action.SUCCESS;
	}

 public String getLiveResults(){	
		try 
		{
			jObj = new JSONObject(getTask());
			
				Long electionId = Long.valueOf(jObj.getString("electionId"));
				electionLiveResultVO = electionLiveResultsAnalysisService.getLiveResultsDetails(electionId);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return Action.SUCCESS;
	}
 public String getDistrictWiseLiveResults(){	
		try 
		{
			jObj = new JSONObject(getTask());
			 if(jObj.getString("task").equalsIgnoreCase("getDistrictWiseLiveResults")){
				Long electionId = Long.valueOf(jObj.getString("electionId"));
				Long districtId = Long.valueOf(jObj.getString("districtId"));
			    districtWiseResults = electionLiveResultsAnalysisService.getCandidatesInfoDistrictWise(electionId,districtId);
			 }else if(jObj.getString("task").equalsIgnoreCase("getWonLeadResults")){
				 Long electionId = Long.valueOf(jObj.getString("electionId"));
				 districtWiseResults = electionLiveResultsAnalysisService.getWonLeadCandidatesInfo(electionId);
				 
			 }
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return Action.SUCCESS;
	}
	
}
