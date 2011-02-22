/* 
 * Copyright (c) 2011 IT Grids India LTD.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on February, 2011
 */
package com.itgrids.partyanalyst.dto;

import java.io.Serializable;

/**
 * @author Sai Krishna
 *
 */
public class ProblemStatusDataVO extends ResultStatus implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long problemId;
	private String problem;
	private String problemDesc;
	private String problemStatus;
	private String updatedDate;
	private String department;
	private String departmentOrganisation;
	private String deptLocation;
	private String officialName;
	private String official;
	private String phoneNo;
	private String cadre;
	private String comments;
	private String postedBy;
	private String postedByName;
	private String postedDate;
	private String existingFrom;
	private String problemLocation;
	private String probClassification;
	private long diffDays;
	
	private String activityHapened;
	
	
	public String getProblemStatus() {
		return problemStatus;
	}
	public void setProblemStatus(String problemStatus) {
		this.problemStatus = problemStatus;
	}
	public String getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getDepartmentOrganisation() {
		return departmentOrganisation;
	}
	public void setDepartmentOrganisation(String departmentOrganisation) {
		this.departmentOrganisation = departmentOrganisation;
	}
	public String getDeptLocation() {
		return deptLocation;
	}
	public void setDeptLocation(String deptLocation) {
		this.deptLocation = deptLocation;
	}
	public String getOfficialName() {
		return officialName;
	}
	public void setOfficialName(String officialName) {
		this.officialName = officialName;
	}
	public String getOfficial() {
		return official;
	}
	public void setOfficial(String official) {
		this.official = official;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getCadre() {
		return cadre;
	}
	public void setCadre(String cadre) {
		this.cadre = cadre;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getProblem() {
		return problem;
	}
	public void setProblem(String problem) {
		this.problem = problem;
	}
	public String getProblemDesc() {
		return problemDesc;
	}
	public void setProblemDesc(String problemDesc) {
		this.problemDesc = problemDesc;
	}
	public String getPostedBy() {
		return postedBy;
	}
	public void setPostedBy(String postedBy) {
		this.postedBy = postedBy;
	}
	public String getPostedDate() {
		return postedDate;
	}
	public void setPostedDate(String postedDate) {
		this.postedDate = postedDate;
	}
	public String getExistingFrom() {
		return existingFrom;
	}
	public void setExistingFrom(String existingFrom) {
		this.existingFrom = existingFrom;
	}
	public String getProblemLocation() {
		return problemLocation;
	}
	public void setProblemLocation(String problemLocation) {
		this.problemLocation = problemLocation;
	}
	public Long getProblemId() {
		return problemId;
	}
	public void setProblemId(Long problemId) {
		this.problemId = problemId;
	}
	public String getActivityHapened() {
		return activityHapened;
	}
	public void setActivityHapened(String activityHapened) {
		this.activityHapened = activityHapened;
	}
	public String getProbClassification() {
		return probClassification;
	}
	public void setProbClassification(String probClassification) {
		this.probClassification = probClassification;
	}
	public String getPostedByName() {
		return postedByName;
	}
	public void setPostedByName(String postedByName) {
		this.postedByName = postedByName;
	}
	public long getDiffDays() {
		return diffDays;
	}
	public void setDiffDays(long diffDays) {
		this.diffDays = diffDays;
	}
	
	
}
