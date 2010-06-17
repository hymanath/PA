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

import com.itgrids.partyanalyst.excel.booth.BoothResultVO;

public class PartyResultsInVotesMarginVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int marginValue1;
	private int marginValue2;
	private int resultsCount;
	
	private List<BoothResultVO> boothResultsVO;
	private ResultStatus resultStatus;
	
	public int getMarginValue1() {
		return marginValue1;
	}
	public void setMarginValue1(int marginValue1) {
		this.marginValue1 = marginValue1;
	}
	public int getMarginValue2() {
		return marginValue2;
	}
	public void setMarginValue2(int marginValue2) {
		this.marginValue2 = marginValue2;
	}
	public int getResultsCount() {
		return resultsCount;
	}
	public void setResultsCount(int resultsCount) {
		this.resultsCount = resultsCount;
	}
	public List<BoothResultVO> getBoothResultsVO() {
		return boothResultsVO;
	}
	public void setBoothResultsVO(List<BoothResultVO> boothResultsVO) {
		this.boothResultsVO = boothResultsVO;
	}
	public ResultStatus getResultStatus() {
		return resultStatus;
	}
	public void setResultStatus(ResultStatus resultStatus) {
		this.resultStatus = resultStatus;
	}
	
    
}
