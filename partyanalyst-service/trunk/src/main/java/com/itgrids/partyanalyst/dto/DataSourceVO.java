package com.itgrids.partyanalyst.dto;

import java.io.Serializable;

public class DataSourceVO implements Serializable {
	private Long totalCount = 0l;
	private Long renewalCount = 0l;
	private Long newCount = 0l;
	private String sourceType;
	private Long regionScopeId = 0l;
	private Long locationValue = 0l;
	public Long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	public Long getRenewalCount() {
		return renewalCount;
	}
	public void setRenewalCount(Long renewalCount) {
		this.renewalCount = renewalCount;
	}
	public Long getNewCount() {
		return newCount;
	}
	public void setNewCount(Long newCount) {
		this.newCount = newCount;
	}
	public String getSourceType() {
		return sourceType;
	}
	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}
	public Long getRegionScopeId() {
		return regionScopeId;
	}
	public void setRegionScopeId(Long regionScopeId) {
		this.regionScopeId = regionScopeId;
	}
	public Long getLocationValue() {
		return locationValue;
	}
	public void setLocationValue(Long locationValue) {
		this.locationValue = locationValue;
	}
	
	
}
