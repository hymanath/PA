/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on March 06,2010
 */
package com.itgrids.partyanalyst.dto;

import java.io.Serializable;

public class MandalCompleteElectionTrendzVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5350602384578144548L;
	
	private Long mandalId;
	private String mandalName;
	private MandalElectionTrendzVO maleVotingTrendz;
	private MandalElectionTrendzVO femaleVotingTrendz;
	private MandalElectionTrendzVO maleAndFemaleVotingTrendz;
	private MandalElectionTrendzVO completeVotingTrendz;
	private ResultStatus resultStatus;
	
	//getters and setters
	public Long getMandalId() {
		return mandalId;
	}
	public void setMandalId(Long mandalId) {
		this.mandalId = mandalId;
	}
	public String getMandalName() {
		return mandalName;
	}
	public void setMandalName(String mandalName) {
		this.mandalName = mandalName;
	}
	public MandalElectionTrendzVO getMaleVotingTrendz() {
		return maleVotingTrendz;
	}
	public void setMaleVotingTrendz(MandalElectionTrendzVO maleVotingTrendz) {
		this.maleVotingTrendz = maleVotingTrendz;
	}
	public MandalElectionTrendzVO getFemaleVotingTrendz() {
		return femaleVotingTrendz;
	}
	public void setFemaleVotingTrendz(MandalElectionTrendzVO femaleVotingTrendz) {
		this.femaleVotingTrendz = femaleVotingTrendz;
	}
	public MandalElectionTrendzVO getMaleAndFemaleVotingTrendz() {
		return maleAndFemaleVotingTrendz;
	}
	public void setMaleAndFemaleVotingTrendz(
			MandalElectionTrendzVO maleAndFemaleVotingTrendz) {
		this.maleAndFemaleVotingTrendz = maleAndFemaleVotingTrendz;
	}
	public MandalElectionTrendzVO getCompleteVotingTrendz() {
		return completeVotingTrendz;
	}
	public void setCompleteVotingTrendz(MandalElectionTrendzVO completeVotingTrendz) {
		this.completeVotingTrendz = completeVotingTrendz;
	}
	public ResultStatus getResultStatus() {
		return resultStatus;
	}
	public void setResultStatus(ResultStatus resultStatus) {
		this.resultStatus = resultStatus;
	}
	
	
}
