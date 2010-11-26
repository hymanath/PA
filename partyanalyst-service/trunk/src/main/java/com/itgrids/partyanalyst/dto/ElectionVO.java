package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.List;

public class ElectionVO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3990779151003505604L;
	
	private List<SelectOptionVO> electionYears;
	private List<TeshilPartyInfoVO> muncipalityVO;
	private ResultStatus resultStatus;
	
	
	public ResultStatus getResultStatus() {
		return resultStatus;
	}

	public void setResultStatus(ResultStatus resultStatus) {
		this.resultStatus = resultStatus;
	}
	
	public List<TeshilPartyInfoVO> getMuncipalityVO() {
		return muncipalityVO;
	}

	public void setMuncipalityVO(List<TeshilPartyInfoVO> muncipalityVO) {
		this.muncipalityVO = muncipalityVO;
	}

	public List<SelectOptionVO> getElectionYears() {
		return electionYears;
	}

	public void setElectionYears(List<SelectOptionVO> electionYears) {
		this.electionYears = electionYears;
	}

}
