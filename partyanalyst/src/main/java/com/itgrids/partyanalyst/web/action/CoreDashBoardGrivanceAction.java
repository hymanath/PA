package com.itgrids.partyanalyst.web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.service.impl.CoreDashboardCoreService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class CoreDashBoardGrivanceAction extends ActionSupport implements ServletRequestAware {

	private HttpServletRequest request;
	private HttpSession session;
	
	private JSONObject 	jObj;
	private String 	task;
	
	private CoreDashboardCoreService coreDashboardCoreService;
	
	
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
		
	}
	public String execute(){
		try {
			
			final HttpSession session = request.getSession();
			
			final RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
			if(user == null || user.getRegistrationID() == null){
				return ERROR;
			}
		}catch(Exception e) {
			LOG.error("Exception raised at execute() in CoreDashBoard Action class", e);
		}
		return Action.SUCCESS;
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
	
}
