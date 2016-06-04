package com.itgrids.partyanalyst.web.action;

import java.text.ParseException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.ServletContextAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.EntitlementVO;
import com.itgrids.partyanalyst.dto.NavigationVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.helper.EntitlementsHelper;
import com.itgrids.partyanalyst.service.IRegistrationService;
import com.itgrids.partyanalyst.service.IUserEntitlementService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class UserEntitlementAction extends ActionSupport implements
ServletRequestAware, ServletContextAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger LOG = Logger.getLogger(UserEntitlementAction.class);
	
	private ServletContext context;
	HttpServletRequest request;
	HttpServletResponse response;
	HttpSession session;
	private String task;
	org.json.JSONObject jObj;
	private IUserEntitlementService userEntitlementService;
	private IRegistrationService registrationService;
	private EntitlementVO allRegisteredUsersData;
	private EntitlementVO entitlements;
	private EntitlementVO allEntitlements;
	private EntitlementVO allGroups;
	private EntitlementVO allUserGroups;
	private NavigationVO navigationVO;
	private EntitlementsHelper entitlementsHelper;
	
	
	public EntitlementsHelper getEntitlementsHelper() {
		return entitlementsHelper;
	}

	public void setEntitlementsHelper(EntitlementsHelper entitlementsHelper) {
		this.entitlementsHelper = entitlementsHelper;
	}

	public NavigationVO getNavigationVO() {
		return navigationVO;
	}

	public void setNavigationVO(NavigationVO navigationVO) {
		this.navigationVO = navigationVO;
	}

	public EntitlementVO getAllUserGroups() {
		return allUserGroups;
	}

	public void setAllUserGroups(EntitlementVO allUserGroups) {
		this.allUserGroups = allUserGroups;
	}

	public EntitlementVO getAllGroups() {
		return allGroups;
	}

	public void setAllGroups(EntitlementVO allGroups) {
		this.allGroups = allGroups;
	}

	public EntitlementVO getAllEntitlements() {
		return allEntitlements;
	}

	public void setAllEntitlements(EntitlementVO allEntitlements) {
		this.allEntitlements = allEntitlements;
	}

	public EntitlementVO getEntitlements() {
		return entitlements;
	}

	public void setEntitlements(EntitlementVO entitlements) {
		this.entitlements = entitlements;
	}

	public EntitlementVO getAllRegisteredUsersData() {
		return allRegisteredUsersData;
	}

	public void setAllRegisteredUsersData(EntitlementVO allRegisteredUsersData) {
		this.allRegisteredUsersData = allRegisteredUsersData;
	}

	public IRegistrationService getRegistrationService() {
		return registrationService;
	}

	public void setRegistrationService(IRegistrationService registrationService) {
		this.registrationService = registrationService;
	}

	public IUserEntitlementService getUserEntitlementService() {
		return userEntitlementService;
	}

	public void setUserEntitlementService(
			IUserEntitlementService userEntitlementService) {
		this.userEntitlementService = userEntitlementService;
	}

	public ServletContext getContext() {
		return context;
	}

	public void setContext(ServletContext context) {
		this.context = context;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public org.json.JSONObject getJObj() {
		return jObj;
	}

	public void setJObj(org.json.JSONObject obj) {
		jObj = obj;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setServletResponse(HttpServletResponse response) {
        this.response = response;
	}
	public void setServletContext(ServletContext context) {
		this.context = context;		
	}

	public String execute(){
		
		session = request.getSession();
	//	 HttpSession session = request.getSession();
		
			RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
			if(user == null)
				return INPUT;
		/*if(session.getAttribute(IConstants.USER) == null && 
				!entitlementsHelper.checkForEntitlementToViewReport(null, IConstants.ENTITLEMENT_PAGE))
			return INPUT;
		if(!entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)session.getAttribute(IConstants.USER), IConstants.ENTITLEMENT_PAGE))
			return ERROR;*/
			List<String> entitlements = null;
		    if(user.getEntitlements() != null && user.getEntitlements().size()>0){
		      entitlements = user.getEntitlements();
	      if(user == null && !entitlements.contains(IConstants.ENTITLEMENT_PAGE)){
	        return INPUT;
	      }
	      if(!entitlements.contains(IConstants.ENTITLEMENT_PAGE))
	        return ERROR;
		
		allRegisteredUsersData = registrationService.getAllRegisterdUsers();
		allEntitlements = userEntitlementService.getAllEntitlements();
		allGroups = userEntitlementService.getAllGroups();
		allUserGroups = userEntitlementService.getAllUserGroups();
	    }
		return Action.SUCCESS;
	}
	
	public String entitlementsAction(){
		
		try {
			jObj = new JSONObject(getTask());
			LOG.info(jObj);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		if(jObj.getString("type").equalsIgnoreCase("getAllDetailsOfAGroup")){
			entitlements = userEntitlementService.getAllDetailsOfAGroup(jObj.getLong("groupId"),jObj.getString("name"));
		}
		
		else if(jObj.getString("type").equalsIgnoreCase("checkForgroupNameAvailability")){
			entitlements = userEntitlementService.checkAvailabilityOfGroup(jObj.getString("name"));
		}
		
		else if(jObj.getString("type").equalsIgnoreCase("checkForUserGroupNameAvailability")){
			entitlements = userEntitlementService.checkForUserGroupNameAvailability(jObj.getString("name"));
		}
		
		else if(jObj.getString("type").equalsIgnoreCase("createAGroup")){
			entitlements = userEntitlementService.creatingAGroup(jObj.getString("name"),jObj.getString("selectedIds"));
		}
		
		else if(jObj.getString("type").equalsIgnoreCase("createAnUserGroup")){
			entitlements = userEntitlementService.creatingAUserGroup(jObj.getString("name"));
		}
		
		else if(jObj.getString("type").equalsIgnoreCase("checkForAvailabilityOfAnEntitlement")){
			entitlements = userEntitlementService.checkAvailabilityOfEntitlement(jObj.getString("name"));
		}
		
		else if(jObj.getString("type").equalsIgnoreCase("createAnEntitlement")){
			entitlements = userEntitlementService.creatingAnEntitlement(jObj.getString("name"));
		}		
		
		else if(jObj.getString("type").equalsIgnoreCase("getAllEntitlements")){
			entitlements = userEntitlementService.getAllEntitlements();
		}
				
		else if(jObj.getString("type").equalsIgnoreCase("getAllUserGroups")){
			entitlements = userEntitlementService.getAllUserGroups();
		}
		
		else if(jObj.getString("type").equalsIgnoreCase("getAllEntitlementGroups")){
			entitlements = userEntitlementService.getAllGroups();
		}
		
		else if(jObj.getString("type").equalsIgnoreCase("saveUserGroupsRelation")){		
			entitlements = userEntitlementService.saveUserGroupsRelation(jObj.getLong("userId"),jObj.getString("groupIds"));
		}
		
		else if(jObj.getString("type").equalsIgnoreCase("getAllEntitlementsForAUserGroup")){
			entitlements = userEntitlementService.getAllEntitlementsForAUserGroup(jObj.getLong("userGroupId"),jObj.getString("name"));
		}
		
		else if(jObj.getString("type").equalsIgnoreCase("saveRelationBetweenEntitlementGroupAndEntitlement")){		
			entitlements = userEntitlementService.saveRelationBetweenEntitlementGroupAndEntitlement(jObj.getLong("groupId"),jObj.getString("entitlementIds"));
		}
		
		else if(jObj.getString("type").equalsIgnoreCase("saveRelationBetweenEntitlementsGroupsAndUserGroupId")){
			entitlements = userEntitlementService.saveRelationBetweenEntitlementsGroupsAndUserGroupId(jObj.getLong("userGroupId"),jObj.getString("entitlementGroupIds"));
		}
		return SUCCESS;
	}
	
	public String entitlementsUserAction(){
		
		try {
			jObj = new JSONObject(getTask());
			LOG.info(jObj);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	   if(jObj.getString("type").equalsIgnoreCase("getAllGroups")){		
			navigationVO = userEntitlementService.getAllGroupsBasedOnUserId(Long.valueOf(jObj.getLong("selectedUserId")));
		}
	   else if(jObj.getString("type").equalsIgnoreCase("getAllEntitlementsBasedOnEntitlementGroup")){		
			navigationVO = userEntitlementService.getAllEntitlementsBasedOnEntitlementGroup(Long.valueOf(jObj.getLong("selectedEntitlementGroupId")));
	   }
	   if(jObj.getString("type").equalsIgnoreCase("getAllEntitlementGroupsBasedOnUserGroup")){		
			navigationVO = userEntitlementService.getAllEntitlementsGroupsBasedOnUserGroupId(Long.valueOf(jObj.getLong("selectedUserGroupId")));
		}
	return SUCCESS;
}
}
