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

public class VotesMarginResultsMainVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long constituencyId;
	private String constituencyName;
	private ConstituencyVO constituencyVO;
	
	private List<PartyVotesMarginResultsInMandal> partyVotesMarginResultsInMandal;
    private ResultStatus resultStatus;
	
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

	public ConstituencyVO getConstituencyVO() {
		return constituencyVO;
	}

	public void setConstituencyVO(ConstituencyVO constituencyVO) {
		this.constituencyVO = constituencyVO;
	}

	public List<PartyVotesMarginResultsInMandal> getPartyVotesMarginResultsInMandal() {
		return partyVotesMarginResultsInMandal;
	}

	public void setPartyVotesMarginResultsInMandal(
			List<PartyVotesMarginResultsInMandal> partyVotesMarginResultsInMandal) {
		this.partyVotesMarginResultsInMandal = partyVotesMarginResultsInMandal;
	}

	public ResultStatus getResultStatus() {
		return resultStatus;
	}

	public void setResultStatus(ResultStatus resultStatus) {
		this.resultStatus = resultStatus;
	}
	
	

}
