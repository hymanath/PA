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
@Table(name="appointment_label")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AppointmentLabel extends BaseModel {
	
	private Long appointmentLabelId; 
	private String labelName;
	private Date date;
	private Date insertedTime;
	private Date updatedTime;
	private Long insertedBy;
	private Long  updatedBy;
	private String isDeleted;
	private Long appointmentLabelStatusId;
	private String deletedRemarks;
	
	private AppointmentUser insertedUser;
	private AppointmentUser updateUser;
	private AppointmentLabelStatus appointmentLabelStatus;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "appointment_label_id", unique = true, nullable = false)
	public Long getAppointmentLabelId() {
		return appointmentLabelId;
	}
	public void setAppointmentLabelId(Long appointmentLabelId) {
		this.appointmentLabelId = appointmentLabelId;
	}
	@Column(name = "label_name")
	public String getLabelName() {
		return labelName;
	}
	public void setLabelName(String labelName) {
		this.labelName = labelName;
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
	
	@Column(name="appointment_label_status_id")
	public Long getAppointmentLabelStatusId() {
		return appointmentLabelStatusId;
	}
	public void setAppointmentLabelStatusId(Long appointmentLabelStatusId) {
		this.appointmentLabelStatusId = appointmentLabelStatusId;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="appointment_label_status_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public AppointmentLabelStatus getAppointmentLabelStatus() {
		return appointmentLabelStatus;
	}
	public void setAppointmentLabelStatus(
			AppointmentLabelStatus appointmentLabelStatus) {
		this.appointmentLabelStatus = appointmentLabelStatus;
	}
	
	@Column(name="deleted_remarks")
	public String getDeletedRemarks() {
		return deletedRemarks;
	}
	public void setDeletedRemarks(String deletedRemarks) {
		this.deletedRemarks = deletedRemarks;
	}
	
	
}
