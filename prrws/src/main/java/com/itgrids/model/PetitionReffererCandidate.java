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
@Table(name = "petition_refferer_candidate")
public class PetitionReffererCandidate {

	private Long petitionReffererCandidateId;
	private String name;
	private String mobileNo;
	private String designation;
	private String emailId;
	private Long addressId;
	private String isDeleted;
	private Long insertedUserId;
	private Long updatedUserId;
	private Date insertedTime;
	private Date updatedTime;
	
	private User insetrUser;
	private User updateUser;
	private LocationAddress locationAddress;
	
	
	@Id
	@Column(name="petition_refferer_candidate_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	public Long getPetitionReffererCandidateId() {
		return petitionReffererCandidateId;
	}
	public void setPetitionReffererCandidateId(Long petitionReffererCandidateId) {
		this.petitionReffererCandidateId = petitionReffererCandidateId;
	}
	@Column(name="name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name="mobile_no")
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	@Column(name="designation")
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	@Column(name="email_id")
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
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
	

	
}
