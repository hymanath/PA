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
	private Long maleAndFemaleVoters;
	private Long totalPolledVotes;
	private Long malePolledVotes;
	private Long femalePolledVotes;
	private Long maleAndFemalePolledVotes;
	private String pollingPercent;
	private String malePollingPercent;
	private String overallMalePollPercent;
	private String femalePollingPercent;
	private String overallFemalePollPercent;
	private String maleAndFemalePollingPercent;
	private String overallMaleOrFemalePollPercent;
	
	private PartyResultsTrendzVO wonCandidateResultTrendz;
    private List<PartyResultsTrendzVO> partyElectionTrendzVO;
    
    
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

}
