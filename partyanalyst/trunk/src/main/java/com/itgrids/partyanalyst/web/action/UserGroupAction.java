package com.itgrids.partyanalyst.web.action;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

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
	private String task = null;
	JSONObject jObj = null;
	
	
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
	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public String execute() throws Exception
	{	
		String param = null;
		param = getTask();
		
		try {
			jObj = new JSONObject(param);
			System.out.println(jObj);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//log.debug("Task::"+jObj.getString("task"));
		
		userGroupsVO = new UserGroupsVO();
		List<UserGroupDetailsVO> grpsByUser = new ArrayList<UserGroupDetailsVO>();
		 				
		UserGroupDetailsVO userGroup1Details = new UserGroupDetailsVO();
		
		userGroup1Details.setGroupName("State Level Media");
		userGroup1Details.setGroupId(1L);
		userGroup1Details.setGroupDesc("State Level Media contains information regarding all the media in the state");
		
		List<UserGroupDetailsVO> subGrpsByUser = new ArrayList<UserGroupDetailsVO>();
		
		UserGroupDetailsVO userGroup1SubGroupDetails = new UserGroupDetailsVO();
		userGroup1SubGroupDetails.setGroupId(1L);
		userGroup1SubGroupDetails.setGroupName("District Level Media");
		userGroup1SubGroupDetails.setGroupDesc("This group contains media persons related to a particular district");
		subGrpsByUser.add(userGroup1SubGroupDetails);
		userGroup1Details.setUserSubGroups(subGrpsByUser);
		
			
		UserGroupDetailsVO userGroup2Details = new UserGroupDetailsVO();
		
		userGroup2Details.setGroupName("District Officials");
		userGroup2Details.setGroupId(2L);
		userGroup2Details.setGroupDesc("District level officials is related with highest governement officials of a district");
					
		grpsByUser.add(userGroup1Details);
		grpsByUser.add(userGroup2Details);
		
		userGroupsVO.setGroupsCreatedByUser(grpsByUser);	
	
		return Action.SUCCESS;
	}
	public String getGroupMbrsForGroup()
	{
		String param = null;
		param = getTask();
		
		try {
			jObj = new JSONObject(param);
			System.out.println(jObj);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Long groupId = new Long(jObj.getString("grpId"));
		userGroupsVO = new UserGroupsVO();
		if(groupId == 1)
		{	
				List<UserGroupMembersVO> mediaGroupMembers = new ArrayList<UserGroupMembersVO>();
				UserGroupMembersVO group1Member1 = new UserGroupMembersVO();
				
				group1Member1.setUserName("Rama Rao");
				group1Member1.setUserId(123l);
				group1Member1.setAddress("HouseNO:3-3 chinna vari tota");
				group1Member1.setMobileNumber("9989900010");
				group1Member1.setPhoneNumber("27508469");
				group1Member1.setGroupName("State Level Media");
				group1Member1.setDesignation("Editor-Eenadu Print Media");
				group1Member1.setLocation("Vijayawada");
				UserGroupMembersVO group1Member2 = new UserGroupMembersVO();
				
				group1Member2.setUserName("Raghunath");
				group1Member2.setUserId(13l);
				group1Member2.setAddress("HouseNO:3-123/7f mallavaram");
				group1Member2.setMobileNumber("9989900010");
				group1Member2.setPhoneNumber("27508469");
				group1Member2.setGroupName("State Level Media");
				group1Member2.setDesignation("News Editor-ETV2 ");
				group1Member2.setLocation("Hyderabad");
				mediaGroupMembers.add(group1Member1);
				mediaGroupMembers.add(group1Member2);
				userGroupsVO.setGroupsMembers(mediaGroupMembers);
			} else
			{
				List<UserGroupMembersVO> distOffGroupMembers = new ArrayList<UserGroupMembersVO>();
				UserGroupMembersVO group2Member1 = new UserGroupMembersVO();
				
				group2Member1.setUserName("SundaraChary");
				group2Member1.setUserId(13l);
				group2Member1.setAddress("FlatNO:12-09/6f Mythritowers");
				group2Member1.setMobileNumber("9989900010");
				group2Member1.setPhoneNumber("27508469");
				group2Member1.setGroupName("District Officials");
				group2Member1.setDesignation("D.E.O");
				group2Member1.setLocation("Nellore");		
				UserGroupMembersVO group2Member2 = new UserGroupMembersVO();
				
				group2Member2.setUserName("Umesh");
				group2Member2.setUserId(32l);
				group2Member2.setAddress("FlatNO:3-123/7f mallavaram");
				group2Member2.setMobileNumber("9989900010");
				group2Member2.setPhoneNumber("27508469");
				group2Member2.setGroupName("District Officials");
				group2Member2.setDesignation("S.P");
				group2Member2.setLocation("Nellore");
				distOffGroupMembers.add(group2Member1);
				distOffGroupMembers.add(group2Member2);
				userGroupsVO.setGroupsMembers(distOffGroupMembers);
			}
		return Action.SUCCESS;	
	}
}	
