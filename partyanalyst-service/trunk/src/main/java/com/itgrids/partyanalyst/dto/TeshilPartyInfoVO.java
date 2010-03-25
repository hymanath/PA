package com.itgrids.partyanalyst.dto;

import java.util.List;

public class TeshilPartyInfoVO {
	
	private String partyName;
	private Long participatedSeats;
	private Long totalValidVotes;
	private Long totalVotesWonByParty;
	private Long seatsWonByParty;
	private Float percentageOfVotesWonByParty;

	public Float getPercentageOfVotesWonByParty() {
		return percentageOfVotesWonByParty;
	}
	public void setPercentageOfVotesWonByParty(Float percentageOfVotesWonByParty) {
		this.percentageOfVotesWonByParty = percentageOfVotesWonByParty;
	}
	public String getPartyName() {
		return partyName;
	}
	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}
	public Long getParticipatedSeats() {
		return participatedSeats;
	}
	public void setParticipatedSeats(Long participatedSeats) {
		this.participatedSeats = participatedSeats;
	}
	public Long getTotalValidVotes() {
		return totalValidVotes;
	}
	public Long getSeatsWonByParty() {
		return seatsWonByParty;
	}
	public void setSeatsWonByParty(Long seatsWonByParty) {
		this.seatsWonByParty = seatsWonByParty;
	}
	public void setTotalValidVotes(Long totalValidVotes) {
		this.totalValidVotes = totalValidVotes;
	}
	public Long getTotalVotesWonByParty() {
		return totalVotesWonByParty;
	}
	public void setTotalVotesWonByParty(Long totalVotesWonByParty) {
		this.totalVotesWonByParty = totalVotesWonByParty;
	}
}
