package com.itgrids.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "petition_work_details")
public class PetitionWorkDetails {

	private Long petitionWorkDetailsId;
	private Long petitionMemberId ;
	private String workName;
	private Long noOfWorks;
	private Long costEstimation;
	private String subject;
	private Long petitionSubjectId;
	private Long petitionSubSubjectId;
	private Long petitionDepartmentId;
	private Long petitionLeadId;
	private Long petitionBriefLeadId;
	private Long petitionGrantId;
	private String isPreviousPetition;
	private String previousPetitionRefNo;
	private String projectDescription;
	private String remarks;
	private Long petitionStatusId;
	private String isDeleted;
	private Long insertedUserId;
	private Long updatedUserId;
	private Date insertedTime;
	private Date updatedTime;
	
	private PetitionStatus petitionStatus;
	private PetitionGrant petitionGrant;
	private PetitionSubject petitionSubject;
	private PetitionSubject petitionSubSubject;
	private PetitionMember petitionMember;
	private PetitionDepartment petitionDepartment;
	private User insertedUser;
	private User updatedUser;
	
	private PetitionLead petitionLead;
	private PetitionBriefLead petitionBriefLead;
	
	@Id
	@Column(name="petition_work_details_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	public Long getPetitionWorkDetailsId() {
		return petitionWorkDetailsId;
	}
	public void setPetitionWorkDetailsId(Long petitionWorkDetailsId) {
		this.petitionWorkDetailsId = petitionWorkDetailsId;
	}
	@Column(name="petition_member_id")
	public Long getPetitionMemberId() {
		return petitionMemberId;
	}
	public void setPetitionMemberId(Long petitionMemberId) {
		this.petitionMemberId = petitionMemberId;
	}
	@Column(name="no_of_works")
	public Long getNoOfWorks() {
		return noOfWorks;
	}
	public void setNoOfWorks(Long noOfWorks) {
		this.noOfWorks = noOfWorks;
	}
	@Column(name="subject")
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	@Column(name="petition_department_id")
	public Long getPetitionDepartmentId() {
		return petitionDepartmentId;
	}
	public void setPetitionDepartmentId(Long petitionDepartmentId) {
		this.petitionDepartmentId = petitionDepartmentId;
	}
	@Column(name="is_previous_petition")
	public String getIsPreviousPetition() {
		return isPreviousPetition;
	}
	public void setIsPreviousPetition(String isPreviousPetition) {
		this.isPreviousPetition = isPreviousPetition;
	}
	@Column(name="previous_petition_ref_no")
	public String getPreviousPetitionRefNo() {
		return previousPetitionRefNo;
	}
	public void setPreviousPetitionRefNo(String previousPetitionRefNo) {
		this.previousPetitionRefNo = previousPetitionRefNo;
	}
	@Column(name="project_description")
	public String getProjectDescription() {
		return projectDescription;
	}
	public void setProjectDescription(String projectDescription) {
		this.projectDescription = projectDescription;
	}
	@Column(name="is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	@Column(name="inserted_user_id")
	public Long getInsertedUserId() {
		return insertedUserId;
	}
	public void setInsertedUserId(Long insertedUserId) {
		this.insertedUserId = insertedUserId;
	}
	@Column(name="updated_user_id")
	public Long getUpdatedUserId() {
		return updatedUserId;
	}
	public void setUpdatedUserId(Long updatedUserId) {
		this.updatedUserId = updatedUserId;
	}
	@Column(name="inserted_time")
	public Date getInsertedTime() {
		return insertedTime;
	}
	public void setInsertedTime(Date insertedTime) {
		this.insertedTime = insertedTime;
	}
	@Column(name="updated_time")
	public Date getUpdatedTime() {
		return updatedTime;
	}
	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "petition_member_id", insertable = false, updatable = false)
	public PetitionMember getPetitionMember() {
		return petitionMember;
	}
	public void setPetitionMember(PetitionMember petitionMember) {
		this.petitionMember = petitionMember;
	}
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "petition_department_id", insertable = false, updatable = false)
	public PetitionDepartment getPetitionDepartment() {
		return petitionDepartment;
	}
	public void setPetitionDepartment(PetitionDepartment petitionDepartment) {
		this.petitionDepartment = petitionDepartment;
	}

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "inserted_user_id", insertable = false, updatable = false)
	public User getInsertedUser() {
		return insertedUser;
	}
	public void setInsertedUser(User insertedUser) {
		this.insertedUser = insertedUser;
	}
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "updated_user_id", insertable = false, updatable = false)
	public User getUpdatedUser() {
		return updatedUser;
	}
	public void setUpdatedUser(User updatedUser) {
		this.updatedUser = updatedUser;
	}
	
	@Column(name="work_name")
	public String getWorkName() {
		return workName;
	}
	public void setWorkName(String workName) {
		this.workName = workName;
	}
	
	@Column(name="petition_subject_id")
	public Long getPetitionSubjectId() {
		return petitionSubjectId;
	}
	public void setPetitionSubjectId(Long petitionSubjectId) {
		this.petitionSubjectId = petitionSubjectId;
	}
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "petition_subject_id", insertable = false, updatable = false)
	public PetitionSubject getPetitionSubject() {
		return petitionSubject;
	}
	public void setPetitionSubject(PetitionSubject petitionSubject) {
		this.petitionSubject = petitionSubject;
	}
	
	@Column(name="cost_estimation")
	public Long getCostEstimation() {
		return costEstimation;
	}
	public void setCostEstimation(Long costEstimation) {
		this.costEstimation = costEstimation;
	}
	
	@Column(name="petition_sub_subject_id")
	public Long getPetitionSubSubjectId() {
		return petitionSubSubjectId;
	}
	public void setPetitionSubSubjectId(Long petitionSubSubjectId) {
		this.petitionSubSubjectId = petitionSubSubjectId;
	}
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "petition_sub_subject_id", insertable = false, updatable = false)
	public PetitionSubject getPetitionSubSubject() {
		return petitionSubSubject;
	}
	public void setPetitionSubSubject(PetitionSubject petitionSubSubject) {
		this.petitionSubSubject = petitionSubSubject;
	}
	
	@Column(name="petition_lead_id")
	public Long getPetitionLeadId() {
		return petitionLeadId;
	}
	public void setPetitionLeadId(Long petitionLeadId) {
		this.petitionLeadId = petitionLeadId;
	}
	
	@Column(name="petition_brief_lead_id")
	public Long getPetitionBriefLeadId() {
		return petitionBriefLeadId;
	}
	public void setPetitionBriefLeadId(Long petitionBriefLeadId) {
		this.petitionBriefLeadId = petitionBriefLeadId;
	}
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "petition_lead_id", insertable = false, updatable = false)
	public PetitionLead getPetitionLead() {
		return petitionLead;
	}
	public void setPetitionLead(PetitionLead petitionLead) {
		this.petitionLead = petitionLead;
	}
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "petition_brief_lead_id", insertable = false, updatable = false)
	public PetitionBriefLead getPetitionBriefLead() {
		return petitionBriefLead;
	}
	public void setPetitionBriefLead(PetitionBriefLead petitionBriefLead) {
		this.petitionBriefLead = petitionBriefLead;
	}
	
	
	@Column(name="petition_grant_id")
	public Long getPetitionGrantId() {
		return petitionGrantId;
	}
	public void setPetitionGrantId(Long petitionGrantId) {
		this.petitionGrantId = petitionGrantId;
	}
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "petition_grant_id", insertable = false, updatable = false)
	public PetitionGrant getPetitionGrant() {
		return petitionGrant;
	}
	public void setPetitionGrant(PetitionGrant petitionGrant) {
		this.petitionGrant = petitionGrant;
	}
	
	@Column(name="remarks")
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "petition_status_id", insertable = false, updatable = false)
	public PetitionStatus getPetitionStatus() {
		return petitionStatus;
	}
	public void setPetitionStatus(PetitionStatus petitionStatus) {
		this.petitionStatus = petitionStatus;
	}
	
	@Column(name="petition_status_id")
	public Long getPetitionStatusId() {
		return petitionStatusId;
	}
	public void setPetitionStatusId(Long petitionStatusId) {
		this.petitionStatusId = petitionStatusId;
	}
}
