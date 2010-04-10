package com.itgrids.partyanalyst.dto;

public class ElectionResultVO {

	private String electionType;
	private String electionYear;
	private Long votesEarned;
	private String percentage;
	private String partyName;
	private Long noOfSeatsWon;
	
	public Long getNoOfSeatsWon() {
		return noOfSeatsWon;
	}

	public void setNoOfSeatsWon(Long noOfSeatsWon) {
		this.noOfSeatsWon = noOfSeatsWon;
	}

	public String getPartyName() {
		return partyName;
	}

	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}

	public String getElectionType() {
		return electionType;
	}
	
	public void setElectionType(String electionType) {
		this.electionType = electionType;
	}
	
	public String getElectionYear() {
		return electionYear;
	}
	
	public void setElectionYear(String electionYear) {
		this.electionYear = electionYear;
	}
	
	public Long getVotesEarned() {
		return votesEarned;
	}
	
	public void setVotesEarned(Long votesEarned) {
		this.votesEarned = votesEarned;
	}
	
	public String getPercentage() {
		return percentage;
	}
	
	public void setPercentage(String percentage) {
		this.percentage = percentage;
	}
	
	
	
}
