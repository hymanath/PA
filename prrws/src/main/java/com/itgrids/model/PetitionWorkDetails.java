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
	private String subject;
	private Long petitionDepartmentId;
	private String isPreviousPetition;
	private String previousPetitionRefNo;
	private String projectDescription;
	private String isDeleted;
	private Long insertedUserId;
	private Long updatedUserId;
	private Date insertedTime;
	private Date updatedTime;
	
	private PetitionMember petitionMember;
	private PetitionDepartment petitionDepartment;
	private User insertedUser;
	private User updatedUser;
	
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
	
	
}
