package com.itgrids.partyanalyst.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.service.ICandidateDetailsService;
import com.itgrids.partyanalyst.service.ISurveyDetailsService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class AssigningSurveyToUserAction extends ActionSupport implements ServletRequestAware {

	private HttpServletRequest request;
	HttpSession session;
	private JSONObject jObj;
	private String task;
	private ResultStatus resultStatus;
	private ICandidateDetailsService candidateDetailsService;
	private ISurveyDetailsService surveyDetailsService;
	private List<SelectOptionVO> userList;
	private List<SelectOptionVO> surveyList;
	
	public void setServletRequest(HttpServletRequest arg) {
		this.request = arg;
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

	public JSONObject getjObj() {
		return jObj;
	}

	public void setjObj(JSONObject jObj) {
		this.jObj = jObj;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public ResultStatus getResultStatus() {
		return resultStatus;
	}

	public void setResultStatus(ResultStatus resultStatus) {
		this.resultStatus = resultStatus;
	}
		
	public ISurveyDetailsService getSurveyDetailsService() {
		return surveyDetailsService;
	}

	public void setSurveyDetailsService(ISurveyDetailsService surveyDetailsService) {
		this.surveyDetailsService = surveyDetailsService;
	}

	public List<SelectOptionVO> getSurveyList() {
		return surveyList;
	}

	public void setSurveyList(List<SelectOptionVO> surveyList) {
		this.surveyList = surveyList;
	}

	public ICandidateDetailsService getCandidateDetailsService() {
		return candidateDetailsService;
	}

	public void setCandidateDetailsService(
			ICandidateDetailsService candidateDetailsService) {
		this.candidateDetailsService = candidateDetailsService;
	}

	public List<SelectOptionVO> getUserList() {
		return userList;
	}

	public void setUserList(List<SelectOptionVO> userList) {
		this.userList = userList;
	}

	public String execute() {
		session = request.getSession();
		RegistrationVO registrationVO = (RegistrationVO) session.getAttribute(IConstants.USER);
		if (registrationVO != null) 
		{
			userList = candidateDetailsService.getAllRegisterUsersForAssigningParty();
			surveyList = surveyDetailsService.getAllSurveys();
		}
		return Action.SUCCESS;
	}
	
	public String ajaxHandler(){
		try 
		{
			jObj = new JSONObject(getTask());
			if(jObj.getString("task").equalsIgnoreCase("saveSurveyDetailsAction"))
			  resultStatus  = surveyDetailsService.saveSurveyDetails(jObj.getLong("userId"),jObj.getLong("surveyId"));
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return SUCCESS;
	}
}


