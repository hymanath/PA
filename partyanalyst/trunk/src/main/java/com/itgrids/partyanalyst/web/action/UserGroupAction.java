package com.itgrids.partyanalyst.web.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.GroupsDetailsForUserVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.UserGroupDetailsVO;
import com.itgrids.partyanalyst.dto.UserGroupMembersVO;
import com.itgrids.partyanalyst.dto.UserGroupsVO;
import com.itgrids.partyanalyst.service.IUserGroupService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class UserGroupAction extends ActionSupport implements ServletRequestAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private HttpSession session;
	private UserGroupsVO userGroupsVO;
	private GroupsDetailsForUserVO subGroupsForStaticGroupForUser;
	private UserGroupDetailsVO userGroupsDescriptionVO;
	private String task = null;
	JSONObject jObj = null;
	private SimpleDateFormat sdf = new SimpleDateFormat(IConstants.DATE_PATTERN);
	private IUserGroupService userGroupService;
		
	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
		this.session = request.getSession();
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

	public GroupsDetailsForUserVO getSubGroupsForStaticGroupForUser() {
		return subGroupsForStaticGroupForUser;
	}

	public void setSubGroupsForStaticGroupForUser(
			GroupsDetailsForUserVO subGroupsForStaticGroupForUser) {
		this.subGroupsForStaticGroupForUser = subGroupsForStaticGroupForUser;
	}	
	
	public UserGroupDetailsVO getUserGroupsDescriptionVO() {
		return userGroupsDescriptionVO;
	}

	public void setUserGroupsDescriptionVO(
			UserGroupDetailsVO userGroupsDescriptionVO) {
		this.userGroupsDescriptionVO = userGroupsDescriptionVO;
	}	

	public IUserGroupService getUserGroupService() {
		return userGroupService;
	}

	public void setUserGroupService(IUserGroupService userGroupService) {
		this.userGroupService = userGroupService;
	}

	public String execute() throws Exception
	{	
		
		UserGroupDetailsVO userGroupDetailsVO;
		session = request.getSession();
		RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
		
		if(user==null)
			return ERROR;
		/*
		String param = null;
		param = getTask();
		
		try {
			jObj = new JSONObject(param);
			System.out.println(jObj);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(jObj.getString("task").equalsIgnoreCase("getSystemGroupsForUser"))
		{
			userGroupsVO = new UserGroupsVO();
			
			userGroupsVO.setGroupsDetailsForUser(userGroupService.systemGroupsDetailsForUser(user.getRegistrationID()));	
		} 
		if(jObj.getString("task").equalsIgnoreCase("createNewGroup"))
		{
			String staticGroupId = jObj.getString("staticGroupId");
			System.out.println(staticGroupId);
			userGroupDetailsVO = new UserGroupDetailsVO();
			userGroupDetailsVO.setCreatedUserId(user.getRegistrationID());
			userGroupDetailsVO.setGroupName(jObj.getString("groupName"));
			userGroupDetailsVO.setGroupDesc(jObj.getString("groupdDesc"));
			if(staticGroupId == "null")
			{
				userGroupDetailsVO.setStaticGroupId(null);
			}
			else
			{
				userGroupDetailsVO.setStaticGroupId(new Long(staticGroupId));	
			}
			
			userGroupService.createGroupForUser(userGroupDetailsVO);
			
		}*/
		return Action.SUCCESS;
		
	}
	
	/*public String getGroupMbrsForGroup()
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
				//userGroupsVO.setGroupsMembers(mediaGroupMembers);
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
				//userGroupsVO.setGroupsMembers(distOffGroupMembers);
			}
		return Action.SUCCESS;	
	}
	
	public String getSubGroupsInStaticGroupForUser()
	{
		String param = null;
		param = getTask();
		
		try {
			jObj = new JSONObject(param);
			System.out.println(jObj);
		} catch (ParseException e) {
		
			e.printStackTrace();
		}
		
		Long staticGrpId = new Long(jObj.getString("staticGrpId"));
		subGroupsForStaticGroupForUser = new GroupsDetailsForUserVO();
		List <UserGroupDetailsVO> subGroupsListForUser = new ArrayList<UserGroupDetailsVO>();
		System.out.println(staticGrpId);
		if(staticGrpId.equals(new Long(1)))
		{
			UserGroupDetailsVO mediaSubGroup1Details = new UserGroupDetailsVO();
			UserGroupDetailsVO mediaSubGroup2Details = new UserGroupDetailsVO();
						
			mediaSubGroup1Details.setGroupId(1L);
			mediaSubGroup1Details.setGroupName("State Level Print Media");
			mediaSubGroup1Details.setStaticGroupId(1L);
			mediaSubGroup1Details.setParentGroupId(0L);
						
			mediaSubGroup2Details.setGroupId(2L);
			mediaSubGroup2Details.setGroupName("State Level Electronic Media");
			mediaSubGroup2Details.setStaticGroupId(1L);
			mediaSubGroup2Details.setParentGroupId(0L);
			subGroupsListForUser.add(mediaSubGroup1Details);
			subGroupsListForUser.add(mediaSubGroup2Details);			
			
			subGroupsForStaticGroupForUser.setSubGroupsCreatedByUser(subGroupsListForUser);
			
			
		} else
		{
			
			//List <UserGroupDetailsVO> subGroupsListForUser = new ArrayList<UserGroupDetailsVO>();
			UserGroupDetailsVO OfficialsSubGroup1Details = new UserGroupDetailsVO();
			UserGroupDetailsVO OfficialsSubGroup2Details = new UserGroupDetailsVO();
			
			OfficialsSubGroup1Details.setGroupId(1L);
			OfficialsSubGroup1Details.setGroupName("State Govt. Officials");
			OfficialsSubGroup1Details.setStaticGroupId(2L);
			OfficialsSubGroup1Details.setParentGroupId(0L);
			OfficialsSubGroup2Details.setGroupId(2L);
			OfficialsSubGroup2Details.setGroupName("Central Govt. Officials");
			OfficialsSubGroup2Details.setStaticGroupId(2L);
			OfficialsSubGroup2Details.setParentGroupId(0L);
			subGroupsListForUser.add(OfficialsSubGroup1Details);
			subGroupsListForUser.add(OfficialsSubGroup2Details);
			
			subGroupsForStaticGroupForUser.setSubGroupsCreatedByUser(subGroupsListForUser);			
		}	
		
		
		return Action.SUCCESS;
	}
	
	@SuppressWarnings("deprecation")
	public String getCompleteGroupDescritption()
	{
		String param = null;
		param = getTask();
		
		try {
			jObj = new JSONObject(param);
			System.out.println(jObj);
		} catch (ParseException e) {
		
			e.printStackTrace();
		}
		
		Long staticGrpId = new Long(jObj.getString("staticGrpId"));
		Long myNodeId = new Long(jObj.getString("myNodeId"));
		Long parentGrpId = new Long(jObj.getString("parentGrpId"));
		List <UserGroupDetailsVO> userSubGroupsList = new ArrayList<UserGroupDetailsVO>();
		userGroupsDescriptionVO = new UserGroupDetailsVO();
		UserGroupDetailsVO userSubGroupDetailsVO = new UserGroupDetailsVO();
		if(staticGrpId == 1 && myNodeId == 1 && parentGrpId == 0)
		{
			Date date = new Date();
			List<UserGroupDetailsVO> subGroupInUserSubGroup = new ArrayList<UserGroupDetailsVO>();
			userGroupsDescriptionVO.setGroupId(1L);
			userGroupsDescriptionVO.setGroupName("State Level Print Media");
			userGroupsDescriptionVO.setGroupDesc("This group contains memebers who are working in print media sector in state level.");
			userGroupsDescriptionVO.setStaticGroupId(1L);
			userGroupsDescriptionVO.setCreatedDate(sdf.format(date));
			userGroupsDescriptionVO.setNoOfPersons("6");
			userGroupsDescriptionVO.setParentGroupId(0L);
					
			List<UserGroupDetailsVO> subGroupInUserSubGroup1 = new ArrayList<UserGroupDetailsVO>();
			
			UserGroupDetailsVO userSubGroup1DetailsVO = new UserGroupDetailsVO();
			
			userSubGroup1DetailsVO.setGroupId(1L);
			userSubGroup1DetailsVO.setGroupName("District Level Print Media");
			userSubGroup1DetailsVO.setGroupDesc("This group contains memebers who are working in print media sector in district level.");
			userSubGroup1DetailsVO.setParentGroupId(1L);
			userSubGroup1DetailsVO.setParentGroupName("State Level Print Media");
			userSubGroup1DetailsVO.setNoOfPersons("4");
			userSubGroup1DetailsVO.setStaticGroupId(1L);
			userSubGroup1DetailsVO.setCreatedDate(sdf.format(date));
			
			subGroupInUserSubGroup.add(userSubGroup1DetailsVO);
			
			userGroupsDescriptionVO.setUserSubGroups(subGroupInUserSubGroup);
			
			UserGroupDetailsVO userSubGroup2DetailsVO = new UserGroupDetailsVO();
			
			userSubGroup2DetailsVO.setGroupId(2L);
			userSubGroup2DetailsVO.setGroupName("Mandal Level Print Media");
			userSubGroup2DetailsVO.setGroupDesc("This group contains memebers who are working in print media sector in mandal level.");
			userSubGroup2DetailsVO.setParentGroupId(1L);
			userSubGroup2DetailsVO.setParentGroupName("District Level Print Media");
			userSubGroup2DetailsVO.setNoOfPersons("2");
			userSubGroup2DetailsVO.setStaticGroupId(1L);
			userSubGroup2DetailsVO.setCreatedDate(sdf.format(date));
			subGroupInUserSubGroup1.add(userSubGroup2DetailsVO);
			userSubGroup1DetailsVO.setUserSubGroups(subGroupInUserSubGroup1);
		} else if(staticGrpId == 1 && myNodeId == 1 && parentGrpId == 1 )
		{
			Date date = new Date();
			userGroupsDescriptionVO.setGroupId(2L);
			userGroupsDescriptionVO.setGroupName("District Level Print Media");
			userGroupsDescriptionVO.setGroupDesc("This group contains memebers who are working in print media sector in district level.");
			userGroupsDescriptionVO.setStaticGroupId(1L);
			userGroupsDescriptionVO.setParentGroupId(1L);
			userGroupsDescriptionVO.setCreatedDate(sdf.format(date));
			userGroupsDescriptionVO.setNoOfPersons("2");
			
		} else if(staticGrpId.equals(new Long(1)) && myNodeId.equals(new Long(2)))
		{
			Date date = new Date();
			userGroupsDescriptionVO = new UserGroupDetailsVO();
			userGroupsDescriptionVO.setGroupId(3L);
			userGroupsDescriptionVO.setGroupName("State Level Electronic Media");
			userGroupsDescriptionVO.setGroupDesc("This group contains memebers who are working in electronic media sector in state level.");
			userGroupsDescriptionVO.setStaticGroupId(1L);
			userGroupsDescriptionVO.setCreatedDate(sdf.format(date));
			userGroupsDescriptionVO.setNoOfPersons("6");
		} else if(staticGrpId.equals(new Long(2)) && myNodeId.equals(new Long(1)))
		{
			Date date = new Date();
			userGroupsDescriptionVO = new UserGroupDetailsVO();
			userGroupsDescriptionVO.setGroupId(1L);
			userGroupsDescriptionVO.setGroupName("State Govt. Officials");
			userGroupsDescriptionVO.setGroupDesc("This group contains memebers who are working in several Governmenet departments in state level.");
			userGroupsDescriptionVO.setStaticGroupId(2L);
			userGroupsDescriptionVO.setCreatedDate(sdf.format(date));
			userGroupsDescriptionVO.setNoOfPersons("6");
		} else if(staticGrpId.equals(new Long(2)) && myNodeId.equals(new Long(2)))
		{
			Date date = new Date();
			userGroupsDescriptionVO = new UserGroupDetailsVO();
			userGroupsDescriptionVO.setGroupId(2L);
			userGroupsDescriptionVO.setGroupName("Central Govt. Officials");
			userGroupsDescriptionVO.setGroupDesc("This group contains memebers who are working in central governmenet departments .");
			userGroupsDescriptionVO.setStaticGroupId(2L);
			userGroupsDescriptionVO.setCreatedDate(sdf.format(date));
			userGroupsDescriptionVO.setNoOfPersons("6");
			
		}
		return Action.SUCCESS;
	}*/
}	
