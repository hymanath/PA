package com.itgrids.partyanalyst.dto;

import java.util.List;

public class LocalUserGroupsInfoVO extends ResultStatus{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8528139338029236944L;
	private List<UserGroupBasicInfoVO> locationsWiseGroupInfo;
	private String groupCategoryName;
	private Long groupsCount;
	private Long categoryId;
	private String description;
	public List<UserGroupBasicInfoVO> getLocationsWiseGroupInfo() {
		return locationsWiseGroupInfo;
	}
	public void setLocationsWiseGroupInfo(
			List<UserGroupBasicInfoVO> locationsWiseGroupInfo) {
		this.locationsWiseGroupInfo = locationsWiseGroupInfo;
	}
	public String getGroupCategoryName() {
		return groupCategoryName;
	}
	public void setGroupCategoryName(String groupCategoryName) {
		this.groupCategoryName = groupCategoryName;
	}
	public Long getGroupsCount() {
		return groupsCount;
	}
	public void setGroupsCount(Long groupsCount) {
		this.groupsCount = groupsCount;
	}
	public Long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
	
	

}
