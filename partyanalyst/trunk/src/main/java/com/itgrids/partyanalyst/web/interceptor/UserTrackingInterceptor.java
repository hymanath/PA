package com.itgrids.partyanalyst.web.interceptor;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.StrutsStatics;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.opensymphony.xwork2.interceptor.Interceptor;
import com.itgrids.partyanalyst.dao.IUserLoginDetailsDAO;
import com.itgrids.partyanalyst.dao.IUserLoginSessionDAO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.UserTrackingVO;

import com.itgrids.partyanalyst.util.IWebConstants;
import com.itgrids.partyanalyst.utils.IConstants;

import com.itgrids.partyanalyst.service.IUserTrackingService;


public class UserTrackingInterceptor extends AbstractInterceptor implements ServletRequestAware,ServletResponseAware,Interceptor  {
	private IUserTrackingService userTrackingService;
	private HttpServletResponse response;
	private IUserLoginSessionDAO userLoginSessionDAO;
	private IUserLoginDetailsDAO userLoginDetailsDAO;
	
    private List<String> userRoles;
	 
	private HttpServletRequest request;
	private static final long serialVersionUID = 1L;
	
 
	
	public HttpServletResponse getResponse() {
		return response;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request=request;
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

	public IUserLoginSessionDAO getUserLoginSessionDAO() {
		return userLoginSessionDAO;
	}

	public void setUserLoginSessionDAO(IUserLoginSessionDAO userLoginSessionDAO) {
		this.userLoginSessionDAO = userLoginSessionDAO;
	}

	
	public String intercept(ActionInvocation invocation) throws Exception 
	{
		try{
		HttpServletRequest servletRequest = ServletActionContext.getRequest();
		HttpSession session = servletRequest.getSession();
		RegistrationVO registrationVO = (RegistrationVO)session.getAttribute(IWebConstants.USER);
		if(registrationVO != null){
		if(registrationVO.getRegistrationID() != null)
        {
			userRoles = registrationVO.getUserRoles();
			
			if(userRoles.contains(IConstants.PARTY_ANALYST_USER) && registrationVO.isMultipleAccessRestriction() == true)
			{
				
				List<String> sessionIds = userLoginSessionDAO.getSessionIds(new Long(registrationVO.getRegistrationID()),session.getId());
	    		if(sessionIds != null && sessionIds.size() > 0)
	    		{
	    			
	    			userLoginSessionDAO.deleteSessionsFromUserLoginSession(session.getId());
	    			session.invalidate();
	    			HttpServletResponse response = (HttpServletResponse)invocation.getInvocationContext().get(StrutsStatics.HTTP_RESPONSE);
	    			return "multipleAccess";
	    			
	    		}
			}

        }
		}
		if(invocation.getProxy().getMethod().equalsIgnoreCase("execute"))
		{	
			UserTrackingVO userTrackingVO;
		
			userTrackingVO = new UserTrackingVO();
			
			userTrackingVO.setRemoteAddress(servletRequest.getRemoteAddr());
            userTrackingVO.setRequestURI(servletRequest.getRequestURI().substring(1).concat(servletRequest.getQueryString() != null ? "?"+servletRequest.getQueryString():""));
            userTrackingVO.setSessionId(session.getId());
            
            if(session != null)
            {
            	//RegistrationVO registrationVO = (RegistrationVO)session.getAttribute(IWebConstants.USER);
            	
            	if(registrationVO != null)
            	{
            		
            		userTrackingVO.setUserId(registrationVO.getRegistrationID());
            		
            	}
            }
            userTrackingService.saveUserTrackingDetails(userTrackingVO);
		}				
		return invocation.invoke();
		}catch (Exception e) {
			return invocation.invoke();
		}
	}


	public void destroy() {
		
		
	}

	public void init() {
	
	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	
}