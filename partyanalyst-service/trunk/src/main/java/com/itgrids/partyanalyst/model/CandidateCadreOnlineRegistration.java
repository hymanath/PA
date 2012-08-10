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
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "candidate_cadre_online_registration")
public class CandidateCadreOnlineRegistration extends BaseModel implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3494198518426443377L;
	private Long candidateCadreOnlineRegistrationId;
	private Candidate candidate;
	private CadreOnlineRegistration cadreOnlineRegistration;
	
	public CandidateCadreOnlineRegistration(){};
	
	public CandidateCadreOnlineRegistration(Candidate candidate,CadreOnlineRegistration cadreOnlineRegistration){
		
		this.candidate = candidate;
		this.cadreOnlineRegistration = cadreOnlineRegistration;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "candidate_cadre_online_registration_id",unique=true, nullable=false)
	public Long getCandidateCadreOnlineRegistrationId() {
		return candidateCadreOnlineRegistrationId;
	}

	public void setCandidateCadreOnlineRegistrationId(
			Long candidateCadreOnlineRegistrationId) {
		this.candidateCadreOnlineRegistrationId = candidateCadreOnlineRegistrationId;
	}

	@ManyToOne(cascade = CascadeType.ALL, fetch= FetchType.LAZY)
	@JoinColumn(name = "candidate_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Candidate getCandidate() {
		return candidate;
	}

	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}

	@ManyToOne(cascade = CascadeType.ALL, fetch= FetchType.LAZY)
	@JoinColumn(name = "cadre_online_registration_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public CadreOnlineRegistration getCadreOnlineRegistration() {
		return cadreOnlineRegistration;
	}

	public void setCadreOnlineRegistration(
			CadreOnlineRegistration cadreOnlineRegistration) {
		this.cadreOnlineRegistration = cadreOnlineRegistration;
	}
	
	
}
