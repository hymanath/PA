/* 
 * Copyright (c) 2010 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on August 26,2010
 */
package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.List;

public class PartyCadreDetailsVO extends CadreBasicInformationVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long partyId;
	private String partyName;
	private String cadreDOB;
	private Long cadreAge;
	private String cadreName;
	private String taskName;
	private String radioButtonValue;
	private String memberShipNo;
	
	private List<CadreCategoryVO> cadreCasteCategory;
	private List<CadreCategoryVO> cadreOccupation;
	private List<CadreCategoryVO> cadreSkillSet;
	private List<CadreCategoryVO> cadreTrainingCamps;
	private List<CadreCategoryVO> cadreWorkingCommittee;
	private List<CadreCategoryVO> cadreKnownLanguage;
	private List<CadreCategoryVO> cadreCommitteeDesignation;
	private List<CadreCategoryVO> cadreEducationQualification;
	
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
	public List<CadreCategoryVO> getCadreCasteCategory() {
		return cadreCasteCategory;
	}
	public void setCadreCasteCategory(List<CadreCategoryVO> cadreCasteCategory) {
		this.cadreCasteCategory = cadreCasteCategory;
	}
	public List<CadreCategoryVO> getCadreOccupation() {
		return cadreOccupation;
	}
	public void setCadreOccupation(List<CadreCategoryVO> cadreOccupation) {
		this.cadreOccupation = cadreOccupation;
	}
	public List<CadreCategoryVO> getCadreSkillSet() {
		return cadreSkillSet;
	}
	public void setCadreSkillSet(List<CadreCategoryVO> cadreSkillSet) {
		this.cadreSkillSet = cadreSkillSet;
	}
	public List<CadreCategoryVO> getCadreTrainingCamps() {
		return cadreTrainingCamps;
	}
	public void setCadreTrainingCamps(List<CadreCategoryVO> cadreTrainingCamps) {
		this.cadreTrainingCamps = cadreTrainingCamps;
	}
	public List<CadreCategoryVO> getCadreWorkingCommittee() {
		return cadreWorkingCommittee;
	}
	public void setCadreWorkingCommittee(List<CadreCategoryVO> cadreWorkingCommittee) {
		this.cadreWorkingCommittee = cadreWorkingCommittee;
	}
	public List<CadreCategoryVO> getCadreKnownLanguage() {
		return cadreKnownLanguage;
	}
	public void setCadreKnownLanguage(List<CadreCategoryVO> cadreKnownLanguage) {
		this.cadreKnownLanguage = cadreKnownLanguage;
	}
	public List<CadreCategoryVO> getCadreCommitteeDesignation() {
		return cadreCommitteeDesignation;
	}
	public void setCadreCommitteeDesignation(
			List<CadreCategoryVO> cadreCommitteeDesignation) {
		this.cadreCommitteeDesignation = cadreCommitteeDesignation;
	}
	public List<CadreCategoryVO> getCadreEducationQualification() {
		return cadreEducationQualification;
	}
	public void setCadreEducationQualification(
			List<CadreCategoryVO> cadreEducationQualification) {
		this.cadreEducationQualification = cadreEducationQualification;
	}
	public String getCadreName() {
		return cadreName;
	}
	public void setCadreName(String cadreName) {
		this.cadreName = cadreName;
	}
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public String getRadioButtonValue() {
		return radioButtonValue;
	}
	public void setRadioButtonValue(String radioButtonValue) {
		this.radioButtonValue = radioButtonValue;
	}
	
	public String getMemberShipNo() {
		return memberShipNo;
	}
	public void setMemberShipNo(String memberShipNo) {
		this.memberShipNo = memberShipNo;
	}
	
	
	

}
