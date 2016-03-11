package com.itgrids.partyanalyst.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name = "appointment")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Appointment extends BaseModel{
	private Long appointmentId;
	private Long appointmentUserId;
	private Long createdBy;
	private Long updatedBy;
	private Date insertedTime;
	private Date updatedTime;
	private Long appointmentPriorityId;
	private String reason;
	private Long appointmentStatusId;
	private Long  appointmentPreferableTimeId;
	private String isDeleted;
	
	private AppointmentUser appointmentUser;
	private AppointmentPriority appointmentPriority;
	private AppointmentStatus appointmentStatus;
	private AppointmentPreferableTime appointmentPreferableTime;
	private User createdUser;
	private User updateUser;
	 
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "appointment_id", unique = true, nullable = false)
	public Long getAppointmentId() {
		return appointmentId;
	}
	public void setAppointmentId(Long appointmentId) {
		this.appointmentId = appointmentId;
	}
	@Column(name = "appointment_user_id")
	public Long getAppointmentUserId() {
		return appointmentUserId;
	}
	public void setAppointmentUserId(Long appointmentUserId) {
		this.appointmentUserId = appointmentUserId;
	}
	@Column(name = "created_by")
	public Long getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}
	@Column(name = "updated_by")
	public Long getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
	}
	@Column(name = "inserted_time")
	public Date getInsertedTime() {
		return insertedTime;
	}
	public void setInsertedTime(Date insertedTime) {
		this.insertedTime = insertedTime;
	}
	@Column(name = "updated_time")
	public Date getUpdatedTime() {
		return updatedTime;
	}
	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}
	@Column(name = "appointment_priority_id")
	public Long getAppointmentPriorityId() {
		return appointmentPriorityId;
	}
	public void setAppointmentPriorityId(Long appointmentPriorityId) {
		this.appointmentPriorityId = appointmentPriorityId;
	}
	@Column(name = "reason")
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	@Column(name = "appointment_status_id")
	public Long getAppointmentStatusId() {
		return appointmentStatusId;
	}
	public void setAppointmentStatusId(Long appointmentStatusId) {
		this.appointmentStatusId = appointmentStatusId;
	}
	@Column(name = "appointment_preferable_time_id")
	public Long getAppointmentPreferableTimeId() {
		return appointmentPreferableTimeId;
	}
	public void setAppointmentPreferableTimeId(Long appointmentPreferableTimeId) {
		this.appointmentPreferableTimeId = appointmentPreferableTimeId;
	}
	@Column(name = "is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="appointment_user_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public AppointmentUser getAppointmentUser() {
		return appointmentUser;
	}
	public void setAppointmentUser(AppointmentUser appointmentUser) {
		this.appointmentUser = appointmentUser;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="appointment_priority_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public AppointmentPriority getAppointmentPriority() {
		return appointmentPriority;
	}
	public void setAppointmentPriority(AppointmentPriority appointmentPriority) {
		this.appointmentPriority = appointmentPriority;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="appointment_status_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public AppointmentStatus getAppointmentStatus() {
		return appointmentStatus;
	}
	public void setAppointmentStatus(AppointmentStatus appointmentStatus) {
		this.appointmentStatus = appointmentStatus;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="appointment_preferable_time_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public AppointmentPreferableTime getAppointmentPreferableTime() {
		return appointmentPreferableTime;
	}
	public void setAppointmentPreferableTime(
			AppointmentPreferableTime appointmentPreferableTime) {
		this.appointmentPreferableTime = appointmentPreferableTime;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="created_by", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public User getCreatedUser() {
		return createdUser;
	}
	public void setCreatedUser(User createdUser) {
		this.createdUser = createdUser;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="updated_by", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public User getUpdateUser() {
		return updateUser;
	}
	public void setUpdateUser(User updateUser) {
		this.updateUser = updateUser;
	}
}
