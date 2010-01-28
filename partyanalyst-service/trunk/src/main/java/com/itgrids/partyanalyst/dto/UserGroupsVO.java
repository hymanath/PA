package com.itgrids.partyanalyst.dto;

import java.util.List;

public class UserGroupsVO {
	private List<UserGroupDetailsVO> groupsCreatedByUser;

	public List<UserGroupDetailsVO> getGroupsCreatedByUser() {
		return groupsCreatedByUser;
	}

	public void setGroupsCreatedByUser(List<UserGroupDetailsVO> groupsCreatedByUser) {
		this.groupsCreatedByUser = groupsCreatedByUser;
	} 	
}
