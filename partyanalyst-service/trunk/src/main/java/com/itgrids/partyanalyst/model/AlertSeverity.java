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
@Table(name = "alert_severity")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AlertSeverity extends BaseModel implements Serializable {
	private Long alertSeverityId;
	private String severity;
	private String severityColor;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "alert_severity_id", unique = true, nullable = false)
	public Long getAlertSeverityId() {
		return alertSeverityId;
	}

	public void setAlertSeverityId(Long alertSeverityId) {
		this.alertSeverityId = alertSeverityId;
	}

	@Column(name = "severity")
	public String getSeverity() {
		return severity;
	}

	public void setSeverity(String severity) {
		this.severity = severity;
	}
	@Column(name = "severity_color")
	public String getSeverityColor() {
		return severityColor;
	}

	public void setSeverityColor(String severityColor) {
		this.severityColor = severityColor;
	}
	

}
