package com.itgrids.partyanalyst.web.interceptor;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.UserTrackingVO;
import com.itgrids.partyanalyst.util.IWebConstants;
import com.itgrids.partyanalyst.service.IUserTrackingService;


public class UserTrackingInterceptor implements ServletRequestAware,Interceptor  {
	private IUserTrackingService userTrackingService;

	 
	private HttpServletRequest request;
	private static final long serialVersionUID = 1L;
	
 
	
	public void setServletRequest(HttpServletRequest request) {
		this.request=request;
	}

	public IUserTrackingService getUserTrackingService() {
		return userTrackingService;
	}

	public void setUserTrackingService(IUserTrackingService userTrackingService) {
		this.userTrackingService = userTrackingService;
	}

	public String intercept(ActionInvocation invocation) throws Exception {
		
		if(invocation.getProxy().getMethod().equalsIgnoreCase("execute"))
		{	
		UserTrackingVO userTrackingVO;
		
			userTrackingVO = new UserTrackingVO();
			HttpServletRequest servletRequest = ServletActionContext.getRequest();
			HttpSession session = servletRequest.getSession();
			
			userTrackingVO.setRemoteAddress(servletRequest.getRemoteAddr());
            userTrackingVO.setRequestURI(servletRequest.getRequestURI().substring(1).concat(servletRequest.getQueryString() != null ? "?"+servletRequest.getQueryString():""));
						
            if(session != null){
            	RegistrationVO registrationVO = (RegistrationVO)session.getAttribute(IWebConstants.USER);
            	if(registrationVO != null){
            		userTrackingVO.setUserType(registrationVO.getUserStatus());
            		userTrackingVO.setUserId(registrationVO.getRegistrationID());
            	}
            }
            userTrackingService.saveUserTrackingDetails(userTrackingVO);
		}				
		return invocation.invoke();
	}

	public void destroy() {
		
		
	}

	public void init() {
	
	}

}