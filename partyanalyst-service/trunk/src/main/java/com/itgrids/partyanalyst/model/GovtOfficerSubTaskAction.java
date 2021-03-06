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
@Table(name = "govt_officer_sub_task_action")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class GovtOfficerSubTaskAction extends BaseModel implements Serializable {
	
	private static final long serialVersionUID = -843232939544655L;
	private Long govtOfficerSubTaskActionId;
	private Long alertId;
	private Long assignedOfficerId;
	private Long govtOfficerSubTaskId;
	private Long alertSubTaskStatusId;
	private Long alertDepartmentCommentId;
	
	private Long insertedById;
	private Date insertedTime;
	private Long updatedById;
	private Date updatedTime;
	
	private Alert alert;
	private AlertSubTaskStatus alertSubTaskStatus;
	private AlertDepartmentComment alertDepartmentComment;
	private User insertedBy;
	private User updatedBy;
	private AlertAssignedOfficerNew alertAssignedOfficer;
	private GovtAlertSubTask govtAlertSubTask;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "govt_officer_sub_task_action_id", unique = true, nullable = false)
	public Long getGovtOfficerSubTaskActionId() {
		return govtOfficerSubTaskActionId;
	}
	public void setGovtOfficerSubTaskActionId(Long govtOfficerSubTaskActionId) {
		this.govtOfficerSubTaskActionId = govtOfficerSubTaskActionId;
	}
	
	@Column(name="alert_id")
	public Long getAlertId() {
		return alertId;
	}
	public void setAlertId(Long alertId) {
		this.alertId = alertId;
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
	
	@Column(name="alert_sub_task_status_id")
	public Long getAlertSubTaskStatusId() {
		return alertSubTaskStatusId;
	}
	public void setAlertSubTaskStatusId(Long alertSubTaskStatusId) {
		this.alertSubTaskStatusId = alertSubTaskStatusId;
	}
	
	@Column(name="alert_department_comment_id")
	public Long getAlertDepartmentCommentId() {
		return alertDepartmentCommentId;
	}
	public void setAlertDepartmentCommentId(Long alertDepartmentCommentId) {
		this.alertDepartmentCommentId = alertDepartmentCommentId;
	}
	
	@Column(name="inserted_by")
	public Long getInsertedById() {
		return insertedById;
	}
	public void setInsertedById(Long insertedById) {
		this.insertedById = insertedById;
	}
	
	@Column(name="inserted_time")
	public Date getInsertedTime() {
		return insertedTime;
	}
	public void setInsertedTime(Date insertedTime) {
		this.insertedTime = insertedTime;
	}
	
	@Column(name="updated_by")
	public Long getUpdatedById() {
		return updatedById;
	}
	public void setUpdatedById(Long updatedById) {
		this.updatedById = updatedById;
	}
	
	@Column(name="updated_time")
	public Date getUpdatedTime() {
		return updatedTime;
	}
	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}
	
	@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "alert_id" ,insertable = false ,updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Alert getAlert() {
		return alert;
	}
	public void setAlert(Alert alert) {
		this.alert = alert;
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
	
	@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "alert_department_comment_id" ,insertable = false ,updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public AlertDepartmentComment getAlertDepartmentComment() {
		return alertDepartmentComment;
	}
	public void setAlertDepartmentComment(
			AlertDepartmentComment alertDepartmentComment) {
		this.alertDepartmentComment = alertDepartmentComment;
	}
	
	@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "inserted_by" ,insertable = false ,updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public User getInsertedBy() {
		return insertedBy;
	}
	public void setInsertedBy(User insertedBy) {
		this.insertedBy = insertedBy;
	}
	
	@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "updated_by" ,insertable = false ,updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public User getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(User updatedBy) {
		this.updatedBy = updatedBy;
	}
	
	@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "assigned_officer_id" ,insertable = false ,updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public AlertAssignedOfficerNew getAlertAssignedOfficer() {
		return alertAssignedOfficer;
	}
	public void setAlertAssignedOfficer(AlertAssignedOfficerNew alertAssignedOfficer) {
		this.alertAssignedOfficer = alertAssignedOfficer;
	}
	
	@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "govt_officer_sub_task_id" ,insertable = false ,updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public GovtAlertSubTask getGovtAlertSubTask() {
		return govtAlertSubTask;
	}
	public void setGovtAlertSubTask(GovtAlertSubTask govtAlertSubTask) {
		this.govtAlertSubTask = govtAlertSubTask;
	}
	
	
}
