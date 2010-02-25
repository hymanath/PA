package com.itgrids.partyanalyst.dto;

import java.util.List;

public class GroupsDetailsForUser {
	private Long userId;
	private Long staticGroupId;
	private String staticGroupName;
	private int numberOfGroups;
	private List<UserGroupDetailsVO> subGroupsCreatedByUser;
	private List<UserGroupMembersVO> groupsMembers;
	
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getStaticGroupId() {
		return staticGroupId;
	}

	public void setStaticGroupId(Long staticGroupId) {
		this.staticGroupId = staticGroupId;
	}

	public String getStaticGroupName() {
		return staticGroupName;
	}

	public void setStaticGroupName(String staticGroupName) {
		this.staticGroupName = staticGroupName;
	}

	public List<UserGroupDetailsVO> getSubGroupsCreatedByUser() {
		return subGroupsCreatedByUser;
	}

	public void setSubGroupsCreatedByUser(
			List<UserGroupDetailsVO> subGroupsCreatedByUser) {
		this.subGroupsCreatedByUser = subGroupsCreatedByUser;
	}

	public List<UserGroupMembersVO> getGroupsMembers() {
		return groupsMembers;
	}

	public void setGroupsMembers(List<UserGroupMembersVO> groupsMembers) {
		this.groupsMembers = groupsMembers;
	}

	public int getNumberOfGroups() {
		return numberOfGroups;
	}

	public void setNumberOfGroups(int numberOfGroups) {
		this.numberOfGroups = numberOfGroups;
	} 
	
	
}
