package com.itgrids.partyanalyst.dto;

import java.io.File;
import java.io.Serializable;
import java.util.List;

public class EventFileUploadVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long userId;
	private Long stateId;
	private Long districtId;
	private Long constituencyId;
	private Long mandalOrMuncipalityId;
	private Long panchayatId;
	private Long levelId;
	private Long levelValue;
	private String activityDate;
	private String fileExtension;
	private Long updatedBy;
	private File file;
	private Long day;
	private Long activityScopeId;
	private Long locationScopeId;
	private Long laocationValueAddress;
	
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	public Long getStateId() {
		return stateId;
	}
	public void setStateId(Long stateId) {
		this.stateId = stateId;
	}
	public Long getDistrictId() {
		return districtId;
	}
	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}
	public Long getConstituencyId() {
		return constituencyId;
	}
	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}
	public Long getMandalOrMuncipalityId() {
		return mandalOrMuncipalityId;
	}
	public void setMandalOrMuncipalityId(Long mandalOrMuncipalityId) {
		this.mandalOrMuncipalityId = mandalOrMuncipalityId;
	}
	public Long getPanchayatId() {
		return panchayatId;
	}
	public void setPanchayatId(Long panchayatId) {
		this.panchayatId = panchayatId;
	}
	public Long getLevelId() {
		return levelId;
	}
	public void setLevelId(Long levelId) {
		this.levelId = levelId;
	}
	public Long getLevelValue() {
		return levelValue;
	}
	public void setLevelValue(Long levelValue) {
		this.levelValue = levelValue;
	}
	public String getActivityDate() {
		return activityDate;
	}
	public void setActivityDate(String activityDate) {
		this.activityDate = activityDate;
	}
	public String getFileExtension() {
		return fileExtension;
	}
	public void setFileExtension(String fileExtension) {
		this.fileExtension = fileExtension;
	}
	public Long getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
	}
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	public Long getDay() {
		return day;
	}
	public void setDay(Long day) {
		this.day = day;
	}
	public Long getActivityScopeId() {
		return activityScopeId;
	}
	public void setActivityScopeId(Long activityScopeId) {
		this.activityScopeId = activityScopeId;
	}
	public Long getLocationScopeId() {
		return locationScopeId;
	}
	public void setLocationScopeId(Long locationScopeId) {
		this.locationScopeId = locationScopeId;
	}
	public Long getLaocationValueAddress() {
		return laocationValueAddress;
	}
	public void setLaocationValueAddress(Long laocationValueAddress) {
		this.laocationValueAddress = laocationValueAddress;
	}
	
	
	
}
