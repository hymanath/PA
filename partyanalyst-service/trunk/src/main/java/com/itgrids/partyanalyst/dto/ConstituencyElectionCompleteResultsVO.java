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
public class ConstituencyElectionCompleteResultsVO extends
		ConstituencyElectionDetailsVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/** Complete Results Of A Party In An Election */
	private PartyElectionCompleteResultsVO partyElectionCompleteResults;
	/** Complete Results List Of Party In Election*/
	private List<PartyElectionCompleteResultsVO> partyElectionCompleteResultsList;
	
	/** Getters And Setters */
	public PartyElectionCompleteResultsVO getPartyElectionCompleteResults() {
		return partyElectionCompleteResults;
	}
	public void setPartyElectionCompleteResults(
			PartyElectionCompleteResultsVO partyElectionCompleteResults) {
		this.partyElectionCompleteResults = partyElectionCompleteResults;
	}
	public List<PartyElectionCompleteResultsVO> getPartyElectionCompleteResultsList() {
		return partyElectionCompleteResultsList;
	}
	public void setPartyElectionCompleteResultsList(
			List<PartyElectionCompleteResultsVO> partyElectionCompleteResultsList) {
		this.partyElectionCompleteResultsList = partyElectionCompleteResultsList;
	}
	

}
