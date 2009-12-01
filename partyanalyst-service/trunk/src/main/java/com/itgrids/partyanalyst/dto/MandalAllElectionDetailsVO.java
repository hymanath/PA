package com.itgrids.partyanalyst.dto;

import java.io.Serializable;

public class MandalAllElectionDetailsVO implements Serializable {
	private String tehsilName;
	private String electionType;
	private String electionYear;
	private Long totalVoters;
	private Long validVoters;
	private Long rejectedVoters;
	private Long tenderedVoters;
	private String candidateName;
	private String partyVotesPercentage;
	private Long electionScopeID;
	private String partyShortName;
	private Long electionID;
	private Long electionTypeID;
	
	public String getTehsilName() {
		return tehsilName;
	}
	public void setTehsilName(String tehsilName) {
		this.tehsilName = tehsilName;
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
	public Long getTotalVoters() {
		return totalVoters;
	}
	public void setTotalVoters(Long totalVoters) {
		this.totalVoters = totalVoters;
	}
	
	public Long getValidVoters() {
		return validVoters;
	}
	public void setValidVoters(Long validVoters) {
		this.validVoters = validVoters;
	}
	public Long getRejectedVoters() {
		return rejectedVoters;
	}
	public void setRejectedVoters(Long rejectedVoters) {
		this.rejectedVoters = rejectedVoters;
	}
	public Long getTenderedVoters() {
		return tenderedVoters;
	}
	public void setTenderedVoters(Long tenderedVoters) {
		this.tenderedVoters = tenderedVoters;
	}
	public String getCandidateName() {
		return candidateName;
	}
	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
	}
	public String getPartyVotesPercentage() {
		return partyVotesPercentage;
	}
	public void setPartyVotesPercentage(String partyVotesPercentage) {
		this.partyVotesPercentage = partyVotesPercentage;
	}
	public Long getElectionScopeID() {
		return electionScopeID;
	}
	public void setElectionScopeID(Long electionScopeID) {
		this.electionScopeID = electionScopeID;
	}
	public String getPartyShortName() {
		return partyShortName;
	}
	public void setPartyShortName(String partyShortName) {
		this.partyShortName = partyShortName;
	}
	public Long getElectionID() {
		return electionID;
	}
	public void setElectionID(Long electionID) {
		this.electionID = electionID;
	}
	public Long getElectionTypeID() {
		return electionTypeID;
	}
	public void setElectionTypeID(Long electionTypeID) {
		this.electionTypeID = electionTypeID;
	}
	
}
