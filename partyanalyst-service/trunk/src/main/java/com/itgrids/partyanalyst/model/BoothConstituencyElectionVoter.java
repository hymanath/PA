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
@Table(name = "booth_constituency_election_voter")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class BoothConstituencyElectionVoter extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long boothConstituencyElectionVoterId;
	private BoothConstituencyElection boothConstituencyElection;
	private Voter voter;
	
	public BoothConstituencyElectionVoter(){
		
	}
	
	public BoothConstituencyElectionVoter(Long boothConstituencyElectionVoterId) {
		this.boothConstituencyElectionVoterId = boothConstituencyElectionVoterId;
	}

	public BoothConstituencyElectionVoter(BoothConstituencyElection boothConstituencyElection, Voter voter) {
		this.boothConstituencyElection = boothConstituencyElection;
		this.voter = voter;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "booth_constituency_election_voter_id", unique = true, nullable = false)
	public Long getBoothConstituencyElectionVoterId() {
		return boothConstituencyElectionVoterId;
	}
	
	public void setBoothConstituencyElectionVoterId(
			Long boothConstituencyElectionVoterId) {
		this.boothConstituencyElectionVoterId = boothConstituencyElectionVoterId;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "booth_constituency_election_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public BoothConstituencyElection getBoothConstituencyElection() {
		return boothConstituencyElection;
	}
	
	public void setBoothConstituencyElection(
			BoothConstituencyElection boothConstituencyElection) {
		this.boothConstituencyElection = boothConstituencyElection;
	}
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "voter_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Voter getVoter() {
		return voter;
	}
	
	public void setVoter(Voter voter) {
		this.voter = voter;
	}
	
	
}
