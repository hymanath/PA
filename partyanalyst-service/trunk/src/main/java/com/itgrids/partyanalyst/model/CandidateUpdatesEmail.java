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

/**
 * candidate_updates_email entity. 
 * @author <a href="mailto:kamalakardandu@gmail.com">Kamalakar Dandu</a>
 */

@Entity
@Table(name = "candidate_updates_email")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CandidateUpdatesEmail implements Serializable{

	private static final long serialVersionUID = -8222312661774185778L;
	private Long candidateUpdatesEmailId;
	private Candidate candidate;
	private String email;
	private String unsubscribed;
	
	public CandidateUpdatesEmail()
	{}
	
	public CandidateUpdatesEmail(Candidate candidate,String email,String unsubscribed)
	{
		this.candidate = candidate;
		this.email = email;
		this.unsubscribed = unsubscribed;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="candidate_updates_email_id", unique=true, nullable=false)
	public Long getCandidateUpdatesEmailId() {
		return candidateUpdatesEmailId;
	}

	public void setCandidateUpdatesEmailId(Long candidateUpdatesEmailId) {
		this.candidateUpdatesEmailId = candidateUpdatesEmailId;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="candidate_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Candidate getCandidate() {
		return candidate;
	}

	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}

	@Column(name="email",length=100)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name="unsubscribed",length=10)
	public String getUnsubscribed() {
		return unsubscribed;
	}

	public void setUnsubscribed(String unsubscribed) {
		this.unsubscribed = unsubscribed;
	}
	
	
}
