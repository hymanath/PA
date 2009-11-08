/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on November 6, 2009
 */
package com.itgrids.partyanalyst.dto;

import java.util.List;

public class DistrictWiseConstituencyElectionResultsVO {

	private Long districtId;
	private String districtName;
	private String stateName;
	private String districtVotesPercentageDiff;
	private List<ConstituencyElectionsDetailedResultVO> constituencyElectionsDetailedResults;
	
	public String getDistrictVotesPercentageDiff() {
		return districtVotesPercentageDiff;
	}
	public void setDistrictVotesPercentageDiff(String districtVotesPercentageDiff) {
		this.districtVotesPercentageDiff = districtVotesPercentageDiff;
	}
	//getters and setters
	public Long getDistrictId() {
		return districtId;
	}
	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}
	public String getDistrictName() {
		return districtName;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	public String getStateName() {
		return stateName;
	}
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	public List<ConstituencyElectionsDetailedResultVO> getConstituencyElectionsDetailedResults() {
		return constituencyElectionsDetailedResults;
	}
	public void setConstituencyElectionsDetailedResults(
			List<ConstituencyElectionsDetailedResultVO> constituencyElectionsDetailedResults) {
		this.constituencyElectionsDetailedResults = constituencyElectionsDetailedResults;
	}
	
}
