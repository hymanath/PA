package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings("serial")
public class UserGroupDetailsVO  implements Serializable {

	private String groupName;
	private Long groupId;
	private String groupDesc;
	private String createdDate;
	private Long createdUserId;
	private Long staticGroupId;
	private Long parentGroupId;
	private String parentGroupName;
	private String noOfPersons;
	private String status;
	private String categoryId;;
	private String categoryName;
	private String categoryType;
	private List<UserGroupMembersVO> userGroupMembersVO;
	private List<UserGroupDetailsVO> userSubGroups;
	private Long myGroupId;
	private ResultStatus rs;
	private String locationInfo;
	
	public Long getGroupId() {
		return groupId;
	}
	public String getGroupDesc() {
		return groupDesc;
	}
	
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	public Long getCreatedUserId() {
		return createdUserId;
	}
	public void setCreatedUserId(Long createdUserId) {
		this.createdUserId = createdUserId;
	}
	public Long getParentGroupId() {
		return parentGroupId;
	}
	public void setParentGroupId(Long parentGroupId) {
		this.parentGroupId = parentGroupId;
	}
	public String getParentGroupName() {
		return parentGroupName;
	}
	public void setParentGroupName(String parentGroupName) {
		this.parentGroupName = parentGroupName;
	}
	public List<UserGroupMembersVO> getUserGroupMembersVO() {
		return userGroupMembersVO;
	}
	public void setUserGroupMembersVO(List<UserGroupMembersVO> userGroupMembersVO) {
		this.userGroupMembersVO = userGroupMembersVO;
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
	public Long getStaticGroupId() {
		return staticGroupId;
	}
	public void setStaticGroupId(Long staticGroupId) {
		this.staticGroupId = staticGroupId;
	}
	public String getNoOfPersons() {
		return noOfPersons;
	}
	public void setNoOfPersons(String noOfPersons) {
		this.noOfPersons = noOfPersons;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getCategoryType() {
		return categoryType;
	}
	public void setCategoryType(String categoryType) {
		this.categoryType = categoryType;
	}
	public Long getMyGroupId() {
		return myGroupId;
	}
	public void setMyGroupId(Long myGroupId) {
		this.myGroupId = myGroupId;
	}
	public void setRs(ResultStatus rs) {
		this.rs = rs;
	}
	public ResultStatus getRs() {
		return rs;
	}
	public String getLocationInfo() {
		return locationInfo;
	}
	public void setLocationInfo(String locationInfo) {
		this.locationInfo = locationInfo;
	}	
}
