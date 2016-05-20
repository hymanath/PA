package com.itgrids.partyanalyst.web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.service.IActivityService;
import com.itgrids.partyanalyst.service.IBloodBankService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class BloodBankAction extends ActionSupport implements ServletRequestAware{

	private static final Logger         		LOG = Logger.getLogger(BloodBankAction.class);

	private HttpServletRequest         			request;
	private HttpSession 						session;
	private IBloodBankService                   bloodBankService;
	private JSONObject							jObj;
	private String 								task;
	
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
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	public IBloodBankService getBloodBankService() {
		return bloodBankService;
	}
	public void setBloodBankService(IBloodBankService bloodBankService) {
		this.bloodBankService = bloodBankService;
	}
	
	public String bloodBankRegistration(){
		return Action.SUCCESS;
	}
	
	public String bloodBankDashBoard(){
		return Action.SUCCESS;
	}
	
	public String bloodBankBleading(){
		return Action.SUCCESS;
	}
}
