package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.Date;

import com.itgrids.partyanalyst.model.BaseModel;

public class ActionableVO extends BaseModel implements Serializable{

	private Long id;
	private String name;
	private String desc;
	private Long regionScopeId;
	private Long regionScopeValue;	
	private String sourceTypeId;//manual,printMedia,electronicMedia
	
	private Long statusId;	
	private Long alertCategory;
	private Long alertType;
	private Long userId;
	
	private String type;
	private String imageUrl;
	
	private Long stateId;
	private Long districtId;
	private Long constituencyId;
	private Long parliamentId;
	private Long mandalId;
	private Long panchayatId;
	private Long villageId;
	private Long municipalCorpGmcId;
	private Long wardId;
	private Long impactScopeId;
	
	private Date insertedTime;
	private Date updatedTime;
	
	
	public Date getInsertedTime() {
		return insertedTime;
	}
	public void setInsertedTime(Date insertedTime) {
		this.insertedTime = insertedTime;
	}
	public Date getUpdatedTime() {
		return updatedTime;
	}
	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
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
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public Long getRegionScopeId() {
		return regionScopeId;
	}
	public void setRegionScopeId(Long regionScopeId) {
		this.regionScopeId = regionScopeId;
	}
	public Long getRegionScopeValue() {
		return regionScopeValue;
	}
	public void setRegionScopeValue(Long regionScopeValue) {
		this.regionScopeValue = regionScopeValue;
	}
	public String getSourceTypeId() {
		return sourceTypeId;
	}
	public void setSourceTypeId(String sourceTypeId) {
		this.sourceTypeId = sourceTypeId;
	}
	public Long getStatusId() {
		return statusId;
	}
	public void setStatusId(Long statusId) {
		this.statusId = statusId;
	}
	public Long getAlertCategory() {
		return alertCategory;
	}
	public void setAlertCategory(Long alertCategory) {
		this.alertCategory = alertCategory;
	}
	public Long getAlertType() {
		return alertType;
	}
	public void setAlertType(Long alertType) {
		this.alertType = alertType;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
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
	public Long getParliamentId() {
		return parliamentId;
	}
	public void setParliamentId(Long parliamentId) {
		this.parliamentId = parliamentId;
	}
	public Long getMandalId() {
		return mandalId;
	}
	public void setMandalId(Long mandalId) {
		this.mandalId = mandalId;
	}
	public Long getPanchayatId() {
		return panchayatId;
	}
	public void setPanchayatId(Long panchayatId) {
		this.panchayatId = panchayatId;
	}
	public Long getVillageId() {
		return villageId;
	}
	public void setVillageId(Long villageId) {
		this.villageId = villageId;
	}
	public Long getMunicipalCorpGmcId() {
		return municipalCorpGmcId;
	}
	public void setMunicipalCorpGmcId(Long municipalCorpGmcId) {
		this.municipalCorpGmcId = municipalCorpGmcId;
	}
	public Long getWardId() {
		return wardId;
	}
	public void setWardId(Long wardId) {
		this.wardId = wardId;
	}
	public Long getImpactScopeId() {
		return impactScopeId;
	}
	public void setImpactScopeId(Long impactScopeId) {
		this.impactScopeId = impactScopeId;
	}
	
	
	
}
