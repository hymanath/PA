/* 
 * Copyright (c) 2011 IT Grids India LTD.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on February 07, 2011
 */
package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * @author Sai Krishna
 *
 */
public class ProblemManagementChartVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<SelectOptionVO> chartLegends;
	
	private String chartTitle;
	private String chartDesc;
	
	private List<ProblemManagementChartDataVO> chartDataVO;
	
	private ResultStatus resultStatus;
	
	private Long startIndex;
	private Long maxIndex;
	private Long totalCount;

	public List<SelectOptionVO> getChartLegends() {
		return chartLegends;
	}

	public void setChartLegends(List<SelectOptionVO> chartLegends) {
		this.chartLegends = chartLegends;
	}

	public String getChartTitle() {
		return chartTitle;
	}

	public void setChartTitle(String chartTitle) {
		this.chartTitle = chartTitle;
	}

	public String getChartDesc() {
		return chartDesc;
	}

	public void setChartDesc(String chartDesc) {
		this.chartDesc = chartDesc;
	}

	public List<ProblemManagementChartDataVO> getChartDataVO() {
		return chartDataVO;
	}

	public void setChartDataVO(List<ProblemManagementChartDataVO> chartDataVO) {
		this.chartDataVO = chartDataVO;
	}

	public ResultStatus getResultStatus() {
		return resultStatus;
	}

	public void setResultStatus(ResultStatus resultStatus) {
		this.resultStatus = resultStatus;
	}

	public Long getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(Long startIndex) {
		this.startIndex = startIndex;
	}

	public Long getMaxIndex() {
		return maxIndex;
	}

	public void setMaxIndex(Long maxIndex) {
		this.maxIndex = maxIndex;
	}

	public Long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	

}
