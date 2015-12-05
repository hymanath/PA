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
	private List<Long> attributesIdsList = new ArrayList<Long>(0);
	private Long typeId;
	private String conditionType;
	private String attributeType;
	
	
	public String getAttributeType() {
		return attributeType;
	}
	public void setAttributeType(String attributeType) {
		this.attributeType = attributeType;
	}
	public String getConditionType() {
		return conditionType;
	}
	public void setConditionType(String conditionType) {
		this.conditionType = conditionType;
	}
	public Long getTypeId() {
		return typeId;
	}
	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}
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
	public List<Long> getAttributesIdsList() {
		return attributesIdsList;
	}
	public void setAttributesIdsList(List<Long> attributesIdsList) {
		this.attributesIdsList = attributesIdsList;
	}
}
