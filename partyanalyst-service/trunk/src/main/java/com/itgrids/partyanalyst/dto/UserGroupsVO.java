package com.itgrids.partyanalyst.dto;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class UserGroupsVO {
	
	private List <GroupsDetailsForUserVO> groupsDetailsForUser;
	private List<UserGroupMembersVO> userGroupMembersList;
	private List<SelectOptionVO> systemGroupsList;
	private List<SelectOptionVO> myGroupsList;
	private List<SelectOptionVO> breadCrumb;
	private Map<Long, String> BMap;
	private Set<SelectOptionVO> breadCrumbList;
	 
	public List<GroupsDetailsForUserVO> getGroupsDetailsForUser() {
		return groupsDetailsForUser;
	}

	public void setGroupsDetailsForUser(
			List<GroupsDetailsForUserVO> groupsDetailsForUser) {
		this.groupsDetailsForUser = groupsDetailsForUser;
	}
	
	public List<UserGroupMembersVO> getUserGroupMembersList() {
		return userGroupMembersList;
	}

	public void setUserGroupMembersList(
			List<UserGroupMembersVO> userGroupMembersList) {
		this.userGroupMembersList = userGroupMembersList;
	}

	public List<SelectOptionVO> getSystemGroupsList() {
		return systemGroupsList;
	}

	public void setSystemGroupsList(List<SelectOptionVO> systemGroupsList) {
		this.systemGroupsList = systemGroupsList;
	}

	public List<SelectOptionVO> getMyGroupsList() {
		return myGroupsList;
	}

	public void setMyGroupsList(List<SelectOptionVO> myGroupsList) {
		this.myGroupsList = myGroupsList;
	}

	public List<SelectOptionVO> getBreadCrumb() {
		return breadCrumb;
	}

	public void setBreadCrumb(List<SelectOptionVO> breadCrumb) {
		this.breadCrumb = breadCrumb;
	}

	public Map<Long, String> getBMap() {
		return BMap;
	}

	public void setBMap(Map<Long, String> map) {
		BMap = map;
	}

	public Set<SelectOptionVO> getBreadCrumbList() {
		return breadCrumbList;
	}

	public void setBreadCrumbList(Set<SelectOptionVO> breadCrumbList) {
		this.breadCrumbList = breadCrumbList;
	} 
	
	
	
}
