package com.itgrids.partyanalyst.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name="notification_device")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class NotificationDevice extends BaseModel implements Serializable{
	
	private Long notificationDeviceId;
	private Long registeredId;
	private Long projectId;
	private String longitude;
	private String latitude;
	private String imeiNo;
	private Date insertedTime;
	private Date updatedTime;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "notification_device_id", unique = true, nullable = false)
	public Long getNotificationDeviceId() {
		return notificationDeviceId;
	}
	public void setNotificationDeviceId(Long notificationDeviceId) {
		this.notificationDeviceId = notificationDeviceId;
	}
	@Column(name="registered_id")
	public Long getRegisteredId() {
		return registeredId;
	}
	public void setRegisteredId(Long registeredId) {
		this.registeredId = registeredId;
	}
	@Column(name="project_id")
	public Long getProjectId() {
		return projectId;
	}
	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}
	@Column(name="longitude")
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	@Column(name="latitude")
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	@Column(name="imei_no")
	public String getImeiNo() {
		return imeiNo;
	}
	public void setImeiNo(String imeiNo) {
		this.imeiNo = imeiNo;
	}
	@Column(name="inserted_time")
	public Date getInsertedTime() {
		return insertedTime;
	}
	public void setInsertedTime(Date insertedTime) {
		this.insertedTime = insertedTime;
	}
	@Column(name="updated_time")
	public Date getUpdatedTime() {
		return updatedTime;
	}
	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}
	
		
}
