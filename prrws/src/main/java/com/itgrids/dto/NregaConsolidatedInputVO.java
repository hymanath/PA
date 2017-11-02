package com.itgrids.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class NregaConsolidatedInputVO implements Serializable{

	private Long id;
	private String name;
	private Long convergenceTypeId;
	private String componentName;
	private String url;
	
	private String fromDate;
	private String toDate;
	private String year;
	private String locationIdStr;
	private String locationType;
	private String subLocationType;
	private List<Long> componentIds = new ArrayList<Long>();
	private String searchType;
	private String program;
	private String categoryName;
	
	
	public void setConvergenceTypeId(Long convergenceTypeId) {
		this.convergenceTypeId = convergenceTypeId;
	}
	public String getProgram() {
		return program;
	}
	public void setProgram(String program) {
		this.program = program;
	}
	public String getSearchType() {
		return searchType;
	}
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getComponentName() {
		return componentName;
	}
	public void setComponentName(String componentName) {
		this.componentName = componentName;
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
	public Long getConvergenceTypeId() {
		return convergenceTypeId;
	}
	public void setConvergenceType(Long convergenceTypeId) {
		this.convergenceTypeId = convergenceTypeId;
	}
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getLocationIdStr() {
		return locationIdStr;
	}
	public void setLocationIdStr(String locationIdStr) {
		this.locationIdStr = locationIdStr;
	}
	public String getLocationType() {
		return locationType;
	}
	public void setLocationType(String locationType) {
		this.locationType = locationType;
	}
	public String getSubLocationType() {
		return subLocationType;
	}
	public void setSubLocationType(String subLocationType) {
		this.subLocationType = subLocationType;
	}
	public List<Long> getComponentIds() {
		return componentIds;
	}
	public void setComponentIds(List<Long> componentIds) {
		this.componentIds = componentIds;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
}
