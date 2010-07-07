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
	
	private List<PartyElectionResultsInConstituencyVO> partyElecResultsInConstituency;
	private List<PartyResultsVO> mandalResults;
	ResultStatus resultStatus;

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

	public ResultStatus getResultStatus() {
		return resultStatus;
	}

	public void setResultStatus(ResultStatus resultStatus) {
		this.resultStatus = resultStatus;
	}

	public List<PartyElectionResultsInConstituencyVO> getPartyElecResultsInConstituency() {
		return partyElecResultsInConstituency;
	}

	public void setPartyElecResultsInConstituency(
			List<PartyElectionResultsInConstituencyVO> partyElecResultsInConstituency) {
		this.partyElecResultsInConstituency = partyElecResultsInConstituency;
	}

	public void setMandalResults(List<PartyResultsVO> mandalResults) {
		this.mandalResults = mandalResults;
	}

	public List<PartyResultsVO> getMandalResults() {
		return mandalResults;
	}

}
