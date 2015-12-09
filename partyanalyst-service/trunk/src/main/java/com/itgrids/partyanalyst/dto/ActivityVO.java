package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ActivityVO implements Serializable{
	
	private Long activityTypeId;
	private Long activityLevelId;
	private Long activityNameId;
	private String plannedDate;
	private String ConductedDate;
	private Long locationLevel;
	private Long locationValue;
	private Long constituencyId;
	private Long mandalId;
	private Long localElectionBodyId;
	private Long villageId;
	private Long wardId;
	private Long attendedCount;
	private String remarks;
	private String startDate;
	private String endDate;
	private List<ActivityVO> activityVoList = new ArrayList<ActivityVO>();
	private Map<String,ActivityVO>  activityMap = new LinkedHashMap<String,ActivityVO>(0);
	private Long id;
	private String name;
	private Long totalCount;
	private Long notPlannedCount;
	private Long plannedCount;
	private Long conductedCount;
	private Long nonConductedCount;
	private String percentage;
	private String notPlannedPerc;
	
	
	public String getNotPlannedPerc() {
		return notPlannedPerc;
	}
	public void setNotPlannedPerc(String notPlannedPerc) {
		this.notPlannedPerc = notPlannedPerc;
	}
	public Map<String, ActivityVO> getActivityMap() {
		return activityMap;
	}
	public void setActivityMap(Map<String, ActivityVO> activityMap) {
		this.activityMap = activityMap;
	}
	
	public Long getNotPlannedCount() {
		return notPlannedCount;
	}
	public void setNotPlannedCount(Long notPlannedCount) {
		this.notPlannedCount = notPlannedCount;
	}
	public String getPercentage() {
		return percentage;
	}
	public void setPercentage(String percentage) {
		this.percentage = percentage;
	}
	public List<ActivityVO> getActivityVoList() {
		return activityVoList;
	}
	public void setActivityVoList(List<ActivityVO> activityVoList) {
		this.activityVoList = activityVoList;
	}
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
	public Long getLocationLevel() {
		return locationLevel;
	}
	public void setLocationLevel(Long locationLevel) {
		this.locationLevel = locationLevel;
	}
	public Long getLocationValue() {
		return locationValue;
	}
	public void setLocationValue(Long locationValue) {
		this.locationValue = locationValue;
	}
	public Long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	public Long getPlannedCount() {
		return plannedCount;
	}
	public void setPlannedCount(Long plannedCount) {
		this.plannedCount = plannedCount;
	}
	public Long getConductedCount() {
		return conductedCount;
	}
	public void setConductedCount(Long conductedCount) {
		this.conductedCount = conductedCount;
	}
	public Long getNonConductedCount() {
		return nonConductedCount;
	}
	public void setNonConductedCount(Long nonConductedCount) {
		this.nonConductedCount = nonConductedCount;
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
	
}
