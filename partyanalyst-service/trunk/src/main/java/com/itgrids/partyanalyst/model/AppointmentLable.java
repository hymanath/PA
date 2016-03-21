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
@Table(name="appointment_lable")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AppointmentLable extends BaseModel {
	
	private Long appointmentLableId; 
	private String lableName;
	private Date date;
	private Date insertedTime;
	private Date updatedTime;
	private Long insertedBy;
	private Long  updatedBy;
	private String isDeleted;
	private Long appointmentLableStatusId;
	private String deletedRemarks;
	
	private AppointmentUser insertedUser;
	private AppointmentUser updateUser;
	private AppointmentLableStatus appointmentLableStatus;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "appointment_lable_id", unique = true, nullable = false)
	public Long getAppointmentLableId() {
		return appointmentLableId;
	}
	public void setAppointmentLableId(Long appointmentLableId) {
		this.appointmentLableId = appointmentLableId;
	}
	@Column(name = "lable_name")
	public String getLableName() {
		return lableName;
	}
	public void setLableName(String lableName) {
		this.lableName = lableName;
	}
	@Column(name = "date")
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
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
	@Column(name = "inserted_by")
	public Long getInsertedBy() {
		return insertedBy;
	}
	public void setInsertedBy(Long insertedBy) {
		this.insertedBy = insertedBy;
	}
	@Column(name = "updated_by")
	public Long getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
	}
	@Column(name ="is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="inserted_by", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public AppointmentUser getInsertedUser() {
		return insertedUser;
	}
	public void setInsertedUser(AppointmentUser insertedUser) {
		this.insertedUser = insertedUser;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="updated_by", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public AppointmentUser getUpdateUser() {
		return updateUser;
	}
	public void setUpdateUser(AppointmentUser updateUser) {
		this.updateUser = updateUser;
	}
	
	@Column(name="appointment_lable_status_id")
	public Long getAppointmentLableStatusId() {
		return appointmentLableStatusId;
	}
	public void setAppointmentLableStatusId(Long appointmentLableStatusId) {
		this.appointmentLableStatusId = appointmentLableStatusId;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="appointment_lable_status_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public AppointmentLableStatus getAppointmentLableStatus() {
		return appointmentLableStatus;
	}
	public void setAppointmentLableStatus(
			AppointmentLableStatus appointmentLableStatus) {
		this.appointmentLableStatus = appointmentLableStatus;
	}
	
	@Column(name="deleted_remarks")
	public String getDeletedRemarks() {
		return deletedRemarks;
	}
	public void setDeletedRemarks(String deletedRemarks) {
		this.deletedRemarks = deletedRemarks;
	}
	
	
}
