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

public class MandalAllElectionResultsVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long mandalId;
	private String mandalName;
	
	private ResultStatus resultStatus;
	private List<AllPartyElectionResultsForElectionTypeVO> electionResultsForAllPartiesVO;

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

	public List<AllPartyElectionResultsForElectionTypeVO> getElectionResultsForAllPartiesVO() {
		return electionResultsForAllPartiesVO;
	}

	public void setElectionResultsForAllPartiesVO(
			List<AllPartyElectionResultsForElectionTypeVO> electionResultsForAllPartiesVO) {
		this.electionResultsForAllPartiesVO = electionResultsForAllPartiesVO;
	}
	
}
