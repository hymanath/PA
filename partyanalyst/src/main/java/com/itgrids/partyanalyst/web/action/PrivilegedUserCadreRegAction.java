package com.itgrids.partyanalyst.web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.service.ICoreDashboardCadreRegistrationService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class PrivilegedUserCadreRegAction extends ActionSupport implements ServletRequestAware {
	private final static Logger LOG = Logger.getLogger(PrivilegedUserCadreRegAction.class);
	private HttpServletRequest request;
	private HttpSession session;
	private JSONObject jObj;
	private String task;
	
	
	private ICoreDashboardCadreRegistrationService coreDashboardCadreRegistrationService;
	
	
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	public HttpSession getSession() {
		return session; 
	}
	public void setSession(HttpSession session) {
		this.session = session;
	}
	public JSONObject getjObj() {
		return jObj;
	}
	public void setjObj(JSONObject jObj) {
		this.jObj = jObj;
	}
	public String getTask() {
		return task;
	}
	public void setTask(String task) {
		this.task = task;  
	}
	public ICoreDashboardCadreRegistrationService getCoreDashboardCadreRegistrationService() {
		return coreDashboardCadreRegistrationService;
	}
	public void setCoreDashboardCadreRegistrationService(ICoreDashboardCadreRegistrationService coreDashboardCadreRegistrationService) {
		this.coreDashboardCadreRegistrationService = coreDashboardCadreRegistrationService;
	}
	
	//Business method
	public String execute(){
		return Action.SUCCESS;
	}

}
