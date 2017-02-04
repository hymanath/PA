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
@Table(name = "alert_department_tracking")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AlertDepartmentTracking extends BaseModel implements Serializable{

	private Long alertDepartmentTrackingId;
	private Long alertDepartmentId;
	private Long alertDepartmentStatusId;
	private Long alertDepartmentCommentId;
	private Long alertTrackingActionId;
	private Long insertedBy;
	private Date insertedTime;
	
	private AlertDepartment alertDepartment;
	private AlertDepartmentStatus alertDepartmentStatus;
	private AlertDepartmentComment alertDepartmentComment;
	private AlertTrackingAction alertTrackingAction;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "alert_department_tracking_id", unique = true, nullable = false)
	public Long getAlertDepartmentTrackingId() {
		return alertDepartmentTrackingId;
	}
	public void setAlertDepartmentTrackingId(Long alertDepartmentTrackingId) {
		this.alertDepartmentTrackingId = alertDepartmentTrackingId;
	}
	
	@Column(name = "alert_department_id")
	public Long getAlertDepartmentId() {
		return alertDepartmentId;
	}
	public void setAlertDepartmentId(Long alertDepartmentId) {
		this.alertDepartmentId = alertDepartmentId;
	}
	
	@Column(name = "alert_department_status_id")
	public Long getAlertDepartmentStatusId() {
		return alertDepartmentStatusId;
	}
	public void setAlertDepartmentStatusId(Long alertDepartmentStatusId) {
		this.alertDepartmentStatusId = alertDepartmentStatusId;
	}
	
	@Column(name = "alert_department_comment_id")
	public Long getAlertDepartmentCommentId() {
		return alertDepartmentCommentId;
	}
	public void setAlertDepartmentCommentId(Long alertDepartmentCommentId) {
		this.alertDepartmentCommentId = alertDepartmentCommentId;
	}
	
	@Column(name = "alert_tracking_action_id")
	public Long getAlertTrackingActionId() {
		return alertTrackingActionId;
	}
	public void setAlertTrackingActionId(Long alertTrackingActionId) {
		this.alertTrackingActionId = alertTrackingActionId;
	}
	
	@Column(name = "inserted_by")
	public Long getInsertedBy() {
		return insertedBy;
	}
	public void setInsertedBy(Long insertedBy) {
		this.insertedBy = insertedBy;
	}
	
	@Column(name = "inserted_time")
	public Date getInsertedTime() {
		return insertedTime;
	}
	public void setInsertedTime(Date insertedTime) {
		this.insertedTime = insertedTime;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="alert_department_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public AlertDepartment getAlertDepartment() {
		return alertDepartment;
	}
	public void setAlertDepartment(AlertDepartment alertDepartment) {
		this.alertDepartment = alertDepartment;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="alert_department_status_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public AlertDepartmentStatus getAlertDepartmentStatus() {
		return alertDepartmentStatus;
	}
	public void setAlertDepartmentStatus(AlertDepartmentStatus alertDepartmentStatus) {
		this.alertDepartmentStatus = alertDepartmentStatus;
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
	@JoinColumn(name="alert_tracking_action_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public AlertTrackingAction getAlertTrackingAction() {
		return alertTrackingAction;
	}
	public void setAlertTrackingAction(AlertTrackingAction alertTrackingAction) {
		this.alertTrackingAction = alertTrackingAction;
	}
}
