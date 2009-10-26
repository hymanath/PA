package com.itgrids.partyanalyst.excel;

import java.util.ArrayList;
import java.util.List;

public class PartyBoothPerformanceVO {
	
	private String partyName;
	private String candidateName;
	private String constituencyName;
	private String electionYear;
	private String electionType;
	private List<BoothResultVO> boothResults = new ArrayList<BoothResultVO>();
	
	public PartyBoothPerformanceVO(){
		
	}
	
	public PartyBoothPerformanceVO(String partyName, String candidateName,
			String electionYear, String electionType, List<BoothResultVO> boothResults) {
		this.partyName = partyName;
		this.candidateName = candidateName;
		this.electionYear = electionYear;
		this.electionType = electionType;
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
	
	

}
