package com.rwss.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class InputVO {

	private String year;
	private String locationType;
	private String locationName;
	private String fromDateStr;
	private String toDateStr;
	private Date fromDate;
	private Date toDate;
	private String filterType;
	private String filterValue;
	private String districtValue;
	private String stressedHabitationYear;
	private String schemeType;
	private String workStatus;
	private String assetType;
	private Integer startValue;
	private Integer endValue;
	private List<String> assetTypeList;
	private List<String> statusList;
	private String type;
	private String status;
	private List<String> stressedHabitationYearsList = new ArrayList<String>(0);
	private String assetSubType;
	

	
	public String getAssetSubType() {
		return assetSubType;
	}
	public void setAssetSubType(String assetSubType) {
		this.assetSubType = assetSubType;
	}
	public List<String> getStressedHabitationYearsList() {
		return stressedHabitationYearsList;
	}
	public void setStressedHabitationYearsList(List<String> stressedHabitationYearsList) {
		this.stressedHabitationYearsList = stressedHabitationYearsList;
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
	public Date getFromDate() {
		return fromDate;
	}
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}
	public Date getToDate() {
		return toDate;
	}
	public void setToDate(Date toDate) {
		this.toDate = toDate;
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
	public String getDistrictValue() {
		return districtValue;
	}
	public void setDistrictValue(String districtValue) {
		this.districtValue = districtValue;
	}
	public String getStressedHabitationYear() {
		return stressedHabitationYear;
	}
	public void setStressedHabitationYear(String stressedHabitationYear) {
		this.stressedHabitationYear = stressedHabitationYear;
	}
	public String getSchemeType() {
		return schemeType;
	}
	public void setSchemeType(String schemeType) {
		this.schemeType = schemeType;
	}
	public String getWorkStatus() {
		return workStatus;
	}
	public void setWorkStatus(String workStatus) {
		this.workStatus = workStatus;
	}
	public String getAssetType() {
		return assetType;
	}
	public void setAssetType(String assetType) {
		this.assetType = assetType;
	}
	public Integer getStartValue() {
		return startValue;
	}
	public void setStartValue(Integer startValue) {
		this.startValue = startValue;
	}
	public Integer getEndValue() {
		return endValue;
	}
	public void setEndValue(Integer endValue) {
		this.endValue = endValue;
	}
	public List<String> getStatusList() {
		return statusList;
	}
	public void setStatusList(List<String> statusList) {
		this.statusList = statusList;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public List<String> getAssetTypeList() {
		return assetTypeList;
	}
	public void setAssetTypeList(List<String> assetTypeList) {
		this.assetTypeList = assetTypeList;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
