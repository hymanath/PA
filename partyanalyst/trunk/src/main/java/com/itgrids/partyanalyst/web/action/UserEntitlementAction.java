package com.itgrids.partyanalyst.web.action;

import java.text.ParseException;
import java.util.Arrays;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.ServletContextAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.EntitlementVO;
import com.itgrids.partyanalyst.service.IRegistrationService;
import com.itgrids.partyanalyst.service.IUserEntitlementService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class UserEntitlementAction extends ActionSupport implements
ServletRequestAware, ServletContextAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger log = Logger.getLogger(UserEntitlementAction.class);
	
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

		allRegisteredUsersData = registrationService.getAllRegisterdUsers();	
		allEntitlements = userEntitlementService.getAllEntitlements();
		return Action.SUCCESS;
	}
	
	public String entitlementsAction(){
		
		try {
			jObj = new JSONObject(getTask());
			System.out.println(jObj);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		if(jObj.getString("type").equalsIgnoreCase("getAllDetailsOfAGroup")){
			entitlements = userEntitlementService.getAllDetailsOfAGroup(jObj.getLong("groupId"),jObj.getString("name"));
		}
		
		else if(jObj.getString("type").equalsIgnoreCase("checkForgroupNameAvailability")){
			entitlements = userEntitlementService.checkAvailabilityOfGroup(jObj.getString("name"));
		}
		
		else if(jObj.getString("type").equalsIgnoreCase("createAGroup")){
			entitlements = userEntitlementService.creatingAGroup(jObj.getString("name"),jObj.getString("selectedIds"));
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
		
		else if(jObj.getString("type").equalsIgnoreCase("getAllGroups")){
			entitlements = userEntitlementService.getAllGroups();
		}
		
		else if(jObj.getString("type").equalsIgnoreCase("getAllUserGroups")){
			entitlements = userEntitlementService.getAllUserGroups();
		}
		
		else if(jObj.getString("type").equalsIgnoreCase("saveUserGroupsRelation")){
		
			entitlements = userEntitlementService.saveUserGroupsRelation(jObj.getLong("userId"),jObj.getString("groupIds"));
		}
		
		else if(jObj.getString("type").equalsIgnoreCase("getAllEntitlementsForAUserGroup")){
			entitlements = userEntitlementService.getAllEntitlementsForAUserGroup(jObj.getLong("userGroupId"));
		}
		
		return SUCCESS;
	}
	
	
}
