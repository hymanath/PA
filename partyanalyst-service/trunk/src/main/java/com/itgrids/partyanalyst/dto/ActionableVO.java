package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
	
	private Long candidateId;
	private Long benefitId;
	
	private String title;
	
	private List<ActionableVO> actionableVoList;
	
	private Long newsCandidateId;
	private String newsCandidate;
	private String organization;
	private Set<String> designationList = new HashSet<String>(0);
	private Long editionTypeId;
	private Long tvNewsChannelId;
	private Long editionId;
	private Long newsOrganizationId;
	private String isDepartment;
	
	private Set<String> categoryList = new HashSet<String>(0);
	private Long newsUserId ;
	
	
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
	public Long getCandidateId() {
		return candidateId;
	}
	public void setCandidateId(Long candidateId) {
		this.candidateId = candidateId;
	}
	public Long getBenefitId() {
		return benefitId;
	}
	public void setBenefitId(Long benefitId) {
		this.benefitId = benefitId;
	}
	public List<ActionableVO> getActionableVoList() {
		return actionableVoList;
	}
	public void setActionableVoList(List<ActionableVO> actionableVoList) {
		this.actionableVoList = actionableVoList;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Set<String> getDesignationList() {
		return designationList;
	}
	public void setDesignationList(Set<String> designationList) {
		this.designationList = designationList;
	}
	public Long getNewsCandidateId() {
		return newsCandidateId;
	}
	public void setNewsCandidateId(Long newsCandidateId) {
		this.newsCandidateId = newsCandidateId;
	}
	public String getNewsCandidate() {
		return newsCandidate;
	}
	public void setNewsCandidate(String newsCandidate) {
		this.newsCandidate = newsCandidate;
	}
	public String getOrganization() {
		return organization;
	}
	public void setOrganization(String organization) {
		this.organization = organization;
	}
	public Long getEditionTypeId() {
		return editionTypeId;
	}
	public void setEditionTypeId(Long editionTypeId) {
		this.editionTypeId = editionTypeId;
	}
	public Long getTvNewsChannelId() {
		return tvNewsChannelId;
	}
	public void setTvNewsChannelId(Long tvNewsChannelId) {
		this.tvNewsChannelId = tvNewsChannelId;
	}
	public Long getEditionId() {
		return editionId;
	}
	public void setEditionId(Long editionId) {
		this.editionId = editionId;
	}
	public Long getNewsOrganizationId() {
		return newsOrganizationId;
	}
	public void setNewsOrganizationId(Long newsOrganizationId) {
		this.newsOrganizationId = newsOrganizationId;
	}
	public String getIsDepartment() {
		return isDepartment;
	}
	public void setIsDepartment(String isDepartment) {
		this.isDepartment = isDepartment;
	}
	public Set<String> getCategoryList() {
		return categoryList;
	}
	public void setCategoryList(Set<String> categoryList) {
		this.categoryList = categoryList;
	}
	public Long getNewsUserId() {
		return newsUserId;
	}
	public void setNewsUserId(Long newsUserId) {
		this.newsUserId = newsUserId;
	}
	
	
	
	
}
