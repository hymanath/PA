/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on Jun 17,2010
 */
package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.List;

public class PartyVotesMarginResultsVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long electionId;
	private String elecionType;
	private String electionYear;
	
	private List<PartyVotesMarginInConstituency> partyVotesMarginInConstituency;
	private ResultStatus resultStatus;

	public Long getElectionId() {
		return electionId;
	}

	public void setElectionId(Long electionId) {
		this.electionId = electionId;
	}

	public String getElecionType() {
		return elecionType;
	}

	public void setElecionType(String elecionType) {
		this.elecionType = elecionType;
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

	public List<PartyVotesMarginInConstituency> getPartyVotesMarginInConstituency() {
		return partyVotesMarginInConstituency;
	}

	public void setPartyVotesMarginInConstituency(
			List<PartyVotesMarginInConstituency> partyVotesMarginInConstituency) {
		this.partyVotesMarginInConstituency = partyVotesMarginInConstituency;
	}

}
