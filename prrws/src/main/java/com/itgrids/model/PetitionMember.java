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

/**
 * @author sys
 *
 */
@Entity
@Table(name = "petition_member")
public class PetitionMember {

	private Long petitionMemberId;
	private String refCode;
	private Date representationDate;
	private Date endorsmentDate;
	private String candidateName;
	private String relativeName;
	private Long age;
	private Date dateOfBirth;//mobile_no
	private String mobileNo;
	private String emailId;
	//private Long voterId;
	private String voterCardNo;
	private Long addressId;
	private String isDeleted;
	private String isExpired;
	private Long insertedUserId;
	private Long updatedUserId;
	private Date insertedTime;
	private Date updatedTime;
	private String memberType;
	private Long petitionReffererCandidateId;
	private Long petitionDesignationId;
	
	private PetitionReffererCandidate petitionReffererCandidate;
	private User insetrUser;
	private User updateUser;
	
	private PetitionDesignation petitionDesignation;
	private LocationAddress locationAddress;
	
	@Id
	@Column(name="petition_member_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	public Long getPetitionMemberId() {
		return petitionMemberId;
	}
	public void setPetitionMemberId(Long petitionMemberId) {
		this.petitionMemberId = petitionMemberId;
	}
	@Column(name="ref_code")
	public String getRefCode() {
		return refCode;
	}
	public void setRefCode(String refCode) {
		this.refCode = refCode;
	}
	@Column(name="representation_date")
	public Date getRepresentationDate() {
		return representationDate;
	}
	public void setRepresentationDate(Date representationDate) {
		this.representationDate = representationDate;
	}
	@Column(name="endorsment_date")
	public Date getEndorsmentDate() {
		return endorsmentDate;
	}
	public void setEndorsmentDate(Date endorsmentDate) {
		this.endorsmentDate = endorsmentDate;
	}
	@Column(name="candidate_name")
	public String getCandidateName() {
		return candidateName;
	}
	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
	}
	@Column(name="relative_name")
	public String getRelativeName() {
		return relativeName;
	}
	public void setRelativeName(String relativeName) {
		this.relativeName = relativeName;
	}
	@Column(name="age")
	public Long getAge() {
		return age;
	}
	public void setAge(Long age) {
		this.age = age;
	}
	@Column(name="date_of_birth")
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	@Column(name="mobile_no")
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	@Column(name="email_id")
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	
	/*@Column(name="voter_id")
	public Long getVoterId() {
		return voterId;
	}
	public void setVoterId(Long voterId) {
		this.voterId = voterId;
	}*/
	
	@Column(name="address_id")
	public Long getAddressId() {
		return addressId;
	}
	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}
	@Column(name="is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	@Column(name="is_expired")	
	public String getIsExpired() {
		return isExpired;
	}
	public void setIsExpired(String isExpired) {
		this.isExpired = isExpired;
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
	@JoinColumn(name = "address_id", insertable = false, updatable = false)
	public LocationAddress getLocationAddress() {
		return locationAddress;
	}
	public void setLocationAddress(LocationAddress locationAddress) {
		this.locationAddress = locationAddress;
	}
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "inserted_user_id", insertable = false, updatable = false)
	public User getInsetrUser() {
		return insetrUser;
	}
	public void setInsetrUser(User insetrUser) {
		this.insetrUser = insetrUser;
	}
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "updated_user_id", insertable = false, updatable = false)
	public User getUpdateUser() {
		return updateUser;
	}
	public void setUpdateUser(User updateUser) {
		this.updateUser = updateUser;
	}
	
	
	@Column(name="voter_id_card_no")
	public String getVoterCardNo() {
		return voterCardNo;
	}
	public void setVoterCardNo(String voterCardNo) {
		this.voterCardNo = voterCardNo;
	}
	
	@Column(name="member_type")
	public String getMemberType() {
		return memberType;
	}
	public void setMemberType(String memberType) {
		this.memberType = memberType;
	}
	
	
	@Column(name="petition_ref_candidate_id")
	public Long getPetitionReffererCandidateId() {
		return petitionReffererCandidateId;
	}
	public void setPetitionReffererCandidateId(Long petitionReffererCandidateId) {
		this.petitionReffererCandidateId = petitionReffererCandidateId;
	}
	
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "petition_ref_candidate_id", insertable = false, updatable = false)
	public PetitionReffererCandidate getPetitionReffererCandidate() {
		return petitionReffererCandidate;
	}
	public void setPetitionReffererCandidate(
			PetitionReffererCandidate petitionReffererCandidate) {
		this.petitionReffererCandidate = petitionReffererCandidate;
	}
	
	@Column(name="petition_designation_id")
	public Long getPetitionDesignationId() {
		return petitionDesignationId;
	}
	public void setPetitionDesignationId(Long petitionDesignationId) {
		this.petitionDesignationId = petitionDesignationId;
	}
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "petition_designation_id", insertable = false, updatable = false)
	public PetitionDesignation getPetitionDesignation() {
		return petitionDesignation;
	}
	public void setPetitionDesignation(PetitionDesignation petitionDesignation) {
		this.petitionDesignation = petitionDesignation;
	}
	
}
