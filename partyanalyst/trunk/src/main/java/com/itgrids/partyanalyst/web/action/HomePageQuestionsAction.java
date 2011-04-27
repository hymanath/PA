package com.itgrids.partyanalyst.web.action;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.util.ServletContextAware;







import com.opensymphony.xwork2.ActionSupport;

public class HomePageQuestionsAction extends ActionSupport implements
		ServletRequestAware, ServletContextAware {
	
	private HttpServletRequest request;
	private ServletContext context;
	private HttpSession session;
	
	
	
	public void setServletRequest(HttpServletRequest req) {
		request = req;

	}

	public void setServletContext(ServletContext context) {
		this.context = context;

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

		public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}

	
	
	public String execute(){
		
		return SUCCESS;
	}
		


}
