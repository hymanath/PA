/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on November 6, 2009
 */
package com.itgrids.partyanalyst.dto;

public class ConstituencyElectionsDetailedResultVO {

	private Long constituencyId;
	private String constituencyName;
	private Long districtId;
	private String votesPercentageDiff;
	
	private ConstituencyElectionResults constituencyElectionResultsForYearOne;
	private ConstituencyElectionResults constituencyElectionResultsForYearTwo;
	
	//getters and setters
	public Long getConstituencyId() {
		return constituencyId;
	}
	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}
	public String getConstituencyName() {
		return constituencyName;
	}
	public void setConstituencyName(String constituencyName) {
		this.constituencyName = constituencyName;
	}
	public Long getDistrictId() {
		return districtId;
	}
	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}
	public String getVotesPercentageDiff() {
		return votesPercentageDiff;
	}
	public void setVotesPercentageDiff(String votesPercentageDiff) {
		this.votesPercentageDiff = votesPercentageDiff;
	}
	public ConstituencyElectionResults getConstituencyElectionResultsForYearOne() {
		return constituencyElectionResultsForYearOne;
	}
	public void setConstituencyElectionResultsForYearOne(
			ConstituencyElectionResults constituencyElectionResultsForYearOne) {
		this.constituencyElectionResultsForYearOne = constituencyElectionResultsForYearOne;
	}
	public ConstituencyElectionResults getConstituencyElectionResultsForYearTwo() {
		return constituencyElectionResultsForYearTwo;
	}
	public void setConstituencyElectionResultsForYearTwo(
			ConstituencyElectionResults constituencyElectionResultsForYearTwo) {
		this.constituencyElectionResultsForYearTwo = constituencyElectionResultsForYearTwo;
	}
	
}
