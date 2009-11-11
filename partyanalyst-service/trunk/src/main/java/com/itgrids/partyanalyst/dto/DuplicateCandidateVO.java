package com.itgrids.partyanalyst.dto;

public class DuplicateCandidateVO {

	private String candidateName;
	private String constituencyName;
	private String electionType;
	private String electionYear;
	
	public DuplicateCandidateVO(){
		
	}
	
	public DuplicateCandidateVO(String candidateName, String constituencyName,
			String electionType, String electionYear) {
		this.candidateName = candidateName;
		this.constituencyName = constituencyName;
		this.electionType = electionType;
		this.electionYear = electionYear;
	}

	public String getCandidateName() {
		return candidateName;
	}

	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
	}

	public String getConstituencyName() {
		return constituencyName;
	}

	public void setConstituencyName(String constituencyName) {
		this.constituencyName = constituencyName;
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
	
}
