package com.itgrids.partyanalyst.model;

import java.io.Serializable;
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
@Table(name = "alert_assigned_officer_action")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AlertAssignedOfficerAction extends BaseModel implements Serializable{

	private Long alertAssignedOfficerActionId;
	private Long alertAssignedOfficerId;
	private Long alertId;
	private Long govtOfficerId;
	private Long alertStatusId;
	private Long alertDepartmentCommentId;
	private Long alertDepartmentDocumentId;
	private Long insertedBy;
	private Long updatedBy;
	private Date insertedTime;
	private Date updatedTime;
	private String isDeleted;
	
	private AlertAssignedOfficer alertAssignedOfficer;
	private Alert alert;
	private GovtOfficer govtOfficer;
	private AlertStatus alertStatus;
	private AlertDepartmentComment alertDepartmentComment;
	private AlertDepartmentDocument alertDepartmentDocument;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "alert_assigned_officer_action_id", unique = true, nullable = false)
	public Long getAlertAssignedOfficerActionId() {
		return alertAssignedOfficerActionId;
	}
	public void setAlertAssignedOfficerActionId(Long alertAssignedOfficerActionId) {
		this.alertAssignedOfficerActionId = alertAssignedOfficerActionId;
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
	
	@Column(name = "govt_officer_id")
	public Long getGovtOfficerId() {
		return govtOfficerId;
	}
	public void setGovtOfficerId(Long govtOfficerId) {
		this.govtOfficerId = govtOfficerId;
	}
	
	@Column(name = "alert_status_id")
	public Long getAlertStatusId() {
		return alertStatusId;
	}
	public void setAlertStatusId(Long alertStatusId) {
		this.alertStatusId = alertStatusId;
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
	@JoinColumn(name="alert_assigned_officer_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public AlertAssignedOfficer getAlertAssignedOfficer() {
		return alertAssignedOfficer;
	}
	public void setAlertAssignedOfficer(AlertAssignedOfficer alertAssignedOfficer) {
		this.alertAssignedOfficer = alertAssignedOfficer;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="alert_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Alert getAlert() {
		return alert;
	}
	public void setAlert(Alert alert) {
		this.alert = alert;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="govt_officer_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public GovtOfficer getGovtOfficer() {
		return govtOfficer;
	}
	public void setGovtOfficer(GovtOfficer govtOfficer) {
		this.govtOfficer = govtOfficer;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="alert_status_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public AlertStatus getAlertStatus() {
		return alertStatus;
	}
	public void setAlertStatus(AlertStatus alertStatus) {
		this.alertStatus = alertStatus;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="alert_department_comment_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public AlertDepartmentComment getAlertDepartmentComment() {
		return alertDepartmentComment;
	}
	public void setAlertDepartmentComment(
			AlertDepartmentComment alertDepartmentComment) {
		this.alertDepartmentComment = alertDepartmentComment;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="alert_department_document_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public AlertDepartmentDocument getAlertDepartmentDocument() {
		return alertDepartmentDocument;
	}
	public void setAlertDepartmentDocument(
			AlertDepartmentDocument alertDepartmentDocument) {
		this.alertDepartmentDocument = alertDepartmentDocument;
	}
}
