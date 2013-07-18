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
@Table(name="voting_trendz_parties_result")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class VotingTrendzPartiesResult extends BaseModel implements Serializable{

	private static final long serialVersionUID = 3014524469318152711L;
	
	private Long votingTrendzPartiesResultId;
	private VotingTrendz votingTrendz;
	private Party party;
	private Long votesGained;
	
	public VotingTrendzPartiesResult(){}
	
	public VotingTrendzPartiesResult(VotingTrendz votingTrendz,Party party,Long votesGained)
	{
		this.votingTrendz = votingTrendz;
		this.party = party;
		this.votesGained = votesGained;
	}

	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="voting_trendz_parties_result_id", unique=true, nullable=false)
	public Long getVotingTrendzPartiesResultId() {
		return votingTrendzPartiesResultId;
	}

	public void setVotingTrendzPartiesResultId(Long votingTrendzPartiesResultId) {
		this.votingTrendzPartiesResultId = votingTrendzPartiesResultId;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="voting_trendz_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public VotingTrendz getVotingTrendz() {
		return votingTrendz;
	}

	public void setVotingTrendz(VotingTrendz votingTrendz) {
		this.votingTrendz = votingTrendz;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="party_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Party getParty() {
		return party;
	}

	public void setParty(Party party) {
		this.party = party;
	}
	@Column(name="votes_gained")
	public Long getVotesGained() {
		return votesGained;
	}

	public void setVotesGained(Long votesGained) {
		this.votesGained = votesGained;
	}
	
}
