package com.itgrids.dto;

import java.util.ArrayList;
import java.util.List;

public class EncVO {
	
	private Long locationId;
	private String locationName;
	private Long paramValue =0l;
	private String paramName;
	private String locationType;
	private String status;
	private String errorMessage;
	private Long totalRoadsLength=0l;
	
	private List<EncVO> subList = new ArrayList<EncVO>();

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

	public Long getParamValue() {
		return paramValue;
	}

	public void setParamValue(Long paramValue) {
		this.paramValue = paramValue;
	}

	public String getParamName() {
		return paramName;
	}

	public void setParamName(String paramName) {
		this.paramName = paramName;
	}

	public String getLocationType() {
		return locationType;
	}

	public void setLocationType(String locationType) {
		this.locationType = locationType;
	}

	public List<EncVO> getSubList() {
		return subList;
	}

	public void setSubList(List<EncVO> subList) {
		this.subList = subList;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public Long getTotalRoadsLength() {
		return totalRoadsLength;
	}

	public void setTotalRoadsLength(Long totalRoadsLength) {
		this.totalRoadsLength = totalRoadsLength;
	}
	
	

}
