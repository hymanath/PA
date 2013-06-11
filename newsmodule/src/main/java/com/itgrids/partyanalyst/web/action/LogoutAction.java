package com.itgrids.partyanalyst.web.action;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.util.ServletContextAware;

import com.itgrids.partyanalyst.dto.UserTrackingVO;
import com.itgrids.partyanalyst.service.ILoginService;
import com.itgrids.partyanalyst.util.IWebConstants;
import com.opensymphony.xwork2.ActionSupport;

public class LogoutAction extends ActionSupport implements ServletContextAware, ServletRequestAware,ServletResponseAware{

	private static final long serialVersionUID = 7141316754962897968L;
	private HttpServletRequest request;
	private HttpSession session;
	private ServletContext context;
	private HttpServletResponse response;
	private ILoginService loginService;

	public ILoginService getLoginService() {
		return loginService;
	}

	public void setLoginService(ILoginService loginService) {
		this.loginService = loginService;
	}

	public void setServletContext(ServletContext context) {
		this.context = context;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}
	
	public String execute()
	{
		session = request.getSession();
		
		session.removeAttribute("USER");
		
		session.invalidate();
		
		try{
			//response.sendRedirect("homePage.action");
			response.sendRedirect("index.jsp");
		}catch (Exception e) {
		}
		return SUCCESS;
	}

	

}
