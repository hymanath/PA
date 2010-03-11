/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on March 08,2010
 */
package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.List;

public class GroupsBasicInfoVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6127973515798917493L;
	
	private Long groupId;
	private String groupName;
	private String desc;
	private String createdDate;
	private Long membersCount;
	private Long subGroupsCount;
	
	private List<UserGroupMembersVO> userGroupMembers;
	
	
    //getters and setters
	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public Long getMembersCount() {
		return membersCount;
	}

	public void setMembersCount(Long membersCount) {
		this.membersCount = membersCount;
	}

	public Long getSubGroupsCount() {
		return subGroupsCount;
	}

	public void setSubGroupsCount(Long subGroupsCount) {
		this.subGroupsCount = subGroupsCount;
	}

	public List<UserGroupMembersVO> getUserGroupMembers() {
		return userGroupMembers;
	}

	public void setUserGroupMembers(List<UserGroupMembersVO> userGroupMembers) {
		this.userGroupMembers = userGroupMembers;
	}

}
