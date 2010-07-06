/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on July 5, 2010
 */
package com.itgrids.partyanalyst.dto;

import java.io.Serializable;

public class PartyVillageLevelAnalysisVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long partyId;
	private String partyName;
	private String electionType;
	private String electionYear;
	
	private int wonVillagesCount;
	private Long totVotesEarned;
	private Long totValidVotes;
	private String votesShareInVill;
	private String votesShareInMandal;
	private String votesShareInConsti;
	
	
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
	public String getElectionType() {
		return electionType;
	}
	public void setElectionType(String electionType) {
		this.electionType = electionType;
	}
	public String getElectionYear() {
		return electionYear;
	}
	public void setElectionYear(String electionYear) {
		this.electionYear = electionYear;
	}
	public int getWonVillagesCount() {
		return wonVillagesCount;
	}
	public void setWonVillagesCount(int wonVillagesCount) {
		this.wonVillagesCount = wonVillagesCount;
	}
	public String getVotesShareInVill() {
		return votesShareInVill;
	}
	public void setVotesShareInVill(String votesShareInVill) {
		this.votesShareInVill = votesShareInVill;
	}
	public String getVotesShareInMandal() {
		return votesShareInMandal;
	}
	public void setVotesShareInMandal(String votesShareInMandal) {
		this.votesShareInMandal = votesShareInMandal;
	}
	public String getVotesShareInConsti() {
		return votesShareInConsti;
	}
	public void setVotesShareInConsti(String votesShareInConsti) {
		this.votesShareInConsti = votesShareInConsti;
	}
	public Long getTotVotesEarned() {
		return totVotesEarned;
	}
	public void setTotVotesEarned(Long totVotesEarned) {
		this.totVotesEarned = totVotesEarned;
	}
	public Long getTotValidVotes() {
		return totValidVotes;
	}
	public void setTotValidVotes(Long totValidVotes) {
		this.totValidVotes = totValidVotes;
	}
}
