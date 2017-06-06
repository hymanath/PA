package com.itgrids.dto;

import java.io.Serializable;
import java.util.List;

public class InputVO implements Serializable {
	private Long levelId;
	private List<Long> levelValues;
	private List<Long> financialYrIdList;
	private List<Long> deptIdsList;
	private List<Long> sourceIdsList;
	private List<Long> schemeIdsList;
	private Long deptId;
	private Long sourceId;
	private String fromDateStr;
	private String toDateStr;
	private String type;
	
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public List<Long> getDeptIdsList() {
		return deptIdsList;
	}
	public void setDeptIdsList(List<Long> deptIdsList) {
		this.deptIdsList = deptIdsList;
	}
	public List<Long> getSourceIdsList() {
		return sourceIdsList;
	}
	public void setSourceIdsList(List<Long> sourceIdsList) {
		this.sourceIdsList = sourceIdsList;
	}
	public List<Long> getSchemeIdsList() {
		return schemeIdsList;
	}
	public void setSchemeIdsList(List<Long> schemeIdsList) {
		this.schemeIdsList = schemeIdsList;
	}
	public Long getLevelId() {
		return levelId;
	}
	public void setLevelId(Long levelId) {
		this.levelId = levelId;
	}
	public List<Long> getLevelValues() {
		return levelValues;
	}
	public void setLevelValues(List<Long> levelValues) {
		this.levelValues = levelValues;
	}
	public List<Long> getFinancialYrIdList() {
		return financialYrIdList;
	}
	public void setFinancialYrIdList(List<Long> financialYrIdList) {
		this.financialYrIdList = financialYrIdList;
	}
	public Long getDeptId() {
		return deptId;
	}
	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}
	public Long getSourceId() {
		return sourceId;
	}
	public void setSourceId(Long sourceId) {
		this.sourceId = sourceId;
	}
	public String getFromDateStr() {
		return fromDateStr;
	}
	public void setFromDateStr(String fromDateStr) {
		this.fromDateStr = fromDateStr;
	}
	public String getToDateStr() {
		return toDateStr;
	}
	public void setToDateStr(String toDateStr) {
		this.toDateStr = toDateStr;
	}
}
