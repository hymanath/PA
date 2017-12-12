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
@Table(name = "pm_representee")
public class PmRepresentee {

	
	private Long pmRepresenteeId;
	private String name;
	private String mobileNo;
	private String email;
	private Long addressId;
	private Long tdpCadreId;
	private String voterCardNo;
	private String adharCardNo;
	private String isDeleted;
	private Long pmRefCandidateId;
	
	private PmRefCandidate pmRefCandidate;
	private LocationAddress userAddress;
	
	private User insertedUser;
	private User updatedUser;
	private Date insertedTime;
	private Date updatedTime;
	
	@Id
	@Column(name="pm_representee_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	public Long getPmRepresenteeId() {
		return pmRepresenteeId;
	}
	public void setPmRepresenteeId(Long pmRepresenteeId) {
		this.pmRepresenteeId = pmRepresenteeId;
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
	@Column(name="email_id")
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Column(name="address_id")
	public Long getAddressId() {
		return addressId;
	}
	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}
	@Column(name="tdp_cadre_id")
	public Long getTdpCadreId() {
		return tdpCadreId;
	}
	public void setTdpCadreId(Long tdpCadreId) {
		this.tdpCadreId = tdpCadreId;
	}
	@Column(name="voter_card_no")
	public String getVoterCardNo() {
		return voterCardNo;
	}
	public void setVoterCardNo(String voterCardNo) {
		this.voterCardNo = voterCardNo;
	}
	@Column(name="is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "address_id", insertable = false, updatable = false)
	public LocationAddress getUserAddress() {
		return userAddress;
	}
	public void setUserAddress(LocationAddress userAddress) {
		this.userAddress = userAddress;
	}
	
	
	@Column(name="adhar_card_no")
	public String getAdharCardNo() {
		return adharCardNo;
	}
	public void setAdharCardNo(String adharCardNo) {
		this.adharCardNo = adharCardNo;
	}
	
	@Column(name="pm_ref_candidate_id")
	public Long getPmRefCandidateId() {
		return pmRefCandidateId;
	}
	public void setPmRefCandidateId(Long pmRefCandidateId) {
		this.pmRefCandidateId = pmRefCandidateId;
	}
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "pm_ref_candidate_id", insertable = false, updatable = false)
	public PmRefCandidate getPmRefCandidate() {
		return pmRefCandidate;
	}
	public void setPmRefCandidate(PmRefCandidate pmRefCandidate) {
		this.pmRefCandidate = pmRefCandidate;
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
	
	
}
