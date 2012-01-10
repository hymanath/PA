package com.itgrids.partyanalyst.dto;

public class CandidatePartyInfoVO {

	private String candidateName;
	private Long candidateId;
	private String party;
	private Long partyId;
	private Long rank;
	private Long votesEarned;
	
	public Long getVotesEarned() {
		return votesEarned;
	}

	public void setVotesEarned(Long votesEarned) {
		this.votesEarned = votesEarned;
	}

	public String getCandidateName() {
		return candidateName;
	}
	
	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
	}
	
	public Long getCandidateId() {
		return candidateId;
	}
	
	public void setCandidateId(Long candidateId) {
		this.candidateId = candidateId;
	}
	
	public String getParty() {
		return party;
	}
	
	public void setParty(String party) {
		this.party = party;
	}
	
	public Long getPartyId() {
		return partyId;
	}
	
	public void setPartyId(Long partyId) {
		this.partyId = partyId;
	}
	
	public Long getRank() {
		return rank;
	}
	
	public void setRank(Long rank) {
		this.rank = rank;
	}
	
	
}
