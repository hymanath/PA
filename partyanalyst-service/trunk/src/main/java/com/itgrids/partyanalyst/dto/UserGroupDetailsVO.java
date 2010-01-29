package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.mysql.jdbc.ResultSetImpl;

@SuppressWarnings("serial")
public class UserGroupDetailsVO  implements Serializable {

	private String groupName;
	private Long groupId;
	private String groupDesc;
	private List<UserGroupDetailsVO> userSubGroups;
	
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
	public List<UserGroupDetailsVO> getUserSubGroups() {
		return userSubGroups;
	}
	public void setUserSubGroups(List<UserGroupDetailsVO> userSubGroups) {
		this.userSubGroups = userSubGroups;
	}	
}
