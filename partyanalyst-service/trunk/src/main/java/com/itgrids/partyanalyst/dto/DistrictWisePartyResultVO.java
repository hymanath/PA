/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on February 15,2010.
 */
package com.itgrids.partyanalyst.dto;

import java.util.List;
import java.util.Set;

public class DistrictWisePartyResultVO extends ResultStatus{

	private static final long serialVersionUID = 1L;
	private Long districtId;
	private String districtName;
	private Long totalConstituencies;
	private Long stateId;
	private String stateName;
	private Long constiCount;
	private Long constiParticipated;
	private Long seatsWon;
	private Double votesPercent;
	private Double totalPercentage;
	private Long seatsWonDiff;
	private Double votesPercentDiff;
	private String chartPath;
	private String detailedChartPath;
	private String pasitionsChart;
	private List<PartyResultVO> partyElectionResultsList;
	private List<ElectionResultVO> alliancePartiesInElection;
	private List<PartyPositionsVO> partiesPositionsInElection;
	private List<ElectionResultVO> result;
	private Set<SelectOptionVO> electionYearsAndType;
	
	public Set<SelectOptionVO> getElectionYearsAndType() {
		return electionYearsAndType;
	}
	public void setElectionYearsAndType(Set<SelectOptionVO> electionYearsAndType) {
		this.electionYearsAndType = electionYearsAndType;
	}
	public List<ElectionResultVO> getResult() {
		return result;
	}
	public void setResult(List<ElectionResultVO> result) {
		this.result = result;
	}
	public Long getSeatsWonDiff() {
		return seatsWonDiff;
	}
	public void setSeatsWonDiff(Long seatsWonDiff) {
		this.seatsWonDiff = seatsWonDiff;
	}
	public Double getVotesPercentDiff() {
		return votesPercentDiff;
	}
	public void setVotesPercentDiff(Double votesPercentDiff) {
		this.votesPercentDiff = votesPercentDiff;
	}
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
	public Long getTotalConstituencies() {
		return totalConstituencies;
	}
	public void setTotalConstituencies(Long totalConstituencies) {
		this.totalConstituencies = totalConstituencies;
	}
	public Double getTotalPercentage() {
		return totalPercentage;
	}
	public void setTotalPercentage(Double totalPercentage) {
		this.totalPercentage = totalPercentage;
	}
	public Long getStateId() {
		return stateId;
	}
	public void setStateId(Long stateId) {
		this.stateId = stateId;
	}
	public String getStateName() {
		return stateName;
	}
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	public Long getConstiCount() {
		return constiCount;
	}
	public void setConstiCount(Long constiCount) {
		this.constiCount = constiCount;
	}
	public Long getConstiParticipated() {
		return constiParticipated;
	}
	public void setConstiParticipated(Long constiParticipated) {
		this.constiParticipated = constiParticipated;
	}
	public Long getSeatsWon() {
		return seatsWon;
	}
	public void setSeatsWon(Long seatsWon) {
		this.seatsWon = seatsWon;
	}
	public Double getVotesPercent() {
		return votesPercent;
	}
	public void setVotesPercent(Double votesPercent) {
		this.votesPercent = votesPercent;
	}
	public List<PartyResultVO> getPartyElectionResultsList() {
		return partyElectionResultsList;
	}
	public void setPartyElectionResultsList(
			List<PartyResultVO> partyElectionResultsList) {
		this.partyElectionResultsList = partyElectionResultsList;
	}
	public String getChartPath() {
		return chartPath;
	}
	public void setChartPath(String chartPath) {
		this.chartPath = chartPath;
	}
	public List<ElectionResultVO> getAlliancePartiesInElection() {
		return alliancePartiesInElection;
	}
	public void setAlliancePartiesInElection(
			List<ElectionResultVO> alliancePartiesInElection) {
		this.alliancePartiesInElection = alliancePartiesInElection;
	}
	public List<PartyPositionsVO> getPartiesPositionsInElection() {
		return partiesPositionsInElection;
	}
	public void setPartiesPositionsInElection(
			List<PartyPositionsVO> partiesPositionsInElection) {
		this.partiesPositionsInElection = partiesPositionsInElection;
	}
	public String getPasitionsChart() {
		return pasitionsChart;
	}
	public void setPasitionsChart(String pasitionsChart) {
		this.pasitionsChart = pasitionsChart;
	}
	public String getDetailedChartPath() {
		return detailedChartPath;
	}
	public void setDetailedChartPath(String detailedChartPath) {
		this.detailedChartPath = detailedChartPath;
	}
	
}
