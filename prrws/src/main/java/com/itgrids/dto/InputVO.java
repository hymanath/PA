package com.itgrids.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class InputVO implements Serializable {
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
	private Long locationTypeId;
	private Long superLocationId;
	private Long searchLevelId;
	private Long searchLevelValue;
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
	
	private Long groupByValue=0l;
	private List<Long> locationValues = new ArrayList<Long>(0);
	private Long stressedHabitationYear;
	private String divType;
	private String districtValue;
	private String workStatus; 
	private String assetType;
	private String endValue;
	private String startValue;
	private List<String> assetTypeList;
	private List<String> statusList;
	private String status;
	private List<Long> govtSchmeIdsList;
	private List<Long> subProgramIdsList;
	private Long glSearchLevelId;
	private List<Long> glSearchLevelValue;
	private String fromPage;
	private String toPage;
	private Long filterId;
	private Long subFilterId;
	private String subFilterType;
	
	private String sublocaType;
	private String SublocationType;
	private String viewType;
	private Long fromRange;
	private Long toRange;
	
	private String locationIdStr;
	
	
	public String getLocationIdStr() {
		return locationIdStr;
	}
	public void setLocationIdStr(String locationIdStr) {
		this.locationIdStr = locationIdStr;
	}
	public String getSublocaType() {
		return sublocaType;
	}
	public void setSublocaType(String sublocaType) {
		this.sublocaType = sublocaType;
	}
	public String getSublocationType() {
		return SublocationType;
	}
	public void setSublocationType(String sublocationType) {
		SublocationType = sublocationType;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<String> getStatusList() {
		return statusList;
	}
	public void setStatusList(List<String> statusList) {
		this.statusList = statusList;
	}
	public List<String> getAssetTypeList() {
		return assetTypeList;
	}
	public void setAssetTypeList(List<String> assetTypeList) {
		this.assetTypeList = assetTypeList;
	}
	public String getEndValue() {
		return endValue;
	}
	public void setEndValue(String endValue) {
		this.endValue = endValue;
	}
	public String getStartValue() {
		return startValue;
	}
	public void setStartValue(String startValue) {
		this.startValue = startValue;
	}
	public String getAssetType() {
		return assetType;
	}
	public void setAssetType(String assetType) {
		this.assetType = assetType;
	}
	public String getWorkStatus() {
		return workStatus;
	}
	public void setWorkStatus(String workStatus) {
		this.workStatus = workStatus;
	}
	public String getDistrictValue() {
		return districtValue;
	}
	public void setDistrictValue(String districtValue) {
		this.districtValue = districtValue;
	}
	public Long getStressedHabitationYear() {
		return stressedHabitationYear;
	}
	public void setStressedHabitationYear(Long stressedHabitationYear) {
		this.stressedHabitationYear = stressedHabitationYear;
	}
	public Long getGroupByValue() {
		return groupByValue;
	}
	public void setGroupByValue(Long groupByValue) {
		this.groupByValue = groupByValue;
	}
	public List<Long> getLocationValues() {
		return locationValues;
	}
	public void setLocationValues(List<Long> locationValues) {
		this.locationValues = locationValues;
	}
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
	public Long getLocationTypeId() {
		return locationTypeId;
	}
	public void setLocationTypeId(Long locationTypeId) {
		this.locationTypeId = locationTypeId;
	}
	public String getDivType() {
		return divType;
	}
	public void setDivType(String divType) {
		this.divType = divType;
	}
	public Long getSearchLevelValue() {
		return searchLevelValue;
	}
	public void setSearchLevelValue(Long searchLevelValue) {
		this.searchLevelValue = searchLevelValue;
	}
	public List<Long> getGovtSchmeIdsList() {
		return govtSchmeIdsList;
	}
	public void setGovtSchmeIdsList(List<Long> govtSchmeIdsList) {
		this.govtSchmeIdsList = govtSchmeIdsList;
	}
	public List<Long> getSubProgramIdsList() {
		return subProgramIdsList;
	}
	public void setSubProgramIdsList(List<Long> subProgramIdsList) {
		this.subProgramIdsList = subProgramIdsList;
	}
	public Long getGlSearchLevelId() {
		return glSearchLevelId;
	}
	public void setGlSearchLevelId(Long glSearchLevelId) {
		this.glSearchLevelId = glSearchLevelId;
	}
	public List<Long> getGlSearchLevelValue() {
		return glSearchLevelValue;
	}
	public void setGlSearchLevelValue(List<Long> glSearchLevelValue) {
		this.glSearchLevelValue = glSearchLevelValue;
	}
	public String getFromPage() {
		return fromPage;
	}
	public void setFromPage(String fromPage) {
		this.fromPage = fromPage;
	}
	public String getToPage() {
		return toPage;
	}
	public void setToPage(String toPage) {
		this.toPage = toPage;
	}
	public Long getFilterId() {
		return filterId;
	}
	public void setFilterId(Long filterId) {
		this.filterId = filterId;
	}
	public Long getSubFilterId() {
		return subFilterId;
	}
	public void setSubFilterId(Long subFilterId) {
		this.subFilterId = subFilterId;
	}
	public String getSubFilterType() {
		return subFilterType;
	}
	public void setSubFilterType(String subFilterType) {
		this.subFilterType = subFilterType;
	}
	public String getViewType() {
		return viewType;
	}
	public void setViewType(String viewType) {
		this.viewType = viewType;
	}
	public Long getFromRange() {
		return fromRange;
	}
	public void setFromRange(Long fromRange) {
		this.fromRange = fromRange;
	}
	public Long getToRange() {
		return toRange;
	}
	public void setToRange(Long toRange) {
		this.toRange = toRange;
	}
	
}
