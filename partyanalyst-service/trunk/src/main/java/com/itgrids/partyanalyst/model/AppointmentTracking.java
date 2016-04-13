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
@Table(name = "appointment_tracking")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AppointmentTracking extends BaseModel {
	
	private Long appointmentTrackingId;
	private Long appointmentId;
	private Long appointmentActionId;
	private Long appointmentStatusId;
	private Long userId;
	private Date actionTime;
	private String remarks;
	
	private Appointment appointment;
	private AppointmentAction appointmentAction;
	private AppointmentStatus appointmentStatus;
	private User user;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "appointment_tracking_id", unique = true, nullable = false)
	public Long getAppointmentTrackingId() {
		return appointmentTrackingId;
	}
	public void setAppointmentTrackingId(Long appointmentTrackingId) {
		this.appointmentTrackingId = appointmentTrackingId;
	}
	@Column(name = "appointment_id")
	public Long getAppointmentId() {
		return appointmentId;
	}
	public void setAppointmentId(Long appointmentId) {
		this.appointmentId = appointmentId;
	}
	@Column(name = "appointment_action_id")
	public Long getAppointmentActionId() {
		return appointmentActionId;
	}
	public void setAppointmentActionId(Long appointmentActionId) {
		this.appointmentActionId = appointmentActionId;
	}
	@Column(name = "appointment_status_id")
	public Long getAppointmentStatusId() {
		return appointmentStatusId;
	}
	public void setAppointmentStatusId(Long appointmentStatusId) {
		this.appointmentStatusId = appointmentStatusId;
	}
	@Column(name = "user_id")
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	@Column(name = "action_time")
	public Date getActionTime() {
		return actionTime;
	}
	public void setActionTime(Date actionTime) {
		this.actionTime = actionTime;
	}
	@Column(name = "remarks")
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="appointment_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Appointment getAppointment() {
		return appointment;
	}
	public void setAppointment(Appointment appointment) {
		this.appointment = appointment;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="appointment_action_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public AppointmentAction getAppointmentAction() {
		return appointmentAction;
	}
	public void setAppointmentAction(AppointmentAction appointmentAction) {
		this.appointmentAction = appointmentAction;
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
	@JoinColumn(name="user_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

}
