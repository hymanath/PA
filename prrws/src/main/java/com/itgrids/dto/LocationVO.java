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
	
}
