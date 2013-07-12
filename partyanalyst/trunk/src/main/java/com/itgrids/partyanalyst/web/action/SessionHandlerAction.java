package com.itgrids.partyanalyst.web.action;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.util.ServletContextAware;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.UserTrackingVO;
import com.itgrids.partyanalyst.service.ILoginService;
import com.itgrids.partyanalyst.service.impl.LoginService;
import com.itgrids.partyanalyst.util.IWebConstants;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.ActionSupport;

public class SessionHandlerAction extends ActionSupport implements HttpSessionListener,ServletContextAware,ServletRequestAware,ServletResponseAware{
	
	private static final long serialVersionUID = -666303609399379455L;
	private HttpServletResponse response;
	private HttpServletRequest request;
	private ServletContext context;
	private static final Logger LOG = Logger.getLogger(SessionHandlerAction.class);
	private ILoginService loginService;
	
	public ILoginService getLoginService() {
		return loginService;
	}

	public void setLoginService(ILoginService loginService) {
		this.loginService = loginService;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
		
	}

	public void setServletContext(ServletContext context) {
		this.context = context;
		
	}

	  public void sessionCreated(HttpSessionEvent createEvent)
	  {
		  
	  }
	 
	  public void sessionDestroyed(HttpSessionEvent destroyedEvent) 
	  {
		  try{ 
			  RegistrationVO regvo = (RegistrationVO) destroyedEvent.getSession().getAttribute(IConstants.USER);
			  
			  if(regvo != null && regvo.getRegistrationID() != null)
			  {
				  UserTrackingVO userTrackingVO = new UserTrackingVO();
				  userTrackingVO.setSessionId(destroyedEvent.getSession().getId());
				  userTrackingVO.setStatus(IWebConstants.LOGOUT);
				  
				  if(loginService == null)
				  {
					  ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(destroyedEvent.getSession().getServletContext());
					  loginService = (LoginService)ctx.getBean("loginService");
				  }
				  loginService.saveUserSessionDetails(userTrackingVO);
			  }
			  
		  }catch (Exception e) 
		  {
			  LOG.error(e); 
		  }
		
	  }
		
}
