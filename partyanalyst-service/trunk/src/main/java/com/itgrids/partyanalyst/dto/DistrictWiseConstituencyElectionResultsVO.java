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
	private String partyVotesPercentage;
	private String newPartyVotesPercentage;
	private String partyVotesPercentForYear2;
	private String year;
	private String previousYear;
	private Long partyId;
	private Long newPartyId;
	private String partyName;
	private String newPartyName;
	private Double districtWiseVotesPercntDiff;
	private Double totalVotesEarnedInDistrictForPartyInYear1;
	private Double totalValidVotesInDistrictForPartyInYear1;
	private Double totalVotesEarnedInDistrictForNewPartyInYear1;
	private Double totalValidVotesInDistrictForNewPartyInYear1;
	private Double totalVotesEarnedInDistrictForPartyInYear2;
	private Double totalValidVotesInDistrictForPartyInYear2;
	private List<ConstituencyElectionsDetailedResultVO> constituencyElectionsDetailedResults;
	
	public String getDistrictVotesPercentageDiff() {
		return districtVotesPercentageDiff;
	}
	public void setDistrictVotesPercentageDiff(String districtVotesPercentageDiff) {
		this.districtVotesPercentageDiff = districtVotesPercentageDiff;
	}
	public Long getPartyId() {
		return partyId;
	}
	public void setPartyId(Long partyId) {
		this.partyId = partyId;
	}
	public Long getNewPartyId() {
		return newPartyId;
	}
	public void setNewPartyId(Long newPartyId) {
		this.newPartyId = newPartyId;
	}
	public String getPartyVotesPercentForYear2() {
		return partyVotesPercentForYear2;
	}
	public void setPartyVotesPercentForYear2(String partyVotesPercentForYear2) {
		this.partyVotesPercentForYear2 = partyVotesPercentForYear2;
	}
	
	public Double getTotalVotesEarnedInDistrictForPartyInYear1() {
		return totalVotesEarnedInDistrictForPartyInYear1;
	}
	public void setTotalVotesEarnedInDistrictForPartyInYear1(
			Double totalVotesEarnedInDistrictForPartyInYear1) {
		this.totalVotesEarnedInDistrictForPartyInYear1 = totalVotesEarnedInDistrictForPartyInYear1;
	}
	public Double getTotalValidVotesInDistrictForPartyInYear1() {
		return totalValidVotesInDistrictForPartyInYear1;
	}
	public void setTotalValidVotesInDistrictForPartyInYear1(
			Double totalValidVotesInDistrictForPartyInYear1) {
		this.totalValidVotesInDistrictForPartyInYear1 = totalValidVotesInDistrictForPartyInYear1;
	}
	public Double getTotalVotesEarnedInDistrictForNewPartyInYear1() {
		return totalVotesEarnedInDistrictForNewPartyInYear1;
	}
	public void setTotalVotesEarnedInDistrictForNewPartyInYear1(
			Double totalVotesEarnedInDistrictForNewPartyInYear1) {
		this.totalVotesEarnedInDistrictForNewPartyInYear1 = totalVotesEarnedInDistrictForNewPartyInYear1;
	}
	public Double getTotalValidVotesInDistrictForNewPartyInYear1() {
		return totalValidVotesInDistrictForNewPartyInYear1;
	}
	public void setTotalValidVotesInDistrictForNewPartyInYear1(
			Double totalValidVotesInDistrictForNewPartyInYear1) {
		this.totalValidVotesInDistrictForNewPartyInYear1 = totalValidVotesInDistrictForNewPartyInYear1;
	}
	public Double getTotalVotesEarnedInDistrictForPartyInYear2() {
		return totalVotesEarnedInDistrictForPartyInYear2;
	}
	public void setTotalVotesEarnedInDistrictForPartyInYear2(
			Double totalVotesEarnedInDistrictForPartyInYear2) {
		this.totalVotesEarnedInDistrictForPartyInYear2 = totalVotesEarnedInDistrictForPartyInYear2;
	}
	public Double getTotalValidVotesInDistrictForPartyInYear2() {
		return totalValidVotesInDistrictForPartyInYear2;
	}
	public void setTotalValidVotesInDistrictForPartyInYear2(
			Double totalValidVotesInDistrictForPartyInYear2) {
		this.totalValidVotesInDistrictForPartyInYear2 = totalValidVotesInDistrictForPartyInYear2;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getPreviousYear() {
		return previousYear;
	}
	public void setPreviousYear(String previousYear) {
		this.previousYear = previousYear;
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
	public Double getDistrictWiseVotesPercntDiff() {
		return districtWiseVotesPercntDiff;
	}
	public void setDistrictWiseVotesPercntDiff(Double districtWiseVotesPercntDiff) {
		this.districtWiseVotesPercntDiff = districtWiseVotesPercntDiff;
	}
	public String getStateName() {
		return stateName;
	}
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	public String getPartyName() {
		return partyName;
	}
	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}
	public String getNewPartyName() {
		return newPartyName;
	}
	public void setNewPartyName(String newPartyName) {
		this.newPartyName = newPartyName;
	}
	public String getPartyVotesPercentage() {
		return partyVotesPercentage;
	}
	public void setPartyVotesPercentage(String partyVotesPercentage) {
		this.partyVotesPercentage = partyVotesPercentage;
	}
	public String getNewPartyVotesPercentage() {
		return newPartyVotesPercentage;
	}
	public void setNewPartyVotesPercentage(String newPartyVotesPercentage) {
		this.newPartyVotesPercentage = newPartyVotesPercentage;
	}
	public List<ConstituencyElectionsDetailedResultVO> getConstituencyElectionsDetailedResults() {
		return constituencyElectionsDetailedResults;
	}
	public void setConstituencyElectionsDetailedResults(
			List<ConstituencyElectionsDetailedResultVO> constituencyElectionsDetailedResults) {
		this.constituencyElectionsDetailedResults = constituencyElectionsDetailedResults;
	}
	
}
