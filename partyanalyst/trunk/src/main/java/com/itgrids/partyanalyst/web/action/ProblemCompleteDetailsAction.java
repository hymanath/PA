package com.itgrids.partyanalyst.web.action;

import java.text.ParseException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;

import org.apache.struts2.util.ServletContextAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.ProblemBeanVO;
import com.itgrids.partyanalyst.service.IProblemManagementService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class ProblemCompleteDetailsAction extends ActionSupport implements
ServletRequestAware, ServletContextAware  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private ServletContext context;
	private IProblemManagementService problemManagementService;
	private ProblemBeanVO problemBeanVO;
	JSONObject jObj = null;
	private String task;
	private static final Logger log = Logger.getLogger(ProblemCompleteDetailsAction.class);
	private Long problemHistoryId; 

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;		
	}	

	public void setServletContext(ServletContext context) {
		this.context = context;		
	}

	public IProblemManagementService getProblemManagementService() {
		return problemManagementService;
	}

	public void setProblemManagementService(
			IProblemManagementService problemManagementService) {
		this.problemManagementService = problemManagementService;
	}	
	
	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public ServletContext getContext() {
		return context;
	}

	public void setContext(ServletContext context) {
		this.context = context;
	}

	public ProblemBeanVO getProblemBeanVO() {
		return problemBeanVO;
	}

	public void setProblemBeanVO(ProblemBeanVO problemBeanVO) {
		this.problemBeanVO = problemBeanVO;
	}

	public JSONObject getJObj() {
		return jObj;
	}

	public void setJObj(JSONObject obj) {
		jObj = obj;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}	

	public Long getProblemHistoryId() {
		return problemHistoryId;
	}

	public void setProblemHistoryId(Long problemHistoryId) {
		this.problemHistoryId = problemHistoryId;
	}	

	public String execute(){
				
		return Action.SUCCESS;
	}
	
	public String getProblemCompleteDetails(){
		String param = null;
		param = getTask();
		
		try {
			jObj = new JSONObject(param);
			System.out.println(jObj);
		} catch (ParseException e) {
			e.printStackTrace();			
		}
		if(log.isDebugEnabled())
			log.debug("Task::"+jObj.getString("task"));
		Long problemHistoryId = jObj.getLong("problemHistoryId");
		log.info("problemHistoryId:"+problemHistoryId);
		
		problemBeanVO = problemManagementService.getProblemCompleteInfo(problemHistoryId);
		return Action.SUCCESS;
	}
	

}
