package com.itgrids.partyanalyst.web.action;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.service.IOpinionPollService;
import com.opensymphony.xwork2.ActionSupport;

public class GetAllPollsAction extends ActionSupport implements
		ServletRequestAware {

	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(GetAllPollsAction.class);
	
	private HttpServletRequest request;
	private HttpSession session;
	private String task = null;
	JSONObject jObj = null;
	private ServletContext context;
	
	
	private IOpinionPollService opinionPollService;
	
	private List<SelectOptionVO> allPolls;
	
		
	public List<SelectOptionVO> getAllPolls() {
		return allPolls;
	}
	public void setAllPolls(List<SelectOptionVO> allPolls) {
		this.allPolls = allPolls;
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
	public String getTask() {
		return task;
	}
	public void setTask(String task) {
		this.task = task;
	}
	public JSONObject getJObj() {
		return jObj;
	}
	public void setJObj(JSONObject obj) {
		jObj = obj;
	}
	public ServletContext getContext() {
		return context;
	}
	public void setContext(ServletContext context) {
		this.context = context;
	}
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;		
	}
		
	public IOpinionPollService getOpinionPollService() {
		return opinionPollService;
	}
	public void setOpinionPollService(IOpinionPollService opinionPollService) {
		this.opinionPollService = opinionPollService;
	}
	public String execute () throws Exception 
	{
		allPolls = opinionPollService.getAllPolls();

		return SUCCESS;
	}	

}
