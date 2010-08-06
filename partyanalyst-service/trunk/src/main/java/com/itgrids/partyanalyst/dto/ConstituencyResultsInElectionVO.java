/* 
 * Copyright (c) 2010 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on August 05, 2010
 */
package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.List;

/*
 * @author Sai Krishna Basetti
 */
public class ConstituencyResultsInElectionVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/** Years of elections happened in Constituency */
	private List<SelectOptionVO> electionYears;
	/** Complete Election Results In Constituency */
	private ConstituencyElectionCompleteResultsVO constituencyElectionCompleteResults;

	public List<SelectOptionVO> getElectionYears() {
		return electionYears;
	}

	public void setElectionYears(List<SelectOptionVO> electionYears) {
		this.electionYears = electionYears;
	}

	public ConstituencyElectionCompleteResultsVO getConstituencyElectionCompleteResults() {
		return constituencyElectionCompleteResults;
	}

	public void setConstituencyElectionCompleteResults(
			ConstituencyElectionCompleteResultsVO constituencyElectionCompleteResults) {
		this.constituencyElectionCompleteResults = constituencyElectionCompleteResults;
	}

}
