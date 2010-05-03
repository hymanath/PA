/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on May 1,2010
 */
package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.List;

public class VotesMarginAnalysisVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8394010146351006404L;
	
	private Long marginValueOne;
	private Long marginValueTwo;
	private String marginRange;
	private Long candidatesCount;
	private Long analyzedCount;
	
	private List<Long> nominationIds;
	private List<AnalysisCategoryBasicVO> analysisCategoryBasicVO;
	private ResultStatus resultStatus;

	public Long getMarginValueOne() {
		return marginValueOne;
	}

	public void setMarginValueOne(Long marginValueOne) {
		this.marginValueOne = marginValueOne;
	}

	public Long getMarginValueTwo() {
		return marginValueTwo;
	}

	public void setMarginValueTwo(Long marginValueTwo) {
		this.marginValueTwo = marginValueTwo;
	}

	public String getMarginRange() {
		return marginRange;
	}

	public void setMarginRange(String marginRange) {
		this.marginRange = marginRange;
	}

	public Long getCandidatesCount() {
		return candidatesCount;
	}

	public void setCandidatesCount(Long candidatesCount) {
		this.candidatesCount = candidatesCount;
	}

	public Long getAnalyzedCount() {
		return analyzedCount;
	}

	public void setAnalyzedCount(Long analyzedCount) {
		this.analyzedCount = analyzedCount;
	}

	public List<Long> getNominationIds() {
		return nominationIds;
	}

	public void setNominationIds(List<Long> nominationIds) {
		this.nominationIds = nominationIds;
	}

	public ResultStatus getResultStatus() {
		return resultStatus;
	}

	public void setResultStatus(ResultStatus resultStatus) {
		this.resultStatus = resultStatus;
	}

	public List<AnalysisCategoryBasicVO> getAnalysisCategoryBasicVO() {
		return analysisCategoryBasicVO;
	}

	public void setAnalysisCategoryBasicVO(
			List<AnalysisCategoryBasicVO> analysisCategoryBasicVO) {
		this.analysisCategoryBasicVO = analysisCategoryBasicVO;
	}

}
