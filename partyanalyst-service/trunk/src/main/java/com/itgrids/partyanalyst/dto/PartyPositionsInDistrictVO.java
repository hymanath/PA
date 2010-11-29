/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on April 07,2010
 */
package com.itgrids.partyanalyst.dto;

import java.io.Serializable;

public class PartyPositionsInDistrictVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6988911071267468461L;
	
	private Long nthPos;
	private Long SeatsWon;
	private Long thirdPos;
	private Long secondPos;
	private Long fourthPos;
	private Long districtId;
	private String districtName;
	private Long totalValidVotes;
	private Long totalVotesEarned;
	private String votesPercentage;
	private Long totalConstituencies;
	private Long totalConstiValidVotes;
	private String completeVotesPercent;
	private Double completeVotesPercentDouble;
	private Long totalConstiParticipated;
	
	//getters and setters
	public Long getNthPos() {
		return nthPos;
	}
	public void setNthPos(Long nthPos) {
		this.nthPos = nthPos;
	}
	public Long getSeatsWon() {
		return SeatsWon;
	}
	public void setSeatsWon(Long seatsWon) {
		SeatsWon = seatsWon;
	}
	public Long getThirdPos() {
		return thirdPos;
	}
	public void setThirdPos(Long thirdPos) {
		this.thirdPos = thirdPos;
	}
	public Long getSecondPos() {
		return secondPos;
	}
	public void setSecondPos(Long secondPos) {
		this.secondPos = secondPos;
	}
	public Long getFourthPos() {
		return fourthPos;
	}
	public void setFourthPos(Long fourthPos) {
		this.fourthPos = fourthPos;
	}
	public Long getDistrictId() {
		return districtId;
	}
	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}
	public String getDistrictName() {
		return districtName;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	public Long getTotalValidVotes() {
		return totalValidVotes;
	}
	public void setTotalValidVotes(Long totalValidVotes) {
		this.totalValidVotes = totalValidVotes;
	}
	public Long getTotalVotesEarned() {
		return totalVotesEarned;
	}
	public void setTotalVotesEarned(Long totalVotesEarned) {
		this.totalVotesEarned = totalVotesEarned;
	}
	public String getVotesPercentage() {
		return votesPercentage;
	}
	public void setVotesPercentage(String votesPercentage) {
		this.votesPercentage = votesPercentage;
	}
	public Long getTotalConstituencies() {
		return totalConstituencies;
	}
	public void setTotalConstituencies(Long totalConstituencies) {
		this.totalConstituencies = totalConstituencies;
	}
	public Long getTotalConstiValidVotes() {
		return totalConstiValidVotes;
	}
	public void setTotalConstiValidVotes(Long totalConstiValidVotes) {
		this.totalConstiValidVotes = totalConstiValidVotes;
	}
	public String getCompleteVotesPercent() {
		return completeVotesPercent;
	}
	public void setCompleteVotesPercent(String completeVotesPercent) {
		this.completeVotesPercent = completeVotesPercent;
	}
	public Long getTotalConstiParticipated() {
		return totalConstiParticipated;
	}
	public void setTotalConstiParticipated(Long totalConstiParticipated) {
		this.totalConstiParticipated = totalConstiParticipated;
	}
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
	public Double getCompleteVotesPercentDouble() {
		return completeVotesPercentDouble;
	}
	public void setCompleteVotesPercentDouble(Double completeVotesPercentDouble) {
		this.completeVotesPercentDouble = completeVotesPercentDouble;
	}

}
