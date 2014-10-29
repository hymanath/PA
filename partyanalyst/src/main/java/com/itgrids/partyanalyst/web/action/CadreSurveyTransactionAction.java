package com.itgrids.partyanalyst.web.action;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.ServletContextAware;

import com.opensymphony.xwork2.ActionSupport;

public class CadreSurveyTransactionAction extends ActionSupport implements ServletRequestAware, ServletContextAware {



	private static final long serialVersionUID = -5934997595739284474L;
	private HttpServletRequest request;
	private HttpSession session;
	private ServletContext context;
	
	private final static Logger LOG = Logger.getLogger(CadreSurveyTransactionAction.class);
	
	public void setServletRequest(final HttpServletRequest request)
	{
		this.request = request;
	}
	
	
	public void setServletContext(ServletContext context) {
		this.context = context;
	}


}
