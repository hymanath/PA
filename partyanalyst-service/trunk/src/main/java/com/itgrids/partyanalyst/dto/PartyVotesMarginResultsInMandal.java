/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on Jun 17,2010
 */
package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.List;

public class PartyVotesMarginResultsInMandal implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long mandalId;
	private String mandalName;
	
	private List<PartyVotesMarginResultsVO> partyVotesMarginResultsVO;
	private ResultStatus resultStatus;

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

	public List<PartyVotesMarginResultsVO> getPartyVotesMarginResultsVO() {
		return partyVotesMarginResultsVO;
	}

	public void setPartyVotesMarginResultsVO(
			List<PartyVotesMarginResultsVO> partyVotesMarginResultsVO) {
		this.partyVotesMarginResultsVO = partyVotesMarginResultsVO;
	}

	public ResultStatus getResultStatus() {
		return resultStatus;
	}

	public void setResultStatus(ResultStatus resultStatus) {
		this.resultStatus = resultStatus;
	}
	
	
}
