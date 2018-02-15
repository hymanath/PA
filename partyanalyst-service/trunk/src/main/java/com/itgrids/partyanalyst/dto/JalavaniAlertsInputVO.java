package com.itgrids.partyanalyst.dto;

import java.io.Serializable;

public class JalavaniAlertsInputVO implements Serializable {
	private Long id;
	private String name;
	private String fromDateStr;
	private String toDateStr;
	private String searchType;
	private String type;
	private Long locationTypeId;
	private Long subLocationId;
	private Long alertCategoryId;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Long getLocationTypeId() {
		return locationTypeId;
	}
	public void setLocationTypeId(Long locationTypeId) {
		this.locationTypeId = locationTypeId;
	}
	public Long getSubLocationId() {
		return subLocationId;
	}
	public void setSubLocationId(Long subLocationId) {
		this.subLocationId = subLocationId;
	}
	public Long getAlertCategoryId() {
		return alertCategoryId;
	}
	public void setAlertCategoryId(Long alertCategoryId) {
		this.alertCategoryId = alertCategoryId;
	}
	public String getSearchType() {
		return searchType;
	}
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
}
