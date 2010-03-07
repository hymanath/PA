/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on March 06,2010
 */
package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.List;

public class ConstituencyWisePartyResultsForMandal implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 261308407228468467L;
	
	private Long constituencyId;
	private Long constiName;
	private List<PartyElectionResultVO> partyElecResults;
	
	public Long getConstituencyId() {
		return constituencyId;
	}
	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}
	public Long getConstiName() {
		return constiName;
	}
	public void setConstiName(Long constiName) {
		this.constiName = constiName;
	}
	public List<PartyElectionResultVO> getPartyElecResults() {
		return partyElecResults;
	}
	public void setPartyElecResults(List<PartyElectionResultVO> partyElecResults) {
		this.partyElecResults = partyElecResults;
	}

}
