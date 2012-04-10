package com.itgrids.partyanalyst.web.action;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.ServletContextAware;

import com.itgrids.partyanalyst.dao.IUserLoginDetailsDAO;
import com.itgrids.partyanalyst.dao.hibernate.UserLoginDetailsDAO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.UserTrackingVO;
import com.itgrids.partyanalyst.service.ILoginService;
import com.itgrids.partyanalyst.service.impl.LoginService;
import com.itgrids.partyanalyst.util.IWebConstants;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.ActionSupport;

public class SessionListenerAction extends ActionSupport implements HttpSessionListener,ServletRequestAware,ServletContextAware{

	private static final long serialVersionUID = -7321543633688170320L;
	private static final Logger log = Logger.getLogger(SessionListenerAction.class);
	private HttpServletRequest request;
	private ServletContext context;
	private ILoginService loginService;
	private HttpSession session;
	
	public ILoginService getLoginService() {
		return loginService;
	}

	public void setLoginService(ILoginService loginService) {
		this.loginService = loginService;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setServletContext(ServletContext context) {
		this.context = context;
	}
	
	public void sessionCreated(HttpSessionEvent httpSessionEvent)
	{
		log.debug("New Visitor is Accessed Our Site");
	}

	public void sessionDestroyed(HttpSessionEvent httpSessionEvent)
	{
		//saveUserSessionDetails(httpSessionEvent,IWebConstants.LOGOUT);
		System.out.println("Session is Destroyed, with session id - "+httpSessionEvent.getSession().getId());
	}
	
	/*public String saveUserSessionDetails(HttpSessionEvent httpSessionEvent,String status)
	{
		session = httpSessionEvent.getSession();
		UserTrackingVO userTrackingVO = new UserTrackingVO();
		
		userTrackingVO.setStatus(status);
		userTrackingVO.setSessionId(session.getId());
		if(loginService == null)
		{
			loginService = new LoginService();
			IUserLoginDetailsDAO userLoginDetailsDAO = new UserLoginDetailsDAO();
			loginService.setUserLoginDetailsDAO(userLoginDetailsDAO);
		}
		loginService.saveUserSessionDetails(userTrackingVO);
		return SUCCESS;
	}*/
	
}
