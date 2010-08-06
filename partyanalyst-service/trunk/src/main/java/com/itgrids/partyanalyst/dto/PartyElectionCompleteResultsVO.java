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
public class PartyElectionCompleteResultsVO extends PartyElectionDetailsVO
		implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/** Constituency Election Details */
	private ConstituencyElectionDetailsVO constituencyElectionDetails;
	/** Complete Results Of Candidate In An Election*/
	private CandidateElectionCompleteResultsVO candidateElectionCompleteResults;
	/** Complete Results List Of Candidate In An Election*/
	private List<CandidateElectionCompleteResultsVO> candidateElectionCompleteResultsList;
	
	/** Getters And Setters */
	public ConstituencyElectionDetailsVO getConstituencyElectionDetails() {
		return constituencyElectionDetails;
	}
	public void setConstituencyElectionDetails(
			ConstituencyElectionDetailsVO constituencyElectionDetails) {
		this.constituencyElectionDetails = constituencyElectionDetails;
	}
	public CandidateElectionCompleteResultsVO getCandidateElectionCompleteResults() {
		return candidateElectionCompleteResults;
	}
	public void setCandidateElectionCompleteResults(
			CandidateElectionCompleteResultsVO candidateElectionCompleteResults) {
		this.candidateElectionCompleteResults = candidateElectionCompleteResults;
	}
	public List<CandidateElectionCompleteResultsVO> getCandidateElectionCompleteResultsList() {
		return candidateElectionCompleteResultsList;
	}
	public void setCandidateElectionCompleteResultsList(
			List<CandidateElectionCompleteResultsVO> candidateElectionCompleteResultsList) {
		this.candidateElectionCompleteResultsList = candidateElectionCompleteResultsList;
	}

}
