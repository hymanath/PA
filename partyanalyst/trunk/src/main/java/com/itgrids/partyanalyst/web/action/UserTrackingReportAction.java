package com.itgrids.partyanalyst.web.action;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.ServletContextAware;
import com.itgrids.partyanalyst.service.IUserTrackingReportService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class UserTrackingReportAction extends ActionSupport implements ServletRequestAware , ServletContextAware{
	
	private static final long serialVersionUID = 5437549265219239930L;
	private static final Logger log = Logger.getLogger(UserTrackingReportAction.class);
	private HttpServletRequest request;
	private ServletContext context;
	private HttpSession session;
	private IUserTrackingReportService userTrackingReportService;
	
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;	
	}
	
	public void setServletContext(ServletContext context) {
		this.context = context;
	}
	
	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}

	public IUserTrackingReportService getUserTrackingReportService() {
		return userTrackingReportService;
	}

	public void setUserTrackingReportService(
			IUserTrackingReportService userTrackingReportService) {
		this.userTrackingReportService = userTrackingReportService;
	}

	public String execute(){
		
		session = request.getSession();

		return Action.SUCCESS;
	}
}
