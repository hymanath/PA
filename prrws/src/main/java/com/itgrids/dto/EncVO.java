package com.itgrids.dto;

import java.util.ArrayList;
import java.util.List;

public class EncVO {
	
	private Long locationId;
	private String locationName;
	private Double paramValue =0.0;
	private String paramName;
	private String locationType;
	private String status;
	private String errorMessage;
	private Double totalRoadsLength=0.0;
	private Long totalHabs=0l;
	private Long districtId;
	private String districtName;
	private Long constituencyId;
	private Long constituencyname;
	
	
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

	public Double getParamValue() {
		return paramValue;
	}

	public void setParamValue(Double paramValue) {
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

	public Double getTotalRoadsLength() {
		return totalRoadsLength;
	}

	public void setTotalRoadsLength(Double totalRoadsLength) {
		this.totalRoadsLength = totalRoadsLength;
	}

	public Long getTotalHabs() {
		return totalHabs;
	}

	public void setTotalHabs(Long totalHabs) {
		this.totalHabs = totalHabs;
	}

	public Long getDistrictId() {
		return districtId;
	}

	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public Long getConstituencyId() {
		return constituencyId;
	}

	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}

	public Long getConstituencyname() {
		return constituencyname;
	}

	public void setConstituencyname(Long constituencyname) {
		this.constituencyname = constituencyname;
	}
	
	

}
