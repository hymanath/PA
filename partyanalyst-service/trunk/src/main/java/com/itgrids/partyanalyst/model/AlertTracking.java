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
@Table(name = "alert_tracking")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AlertTracking extends BaseModel implements Serializable {
	private Long alertTrackingId;
	private Long alertId;
	private Long alertStatusId;
	private Long alertCommentId;
	private Long alertUserTypeId;
	private Long insertedBy;
	private Date insertedTime;
	private AlertTracking alertTracking;
	private Long alertTrackingActionId;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "alert_type_id", unique = true, nullable = false)
	public Long getAlertTrackingId() {
		return alertTrackingId;
	}

	public void setAlertTrackingId(Long alertTrackingId) {
		this.alertTrackingId = alertTrackingId;
	}

	@Column(name = "alert_id")
	public Long getAlertId() {
		return alertId;
	}

	public void setAlertId(Long alertId) {
		this.alertId = alertId;
	}

	@Column(name = "alert_status_id")
	public Long getAlertStatusId() {
		return alertStatusId;
	}

	public void setAlertStatusId(Long alertStatusId) {
		this.alertStatusId = alertStatusId;
	}

	@Column(name = "alert_comment_id")
	public Long getAlertCommentId() {
		return alertCommentId;
	}

	public void setAlertCommentId(Long alertCommentId) {
		this.alertCommentId = alertCommentId;
	}

	@Column(name = "alert_user_type_id")
	public Long getAlertUserTypeId() {
		return alertUserTypeId;
	}

	public void setAlertUserTypeId(Long alertUserTypeId) {
		this.alertUserTypeId = alertUserTypeId;
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
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "alert_tracking_action_id", insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public AlertTracking getAlertTracking() {
		return alertTracking;
	}

	public void setAlertTracking(AlertTracking alertTracking) {
		this.alertTracking = alertTracking;
	}
	@Column(name = "alert_tracking_action_id")
	public Long getAlertTrackingActionId() {
		return alertTrackingActionId;
	}

	public void setAlertTrackingActionId(Long alertTrackingActionId) {
		this.alertTrackingActionId = alertTrackingActionId;
	}


}
