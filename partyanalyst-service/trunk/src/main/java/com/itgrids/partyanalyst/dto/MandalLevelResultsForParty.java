/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on Jun 17,2010
 */

package com.itgrids.partyanalyst.dto;

import java.io.Serializable;

public class MandalLevelResultsForParty implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long totalVoters;
	private Long polledVotes;
	private Long votesEarned;
	private Long totalBooths;
	private String votesPercent;
	
	private String oppParty;
	private Long oppVotesEarned;
	private Long oppValidVotes;
	private String oppVotesPercent;
	
	
	public Long getTotalVoters() {
		return totalVoters;
	}
	public void setTotalVoters(Long totalVoters) {
		this.totalVoters = totalVoters;
	}
	public Long getPolledVotes() {
		return polledVotes;
	}
	public void setPolledVotes(Long polledVotes) {
		this.polledVotes = polledVotes;
	}
	public Long getVotesEarned() {
		return votesEarned;
	}
	public void setVotesEarned(Long votesEarned) {
		this.votesEarned = votesEarned;
	}
	public Long getTotalBooths() {
		return totalBooths;
	}
	public void setTotalBooths(Long totalBooths) {
		this.totalBooths = totalBooths;
	}
	public String getVotesPercent() {
		return votesPercent;
	}
	public void setVotesPercent(String votesPercent) {
		this.votesPercent = votesPercent;
	}
	public String getOppParty() {
		return oppParty;
	}
	public void setOppParty(String oppParty) {
		this.oppParty = oppParty;
	}
	public Long getOppVotesEarned() {
		return oppVotesEarned;
	}
	public void setOppVotesEarned(Long oppVotesEarned) {
		this.oppVotesEarned = oppVotesEarned;
	}
	public Long getOppValidVotes() {
		return oppValidVotes;
	}
	public void setOppValidVotes(Long oppValidVotes) {
		this.oppValidVotes = oppValidVotes;
	}
	public String getOppVotesPercent() {
		return oppVotesPercent;
	}
	public void setOppVotesPercent(String oppVotesPercent) {
		this.oppVotesPercent = oppVotesPercent;
	}

}
