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

public class BiElectionResultsVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long constituencyId;
	private String constituencyName;
	
	private List<ElectionResultsForMandalVO> biElectionResultsVO;
    private ResultStatus resultStatus; 
		
	private List<SelectOptionVO> participatedParties;	
	

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

	public List<ElectionResultsForMandalVO> getBiElectionResultsVO() {
		return biElectionResultsVO;
	}

	public void setBiElectionResultsVO(
			List<ElectionResultsForMandalVO> biElectionResultsVO) {
		this.biElectionResultsVO = biElectionResultsVO;
	}

	public ResultStatus getResultStatus() {
		return resultStatus;
	}

	public void setResultStatus(ResultStatus resultStatus) {
		this.resultStatus = resultStatus;
	}

	public List<SelectOptionVO> getParticipatedParties() {
		return participatedParties;
	}

	public void setParticipatedParties(List<SelectOptionVO> participatedParties) {
		this.participatedParties = participatedParties;
	}

}
