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
	private Long alertAssignedOfficerId;
	private Long govtAlertSubTaskId;
	private Long govtAlertActionTypeId;
	private Long alertSubTaskStatusId;
	private Long alertDepartmentCommentId;
	private Long alertDepartmentDocumentId;
	private Date dueDate;
	private Date insertedTime;
	private Long insertedById;
	private String isDeleted;
	private Long alertSeverityId;
	
	private AlertSubTaskStatus alertSubTaskStatus;
	private AlertAssignedOfficerNew alertAssignedOfficerNew;
	private GovtAlertSubTask govtAlertSubTask;
	private GovtAlertActionType govtAlertActionType;
	private AlertSeverity alertSeverity;
	private AlertDepartmentCommentNew alertDepartmentComment;
	private AlertDepartmentDocumentNew alertDepartmentDocument;
	private User insertedBy;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "govt_officer_sub_task_tracking_id",unique = true,nullable = false)
	public Long getGovtOfficerSubTaskTrackingId() {
		return govtOfficerSubTaskTrackingId;
	}
	public void setGovtOfficerSubTaskTrackingId(Long govtOfficerSubTaskTrackingId) {
		this.govtOfficerSubTaskTrackingId = govtOfficerSubTaskTrackingId;
	}
	
	@Column(name = "alert_assigned_officer_id")
	public Long getAlertAssignedOfficerId() {
		return alertAssignedOfficerId;
	}
	public void setAlertAssignedOfficerId(Long alertAssignedOfficerId) {
		this.alertAssignedOfficerId = alertAssignedOfficerId;
	}
	
	@Column(name = "govt_alert_sub_task_id")
	public Long getGovtAlertSubTaskId() {
		return govtAlertSubTaskId;
	}
	public void setGovtAlertSubTaskId(Long govtAlertSubTaskId) {
		this.govtAlertSubTaskId = govtAlertSubTaskId;
	}
	
	@Column(name = "govt_alert_action_type_id")
	public Long getGovtAlertActionTypeId() {
		return govtAlertActionTypeId;
	}
	public void setGovtAlertActionTypeId(Long govtAlertActionTypeId) {
		this.govtAlertActionTypeId = govtAlertActionTypeId;
	}
	
	@Column(name = "alert_sub_task_status_id")
	public Long getAlertSubTaskStatusId() {
		return alertSubTaskStatusId;
	}
	public void setAlertSubTaskStatusId(Long alertSubTaskStatusId) {
		this.alertSubTaskStatusId = alertSubTaskStatusId;
	}
	
	@Column(name = "alert_department_comment_id")
	public Long getAlertDepartmentCommentId() {
		return alertDepartmentCommentId;
	}
	public void setAlertDepartmentCommentId(Long alertDepartmentCommentId) {
		this.alertDepartmentCommentId = alertDepartmentCommentId;
	}
	
	@Column(name = "alert_department_document_id")
	public Long getAlertDepartmentDocumentId() {
		return alertDepartmentDocumentId;
	}
	public void setAlertDepartmentDocumentId(Long alertDepartmentDocumentId) {
		this.alertDepartmentDocumentId = alertDepartmentDocumentId;
	}
	
	@Column(name = "due_date")
	public Date getDueDate() {
		return dueDate;
	}
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	
	@Column(name = "inserted_time")
	public Date getInsertedTime() {
		return insertedTime;
	}
	public void setInsertedTime(Date insertedTime) {
		this.insertedTime = insertedTime;
	}
	
	@Column(name = "inserted_by")
	public Long getInsertedById() {
		return insertedById;
	}
	public void setInsertedById(Long insertedById) {
		this.insertedById = insertedById;
	}
	
	@Column(name = "is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	@Column(name = "alert_severity_id")
	public Long getAlertSeverityId() {
		return alertSeverityId;
	}
	public void setAlertSeverityId(Long alertSeverityId) {
		this.alertSeverityId = alertSeverityId;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="alert_sub_task_status_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public AlertSubTaskStatus getAlertSubTaskStatus() {
		return alertSubTaskStatus;
	}
	public void setAlertSubTaskStatus(AlertSubTaskStatus alertSubTaskStatus) {
		this.alertSubTaskStatus = alertSubTaskStatus;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="alert_assigned_officer_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public AlertAssignedOfficerNew getAlertAssignedOfficerNew() {
		return alertAssignedOfficerNew;
	}
	public void setAlertAssignedOfficerNew(
			AlertAssignedOfficerNew alertAssignedOfficerNew) {
		this.alertAssignedOfficerNew = alertAssignedOfficerNew;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="govt_alert_sub_task_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public GovtAlertSubTask getGovtAlertSubTask() {
		return govtAlertSubTask;
	}
	public void setGovtAlertSubTask(GovtAlertSubTask govtAlertSubTask) {
		this.govtAlertSubTask = govtAlertSubTask;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="govt_alert_action_type_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public GovtAlertActionType getGovtAlertActionType() {
		return govtAlertActionType;
	}
	public void setGovtAlertActionType(GovtAlertActionType govtAlertActionType) {
		this.govtAlertActionType = govtAlertActionType;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="alert_severity_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public AlertSeverity getAlertSeverity() {
		return alertSeverity;
	}
	public void setAlertSeverity(AlertSeverity alertSeverity) {
		this.alertSeverity = alertSeverity;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="alert_department_comment_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public AlertDepartmentCommentNew getAlertDepartmentComment() {
		return alertDepartmentComment;
	}
	public void setAlertDepartmentComment(
			AlertDepartmentCommentNew alertDepartmentComment) {
		this.alertDepartmentComment = alertDepartmentComment;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="alert_department_document_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public AlertDepartmentDocumentNew getAlertDepartmentDocument() {
		return alertDepartmentDocument;
	}
	public void setAlertDepartmentDocument(
			AlertDepartmentDocumentNew alertDepartmentDocument) {
		this.alertDepartmentDocument = alertDepartmentDocument;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="inserted_by", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public User getInsertedBy() {
		return insertedBy;
	}
	public void setInsertedBy(User insertedBy) {
		this.insertedBy = insertedBy;
	}
	
}
