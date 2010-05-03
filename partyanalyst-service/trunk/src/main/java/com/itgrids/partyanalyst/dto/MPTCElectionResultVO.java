package com.itgrids.partyanalyst.dto;

import java.io.Serializable;

public class MPTCElectionResultVO  extends ResultStatus implements Serializable {
	private Integer constituencyElections = 0;
	private Integer constituencyElectionResults = 0;
	private Integer nominations = 0;
	private Integer parties = 0;
	private Integer candidates = 0;
	private Integer candidateResults = 0;
	private Integer currentRow;
	private String electionType;
	
	public Integer getConstituencyElections() {
		return constituencyElections;
	}
	public void addConstituencyElections() {
		this.constituencyElections++;
	}
	public Integer getConstituencyElectionResults() {
		return constituencyElectionResults;
	}
	public void addConstituencyElectionResults() {
		this.constituencyElectionResults++;
	}
	public Integer getNominations() {
		return nominations;
	}
	public void addNominations() {
		this.nominations++;
	}
	public Integer getParties() {
		return parties;
	}
	public void addParties() {
		this.parties++;
	}
	public Integer getCandidates() {
		return candidates;
	}
	public void addCandidates() {
		this.candidates++;
	}
	public Integer getCandidateResults() {
		return candidateResults;
	}
	public void addCandidateResults() {
		this.candidateResults++;
	}
	public Integer getCurrentRow() {
		return currentRow;
	}
	public void setCurrentRow(Integer currentRow) {
		this.currentRow = currentRow;
	}
	public String getElectionType() {
		return electionType;
	}
	public void setElectionType(String electionType) {
		this.electionType = electionType;
	}
	
}
