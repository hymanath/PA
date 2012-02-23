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

	public String execute(){
		
		HttpSession session = request.getSession();
		
		if(session.getAttribute(IConstants.USER) == null && 
				!entitlementsHelper.checkForEntitlementToViewReport(null, IConstants.LIVE_RESULTS_ANALYSIS_ENTITLEMENT))
			return IConstants.NOT_LOGGED_IN;
		if(!entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)session.getAttribute(IConstants.USER), IConstants.LIVE_RESULTS_ANALYSIS_ENTITLEMENT))
			return ERROR;
		
		 return SUCCESS;
	 }
	 
	public String ajaxCallHandlerElectionLiveResults(){
		
		try {
			jObj = new JSONObject(getTask());
			if(jObj.getString("task").equalsIgnoreCase("getConstituenciesCount")){
				Long electionId = new Long (jObj.getString("electionId"));
			//	electionLiveResultVO = electionLiveResultsAnalysisService.getCountOfConstituenciesForAElection(electionId);
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
				
				Long electionId = new Long(jObj.getString("electionId"));
				electionLiveResultVOList = electionLiveResultsAnalysisService.getLeadingOrWinningContituenciesForAParty(electionId);
			}
			else if(jObj.getString("task").equalsIgnoreCase("getPartiesGainAndLossInfo"))
			{
				Long electionId = new Long(jObj.getString("electionId"));
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
				Long electionId = new Long(jObj.getString("electionId"));
				candidatesList = electionLiveResultsAnalysisService.getConstituencyWiseCandidatesStates(electionId);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return Action.SUCCESS;
	}

}
