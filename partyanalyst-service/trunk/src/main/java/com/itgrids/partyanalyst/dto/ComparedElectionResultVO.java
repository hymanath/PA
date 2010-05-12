/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on February 18,2010.
 */
package com.itgrids.partyanalyst.dto;

public class ComparedElectionResultVO implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4969671778993399297L;
    
	private String candName;
	private String constiName;
	private String partyName;
	private Long rank;
	private Long votesEarned;
	private String votesPercent;
	private String secndCandName;
	private String secndConstiName;
	private Long votesEarnedBySecnd;
	private String secndVotesPercent;
	private String secndCandPartyName;
	private Long secndCandRank;
	private String votesPercentDiff;
	
	
	public String getCandName() {
		return candName;
	}
	public void setCandName(String candName) {
		this.candName = candName;
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
	public String getVotesPercent() {
		return votesPercent;
	}
	public void setVotesPercent(String votesPercent) {
		this.votesPercent = votesPercent;
	}
	public String getSecndCandName() {
		return secndCandName;
	}
	public void setSecndCandName(String secndCandName) {
		this.secndCandName = secndCandName;
	}
	public String getSecndConstiName() {
		return secndConstiName;
	}
	public void setSecndConstiName(String secndConstiName) {
		this.secndConstiName = secndConstiName;
	}
	public Long getVotesEarnedBySecnd() {
		return votesEarnedBySecnd;
	}
	public void setVotesEarnedBySecnd(Long votesEarnedBySecnd) {
		this.votesEarnedBySecnd = votesEarnedBySecnd;
	}
	public String getSecndVotesPercent() {
		return secndVotesPercent;
	}
	public void setSecndVotesPercent(String secndVotesPercent) {
		this.secndVotesPercent = secndVotesPercent;
	}
	public String getVotesPercentDiff() {
		return votesPercentDiff;
	}
	public void setVotesPercentDiff(String votesPercentDiff) {
		this.votesPercentDiff = votesPercentDiff;
	}
	public String getPartyName() {
		return partyName;
	}
	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}
	public Long getRank() {
		return rank;
	}
	public void setRank(Long rank) {
		this.rank = rank;
	}
	public String getSecndCandPartyName() {
		return secndCandPartyName;
	}
	public void setSecndCandPartyName(String secndCandPartyName) {
		this.secndCandPartyName = secndCandPartyName;
	}
	public Long getSecndCandRank() {
		return secndCandRank;
	}
	public void setSecndCandRank(Long secndCandRank) {
		this.secndCandRank = secndCandRank;
	}
}
