/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on April 12,2010
 */
package com.itgrids.partyanalyst.model;

import java.io.Serializable;
import java.util.Date;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name = "party_election_state_result_with_alliance")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class PartyElectionStateResultWithAlliance extends BaseModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6428927970680216518L;
	
	private Long PartyElectionStateResultId;
	
	private Party party;
	private State state;
	private Election election;
	private Group group;
	
	private Long nthPosWon;	
	private Long thirdPosWon;
	private Long secondPosWon;
	private Long fourthPosWon;
	private Long totalSeatsWon;
	private Long totalConstiParticipated;
	
	private Double totalValidVotes;
    private Double totalVotesGained;
	private Double completeConstiValidVotes;
	
	
    private Date lastUpdated;
	
	private String completeVotesPercent;
	private String votesPercentage;
	
	
	//default constructor
	public PartyElectionStateResultWithAlliance() {
		
	}

	//parameterized constructor
	public PartyElectionStateResultWithAlliance(
			Party party, State state, Election election, Long nthPosWon,
			Date lastUpdated, Long thirdPosWon, Long secondPosWon,
			Long fourthPosWon, Long totalSeatsWon, Double totalValidVotes,
			String votesPercentage, Double totalVotesGained,
			String completeVotesPercent, Long totalConstiParticipated,
			Double completeConstiValidVotes,Group group) {
		super();
		this.party = party;
		this.state = state;
		this.election = election;
		this.nthPosWon = nthPosWon;
		this.lastUpdated = lastUpdated;
		this.thirdPosWon = thirdPosWon;
		this.secondPosWon = secondPosWon;
		this.fourthPosWon = fourthPosWon;
		this.totalSeatsWon = totalSeatsWon;
		this.totalValidVotes = totalValidVotes;
		this.votesPercentage = votesPercentage;
		this.totalVotesGained = totalVotesGained;
		this.completeVotesPercent = completeVotesPercent;
		this.totalConstiParticipated = totalConstiParticipated;
		this.completeConstiValidVotes = completeConstiValidVotes;
		this.group = group;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "party_election_state_result_with_alliance_id", unique = true, nullable = false)
	public Long getPartyElectionStateResultId() {
		return PartyElectionStateResultId;
	}

	public void setPartyElectionStateResultId(Long partyElectionStateResultId) {
		PartyElectionStateResultId = partyElectionStateResultId;
	}

	@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "group_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)	
	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
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

	@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "state_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
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

	@Column(name = "nth_pos_won", length = 25)
	public Long getNthPosWon() {
		return nthPosWon;
	}

	public void setNthPosWon(Long nthPosWon) {
		this.nthPosWon = nthPosWon;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "last_updated", length = 10)
	public Date getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	@Column(name = "third_pos_won")
	public Long getThirdPosWon() {
		return thirdPosWon;
	}

	public void setThirdPosWon(Long thirdPosWon) {
		this.thirdPosWon = thirdPosWon;
	}

	@Column(name = "second_pos_won", length = 25)
	public Long getSecondPosWon() {
		return secondPosWon;
	}

	public void setSecondPosWon(Long secondPosWon) {
		this.secondPosWon = secondPosWon;
	}

	@Column(name = "fourth_pos_won", length = 25)
	public Long getFourthPosWon() {
		return fourthPosWon;
	}

	public void setFourthPosWon(Long fourthPosWon) {
		this.fourthPosWon = fourthPosWon;
	}

	@Column(name = "total_seats_won", length = 25)
	public Long getTotalSeatsWon() {
		return totalSeatsWon;
	}

	public void setTotalSeatsWon(Long totalSeatsWon) {
		this.totalSeatsWon = totalSeatsWon;
	}

	@Column(name = "total_valid_votes", precision = 10, scale = 0)
	public Double getTotalValidVotes() {
		return totalValidVotes;
	}

	public void setTotalValidVotes(Double totalValidVotes) {
		this.totalValidVotes = totalValidVotes;
	}

	@Column(name = "votes_percentage", length = 25)
	public String getVotesPercentage() {
		return votesPercentage;
	}

	public void setVotesPercentage(String votesPercentage) {
		this.votesPercentage = votesPercentage;
	}

	@Column(name = "total_votes_gained", precision = 10, scale = 0)
	public Double getTotalVotesGained() {
		return totalVotesGained;
	}

	public void setTotalVotesGained(Double totalVotesGained) {
		this.totalVotesGained = totalVotesGained;
	}

	@Column(name = "complete_votes_percentage", length = 25)
	public String getCompleteVotesPercent() {
		return completeVotesPercent;
	}

	public void setCompleteVotesPercent(String completeVotesPercent) {
		this.completeVotesPercent = completeVotesPercent;
	}

	@Column(name = "total_consti_participated", length = 25)
	public Long getTotalConstiParticipated() {
		return totalConstiParticipated;
	}

	public void setTotalConstiParticipated(Long totalConstiParticipated) {
		this.totalConstiParticipated = totalConstiParticipated;
	}

	@Column(name = "complete_consti_valid_votes", precision = 10, scale = 0)
	public Double getCompleteConstiValidVotes() {
		return completeConstiValidVotes;
	}

	public void setCompleteConstiValidVotes(Double completeConstiValidVotes) {
		this.completeConstiValidVotes = completeConstiValidVotes;
	}

}
