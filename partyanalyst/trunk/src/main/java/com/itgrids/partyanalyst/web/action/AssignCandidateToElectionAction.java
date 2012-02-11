package com.itgrids.partyanalyst.web.action;

import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.service.ICandidateDetailsService;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.itgrids.partyanalyst.service.impl.StaticDataService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class AssignCandidateToElectionAction extends ActionSupport implements ServletRequestAware ,ServletResponseAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6925944204324568325L;
	
	private HttpServletRequest request;
	private HttpServletResponse response;
	private JSONObject jObj;
	private String task;
	private IStaticDataService staticDataService;
	private List<SelectOptionVO> optionVOList;
	private List<SelectOptionVO> statesList;
	private ICandidateDetailsService candidateDetailsService;
	
	public ICandidateDetailsService getCandidateDetailsService() {
		return candidateDetailsService;
	}

	public void setCandidateDetailsService(
			ICandidateDetailsService candidateDetailsService) {
		this.candidateDetailsService = candidateDetailsService;
	}

	public void setStatesList(List<SelectOptionVO> statesList) {
		this.statesList = statesList;
	}

	public List<SelectOptionVO> getStatesList() {
		return statesList;
	}

	public IStaticDataService getStaticDataService() {
		return staticDataService;
	}

	public void setStaticDataService(IStaticDataService staticDataService) {
		this.staticDataService = staticDataService;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
		
	}
	
	public void setTask(String task) {
		this.task = task;
	}

	public String getTask() {
		return task;
	}

	public void setJObj(JSONObject jObj) {
		this.jObj = jObj;
	}

	public JSONObject getJObj() {
		return jObj;
	}

	public void setOptionVOList(List<SelectOptionVO> optionVOList) {
		this.optionVOList = optionVOList;
	}

	public List<SelectOptionVO> getOptionVOList() {
		return optionVOList;
	}

	public String execute(){
		
		statesList = staticDataService.getParticipatedStatesForAnElectionType(2l);
		
		return SUCCESS;
		
	}
	
	public String ajaxCallHandlerForAssignCandidateToElection(){
		
		try {
			jObj = new JSONObject(getTask());
			
			
			if(jObj.getString("task").equalsIgnoreCase("getStates")){
				Long electionTypeId = new Long(jObj.getString("electionTypeId"));
				optionVOList = staticDataService.getStatesAndCountryBasedOnElectionType(electionTypeId);
			}
			else if(jObj.getString("task").equalsIgnoreCase("getElectionYears")){
				Long electionTypeId = new Long(jObj.getString("electionTypeId"));
				if(electionTypeId == 1l){
					optionVOList = staticDataService.getElectionYearsBasedOnElectionType(electionTypeId);
				}
				else
					optionVOList = staticDataService.getElectionYearsBasedOnStateIdAndElecTypeId(new Long(jObj.getString("stateId")),electionTypeId);
			}
			else if(jObj.getString("task").equalsIgnoreCase("getCandidatesBasedOnElectionId")){
				
				Long electionId = new Long(jObj.getString("electionId"));
				optionVOList = candidateDetailsService.getCandidatesBasedOnElectionId(electionId);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}
}
