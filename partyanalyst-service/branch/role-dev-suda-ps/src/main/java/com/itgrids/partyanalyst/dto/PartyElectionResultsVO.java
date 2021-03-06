/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on October 12, 2009
 */
package com.itgrids.partyanalyst.dto;

public class PartyElectionResultsVO {

	
	private Long partyId;
	private String partyName;
	private Long candidateId;
	private String candidateName;
	private Long constituencyId;
	private String constituencyName;
	private Long votesEarned;
	private Long rank;
	private String votesPercentage;
	private Double totalElectors;
	private String secondCandidateName;
	private Long votesEarnedBySecond;
	private String votesPercentageBySecond;
	private Long rankBySecond;
	private String votesDiff;
	private Double voteDiff;
	private Double totalElectorsForSecond;
	private String electorsPercentageDiff;

	//getters and setters
	public Long getPartyId() {
		return partyId;
	}
	public void setPartyId(Long partyId) {
		this.partyId = partyId;
	}
	public String getPartyName() {
		return partyName;
	}
	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}
	public Long getCandidateId() {
		return candidateId;
	}
	public void setCandidateId(Long candidateId) {
		this.candidateId = candidateId;
	}
	public String getCandidateName() {
		return candidateName;
	}
	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
	}
	public Long getConstituencyId() {
		return constituencyId;
	}
	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}
	public String getConstituencyName() {
		return constituencyName;
	}
	public void setConstituencyName(String constituencyName) {
		this.constituencyName = constituencyName;
	}
	public Long getVotesEarned() {
		return votesEarned;
	}
	public void setVotesEarned(Long votesEarned) {
		this.votesEarned = votesEarned;
	}
	public Long getRank() {
		return rank;
	}
	public void setRank(Long rank) {
		this.rank = rank;
	}
	public String getVotesPercentage() {
		return votesPercentage;
	}
	public void setVotesPercentage(String votesPercentage) {
		this.votesPercentage = votesPercentage;
	}
	public String getSecondCandidateName() {
		return secondCandidateName;
	}
	public void setSecondCandidateName(String secondCandidateName) {
		this.secondCandidateName = secondCandidateName;
	}
	public Long getVotesEarnedBySecond() {
		return votesEarnedBySecond;
	}
	public void setVotesEarnedBySecond(Long votesEarnedBySecond) {
		this.votesEarnedBySecond = votesEarnedBySecond;
	}
	public String getVotesPercentageBySecond() {
		return votesPercentageBySecond;
	}
	public void setVotesPercentageBySecond(String votesPercentageBySecond) {
		this.votesPercentageBySecond = votesPercentageBySecond;
	}
	public Long getRankBySecond() {
		return rankBySecond;
	}
	public void setRankBySecond(Long rankBySecond) {
		this.rankBySecond = rankBySecond;
	}
	public String getVotesDiff() {
		return votesDiff;
	}
	public void setVotesDiff(String votesDiff) {
		this.votesDiff = votesDiff;
	}
	public Double getVoteDiff() {
		return voteDiff;
	}
	public void setVoteDiff(Double voteDiff) {
		this.voteDiff = voteDiff;
	}
	public Double getTotalElectors() {
		return totalElectors;
	}
	public void setTotalElectors(Double totalElectors) {
		this.totalElectors = totalElectors;
	}
	public Double getTotalElectorsForSecond() {
		return totalElectorsForSecond;
	}
	public void setTotalElectorsForSecond(Double totalElectorsForSecond) {
		this.totalElectorsForSecond = totalElectorsForSecond;
	}
	public String getElectorsPercentageDiff() {
		return electorsPercentageDiff;
	}
	public void setElectorsPercentageDiff(String electorsPercentageDiff) {
		this.electorsPercentageDiff = electorsPercentageDiff;
	}
	
	
}
