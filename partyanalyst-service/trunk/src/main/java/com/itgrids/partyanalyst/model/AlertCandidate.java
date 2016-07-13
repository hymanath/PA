package com.itgrids.partyanalyst.model;

import java.io.Serializable;

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
@Table(name = "alert_candidate")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AlertCandidate extends BaseModel implements Serializable {
	private Long alertCandidateId;
	private Long alertId;
	private Long tdpCadreId;
	private Long alertImpactId;
	private Alert alert;
	private TdpCadre tdpCadre;
	private AlertImpact alertImpact;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "alert_candidate_id", unique = true, nullable = false)
	public Long getAlertCandidateId() {
		return alertCandidateId;
	}

	public void setAlertCandidateId(Long alertCandidateId) {
		this.alertCandidateId = alertCandidateId;
	}

	@Column(name = "alert_id")
	public Long getAlertId() {
		return alertId;
	}

	public void setAlertId(Long alertId) {
		this.alertId = alertId;
	}

	@Column(name = "tdp_cadre_id" )
	public Long getTdpCadreId() {
		return tdpCadreId;
	}
	public void setTdpCadreId(Long tdpCadreId) {
		this.tdpCadreId = tdpCadreId;
	}
	@Column(name = "alert_impact_id")
	public Long getAlertImpactId() {
		return alertImpactId;
	}

	public void setAlertImpactId(Long alertImpactId) {
		this.alertImpactId = alertImpactId;
	}
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "alert_id" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Alert getAlert() {
		return alert;
	}

	public void setAlert(Alert alert) {
		this.alert = alert;
	}
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "tdp_cadre_id", insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public TdpCadre getTdpCadre() {
		return tdpCadre;
	}
	public void setTdpCadre(TdpCadre tdpCadre) {
		this.tdpCadre = tdpCadre;
	}

	
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "alert_impact_id", insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public AlertImpact getAlertImpact() {
		return alertImpact;
	}

	public void setAlertImpact(AlertImpact alertImpact) {
		this.alertImpact = alertImpact;
	}
	

}
