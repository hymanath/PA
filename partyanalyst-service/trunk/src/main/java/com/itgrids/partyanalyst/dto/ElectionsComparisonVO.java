/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on October 20, 2009
 */
package com.itgrids.partyanalyst.dto;

import java.util.List;

public class ElectionsComparisonVO {

	private String firstYear;
	private String secondYear;
	private String partyName;
	private List<ElectionComparisonResultVO> votesGained;
	private List<ElectionComparisonResultVO> votesLost;
	
	//getters and setters
	public String getFirstYear() {
		return firstYear;
	}
	public void setFirstYear(String firstYear) {
		this.firstYear = firstYear;
	}
	public String getSecondYear() {
		return secondYear;
	}
	public void setSecondYear(String secondYear) {
		this.secondYear = secondYear;
	}
	public String getPartyName() {
		return partyName;
	}
	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}
	
	public List<ElectionComparisonResultVO> getVotesGained() {
		return votesGained;
	}
	public void setVotesGained(List<ElectionComparisonResultVO> votesGained) {
		this.votesGained = votesGained;
	}
	public List<ElectionComparisonResultVO> getVotesLost() {
		return votesLost;
	}
	public void setVotesLost(List<ElectionComparisonResultVO> votesLost) {
		this.votesLost = votesLost;
	}
}
