/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on March 06,2010
 */
package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.List;

public class ElectionTrendzOverviewVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7937627434845833917L;
	
	private Long totalVoters;
	private Long maleVoters;
	private Long femaleVoters;
	private Long malePolledVotes;
	private Long totalPolledVotes;
	private String pollingPercent;
	private Long femalePolledVotes;
	private String maleVotersPercent;
	private Long maleAndFemaleVoters;
	private String malePollingPercent;
	private String femaleVotersPercent;
	private String femalePollingPercent;
	private Long maleAndFemalePolledVotes;
	private String overallMalePollPercent;
	private Long maleVotersInConstituency;
	private String maleVotersPercentInConsti;
	private Long femaleVotersInConstituency;
	private String femaleVotersPercentInConsti;
	private String overallFemalePollPercent;
	private String maleOrFemaleVotersPercent;
	private String maleAndFemalePollingPercent;
	private String overallMaleOrFemalePollPercent;
	private String malePollingPercentInTotalPolledVotes;
	private String femalePollingPercentInTotalPolledVotes;
	private String maleOrFemalePollingPercentInTotalPolledVotes;
	
	private PartyResultsTrendzVO wonCandidateResultTrendz;
    private List<PartyResultsTrendzVO> partyElectionTrendzVO;
    private CandidateVotingTrendzCharts electionTrendzCharts;
    
    
    
    //getters and setters
    
	public Long getTotalVoters() {
		return totalVoters;
	}

	public void setTotalVoters(Long totalVoters) {
		this.totalVoters = totalVoters;
	}

	public Long getMaleVoters() {
		return maleVoters;
	}

	public void setMaleVoters(Long maleVoters) {
		this.maleVoters = maleVoters;
	}

	public Long getFemaleVoters() {
		return femaleVoters;
	}

	public void setFemaleVoters(Long femaleVoters) {
		this.femaleVoters = femaleVoters;
	}

	public Long getTotalPolledVotes() {
		return totalPolledVotes;
	}

	public void setTotalPolledVotes(Long totalPolledVotes) {
		this.totalPolledVotes = totalPolledVotes;
	}

	public Long getMalePolledVotes() {
		return malePolledVotes;
	}

	public void setMalePolledVotes(Long malePolledVotes) {
		this.malePolledVotes = malePolledVotes;
	}

	public Long getFemalePolledVotes() {
		return femalePolledVotes;
	}

	public void setFemalePolledVotes(Long femalePolledVotes) {
		this.femalePolledVotes = femalePolledVotes;
	}

	public String getPollingPercent() {
		return pollingPercent;
	}

	public void setPollingPercent(String pollingPercent) {
		this.pollingPercent = pollingPercent;
	}

	public String getMalePollingPercent() {
		return malePollingPercent;
	}

	public void setMalePollingPercent(String malePollingPercent) {
		this.malePollingPercent = malePollingPercent;
	}

	public String getFemalePollingPercent() {
		return femalePollingPercent;
	}

	public void setFemalePollingPercent(String femalePollingPercent) {
		this.femalePollingPercent = femalePollingPercent;
	}

	
	public PartyResultsTrendzVO getWonCandidateResultTrendz() {
		return wonCandidateResultTrendz;
	}

	public void setWonCandidateResultTrendz(
			PartyResultsTrendzVO wonCandidateResultTrendz) {
		this.wonCandidateResultTrendz = wonCandidateResultTrendz;
	}

	public List<PartyResultsTrendzVO> getPartyElectionTrendzVO() {
		return partyElectionTrendzVO;
	}

	public void setPartyElectionTrendzVO(
			List<PartyResultsTrendzVO> partyElectionTrendzVO) {
		this.partyElectionTrendzVO = partyElectionTrendzVO;
	}
	
	public Long getMaleAndFemaleVoters() {
		return maleAndFemaleVoters;
	}

	public void setMaleAndFemaleVoters(Long maleAndFemaleVoters) {
		this.maleAndFemaleVoters = maleAndFemaleVoters;
	}

	public Long getMaleAndFemalePolledVotes() {
		return maleAndFemalePolledVotes;
	}

	public void setMaleAndFemalePolledVotes(Long maleAndFemalePolledVotes) {
		this.maleAndFemalePolledVotes = maleAndFemalePolledVotes;
	}

	public String getMaleAndFemalePollingPercent() {
		return maleAndFemalePollingPercent;
	}

	public void setMaleAndFemalePollingPercent(String maleAndFemalePollingPercent) {
		this.maleAndFemalePollingPercent = maleAndFemalePollingPercent;
	}

	public String getOverallMalePollPercent() {
		return overallMalePollPercent;
	}

	public void setOverallMalePollPercent(String overallMalePollPercent) {
		this.overallMalePollPercent = overallMalePollPercent;
	}

	public String getOverallFemalePollPercent() {
		return overallFemalePollPercent;
	}

	public void setOverallFemalePollPercent(String overallFemalePollPercent) {
		this.overallFemalePollPercent = overallFemalePollPercent;
	}

	public String getOverallMaleOrFemalePollPercent() {
		return overallMaleOrFemalePollPercent;
	}

	public void setOverallMaleOrFemalePollPercent(
			String overallMaleOrFemalePollPercent) {
		this.overallMaleOrFemalePollPercent = overallMaleOrFemalePollPercent;
	}

	public Long getMaleVotersInConstituency() {
		return maleVotersInConstituency;
	}

	public void setMaleVotersInConstituency(Long maleVotersInConstituency) {
		this.maleVotersInConstituency = maleVotersInConstituency;
	}

	public Long getFemaleVotersInConstituency() {
		return femaleVotersInConstituency;
	}

	public void setFemaleVotersInConstituency(Long femaleVotersInConstituency) {
		this.femaleVotersInConstituency = femaleVotersInConstituency;
	}

	public String getMaleVotersPercent() {
		return maleVotersPercent;
	}

	public void setMaleVotersPercent(String maleVotersPercent) {
		this.maleVotersPercent = maleVotersPercent;
	}

	public String getFemaleVotersPercent() {
		return femaleVotersPercent;
	}

	public void setFemaleVotersPercent(String femaleVotersPercent) {
		this.femaleVotersPercent = femaleVotersPercent;
	}

	public String getMaleOrFemaleVotersPercent() {
		return maleOrFemaleVotersPercent;
	}

	public void setMaleOrFemaleVotersPercent(String maleOrFemaleVotersPercent) {
		this.maleOrFemaleVotersPercent = maleOrFemaleVotersPercent;
	}

	public String getMaleVotersPercentInConsti() {
		return maleVotersPercentInConsti;
	}

	public void setMaleVotersPercentInConsti(String maleVotersPercentInConsti) {
		this.maleVotersPercentInConsti = maleVotersPercentInConsti;
	}

	public String getFemaleVotersPercentInConsti() {
		return femaleVotersPercentInConsti;
	}

	public void setFemaleVotersPercentInConsti(String femaleVotersPercentInConsti) {
		this.femaleVotersPercentInConsti = femaleVotersPercentInConsti;
	}

	public CandidateVotingTrendzCharts getElectionTrendzCharts() {
		return electionTrendzCharts;
	}

	public void setElectionTrendzCharts(
			CandidateVotingTrendzCharts electionTrendzCharts) {
		this.electionTrendzCharts = electionTrendzCharts;
	}

	public String getMalePollingPercentInTotalPolledVotes() {
		return malePollingPercentInTotalPolledVotes;
	}

	public void setMalePollingPercentInTotalPolledVotes(
			String malePollingPercentInTotalPolledVotes) {
		this.malePollingPercentInTotalPolledVotes = malePollingPercentInTotalPolledVotes;
	}

	public String getFemalePollingPercentInTotalPolledVotes() {
		return femalePollingPercentInTotalPolledVotes;
	}

	public void setFemalePollingPercentInTotalPolledVotes(
			String femalePollingPercentInTotalPolledVotes) {
		this.femalePollingPercentInTotalPolledVotes = femalePollingPercentInTotalPolledVotes;
	}

	public String getMaleOrFemalePollingPercentInTotalPolledVotes() {
		return maleOrFemalePollingPercentInTotalPolledVotes;
	}

	public void setMaleOrFemalePollingPercentInTotalPolledVotes(
			String maleOrFemalePollingPercentInTotalPolledVotes) {
		this.maleOrFemalePollingPercentInTotalPolledVotes = maleOrFemalePollingPercentInTotalPolledVotes;
	}

}
