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
	private Long candidateId;
	private Long alertImpactId;
	private Alert alert;
	private Candidate candidate;

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

	@Column(name = "candidate_id")
	public Long getCandidateId() {
		return candidateId;
	}

	public void setCandidateId(Long candidateId) {
		this.candidateId = candidateId;
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
	@JoinColumn(name = "candidate_id" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Candidate getCandidate() {
		return candidate;
	}

	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}
	
	

}
