package com.itgrids.partyanalyst.web.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.itgrids.partyanalyst.dto.UserGroupDetailsVO;
import com.itgrids.partyanalyst.dto.UserGroupMembersVO;
import com.itgrids.partyanalyst.dto.UserGroupsVO;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class UserGroupAction extends ActionSupport implements ServletRequestAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private UserGroupsVO userGroupsVO;
	
	
	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}	

	public UserGroupsVO getUserGroupsVO() {
		return userGroupsVO;
	}

	public void setUserGroupsVO(UserGroupsVO userGroupsVO) {
		this.userGroupsVO = userGroupsVO;
	}

	public String execute() throws Exception
	{	
		userGroupsVO = new UserGroupsVO();
		List<UserGroupDetailsVO> grpsByUser = new ArrayList<UserGroupDetailsVO>();
				
		
		List<UserGroupMembersVO> mediaGroupMembers = new ArrayList<UserGroupMembersVO>();
		List<UserGroupMembersVO> distOffGroupMembers = new ArrayList<UserGroupMembersVO>();
				
		UserGroupDetailsVO userGroup1Details = new UserGroupDetailsVO();
		userGroup1Details.setGroupName("STATE LEVEL MEDIA");
		userGroup1Details.setGroupId(1L);
		userGroup1Details.setGroupDesc("State Level Media contains information regarding all the media in the state");
		
		UserGroupMembersVO group1Member1 = new UserGroupMembersVO();
		
		group1Member1.setUserName("Rama Rao");
		group1Member1.setUserId(123l);
		group1Member1.setAddress("HouseNO:3-3 chinna vari tota Nellore");
		group1Member1.setMobileNumber("08694569151");
		group1Member1.setPhoneNumber("9589456232");
		
		UserGroupMembersVO group1Member2 = new UserGroupMembersVO();
		
		group1Member2.setUserName("Raghunath");
		group1Member2.setUserId(13l);
		group1Member2.setAddress("HouseNO:3-123/7f mallavaram Guntur");
		group1Member2.setMobileNumber("0863456789");
		group1Member2.setPhoneNumber("9949930689");
		
		mediaGroupMembers.add(group1Member1);
		mediaGroupMembers.add(group1Member2);
		
		userGroup1Details.setMembers(mediaGroupMembers);
			
		UserGroupDetailsVO userGroup2Details = new UserGroupDetailsVO();
		
		userGroup2Details.setGroupName("DISTRICT OFFICIALS");
		userGroup2Details.setGroupId(2L);
		userGroup2Details.setGroupDesc("District level officials is related with highest governement officials of a district");
		
		UserGroupMembersVO group2Member1 = new UserGroupMembersVO();
		
		group2Member1.setUserName("SundaraChary");
		group2Member1.setUserId(13l);
		group2Member1.setAddress("FlatNO:12-09/6f Mythritowers Borananda");
		group2Member1.setMobileNumber("08694569151");
		group2Member1.setPhoneNumber("9589456232");
				
		UserGroupMembersVO group2Member2 = new UserGroupMembersVO();
		
		group2Member2.setUserName("UmeshChandra");
		group2Member2.setUserId(32l);
		group2Member2.setAddress("FlatNO:3-123/7f mallavaram Guntur");
		group2Member2.setMobileNumber("0863456789");
		group2Member2.setPhoneNumber("9949930689");
		
		distOffGroupMembers.add(group2Member1);
		distOffGroupMembers.add(group2Member2);
		
		userGroup2Details.setMembers(distOffGroupMembers);
		
		grpsByUser.add(userGroup1Details);
		grpsByUser.add(userGroup2Details);
		
		userGroupsVO.setGroupsCreatedByUser(grpsByUser);
		
		return Action.SUCCESS;
	}

}
