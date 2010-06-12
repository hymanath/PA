/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on Jun 12, 2010
 */
package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.List;

public class MandalElectionResultVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long mandalId;
	private String mandalName;
	
	private List<PartyElectionResultsInConstituencyVO> partyElecResultsInCosntituency;

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

	public List<PartyElectionResultsInConstituencyVO> getPartyElecResultsInCosntituency() {
		return partyElecResultsInCosntituency;
	}

	public void setPartyElecResultsInCosntituency(
			List<PartyElectionResultsInConstituencyVO> partyElecResultsInCosntituency) {
		this.partyElecResultsInCosntituency = partyElecResultsInCosntituency;
	}

}
