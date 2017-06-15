package com.itgrids.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MatrixRangeVO implements Serializable{

	private Long id;
	private String name;
	private Long count;
	private String min;
	private String max;
	private Long presentFinancialYearId;
	private Long previousFinancialYearId;
	List<MatrixRangeVO> subList = new ArrayList<MatrixRangeVO>(0);
	private Long scopeId;
	private Long totalCount;
	
	
	public Long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	public Long getScopeId() {
		return scopeId;
	}
	public void setScopeId(Long scopeId) {
		this.scopeId = scopeId;
	}
	
	public Long getPresentFinancialYearId() {
		return presentFinancialYearId;
	}
	public void setPresentFinancialYearId(Long presentFinancialYearId) {
		this.presentFinancialYearId = presentFinancialYearId;
	}
	public Long getPreviousFinancialYearId() {
		return previousFinancialYearId;
	}
	public void setPreviousFinancialYearId(Long previousFinancialYearId) {
		this.previousFinancialYearId = previousFinancialYearId;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	
	public String getMin() {
		return min;
	}
	public void setMin(String min) {
		this.min = min;
	}
	public String getMax() {
		return max;
	}
	public void setMax(String max) {
		this.max = max;
	}
	public List<MatrixRangeVO> getSubList() {
		return subList;
	}
	public void setSubList(List<MatrixRangeVO> subList) {
		this.subList = subList;
	}
}
