package com.itgrids.partyanalyst.dto;

import java.util.ArrayList;
import java.util.List;

public class LocationInputVO {
	private Long id;
	private Long name;
	private Long count;
	private Long totalCount;
	private Long designationId;
	private Long levelId;
	private String levelStr;
	
	private String designation;
	private Long committeeId;
	
	private List<Long> localStateIds;
	private List<Long> localDistrictIds;
	private List<Long> localConstituencyIds;
	private List<Long> localMandalIds;
	private List<Long> localPanchayatIds;
	
	private List<Long> counrtyIds = new ArrayList<Long>(0);
	private List<Long> stateIdsList;	
	private List<Long> districtIdsList ;	
	private List<Long> tehsilIdsList;
	private List<Long> townIdsList;
	private List<Long> divisionIdsList;	
	private List<Long> villageIdsList;
	private List<Long> wardIdsList;
	private List<Long> designationIds;
	private Long stateId;
	private Long aptUserId;
	private List<Long> constituencyIds;
	
	
	
	
	public List<Long> getConstituencyIds() {
		return constituencyIds;
	}
	public void setConstituencyIds(List<Long> constituencyIds) {
		this.constituencyIds = constituencyIds;
	}
	public Long getAptUserId() {
		return aptUserId;
	}
	public void setAptUserId(Long aptUserId) {
		this.aptUserId = aptUserId;
	}
	public Long getStateId() {
		return stateId;
	}
	public void setStateId(Long stateId) {
		this.stateId = stateId;
	}
	public List<Long> getDesignationIds() {
		return designationIds;
	}
	public void setDesignationIds(List<Long> designationIds) {
		this.designationIds = designationIds;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getName() {
		return name;
	}
	public void setName(Long name) {
		this.name = name;
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	public Long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	public Long getDesignationId() {
		return designationId;
	}
	public void setDesignationId(Long designationId) {
		this.designationId = designationId;
	}
	public Long getLevelId() {
		return levelId;
	}
	public void setLevelId(Long levelId) {
		this.levelId = levelId;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public List<Long> getStateIdsList() {
		return stateIdsList;
	}
	public void setStateIdsList(List<Long> stateIdsList) {
		this.stateIdsList = stateIdsList;
	}
	public List<Long> getDistrictIdsList() {
		return districtIdsList;
	}
	public void setDistrictIdsList(List<Long> districtIdsList) {
		this.districtIdsList = districtIdsList;
	}
	public List<Long> getTehsilIdsList() {
		return tehsilIdsList;
	}
	public void setTehsilIdsList(List<Long> tehsilIdsList) {
		this.tehsilIdsList = tehsilIdsList;
	}
	public List<Long> getTownIdsList() {
		return townIdsList;
	}
	public void setTownIdsList(List<Long> townIdsList) {
		this.townIdsList = townIdsList;
	}
	public List<Long> getDivisionIdsList() {
		return divisionIdsList;
	}
	public void setDivisionIdsList(List<Long> divisionIdsList) {
		this.divisionIdsList = divisionIdsList;
	}
	public List<Long> getVillageIdsList() {
		return villageIdsList;
	}
	public void setVillageIdsList(List<Long> villageIdsList) {
		this.villageIdsList = villageIdsList;
	}
	public List<Long> getWardIdsList() {
		return wardIdsList;
	}
	public void setWardIdsList(List<Long> wardIdsList) {
		this.wardIdsList = wardIdsList;
	}
	public List<Long> getLocalStateIds() {
		return localStateIds;
	}
	public void setLocalStateIds(List<Long> localStateIds) {
		this.localStateIds = localStateIds;
	}
	public List<Long> getLocalDistrictIds() {
		return localDistrictIds;
	}
	public void setLocalDistrictIds(List<Long> localDistrictIds) {
		this.localDistrictIds = localDistrictIds;
	}
	public List<Long> getLocalConstituencyIds() {
		return localConstituencyIds;
	}
	public void setLocalConstituencyIds(List<Long> localConstituencyIds) {
		this.localConstituencyIds = localConstituencyIds;
	}
	public List<Long> getLocalMandalIds() {
		return localMandalIds;
	}
	public void setLocalMandalIds(List<Long> localMandalIds) {
		this.localMandalIds = localMandalIds;
	}
	public List<Long> getLocalPanchayatIds() {
		return localPanchayatIds;
	}
	public void setLocalPanchayatIds(List<Long> localPanchayatIds) {
		this.localPanchayatIds = localPanchayatIds;
	}
	public String getLevelStr() {
		return levelStr;
	}
	public void setLevelStr(String levelStr) {
		this.levelStr = levelStr;
	}
	public Long getCommitteeId() {
		return committeeId;
	}
	public void setCommitteeId(Long committeeId) {
		this.committeeId = committeeId;
	}
	public List<Long> getCounrtyIds() {
		return counrtyIds;
	}
	public void setCounrtyIds(List<Long> counrtyIds) {
		this.counrtyIds = counrtyIds;
	}

	
}
