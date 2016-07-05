package com.itgrids.partyanalyst.dto;

public class AlertDataVO {
	private String severity;
	private String desc;
	private String alertType;
	private Long count=0l;
	private String userType;
	private String date;
	private String status;
	private Long id;
	private LocationVO locationVO;
	private Long regionScopeId;
	private String regionScope;
	private Long statusId;
	
	
	public Long getStatusId() {
		return statusId;
	}
	public void setStatusId(Long statusId) {
		this.statusId = statusId;
	}
	public String getSeverity() {
		return severity;
	}
	public void setSeverity(String severity) {
		this.severity = severity;
	}
	public String getDesc() {
		return desc;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getAlertType() {
		return alertType;
	}
	public void setAlertType(String alertType) {
		this.alertType = alertType;
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public LocationVO getLocationVO() {
		return locationVO;
	}
	public void setLocationVO(LocationVO locationVO) {
		this.locationVO = locationVO;
	}
	public Long getRegionScopeId() {
		return regionScopeId;
	}
	public void setRegionScopeId(Long regionScopeId) {
		this.regionScopeId = regionScopeId;
	}
	public String getRegionScope() {
		return regionScope;
	}
	public void setRegionScope(String regionScope) {
		this.regionScope = regionScope;
	}
	
	
	
	
	
}
