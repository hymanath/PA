package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.List;

public class VoterDataVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long constituencyId;
	private Long PanchayatId;
	private Long mandaId;
	private Long hamletId;
	private Long boothId;
	private Long publicationId;
	private Long startIndex;
	private Long maxIndex;
	private String dir;
	private String buildType;
	private String sort;
	private String task;
	private Boolean partyPresent = false;
	private Boolean castePresent = false;
	private List<VoterDataVO> categoriesList;
	private Long CategoryValuesId;
	private String name;
	private Long id;
	private Long categoryId;
	
	private Long customVoterGroupId;
	private String gender;
	private Long casteId;
	private String locationId;
	
	
	public String getLocationId() {
		return locationId;
	}
	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Long getCasteId() {
		return casteId;
	}
	public void setCasteId(Long casteId) {
		this.casteId = casteId;
	}
	public Long getCustomVoterGroupId() {
		return customVoterGroupId;
	}
	public void setCustomVoterGroupId(Long customVoterGroupId) {
		this.customVoterGroupId = customVoterGroupId;
	}
	public Long getConstituencyId() {
		return constituencyId;
	}
	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}
	public Long getPanchayatId() {
		return PanchayatId;
	}
	public void setPanchayatId(Long panchayatId) {
		PanchayatId = panchayatId;
	}
	public Long getMandaId() {
		return mandaId;
	}
	public void setMandaId(Long mandaId) {
		this.mandaId = mandaId;
	}
	public Long getHamletId() {
		return hamletId;
	}
	public void setHamletId(Long hamletId) {
		this.hamletId = hamletId;
	}
	public Long getBoothId() {
		return boothId;
	}
	public void setBoothId(Long boothId) {
		this.boothId = boothId;
	}
	public Long getPublicationId() {
		return publicationId;
	}
	public void setPublicationId(Long publicationId) {
		this.publicationId = publicationId;
	}
	public Long getStartIndex() {
		return startIndex;
	}
	public void setStartIndex(Long startIndex) {
		this.startIndex = startIndex;
	}
	public Long getMaxIndex() {
		return maxIndex;
	}
	public void setMaxIndex(Long maxIndex) {
		this.maxIndex = maxIndex;
	}
	public String getDir() {
		return dir;
	}
	public void setDir(String dir) {
		this.dir = dir;
	}
	public String getBuildType() {
		return buildType;
	}
	public void setBuildType(String buildType) {
		this.buildType = buildType;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getTask() {
		return task;
	}
	public void setTask(String task) {
		this.task = task;
	}
	public Boolean getPartyPresent() {
		return partyPresent;
	}
	public void setPartyPresent(Boolean partyPresent) {
		this.partyPresent = partyPresent;
	}
	public Boolean getCastePresent() {
		return castePresent;
	}
	public void setCastePresent(Boolean castePresent) {
		this.castePresent = castePresent;
	}
	public List<VoterDataVO> getCategoriesList() {
		return categoriesList;
	}
	public void setCategoriesList(List<VoterDataVO> categoriesList) {
		this.categoriesList = categoriesList;
	}
	public Long getCategoryValuesId() {
		return CategoryValuesId;
	}
	public void setCategoryValuesId(Long categoryValuesId) {
		CategoryValuesId = categoryValuesId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	
	
	
	
	
	
}
