package com.itgrids.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GovtMainWorkVO implements Serializable{
	
	private Long govtMainWorkId;
	private String govtMainWork;
	private Long worksCount=0l;
	private Long completedWorksCount=0l;
	private Double totalKms;
	private Double progressKms;
	private Double completedKms=0.00;
	private Double completedPercentage;
	private Long locationScopeId;
	private Long locationValue;
	private Long locationAddressId;
	private Long totalCount;
	private Long workPraposedAreas=0l;
	private Long workZonesCount;
	private Long totalWorks;
	private Double estimationCost;
	
	public Long getGovtMainWorkId() {
		return govtMainWorkId;
	}
	public void setGovtMainWorkId(Long govtMainWorkId) {
		this.govtMainWorkId = govtMainWorkId;
	}
	public String getGovtMainWork() {
		return govtMainWork;
	}
	public void setGovtMainWork(String govtMainWork) {
		this.govtMainWork = govtMainWork;
	}
	public Long getWorksCount() {
		return worksCount;
	}
	public void setWorksCount(Long worksCount) {
		this.worksCount = worksCount;
	}
	public Double getTotalKms() {
		return totalKms;
	}
	public void setTotalKms(Double totalKms) {
		this.totalKms = totalKms;
	}
	public Double getProgressKms() {
		return progressKms;
	}
	public void setProgressKms(Double progressKms) {
		this.progressKms = progressKms;
	}
	public Double getCompletedPercentage() {
		return completedPercentage;
	}
	public void setCompletedPercentage(Double completedPercentage) {
		this.completedPercentage = completedPercentage;
	}
	public Long getLocationScopeId() {
		return locationScopeId;
	}
	public void setLocationScopeId(Long locationScopeId) {
		this.locationScopeId = locationScopeId;
	}
	public Long getLocationValue() {
		return locationValue;
	}
	public void setLocationValue(Long locationValue) {
		this.locationValue = locationValue;
	}
	public Long getLocationAddressId() {
		return locationAddressId;
	}
	public void setLocationAddressId(Long locationAddressId) {
		this.locationAddressId = locationAddressId;
	}
	public Double getCompletedKms() {
		return completedKms;
	}
	public void setCompletedKms(Double completedKms) {
		this.completedKms = completedKms;
	}
	public Long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	public Long getCompletedWorksCount() {
		return completedWorksCount;
	}
	public void setCompletedWorksCount(Long completedWorksCount) {
		this.completedWorksCount = completedWorksCount;
	}
	public Long getWorkPraposedAreas() {
		return workPraposedAreas;
	}
	public void setWorkPraposedAreas(Long workPraposedAreas) {
		this.workPraposedAreas = workPraposedAreas;
	}
	public Long getWorkZonesCount() {
		return workZonesCount;
	}
	public void setWorkZonesCount(Long workZonesCount) {
		this.workZonesCount = workZonesCount;
	}
	public Long getTotalWorks() {
		return totalWorks;
	}
	public void setTotalWorks(Long totalWorks) {
		this.totalWorks = totalWorks;
	}
	public Double getEstimationCost() {
		return estimationCost;
	}
	public void setEstimationCost(Double estimationCost) {
		this.estimationCost = estimationCost;
	}

	
}
