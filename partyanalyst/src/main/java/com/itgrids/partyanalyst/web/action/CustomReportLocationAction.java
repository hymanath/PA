package com.itgrids.partyanalyst.web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.opensymphony.xwork2.ActionSupport;

public class CustomReportLocationAction extends ActionSupport implements ServletRequestAware {
	private static final Logger LOG = Logger.getLogger(CustomReportAction.class);
	private HttpServletRequest 					request;
	private HttpSession 						session;
	private String 								task;
	private JSONObject							jObj;
	
	public HttpServletRequest getRequest() {
		return request;
	}
	public HttpSession getSession() {
		return session;
	}
	public String getTask() {
		return task;
	}
	public JSONObject getjObj() {
		return jObj;
	}
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	public void setSession(HttpSession session) {
		this.session = session;
	}
	public void setTask(String task) {
		this.task = task;
	}
	public void setjObj(JSONObject jObj) {
		this.jObj = jObj;
	}
	public void setServletRequest(HttpServletRequest arg0) {			
	}
}
