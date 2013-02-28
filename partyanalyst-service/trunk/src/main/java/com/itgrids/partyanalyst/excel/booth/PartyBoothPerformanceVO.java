package com.itgrids.partyanalyst.excel.booth;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.itgrids.partyanalyst.dto.SelectOptionVO;

public class PartyBoothPerformanceVO {
	
	private String partyName;
	private String candidateName;
	private String constituencyName;
	private String electionYear;
	private String electionType;
	private int totalValidVotes;
	private int votesGained;
	private String percentage;
	private long totalVotes;
	private String votingPercentage;
	private List<BoothResultVO> boothResults = new ArrayList<BoothResultVO>();
	private List<BoothResultVO> perWiseboothResults = new ArrayList<BoothResultVO>();
	private List<BoothResultVO> partyPerWiseboothResults = new ArrayList<BoothResultVO>();
	private Map<String, List<SelectOptionVO>> boothsMap;
	private Map<String, List<SelectOptionVO>> boothsMap1;
	private Long rank;
	private List wonCandidate;
	private Long marginVotes;
	
	
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
	
    
	
	public Long getMarginVotes() {
		return marginVotes;
	}



	public void setMarginVotes(Long marginVotes) {
		this.marginVotes = marginVotes;
	}


	public List getWonCandidate() {
		return wonCandidate;
	}



	public void setWonCandidate(List wonCandidate) {
		this.wonCandidate = wonCandidate;
	}



	public Long getRank() {
		return rank;
	}




	public void setRank(Long rank) {
		this.rank = rank;
	}


	public Map<String, List<SelectOptionVO>> getBoothsMap() {
		return boothsMap;
	}



	public void setBoothsMap(Map<String, List<SelectOptionVO>> boothsMap) {
		this.boothsMap = boothsMap;
	}



	public Map<String, List<SelectOptionVO>> getBoothsMap1() {
		return boothsMap1;
	}



	public void setBoothsMap1(Map<String, List<SelectOptionVO>> boothsMap1) {
		this.boothsMap1 = boothsMap1;
	}



	public List<BoothResultVO> getPerWiseboothResults() {
		return perWiseboothResults;
	}

	public void setPerWiseboothResults(List<BoothResultVO> perWiseboothResults) {
		this.perWiseboothResults = perWiseboothResults;
	}

	public PartyBoothPerformanceVO(){
		
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
	public long getTotalVotes() {
		return totalVotes;
	}

	public void setTotalVotes(long totalVotes) {
		this.totalVotes = totalVotes;
	}

	public String getVotingPercentage() {
		return votingPercentage;
	}

	public void setVotingPercentage(String votingPercentage) {
		this.votingPercentage = votingPercentage;
	}
	
	public List<BoothResultVO> getPartyPerWiseboothResults() {
		return partyPerWiseboothResults;
	}

	public void setPartyPerWiseboothResults(
			List<BoothResultVO> partyPerWiseboothResults) {
		this.partyPerWiseboothResults = partyPerWiseboothResults;
	}

}
