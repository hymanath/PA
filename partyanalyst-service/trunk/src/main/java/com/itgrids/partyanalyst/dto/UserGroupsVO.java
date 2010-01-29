package com.itgrids.partyanalyst.dto;

import java.util.List;

public class UserGroupsVO {
	private List<UserGroupDetailsVO> groupsCreatedByUser;
	private List<UserGroupMembersVO> groupsMembers;

	public List<UserGroupDetailsVO> getGroupsCreatedByUser() {
		return groupsCreatedByUser;
	}

	public void setGroupsCreatedByUser(List<UserGroupDetailsVO> groupsCreatedByUser) {
		this.groupsCreatedByUser = groupsCreatedByUser;
	}
	public List<UserGroupMembersVO> getGroupsMembers() {
		return groupsMembers;
	}

	public void setGroupsMembers(List<UserGroupMembersVO> groupsMembers) {
		this.groupsMembers = groupsMembers;
	} 	
	
}
