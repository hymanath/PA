package com.itgrids.partyanalyst.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name = "party_rebel")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class PartyRebel extends BaseModel {

	
	private static final long serialVersionUID = 1L;
	private Long partyRebelId;
	private Election election;
	private Party party;
	private Set<PartyRebelCandidate> rebelCandidates;
	
	public PartyRebel(){}
	
	public PartyRebel(Long partyRebelId) {
		this.partyRebelId = partyRebelId;
	}
	
	public PartyRebel(Long partyRebelId, Election election, Party party) {
		super();
		this.partyRebelId = partyRebelId;
		this.election = election;
		this.party = party;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "party_rebel_id", unique = true, nullable = false)
	public Long getPartyRebelId() {
		return partyRebelId;
	}

	public void setPartyRebelId(Long partyRebelId) {
		this.partyRebelId = partyRebelId;
	}
	
	@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "election_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Election getElection() {
		return election;
	}

	public void setElection(Election election) {
		this.election = election;
	}
	
	@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "party_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Party getParty() {
		return party;
	}
	public void setParty(Party party) {
		this.party = party;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "partyRebel")
	public Set<PartyRebelCandidate> getRebelCandidates() {
		return rebelCandidates;
	}

	public void setRebelCandidates(Set<PartyRebelCandidate> rebelCandidates) {
		this.rebelCandidates = rebelCandidates;
	}
	
	
	
}
