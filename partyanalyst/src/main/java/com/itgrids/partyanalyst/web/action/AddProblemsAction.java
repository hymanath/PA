/**
 * 
 */
package com.itgrids.partyanalyst.web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @author Sai Krishna Basetti
 *
 */
public class AddProblemsAction extends ActionSupport implements
		ServletRequestAware, ServletResponseAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final Logger LOG = Logger.getLogger(AddProblemsAction.class);
	
	transient private HttpServletRequest request;
	transient private HttpServletResponse response;
	private HttpSession session;

	
    /* (non-Javadoc)
	 * @see org.apache.struts2.interceptor.ServletRequestAware#setServletRequest(javax.servlet.http.HttpServletRequest)
	 */
	public HttpServletRequest getRequest() {
		return request;
	}
	
	public void setServletRequest(final HttpServletRequest request) {
		this.request = request;
	}

	/* (non-Javadoc)
	 * @see org.apache.struts2.interceptor.ServletResponseAware#setServletResponse(javax.servlet.http.HttpServletResponse)
	 */
	public HttpServletResponse getResponse() {
		return response;
	}
	
	public void setServletResponse(final HttpServletResponse response) {
		this.response = response;
	}
	
	public HttpSession getSession() {
		return session;
	}

	public void setSession(final HttpSession session) {
		this.session = session;
	}
	
	public String execute(){
		
		
		return Action.SUCCESS;
	}

}
