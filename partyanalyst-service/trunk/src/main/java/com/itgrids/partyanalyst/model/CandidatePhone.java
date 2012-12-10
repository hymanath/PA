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
@Table(name = "candidate_phone")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CandidatePhone  extends BaseModel implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Long candidatePhoneId;
	private Candidate candidate;
	private PhoneNumber phoneNumber;
		
	public CandidatePhone(){
		
	}
	
	public CandidatePhone(Long candidatePhoneId,Candidate candidate,PhoneNumber phoneNumber){
		this.candidatePhoneId=candidatePhoneId;
		this.candidate=candidate;
		this.phoneNumber=phoneNumber;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "candidate_phone_id", unique = true, nullable = false)
	
	public Long getCandidatePhoneId() {
		return candidatePhoneId;
	}

	public void setCandidatePhoneId(Long candidatePhoneId) {
		this.candidatePhoneId = candidatePhoneId;
	}
	
	
	
	@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "candidate_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Candidate getCandidate() {
		return candidate;
	}
	

	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}

	
	
	
	
	@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "phone_number_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public PhoneNumber getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(PhoneNumber phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	
}
