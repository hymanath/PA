package com.itgrids.partyanalyst.web.action;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.AssignKeyCandidateVO;
import com.itgrids.partyanalyst.service.IElectionLiveResultsAnalysisService;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.itgrids.partyanalyst.utils.IConstants;

import java.text.ParseException;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class AssignKeyCandidateAction extends ActionSupport implements ServletRequestAware ,ServletResponseAware{

	private HttpServletRequest request;
	private HttpServletResponse response;
	private JSONObject jObj;
	private String task;
	private List<SelectOptionVO> optionVOList;
	private ResultStatus resultStatus;
	private IStaticDataService staticDataService; 
	private List<SelectOptionVO> parties;
	private IElectionLiveResultsAnalysisService electionLiveResultsAnalysisService;
	private AssignKeyCandidateVO assignKeyCandidateVO; 
	
	public AssignKeyCandidateVO getAssignKeyCandidateVO() {
		return assignKeyCandidateVO;
	}
	public void setAssignKeyCandidateVO(AssignKeyCandidateVO assignKeyCandidateVO) {
		this.assignKeyCandidateVO = assignKeyCandidateVO;
	}
	public IElectionLiveResultsAnalysisService getElectionLiveResultsAnalysisService() {
		return electionLiveResultsAnalysisService;
	}
	public void setElectionLiveResultsAnalysisService(
			IElectionLiveResultsAnalysisService electionLiveResultsAnalysisService) {
		this.electionLiveResultsAnalysisService = electionLiveResultsAnalysisService;
	}
	public IStaticDataService getStaticDataService() {
		return staticDataService;
	}
	public void setStaticDataService(IStaticDataService staticDataService) {
		this.staticDataService = staticDataService;
	}
	public HttpServletRequest getRequest() {
		return request;
	}
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	public HttpServletResponse getResponse() {
		return response;
	}
	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}
	public void setJObj(JSONObject jObj) {
		this.jObj = jObj;
	}

	public JSONObject getJObj() {
		return jObj;
	}
	public String getTask() {
		return task;
	}
	public void setTask(String task) {
		this.task = task;
	}
	public List<SelectOptionVO> getOptionVOList() {
		return optionVOList;
	}
	public void setOptionVOList(List<SelectOptionVO> optionVOList) {
		this.optionVOList = optionVOList;
	}
	public ResultStatus getResultStatus() {
		return resultStatus;
	}
	public void setResultStatus(ResultStatus resultStatus) {
		this.resultStatus = resultStatus;
	}
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
		
	}
	public List<SelectOptionVO> getParties() {
		return parties;
	}
	public void setParties(List<SelectOptionVO> parties) {
		this.parties = parties;
	}
	
	public String ajaxCallHandlerForAssignKeyCandidate(){
		try{
			jObj = new JSONObject(getTask());
			if(jObj.getString("task").equalsIgnoreCase("getStates")){
				Long electionTypeId = new Long(jObj.getString("electionTypeId"));
				optionVOList = staticDataService.getStatesAndCountryBasedOnElectionType(electionTypeId);
			}
			else if(jObj.getString("task").equalsIgnoreCase("getParties")){
				Long stateId = new Long(jObj.getString("stateId"));
				String electionType = jObj.getString("electionType");
				if(electionType.equals(IConstants.PARLIAMENT_ELECTION_TYPE)){
					optionVOList = staticDataService.getAllNationalParties();
					Collections.sort(optionVOList);
				}
				else{
					optionVOList = staticDataService.getStaticPartiesListForAState(stateId);
				Collections.sort(optionVOList);
				}
			}
			else if(jObj.getString("task").equalsIgnoreCase("getCandidates")){
				String candidatename = jObj.getString("candidateName");
				Long stateId = new Long(jObj.getString("stateId"));
				Long partyId = new Long(jObj.getString("partyId"));
				optionVOList = electionLiveResultsAnalysisService.getCandidatesBasedOnSelection(candidatename,stateId,partyId);
				}
			else if(jObj.getString("task").equalsIgnoreCase("assignKeyCandidate")){
				assignKeyCandidateVO = new AssignKeyCandidateVO();
				Long candidateId = new Long(jObj.getString("candidateId"));
				//String candidateName = jObj.getString("candidateName");
				assignKeyCandidateVO.setKeyCandidateId(candidateId);
				assignKeyCandidateVO.setDescription(jObj.getString("description"));
				resultStatus = electionLiveResultsAnalysisService.saveKeyCandidates(assignKeyCandidateVO); 
				
			}
		}	
		catch (ParseException e) {
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}
	
	
}
