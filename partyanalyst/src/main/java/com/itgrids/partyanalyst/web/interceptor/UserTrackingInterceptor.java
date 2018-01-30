package com.itgrids.partyanalyst.web.interceptor;


import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
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
	private static final Logger LOG = Logger.getLogger(UserTrackingInterceptor.class);
	
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
		String host = request.getHeader("host");
		HttpSession session = request.getSession();
		String url = request.getRequestURL().toString();
		String requestMethod = request.getHeader("X-Requested-With");
		RegistrationVO registrationVO = (RegistrationVO)session.getAttribute(IWebConstants.USER);
		String pageTrack = (String) session.getAttribute("PageTrack");
		String requestTrack = (String) session.getAttribute("RequestTrack");
		
		/*if(registrationVO != null && registrationVO.getRegistrationID() != null)
		{	
			try{
			if((requestTrack != null && requestTrack.equalsIgnoreCase("Y")) || (pageTrack != null && pageTrack.equalsIgnoreCase("Y") && 
					(requestMethod == null || (requestMethod != null && !requestMethod.equalsIgnoreCase("XMLHttpRequest")))))
			{
				UserTrackingVO userTrackingVO = new UserTrackingVO();
				userTrackingVO.setRemoteAddress(getClientIp(request));
	            userTrackingVO.setRequestURI(request.getRequestURI().substring(1).concat(request.getQueryString() != null ? "?"+request.getQueryString():""));
	            userTrackingVO.setSessionId(session.getId());
            
        	if(registrationVO != null)
        		userTrackingVO.setUserId(registrationVO.getRegistrationID());
        	
            	userTrackingService.saveUserTrackingDetails(userTrackingVO);
			}
			}catch(Exception e)
			{
				LOG.error(e);
			}
		}*/
		
		try{
		if(requestMethod == null || (requestMethod != null && !requestMethod.equalsIgnoreCase("XMLHttpRequest")))
		{
			UserTrackingVO userTrackingVO = new UserTrackingVO();
			userTrackingVO.setRemoteAddress(getClientIp(request));
            userTrackingVO.setRequestURI(request.getRequestURI().substring(1).concat(request.getQueryString() != null ? "?"+request.getQueryString():""));
            userTrackingVO.setSessionId(session.getId());
        
            if(registrationVO != null)
            	userTrackingVO.setUserId(registrationVO.getRegistrationID());
    	
        	userTrackingService.saveUserTrackingDetails(userTrackingVO);
		}
		}catch(Exception e)
		{
			LOG.error(e);
		}
		
		if(url.contains("partyAndLeaderActivitiesAndPerformanceTracking") || url.contains("leadertoleader.in"))
			return invocation.invoke();
		
		url = url.substring(url.lastIndexOf("/")+1,url.indexOf(".action"));
		List<String> exList = Arrays.asList(IConstants.EXCLUDE_URL_LIST);
		
		if(exList.contains(url))
				return invocation.invoke();
		
		if(url.contains("leadertoleader.in") || url.contains("localhost:8080/PartyAnalyst"))
			return invocation.invoke();
		
		
		if( request.getRequestURL().indexOf("loginPopUpsAction") == -1 && checkRequestNeedAuthentication(request.getRequestURL())  && IConstants.DEPLOYED_HOST.equalsIgnoreCase("tdpserver"))
		{
			String[] arr = request.getRequestURL().toString().split("/");
			//if(arr[arr.length-1].toString().trim().equalsIgnoreCase("unionSearchRegiststrationAction.action"))
			if(!(Arrays.asList(IConstants.LOGIN_SKIP_URLS_LIST).contains(arr[arr.length-1].toString().trim())))
			{
				if(registrationVO == null && host.contains("mydepartments.in"))
					return "apGovtLoginPage";
				
				if(registrationVO == null && request.getRequestURL().indexOf("login") == -1)
					return "tdpLoginPage";
				else if(arr[arr.length-1].toString().trim().equalsIgnoreCase("login.action"))
					return "success";
				else if(arr[arr.length-1].toString().trim().equalsIgnoreCase("loginPopUpsAction.action"))
					return "dashboard";
			}
			
		}
		
		if(registrationVO != null && registrationVO.getRegistrationID() != null)
        {
			userRoles = registrationVO.getUserRoles();
			
			if(userRoles.contains(IConstants.PARTY_ANALYST_USER) && registrationVO.isMultipleAccessRestriction())
			{
				/*String sessionId = accessRestrictedSessionDAO.verifySessionIdAvailability(session.getId());
	    		if(sessionId != null)
	    		{
	    			accessRestrictedSessionDAO.deleteSessionsFromAccessRestrictedSession(session.getId());
	    			session.invalidate();
	    			return "multipleAccess";
	    		}*/
			}
		}
						
		return invocation.invoke();
		}catch (Exception e) {
			LOG.error("Exception Occured, Exception is - ",e);
			return invocation.invoke();
		}
	}


	public void destroy() {}

	public void init() {}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	public boolean checkRequestNeedAuthentication(StringBuffer requestUrl){
		boolean needAuth = true;
		
		for(String url:IConstants.excludeUrl){
			if(!(requestUrl.indexOf(url) == -1)){
				needAuth = false;
			}
		}
		
		return needAuth;
	}
	
	private static String getClientIp(HttpServletRequest request) 
	{
		try{
			String clientAddr = "";

			if (request != null) 
			{
				clientAddr = request.getHeader("X-FORWARDED-FOR");
				
				if(clientAddr == null || "".equals(clientAddr)) 
					clientAddr = request.getRemoteAddr();
			}
			return clientAddr;
		}catch(Exception e)
	    {
	    	LOG.error("Exception Occured, Exception is - ",e);
	    }
		return null;
	}
}