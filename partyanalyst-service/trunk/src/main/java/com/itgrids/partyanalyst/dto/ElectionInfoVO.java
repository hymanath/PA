package com.itgrids.partyanalyst.dto;

import java.util.List;

public class ElectionInfoVO {

	private String electionTypeYear;
	private String constituencyName;
	private String constituencyId;
	private List<PartyResultsInfoVO> partyResults;
	private Long totalConstituencies;
	private Long participatedConstituencies;
	private Long totalValidVotes;
    private Double votesPercentage;
    private Double completeVotesPercentage;
    
    
	public Double getVotesPercentage() {
		return votesPercentage;
	}

	public void setVotesPercentage(Double votesPercentage) {
		this.votesPercentage = votesPercentage;
	}

	public Double getCompleteVotesPercentage() {
		return completeVotesPercentage;
	}

	public void setCompleteVotesPercentage(Double completeVotesPercentage) {
		this.completeVotesPercentage = completeVotesPercentage;
	}

	public Long getParticipatedConstituencies() {
		return participatedConstituencies;
	}

	public void setParticipatedConstituencies(Long participatedConstituencies) {
		this.participatedConstituencies = participatedConstituencies;
	}

	public String getElectionTypeYear() {
		return electionTypeYear;
	}
	
	public void setElectionTypeYear(String electionTypeYear) {
		this.electionTypeYear = electionTypeYear;
	}
	
	public String getConstituencyName() {
		return constituencyName;
	}
	
	public void setConstituencyName(String constituencyName) {
		this.constituencyName = constituencyName;
	}
	
	public String getConstituencyId() {
		return constituencyId;
	}
	
	public void setConstituencyId(String constituencyId) {
		this.constituencyId = constituencyId;
	}
	
	public List<PartyResultsInfoVO> getPartyResults() {
		return partyResults;
	}
	
	public void setPartyResults(List<PartyResultsInfoVO> partyResults) {
		this.partyResults = partyResults;
	}

	public Long getTotalConstituencies() {
		return totalConstituencies;
	}

	public void setTotalConstituencies(Long totalConstituencies) {
		this.totalConstituencies = totalConstituencies;
	}

	public Long getTotalValidVotes() {
		return totalValidVotes;
	}

	public void setTotalValidVotes(Long totalValidVotes) {
		this.totalValidVotes = totalValidVotes;
	}
	
	
	
}
