package com.itgrids.partyanalyst.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

public class LocationVO implements Serializable {

	private Long id;
	private String name;
	private Long districtId;
	private String districtName;
	private Long constituencyId;
	private String constituencyName;
	private Long tehsilId;
	private String tehsilName;
	private Long villageId;
	private String villageName;
	private Long parliamentConstituencyId;
	private Long localBodyId;
	private String localEleBodyName;
	private Long stateId;
	private String state;
	private Long wardId;
	private String wardName;
	private String locationType;
	private String fromDate;
	private String toDate;
	private String status;
	private Long statusId;
	
	private Long categoryId;
	private String category;
	private Long actionTypeStatusId;
	private String task;
	private String fromDate2;
	private String toDate2;
	private Long hamletId;
	private String hamletName;
	private String searchType;
	private Long  constituencyCount;
	private Long   tehsilCount;
	private Long  villageIdCount;
	private Long  hamletCount;	
	private Long municipalityCount;
	private Long boothCount;
	private Long totalNoOfWards;
	
	public Long getBoothCount() {
		return boothCount;
	}
	public void setBoothCount(Long boothCount) {
		this.boothCount = boothCount;
	}
	public String getTask() {
		return task;
	}
	public void setTask(String task) {
		this.task = task;
	}
	public Long getActionTypeStatusId() {
		return actionTypeStatusId;
	}
	public void setActionTypeStatusId(Long actionTypeStatusId) {
		this.actionTypeStatusId = actionTypeStatusId;
	}
	public Long getStatusId() {
		return statusId;
	}
	public void setStatusId(Long statusId) {
		this.statusId = statusId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	
	public String getLocationType() {
		return locationType;
	}
	public void setLocationType(String locationType) {
		this.locationType = locationType;
	}
	public Long getWardId() {
		return wardId;
	}
	public void setWardId(Long wardId) {
		this.wardId = wardId;
	}
	public String getWardName() {
		return wardName;
	}
	public void setWardName(String wardName) {
		this.wardName = wardName;
	}
	public Long getStateId() {
		return stateId;
	}
	public void setStateId(Long stateId) {
		this.stateId = stateId;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Long getLocalBodyId() {
		return localBodyId;
	}
	public void setLocalBodyId(Long localBodyId) {
		this.localBodyId = localBodyId;
	}
	public String getLocalEleBodyName() {
		return localEleBodyName;
	}
	public void setLocalEleBodyName(String localEleBodyName) {
		this.localEleBodyName = localEleBodyName;
	}
	public Long getDistrictId() {
		return districtId;
	}
	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}
	public String getDistrictName() {
		return districtName;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	public Long getConstituencyId() {
		return constituencyId;
	}
	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}
	public String getConstituencyName() {
		return constituencyName;
	}
	public void setConstituencyName(String constituencyName) {
		this.constituencyName = constituencyName;
	}
	public Long getTehsilId() {
		return tehsilId;
	}
	public void setTehsilId(Long tehsilId) {
		this.tehsilId = tehsilId;
	}
	public String getTehsilName() {
		return tehsilName;
	}
	public void setTehsilName(String tehsilName) {
		this.tehsilName = tehsilName;
	}
	public Long getVillageId() {
		return villageId;
	}
	public void setVillageId(Long villageId) {
		this.villageId = villageId;
	}
	public String getVillageName() {
		return villageName;
	}
	public void setVillageName(String villageName) {
		this.villageName = villageName;
	}
	public Long getParliamentConstituencyId() {
		return parliamentConstituencyId;
	}
	public void setParliamentConstituencyId(Long parliamentConstituencyId) {
		this.parliamentConstituencyId = parliamentConstituencyId;
	}
	public Long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getFromDate2() {
		return fromDate2;
	}
	public void setFromDate2(String fromDate2) {
		this.fromDate2 = fromDate2;
	}
	public String getToDate2() {
		return toDate2;
	}
	public void setToDate2(String toDate2) {
		this.toDate2 = toDate2;
	}
	public Long getHamletId() {
		return hamletId;
	}
	public void setHamletId(Long hamletId) {
		this.hamletId = hamletId;
	}
	public String getHamletName() {
		return hamletName;
	}
	public void setHamletName(String hamletName) {
		this.hamletName = hamletName;
	}
	public String getSearchType() {
		return searchType;
	}
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	public Long getConstituencyCount() {
		return constituencyCount;
	}
	public void setConstituencyCount(Long constituencyCount) {
		this.constituencyCount = constituencyCount;
	}
	public Long getTehsilCount() {
		return tehsilCount;
	}
	public void setTehsilCount(Long tehsilCount) {
		this.tehsilCount = tehsilCount;
	}
	public Long getVillageIdCount() {
		return villageIdCount;
	}
	public void setVillageIdCount(Long villageIdCount) {
		this.villageIdCount = villageIdCount;
	}
	public Long getHamletCount() {
		return hamletCount;
	}
	public void setHamletCount(Long hamletCount) {
		this.hamletCount = hamletCount;
	}
	public Long getMunicipalityCount() {
		return municipalityCount;
	}
	public void setMunicipalityCount(Long municipalityCount) {
		this.municipalityCount = municipalityCount;
	}
	public Long getTotalNoOfWards() {
		return totalNoOfWards;
	}
	public void setTotalNoOfWards(Long totalNoOfWards) {
		this.totalNoOfWards = totalNoOfWards;
	}
	
	
	
}
