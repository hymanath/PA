/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on March 08,2010
 */
package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.List;

public class ElectionTrendzInfoVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5121537898181544482L;
	
	private List<BoothTotalVotesVO> boothsDetails;
	private ConstituencyWisePartyResultsForMandal constituencyWiseResults;
	
	//getters and setters
	public List<BoothTotalVotesVO> getBoothsDetails() {
		return boothsDetails;
	}
	public void setBoothsDetails(List<BoothTotalVotesVO> boothsDetails) {
		this.boothsDetails = boothsDetails;
	}
	public ConstituencyWisePartyResultsForMandal getConstituencyWiseResults() {
		return constituencyWiseResults;
	}
	public void setConstituencyWiseResults(
			ConstituencyWisePartyResultsForMandal constituencyWiseResults) {
		this.constituencyWiseResults = constituencyWiseResults;
	}

}
