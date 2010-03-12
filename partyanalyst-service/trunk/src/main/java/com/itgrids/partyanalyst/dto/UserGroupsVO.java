package com.itgrids.partyanalyst.dto;

import java.util.List;

public class UserGroupsVO {
	
	private List <GroupsDetailsForUserVO> groupsDetailsForUser;
	private List<UserGroupMembersVO> userGroupMembersList;
	 
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

 	
}
