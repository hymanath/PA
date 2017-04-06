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

import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name = "govt_officer_sub_task_tracking")
public class GovtOfficerSubTaskTracking {

	private Long govtOfficerSubTaskTrackingId;
	private Long assignedOfficerId;
	private Long govtOfficerSubTaskId;
	private Long govtSubTaskActionTypeId;
	private Long alertSubTaskStatusId;
	private AlertSeverity alertSeverity;
	private Date dueDate;
	private Date insertedTime;
	private String isDeleted;
	private Long alertSeverityId;
	
	private GovtSubTaskActionType govtSubTaskActionType;
	private AlertSubTaskStatus alertSubTaskStatus;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "govt_officer_sub_task_tracking_id",unique = true,nullable = false)
	public Long getGovtOfficerSubTaskTrackingId() {
		return govtOfficerSubTaskTrackingId;
	}
	public void setGovtOfficerSubTaskTrackingId(Long govtOfficerSubTaskTrackingId) {
		this.govtOfficerSubTaskTrackingId = govtOfficerSubTaskTrackingId;
	}
	
	@Column(name="assigned_officer_id")
	public Long getAssignedOfficerId() {
		return assignedOfficerId;
	}
	public void setAssignedOfficerId(Long assignedOfficerId) {
		this.assignedOfficerId = assignedOfficerId;
	}
	
	@Column(name="govt_officer_sub_task_id")
	public Long getGovtOfficerSubTaskId() {
		return govtOfficerSubTaskId;
	}
	public void setGovtOfficerSubTaskId(Long govtOfficerSubTaskId) {
		this.govtOfficerSubTaskId = govtOfficerSubTaskId;
	}
	
	@Column(name="govt_sub_task_action_type_id")
	public Long getGovtSubTaskActionTypeId() {
		return govtSubTaskActionTypeId;
	}
	public void setGovtSubTaskActionTypeId(Long govtSubTaskActionTypeId) {
		this.govtSubTaskActionTypeId = govtSubTaskActionTypeId;
	}
	
	@Column(name="alert_sub_task_status_id")
	public Long getAlertSubTaskStatusId() {
		return alertSubTaskStatusId;
	}
	public void setAlertSubTaskStatusId(Long alertSubTaskStatusId) {
		this.alertSubTaskStatusId = alertSubTaskStatusId;
	}
	
	@Column(name="alert_severity_id")
	public Long getAlertSeverityId() {
		return alertSeverityId;
	}
	public void setAlertSeverityId(Long alertSeverityId) {
		this.alertSeverityId = alertSeverityId;
	}
	@Column(name="due_date")
	public Date getDueDate() {
		return dueDate;
	}
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	
	@Column(name="inserted_by")
	public Date getInsertedTime() {
		return insertedTime;
	}
	public void setInsertedTime(Date insertedTime) {
		this.insertedTime = insertedTime;
	}
	
	@Column(name="is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "alert_severity_id" ,insertable = false ,updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public AlertSeverity getAlertSeverity() {
		return alertSeverity;
	}
	public void setAlertSeverity(AlertSeverity alertSeverity) {
		this.alertSeverity = alertSeverity;
	}
	
	@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "govt_sub_task_action_type_id" ,insertable = false ,updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public GovtSubTaskActionType getGovtSubTaskActionType() {
		return govtSubTaskActionType;
	}
	public void setGovtSubTaskActionType(GovtSubTaskActionType govtSubTaskActionType) {
		this.govtSubTaskActionType = govtSubTaskActionType;
	}
	
	@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "alert_sub_task_status_id" ,insertable = false ,updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public AlertSubTaskStatus getAlertSubTaskStatus() {
		return alertSubTaskStatus;
	}
	public void setAlertSubTaskStatus(AlertSubTaskStatus alertSubTaskStatus) {
		this.alertSubTaskStatus = alertSubTaskStatus;
	}
	

}
