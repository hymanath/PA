package com.itgrids.partyanalyst.web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.SurveyDashBoardVO;
import com.itgrids.partyanalyst.service.impl.SurveyDashBoardService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class SurveyDashBoardAction  extends ActionSupport implements ServletRequestAware{
	
	public static final Logger LOG = Logger.getLogger(SurveyDashBoardAction.class);
	HttpServletRequest request;
	
	private String task;
	private JSONObject jObj;
	
	private SurveyDashBoardService surveyDashBoardService;

	
	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public JSONObject getjObj() {
		return jObj;
	}

	public void setjObj(JSONObject jObj) {
		this.jObj = jObj;
	}

	

	public SurveyDashBoardService getSurveyDashBoardService() {
		return surveyDashBoardService;
	}

	public void setSurveyDashBoardService(
			SurveyDashBoardService surveyDashBoardService) {
		this.surveyDashBoardService = surveyDashBoardService;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	private SurveyDashBoardVO resultVO;
	private String status;
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public SurveyDashBoardVO getResultVO() {
		return resultVO;
	}

	public void setResultVO(SurveyDashBoardVO resultVO) {
		this.resultVO = resultVO;
	}

	public String execute()
	{
		HttpSession session = request.getSession();
		RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
		if(user == null)
		{
			return Action.INPUT;
		}
		resultVO = surveyDashBoardService.getCompletdConstituenciesDetails();
		return Action.SUCCESS;
	}
	
	public String saveSurveyCompletionDetails()
	{
		try
		{
		jObj = new JSONObject(getTask());
		
		Long scopeId = jObj.getLong("scopeId");
		Long locationValue = jObj.getLong("locationValue");
		
		status = surveyDashBoardService.saveSurveyCompletionDetails(scopeId,locationValue);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return Action.SUCCESS;
		
	}

}
