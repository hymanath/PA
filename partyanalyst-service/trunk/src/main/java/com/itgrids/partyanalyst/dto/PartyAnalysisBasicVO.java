/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on April 24,2010
 */
package com.itgrids.partyanalyst.dto;

import java.io.Serializable;

public class PartyAnalysisBasicVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8923339992338157982L;
	
	private Long partyId;
	private String partyName;
	private Long seatsWon;
	private Long seatsLost;
	private Long partiConstituencies;
	private Long analyzedConsti;
	private Long notAnalyzedConsti;
	private String analysisChart;
	private String resultsChart;
	
	
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
	public Long getSeatsWon() {
		return seatsWon;
	}
	public void setSeatsWon(Long seatsWon) {
		this.seatsWon = seatsWon;
	}
	public Long getSeatsLost() {
		return seatsLost;
	}
	public void setSeatsLost(Long seatsLost) {
		this.seatsLost = seatsLost;
	}
	public Long getPartiConstituencies() {
		return partiConstituencies;
	}
	public void setPartiConstituencies(Long partiConstituencies) {
		this.partiConstituencies = partiConstituencies;
	}
	public Long getAnalyzedConsti() {
		return analyzedConsti;
	}
	public void setAnalyzedConsti(Long analyzedConsti) {
		this.analyzedConsti = analyzedConsti;
	}
	public Long getNotAnalyzedConsti() {
		return notAnalyzedConsti;
	}
	public void setNotAnalyzedConsti(Long notAnalyzedConsti) {
		this.notAnalyzedConsti = notAnalyzedConsti;
	}
	public String getAnalysisChart() {
		return analysisChart;
	}
	public void setAnalysisChart(String analysisChart) {
		this.analysisChart = analysisChart;
	}
	public String getResultsChart() {
		return resultsChart;
	}
	public void setResultsChart(String resultsChart) {
		this.resultsChart = resultsChart;
	}
	
	
}
