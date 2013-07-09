package com.itgrids.partyanalyst.web.action;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.util.ServletContextAware;

import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.UserTrackingVO;
import com.itgrids.partyanalyst.service.ILoginService;
import com.itgrids.partyanalyst.util.IWebConstants;
import com.itgrids.partyanalyst.utils.IConstants;

public class SessionHandlerAction implements HttpSessionListener,ServletContextAware,ServletRequestAware,ServletResponseAware{
	private ILoginService loginService;
	private HttpServletResponse response;
	private HttpServletRequest request;
	private ServletContext context;
	
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

	public ILoginService getLoginService() {
		return loginService;
	}

	public void setLoginService(ILoginService loginService) {
		this.loginService = loginService;
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
			  
			  if(regvo.getRegistrationID() != null)
			  {
				  UserTrackingVO userTrackingVO = new UserTrackingVO();
				  userTrackingVO.setSessionId(destroyedEvent.getSession().getId());
				  userTrackingVO.setStatus(IWebConstants.LOGOUT);
				  loginService.saveUserSessionDetails(userTrackingVO);
			  }
			  destroyedEvent.getSession().invalidate();
		  }catch (Exception e) 
		  {
			  
		  }
		
	  }

		public void redirect()
		{
			 try{
				 
			response.sendRedirect("homePage.action");
		}catch (Exception e) {
		}
}
}
