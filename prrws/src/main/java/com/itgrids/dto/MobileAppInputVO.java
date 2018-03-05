package com.itgrids.dto;

import java.util.List;

public class MobileAppInputVO {
	private Long userId;
	private String userName;
	private String password;
	private String imei;
	private String appVersion;
	private String lattitude;
	private String longitude;
	private String latLongAccuracy;
	private Long workTypeId;
	private Long workId;
	private Long mainWorkId;
	private String fromDate;
	private String toDate;
	private String reportType="";
	private Long userTypeId;
	private List<Long> userIds;
	private Long locationScopeId;
	private List<Long> locationIds;
	private List<Long> workZoneIds;
	
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getImei() {
		return imei;
	}
	public void setImei(String imei) {
		this.imei = imei;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getAppVersion() {
		return appVersion;
	}
	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}
	public String getLattitude() {
		return lattitude;
	}
	public void setLattitude(String lattitude) {
		this.lattitude = lattitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getLatLongAccuracy() {
		return latLongAccuracy;
	}
	public void setLatLongAccuracy(String latLongAccuracy) {
		this.latLongAccuracy = latLongAccuracy;
	}
	public Long getWorkTypeId() {
		return workTypeId;
	}
	public void setWorkTypeId(Long workTypeId) {
		this.workTypeId = workTypeId;
	}
	public Long getWorkId() {
		return workId;
	}
	public void setWorkId(Long workId) {
		this.workId = workId;
	}
	public Long getMainWorkId() {
		return mainWorkId;
	}
	public void setMainWorkId(Long mainWorkId) {
		this.mainWorkId = mainWorkId;
	}
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	public String getReportType() {
		return reportType;
	}
	public void setReportType(String reportType) {
		this.reportType = reportType;
	}
	public Long getUserTypeId() {
		return userTypeId;
	}
	public void setUserTypeId(Long userTypeId) {
		this.userTypeId = userTypeId;
	}
	public List<Long> getUserIds() {
		return userIds;
	}
	public void setUserIds(List<Long> userIds) {
		this.userIds = userIds;
	}
	public List<Long> getLocationIds() {
		return locationIds;
	}
	public void setLocationIds(List<Long> locationIds) {
		this.locationIds = locationIds;
	}
	public Long getLocationScopeId() {
		return locationScopeId;
	}
	public void setLocationScopeId(Long locationScopeId) {
		this.locationScopeId = locationScopeId;
	}
	public List<Long> getWorkZoneIds() {
		return workZoneIds;
	}
	public void setWorkZoneIds(List<Long> workZoneIds) {
		this.workZoneIds = workZoneIds;
	}
	
	
}
