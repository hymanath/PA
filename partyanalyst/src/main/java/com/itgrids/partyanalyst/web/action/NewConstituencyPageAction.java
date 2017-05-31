package com.itgrids.partyanalyst.web.action;


import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.ServletContextAware;
import org.json.JSONObject;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class NewConstituencyPageAction extends ActionSupport implements ServletRequestAware, ServletContextAware{
	private static final Logger LOG = Logger.getLogger(NewConstituencyPageAction.class);
	
	private HttpServletRequest request;
	private HttpServletResponse response;
	private HttpSession session;
	private String task;
	private JSONObject jObj;
	

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
	public JSONObject getjObj() {
		return jObj;
	}
	public void setjObj(JSONObject jObj) {
		this.jObj = jObj;
	}
	public void setServletContext(ServletContext arg0) {
	}
	public void setServletRequest(HttpServletRequest arg0) {
	}
	
	public String newConstituencyPage(){
		return Action.SUCCESS;
	}
	
}
