/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on October 1, 2009
 */
package com.itgrids.partyanalyst.dto;

public class CandidateOppositionVO {

	/*
	 * 
	 */
	
	private Long partyId;
	private String partyName;
	private Long candidateId;
	private String candidateName;
	private String votesEarned;
	private String votesPercentage;
	private Long rank;
	private Boolean status;
	private String partyFlag;
	private String partyLongName;
	private String partyShortName;
	private String votesMargin;
	private String votesPercentMargin;

	//getters and setters

	public String getPartyShortName() {
		return partyShortName;
	}
	public void setPartyShortName(String partyShortName) {
		this.partyShortName = partyShortName;
	}
	public String getPartyLongName() {
		return partyLongName;
	}
	public void setPartyLongName(String partyLongName) {
		this.partyLongName = partyLongName;
	}
	public String getPartyFlag() {
		return partyFlag;
	}
	public void setPartyFlag(String partyFlag) {
		this.partyFlag = partyFlag;
	}
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
	public String getVotesEarned() {
		return votesEarned;
	}
	public void setVotesEarned(String votesEarned) {
		this.votesEarned = votesEarned;
	}
	public String getVotesPercentage() {
		return votesPercentage;
	}
	public void setVotesPercentage(String votesPercentage) {
		this.votesPercentage = votesPercentage;
	}
	public Long getRank() {
		return rank;
	}
	public void setRank(Long rank) {
		this.rank = rank;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	public String getVotesMargin() {
		return votesMargin;
	}
	public void setVotesMargin(String votesMargin) {
		this.votesMargin = votesMargin;
	}
	public String getVotesPercentMargin() {
		return votesPercentMargin;
	}
	public void setVotesPercentMargin(String votesPercentMargin) {
		this.votesPercentMargin = votesPercentMargin;
	}
	
}
