package com.itgrids.partyanalyst.dto;

import java.io.Serializable;

public class PartyMeetingsDataVO implements Serializable{
	
	private Long   id;
	private String name;
	private String locationLevelName;
	private Long   totalCount = 0l;
	private Long   conductedCount=0l;
	
	private Double conductedPerc = 0.0;
	private String requiredName;
	
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
	public String getLocationLevelName() {
		return locationLevelName;
	}
	public void setLocationLevelName(String locationLevelName) {
		this.locationLevelName = locationLevelName;
	}
	public Long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	public Long getConductedCount() {
		return conductedCount;
	}
	public void setConductedCount(Long conductedCount) {
		this.conductedCount = conductedCount;
	}
	public Double getConductedPerc() {
		return conductedPerc;
	}
	public void setConductedPerc(Double conductedPerc) {
		this.conductedPerc = conductedPerc;
	}
	public String getRequiredName() {
		return requiredName;
	}
	public void setRequiredName(String requiredName) {
		this.requiredName = requiredName;
	}
	
}
