package com.itgrids.partyanalyst.dto;

import java.util.Date;

public class NotificationDeviceVO {
	
	private Long notificationDeviceId;
	private Long registeredId;
	private Long projectId;
	private String longitude;
	private String latitude;
	private String imeiNo;
	private Date insertedTime;
	private Date updatedTime;
	
 	public Date getInsertedTime() {
		return insertedTime;
	}
	public void setInsertedTime(Date insertedTime) {
		this.insertedTime = insertedTime;
	}
	public Date getUpdatedTime() {
		return updatedTime;
	}
	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}
	public Long getNotificationDeviceId() {
		return notificationDeviceId;
	}
	public void setNotificationDeviceId(Long notificationDeviceId) {
		this.notificationDeviceId = notificationDeviceId;
	}
	public Long getRegisteredId() {
		return registeredId;
	}
	public void setRegisteredId(Long registeredId) {
		this.registeredId = registeredId;
	}
	public Long getProjectId() {
		return projectId;
	}
	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getImeiNo() {
		return imeiNo;
	}
	public void setImeiNo(String imeiNo) {
		this.imeiNo = imeiNo;
	}
	
	
}