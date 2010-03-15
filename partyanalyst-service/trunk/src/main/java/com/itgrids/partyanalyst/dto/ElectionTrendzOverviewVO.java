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
	private Long mAndFVoters;
	private Long totalPolledVotes;
	private Long malePolledVotes;
	private Long femalePolledVotes;
	private Long mAFPolledVotes;
	private String pollingPercent;
	private String malePollingPercent;
	private String femalePollingPercent;
	private String mAFPollingPercent;
	
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

	public Long getMAndFVoters() {
		return mAndFVoters;
	}

	public void setMAndFVoters(Long andFVoters) {
		mAndFVoters = andFVoters;
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

	public Long getMAFPolledVotes() {
		return mAFPolledVotes;
	}

	public void setMAFPolledVotes(Long polledVotes) {
		mAFPolledVotes = polledVotes;
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

	public String getMAFPollingPercent() {
		return mAFPollingPercent;
	}

	public void setMAFPollingPercent(String pollingPercent) {
		mAFPollingPercent = pollingPercent;
	}

	public List<PartyResultsTrendzVO> getPartyElectionTrendzVO() {
		return partyElectionTrendzVO;
	}

	public void setPartyElectionTrendzVO(
			List<PartyResultsTrendzVO> partyElectionTrendzVO) {
		this.partyElectionTrendzVO = partyElectionTrendzVO;
	}
}
