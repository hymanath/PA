package com.itgrids.dto;

import java.util.ArrayList;
import java.util.List;

public class KPIVO {
	private String locationId="";
	private String locationName="";
	private Long pcTarget=0l,pcAchivement=0l;
	private Long qaTarget=0l,qaAchivement=0l;
	private Double pcPercentage=0.00,qaPercentage=0.00;
	private String parentLocationId;
	
	
	public String getParentLocationId() {
		return parentLocationId;
	}
	public void setParentLocationId(String parentLocationId) {
		this.parentLocationId = parentLocationId;
	}
	public Double getPcPercentage() {
		return pcPercentage;
	}
	public void setPcPercentage(Double pcPercentage) {
		this.pcPercentage = pcPercentage;
	}
	public Double getQaPercentage() {
		return qaPercentage;
	}
	public void setQaPercentage(Double qaPercentage) {
		this.qaPercentage = qaPercentage;
	}
	public Long getPcTarget() {
		return pcTarget;
	}
	public void setPcTarget(Long pcTarget) {
		this.pcTarget = pcTarget;
	}
	public Long getPcAchivement() {
		return pcAchivement;
	}
	public void setPcAchivement(Long pcAchivement) {
		this.pcAchivement = pcAchivement;
	}
	public Long getQaTarget() {
		return qaTarget;
	}
	public void setQaTarget(Long qaTarget) {
		this.qaTarget = qaTarget;
	}
	public Long getQaAchivement() {
		return qaAchivement;
	}
	public void setQaAchivement(Long qaAchivement) {
		this.qaAchivement = qaAchivement;
	}
	public String getLocationId() {
		return locationId;
	}
	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}
	public String getLocationName() {
		return locationName;
	}
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
}
