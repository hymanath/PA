package com.itgrids.partyanalyst.web.interceptor;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.itgrids.partyanalyst.dao.IAccessRestrictedSessionDAO;
import com.itgrids.partyanalyst.dao.IUserLoginDetailsDAO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.UserTrackingVO;
import com.itgrids.partyanalyst.service.IUserTrackingService;
import com.itgrids.partyanalyst.util.IWebConstants;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.opensymphony.xwork2.interceptor.Interceptor;


public class UserTrackingInterceptor extends AbstractInterceptor implements ServletRequestAware,ServletResponseAware,Interceptor  {
	private IUserTrackingService userTrackingService;
	private HttpServletResponse response;
	private IUserLoginDetailsDAO userLoginDetailsDAO;
	private IAccessRestrictedSessionDAO accessRestrictedSessionDAO;
	private List<String> userRoles;
	private HttpServletRequest request;
	private static final long serialVersionUID = 1L;
	
	public IAccessRestrictedSessionDAO getAccessRestrictedSessionDAO() {
		return accessRestrictedSessionDAO;
	}

	public void setAccessRestrictedSessionDAO(
			IAccessRestrictedSessionDAO accessRestrictedSessionDAO) {
		this.accessRestrictedSessionDAO = accessRestrictedSessionDAO;
	}
	
	public HttpServletResponse getResponse() {
		return response;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public List<String> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(List<String> userRoles) {
		this.userRoles = userRoles;
	}

	public IUserTrackingService getUserTrackingService() {
		return userTrackingService;
	}

	public void setUserTrackingService(IUserTrackingService userTrackingService) {
		this.userTrackingService = userTrackingService;
	}

	public IUserLoginDetailsDAO getUserLoginDetailsDAO() {
		return userLoginDetailsDAO;
	}

	public void setUserLoginDetailsDAO(IUserLoginDetailsDAO userLoginDetailsDAO) {
		this.userLoginDetailsDAO = userLoginDetailsDAO;
	}

	public String intercept(ActionInvocation invocation) throws Exception 
	{
		try{
		request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		RegistrationVO registrationVO = (RegistrationVO)session.getAttribute(IWebConstants.USER);
		
		if(registrationVO != null && registrationVO.getRegistrationID() != null)
        {
			userRoles = registrationVO.getUserRoles();
			
			if(userRoles.contains(IConstants.PARTY_ANALYST_USER) && registrationVO.isMultipleAccessRestriction())
			{
				String sessionId = accessRestrictedSessionDAO.verifySessionIdAvailability(session.getId());
	    		if(sessionId != null)
	    		{
	    			accessRestrictedSessionDAO.deleteSessionsFromAccessRestrictedSession(session.getId());
	    			session.invalidate();
	    			return "multipleAccess";
	    		}
			}
		}
		if(invocation.getProxy().getMethod().equalsIgnoreCase("execute"))
		{	
			UserTrackingVO userTrackingVO = new UserTrackingVO();
			userTrackingVO.setRemoteAddress(request.getRemoteAddr());
            userTrackingVO.setRequestURI(request.getRequestURI().substring(1).concat(request.getQueryString() != null ? "?"+request.getQueryString():""));
            userTrackingVO.setSessionId(session.getId());
            
        	if(registrationVO != null)
        		userTrackingVO.setUserId(registrationVO.getRegistrationID());
        	
            userTrackingService.saveUserTrackingDetails(userTrackingVO);
		}				
		return invocation.invoke();
		}catch (Exception e) {
			return invocation.invoke();
		}
	}


	public void destroy() {}

	public void init() {}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	
}