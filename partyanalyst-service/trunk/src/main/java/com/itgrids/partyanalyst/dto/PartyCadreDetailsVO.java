/* 
 * Copyright (c) 2010 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on August 26,2010
 */
package com.itgrids.partyanalyst.dto;

import java.io.Serializable;

public class PartyCadreDetailsVO extends CadreBasicInformationVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long partyId;
	private String partyName;
	private String cadreDOB;
	private Long cadreAge;
	
	private CadreCategoryVO cadreCasteCategory;
	private CadreCategoryVO cadreOccupation;
	private CadreCategoryVO cadreSkillSet;
	private CadreCategoryVO cadreTrainingCamps;
	private CadreCategoryVO cadreWorkingCommittee;
	private CadreCategoryVO cadreKnownLanguage;
	private CadreCategoryVO cadreCommitteeDesignation;
	private CadreCategoryVO cadreEducationQualification;
	
	//Getters and Setters
	public Long getPartyId() {
		return partyId;
	}
	public void setPartyId(Long partyId) {
		this.partyId = partyId;
	}
	public String getPartyName() {
		return partyName;
	}
	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}
	public String getCadreDOB() {
		return cadreDOB;
	}
	public void setCadreDOB(String cadreDOB) {
		this.cadreDOB = cadreDOB;
	}
	public Long getCadreAge() {
		return cadreAge;
	}
	public void setCadreAge(Long cadreAge) {
		this.cadreAge = cadreAge;
	}
	public CadreCategoryVO getCadreCasteCategory() {
		return cadreCasteCategory;
	}
	public void setCadreCasteCategory(CadreCategoryVO cadreCasteCategory) {
		this.cadreCasteCategory = cadreCasteCategory;
	}
	public CadreCategoryVO getCadreOccupation() {
		return cadreOccupation;
	}
	public void setCadreOccupation(CadreCategoryVO cadreOccupation) {
		this.cadreOccupation = cadreOccupation;
	}
	public CadreCategoryVO getCadreSkillSet() {
		return cadreSkillSet;
	}
	public void setCadreSkillSet(CadreCategoryVO cadreSkillSet) {
		this.cadreSkillSet = cadreSkillSet;
	}
	public CadreCategoryVO getCadreTrainingCamps() {
		return cadreTrainingCamps;
	}
	public void setCadreTrainingCamps(CadreCategoryVO cadreTrainingCamps) {
		this.cadreTrainingCamps = cadreTrainingCamps;
	}
	public CadreCategoryVO getCadreWorkingCommittee() {
		return cadreWorkingCommittee;
	}
	public void setCadreWorkingCommittee(CadreCategoryVO cadreWorkingCommittee) {
		this.cadreWorkingCommittee = cadreWorkingCommittee;
	}
	public CadreCategoryVO getCadreKnownLanguage() {
		return cadreKnownLanguage;
	}
	public void setCadreKnownLanguage(CadreCategoryVO cadreKnownLanguage) {
		this.cadreKnownLanguage = cadreKnownLanguage;
	}
	public CadreCategoryVO getCadreCommitteeDesignation() {
		return cadreCommitteeDesignation;
	}
	public void setCadreCommitteeDesignation(
			CadreCategoryVO cadreCommitteeDesignation) {
		this.cadreCommitteeDesignation = cadreCommitteeDesignation;
	}
	public CadreCategoryVO getCadreEducationQualification() {
		return cadreEducationQualification;
	}
	public void setCadreEducationQualification(
			CadreCategoryVO cadreEducationQualification) {
		this.cadreEducationQualification = cadreEducationQualification;
	}
	

}
