package com.itgrids.partyanalyst.model;

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
@Table(name = "party_rebel_candidate")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class PartyRebelCandidate {

	private Long partyRebelCandidateId;
	private PartyRebel partyRebel;
	private Candidate candidate;
	
	public PartyRebelCandidate(){}
	
	public PartyRebelCandidate(Long partyRebelCandidateId) {
		this.partyRebelCandidateId = partyRebelCandidateId;
	}
	
	public PartyRebelCandidate(Long partyRebelCandidateId,
			PartyRebel partyRebel, Candidate candidate) {
		super();
		this.partyRebelCandidateId = partyRebelCandidateId;
		this.partyRebel = partyRebel;
		this.candidate = candidate;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "party_rebel_candidate_id", unique = true, nullable = false)
	public Long getPartyRebelCandidateId() {
		return partyRebelCandidateId;
	}
	public void setPartyRebelCandidateId(Long partyRebelCandidateId) {
		this.partyRebelCandidateId = partyRebelCandidateId;
	}
	
	@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "party_rebel_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public PartyRebel getPartyRebel() {
		return partyRebel;
	}
	public void setPartyRebel(PartyRebel partyRebel) {
		this.partyRebel = partyRebel;
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
	
	
	
}
