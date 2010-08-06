/* 
 * Copyright (c) 2010 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on August 05, 2010
 */
package com.itgrids.partyanalyst.dto;

import java.io.Serializable;

/*
 * @author Sai Krishna Basetti
 */
public class CandidateElectionCompleteResultsVO extends CandidateBasicInformationVO
		implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/** Constituency Election Details */
	private ConstituencyElectionDetailsVO constituencyElectionDetails;
	/** Party Election Details */
	private PartyElectionDetailsVO partyElectionDetails;
	/** Votes Earned By Candidate */
	private Long candidateVotesEarned;
	/** Votes Percentage Of Candidate */
	private Double votesPercentage;
	/** Votes Percentage As String */
	private String votesPercentAsString;
	/** Rank Of Candidate */
	private Long rank;
	/** Margin Votes Earned*/
	private Long marginVotesEarned;
	/** Margin Votes Percentage */
	private Double marginVotesPercentage;
	/** Margin Votes Percentage As String */
	private String marginVotesPercentAsString;
	
	
	/** Male Votes Earned By Candidate */
	private Long maleVotesEarned;
	/** Male Votes Percentage */
	private Double maleVotesPercentage;
	/** Male Votes Percent As String */
	private String maleVotesPercentAsString;
	
	/** Female Votes Earned By Candidate */
	private Long femaleVotesEarned;
	/** Female Votes Percentage */
	private Double femaleVotesPercentage;
	/** Female Votes Percent As String */
	private String femaleVotesPercentAsString;
	
	/** Male/Female Votes Earned By Candidate */
	private Long mfVotesEarned;
	/** Male/Female Votes Percentage */
	private Double mfVotesPercentage;
	/** Male/Female Votes Percent As String */
	private String mfVotesPercentAsString;
	
	/** Getters And Setters */
	public ConstituencyElectionDetailsVO getConstituencyElectionDetails() {
		return constituencyElectionDetails;
	}
	public void setConstituencyElectionDetails(
			ConstituencyElectionDetailsVO constituencyElectionDetails) {
		this.constituencyElectionDetails = constituencyElectionDetails;
	}
	public PartyElectionDetailsVO getPartyElectionDetails() {
		return partyElectionDetails;
	}
	public void setPartyElectionDetails(PartyElectionDetailsVO partyElectionDetails) {
		this.partyElectionDetails = partyElectionDetails;
	}
	public Long getCandidateVotesEarned() {
		return candidateVotesEarned;
	}
	public void setCandidateVotesEarned(Long candidateVotesEarned) {
		this.candidateVotesEarned = candidateVotesEarned;
	}
	public Double getVotesPercentage() {
		return votesPercentage;
	}
	public void setVotesPercentage(Double votesPercentage) {
		this.votesPercentage = votesPercentage;
	}
	public String getVotesPercentAsString() {
		return votesPercentAsString;
	}
	public void setVotesPercentAsString(String votesPercentAsString) {
		this.votesPercentAsString = votesPercentAsString;
	}
	public Long getRank() {
		return rank;
	}
	public void setRank(Long rank) {
		this.rank = rank;
	}
	public Long getMaleVotesEarned() {
		return maleVotesEarned;
	}
	public void setMaleVotesEarned(Long maleVotesEarned) {
		this.maleVotesEarned = maleVotesEarned;
	}
	public Double getMaleVotesPercentage() {
		return maleVotesPercentage;
	}
	public void setMaleVotesPercentage(Double maleVotesPercentage) {
		this.maleVotesPercentage = maleVotesPercentage;
	}
	public String getMaleVotesPercentAsString() {
		return maleVotesPercentAsString;
	}
	public void setMaleVotesPercentAsString(String maleVotesPercentAsString) {
		this.maleVotesPercentAsString = maleVotesPercentAsString;
	}
	public Long getFemaleVotesEarned() {
		return femaleVotesEarned;
	}
	public void setFemaleVotesEarned(Long femaleVotesEarned) {
		this.femaleVotesEarned = femaleVotesEarned;
	}
	public Double getFemaleVotesPercentage() {
		return femaleVotesPercentage;
	}
	public void setFemaleVotesPercentage(Double femaleVotesPercentage) {
		this.femaleVotesPercentage = femaleVotesPercentage;
	}
	public String getFemaleVotesPercentAsString() {
		return femaleVotesPercentAsString;
	}
	public void setFemaleVotesPercentAsString(String femaleVotesPercentAsString) {
		this.femaleVotesPercentAsString = femaleVotesPercentAsString;
	}
	public Long getMfVotesEarned() {
		return mfVotesEarned;
	}
	public void setMfVotesEarned(Long mfVotesEarned) {
		this.mfVotesEarned = mfVotesEarned;
	}
	public Double getMfVotesPercentage() {
		return mfVotesPercentage;
	}
	public void setMfVotesPercentage(Double mfVotesPercentage) {
		this.mfVotesPercentage = mfVotesPercentage;
	}
	public String getMfVotesPercentAsString() {
		return mfVotesPercentAsString;
	}
	public void setMfVotesPercentAsString(String mfVotesPercentAsString) {
		this.mfVotesPercentAsString = mfVotesPercentAsString;
	}
	public Long getMarginVotesEarned() {
		return marginVotesEarned;
	}
	public void setMarginVotesEarned(Long marginVotesEarned) {
		this.marginVotesEarned = marginVotesEarned;
	}
	public Double getMarginVotesPercentage() {
		return marginVotesPercentage;
	}
	public void setMarginVotesPercentage(Double marginVotesPercentage) {
		this.marginVotesPercentage = marginVotesPercentage;
	}
	public String getMarginVotesPercentAsString() {
		return marginVotesPercentAsString;
	}
	public void setMarginVotesPercentAsString(String marginVotesPercentAsString) {
		this.marginVotesPercentAsString = marginVotesPercentAsString;
	}

}
