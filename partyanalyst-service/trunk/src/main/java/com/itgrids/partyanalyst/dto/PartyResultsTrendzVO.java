/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on March 06,2010
 */
package com.itgrids.partyanalyst.dto;

import java.io.Serializable;

public class PartyResultsTrendzVO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2707064198259860479L;
	
	private Long partyId;
	private String partyName;
	private String partyLogo;
	private String partyFlag;
	private Long candidateId;
	private String candidateName;
	private Long votesEarned;
	private Long validVotes;
	private Long totalVotes;
	private Long maleVotes;
	private Long femaleVotes;
	private Long maleAndFemaleVotes;
	private String totalVotesPercent;
	private String maleVotesPercent;
	private String femaleVotesPercent;
	private String maleAndFemaleVotesPercent;
	private Long rank;
	private String status;
	
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
	public String getPartyLogo() {
		return partyLogo;
	}
	public void setPartyLogo(String partyLogo) {
		this.partyLogo = partyLogo;
	}
	public String getPartyFlag() {
		return partyFlag;
	}
	public void setPartyFlag(String partyFlag) {
		this.partyFlag = partyFlag;
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
	public Long getVotesEarned() {
		return votesEarned;
	}
	public void setVotesEarned(Long votesEarned) {
		this.votesEarned = votesEarned;
	}
	public Long getValidVotes() {
		return validVotes;
	}
	public void setValidVotes(Long validVotes) {
		this.validVotes = validVotes;
	}
	public Long getTotalVotes() {
		return totalVotes;
	}
	public void setTotalVotes(Long totalVotes) {
		this.totalVotes = totalVotes;
	}
	public Long getMaleVotes() {
		return maleVotes;
	}
	public void setMaleVotes(Long maleVotes) {
		this.maleVotes = maleVotes;
	}
	public Long getFemaleVotes() {
		return femaleVotes;
	}
	public void setFemaleVotes(Long femaleVotes) {
		this.femaleVotes = femaleVotes;
	}
	
	public String getTotalVotesPercent() {
		return totalVotesPercent;
	}
	public void setTotalVotesPercent(String totalVotesPercent) {
		this.totalVotesPercent = totalVotesPercent;
	}
	public String getMaleVotesPercent() {
		return maleVotesPercent;
	}
	public void setMaleVotesPercent(String maleVotesPercent) {
		this.maleVotesPercent = maleVotesPercent;
	}
	public String getFemaleVotesPercent() {
		return femaleVotesPercent;
	}
	public void setFemaleVotesPercent(String femaleVotesPercent) {
		this.femaleVotesPercent = femaleVotesPercent;
	}
	
	public Long getRank() {
		return rank;
	}
	public void setRank(Long rank) {
		this.rank = rank;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public Long getMaleAndFemaleVotes() {
		return maleAndFemaleVotes;
	}
	public void setMaleAndFemaleVotes(Long maleAndFemaleVotes) {
		this.maleAndFemaleVotes = maleAndFemaleVotes;
	}
	public String getMaleAndFemaleVotesPercent() {
		return maleAndFemaleVotesPercent;
	}
	public void setMaleAndFemaleVotesPercent(String maleAndFemaleVotesPercent) {
		this.maleAndFemaleVotesPercent = maleAndFemaleVotesPercent;
	}
}
