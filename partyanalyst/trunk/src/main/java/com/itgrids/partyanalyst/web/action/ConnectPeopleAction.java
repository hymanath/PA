package com.itgrids.partyanalyst.web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class ConnectPeopleAction extends ActionSupport implements ServletRequestAware{
	
	private static final Logger log = Logger.getLogger(ProblemPostControlAction.class);
	
	private HttpServletRequest request;
	private HttpServletResponse response;
	private HttpSession session;
	private String redirectLoc;
	private String districtId;
	private String task;
	JSONObject jObj;
	
	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public String getDistrictId() {
		return districtId;
	}

	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}

	public String getRedirectLoc() {
		return redirectLoc;
	}

	public void setRedirectLoc(String redirectLoc) {
		this.redirectLoc = redirectLoc;
	}

	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}


	public void setServletRequest(HttpServletRequest request) {
			
		this.request = request;
	}
	
	
	public String execute() throws Exception
	{
		session = request.getSession();
		Object regVO = session.getAttribute(IConstants.USER);
		
		if(regVO == null){
			log.error(" No User Log In .....");			
			return IConstants.NOT_LOGGED_IN;
		}
		
		return Action.SUCCESS;
	}
	
	public String connectToUser() throws Exception
	{
		String param;
		param = getTask();
		
		try {
			jObj = new JSONObject(param);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Long districtId = new Long(jObj.getString("districtId"));
		String districtName = jObj.getString("districtName");
		Long connectUserId = new Long(jObj.getString("connectUserId"));
		String connectUserName = jObj.getString("connectUserName");
		Long userId = new Long(jObj.getString("userId"));
		
		
		return Action.SUCCESS;
	}

}
