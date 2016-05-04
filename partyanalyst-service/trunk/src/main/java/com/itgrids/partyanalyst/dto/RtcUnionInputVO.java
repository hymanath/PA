package com.itgrids.partyanalyst.dto;

import java.util.List;

public class RtcUnionInputVO {

	private List<Long> memeberTypeIds;
	private String locationType;
	private Long locationId;
	private String startDate;
	private String toDate;
	private String dateType;
	private String appType;
	private String sourceType;
	public List<Long> getMemeberTypeIds() {
		return memeberTypeIds;
	}
	public void setMemeberTypeIds(List<Long> memeberTypeIds) {
		this.memeberTypeIds = memeberTypeIds;
	}
	public String getLocationType() {
		return locationType;
	}
	public void setLocationType(String locationType) {
		this.locationType = locationType;
	}
	public Long getLocationId() {
		return locationId;
	}
	public void setLocationId(Long locationId) {
		this.locationId = locationId;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	public String getDateType() {
		return dateType;
	}
	public void setDateType(String dateType) {
		this.dateType = dateType;
	}
	public String getAppType() {
		return appType;
	}
	public void setAppType(String appType) {
		this.appType = appType;
	}
	public String getSourceType() {
		return sourceType;
	}
	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}
	
	
	
	
}
