package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SearchAttributeVO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private Date startDate;
	private Date endDate;
	private String searchType;
	private List<Long> locationIdsList = new ArrayList<Long>();
	private List<Long> locationTypeIdsList = new ArrayList<Long>();
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getSearchType() {
		return searchType;
	}
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	public List<Long> getLocationIdsList() {
		return locationIdsList;
	}
	public void setLocationIdsList(List<Long> locationIdsList) {
		this.locationIdsList = locationIdsList;
	}
	public List<Long> getLocationTypeIdsList() {
		return locationTypeIdsList;
	}
	public void setLocationTypeIdsList(List<Long> locationTypeIdsList) {
		this.locationTypeIdsList = locationTypeIdsList;
	}
}
