/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on February 18,2010.
 */
package com.itgrids.partyanalyst.dto;

import java.util.List;

public class ComparedReportVO implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3740352990392569280L;

	private List<ComparedElectionResultVO> votesPercentGainedResults;
	private List<ComparedElectionResultVO> votesPercentLostResults;
	private List<PartyResultVO> notConsideredYearOneResults;
	private List<PartyResultVO> notConsideredYearTwoResults;
	private List<PartyPositionsVO> positionsYearOne;
	private List<PartyPositionsVO> positionsYearTwo;
	private String yearOne;
	private String yearTwo;
	
	
	public List<ComparedElectionResultVO> getVotesPercentGainedResults() {
		return votesPercentGainedResults;
	}
	public void setVotesPercentGainedResults(
			List<ComparedElectionResultVO> votesPercentGainedResults) {
		this.votesPercentGainedResults = votesPercentGainedResults;
	}
	public List<ComparedElectionResultVO> getVotesPercentLostResults() {
		return votesPercentLostResults;
	}
	public void setVotesPercentLostResults(
			List<ComparedElectionResultVO> votesPercentLostResults) {
		this.votesPercentLostResults = votesPercentLostResults;
	}
	public List<PartyResultVO> getNotConsideredYearOneResults() {
		return notConsideredYearOneResults;
	}
	public void setNotConsideredYearOneResults(
			List<PartyResultVO> notConsideredYearOneResults) {
		this.notConsideredYearOneResults = notConsideredYearOneResults;
	}
	public List<PartyResultVO> getNotConsideredYearTwoResults() {
		return notConsideredYearTwoResults;
	}
	public void setNotConsideredYearTwoResults(
			List<PartyResultVO> notConsideredYearTwoResults) {
		this.notConsideredYearTwoResults = notConsideredYearTwoResults;
	}
	public List<PartyPositionsVO> getPositionsYearOne() {
		return positionsYearOne;
	}
	public void setPositionsYearOne(List<PartyPositionsVO> positionsYearOne) {
		this.positionsYearOne = positionsYearOne;
	}
	public List<PartyPositionsVO> getPositionsYearTwo() {
		return positionsYearTwo;
	}
	public void setPositionsYearTwo(List<PartyPositionsVO> positionsYearTwo) {
		this.positionsYearTwo = positionsYearTwo;
	}
	public String getYearOne() {
		return yearOne;
	}
	public void setYearOne(String yearOne) {
		this.yearOne = yearOne;
	}
	public String getYearTwo() {
		return yearTwo;
	}
	public void setYearTwo(String yearTwo) {
		this.yearTwo = yearTwo;
	}
	
}
