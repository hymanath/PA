/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on March 15,2010
 */
package com.itgrids.partyanalyst.dto;

public class PartyWiseResultVO implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8515899710684470730L;

	private Long partyId;
	private String partyName;
	private String partyFlag;
	private int seatsParticipated;
	private Long votesEarned;
	private String percentage;
	private Long totalSeatsWon;
	
	//getters and setters
	public Long getPartyId() {
		return partyId;
	}
	public void setPartyId(Long partyId) {
		this.partyId = partyId;
	}
	public String getPartyName() {
		return partyName;
	}
	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}
	public String getPartyFlag() {
		return partyFlag;
	}
	public void setPartyFlag(String partyFlag) {
		this.partyFlag = partyFlag;
	}
	public int getSeatsParticipated() {
		return seatsParticipated;
	}
	public void setSeatsParticipated(int seatsParticipated) {
		this.seatsParticipated = seatsParticipated;
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
	public Long getTotalSeatsWon() {
		return totalSeatsWon;
	}
	public void setTotalSeatsWon(Long totalSeatsWon) {
		this.totalSeatsWon = totalSeatsWon;
	}
}
