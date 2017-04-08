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
@Table(name="alert_assigned_officer_tracking_new")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AlertAssignedOfficerTrackingNew {

	private Long alertAssignedOfficerTrackingId;
	private Long alertAssignedOfficerId;
	private Long alertId;
	private Long govtDepartmentDesignationOfficerId;
	private Long govtOfficerId;
	private Long govtAlertActionTypeId;
	private Long alertDepartmentCommentId;
	private Long alertDepartmentDocumentId;
	private Date dueDate;
	private Long insertedBy;
	private Long alertStatusId;
	private Long updatedBy;
	private Date insertedTime;
	private Date updatedTime;
	private String isApproved;
	private Long alertSeviorityId;
	
	private AlertSeverity alertSeviority;
	private AlertAssignedOfficerNew alertAssignedOfficer;
	private Alert alert;
	private GovtDepartmentDesignationOfficerNew govtDepartmentDesignationOfficer;
	private GovtOfficerNew govtOfficer;
	private AlertStatus alertStatus;
	private GovtAlertActionType govtAlertActionType;
	private AlertDepartmentCommentNew alertDepartmentComment;
	private AlertDepartmentDocumentNew alertDepartmentDocument;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="alert_assigned_officer_tracking_id", unique=true, nullable=false)
	public Long getAlertAssignedOfficerTrackingId() {
		return alertAssignedOfficerTrackingId;
	}
	public void setAlertAssignedOfficerTrackingId(
			Long alertAssignedOfficerTrackingId) {
		this.alertAssignedOfficerTrackingId = alertAssignedOfficerTrackingId;
	}
	@Column(name = "alert_assigned_officer_id")
	public Long getAlertAssignedOfficerId() {
		return alertAssignedOfficerId;
	}
	public void setAlertAssignedOfficerId(Long alertAssignedOfficerId) {
		this.alertAssignedOfficerId = alertAssignedOfficerId;
	}
	@Column(name = "alert_id")
	public Long getAlertId() {
		return alertId;
	}
	public void setAlertId(Long alertId) {
		this.alertId = alertId;
	}
	@Column(name = "govt_department_designation_officer_id")
	public Long getGovtDepartmentDesignationOfficerId() {
		return govtDepartmentDesignationOfficerId;
	}
	public void setGovtDepartmentDesignationOfficerId(
			Long govtDepartmentDesignationOfficerId) {
		this.govtDepartmentDesignationOfficerId = govtDepartmentDesignationOfficerId;
	}
	@Column(name = "govt_officer_id")
	public Long getGovtOfficerId() {
		return govtOfficerId;
	}
	public void setGovtOfficerId(Long govtOfficerId) {
		this.govtOfficerId = govtOfficerId;
	}
	@Column(name = "govt_alert_action_type_id")
	public Long getGovtAlertActionTypeId() {
		return govtAlertActionTypeId;
	}
	public void setGovtAlertActionTypeId(Long govtAlertActionTypeId) {
		this.govtAlertActionTypeId = govtAlertActionTypeId;
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
	@Column(name = "inserted_by")
	public Long getInsertedBy() {
		return insertedBy;
	}
	public void setInsertedBy(Long insertedBy) {
		this.insertedBy = insertedBy;
	}
	@Column(name = "alert_status_id")
	public Long getAlertStatusId() {
		return alertStatusId;
	}
	public void setAlertStatusId(Long alertStatusId) {
		this.alertStatusId = alertStatusId;
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
	@Column(name = "is_approved")
	public String getIsApproved() {
		return isApproved;
	}
	public void setIsApproved(String isApproved) {
		this.isApproved = isApproved;
	}
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "alert_assigned_officer_id", insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public AlertAssignedOfficerNew getAlertAssignedOfficer() {
		return alertAssignedOfficer;
	}
	public void setAlertAssignedOfficer(AlertAssignedOfficerNew alertAssignedOfficer) {
		this.alertAssignedOfficer = alertAssignedOfficer;
	}
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "alert_id", insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public Alert getAlert() {
		return alert;
	}
	public void setAlert(Alert alert) {
		this.alert = alert;
	}
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "govt_department_designation_officer_id", insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public GovtDepartmentDesignationOfficerNew getGovtDepartmentDesignationOfficer() {
		return govtDepartmentDesignationOfficer;
	}
	public void setGovtDepartmentDesignationOfficer(
			GovtDepartmentDesignationOfficerNew govtDepartmentDesignationOfficer) {
		this.govtDepartmentDesignationOfficer = govtDepartmentDesignationOfficer;
	}
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "govt_officer_id", insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public GovtOfficerNew getGovtOfficer() {
		return govtOfficer;
	}
	public void setGovtOfficer(GovtOfficerNew govtOfficer) {
		this.govtOfficer = govtOfficer;
	}
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "alert_status_id", insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public AlertStatus getAlertStatus() {
		return alertStatus;
	}
	public void setAlertStatus(AlertStatus alertStatus) {
		this.alertStatus = alertStatus;
	}
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "govt_alert_action_type_id", insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public GovtAlertActionType getGovtAlertActionType() {
		return govtAlertActionType;
	}
	public void setGovtAlertActionType(GovtAlertActionType govtAlertActionType) {
		this.govtAlertActionType = govtAlertActionType;
	}
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "alert_department_comment_id", insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public AlertDepartmentCommentNew getAlertDepartmentComment() {
		return alertDepartmentComment;
	}
	public void setAlertDepartmentComment(
			AlertDepartmentCommentNew alertDepartmentComment) {
		this.alertDepartmentComment = alertDepartmentComment;
	}
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "alert_department_document_id", insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public AlertDepartmentDocumentNew getAlertDepartmentDocument() {
		return alertDepartmentDocument;
	}
	public void setAlertDepartmentDocument(
			AlertDepartmentDocumentNew alertDepartmentDocument) {
		this.alertDepartmentDocument = alertDepartmentDocument;
	}
	
	@Column(name = "alert_seviority")
	public Long getAlertSeviorityId() {
		return alertSeviorityId;
	}
	public void setAlertSeviorityId(Long alertSeviorityId) {
		this.alertSeviorityId = alertSeviorityId;
	}
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "alert_seviority", insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public AlertSeverity getAlertSeviority() {
		return alertSeviority;
	}
	public void setAlertSeviority(AlertSeverity alertSeviority) {
		this.alertSeviority = alertSeviority;
	}
	
	
	
	
}
