/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on November 6, 2009
 */

package com.itgrids.partyanalyst.dto;

public class ConstituencyElectionResults {

	private ConstituencyElectionResultVO electionResultForParty;
	private ConstituencyElectionResultVO electionResultForNewParty;
	
	//getters and setters
	public ConstituencyElectionResultVO getElectionResultForParty() {
		return electionResultForParty;
	}
	public void setElectionResultForParty(
			ConstituencyElectionResultVO electionResultForParty) {
		this.electionResultForParty = electionResultForParty;
	}
	public ConstituencyElectionResultVO getElectionResultForNewParty() {
		return electionResultForNewParty;
	}
	public void setElectionResultForNewParty(
			ConstituencyElectionResultVO electionResultForNewParty) {
		this.electionResultForNewParty = electionResultForNewParty;
	}
}
