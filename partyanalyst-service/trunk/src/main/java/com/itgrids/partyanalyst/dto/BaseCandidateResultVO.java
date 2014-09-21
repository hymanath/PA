package com.itgrids.partyanalyst.dto;

public class BaseCandidateResultVO {

	private Long candidateId;
	private String candidateName;
	
	private Long partyId;
	private String partyName;
	
	private Long count;
	
	private Long rank;
	
	private Double votesEarned;	
	private String votesPercengate;	
	private String marginVotesPercentage;
	private Double marginVotes;
	private String wonStatus;
	private Integer status;
	private String Reservation;
	
	
	public String getWonStatus() {
		return wonStatus;
	}

	public void setWonStatus(String wonStatus) {
		this.wonStatus = wonStatus;
	}

	public Long getCandidateId() {
		return candidateId;
	}

	public void setCandidateId(Long candidateId) {
		this.candidateId = candidateId;
	}

	public Long getPartyId() {
		return partyId;
	}

	public void setPartyId(Long partyId) {
		this.partyId = partyId;
	}

	public String getReservation() {
		return Reservation;
	}

	public void setReservation(String reservation) {
		Reservation = reservation;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Double getVotesEarned() {
		return votesEarned;
	}

	public void setVotesEarned(Double votesEarned) {
		this.votesEarned = votesEarned;
	}

	public String getVotesPercengate() {
		return votesPercengate;
	}

	public void setVotesPercengate(String votesPercengate) {
		this.votesPercengate = votesPercengate;
	}

	public String getMarginVotesPercentage() {
		return marginVotesPercentage;
	}

	public void setMarginVotesPercentage(String marginVotesPercentage) {
		this.marginVotesPercentage = marginVotesPercentage;
	}

	public Double getMarginVotes() {
		return marginVotes;
	}

	public void setMarginVotes(Double marginVotes) {
		this.marginVotes = marginVotes;
	}

	public Long getRank() {
		return rank;
	}

	public void setRank(Long rank) {
		this.rank = rank;
	}

	public String getCandidateName() {
		return candidateName;
	}

	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
	}

	public String getPartyName() {
		return partyName;
	}

	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}
	
	
	
}
