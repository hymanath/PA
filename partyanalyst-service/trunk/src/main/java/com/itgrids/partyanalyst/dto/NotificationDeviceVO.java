package com.itgrids.partyanalyst.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NotificationDeviceVO {
	
	private Long notificationDeviceId;
	private Long registeredId;
	private Long projectId;
	private String longitude;
	private String latitude;
	private String imeiNo;
	private Date insertedTime;
	private Date updatedTime;
	private List<NotificationDeviceVO> activeNotofocations = new ArrayList<NotificationDeviceVO>(0);
	private Long id;
	private Long notificationTypeId;
	private String notificationType;
	private Long notificationId;
	private String notification;
	private Long lastNotificationId;
	private String status;
	
	public NotificationDeviceVO(){
		
	}
	public NotificationDeviceVO(String status){
		this.status = status;
	}
	public Long getLastNotificationId() {
		return lastNotificationId;
	}
	public void setLastNotificationId(Long lastNotificationId) {
		this.lastNotificationId = lastNotificationId;
	}
	public Long getNotificationTypeId() {
		return notificationTypeId;
	}
	public void setNotificationTypeId(Long notificationTypeId) {
		this.notificationTypeId = notificationTypeId;
	}
	public String getNotificationType() {
		return notificationType;
	}
	public void setNotificationType(String notificationType) {
		this.notificationType = notificationType;
	}
	public Long getNotificationId() {
		return notificationId;
	}
	public void setNotificationId(Long notificationId) {
		this.notificationId = notificationId;
	}
	public String getNotification() {
		return notification;
	}
	public void setNotification(String notification) {
		this.notification = notification;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

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
	public List<NotificationDeviceVO> getActiveNotofocations() {
		return activeNotofocations;
	}
	public void setActiveNotofocations(
			List<NotificationDeviceVO> activeNotofocations) {
		this.activeNotofocations = activeNotofocations;
	}
	
}