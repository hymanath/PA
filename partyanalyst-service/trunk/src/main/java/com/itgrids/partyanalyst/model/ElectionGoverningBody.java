package com.itgrids.partyanalyst.model;

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
@Table(name = "election_governing_body")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ElectionGoverningBody {
		
	private Long governingElectionBodyId;
	private Candidate candidate;
	private Long duration;
	private LocalElectionBody localElectionBody;
	private Election election;
	private ElectionGoverningBodyPosition electionGoverningBodyPosition;
	private Party party;
	
	public ElectionGoverningBody(){
		
	}
	
	public ElectionGoverningBody(Long governingElectionBodyId){
		this.governingElectionBodyId = governingElectionBodyId;
	}

	public ElectionGoverningBody(Candidate candidate, Long duration, LocalElectionBody localElectionBody, 
			Election election, ElectionGoverningBodyPosition electionGoverningBodyPosition, Party party) {
		this.candidate = candidate;
		this.duration = duration;
		this.localElectionBody = localElectionBody;
		this.election = election;
		this.electionGoverningBodyPosition = electionGoverningBodyPosition;
		this.party = party;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "election_governing_body_id", unique = true, nullable = false)
	public Long getGoverningElectionBodyId() {
		return governingElectionBodyId;
	}

	public void setGoverningElectionBodyId(Long governingElectionBodyId) {
		this.governingElectionBodyId = governingElectionBodyId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "candidate_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Candidate getCandidate() {
		return candidate;
	}

	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}

	@Column(name = "duration")
	public Long getDuration() {
		return duration;
	}

	public void setDuration(Long duration) {
		this.duration = duration;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "election_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Election getElection() {
		return election;
	}

	public void setElection(Election election) {
		this.election = election;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "election_body_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public LocalElectionBody getLocalElectionBody() {
		return localElectionBody;
	}

	public void setLocalElectionBody(LocalElectionBody localElectionBody) {
		this.localElectionBody = localElectionBody;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "election_governing_body_position_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public ElectionGoverningBodyPosition getElectionGoverningBodyPosition() {
		return electionGoverningBodyPosition;
	}

	public void setElectionGoverningBodyPosition(
			ElectionGoverningBodyPosition electionGoverningBodyPosition) {
		this.electionGoverningBodyPosition = electionGoverningBodyPosition;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "party_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Party getParty() {
		return party;
	}

	public void setParty(Party party) {
		this.party = party;
	}
	
	
	
}
