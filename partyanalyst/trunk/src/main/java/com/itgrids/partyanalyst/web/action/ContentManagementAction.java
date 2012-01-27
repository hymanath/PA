package com.itgrids.partyanalyst.web.action;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.util.ServletContextAware;

import com.itgrids.partyanalyst.service.IContentManagementService;
import com.opensymphony.xwork2.ActionSupport;

public class ContentManagementAction extends ActionSupport implements
ServletRequestAware, ServletResponseAware, ServletContextAware{

	private static final long serialVersionUID = 2308975660429251934L;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private ServletContext context;
	private HttpSession session;
	
	private IContentManagementService contentManagementService;
	
	
	public IContentManagementService getContentManagementService() {
		return contentManagementService;
	}

	public void setContentManagementService(
			IContentManagementService contentManagementService) {
		this.contentManagementService = contentManagementService;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
		this.session = request.getSession();
	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}
	
	public void setServletContext(ServletContext context) {
		this.context = context;
	}
	
	public String execute()
	{
		return SUCCESS;
	}
}
