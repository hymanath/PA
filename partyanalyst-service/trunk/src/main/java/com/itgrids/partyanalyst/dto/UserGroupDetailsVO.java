package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.mysql.jdbc.ResultSetImpl;

public class UserGroupDetailsVO  implements Serializable {

	private String groupName;
	private Long groupId;
	private String groupDesc;
	private List<UserGroupMembersVO> members;
	
	public Long getGroupId() {
		return groupId;
	}
	public String getGroupDesc() {
		return groupDesc;
	}
	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}
	public void setGroupDesc(String groupDesc) {
		this.groupDesc = groupDesc;
	}
	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public List<UserGroupMembersVO> getMembers() {
		return members;
	}
	public void setMembers(List<UserGroupMembersVO> members) {
		this.members = members;
	}
	
}
