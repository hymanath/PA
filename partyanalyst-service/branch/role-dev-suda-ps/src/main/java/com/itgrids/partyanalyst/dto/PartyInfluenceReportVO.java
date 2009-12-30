/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on November 10, 2009
 */
package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.List;

public class PartyInfluenceReportVO extends ResultStatus implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3119757028618351557L;
	
	private String impactedPartyName;
	private String newPartyName;
	private String year;
	private String previousYear;
	private int totalConstituenciesConsidered;
	private int totalDistrictsConsidered;
	private String totalDistrictsVotesPercentForPartyForYearOne;
	private String totalDistrictsVotesPercentForNewPartyForYearOne;
	private String totalDistrictsVotesPercentForPartyForYearTwo;
	private String totalDistrictsVotesPercentDiffForParty;
	
	private List<DistrictWiseConstituencyElectionResultsVO> districtWiseConstituencyElectionResultsVO;
	
	
	
	public String getTotalDistrictsVotesPercentForPartyForYearOne() {
		return totalDistrictsVotesPercentForPartyForYearOne;
	}
	public void setTotalDistrictsVotesPercentForPartyForYearOne(
			String totalDistrictsVotesPercentForPartyForYearOne) {
		this.totalDistrictsVotesPercentForPartyForYearOne = totalDistrictsVotesPercentForPartyForYearOne;
	}
	public String getTotalDistrictsVotesPercentForNewPartyForYearOne() {
		return totalDistrictsVotesPercentForNewPartyForYearOne;
	}
	public void setTotalDistrictsVotesPercentForNewPartyForYearOne(
			String totalDistrictsVotesPercentForNewPartyForYearOne) {
		this.totalDistrictsVotesPercentForNewPartyForYearOne = totalDistrictsVotesPercentForNewPartyForYearOne;
	}
	public String getTotalDistrictsVotesPercentForPartyForYearTwo() {
		return totalDistrictsVotesPercentForPartyForYearTwo;
	}
	public void setTotalDistrictsVotesPercentForPartyForYearTwo(
			String totalDistrictsVotesPercentForPartyForYearTwo) {
		this.totalDistrictsVotesPercentForPartyForYearTwo = totalDistrictsVotesPercentForPartyForYearTwo;
	}
	public String getTotalDistrictsVotesPercentDiffForParty() {
		return totalDistrictsVotesPercentDiffForParty;
	}
	public void setTotalDistrictsVotesPercentDiffForParty(
			String totalDistrictsVotesPercentDiffForParty) {
		this.totalDistrictsVotesPercentDiffForParty = totalDistrictsVotesPercentDiffForParty;
	}
	//getters and setters
	public String getImpactedPartyName() {
		return impactedPartyName;
	}
	public void setImpactedPartyName(String impactedPartyName) {
		this.impactedPartyName = impactedPartyName;
	}
	public String getNewPartyName() {
		return newPartyName;
	}
	public void setNewPartyName(String newPartyName) {
		this.newPartyName = newPartyName;
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
	public int getTotalConstituenciesConsidered() {
		return totalConstituenciesConsidered;
	}
	public void setTotalConstituenciesConsidered(int totalConstituenciesConsidered) {
		this.totalConstituenciesConsidered = totalConstituenciesConsidered;
	}
	public int getTotalDistrictsConsidered() {
		return totalDistrictsConsidered;
	}
	public void setTotalDistrictsConsidered(int totalDistrictsConsidered) {
		this.totalDistrictsConsidered = totalDistrictsConsidered;
	}
	public List<DistrictWiseConstituencyElectionResultsVO> getDistrictWiseConstituencyElectionResultsVO() {
		return districtWiseConstituencyElectionResultsVO;
	}
	public void setDistrictWiseConstituencyElectionResultsVO(
			List<DistrictWiseConstituencyElectionResultsVO> districtWiseConstituencyElectionResultsVO) {
		this.districtWiseConstituencyElectionResultsVO = districtWiseConstituencyElectionResultsVO;
	}
	
	
}
