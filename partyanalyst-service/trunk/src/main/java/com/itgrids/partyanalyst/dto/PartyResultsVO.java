/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on September 17, 2009
 */
package com.itgrids.partyanalyst.dto;

import java.util.List;

public class PartyResultsVO {

	private Long partyId;
	private String partyName;
	private Long candidateId;
	private String candidateName;
	private String partyFlag;
	private int seatsParticipated;
	private Long votesEarned;
	private Long validVotes;
	private String percentage;
	private int totalSeatsWon;
	private Long totalPolledVotes;
	private Long rank;
	private List<ConstituencyWisePartyInfoVO> constituencyWisePatiesInfoVOs;
	
	// getters and setters
	
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
	public int getTotalSeatsWon() {
		return totalSeatsWon;
	}
	public void setTotalSeatsWon(int totalSeatsWon) {
		this.totalSeatsWon = totalSeatsWon;
	}
	public List<ConstituencyWisePartyInfoVO> getConstituencyWisePatiesInfoVOs() {
		return constituencyWisePatiesInfoVOs;
	}
	public void setConstituencyWisePatiesInfoVOs(
			List<ConstituencyWisePartyInfoVO> constituencyWisePatiesInfoVOs) {
		this.constituencyWisePatiesInfoVOs = constituencyWisePatiesInfoVOs;
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
	
	public Long getTotalPolledVotes() {
		return totalPolledVotes;
	}
	
	public void setTotalPolledVotes(Long totalPolledVotes) {
		this.totalPolledVotes = totalPolledVotes;
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
	public Long getValidVotes() {
		return validVotes;
	}
	public void setValidVotes(Long validVotes) {
		this.validVotes = validVotes;
	}
	public Long getRank() {
		return rank;
	}
	public void setRank(Long rank) {
		this.rank = rank;
	}
	
	@Override
	public boolean equals(Object obj){
		if(!(obj instanceof PartyResultsVO))
			return false;
		PartyResultsVO voObj = (PartyResultsVO) obj;
		return this.partyId.equals(voObj.getPartyId());
	}
	
	@Override
	public int hashCode(){
		return this.partyId.hashCode();
	}
	
}
