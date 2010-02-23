package com.itgrids.partyanalyst.dto;

import java.util.List;

public class ElectionInfoVO {

	private String electionTypeYear;
	private String constituencyName;
	private String constituencyId;
	private List<PartyResultsInfoVO> partyResults;
	
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
	
	
	
}
