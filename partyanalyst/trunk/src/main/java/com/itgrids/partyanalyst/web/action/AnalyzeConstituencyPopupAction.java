package com.itgrids.partyanalyst.web.action;

import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

import com.itgrids.partyanalyst.dto.CandidateVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.service.ICommentsDataService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class AnalyzeConstituencyPopupAction extends ActionSupport implements ServletRequestAware
{
	private HttpServletRequest request;
	
	JSONObject jObj = null;
	private String task = null;
	private String constituencyId;
	private String redirectLoc;
	private List<SelectOptionVO> electionYears;
	private ICommentsDataService commentsDataService;
	private List<CandidateVO> candidateVO;
	private String parliamentConstiId;
	private String constituencyName;
	
	public String getConstituencyName() {
		return constituencyName;
	}

	public void setConstituencyName(String constituencyName) {
		this.constituencyName = constituencyName;
	}

	public String getParliamentConstiId() {
		return parliamentConstiId;
	}

	public void setParliamentConstiId(String parliamentConstiId) {
		this.parliamentConstiId = parliamentConstiId;
	}

	public List<CandidateVO> getCandidateVO() {
		return candidateVO;
	}

	public void setCandidateVO(List<CandidateVO> candidateVO) {
		this.candidateVO = candidateVO;
	}
	
	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	public ICommentsDataService getCommentsDataService() {
		return commentsDataService;
	}

	public void setCommentsDataService(ICommentsDataService commentsDataService) {
		this.commentsDataService = commentsDataService;
	}

	public java.util.List<SelectOptionVO> getElectionYears() {
		return electionYears;
	}

	public void setElectionYears(java.util.List<SelectOptionVO> electionYears) {
		this.electionYears = electionYears;
	}

	public String getRedirectLoc() {
		return redirectLoc;
	}

	public void setRedirectLoc(String redirectLoc) {
		this.redirectLoc = redirectLoc;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;		
	}
	
	public String getConstituencyId() {
		return constituencyId;
	}

	public void setConstituencyId(String constituencyId) {
		this.constituencyId = constituencyId;
	}

	public String execute()
	{	
		electionYears = commentsDataService.getElectionYearsForConstituency(Long.parseLong(constituencyId));
		return Action.SUCCESS;
	}
	
	public String getCandidateResults()
	{
		try {
			jObj = new JSONObject(getTask());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		Long constiElectionId = Long.parseLong(jObj.getString("constElecId"));
		
		candidateVO = commentsDataService.getCandidateResultsForConstiElectionId(constiElectionId);
		
		return Action.SUCCESS;
	}
	
	public String getElectionYearsForConstituency()
	{
		try {
			jObj = new JSONObject(getTask());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		Long constituencyId = Long.parseLong(jObj.getString("constituencyId"));
		electionYears = commentsDataService.getElectionYearsForConstituency(constituencyId);
		
		return Action.SUCCESS;
	}
}
