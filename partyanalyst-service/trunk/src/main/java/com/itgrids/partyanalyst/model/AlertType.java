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
@Table(name = "alert_type")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AlertType extends BaseModel implements Serializable {
	private Long alertTypeId;
	private String alertType;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "alert_type_id", unique = true, nullable = false)
	public Long getAlertTypeId() {
		return alertTypeId;
	}

	public void setAlertTypeId(Long alertTypeId) {
		this.alertTypeId = alertTypeId;
	}

	@Column(name = "alert_type")
	public String getAlertType() {
		return alertType;
	}

	public void setAlertType(String alertType) {
		this.alertType = alertType;
	}

}
