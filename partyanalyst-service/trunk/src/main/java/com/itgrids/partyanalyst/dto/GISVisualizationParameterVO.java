package com.itgrids.partyanalyst.dto;

public class GISVisualizationParameterVO implements java.io.Serializable{

	private Long stateId;
	private String parentLocationType;
	private Long parentLocationTypeId;
	private String childLocationType;
	private Long childLocationTypeId;
	private String areaType;
	private String startDate;
	private String endDate;
	private Long districtId;
	
	
	public Long getDistrictId() {
		return districtId;
	}
	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}
	public String getAreaType() {
		return areaType;
	}
	public void setAreaType(String areaType) {
		this.areaType = areaType;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public Long getStateId() {
		return stateId;
	}
	public void setStateId(Long stateId) {
		this.stateId = stateId;
	}
	public String getParentLocationType() {
		return parentLocationType;
	}
	public void setParentLocationType(String parentLocationType) {
		this.parentLocationType = parentLocationType;
	}
	public Long getParentLocationTypeId() {
		return parentLocationTypeId;
	}
	public void setParentLocationTypeId(Long parentLocationTypeId) {
		this.parentLocationTypeId = parentLocationTypeId;
	}
	public String getChildLocationType() {
		return childLocationType;
	}
	public void setChildLocationType(String childLocationType) {
		this.childLocationType = childLocationType;
	}
	public Long getChildLocationTypeId() {
		return childLocationTypeId;
	}
	public void setChildLocationTypeId(Long childLocationTypeId) {
		this.childLocationTypeId = childLocationTypeId;
	}
	
}
