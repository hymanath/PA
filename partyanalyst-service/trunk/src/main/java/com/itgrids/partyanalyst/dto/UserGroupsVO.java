package com.itgrids.partyanalyst.dto;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class UserGroupsVO {
	
	private List <GroupsDetailsForUserVO> groupsDetailsForUser;
	private List<UserGroupMembersVO> userGroupMembersList;
	private List<SelectOptionVO> systemGroupsList;
	private List<SelectOptionVO> myGroupsList;
	private Set<SelectOptionVO> systemGroupsBCList;
	private Set<SelectOptionVO> myGroupsBCList; 
	private UserGroupDetailsVO userGroupDetailsVO;
	private boolean groupAlreadyExists;
	private UserGroupMembersVO userGroupMembersVO;
	
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

	public Set<SelectOptionVO> getSystemGroupsBCList() {
		return systemGroupsBCList;
	}

	public void setSystemGroupsBCList(Set<SelectOptionVO> systemGroupsBCList) {
		this.systemGroupsBCList = systemGroupsBCList;
	}
	
	public Set<SelectOptionVO> getMyGroupsBCList() {
		return myGroupsBCList;
	}

	public void setMyGroupsBCList(Set<SelectOptionVO> myGroupsBCList) {
		this.myGroupsBCList = myGroupsBCList;
	}

	public UserGroupDetailsVO getUserGroupDetailsVO() {
		return userGroupDetailsVO;
	}

	public void setUserGroupDetailsVO(UserGroupDetailsVO userGroupDetailsVO) {
		this.userGroupDetailsVO = userGroupDetailsVO;
	}

	public boolean isGroupAlreadyExists() {
		return groupAlreadyExists;
	}

	public void setGroupAlreadyExists(boolean groupAlreadyExists) {
		this.groupAlreadyExists = groupAlreadyExists;
	}

	public UserGroupMembersVO getUserGroupMembersVO() {
		return userGroupMembersVO;
	}

	public void setUserGroupMembersVO(UserGroupMembersVO userGroupMembersVO) {
		this.userGroupMembersVO = userGroupMembersVO;
	}
	
	
	
	
	

		

	
	
	
}
