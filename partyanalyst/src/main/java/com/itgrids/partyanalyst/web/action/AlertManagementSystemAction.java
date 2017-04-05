package com.itgrids.partyanalyst.web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.opensymphony.xwork2.Action;

public class AlertManagementSystemAction {

	private final static Logger LOG = Logger.getLogger(AlertManagementSystemAction.class);
	private HttpServletRequest request;
	private HttpSession session;
	private JSONObject jObj;
	private String task;	
	
	
	
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



	public String execute(){
		  
		return Action.SUCCESS;
		  
	  }
	public String getDepartmentDetails(){
		try{
			session = request.getSession();
		   	/*RegistrationVO regVo = (RegistrationVO)session.getAttribute("USER");
			Long userId = regVo.getRegistrationID();*/
			
			//officerName = cccDashboardService.getDesignationForUser(userId);
			//session.setAttribute("officerName", officerName);
			
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Exception occured in getDepartmentDetails() of CccDashboardAction",e);
		}
		return Action.SUCCESS;
	}
}
