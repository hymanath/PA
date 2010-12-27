/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on February 08,2010
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
@Table(name = "party_election_result_with_alliance")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class PartyElectionResultWithAlliance extends BaseModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4407536761020932438L;

	private Long partyElectionResultWithAllianceId;
	
	private Party party;
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
		
	private String votesPercentage;
	private String completeVotesPercent;
	
	private Date lastUpdated;
		
	//default constructor
	public PartyElectionResultWithAlliance(){
		
	}
	
	//parameterized constructor
	public PartyElectionResultWithAlliance(Party party, Election election,Group group,
			Long totalConstiParticipated, Long totalSeatsWon,
			Long secondPosWon, Long thirdPosWon, Long fourthPosWon,
			Long nthPosWon, String votesPercentage,String completeVotesPercent,
			Date lastUpdated,Double totalVotesGained,Double totalValidVotes,Double completeConstiValidVotes){
		super();
		this.party = party;
		this.election = election;
		this.group=group;
		
		this.totalConstiParticipated = totalConstiParticipated;
		this.totalSeatsWon = totalSeatsWon;
		this.secondPosWon = secondPosWon;
		this.thirdPosWon = thirdPosWon;
		this.fourthPosWon = fourthPosWon;
		this.nthPosWon = nthPosWon;
		this.votesPercentage = votesPercentage;
		this.completeVotesPercent = completeVotesPercent;
		this.lastUpdated = lastUpdated;
		this.totalVotesGained = totalVotesGained;
		this.totalValidVotes = totalValidVotes;
		this.completeConstiValidVotes = completeConstiValidVotes;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "party_election_result_with_alliance_id", unique = true, nullable = false)
	public Long getPartyElectionResultWithAllianceId() {
		return partyElectionResultWithAllianceId;
	}

	public void setPartyElectionResultWithAllianceId(
			Long partyElectionResultWithAllianceId) {
		this.partyElectionResultWithAllianceId = partyElectionResultWithAllianceId;
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
	@JoinColumn(name = "election_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Election getElection() {
		return election;
	}

	public void setElection(Election election) {
		this.election = election;
	}

	@Column(name = "total_consti_participated", length = 25)
	public Long getTotalConstiParticipated() {
		return totalConstiParticipated;
	}

	public void setTotalConstiParticipated(Long totalConstiParticipated) {
		this.totalConstiParticipated = totalConstiParticipated;
	}

	@Column(name = "total_seats_won", length = 25)
	public Long getTotalSeatsWon() {
		return totalSeatsWon;
	}

	public void setTotalSeatsWon(Long totalSeatsWon) {
		this.totalSeatsWon = totalSeatsWon;
	}

	@Column(name = "second_pos_won", length = 25)
	public Long getSecondPosWon() {
		return secondPosWon;
	}

	public void setSecondPosWon(Long secondPosWon) {
		this.secondPosWon = secondPosWon;
	}

	@Column(name = "third_pos_won", length = 25)
	public Long getThirdPosWon() {
		return thirdPosWon;
	}

	public void setThirdPosWon(Long thirdPosWon) {
		this.thirdPosWon = thirdPosWon;
	}

	@Column(name = "fourth_pos_won", length = 25)
	public Long getFourthPosWon() {
		return fourthPosWon;
	}

	public void setFourthPosWon(Long fourthPosWon) {
		this.fourthPosWon = fourthPosWon;
	}

	@Column(name = "nth_pos_won", length = 25)
	public Long getNthPosWon() {
		return nthPosWon;
	}

	public void setNthPosWon(Long nthPosWon) {
		this.nthPosWon = nthPosWon;
	}

	@Column(name = "votes_percentage", length = 25)
	public String getVotesPercentage() {
		return votesPercentage;
	}

	public void setVotesPercentage(String votesPercentage) {
		this.votesPercentage = votesPercentage;
	}

	@Column(name = "complete_votes_percentage", length = 25)
	public String getCompleteVotesPercent() {
		return completeVotesPercent;
	}

	public void setCompleteVotesPercent(String completeVotesPercent) {
		this.completeVotesPercent = completeVotesPercent;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "last_updated", length = 10)
	public Date getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	@Column(name = "total_votes_gained", precision = 10, scale = 0)
	public Double getTotalVotesGained() {
		return totalVotesGained;
	}

	public void setTotalVotesGained(Double totalVotesGained) {
		this.totalVotesGained = totalVotesGained;
	}

	@Column(name = "total_valid_votes", precision = 10, scale = 0)
	public Double getTotalValidVotes() {
		return totalValidVotes;
	}

	public void setTotalValidVotes(Double totalValidVotes) {
		this.totalValidVotes = totalValidVotes;
	}

	@Column(name = "complete_consti_valid_votes", precision = 10, scale = 0)
	public Double getCompleteConstiValidVotes() {
		return completeConstiValidVotes;
	}

	public void setCompleteConstiValidVotes(Double completeConstiValidVotes) {
		this.completeConstiValidVotes = completeConstiValidVotes;
	}
	
	
	
}
