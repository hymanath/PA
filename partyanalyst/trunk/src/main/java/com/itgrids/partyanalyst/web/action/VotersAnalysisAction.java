package com.itgrids.partyanalyst.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.service.ICrossVotingEstimationService;
import com.opensymphony.xwork2.ActionSupport;
import com.sun.org.apache.xpath.internal.Arg;

public class VotersAnalysisAction extends ActionSupport implements ServletRequestAware{
	
	private HttpServletRequest request;
	
	private HttpSession session;
	
	private String task;
	
	JSONObject jObj = null;
	
	private ICrossVotingEstimationService crossVotingEstimationService;
	
	private List<SelectOptionVO> constituencyList;
	
	public List<SelectOptionVO> getConstituencyList() {
		return constituencyList;
	}

	public void setConstituencyList(List<SelectOptionVO> constituencyList) {
		this.constituencyList = constituencyList;
	}

	public ICrossVotingEstimationService getCrossVotingEstimationService() {
		return crossVotingEstimationService;
	}

	public void setCrossVotingEstimationService(
		ICrossVotingEstimationService crossVotingEstimationService) {
		this.crossVotingEstimationService = crossVotingEstimationService;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}
	
	public void setServletRequest(HttpServletRequest arg0) {
		
		this.request=arg0;
	}


	
	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}
	
	public String execute() throws Exception
	{
		HttpSession session = request.getSession();
		RegistrationVO user=(RegistrationVO) session.getAttribute("USER");
		if(user == null)
			return ERROR;
		Long userID = user.getRegistrationID();
		
		Long electionYear = 2009l;
		Long electionTypeId = 2l;
		constituencyList = crossVotingEstimationService.getConstituenciesForElectionYearAndTypeWithUserAccess(userID,electionYear,electionTypeId);
		return SUCCESS;
		
	}
	
}
