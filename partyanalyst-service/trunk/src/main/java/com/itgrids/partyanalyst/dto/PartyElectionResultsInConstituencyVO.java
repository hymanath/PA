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

public class PartyElectionResultsInConstituencyVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long constituencyId;
	private String constituencyName;
	
	private List<PartyResultsVO> partyElecResults;

	public Long getConstituencyId() {
		return constituencyId;
	}

	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}

	public String getConstituencyName() {
		return constituencyName;
	}

	public void setConstituencyName(String constituencyName) {
		this.constituencyName = constituencyName;
	}

	public List<PartyResultsVO> getPartyElecResults() {
		return partyElecResults;
	}

	public void setPartyElecResults(List<PartyResultsVO> partyElecResults) {
		this.partyElecResults = partyElecResults;
	}

}
