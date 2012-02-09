/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on March 06,2010
 */
package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class PartyElectionResultVO extends ConstituencyAreaTypeVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1689182456480682223L;

	private Long partyId;
	private Long electionId; 
	private String partyName;
	private String partyLogo;
	private String partyFlag;
	private Long candidateId;
	private String candidateName;
	private Long constiId;
	private String constiName;
	private Long votesEarned;
	private Long validVotes;
	private Long totalVoters;
	private Long maleVoters;
	private Long femaleVoters;
	private String votesPercentage = "0";
	private BigDecimal votesPercent = new BigDecimal(0);
	private Long rank;
	private Long totalBooths;
	private String status;
	private Long totalVotes;
	private String electionTypeYear;
	private Long townshipId;
	private String townshipName;
	private Long totalParticipated;
	private Long totalSeatsWon;
	private String completeVotesPercent;
	private String pVotesPercent;
	private Long malePerticipated;
	private Long femalePerticipated;
	private Long maleWon;
	private Long femaleWon;
	private String mVotesPercent;
	private String fVotesPercent;
	public Long mVotesPolled;
	public Long fVotesPolled;
	private BigDecimal assets;
	
	public Long getMVotesPolled() {
		return mVotesPolled;
	}
	public void setMVotesPolled(Long votesPolled) {
		mVotesPolled = votesPolled;
	}
	public Long getFVotesPolled() {
		return fVotesPolled;
	}
	public void setFVotesPolled(Long votesPolled) {
		fVotesPolled = votesPolled;
	}
	public Long getMalePerticipated() {
		return malePerticipated;
	}
	public void setMalePerticipated(Long malePerticipated) {
		this.malePerticipated = malePerticipated;
	}
	public Long getFemalePerticipated() {
		return femalePerticipated;
	}
	public void setFemalePerticipated(Long femalePerticipated) {
		this.femalePerticipated = femalePerticipated;
	}
	public Long getMaleWon() {
		return maleWon;
	}
	public void setMaleWon(Long maleWon) {
		this.maleWon = maleWon;
	}
	public Long getFemaleWon() {
		return femaleWon;
	}
	public void setFemaleWon(Long femaleWon) {
		this.femaleWon = femaleWon;
	}
	public String getMVotesPercent() {
		return mVotesPercent;
	}
	public void setMVotesPercent(String votesPercent) {
		mVotesPercent = votesPercent;
	}
	public String getFVotesPercent() {
		return fVotesPercent;
	}
	public void setFVotesPercent(String votesPercent) {
		fVotesPercent = votesPercent;
	}
	public String getCompleteVotesPercent() {
		return completeVotesPercent;
	}
	public void setCompleteVotesPercent(String completeVotesPercent) {
		this.completeVotesPercent = completeVotesPercent;
	}
	public String getPVotesPercent() {
		return pVotesPercent;
	}
	public void setPVotesPercent(String votesPercent) {
		pVotesPercent = votesPercent;
	}
	public Long getTotalSeatsWon() {
		return totalSeatsWon;
	}
	public void setTotalSeatsWon(Long totalSeatsWon) {
		this.totalSeatsWon = totalSeatsWon;
	}
	public Long getTotalParticipated() {
		return totalParticipated;
	}
	public void setTotalParticipated(Long totalParticipated) {
		this.totalParticipated = totalParticipated;
	}
	//getters and setters
	public Long getTotalVotes() {
		return totalVotes;
	}
	public void setTotalVotes(Long totalVotes) {
		this.totalVotes = totalVotes;
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
	public Long getConstiId() {
		return constiId;
	}
	public void setConstiId(Long constiId) {
		this.constiId = constiId;
	}
	public String getConstiName() {
		return constiName;
	}
	public void setConstiName(String constiName) {
		this.constiName = constiName;
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
	public Long getTotalVoters() {
		return totalVoters;
	}
	public void setTotalVoters(Long totalVoters) {
		this.totalVoters = totalVoters;
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
	public Long getMaleVoters() {
		return maleVoters;
	}
	public void setMaleVoters(Long maleVoters) {
		this.maleVoters = maleVoters;
	}
	public Long getFemaleVoters() {
		return femaleVoters;
	}
	public void setFemaleVoters(Long femaleVoters) {
		this.femaleVoters = femaleVoters;
	}
	public Long getTotalBooths() {
		return totalBooths;
	}
	public void setTotalBooths(Long totalBooths) {
		this.totalBooths = totalBooths;
	}
	public String getStatus() {
		return status;
	}
	public Long getElectionId() {
		return electionId;
	}
	public void setElectionId(Long electionId) {
		this.electionId = electionId;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getElectionTypeYear() {
		return electionTypeYear;
	}
	public void setElectionTypeYear(String electionTypeYear) {
		this.electionTypeYear = electionTypeYear;
	}
	public Long getTownshipId() {
		return townshipId;
	}
	public void setTownshipId(Long townshipId) {
		this.townshipId = townshipId;
	}
	public String getTownshipName() {
		return townshipName;
	}
	public void setTownshipName(String townshipName) {
		this.townshipName = townshipName;
	}
	public BigDecimal getVotesPercent() {
		return votesPercent;
	}
	public void setVotesPercent(BigDecimal votesPercent) {
		this.votesPercent = votesPercent;
	}
	public BigDecimal getAssets() {
		return assets;
	}
	public void setAssets(BigDecimal assets) {
		this.assets = assets;
	}
	
	
}
