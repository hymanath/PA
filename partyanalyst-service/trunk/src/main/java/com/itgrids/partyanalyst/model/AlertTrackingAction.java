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

	
}
