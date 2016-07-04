package com.itgrids.partyanalyst.model;

import java.io.Serializable;

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
@Table(name = "alert_tracking_action")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AlertTrackingAction extends BaseModel implements Serializable {
	private Long alert_tracking_action_id;
	private String action;
	private AlertTracking alertTracking;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "alert_tracking_action_id", unique = true, nullable = false)
	public Long getAlert_tracking_action_id() {
		return alert_tracking_action_id;
	}

	public void setAlert_tracking_action_id(Long alert_tracking_action_id) {
		this.alert_tracking_action_id = alert_tracking_action_id;
	}

	@Column(name = "action")
	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
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

}
