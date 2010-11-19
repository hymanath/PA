/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on November 15, 2010
 */
package com.itgrids.partyanalyst.web.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.util.ServletContextAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.UserGroupMembersVO;
import com.itgrids.partyanalyst.service.IInfluencingPeopleService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @author Sai Krishna
 *
 */
public class SaveLocalGroupMembersAction extends ActionSupport implements
		ServletRequestAware, ServletResponseAware, ServletContextAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(SaveLocalGroupMembersAction.class);
	
	private HttpServletRequest request;
	private HttpServletResponse response;
	private ServletContext context;
	private HttpSession session;
	
	JSONObject jObj =null;
	private String task = null;
	
	private String name;
	private String mobile;
	private String email;
	private String address;
	private String city;
	private String groupCategory;
	private String groupName;
	private String designations;
	
	UserGroupMembersVO userGroupMemberVO;
	
	private IInfluencingPeopleService influencingPeopleService;

	
	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getGroupCategory() {
		return groupCategory;
	}

	public void setGroupCategory(String groupCategory) {
		this.groupCategory = groupCategory;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getDesignations() {
		return designations;
	}

	public void setDesignations(String designations) {
		this.designations = designations;
	}

	public UserGroupMembersVO getUserGroupMemberVO() {
		return userGroupMemberVO;
	}

	public void setUserGroupMemberVO(UserGroupMembersVO userGroupMemberVO) {
		this.userGroupMemberVO = userGroupMemberVO;
	}

	public IInfluencingPeopleService getInfluencingPeopleService() {
		return influencingPeopleService;
	}

	public void setInfluencingPeopleService(
			IInfluencingPeopleService influencingPeopleService) {
		this.influencingPeopleService = influencingPeopleService;
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
	public ServletContext getContext() {
		return context;
	}

	public void setServletContext(ServletContext context) {
	   this.context = context;
	}

	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}
	
	
	public String execute(){
		
		session = request.getSession();
		RegistrationVO regVO = (RegistrationVO)session.getAttribute("USER");
		Long userId = regVO.getRegistrationID();
		
		userGroupMemberVO = new UserGroupMembersVO();
		
		userGroupMemberVO.setUserId(userId);
		userGroupMemberVO.setName(name);
		userGroupMemberVO.setAddress(address);
		userGroupMemberVO.setEmailId(email);
		userGroupMemberVO.setMobileNumber(mobile);
		userGroupMemberVO.setLocation(city);
		userGroupMemberVO.setDesignationId(new Long(designations));
		userGroupMemberVO.setGroupId(new Long(groupName));
		userGroupMemberVO.setWindowTask("save");
				
		userGroupMemberVO = influencingPeopleService.saveUserGroupMemberDetails(userGroupMemberVO);
		
				
	 return Action.SUCCESS;
	}

}
