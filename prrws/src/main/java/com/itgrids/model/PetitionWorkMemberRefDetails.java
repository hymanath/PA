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
@Table(name = "petition_work_member_ref_details")
public class PetitionWorkMemberRefDetails {

	private Long petitionWorkMemberRefDetails_id;
	private Long petitionWorkDetailsId;
	private Long  	 petitionRefCandidateId;
	private Long  	 petitionMemberId;
	private String  isDeleted;
	private Long  	insertedUserId;
	private Long  	updaetdUserId;
	private Date insertedTime;
	private Date updatedTime;
	
	private PetitionWorkDetails petitionWorkDetails;
	private PetitionReffererCandidate petitionReffererCandidate;
	private PetitionMember petitionMember;
	
	private User insertedUser;
	private User updatedUser;
	
	@Id
	@Column(name="petition_work_member_ref_details_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	public Long getPetitionWorkMemberRefDetails_id() {
		return petitionWorkMemberRefDetails_id;
	}
	public void setPetitionWorkMemberRefDetails_id(Long petitionWorkMemberRefDetails_id) {
		this.petitionWorkMemberRefDetails_id = petitionWorkMemberRefDetails_id;
	}
	@Column(name="petition_work_details_id")
	public Long getPetitionWorkDetailsId() {
		return petitionWorkDetailsId;
	}
	public void setPetitionWorkDetailsId(Long petitionWorkDetailsId) {
		this.petitionWorkDetailsId = petitionWorkDetailsId;
	}
	@Column(name="petition_ref_candidate_id")
	public Long getPetitionRefCandidateId() {
		return petitionRefCandidateId;
	}
	public void setPetitionRefCandidateId(Long petitionRefCandidateId) {
		this.petitionRefCandidateId = petitionRefCandidateId;
	}
	@Column(name="petition_member_id")
	public Long getPetitionMemberId() {
		return petitionMemberId;
	}
	public void setPetitionMemberId(Long petitionMemberId) {
		this.petitionMemberId = petitionMemberId;
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
	@Column(name="updaetd_user_id")
	public Long getUpdaetdUserId() {
		return updaetdUserId;
	}
	public void setUpdaetdUserId(Long updaetdUserId) {
		this.updaetdUserId = updaetdUserId;
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
	@JoinColumn(name = "petition_work_details_id", insertable = false, updatable = false)
	public PetitionWorkDetails getPetitionWorkDetails() {
		return petitionWorkDetails;
	}
	public void setPetitionWorkDetails(PetitionWorkDetails petitionWorkDetails) {
		this.petitionWorkDetails = petitionWorkDetails;
	}
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "petition_ref_candidate_id", insertable = false, updatable = false)
	public PetitionReffererCandidate getPetitionReffererCandidate() {
		return petitionReffererCandidate;
	}
	public void setPetitionReffererCandidate(PetitionReffererCandidate petitionReffererCandidate) {
		this.petitionReffererCandidate = petitionReffererCandidate;
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
	@JoinColumn(name = "inserted_user_id", insertable = false, updatable = false)
	public User getInsertedUser() {
		return insertedUser;
	}
	public void setInsertedUser(User insertedUser) {
		this.insertedUser = insertedUser;
	}
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "updaetd_user_id", insertable = false, updatable = false)
	public User getUpdatedUser() {
		return updatedUser;
	}
	public void setUpdatedUser(User updatedUser) {
		this.updatedUser = updatedUser;
	}
	
}
