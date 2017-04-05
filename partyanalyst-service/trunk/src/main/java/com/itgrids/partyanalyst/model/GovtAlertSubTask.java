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
@Table(name = "govt_alert_sub_task")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class GovtAlertSubTask {

	private Long govtAlertSubTaskId;
	private Long alertId;
	private String title;
	private String description;
	private Long govtDepartmentDesignationOfficerId;
	private Long alertAssignedOfficerId;
    private Long subTaskGovtOfficerId;
    private Long alertSeverityId;
    private Date dueDate;
    private Long alertSubTaskStatusId;
    private String isApproved;
    private String isDeleted;
    private Long createdBy;
    private Date createdTime;
    private Long updatedBy;
    private Date updatedTime;
	
	
	private Alert alert;
	private GovtDepartmentDesignationOfficerNew govtDepartmentDesignationOfficer;
	private AlertAssignedOfficerNew alertAssignedOfficer;
	private AlertSeverity alertSeverity;
	private User createdUser;
	private User updatedUser;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "govt_alert_sub_task_id", unique = true, nullable = false)
	public Long getGovtAlertSubTaskId() {
		return govtAlertSubTaskId;
	}
	public void setGovtAlertSubTaskId(Long govtAlertSubTaskId) {
		this.govtAlertSubTaskId = govtAlertSubTaskId;
	}
	@Column(name = "alert_id")
	public Long getAlertId() {
		return alertId;
	}
	public void setAlertId(Long alertId) {
		this.alertId = alertId;
	}
	@Column(name = "title")
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@Column(name = "description")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Column(name = "govt_department_designation_officer_id")
	public Long getGovtDepartmentDesignationOfficerId() {
		return govtDepartmentDesignationOfficerId;
	}
	public void setGovtDepartmentDesignationOfficerId(
			Long govtDepartmentDesignationOfficerId) {
		this.govtDepartmentDesignationOfficerId = govtDepartmentDesignationOfficerId;
	}
	@Column(name = "alert_assigned_officer_id")
	public Long getAlertAssignedOfficerId() {
		return alertAssignedOfficerId;
	}
	public void setAlertAssignedOfficerId(Long alertAssignedOfficerId) {
		this.alertAssignedOfficerId = alertAssignedOfficerId;
	}
	@Column(name = "sub_task_govt_officer_id")
	public Long getSubTaskGovtOfficerId() {
		return subTaskGovtOfficerId;
	}
	public void setSubTaskGovtOfficerId(Long subTaskGovtOfficerId) {
		this.subTaskGovtOfficerId = subTaskGovtOfficerId;
	}
	@Column(name = "alert_severity_id")
	public Long getAlertSeverityId() {
		return alertSeverityId;
	}
	public void setAlertSeverityId(Long alertSeverityId) {
		this.alertSeverityId = alertSeverityId;
	}
	@Column(name = "due_date")
	public Date getDueDate() {
		return dueDate;
	}
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	@Column(name = "alert_sub_task_status_id")
	public Long getAlertSubTaskStatusId() {
		return alertSubTaskStatusId;
	}
	public void setAlertSubTaskStatusId(Long alertSubTaskStatusId) {
		this.alertSubTaskStatusId = alertSubTaskStatusId;
	}
	@Column(name = "is_approved")
	public String getIsApproved() {
		return isApproved;
	}
	public void setIsApproved(String isApproved) {
		this.isApproved = isApproved;
	}
	@Column(name = "is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	@Column(name = "created_by")
	public Long getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}
	@Column(name = "created_time")
	public Date getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	@Column(name = "updated_by")
	public Long getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
	}
	@Column(name = "updated_time")
	public Date getUpdatedTime() {
		return updatedTime;
	}
	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
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
	@JoinColumn(name="govt_department_designation_officer_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public GovtDepartmentDesignationOfficerNew getGovtDepartmentDesignationOfficer() {
		return govtDepartmentDesignationOfficer;
	}
	public void setGovtDepartmentDesignationOfficer(
			GovtDepartmentDesignationOfficerNew govtDepartmentDesignationOfficer) {
		this.govtDepartmentDesignationOfficer = govtDepartmentDesignationOfficer;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="alert_assigned_officer_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public AlertAssignedOfficerNew getAlertAssignedOfficer() {
		return alertAssignedOfficer;
	}
	public void setAlertAssignedOfficer(AlertAssignedOfficerNew alertAssignedOfficer) {
		this.alertAssignedOfficer = alertAssignedOfficer;
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
	public User getUpdatedUser() {
		return updatedUser;
	}
	public void setUpdatedUser(User updatedUser) {
		this.updatedUser = updatedUser;
	}
	
	
}
