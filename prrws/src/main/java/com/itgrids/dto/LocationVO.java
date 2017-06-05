package com.itgrids.dto;

import java.io.Serializable;

public class LocationVO implements Serializable {
	private Long locationLevelId;
	private Long locaitonLevelName;
	private Long locationId;
	private String locationName;
	private Double amount = 0.0D;
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
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public Long getLocaitonLevelName() {
		return locaitonLevelName;
	}
	public void setLocaitonLevelName(Long locaitonLevelName) {
		this.locaitonLevelName = locaitonLevelName;
	}
	
}
