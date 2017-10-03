package com.itgrids.partyanalyst.web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.core.api.service.ILoactionDetailsDashBoardService;
import com.opensymphony.xwork2.ActionSupport;

public class LoactionDetailsDashBoardAction extends ActionSupport implements ServletRequestAware {
	private final static Logger LOG = Logger.getLogger(LoactionDetailsDashBoardAction.class);
	private HttpServletRequest request;
	private HttpSession session;
	private JSONObject jObj;
	private String task;
	
	private ILoactionDetailsDashBoardService loactionDetailsDashBoardService;
	
	public void setServletRequest(HttpServletRequest request) {
		   this.request = request;    
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

	public void setLoactionDetailsDashBoardService(
			ILoactionDetailsDashBoardService loactionDetailsDashBoardService) {
		this.loactionDetailsDashBoardService = loactionDetailsDashBoardService;
	}
	
	
	
}
