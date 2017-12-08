package com.itgrids.model;

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
@Table(name = "petition_ref_candidate")
public class PmRefCandidate {
	
	
	private Long petitionRefCandidateId;
	private String relativeName;
	private String mobileNo;
	private String email;
	private Long tdpCadreId;
	private Long addressId;
	private String partyName;
	private String imagePath;
	private String  isDeleted;
	private Long candidateId;
	private Long partyId;
	
	
	
	private LocationAddress address;
	
	@Id
	@Column(name="petition_ref_candidate_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	public Long getPetitionRefCandidateId() {
		return petitionRefCandidateId;
	}
	public void setPetitionRefCandidateId(Long petitionRefCandidateId) {
		this.petitionRefCandidateId = petitionRefCandidateId;
	}
	@Column(name="relative_name")
	public String getRelativeName() {
		return relativeName;
	}
	public void setRelativeName(String relativeName) {
		this.relativeName = relativeName;
	}
	@Column(name="mobile_no")
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	@Column(name="email")
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Column(name="tdp_cadre_id")
	public Long getTdpCadreId() {
		return tdpCadreId;
	}
	public void setTdpCadreId(Long tdpCadreId) {
		this.tdpCadreId = tdpCadreId;
	}
	@Column(name="address_id")
	public Long getAddressId() {
		return addressId;
	}
	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}
	@Column(name="party_name")
	public String getPartyName() {
		return partyName;
	}
	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}
	@Column(name="image_path")
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	@Column(name="is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	@Column(name="candidate_id")
	public Long getCandidateId() {
		return candidateId;
	}
	public void setCandidateId(Long candidateId) {
		this.candidateId = candidateId;
	}
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "location_address_id", insertable = false, updatable = false)
	public LocationAddress getAddress() {
		return address;
	}
	public void setAddress(LocationAddress address) {
		this.address = address;
	}
	@Column(name="party_id")
	public Long getPartyId() {
		return partyId;
	}
	public void setPartyId(Long partyId) {
		this.partyId = partyId;
	}	
}
