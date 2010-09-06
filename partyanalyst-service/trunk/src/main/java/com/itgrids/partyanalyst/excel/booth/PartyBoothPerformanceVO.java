package com.itgrids.partyanalyst.excel.booth;

import java.util.ArrayList;
import java.util.List;

public class PartyBoothPerformanceVO {
	
	private String partyName;
	private String candidateName;
	private String constituencyName;
	private String electionYear;
	private String electionType;
	private int totalValidVotes;
	private int votesGained;
	private String percentage;
	private Long totalVoters;
	private String votingPercentage;
	private List<BoothResultVO> boothResults = new ArrayList<BoothResultVO>();
	
	public PartyBoothPerformanceVO(){
		
	}
	
	public PartyBoothPerformanceVO(String partyName, String candidateName,
			String electionYear, String electionType, int totalValidVotes, int votesGained,
			String percentage, List<BoothResultVO> boothResults) {
		this.partyName = partyName;
		this.candidateName = candidateName;
		this.electionYear = electionYear;
		this.electionType = electionType;
		this.totalValidVotes = totalValidVotes;
		this.percentage = percentage;
		this.votesGained = votesGained;
		this.boothResults = boothResults;
	}
	
	public String getConstituencyName() {
		return constituencyName;
	}

	public void setConstituencyName(String constituencyName) {
		this.constituencyName = constituencyName;
	}

	public String getPartyName() {
		return partyName;
	}

	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}

	public String getCandidateName() {
		return candidateName;
	}

	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
	}

	public String getElectionYear() {
		return electionYear;
	}

	public void setElectionYear(String electionYear) {
		this.electionYear = electionYear;
	}

	public String getElectionType() {
		return electionType;
	}

	public void setElectionType(String electionType) {
		this.electionType = electionType;
	}

	public List<BoothResultVO> getBoothResults() {
		return boothResults;
	}

	public void setBoothResults(List<BoothResultVO> boothResults) {
		this.boothResults = boothResults;
	}

	public int getTotalValidVotes() {
		return totalValidVotes;
	}

	public void setTotalValidVotes(int totalValidVotes) {
		this.totalValidVotes = totalValidVotes;
	}

	public int getVotesGained() {
		return votesGained;
	}

	public void setVotesGained(int votesGained) {
		this.votesGained = votesGained;
	}

	public String getPercentage() {
		return percentage;
	}

	public void setPercentage(String percentage) {
		this.percentage = percentage;
	}

	public Long getTotalVoters() {
		return totalVoters;
	}

	public void setTotalVoters(Long totalVoters) {
		this.totalVoters = totalVoters;
	}

	public String getVotingPercentage() {
		return votingPercentage;
	}

	public void setVotingPercentage(String votingPercentage) {
		this.votingPercentage = votingPercentage;
	}

}
