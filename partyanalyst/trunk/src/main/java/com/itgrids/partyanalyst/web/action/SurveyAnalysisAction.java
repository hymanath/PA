package com.itgrids.partyanalyst.web.action;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.util.ServletContextAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.service.ISurveyAnalysisService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class SurveyAnalysisAction extends ActionSupport implements ServletRequestAware, ServletResponseAware,ServletContextAware{
	
	 private static final Logger LOG = Logger.getLogger(SurveyAnalysisAction.class);
     private ISurveyAnalysisService surveyAnalysisService;
     private ResultStatus resultStatus;
     private String task;
     private JSONObject jObj;
     private HttpSession session;
 	 private HttpServletRequest request;
 	 private HttpServletResponse response;; 
 	 private ServletContext context;
 	 private List<SelectOptionVO>  questionType;
 	
	 public ISurveyAnalysisService getSurveyAnalysisService() {
		return surveyAnalysisService;
	}
		
	public void setSurveyAnalysisService(
			ISurveyAnalysisService surveyAnalysisService) {
		this.surveyAnalysisService = surveyAnalysisService;
	}
	public ResultStatus getResultStatus() {
		return resultStatus;
	}

	public void setResultStatus(ResultStatus resultStatus) {
		this.resultStatus = resultStatus;
	}
	
	public void setServletContext(ServletContext context) {
		this.context = context;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}
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

	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
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
	
	public List<SelectOptionVO> getQuestionType() {
		return questionType;
	}

	public void setQuestionType(List<SelectOptionVO> questionType) {
		this.questionType = questionType;
	}

	public String execute(){
		 System.out.println("In");
		 return Action.SUCCESS;
	 }
	
	public String ajaxHandler()
	{
		return Action.SUCCESS;
	}

	
	 
	public String createNewQuestion(){
		try{
			LOG.debug("Entered into the getQuestionOptionTypes() method in SurveyAnalysisAction");
			questionType = surveyAnalysisService.getOptionTypes();
			questionType.add(0, new SelectOptionVO(0L,"Select"));
			}catch(Exception e){
				LOG.error("Entered into the getQuestionOptionTypes() method in SurveyAnalysisAction");	
			}
		return Action.SUCCESS;
	}
	
}
