/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on April 26,2010
 */
package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.List;

public class PartyPositionAnalysisResultVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 950496479227277254L;
	
	private String resultType;      //(won / lost)
	private Long resultTypeValue;   //(seats count)
	private Long analyzedConsti;
	private Long notAnalyzedConsti;
	
	private List<SelectOptionVO> multipleCategories;
	private List<AnalysisCategoryBasicVO> analysisCategoryBasicResultVO;
	
	private Long totalUsers;
	
	public Long getTotalUsers() {
		return totalUsers;
	}

	public void setTotalUsers(Long totalUsers) {
		this.totalUsers = totalUsers;
	}

	private ResultStatus resultStatus = new ResultStatus();
	
	public String getResultType() {
		return resultType;
	}

	public void setResultType(String resultType) {
		this.resultType = resultType;
	}

	public Long getResultTypeValue() {
		return resultTypeValue;
	}

	public void setResultTypeValue(Long resultTypeValue) {
		this.resultTypeValue = resultTypeValue;
	}

	public Long getAnalyzedConsti() {
		return analyzedConsti;
	}

	public void setAnalyzedConsti(Long analyzedConsti) {
		this.analyzedConsti = analyzedConsti;
	}

	public Long getNotAnalyzedConsti() {
		return notAnalyzedConsti;
	}

	public void setNotAnalyzedConsti(Long notAnalyzedConsti) {
		this.notAnalyzedConsti = notAnalyzedConsti;
	}

	public List<SelectOptionVO> getMultipleCategories() {
		return multipleCategories;
	}

	public void setMultipleCategories(List<SelectOptionVO> multipleCategories) {
		this.multipleCategories = multipleCategories;
	}

	public List<AnalysisCategoryBasicVO> getAnalysisCategoryBasicResultVO() {
		return analysisCategoryBasicResultVO;
	}

	public void setAnalysisCategoryBasicResultVO(
			List<AnalysisCategoryBasicVO> analysisCategoryBasicResultVO) {
		this.analysisCategoryBasicResultVO = analysisCategoryBasicResultVO;
	}

	public ResultStatus getResultStatus() {
		return resultStatus;
	}

	public void setResultStatus(ResultStatus resultStatus) {
		this.resultStatus = resultStatus;
	}
	
	

}
