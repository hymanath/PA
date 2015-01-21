package com.itgrids.partyanalyst.dto;

import java.io.Serializable;

import java.util.Date;

public class CadrePreviousRollesVO implements Serializable {

	
	private static final long 	serialVersionUID = 6426809253013344836L;
	private Long 				designationLevelId;
	private Long 				designationLevelValue;
	private Date 				fromDate;
	private Date 				toDate;
	
	private String 				fromDateStr;
	private String 				toDateStr;
	
	private String 				electionTypeId;
	private String 				year;
	private String 				constituencyId;
	private Long 				electionYear;
	
	
	private Long 				cadreCommitteeLevelId;
	private Long 				cadreCommitteeId;
	private Long 				cadreRoleId;
	
	private String				candidateId;
	private Long 				cadreCommitteeLevelValue;
	private Long 				cadreCommitteeTypeId;
	private Long 				committeeMngtType;
	
	
	public Long getCommitteeMngtType() {
		return committeeMngtType;
	}
	public void setCommitteeMngtType(Long committeeMngtType) {
		this.committeeMngtType = committeeMngtType;
	}
	public Long getCadreCommitteeTypeId() {
		return cadreCommitteeTypeId;
	}
	public void setCadreCommitteeTypeId(Long cadreCommitteeTypeId) {
		this.cadreCommitteeTypeId = cadreCommitteeTypeId;
	}
	public Long getCadreCommitteeLevelValue() {
		return cadreCommitteeLevelValue;
	}
	public void setCadreCommitteeLevelValue(Long cadreCommitteeLevelValue) {
		this.cadreCommitteeLevelValue = cadreCommitteeLevelValue;
	}
	public Long getDesignationLevelId() {
		return designationLevelId;
	}
	public void setDesignationLevelId(Long designationLevelId) {
		this.designationLevelId = designationLevelId;
	}
	public Long getDesignationLevelValue() {
		return designationLevelValue;
	}
	public void setDesignationLevelValue(Long designationLevelValue) {
		this.designationLevelValue = designationLevelValue;
	}
	public Date getFromDate() {
		return fromDate;
	}
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}
	public Date getToDate() {
		return toDate;
	}
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}
	public String getElectionTypeId() {
		return electionTypeId;
	}
	public void setElectionTypeId(String electionTypeId) {
		this.electionTypeId = electionTypeId;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getConstituencyId() {
		return constituencyId;
	}
	public void setConstituencyId(String constituencyId) {
		this.constituencyId = constituencyId;
	}
	public String getFromDateStr() {
		return fromDateStr;
	}
	public void setFromDateStr(String fromDateStr) {
		this.fromDateStr = fromDateStr;
	}
	public String getToDateStr() {
		return toDateStr;
	}
	public void setToDateStr(String toDateStr) {
		this.toDateStr = toDateStr;
	}
	public Long getElectionYear() {
		return electionYear;
	}
	public void setElectionYear(Long electionYear) {
		this.electionYear = electionYear;
	}
	
	
	
	public Long getCadreCommitteeLevelId() {
		return cadreCommitteeLevelId;
	}
	public void setCadreCommitteeLevelId(Long cadreCommitteeLevelId) {
		this.cadreCommitteeLevelId = cadreCommitteeLevelId;
	}
	public Long getCadreCommitteeId() {
		return cadreCommitteeId;
	}
	public void setCadreCommitteeId(Long cadreCommitteeId) {
		this.cadreCommitteeId = cadreCommitteeId;
	}
	public Long getCadreRoleId() {
		return cadreRoleId;
	}
	public void setCadreRoleId(Long cadreRoleId) {
		this.cadreRoleId = cadreRoleId;
	}
	public String getCandidateId() {
		return candidateId;
	}
	public void setCandidateId(String candidateId) {
		this.candidateId = candidateId;
	}
	
	
	
	
	
	
}
