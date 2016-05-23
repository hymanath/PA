package com.itgrids.partyanalyst.dto;

import java.io.Serializable;

public class BloodBankVO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String name;
	private String sex;
	private Long age;
	private String dob;
	private String married;
	private String mobile;
	private String email;
	private String education;
	private String occupation;
	private String address;
	private Long educationId;
	private Long occupationId;
	private Long donationsInBloodBank;
	private String donationsInOtherPlaces;
	private String donationDate;
	private String lastDonation;
	private Long bloodComponentId;
	private String bloodComponent;
	private String willingEmergencyDonation;
	private String willingToCallDonation;
	private String remarks;
	private Long donorAge;
	private String membershipNo;
	private Long bloodDonationCampId;
	private String bagNo;
	private Long quantity;
	private boolean alreadyDonated;
	private Long statusId;
	private String status;
	private Long bagTypeId;
	private String bagType;
	private Long bloodBankQuantityId;
	private String quantityType;
	private String registrationNo;
	
	private AddressVO addressVO=new AddressVO();
	
	public AddressVO getAddressVO() {
		return addressVO;
	}
	public void setAddressVO(AddressVO addressVO) {
		this.addressVO = addressVO;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	private String relativeName;
	public String getRelativeName() {
		return relativeName;
	}
	public void setRelativeName(String relativeName) {
		this.relativeName = relativeName;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Long getAge() {
		return age;
	}
	public void setAge(Long age) {
		this.age = age;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getMarried() {
		return married;
	}
	public void setMarried(String married) {
		this.married = married;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEducation() {
		return education;
	}
	public void setEducation(String education) {
		this.education = education;
	}
	public String getOccupation() {
		return occupation;
	}
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}	
	public Long getEducationId() {
		return educationId;
	}
	public void setEducationId(Long educationId) {
		this.educationId = educationId;
	}
	public Long getOccupationId() {
		return occupationId;
	}
	public void setOccupationId(Long occupationId) {
		this.occupationId = occupationId;
	}
	public Long getDonationsInBloodBank() {
		return donationsInBloodBank;
	}
	public void setDonationsInBloodBank(Long donationsInBloodBank) {
		this.donationsInBloodBank = donationsInBloodBank;
	}
	public String getDonationsInOtherPlaces() {
		return donationsInOtherPlaces;
	}
	public void setDonationsInOtherPlaces(String donationsInOtherPlaces) {
		this.donationsInOtherPlaces = donationsInOtherPlaces;
	}
	public String getDonationDate() {
		return donationDate;
	}
	public void setDonationDate(String donationDate) {
		this.donationDate = donationDate;
	}
	public String getLastDonation() {
		return lastDonation;
	}
	public void setLastDonation(String lastDonation) {
		this.lastDonation = lastDonation;
	}
	public Long getBloodComponentId() {
		return bloodComponentId;
	}
	public void setBloodComponentId(Long bloodComponentId) {
		this.bloodComponentId = bloodComponentId;
	}
	public String getBloodComponent() {
		return bloodComponent;
	}
	public void setBloodComponent(String bloodComponent) {
		this.bloodComponent = bloodComponent;
	}
	public String getWillingEmergencyDonation() {
		return willingEmergencyDonation;
	}
	public void setWillingEmergencyDonation(String willingEmergencyDonation) {
		this.willingEmergencyDonation = willingEmergencyDonation;
	}
	public String getWillingToCallDonation() {
		return willingToCallDonation;
	}
	public void setWillingToCallDonation(String willingToCallDonation) {
		this.willingToCallDonation = willingToCallDonation;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public Long getDonorAge() {
		return donorAge;
	}
	public void setDonorAge(Long donorAge) {
		this.donorAge = donorAge;
	}
	public String getMembershipNo() {
		return membershipNo;
	}
	public void setMembershipNo(String membershipNo) {
		this.membershipNo = membershipNo;
	}
	public Long getBloodDonationCampId() {
		return bloodDonationCampId;
	}
	public void setBloodDonationCampId(Long bloodDonationCampId) {
		this.bloodDonationCampId = bloodDonationCampId;
	}
	public String getBagNo() {
		return bagNo;
	}
	public void setBagNo(String bagNo) {
		this.bagNo = bagNo;
	}
	public Long getQuantity() {
		return quantity;
	}
	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}
	public boolean isAlreadyDonated() {
		return alreadyDonated;
	}
	public void setAlreadyDonated(boolean alreadyDonated) {
		this.alreadyDonated = alreadyDonated;
	}
	public Long getStatusId() {
		return statusId;
	}
	public void setStatusId(Long statusId) {
		this.statusId = statusId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Long getBagTypeId() {
		return bagTypeId;
	}
	public void setBagTypeId(Long bagTypeId) {
		this.bagTypeId = bagTypeId;
	}
	public String getBagType() {
		return bagType;
	}
	public void setBagType(String bagType) {
		this.bagType = bagType;
	}
	public Long getBloodBankQuantityId() {
		return bloodBankQuantityId;
	}
	public void setBloodBankQuantityId(Long bloodBankQuantityId) {
		this.bloodBankQuantityId = bloodBankQuantityId;
	}
	public String getQuantityType() {
		return quantityType;
	}
	public void setQuantityType(String quantityType) {
		this.quantityType = quantityType;
	}
	public String getRegistrationNo() {
		return registrationNo;
	}
	public void setRegistrationNo(String registrationNo) {
		this.registrationNo = registrationNo;
	}
	
	
}
