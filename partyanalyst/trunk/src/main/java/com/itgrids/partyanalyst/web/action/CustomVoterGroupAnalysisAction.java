package com.itgrids.partyanalyst.web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.opensymphony.xwork2.ActionSupport;

public class CustomVoterGroupAnalysisAction extends ActionSupport implements ServletRequestAware{

	private HttpSession session;
	
	private HttpServletRequest request;
	
	private String task;
	
	JSONObject jobj;

	@Override
	public void setServletRequest(HttpServletRequest arg) {
		this.request = arg;
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

	public JSONObject getJobj() {
		return jobj;
	}

	public void setJobj(JSONObject jobj) {
		this.jobj = jobj;
	}
	
	public String execute()
	{
		return ActionSupport.SUCCESS;
	}

}
