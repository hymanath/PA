package com.itgrids.partyanalyst.web.action;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.opensymphony.xwork2.ActionSupport;

public class PredictedCasteAssignAction extends ActionSupport implements ServletRequestAware{
	private HttpServletRequest request;

	private HttpSession session;
	
	private String task;
	JSONObject jObj;
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
	public JSONObject getjObj() {
		return jObj;
	}
	public void setjObj(JSONObject jObj) {
		this.jObj = jObj;
	}
	public void setServletRequest(HttpServletRequest arg0) {
		
		this.request=arg0;
	}
	
	public String execute()
	{
		
		
		return ActionSupport.SUCCESS;
		
	}
	
	
	

}
