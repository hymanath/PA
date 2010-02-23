package com.itgrids.partyanalyst.dto;

import java.io.Serializable;

public class PartyGenderWiseVotesVO implements Serializable {
	private Long partyID;
	private String partyName;
	private String candidateNameWithStatus;
	private Long candidateID;
	private Long totalVotes;
	private Long maleBoothResults;
	private Long femaleBoothResults;
	private Long fmBoothResults;
	
	public Long getPartyID() {
		return partyID;
	}
	public void setPartyID(Long partyID) {
		this.partyID = partyID;
	}
	public String getPartyName() {
		return partyName;
	}
	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}
	public String getCandidateNameWithStatus() {
		return candidateNameWithStatus;
	}
	public void setCandidateNameWithStatus(String candidateNameWithStatus) {
		this.candidateNameWithStatus = candidateNameWithStatus;
	}
	public Long getCandidateID() {
		return candidateID;
	}
	public void setCandidateID(Long candidateID) {
		this.candidateID = candidateID;
	}
	public Long getTotalVotes() {
		return totalVotes;
	}
	public void setTotalVotes(Long totalVotes) {
		this.totalVotes = totalVotes;
	}
	public Long getMaleBoothResults() {
		return maleBoothResults;
	}
	public void setMaleBoothResults(Long maleBoothResults) {
		this.maleBoothResults = maleBoothResults;
	}
	public Long getFemaleBoothResults() {
		return femaleBoothResults;
	}
	public void setFemaleBoothResults(Long femaleBoothResults) {
		this.femaleBoothResults = femaleBoothResults;
	}
	public Long getFmBoothResults() {
		return fmBoothResults;
	}
	public void setFmBoothResults(Long fmBoothResults) {
		this.fmBoothResults = fmBoothResults;
	}
}
