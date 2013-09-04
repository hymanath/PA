/* 
 * Copyright (c) 2010 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on August 26,2010
 */
package com.itgrids.partyanalyst.dto;

import java.io.Serializable;

public class CadreBasicInformationVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long cadreId;
	private Long userId;
	private Long cadreLevelId;
	private Long cadreLevelTypeId;
	private String cadreLevelType;
	private Long cadreLocationId;
	private String cadreLocation;
	private String cadreType;
	private String searchType;
	private Boolean isSocialStatus;
	private String skillsSearchType;
	private Boolean isAndSearch;	//search based on AND/OR
	private Boolean isOrSearch;
	private String genderSearchType;
	private Long bloodGroupId;
	private String registerCadreSearchType;
	private Boolean isVoiceSms;
	
	//Getters and Setters
	
	public Long getBloodGroupId() {
		return bloodGroupId;
	}
	public Boolean getIsVoiceSms() {
		return isVoiceSms;
	}
	public void setIsVoiceSms(Boolean isVoiceSms) {
		this.isVoiceSms = isVoiceSms;
	}
	public void setBloodGroupId(Long bloodGroupId) {
		this.bloodGroupId = bloodGroupId;
	}
	public String getSkillsSearchType() {
		return skillsSearchType;
	}
	public void setSkillsSearchType(String skillsSearchType) {
		this.skillsSearchType = skillsSearchType;
	}
	public String getSearchType() {
		return searchType;
	}
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	public Boolean getIsSocialStatus() {
		return isSocialStatus;
	}
	public void setIsSocialStatus(Boolean isSocialStatus) {
		this.isSocialStatus = isSocialStatus;
	}
	
	public Long getCadreId() {
		return cadreId;
	}
	public void setCadreId(Long cadreId) {
		this.cadreId = cadreId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getCadreLevelId() {
		return cadreLevelId;
	}
	public void setCadreLevelId(Long cadreLevelId) {
		this.cadreLevelId = cadreLevelId;
	}
	public String getCadreLevelType() {
		return cadreLevelType;
	}
	public void setCadreLevelType(String cadreLevelType) {
		this.cadreLevelType = cadreLevelType;
	}
	public Long getCadreLocationId() {
		return cadreLocationId;
	}
	public void setCadreLocationId(Long cadreLocationId) {
		this.cadreLocationId = cadreLocationId;
	}
	public String getCadreLocation() {
		return cadreLocation;
	}
	public void setCadreLocation(String cadreLocation) {
		this.cadreLocation = cadreLocation;
	}
	public String getCadreType() {
		return cadreType;
	}
	public void setCadreType(String cadreType) {
		this.cadreType = cadreType;
	}
	public Long getCadreLevelTypeId() {
		return cadreLevelTypeId;
	}
	public void setCadreLevelTypeId(Long cadreLevelTypeId) {
		this.cadreLevelTypeId = cadreLevelTypeId;
	}
	public Boolean getIsAndSearch() {
		return isAndSearch;
	}
	public void setIsAndSearch(Boolean isAndSearch) {
		this.isAndSearch = isAndSearch;
	}
	public Boolean getIsOrSearch() {
		return isOrSearch;
	}
	public void setIsOrSearch(Boolean isOrSearch) {
		this.isOrSearch = isOrSearch;
	}
	public String getGenderSearchType() {
		return genderSearchType;
	}
	public void setGenderSearchType(String genderSearchType) {
		this.genderSearchType = genderSearchType;
	}
	public String getRegisterCadreSearchType() {
		return registerCadreSearchType;
	}
	public void setRegisterCadreSearchType(String registerCadreSearchType) {
		this.registerCadreSearchType = registerCadreSearchType;
	}
	

}
