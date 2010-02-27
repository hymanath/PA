/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on February 08,2010
 */
package com.itgrids.partyanalyst.dto;

import java.io.Serializable;

public class PartyPositionsVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3958738425459624269L;
	
	private Long partyId;
	private String partyName;
	private Long totalConstiParticipated;
	private Long totalConstituencies;
	private Long totalSeatsWon;
	private Long secondPosWon;
	private Long thirdPosWon;
	private Long fourthPosWon;
	private Long nthPosWon;
	private String votesPercentage;
	private String completeVotesPercent;
	
	
	public Long getPartyId() {
		return partyId;
	}
	public void setPartyId(Long partyId) {
		this.partyId = partyId;
	}
	public Long getTotalConstiParticipated() {
		return totalConstiParticipated;
	}
	public void setTotalConstiParticipated(Long totalConstiParticipated) {
		this.totalConstiParticipated = totalConstiParticipated;
	}
	public Long getTotalConstituencies() {
		return totalConstituencies;
	}
	public void setTotalConstituencies(Long totalConstituencies) {
		this.totalConstituencies = totalConstituencies;
	}
	public Long getTotalSeatsWon() {
		return totalSeatsWon;
	}
	public void setTotalSeatsWon(Long totalSeatsWon) {
		this.totalSeatsWon = totalSeatsWon;
	}
	public Long getSecondPosWon() {
		return secondPosWon;
	}
	public void setSecondPosWon(Long secondPosWon) {
		this.secondPosWon = secondPosWon;
	}
	public Long getThirdPosWon() {
		return thirdPosWon;
	}
	public void setThirdPosWon(Long thirdPosWon) {
		this.thirdPosWon = thirdPosWon;
	}
	public Long getFourthPosWon() {
		return fourthPosWon;
	}
	public void setFourthPosWon(Long fourthPosWon) {
		this.fourthPosWon = fourthPosWon;
	}
	public Long getNthPosWon() {
		return nthPosWon;
	}
	public void setNthPosWon(Long nthPosWon) {
		this.nthPosWon = nthPosWon;
	}
	public String getVotesPercentage() {
		return votesPercentage;
	}
	public void setVotesPercentage(String votesPercentage) {
		this.votesPercentage = votesPercentage;
	}
	public String getPartyName() {
		return partyName;
	}
	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}
	public String getCompleteVotesPercent() {
		return completeVotesPercent;
	}
	public void setCompleteVotesPercent(String completeVotesPercent) {
		this.completeVotesPercent = completeVotesPercent;
	}
}
