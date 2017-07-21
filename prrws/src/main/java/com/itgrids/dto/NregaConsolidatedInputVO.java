package com.itgrids.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class NregaConsolidatedInputVO implements Serializable{

	private Long id;
	private String name;
	private Long convergenceTypeId;
	
	private String fromDate;
	private String toDate;
	private String year;
	private String locationId;
	private String locationType;
	private String subLocationType;
	private List<Long> componentIds = new ArrayList<Long>();
	
	
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
	public String getLocationId() {
		return locationId;
	}
	public void setLocationId(String locationId) {
		this.locationId = locationId;
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
}
