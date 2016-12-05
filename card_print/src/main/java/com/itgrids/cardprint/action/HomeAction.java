package com.itgrids.cardprint.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class HomeAction  extends ActionSupport implements ServletRequestAware{

	
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(HomeAction.class);
	private HttpServletRequest request;
	private String task;
	private JSONObject jobj;
	private HttpSession session;
	
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
		
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
	public HttpSession getSession() {
		return session;
	}
	public void setSession(HttpSession session) {
		this.session = session;
	}
	
	public String execute()
	{
		try{
			System.out.println("in execute");
		}catch(Exception e){
			LOG.error("Exception rised in execute",e);
		}
		return Action.SUCCESS;
	}
}
