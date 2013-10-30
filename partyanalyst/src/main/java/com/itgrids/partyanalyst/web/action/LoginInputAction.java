package com.itgrids.partyanalyst.web.action;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.ServletContextAware;

import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class LoginInputAction extends ActionSupport implements ServletRequestAware, ServletContextAware{

	private ServletContext context;
	private HttpServletRequest request;
	private HttpSession session;
	private String pwdChanged;
	
	

	public String getPwdChanged() {
		return pwdChanged;
	}

	public void setPwdChanged(String pwdChanged) {
		this.pwdChanged = pwdChanged;
	}

	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public ServletContext getContext() {
		return context;
	}

	public void setContext(ServletContext context) {
		this.context = context;
	}

	
	public void setServletContext(ServletContext context) {
		this.context = context;
	}

	
	public void setServletRequest(HttpServletRequest request) {
		
		this.request = request;
	}
	
	public String execute(){
		
		try{
		pwdChanged = request.getParameter("pwdChanged");
		session = request.getSession();
		RegistrationVO user = (RegistrationVO)session.getAttribute("USER");
		
		if(user == null)
		   return Action.SUCCESS;
		
		else if(user != null && (user.getUserType().equalsIgnoreCase(IConstants.FREE_USER)))
			return "dashBoard";
		}catch (Exception e) {

		e.printStackTrace();
		
		}
		
		return Action.SUCCESS;
		
		
	}

}
