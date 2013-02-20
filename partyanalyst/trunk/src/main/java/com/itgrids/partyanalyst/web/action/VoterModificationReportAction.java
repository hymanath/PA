package com.itgrids.partyanalyst.web.action;

import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.interceptor.ServletRequestAware;

import org.apache.log4j.Logger;
import org.json.JSONObject;

public class VoterModificationReportAction extends ActionSupport implements ServletRequestAware{
	
	
	private static final long serialVersionUID = -8550656684382574240L;
	private HttpServletRequest request;
	private HttpSession session;
	private String task;
	JSONObject jObj;
	private static final Logger LOG = Logger.getLogger(VoterModificationReportAction.class);
	
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

	public String execute()throws Exception
	{
		HttpSession session = request.getSession();
		RegistrationVO user = (RegistrationVO)session.getAttribute("USER");
		if(user == null)
			return ERROR;
		
		return Action.SUCCESS;
	}

	
	
}
