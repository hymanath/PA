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
@Table(name = "appointment_time_slot_history")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AppointmentTimeSlotHistory extends BaseModel implements Serializable{
	
	private Long appointmentTimeSlotHistoryId;
	private Long appointmentTimeSlotId;
	private Long appointmentId;
	private Date fromDate;
	private Date toDate;
	private String isDeleted;
	private Long insertedBy;
	private Long updateBy;
	private Date insertedTime;
	private Date updatedTime;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "appointment_time_slot_history_id", unique = true, nullable = false)
	public Long getAppointmentTimeSlotHistoryId() {
		return appointmentTimeSlotHistoryId;
	}
	public void setAppointmentTimeSlotHistoryId(Long appointmentTimeSlotHistoryId) {
		this.appointmentTimeSlotHistoryId = appointmentTimeSlotHistoryId;
	}
	
	@Column(name="appointment_time_slot_id")
	public Long getAppointmentTimeSlotId() {
		return appointmentTimeSlotId;
	}
	public void setAppointmentTimeSlotId(Long appointmentTimeSlotId) {
		this.appointmentTimeSlotId = appointmentTimeSlotId;
	}
	
	@Column(name="appointment_id")
	public Long getAppointmentId() {
		return appointmentId;
	}
	public void setAppointmentId(Long appointmentId) {
		this.appointmentId = appointmentId;
	}
	
	@Column(name="from_date")
	public Date getFromDate() {
		return fromDate;
	}
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}
	
	@Column(name="to_date")
	public Date getToDate() {
		return toDate;
	}
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}
	
	@Column(name="is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	@Column(name="inserted_by")
	public Long getInsertedBy() {
		return insertedBy;
	}
	public void setInsertedBy(Long insertedBy) {
		this.insertedBy = insertedBy;
	}
	
	@Column(name="updated_by")
	public Long getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(Long updateBy) {
		this.updateBy = updateBy;
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
