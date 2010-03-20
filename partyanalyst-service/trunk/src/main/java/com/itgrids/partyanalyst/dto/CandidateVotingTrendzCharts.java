package com.itgrids.partyanalyst.dto;

import java.io.Serializable;

public class CandidateVotingTrendzCharts implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4765301587243158318L;
	
	private String candOverallVotesPercent;
	private String candVotingTrendz;
	
	
	public String getCandOverallVotesPercent() {
		return candOverallVotesPercent;
	}
	public void setCandOverallVotesPercent(String candOverallVotesPercent) {
		this.candOverallVotesPercent = candOverallVotesPercent;
	}
	public String getCandVotingTrendz() {
		return candVotingTrendz;
	}
	public void setCandVotingTrendz(String candVotingTrendz) {
		this.candVotingTrendz = candVotingTrendz;
	}

}
