/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on March 31,2010
 */
package com.itgrids.partyanalyst.dto;

import java.io.Serializable;

public class ElectionResultsReportVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1224213071802672407L;
	
	private String state;
	private String electionType;
	private String electionYear;
	private String statewiseElectionResultsChartName;	
	private ResultStatus resultStatus;
	
	private ElectionResultsVO electionBasicResultsVO;
	
    //getters and setters
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
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

	public ResultStatus getResultStatus() {
		return resultStatus;
	}

	public void setResultStatus(ResultStatus resultStatus) {
		this.resultStatus = resultStatus;
	}

	public ElectionResultsVO getElectionBasicResultsVO() {
		return electionBasicResultsVO;
	}

	public void setElectionBasicResultsVO(ElectionResultsVO electionBasicResultsVO) {
		this.electionBasicResultsVO = electionBasicResultsVO;
	}

	public String getStatewiseElectionResultsChartName() {
		return statewiseElectionResultsChartName;
	}

	public void setStatewiseElectionResultsChartName(
			String statewiseElectionResultsChartName) {
		this.statewiseElectionResultsChartName = statewiseElectionResultsChartName;
	}
	
	
	

}
