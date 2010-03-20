package com.itgrids.partyanalyst.dto;

import java.io.Serializable;

public class CandidateVotingTrendzCharts implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4765301587243158318L;
	
	private String pollingDetailsChart;
	private String votingTrendzMainChart;
	private String candOverallVotesPercent;
	private String candVotingTrendz;
	
	
	public String getCandOverallVotesPercent() {
		return candOverallVotesPercent;
	}
	public String getPollingDetailsChart() {
		return pollingDetailsChart;
	}
	public void setPollingDetailsChart(String pollingDetailsChart) {
		this.pollingDetailsChart = pollingDetailsChart;
	}
	public String getVotingTrendzMainChart() {
		return votingTrendzMainChart;
	}
	public void setVotingTrendzMainChart(String votingTrendzMainChart) {
		this.votingTrendzMainChart = votingTrendzMainChart;
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
