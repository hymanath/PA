/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on November 5, 2010
 */
package com.itgrids.partyanalyst.dto;

import java.io.Serializable;

/**
 * @author Sai Krishna
 *
 */
public class LocalUserGroupDetailsVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long localUserGroupId;
	private Long groupCategoryId;
	private String localUserGroupName;
	private String groupCategoryType;
	private String groupDesc;
	private String createdDate;
	private Long groupLocationId;
	private String groupLocation;
	private Long groupMembersCount;
	
	private ResultStatus resultStatus;
	
	
	public Long getLocalUserGroupId() {
		return localUserGroupId;
	}
	public void setLocalUserGroupId(Long localUserGroupId) {
		this.localUserGroupId = localUserGroupId;
	}
	public Long getGroupCategoryId() {
		return groupCategoryId;
	}
	public void setGroupCategoryId(Long groupCategoryId) {
		this.groupCategoryId = groupCategoryId;
	}
	public String getLocalUserGroupName() {
		return localUserGroupName;
	}
	public void setLocalUserGroupName(String localUserGroupName) {
		this.localUserGroupName = localUserGroupName;
	}
	public String getGroupCategoryType() {
		return groupCategoryType;
	}
	public void setGroupCategoryType(String groupCategoryType) {
		this.groupCategoryType = groupCategoryType;
	}
	public String getGroupDesc() {
		return groupDesc;
	}
	public void setGroupDesc(String groupDesc) {
		this.groupDesc = groupDesc;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	public Long getGroupLocationId() {
		return groupLocationId;
	}
	public void setGroupLocationId(Long groupLocationId) {
		this.groupLocationId = groupLocationId;
	}
	public String getGroupLocation() {
		return groupLocation;
	}
	public void setGroupLocation(String groupLocation) {
		this.groupLocation = groupLocation;
	}
	public Long getGroupMembersCount() {
		return groupMembersCount;
	}
	public void setGroupMembersCount(Long groupMembersCount) {
		this.groupMembersCount = groupMembersCount;
	}
	public ResultStatus getResultStatus() {
		return resultStatus;
	}
	public void setResultStatus(ResultStatus resultStatus) {
		this.resultStatus = resultStatus;
	}
	

}
