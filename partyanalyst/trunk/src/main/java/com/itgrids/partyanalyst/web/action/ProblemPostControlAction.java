/* 
 * Copyright (c) 2009 IT Grids India LTD.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on September 29, 2010
 */
package com.itgrids.partyanalyst.web.action;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.util.ServletContextAware;

import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @author Sai Krishna Basetti
 *
 */
public class ProblemPostControlAction extends ActionSupport implements
		ServletRequestAware, ServletResponseAware, ServletContextAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final Logger log = Logger.getLogger(ProblemPostControlAction.class);
	
	private HttpServletRequest request;
	private HttpServletResponse response;
	private ServletContext context;
    private HttpSession session;
	
    private String userStatus = null;
    
    private String redirectLoc;
    
    private String task;
    private String name;
    private Long stateId;
    private Long districtId;
    private String districtName;
    private Long localBodyId;
    private Long constituencyId;
    private Long localBodyElectionTypeId;
    private Long parliamentConstiId;
    private String parliamentConstiName;
    private String constituencyName;
    private String taskType;
	
	public String getTaskType() {
		return taskType;
	}

	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}

	public String getConstituencyName() {
		return constituencyName;
	}

	public void setConstituencyName(String constituencyName) {
		this.constituencyName = constituencyName;
	}

	public Long getParliamentConstiId() {
		return parliamentConstiId;
	}

	public void setParliamentConstiId(Long parliamentConstiId) {
		this.parliamentConstiId = parliamentConstiId;
	}

	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public Long getDistrictId() {
		return districtId;
	}

	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}

	public Long getLocalBodyId() {
		return localBodyId;
	}

	public void setLocalBodyId(Long localBodyId) {
		this.localBodyId = localBodyId;
	}

	public Long getLocalBodyElectionTypeId() {
		return localBodyElectionTypeId;
	}

	public void setLocalBodyElectionTypeId(Long localBodyElectionTypeId) {
		this.localBodyElectionTypeId = localBodyElectionTypeId;
	}

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public Long getStateId() {
		return stateId;
	}

	public void setStateId(Long stateId) {
		this.stateId = stateId;
	}

	public String getRedirectLoc() {
		return redirectLoc;
	}

	public void setRedirectLoc(String redirectLoc) {
		this.redirectLoc = redirectLoc;
	}

	public Long getConstituencyId() {
		return constituencyId;
	}

	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}

	/* (non-Javadoc)
	 * @see org.apache.struts2.interceptor.ServletRequestAware#setServletRequest(javax.servlet.http.HttpServletRequest)
	 */
	public HttpServletRequest getRequest() {
		return request;
	}
	
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	/* (non-Javadoc)
	 * @see org.apache.struts2.interceptor.ServletResponseAware#setServletResponse(javax.servlet.http.HttpServletResponse)
	 */
	public HttpServletResponse getResponse() {
		return response;
	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	/* (non-Javadoc)
	 * @see org.apache.struts2.util.ServletContextAware#setServletContext(javax.servlet.ServletContext)
	 */
	public void setServletContext(ServletContext context) {
		this.context = context;
	}
	
	public String getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}	

	public String getParliamentConstiName() {
		return parliamentConstiName;
	}

	public void setParliamentConstiName(String parliamentConstiName) {
		this.parliamentConstiName = parliamentConstiName;
	}

	public String execute(){
		
		session = request.getSession();
		Object regVO = session.getAttribute(IConstants.USER);
		
		if(regVO == null){
			log.error(" No User Log In .....");
			userStatus = IConstants.PROBLEM_MANAGEMENT_LOGIN;
			return IConstants.NOT_LOGGED_IN;
		}
		
		else if(regVO != null){
			
			RegistrationVO userVO = (RegistrationVO)regVO;
			if(userVO.getUserStatus().equals(IConstants.PARTY_ANALYST_USER)){
				if(taskType.equalsIgnoreCase("analyze"))
					return getRedirectPageDetails();
				else
				return IConstants.PARTY_ANALYST_USER;
			}else if(userVO.getUserStatus().equals(IConstants.FREE_USER)){
				//task = "pm_redirect";
				return getRedirectPageDetails();
			}
				
		}
		
	 return Action.SUCCESS;
	}
	
	public String getRedirectPageDetails(){
		
		if(redirectLoc != null){
			if(redirectLoc.equalsIgnoreCase(IConstants.STATE)){
				return "statePageRedirect";
			}else if(redirectLoc.equalsIgnoreCase(IConstants.DISTRICT)){
				return "districtPageRedirect";
			}else if(redirectLoc.equalsIgnoreCase(IConstants.CONSTITUENCY)){
				return "constituencyPageRedirect";
			}else if(redirectLoc.equalsIgnoreCase(IConstants.LOCAL_BODY_ELECTION)){
				return "localElectionBodyPageRedirect";
			}
		}
		
	 return Action.ERROR;
	}

}
