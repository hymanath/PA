package com.itgrids.partyanalyst.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "notification_type")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class NotificationType extends BaseModel implements Serializable{
	
	private Long notificationTypeId;
	private String notificationType;
	private Long isActive;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "notification_type_id", unique = true, nullable = false)
	public Long getNotificationTypeId() {
		return notificationTypeId;
	}
	public void setNotificationTypeId(Long notificationTypeId) {
		this.notificationTypeId = notificationTypeId;
	}
	@Column(name="notification_type")
	public String getNotificationType() {
		return notificationType;
	}
	public void setNotificationType(String notificationType) {
		this.notificationType = notificationType;
	}
	@Column(name="is_active")
	public Long getIsActive() {
		return isActive;
	}
	public void setIsActive(Long isActive) {
		this.isActive = isActive;
	}
	
}
