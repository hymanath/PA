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
@Table(name = "petition_refferer")
public class PetitionRefferer {

	private Long petitionReffererId;
	private Long petitionReffererCandidateId ;
	private Long petitionMemberId;
	private String isDeleted;
	private Long insertedUserId;
	private Long updatedUserId;
	private Date insertedTime;
	private Date updatedTime;
	
	private PetitionReffererCandidate petitionReffererCandidate;
	private PetitionMember petitionMember;
	private User insertedUser;
	private User updatedUser;
	
	@Id
	@Column(name="petition_refferer_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	public Long getPetitionReffererId() {
		return petitionReffererId;
	}
	public void setPetitionReffererId(Long petitionReffererId) {
		this.petitionReffererId = petitionReffererId;
	}
	@Column(name="petition_ref_candidate_id")
	public Long getPetitionReffererCandidateId() {
		return petitionReffererCandidateId;
	}
	public void setPetitionReffererCandidateId(Long petitionReffererCandidateId) {
		this.petitionReffererCandidateId = petitionReffererCandidateId;
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
	@JoinColumn(name = "updated_user_id", insertable = false, updatable = false)
	public User getUpdatedUser() {
		return updatedUser;
	}
	public void setUpdatedUser(User updatedUser) {
		this.updatedUser = updatedUser;
	}
	
		
}
