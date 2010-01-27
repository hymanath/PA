package com.itgrids.partyanalyst.dto;

import java.io.Serializable;

public class MPTCMandalLeaderVO implements Serializable {

	/*System.out.print(obj[0]+" -   ");//electionYear
	System.out.print(obj[1]+" -   ");//rank
	System.out.print(obj[2]+" -   ");//mptc name
	System.out.print(obj[3]+" -   ");//party
	System.out.print(obj[4]+" -   ");//lastname
	System.out.print(obj[5]+" -   ");//votes earned by candidate
	System.out.print(obj[6]);//constituency valid votes 
*/
	
	private String electionYear;
	private Long rank;
	private String mptcName;
	private String party;
	private String candidateName;
	private Double candidateEarnedVotes;
	private Double validVotes;
	
	public String getElectionYear() {
		return electionYear;
	}
	public void setElectionYear(String electionYear) {
		this.electionYear = electionYear;
	}
	public Long getRank() {
		return rank;
	}
	public void setRank(Long rank) {
		this.rank = rank;
	}
	public String getMptcName() {
		return mptcName;
	}
	public void setMptcName(String mptcName) {
		this.mptcName = mptcName;
	}
	public String getParty() {
		return party;
	}
	public void setParty(String party) {
		this.party = party;
	}
	public String getCandidateName() {
		return candidateName;
	}
	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
	}
	public Double getCandidateEarnedVotes() {
		return candidateEarnedVotes;
	}
	public void setCandidateEarnedVotes(Double candidateEarnedVotes) {
		this.candidateEarnedVotes = candidateEarnedVotes;
	}
	public Double getValidVotes() {
		return validVotes;
	}
	public void setValidVotes(Double validVotes) {
		this.validVotes = validVotes;
	}
	
	

}
