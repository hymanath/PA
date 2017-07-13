package com.itgrids.partyanalyst.dto;

import java.util.Date;

public class InputVO {

	private String locationLevel;
	private String filterLevel;
	private Long filterValue;
	private String fromDateStr;
	private String toDateStr;
	private Date fromDate;
	private Date toDate;
	private Long boothInchargeEnrollmentId;
	private String resultType;
	
	public String getLocationLevel() {
		return locationLevel;
	}
	public void setLocationLevel(String locationLevel) {
		this.locationLevel = locationLevel;
	}
	public String getFilterLevel() {
		return filterLevel;
	}
	public void setFilterLevel(String filterLevel) {
		this.filterLevel = filterLevel;
	}
	public Long getFilterValue() {
		return filterValue;
	}
	public void setFilterValue(Long filterValue) {
		this.filterValue = filterValue;
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
	public Long getBoothInchargeEnrollmentId() {
		return boothInchargeEnrollmentId;
	}
	public void setBoothInchargeEnrollmentId(Long boothInchargeEnrollmentId) {
		this.boothInchargeEnrollmentId = boothInchargeEnrollmentId;
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
	public String getResultType() {
		return resultType;
	}
	public void setResultType(String resultType) {
		this.resultType = resultType;
	}
    
	
}
