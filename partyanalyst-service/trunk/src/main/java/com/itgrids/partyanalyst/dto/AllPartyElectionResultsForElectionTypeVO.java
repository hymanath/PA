/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on Jun 10, 2010
 */
package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.List;

public class AllPartyElectionResultsForElectionTypeVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
    private String electionType;
    
    private List<ConstituencyElectionResultAllPartyVO> constituencyElectionResultsVO;

	public String getElectionType() {
		return electionType;
	}

	public void setElectionType(String electionType) {
		this.electionType = electionType;
	}

	public List<ConstituencyElectionResultAllPartyVO> getConstituencyElectionResultsVO() {
		return constituencyElectionResultsVO;
	}

	public void setConstituencyElectionResultsVO(
			List<ConstituencyElectionResultAllPartyVO> constituencyElectionResultsVO) {
		this.constituencyElectionResultsVO = constituencyElectionResultsVO;
	}
	
}
