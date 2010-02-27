package com.itgrids.partyanalyst.dto;

import java.util.List;

public class UserGroupsVO {
	
	private List <GroupsDetailsForUserVO> groupsDetailsForUser;
	 
	public List<GroupsDetailsForUserVO> getGroupsDetailsForUser() {
		return groupsDetailsForUser;
	}

	public void setGroupsDetailsForUser(
			List<GroupsDetailsForUserVO> groupsDetailsForUser) {
		this.groupsDetailsForUser = groupsDetailsForUser;
	}	
 	
}
