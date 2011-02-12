/* 
 * Copyright (c) 2011 IT Grids India LTD.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on February 07, 2011
 */
package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author Sai Krishna
 *
 */
public class ProblemManagementChartDataVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String chartLabel;
	private BigDecimal problemsCount;
	private BigDecimal avgCount;
	
	private List<BigDecimal> chartValues;
	
	public String getChartLabel() {
		return chartLabel;
	}
	public void setChartLabel(String chartLabel) {
		this.chartLabel = chartLabel;
	}
	public List<BigDecimal> getChartValues() {
		return chartValues;
	}
	public void setChartValues(List<BigDecimal> chartValues) {
		this.chartValues = chartValues;
	}
	public BigDecimal getProblemsCount() {
		return problemsCount;
	}
	public void setProblemsCount(BigDecimal problemsCount) {
		this.problemsCount = problemsCount;
	}
	public BigDecimal getAvgCount() {
		return avgCount;
	}
	public void setAvgCount(BigDecimal avgCount) {
		this.avgCount = avgCount;
	}
	
	

}
