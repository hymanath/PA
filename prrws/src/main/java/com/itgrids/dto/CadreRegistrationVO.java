package com.itgrids.dto;

import java.util.ArrayList;
import java.util.List;

public class CadreRegistrationVO {

	private Long id;
	private String name;
	
	private Long tdpCadreId;
	private String lastName;
	private String gender;
	private Long age;
	private String nameType;
	private String memberTypeId;
	private String dobStr;
	private String imageBase64String;
	private String mobileNumber;
	private String email;
	private String candidateAadherNo;
	private Long casteId;
	private Long educationId;
	private Long occupationId;
	private String nomineeName;
	private Long nomineeAge;
	private String nomineeGender;
	private String relativeType;
	private String voterCardNo;
	private Long voterRelationId;
	private Long familyVoterId;
	private String voterCardNumber;
	private List<CadreFamilyVO> cadreFamilyDetails = new ArrayList<CadreFamilyVO>();;
	private String constituencyId;
	private Long nomineeRelationId;
	private String imagePath;
	private String paymentStatus;
	private String membershipNo;
	private String photoType;
	private Long userAddressId;
	private Long  localElectionBodyId;
	
	private String houseNo;
	private Long stateId;
	private Long districtId;
	private Long mandalId;
	private Long villageId;
	private Long pincode;
	private PaymentGatewayVO paymentGatewayVO = new PaymentGatewayVO();
	private Long statusCode;
	
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
	public Long getTdpCadreId() {
		return tdpCadreId;
	}
	public void setTdpCadreId(Long tdpCadreId) {
		this.tdpCadreId = tdpCadreId;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Long getAge() {
		return age;
	}
	public void setAge(Long age) {
		this.age = age;
	}
	public String getNameType() {
		return nameType;
	}
	public void setNameType(String nameType) {
		this.nameType = nameType;
	}
	public String getMemberTypeId() {
		return memberTypeId;
	}
	public void setMemberTypeId(String memberTypeId) {
		this.memberTypeId = memberTypeId;
	}
	public String getDobStr() {
		return dobStr;
	}
	public void setDobStr(String dobStr) {
		this.dobStr = dobStr;
	}
	public String getImageBase64String() {
		return imageBase64String;
	}
	public void setImageBase64String(String imageBase64String) {
		this.imageBase64String = imageBase64String;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCandidateAadherNo() {
		return candidateAadherNo;
	}
	public void setCandidateAadherNo(String candidateAadherNo) {
		this.candidateAadherNo = candidateAadherNo;
	}
	public Long getCasteId() {
		return casteId;
	}
	public void setCasteId(Long casteId) {
		this.casteId = casteId;
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
	public String getNomineeName() {
		return nomineeName;
	}
	public void setNomineeName(String nomineeName) {
		this.nomineeName = nomineeName;
	}
	public Long getNomineeAge() {
		return nomineeAge;
	}
	public void setNomineeAge(Long nomineeAge) {
		this.nomineeAge = nomineeAge;
	}
	public String getNomineeGender() {
		return nomineeGender;
	}
	public void setNomineeGender(String nomineeGender) {
		this.nomineeGender = nomineeGender;
	}
	public String getRelativeType() {
		return relativeType;
	}
	public void setRelativeType(String relativeType) {
		this.relativeType = relativeType;
	}
	public String getVoterCardNo() {
		return voterCardNo;
	}
	public void setVoterCardNo(String voterCardNo) {
		this.voterCardNo = voterCardNo;
	}
	public Long getVoterRelationId() {
		return voterRelationId;
	}
	public void setVoterRelationId(Long voterRelationId) {
		this.voterRelationId = voterRelationId;
	}
	public Long getFamilyVoterId() {
		return familyVoterId;
	}
	public void setFamilyVoterId(Long familyVoterId) {
		this.familyVoterId = familyVoterId;
	}
	public String getVoterCardNumber() {
		return voterCardNumber;
	}
	public void setVoterCardNumber(String voterCardNumber) {
		this.voterCardNumber = voterCardNumber;
	}
	public List<CadreFamilyVO> getCadreFamilyDetails() {
		return cadreFamilyDetails;
	}
	public void setCadreFamilyDetails(List<CadreFamilyVO> cadreFamilyDetails) {
		this.cadreFamilyDetails = cadreFamilyDetails;
	}
	public String getConstituencyId() {
		return constituencyId;
	}
	public void setConstituencyId(String constituencyId) {
		this.constituencyId = constituencyId;
	}
	public Long getNomineeRelationId() {
		return nomineeRelationId;
	}
	public void setNomineeRelationId(Long nomineeRelationId) {
		this.nomineeRelationId = nomineeRelationId;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public String getPaymentStatus() {
		return paymentStatus;
	}
	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	public String getMembershipNo() {
		return membershipNo;
	}
	public void setMembershipNo(String membershipNo) {
		this.membershipNo = membershipNo;
	}
	public String getPhotoType() {
		return photoType;
	}
	public void setPhotoType(String photoType) {
		this.photoType = photoType;
	}
	public Long getUserAddressId() {
		return userAddressId;
	}
	public void setUserAddressId(Long userAddressId) {
		this.userAddressId = userAddressId;
	}
	public Long getLocalElectionBodyId() {
		return localElectionBodyId;
	}
	public void setLocalElectionBodyId(Long localElectionBodyId) {
		this.localElectionBodyId = localElectionBodyId;
	}
	public String getHouseNo() {
		return houseNo;
	}
	public void setHouseNo(String houseNo) {
		this.houseNo = houseNo;
	}
	public Long getStateId() {
		return stateId;
	}
	public void setStateId(Long stateId) {
		this.stateId = stateId;
	}
	public Long getDistrictId() {
		return districtId;
	}
	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}
	public Long getMandalId() {
		return mandalId;
	}
	public void setMandalId(Long mandalId) {
		this.mandalId = mandalId;
	}
	public Long getVillageId() {
		return villageId;
	}
	public void setVillageId(Long villageId) {
		this.villageId = villageId;
	}
	public Long getPincode() {
		return pincode;
	}
	public void setPincode(Long pincode) {
		this.pincode = pincode;
	}
	public PaymentGatewayVO getPaymentGatewayVO() {
		return paymentGatewayVO;
	}
	public void setPaymentGatewayVO(PaymentGatewayVO paymentGatewayVO) {
		this.paymentGatewayVO = paymentGatewayVO;
	}
	public Long getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(Long statusCode) {
		this.statusCode = statusCode;
	}
	
	
}
