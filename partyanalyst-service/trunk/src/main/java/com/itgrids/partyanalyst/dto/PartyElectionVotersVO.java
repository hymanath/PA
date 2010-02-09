package com.itgrids.partyanalyst.dto;

public class PartyElectionVotersVO implements  Comparable<PartyElectionVotersVO>{
	private String party;
	private Integer electionYear;
	private String electionType;
	private Long voterSize;
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
