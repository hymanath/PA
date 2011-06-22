/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on February 15,2010.
 */
package com.itgrids.partyanalyst.dto;

import java.util.List;

public class ElectionComparisonReportVO {

	private String yearOne;
	private String yearTwo;
	private List<PartyPositionsVO> positionsForYearOne;
	private List<PartyPositionsVO> positionsForYearTwo;
	private List<DistrictWisePartyResultVO> districtWisePartyResultsForYearOne;
	private List<DistrictWisePartyResultVO> districtWisePartyResultsForYearTwo;
	private ResultStatus resultStatus;
	private CompleteResultsVO forYearOne;
	private CompleteResultsVO forYearTwo;
	private Long seatsDiff;
	private String votesPercentDiff;
	private Long electionTypeId;
	private String electionType;
	private Long elecIdYearOne;
	private Long elecIdYearTwo;
	private Long stateId;
	private Long partyId;
	private Boolean hasAlliances;
	private String label;
	private String percentageChart;
	private String seatsWonChart;
	private String totalPercentChart;
	private String stateName;
	
	public String getStateName() {
		return stateName;
	}
	public void setStateName(String stateName) {
		this.stateName = stateName;
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
	public List<DistrictWisePartyResultVO> getDistrictWisePartyResultsForYearOne() {
		return districtWisePartyResultsForYearOne;
	}
	public void setDistrictWisePartyResultsForYearOne(
			List<DistrictWisePartyResultVO> districtWisePartyResultsForYearOne) {
		this.districtWisePartyResultsForYearOne = districtWisePartyResultsForYearOne;
	}
	public List<DistrictWisePartyResultVO> getDistrictWisePartyResultsForYearTwo() {
		return districtWisePartyResultsForYearTwo;
	}
	public void setDistrictWisePartyResultsForYearTwo(
			List<DistrictWisePartyResultVO> districtWisePartyResultsForYearTwo) {
		this.districtWisePartyResultsForYearTwo = districtWisePartyResultsForYearTwo;
	}
	public ResultStatus getResultStatus() {
		return resultStatus;
	}
	public void setResultStatus(ResultStatus resultStatus) {
		this.resultStatus = resultStatus;
	}
	public CompleteResultsVO getForYearOne() {
		return forYearOne;
	}
	public void setForYearOne(CompleteResultsVO forYearOne) {
		this.forYearOne = forYearOne;
	}
	public CompleteResultsVO getForYearTwo() {
		return forYearTwo;
	}
	public void setForYearTwo(CompleteResultsVO forYearTwo) {
		this.forYearTwo = forYearTwo;
	}
	
	public Long getElectionTypeId() {
		return electionTypeId;
	}
	public void setElectionTypeId(Long electionTypeId) {
		this.electionTypeId = electionTypeId;
	}
	public Long getElecIdYearOne() {
		return elecIdYearOne;
	}
	public void setElecIdYearOne(Long elecIdYearOne) {
		this.elecIdYearOne = elecIdYearOne;
	}
	public Long getElecIdYearTwo() {
		return elecIdYearTwo;
	}
	public void setElecIdYearTwo(Long elecIdYearTwo) {
		this.elecIdYearTwo = elecIdYearTwo;
	}
	public Long getStateId() {
		return stateId;
	}
	public void setStateId(Long stateId) {
		this.stateId = stateId;
	}
	public Long getPartyId() {
		return partyId;
	}
	public void setPartyId(Long partyId) {
		this.partyId = partyId;
	}
	public Boolean getHasAlliances() {
		return hasAlliances;
	}
	public void setHasAlliances(Boolean hasAlliances) {
		this.hasAlliances = hasAlliances;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public List<PartyPositionsVO> getPositionsForYearOne() {
		return positionsForYearOne;
	}
	public void setPositionsForYearOne(List<PartyPositionsVO> positionsForYearOne) {
		this.positionsForYearOne = positionsForYearOne;
	}
	public List<PartyPositionsVO> getPositionsForYearTwo() {
		return positionsForYearTwo;
	}
	public void setPositionsForYearTwo(List<PartyPositionsVO> positionsForYearTwo) {
		this.positionsForYearTwo = positionsForYearTwo;
	}
	public String getVotesPercentDiff() {
		return votesPercentDiff;
	}
	public void setVotesPercentDiff(String votesPercentDiff) {
		this.votesPercentDiff = votesPercentDiff;
	}
	public Long getSeatsDiff() {
		return seatsDiff;
	}
	public void setSeatsDiff(Long seatsDiff) {
		this.seatsDiff = seatsDiff;
	}
	public String getPercentageChart() {
		return percentageChart;
	}
	public void setPercentageChart(String percentageChart) {
		this.percentageChart = percentageChart;
	}
	public String getSeatsWonChart() {
		return seatsWonChart;
	}
	public void setSeatsWonChart(String seatsWonChart) {
		this.seatsWonChart = seatsWonChart;
	}
	public String getElectionType() {
		return electionType;
	}
	public void setElectionType(String electionType) {
		this.electionType = electionType;
	}
	public String getTotalPercentChart() {
		return totalPercentChart;
	}
	public void setTotalPercentChart(String totalPercentChart) {
		this.totalPercentChart = totalPercentChart;
	}
}
