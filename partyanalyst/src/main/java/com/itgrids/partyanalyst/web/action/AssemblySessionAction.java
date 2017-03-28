package com.itgrids.partyanalyst.web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.ResultStatus;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class AssemblySessionAction  extends ActionSupport implements ServletRequestAware  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7220092157888269387L;
	private static final Logger LOG = Logger.getLogger(AssemblySessionAction.class);
	private HttpServletRequest request;
	private HttpSession session;
	private String task;
	public  transient JSONObject jObj;
	private ResultStatus resultStatus;
	
	
	public ResultStatus getResultStatus() {
		return resultStatus;
	}


	public void setResultStatus(final ResultStatus resultStatus) {
		this.resultStatus = resultStatus;
	}


	public HttpServletRequest getRequest() {
		return request;
	}


	public void setRequest(final HttpServletRequest request) {
		this.request = request;
	}


	public HttpSession getSession() {
		return session;
	}


	public void setSession(final HttpSession session) {
		this.session = session;
	}


	public String getTask() {
		return task;
	}


	public void setTask(final String task) {
		this.task = task;
	}
    //override
	public void setServletRequest(final HttpServletRequest request) {
		this.request = request;
		
	}
	public String execute()
	{
		/*session = request.getSession();
		final RegistrationVO registrationVO = (RegistrationVO) session.getAttribute(IConstants.USER);
		if (registrationVO != null) 
		{
			if (!registrationVO.getIsAdmin().equals("true")){
				  return ERROR;
			}
		} 
		else{
			return ERROR;
		}*/
		return Action.SUCCESS;
		
	}
	
}
