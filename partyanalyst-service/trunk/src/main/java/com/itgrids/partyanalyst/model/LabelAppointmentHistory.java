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
@Table(name="label_appointment_history")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class LabelAppointmentHistory extends BaseModel {
	private Long labelAppointmentHistoryId;
	private Long appointmentLabelId;
	private Long labelAppointmentId;
	private Long appointmentId;
	private Long labelStatusId;
	private Long createdBy;
	private Long updatedBy;
	private Date insertedTime;
	private Date updatedTime;
	private String isDeleted;
	
	private AppointmentLable appointmentLable;
	private LabelAppointment labelAppointment;
	private Appointment appointment;
	private AppointmentLableStatus appointmentLableStatus;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "label_appointment_history_id", unique = true, nullable = false)
	public Long getLabelAppointmentHistoryId() {
		return labelAppointmentHistoryId;
	}
	public void setLabelAppointmentHistoryId(Long labelAppointmentHistoryId) {
		this.labelAppointmentHistoryId = labelAppointmentHistoryId;
	}
	@Column(name = "appointment_label_id")
	public Long getAppointmentLabelId() {
		return appointmentLabelId;
	}
	public void setAppointmentLabelId(Long appointmentLabelId) {
		this.appointmentLabelId = appointmentLabelId;
	}
	@Column(name = "label_appointment_id")
	public Long getLabelAppointmentId() {
		return labelAppointmentId;
	}
	public void setLabelAppointmentId(Long labelAppointmentId) {
		this.labelAppointmentId = labelAppointmentId;
	}
	@Column(name = "appointment_id")
	public Long getAppointmentId() {
		return appointmentId;
	}
	public void setAppointmentId(Long appointmentId) {
		this.appointmentId = appointmentId;
	}
	@Column(name = "label_status_id")
	public Long getLabelStatusId() {
		return labelStatusId;
	}
	public void setLabelStatusId(Long labelStatusId) {
		this.labelStatusId = labelStatusId;
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
	@Column(name = "is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="appointment_label_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public AppointmentLable getAppointmentLable() {
		return appointmentLable;
	}
	public void setAppointmentLable(AppointmentLable appointmentLable) {
		this.appointmentLable = appointmentLable;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="label_appointment_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public LabelAppointment getLabelAppointment() {
		return labelAppointment;
	}
	public void setLabelAppointment(LabelAppointment labelAppointment) {
		this.labelAppointment = labelAppointment;
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
	@JoinColumn(name="label_status_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public AppointmentLableStatus getAppointmentLableStatus() {
		return appointmentLableStatus;
	}
	public void setAppointmentLableStatus(
			AppointmentLableStatus appointmentLableStatus) {
		this.appointmentLableStatus = appointmentLableStatus;
	}
	
	
	
}
