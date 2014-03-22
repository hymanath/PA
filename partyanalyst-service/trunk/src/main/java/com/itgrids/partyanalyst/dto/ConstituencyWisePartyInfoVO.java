package com.itgrids.partyanalyst.dto;

import java.io.Serializable;

public class ConstituencyWisePartyInfoVO implements Serializable{

	private Long constituencyId;
	private String constituencyName;
	private Long candidateId;
	private String candidateName;
	private Long rank;
	private Long votesEarned;
	private String percentage;

	public Long getConstituencyId() {
		return constituencyId;
	}
	
	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}
	
	public String getConstituencyName() {
		return constituencyName;
	}
	
	public void setConstituencyName(String constituencyName) {
		this.constituencyName = constituencyName;
	}
	
	public Long getCandidateId() {
		return candidateId;
	}
	
	public void setCandidateId(Long candidateId) {
		this.candidateId = candidateId;
	}
	
	public String getCandidateName() {
		return candidateName;
	}
	
	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
	}
	
	public Long getRank() {
		return rank;
	}
	
	public void setRank(Long rank) {
		this.rank = rank;
	}
	
	public Long getVotesEarned() {
		return votesEarned;
	}
	
	public void setVotesEarned(Long votesEarned) {
		this.votesEarned = votesEarned;
	}
	
	public String getPercentage() {
		return percentage;
	}
	
	public void setPercentage(String percentage) {
		this.percentage = percentage;
	}	
	
}
