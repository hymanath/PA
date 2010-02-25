package com.itgrids.partyanalyst.dto;

import java.util.List;

public class UserGroupsVO {
	
	private List <GroupsDetailsForUser> groupsDetailsForUser;
	 
	public List<GroupsDetailsForUser> getGroupsDetailsForUser() {
		return groupsDetailsForUser;
	}

	public void setGroupsDetailsForUser(
			List<GroupsDetailsForUser> groupsDetailsForUser) {
		this.groupsDetailsForUser = groupsDetailsForUser;
	}	
 	
}
