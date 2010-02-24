package com.itgrids.partyanalyst.dto;

import java.io.Serializable;

public class PartyGenderWiseVotesVO implements Serializable {
	private Long partyID;
	private String partyName;
	private String candidateNameWithStatus;
	private Long candidateID;
	private Long totalVotesEarned;
	private Long maleBoothResults;
	private Long femaleBoothResults;
	private Long fmBoothResults;
	private String maleBoothResultsPercentage;
	private String femaleBoothResultsPercentage;
	private String fmBoothResultsPercentage;
	private String totalVotesEarnedPercentage;
	private Long rank;
	
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
	public Long getTotalVotesEarned() {
		return totalVotesEarned;
	}
	public void setTotalVotesEarned(Long totalVotesEarned) {
		this.totalVotesEarned = totalVotesEarned;
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
	
	@Override
	public boolean equals(Object obj){
		PartyGenderWiseVotesVO voObj = (PartyGenderWiseVotesVO) obj;
		return this.partyID.equals(voObj.getPartyID());
	}
	
	@Override
	public int hashCode(){
		return this.partyID.hashCode();
	}
	public String getMaleBoothResultsPercentage() {
		return maleBoothResultsPercentage;
	}
	public void setMaleBoothResultsPercentage(String maleBoothResultsPercentage) {
		this.maleBoothResultsPercentage = maleBoothResultsPercentage;
	}
	public String getFemaleBoothResultsPercentage() {
		return femaleBoothResultsPercentage;
	}
	public void setFemaleBoothResultsPercentage(String femaleBoothResultsPercentage) {
		this.femaleBoothResultsPercentage = femaleBoothResultsPercentage;
	}
	public String getFmBoothResultsPercentage() {
		return fmBoothResultsPercentage;
	}
	public void setFmBoothResultsPercentage(String fmBoothResultsPercentage) {
		this.fmBoothResultsPercentage = fmBoothResultsPercentage;
	}
	public String getTotalVotesEarnedPercentage() {
		return totalVotesEarnedPercentage;
	}
	public void setTotalVotesEarnedPercentage(String totalVotesEarnedPercentage) {
		this.totalVotesEarnedPercentage = totalVotesEarnedPercentage;
	}
	public Long getRank() {
		return rank;
	}
	public void setRank(Long rank) {
		this.rank = rank;
	}
}
