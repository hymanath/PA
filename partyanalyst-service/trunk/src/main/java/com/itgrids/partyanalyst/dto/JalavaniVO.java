package com.itgrids.partyanalyst.dto;

import java.util.ArrayList;
import java.util.List;

public class JalavaniVO {
	private Long blockLevelId;
	private List<Long> levelValues = new ArrayList<Long>(0);
	private List<Long> financialYrIdList;
	private List<Long> deptIdsList;
	private List<Long> sourceIdsList;
	private List<Long> schemeIdsList;
	private Long deptId;
	private Long sourceId;
	private Long schemeId;
	private String fromDateStr;
	private String toDateStr;
	private String sortingType;
	private String order;
	private String type;
	private Long locationId;
	private Long superLocationId;
	private Long searchLevelId;
	private List<Long> searchLvlVals;
	
	private String year;
	private String locationType;
	private String locationName;
	private String filterType;
	private String filterValue;
	private String fromDate;
	private String toDate;
	
	private int startIndex;
	private int endIndex;
	private List<Long> statusIds = new ArrayList<Long>(0);
	
	
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
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
	
	public Long getBlockLevelId() {
		return blockLevelId;
	}
	public void setBlockLevelId(Long blockLevelId) {
		this.blockLevelId = blockLevelId;
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
	public Long getSchemeId() {
		return schemeId;
	}
	public void setSchemeId(Long schemeId) {
		this.schemeId = schemeId;
	}
	public String getSortingType() {
		return sortingType;
	}
	public void setSortingType(String sortingType) {
		this.sortingType = sortingType;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	public Long getLocationId() {
		return locationId;
	}
	public void setLocationId(Long locationId) {
		this.locationId = locationId;
	}
	public Long getSuperLocationId() {
		return superLocationId;
	}
	public void setSuperLocationId(Long superLocationId) {
		this.superLocationId = superLocationId;
	}
	public Long getSearchLevelId() {
		return searchLevelId;
	}
	public void setSearchLevelId(Long searchLevelId) {
		this.searchLevelId = searchLevelId;
	}
	public List<Long> getSearchLvlVals() {
		return searchLvlVals;
	}
	public void setSearchLvlVals(List<Long> searchLvlVals) {
		this.searchLvlVals = searchLvlVals;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getLocationType() {
		return locationType;
	}
	public void setLocationType(String locationType) {
		this.locationType = locationType;
	}
	public String getLocationName() {
		return locationName;
	}
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	public String getFilterType() {
		return filterType;
	}
	public void setFilterType(String filterType) {
		this.filterType = filterType;
	}
	public String getFilterValue() {
		return filterValue;
	}
	public void setFilterValue(String filterValue) {
		this.filterValue = filterValue;
	}
	public int getStartIndex() {
		return startIndex;
	}
	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}
	public int getEndIndex() {
		return endIndex;
	}
	public void setEndIndex(int endIndex) {
		this.endIndex = endIndex;
	}
	public List<Long> getStatusIds() {
		return statusIds;
	}
	public void setStatusIds(List<Long> statusIds) {
		this.statusIds = statusIds;
	}
	
	
	
}
