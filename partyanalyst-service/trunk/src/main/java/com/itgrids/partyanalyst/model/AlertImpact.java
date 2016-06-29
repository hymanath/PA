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
@Table(name = "alert_impact")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AlertImpact extends BaseModel implements Serializable {
	private Long alertImpactId;
	private String impact;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "alert_impact_id", unique = true, nullable = false)
	public Long getAlertImpactId() {
		return alertImpactId;
	}

	public void setAlertImpactId(Long alertImpactId) {
		this.alertImpactId = alertImpactId;
	}

	@Column(name = "impact")
	public String getImpact() {
		return impact;
	}

	public void setImpact(String impact) {
		this.impact = impact;
	}

}
