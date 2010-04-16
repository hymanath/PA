package com.itgrids.partyanalyst.dto;


public class PanchayathOrTownInfoVO {

	private String locationName;
	private Object[] rawData;
	
	public String getLocationName() {
		return locationName;
	}
	
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	
	public Object[] getRawData() {
		return rawData;
	}
	
	public void setRawData(Object[] rawData) {
		this.rawData = rawData;
	}
	
}
