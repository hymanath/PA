/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on February 08,2010
 */
package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.List;

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
	private Long totalVotesEarned;
	private Long totalValidVotes;
	private Long totalConstiValidVotes;
	private Long totalVotesForState;
	private Long totalValidVotesForState;
	private Long totalPolledVotesForState;
	private Double totalVotingPercentageForState;
	private Long totalSeatsParticipated;
	
	public Long getTotalPolledVotesForState() {
		return totalPolledVotesForState;
	}
	public void setTotalPolledVotesForState(Long totalPolledVotesForState) {
		this.totalPolledVotesForState = totalPolledVotesForState;
	}
	public Long getTotalSeatsParticipated() {
		return totalSeatsParticipated;
	}
	public void setTotalSeatsParticipated(Long totalSeatsParticipated) {
		this.totalSeatsParticipated = totalSeatsParticipated;
	}
	public void setTotalVotingPercentageForState(Double totalVotingPercentageForState) {
		this.totalVotingPercentageForState = totalVotingPercentageForState;
	}
	public Double getTotalVotingPercentageForState() {
		return totalVotingPercentageForState;
	}
	public Long getTotalValidVotesForState() {
		return  totalValidVotesForState;
	}
	public void setTotalValidVotesForState(Long totalValidVotesForState) {
		this.totalValidVotesForState = totalValidVotesForState;
	
	}
	public Long getTotalVotesForState() {
		return totalVotesForState;
	}
	public void setTotalVotesForState(Long totalVotesForState) {
		this.totalVotesForState =  totalVotesForState;
	}
	
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
	public Long getTotalVotesEarned() {
		return totalVotesEarned;
	}
	public void setTotalVotesEarned(Long totalVotesEarned) {
		this.totalVotesEarned = totalVotesEarned;
	}
	public Long getTotalValidVotes() {
		return totalValidVotes;
	}
	public void setTotalValidVotes(Long totalValidVotes) {
		this.totalValidVotes = totalValidVotes;
	}
	public Long getTotalConstiValidVotes() {
		return totalConstiValidVotes;
	}
	public void setTotalConstiValidVotes(Long totalConstiValidVotes) {
		this.totalConstiValidVotes = totalConstiValidVotes;
	}
}
