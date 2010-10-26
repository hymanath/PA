package com.itgrids.partyanalyst.dto;

public class UserGroupBasicInfoVO {

	private Long locationsCount;
	private Long groupsCount;
	private String areaType;
	private String categoryName;
	
	public Long getLocationsCount() {
		return locationsCount;
	}
	public void setLocationsCount(Long locationsCount) {
		this.locationsCount = locationsCount;
	}
	public Long getGroupsCount() {
		return groupsCount;
	}
	public void setGroupsCount(Long groupsCount) {
		this.groupsCount = groupsCount;
	}
	public String getAreaType() {
		return areaType;
	}
	public void setAreaType(String areaType) {
		this.areaType = areaType;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	
	
}
