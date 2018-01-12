package com.itgrids.partyanalyst.web.action;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.ServletContextAware;
import org.json.JSONObject;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class AlertUpdationAPIAction extends ActionSupport implements ServletRequestAware, ServletContextAware{
	private HttpSession session;
	private HttpServletRequest request;
	private String task = null;
	JSONObject jObj = null;	
	//private IAlertUpdationAPIService alertUpdationAPIService;
	
	
	public void setServletContext(ServletContext arg0) {
		// TODO Auto-generated method stub
	}
	
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
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
	
	public String execute()	{
		session = request.getSession();
		return Action.SUCCESS;
	}

	/*public IAlertUpdationAPIService getAlertUpdationAPIService() {
		return alertUpdationAPIService;
	}

	public void setAlertUpdationAPIService(IAlertUpdationAPIService alertUpdationAPIService) {
		this.alertUpdationAPIService = alertUpdationAPIService;
	}*/
	
	

}
