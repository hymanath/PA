package com.itgrids.partyanalyst.dto;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ActivityVO implements Serializable{
	
	private Long activityTypeId;
	private Long activityLevelId;
	private Long activityNameId;
	private String plannedDate;
	private String ConductedDate;
	private Long locationLevelId;
	private Long constituencyId;
	private Long mandalId;
	private Long localElectionBodyId;
	private Long villageId;
	private Long wardId;
	private Long attendedCount;
	private String remarks;
	private List<File> imageForDisplayList = new ArrayList<File>(0); 
	private List<String> imageForDisplayContentTypeList =new ArrayList<String>(0);
	private List<String> imageForDisplayFileNameList = new ArrayList<String>(0);
	private String startDate;
	private String endDate;
	
	
	public Long getActivityTypeId() {
		return activityTypeId;
	}
	public void setActivityTypeId(Long activityTypeId) {
		this.activityTypeId = activityTypeId;
	}
	public Long getActivityLevelId() {
		return activityLevelId;
	}
	public void setActivityLevelId(Long activityLevelId) {
		this.activityLevelId = activityLevelId;
	}
	public Long getActivityNameId() {
		return activityNameId;
	}
	public void setActivityNameId(Long activityNameId) {
		this.activityNameId = activityNameId;
	}
	public String getPlannedDate() {
		return plannedDate;
	}
	public void setPlannedDate(String plannedDate) {
		this.plannedDate = plannedDate;
	}
	public String getConductedDate() {
		return ConductedDate;
	}
	public void setConductedDate(String conductedDate) {
		ConductedDate = conductedDate;
	}
	public Long getLocationLevelId() {
		return locationLevelId;
	}
	public void setLocationLevelId(Long locationLevelId) {
		this.locationLevelId = locationLevelId;
	}
	public Long getConstituencyId() {
		return constituencyId;
	}
	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}
	public Long getMandalId() {
		return mandalId;
	}
	public void setMandalId(Long mandalId) {
		this.mandalId = mandalId;
	}
	public Long getLocalElectionBodyId() {
		return localElectionBodyId;
	}
	public void setLocalElectionBodyId(Long localElectionBodyId) {
		this.localElectionBodyId = localElectionBodyId;
	}
	public Long getVillageId() {
		return villageId;
	}
	public void setVillageId(Long villageId) {
		this.villageId = villageId;
	}
	public Long getWardId() {
		return wardId;
	}
	public void setWardId(Long wardId) {
		this.wardId = wardId;
	}
	public Long getAttendedCount() {
		return attendedCount;
	}
	public void setAttendedCount(Long attendedCount) {
		this.attendedCount = attendedCount;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public List<File> getImageForDisplayList() {
		return imageForDisplayList;
	}
	public void setImageForDisplayList(List<File> imageForDisplayList) {
		this.imageForDisplayList = imageForDisplayList;
	}
	public List<String> getImageForDisplayContentTypeList() {
		return imageForDisplayContentTypeList;
	}
	public void setImageForDisplayContentTypeList(
			List<String> imageForDisplayContentTypeList) {
		this.imageForDisplayContentTypeList = imageForDisplayContentTypeList;
	}
	public List<String> getImageForDisplayFileNameList() {
		return imageForDisplayFileNameList;
	}
	public void setImageForDisplayFileNameList(
			List<String> imageForDisplayFileNameList) {
		this.imageForDisplayFileNameList = imageForDisplayFileNameList;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
}
