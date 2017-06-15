package com.itgrids.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class LocationVO implements Serializable {
	private Long locationLevelId;
	private Long locaitonLevelName;
	private Long locationId;
	private String locationName;
	private Long amount = 0L;
	private Long count = 0L;
	private Long financialYearId;
	private String financialYear;
	private List<LocationVO> locationList1;
	private List<LocationVO> locationList2;
	private String amunt = "0.0";
	private String workName;
	private String departmentName;
	private String schemeName;
	private String goNoDate;
	private String sactionAmount;
	
	
	private Long streetHabitationCount;
	private Long totalCount;
	private List<StatusVO> statusList = new ArrayList<StatusVO>(0);
	
	
	public Long getLocationLevelId() {
		return locationLevelId;
	}
	public void setLocationLevelId(Long locationLevelId) {
		this.locationLevelId = locationLevelId;
	}
	public Long getLocationId() {
		return locationId;
	}
	public void setLocationId(Long locationId) {
		this.locationId = locationId;
	}
	public String getLocationName() {
		return locationName;
	}
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	public Long getAmount() {
		return amount;
	}
	public void setAmount(Long amount) {
		this.amount = amount;
	}
	public Long getLocaitonLevelName() {
		return locaitonLevelName;
	}
	public void setLocaitonLevelName(Long locaitonLevelName) {
		this.locaitonLevelName = locaitonLevelName;
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	public Long getFinancialYearId() {
		return financialYearId;
	}
	public void setFinancialYearId(Long financialYearId) {
		this.financialYearId = financialYearId;
	}
	public String getFinancialYear() {
		return financialYear;
	}
	public void setFinancialYear(String financialYear) {
		this.financialYear = financialYear;
	}
	public List<LocationVO> getLocationList1() {
		if(locationList1 == null){
			locationList1 = new ArrayList<LocationVO>();
		}
		return locationList1;
	}
	public List<LocationVO> getLocationList2() {
		if(locationList2 == null){
			locationList2 = new ArrayList<LocationVO>();
		}
		return locationList2;
	}
	public String getAmunt() {
		return amunt;
	}
	public void setAmunt(String amunt) {
		this.amunt = amunt;
	}
	public String getWorkName() {
		return workName;
	}
	public void setWorkName(String workName) {
		this.workName = workName;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public String getSchemeName() {
		return schemeName;
	}
	public void setSchemeName(String schemeName) {
		this.schemeName = schemeName;
	}
	public String getGoNoDate() {
		return goNoDate;
	}
	public void setGoNoDate(String goNoDate) {
		this.goNoDate = goNoDate;
	}
	public Long getStreetHabitationCount() {
		return streetHabitationCount;
	}
	public void setStreetHabitationCount(Long streetHabitationCount) {
		this.streetHabitationCount = streetHabitationCount;
	}
	public Long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	public List<StatusVO> getStatusList() {
		return statusList;
	}
	public void setStatusList(List<StatusVO> statusList) {
		this.statusList = statusList;
	}
	public String getSactionAmount() {
		return sactionAmount;
	}
	public void setSactionAmount(String sactionAmount) {
		this.sactionAmount = sactionAmount;
	}
	
	
	
}
