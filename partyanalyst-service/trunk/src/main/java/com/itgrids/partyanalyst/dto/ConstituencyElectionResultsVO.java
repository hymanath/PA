/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on September 24, 2009
 */
package com.itgrids.partyanalyst.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class ConstituencyElectionResultsVO extends ResultStatus{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5903894658175665595L;
	private Long electionId;
	private String electionType;
	private String electionYear;
	private Date electionDate;
	private String constituencyName;
	private Long constituencyId;
	private Long totalVoters;
	private Long validVotes;
	private Long districtId;
	private String districtName;
	private Long stateId;
	private String stateName;
	private String totalVotes;
	private String totalPolledVotes;
	private String votingPercentage;
	private Long votesPercentage;
	private List<PartyResultsVO> partyResultsVO;
	private CandidateWonVO candidateResultsVO;
	private CandidateOppositionVO runnerUp;
	private List<CandidateOppositionVO> candidateOppositionList;
	private List<SelectOptionVO> allElectionYears;
	private Boolean resultsFlag;
	private String chartName;
	private List<SelectOptionVO> partiesList = new ArrayList<SelectOptionVO>();
	private Set<String> participatedParties;
	private Double censusReportPercent = 0.0;
	
	//getters and setters
	public Long getElectionId() {
		return electionId;
	}
	public void setElectionId(Long electionId) {
		this.electionId = electionId;
	}
	public String getElectionType() {
		return electionType;
	}
	public void setElectionType(String electionType) {
		this.electionType = electionType;
	}
	public String getElectionYear() {
		return electionYear;
	}
	public void setElectionYear(String electionYear) {
		this.electionYear = electionYear;
	}
	public Date getElectionDate() {
		return electionDate;
	}
	public void setElectionDate(Date electionDate) {
		this.electionDate = electionDate;
	}
	public List<PartyResultsVO> getPartyResultsVO() {
		return partyResultsVO;
	}
	public void setPartyResultsVO(List<PartyResultsVO> partyResultsVO) {
		this.partyResultsVO = partyResultsVO;
	}
	public CandidateWonVO getCandidateResultsVO() {
		return candidateResultsVO;
	}
	public void setCandidateResultsVO(CandidateWonVO candidateResultsVO) {
		this.candidateResultsVO = candidateResultsVO;
	}
	public List<CandidateOppositionVO> getCandidateOppositionList() {
		return candidateOppositionList;
	}
	public void setCandidateOppositionList(
			List<CandidateOppositionVO> candidateOppositionList) {
		this.candidateOppositionList = candidateOppositionList;
	}
	public String getConstituencyName() {
		return constituencyName;
	}
	public void setConstituencyName(String constituencyName) {
		this.constituencyName = constituencyName;
	}
	public Long getConstituencyId() {
		return constituencyId;
	}
	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}
	public Long getTotalVoters() {
		return totalVoters;
	}
	public void setTotalVoters(Long totalVoters) {
		this.totalVoters = totalVoters;
	}
	public Long getValidVotes() {
		return validVotes;
	}
	public void setValidVotes(Long validVotes) {
		this.validVotes = validVotes;
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
	public List<SelectOptionVO> getAllElectionYears() {
		return allElectionYears;
	}
	public void setAllElectionYears(List<SelectOptionVO> allElectionYears) {
		this.allElectionYears = allElectionYears;
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
	public String getTotalVotes() {
		return totalVotes;
	}
	public void setTotalVotes(String totalVotes) {
		this.totalVotes = totalVotes;
	}
	public String getTotalPolledVotes() {
		return totalPolledVotes;
	}
	public void setTotalPolledVotes(String totalPolledVotes) {
		this.totalPolledVotes = totalPolledVotes;
	}
	public String getVotingPercentage() {
		return votingPercentage;
	}
	public void setVotingPercentage(String votingPercentage) {
		this.votingPercentage = votingPercentage;
	}
	
	@Override
	public boolean equals(Object obj){
		if(!(obj instanceof ConstituencyElectionResultsVO))
			return false;
		ConstituencyElectionResultsVO voObj = (ConstituencyElectionResultsVO) obj;
		return this.constituencyId.equals(voObj.getConstituencyId());
	}
	
	@Override
	public int hashCode(){
		return this.constituencyId.hashCode();
	}
	public void setRunnerUp(CandidateOppositionVO runnerUp) {
		this.runnerUp = runnerUp;
	}
	public CandidateOppositionVO getRunnerUp() {
		return runnerUp;
	}
	public void setResultsFlag(Boolean resultsFlag) {
		this.resultsFlag = resultsFlag;
	}
	public Boolean getResultsFlag() {
		return resultsFlag;
	}
	public String getChartName() {
		return chartName;
	}
	public void setChartName(String chartName) {
		this.chartName = chartName;
	}
	public Long getVotesPercentage() {
		return votesPercentage;
	}
	public void setVotesPercentage(Long votesPercentage) {
		this.votesPercentage = votesPercentage;
	}
	public List<SelectOptionVO> getPartiesList() {
		return partiesList;
	}
	public void setPartiesList(List<SelectOptionVO> partiesList) {
		this.partiesList = partiesList;
	}
	public Set<String> getParticipatedParties() {
		return participatedParties;
	}
	public void setParticipatedParties(Set<String> participatedParties) {
		this.participatedParties = participatedParties;
	}
	public Double getCensusReportPercent() {
		return censusReportPercent;
	}
	public void setCensusReportPercent(Double censusReportPercent) {
		this.censusReportPercent = censusReportPercent;
	}
	
}
