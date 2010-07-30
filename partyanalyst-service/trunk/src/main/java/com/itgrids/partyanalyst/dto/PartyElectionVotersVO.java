package com.itgrids.partyanalyst.dto;

public class PartyElectionVotersVO implements  Comparable<PartyElectionVotersVO>{
	private String party;
	private Integer electionYear;
	private String electionType;
	private Long voterSize;
	private Long presVotesEarned;
	private Long prevVotesEarned;
	private String presPercentage;
	private String prevPercentage;
	
	public String getParty() {
		return party;
	}
	public void setParty(String party) {
		this.party = party;
	}
	public Integer getElectionYear() {
		return electionYear;
	}
	public void setElectionYear(Integer electionYear) {
		this.electionYear = electionYear;
	}
	public String getElectionType() {
		return electionType;
	}
	public void setElectionType(String electionType) {
		this.electionType = electionType;
	}
	public Long getVoterSize() {
		return voterSize;
	}
	public void setVoterSize(Long voterSize) {
		this.voterSize = voterSize;
	}	
	
	public Long getPresVotesEarned() {
		return presVotesEarned;
	}
	
	public void setPresVotesEarned(Long presVotesEarned) {
		this.presVotesEarned = presVotesEarned;
	}
	
	public Long getPrevVotesEarned() {
		return prevVotesEarned;
	}
	
	public void setPrevVotesEarned(Long prevVotesEarned) {
		this.prevVotesEarned = prevVotesEarned;
	}
	
	public String getPresPercentage() {
		return presPercentage;
	}
	
	public void setPresPercentage(String presPercentage) {
		this.presPercentage = presPercentage;
	}

	public String getPrevPercentage() {
		return prevPercentage;
	}

	public void setPrevPercentage(String prevPercentage) {
		this.prevPercentage = prevPercentage;
	}
	
	public int compareTo(PartyElectionVotersVO obj){
		int result = this.party.compareTo(obj.getParty());
		if(result==0){
			result = this.electionYear.compareTo(obj.getElectionYear());
			if(result==0){
				result = this.electionType.compareTo(obj.getElectionType());
			}
		}
		
		return result;
	}
}
