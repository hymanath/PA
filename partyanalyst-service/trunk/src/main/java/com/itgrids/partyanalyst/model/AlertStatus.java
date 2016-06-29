package com.itgrids.partyanalyst.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "alert_status")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AlertStatus extends BaseModel implements Serializable {
	private Long alertStatusId;
	private String alertStatus;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "alert_status_id", unique = true, nullable = false)
	public Long getAlertStatusId() {
		return alertStatusId;
	}

	public void setAlertStatusId(Long alertStatusId) {
		this.alertStatusId = alertStatusId;
	}

	@Column(name = "alert_status")
	public String getAlertStatus() {
		return alertStatus;
	}

	public void setAlertStatus(String alertStatus) {
		this.alertStatus = alertStatus;
	}

}
