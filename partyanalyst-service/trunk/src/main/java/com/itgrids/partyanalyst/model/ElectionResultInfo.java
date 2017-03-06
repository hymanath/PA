package com.itgrids.partyanalyst.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;


@Entity
@Table(name = "election_result_info")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ElectionResultInfo  implements java.io.Serializable{
	
	
	private Long electionResultInfoId;
	private Long locationLevelId;
	private Long locationLevelValue;
	private Long electionId;
	private Long partyId;
	private Long rank;
	private Long gainVotes;
	private String gainVotesPerc;
	private Long marginVotes;
	private String marginVotesPerc;

	private Election election;
	private Party party;
	
	@Column(name = "election_result_info_id")
	public Long getElectionResultInfoId() {
		return electionResultInfoId;
	}
	public void setElectionResultInfoId(Long electionResultInfoId) {
		this.electionResultInfoId = electionResultInfoId;
	}
	
	@Column(name = "location_level_id")
	public Long getLocationLevelId() {
		return locationLevelId;
	}
	public void setLocationLevelId(Long locationLevelId) {
		this.locationLevelId = locationLevelId;
	}
	
	@Column(name = "location_level_value")
	public Long getLocationLevelValue() {
		return locationLevelValue;
	}
	public void setLocationLevelValue(Long locationLevelValue) {
		this.locationLevelValue = locationLevelValue;
	}
	
	@Column(name = "election_id")
	public Long getElectionId() {
		return electionId;
	}
	public void setElectionId(Long electionId) {
		this.electionId = electionId;
	}
	
	@Column(name = "party_id")
	public Long getPartyId() {
		return partyId;
	}
	public void setPartyId(Long partyId) {
		this.partyId = partyId;
	}
	
	@Column(name = "rank")
	public Long getRank() {
		return rank;
	}
	public void setRank(Long rank) {
		this.rank = rank;
	}
	
	@Column(name = "gain_votes")
	public Long getGainVotes() {
		return gainVotes;
	}
	public void setGainVotes(Long gainVotes) {
		this.gainVotes = gainVotes;
	}
	
	@Column(name = "gain_votes_perc")
	public String getGainVotesPerc() {
		return gainVotesPerc;
	}
	public void setGainVotesPerc(String gainVotesPerc) {
		this.gainVotesPerc = gainVotesPerc;
	}
	
	@Column(name = "margin_votes")
	public Long getMarginVotes() {
		return marginVotes;
	}
	public void setMarginVotes(Long marginVotes) {
		this.marginVotes = marginVotes;
	}
	
	@Column(name = "margin_votes_perc")
	public String getMarginVotesPerc() {
		return marginVotesPerc;
	}
	public void setMarginVotesPerc(String marginVotesPerc) {
		this.marginVotesPerc = marginVotesPerc;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "election_id", unique = true, nullable = false)
	public Election getElection() {
		return election;
	}
	public void setElection(Election election) {
		this.election = election;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "party_id", unique = true, nullable = false)
	public Party getParty() {
		return party;
	}
	public void setParty(Party party) {
		this.party = party;
	}	
	
}
