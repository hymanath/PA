package com.itgrids.partyanalyst.dto;

import java.io.File;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class CadreRegistrationVO implements Serializable{

	
	private static final long serialVersionUID = 1951217282876724276L;
	
	private Long 			 tdpCadreId;
	private String 			 voterName;
	private Date		 	 dob;
	private String		 	 dobStr;
	private String			 gender;
	private String 			 relativeName;
    private Long             cadreId;
	private String 			 voterId;
	private String 			 previousEnrollmentNumber;
	private String 			 voterCardNo;
	private Date 			 partyMemberSince;
	private String 			 partyMemberSinceStr;
	private Long 			 bloodGroupId;
	private String 			 street;
	private Long 			 casteId;
	private String 			 casteName;
	private String 			 mobileNumber;
	private String 		     emailId;
	private Long 			 educationId;
	private Long 			 occupationId;
	private String 			 houseNo;
	private Long 			 age;
	private String 			 lastName;
	private String 			 landMobileNo;
	private Long 			 enrollmentNumber;
	private Long 			 createdUserId;
	private Long 			 updatedUserId;
	
	private String  		 longititude;
	private String 			 latitude;
	private Date 			 surveyTime;
	
	private String 			 voterCardNumber;
	private String 			 uniqueKey;
	
	private File 			 uploadImage;
	private String			 uploadImageContentType;
	private String 			 uploadImageFileName;
	
	private String 			 path;
	private String           refNo;
	private String 			 imageBase64String;
	
	
	private String 			aadheerNo;
	private String 			nomineeName;
	private Long			nomineeAge;
	private String			nomineeGender;
	
	private String 			constituencyId;
	private String			panchayatId;
	private String          boothId;
	private String			muncipalityId;
	
	private Long			voterRelationId;
	private String 			cadreType;
	
	private String			photoType;
	private String			nameType;
	private String			relationType;
	private boolean         relative;
	private Long            relationTypeId;
	private Long			familyVoterId;

	private String          candidateAadherNo;
	private String		    cadrePrevYear;
	
	private String		   surveyTimeStr;
	private String          area;
	
	private List<CadrePreviousRollesVO> previousRollesList;
	
	private List<CadrePreviousRollesVO> previousParicaptedElectionsList;
	
	private List<CadreFamilyVO> cadreFamilyDetails;
	private String			relativeVoterId;
	private String     voterTeluguName;
	private String     permanentAddress;
	private String     address;
	private String     pincode;
	private String     deliveryMode;
	private String     shipCountry;
	private String     shipAddress;
	private String     orderId;
	private String     email;
	private String       mandalId;
	private String       wardId;
	private String    onlineId;
	private String    absolutePath;
	
	private Long 		familyRelationId;
	private String 		isSmartPhone;
	private String 		registrationType;
	
	
	public String getRegistrationType() {
		return registrationType;
	}
	public void setRegistrationType(String registrationType) {
		this.registrationType = registrationType;
	}
	public String getIsSmartPhone() {
		return isSmartPhone;
	}
	public void setIsSmartPhone(String isSmartPhone) {
		this.isSmartPhone = isSmartPhone;
	}
	public Long getTdpCadreId() {
		return tdpCadreId;
	}
	public void setTdpCadreId(Long tdpCadreId) {
		this.tdpCadreId = tdpCadreId;
	}
	public String getRelativeVoterId() {
		return relativeVoterId;
	}
	public void setRelativeVoterId(String relativeVoterId) {
		this.relativeVoterId = relativeVoterId;
	}
	
	public String getVoterName() {
		return voterName;
	}
	public void setVoterName(String voterName) {
		this.voterName = voterName;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getRelativeName() {
		return relativeName;
	}
	public void setRelativeName(String relativeName) {
		this.relativeName = relativeName;
	}
	public String getVoterId() {
		return voterId;
	}
	public void setVoterId(String voterId) {
		this.voterId = voterId;
	}
	public String getPreviousEnrollmentNumber() {
		return previousEnrollmentNumber;
	}
	public void setPreviousEnrollmentNumber(String previousEnrollmentNumber) {
		this.previousEnrollmentNumber = previousEnrollmentNumber;
	}
	public String getVoterCardNo() {
		return voterCardNo;
	}
	public void setVoterCardNo(String voterCardNo) {
		this.voterCardNo = voterCardNo;
	}
	public Date getPartyMemberSince() {
		return partyMemberSince;
	}
	public void setPartyMemberSince(Date partyMemberSince) {
		this.partyMemberSince = partyMemberSince;
	}
	public Long getBloodGroupId() {
		return bloodGroupId;
	}
	public void setBloodGroupId(Long bloodGroupId) {
		this.bloodGroupId = bloodGroupId;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public Long getCasteId() {
		return casteId;
	}
	public void setCasteId(Long casteId) {
		this.casteId = casteId;
	}
	public String getCasteName() {
		return casteName;
	}
	public void setCasteName(String casteName) {
		this.casteName = casteName;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
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
	public String getHouseNo() {
		return houseNo;
	}
	public void setHouseNo(String houseNo) {
		this.houseNo = houseNo;
	}
	public List<CadrePreviousRollesVO> getPreviousRollesList() {
		return previousRollesList;
	}
	public void setPreviousRollesList(List<CadrePreviousRollesVO> previousRollesList) {
		this.previousRollesList = previousRollesList;
	}
	public List<CadrePreviousRollesVO> getPreviousParicaptedElectionsList() {
		return previousParicaptedElectionsList;
	}
	public void setPreviousParicaptedElectionsList(
			List<CadrePreviousRollesVO> previousParicaptedElectionsList) {
		this.previousParicaptedElectionsList = previousParicaptedElectionsList;
	}
	public Long getAge() {
		return age;
	}
	public void setAge(Long age) {
		this.age = age;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getLandMobileNo() {
		return landMobileNo;
	}
	public void setLandMobileNo(String landMobileNo) {
		this.landMobileNo = landMobileNo;
	}
	public Long getEnrollmentNumber() {
		return enrollmentNumber;
	}
	public void setEnrollmentNumber(Long enrollmentNumber) {
		this.enrollmentNumber = enrollmentNumber;
	}
	public Long getCreatedUserId() {
		return createdUserId;
	}
	public void setCreatedUserId(Long createdUserId) {
		this.createdUserId = createdUserId;
	}
	public Long getUpdatedUserId() {
		return updatedUserId;
	}
	public void setUpdatedUserId(Long updatedUserId) {
		this.updatedUserId = updatedUserId;
	}
	public String getLongititude() {
		return longititude;
	}
	public void setLongititude(String longititude) {
		this.longititude = longititude;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public Date getSurveyTime() {
		return surveyTime;
	}
	public void setSurveyTime(Date surveyTime) {
		this.surveyTime = surveyTime;
	}
	public String getVoterCardNumber() {
		return voterCardNumber;
	}
	public void setVoterCardNumber(String voterCardNumber) {
		this.voterCardNumber = voterCardNumber;
	}
	public String getDobStr() {
		return dobStr;
	}
	public void setDobStr(String dobStr) {
		this.dobStr = dobStr;
	}
	public String getPartyMemberSinceStr() {
		return partyMemberSinceStr;
	}
	public void setPartyMemberSinceStr(String partyMemberSinceStr) {
		this.partyMemberSinceStr = partyMemberSinceStr;
	}
	public String getUniqueKey() {
		return uniqueKey;
	}
	public void setUniqueKey(String uniqueKey) {
		this.uniqueKey = uniqueKey;
	}
	public File getUploadImage() {
		return uploadImage;
	}
	public void setUploadImage(File uploadImage) {
		this.uploadImage = uploadImage;
	}
	public String getUploadImageContentType() {
		return uploadImageContentType;
	}
	public void setUploadImageContentType(String uploadImageContentType) {
		this.uploadImageContentType = uploadImageContentType;
	}
	public String getUploadImageFileName() {
		return uploadImageFileName;
	}
	public void setUploadImageFileName(String uploadImageFileName) {
		this.uploadImageFileName = uploadImageFileName;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public List<CadreFamilyVO> getCadreFamilyDetails() {
		return cadreFamilyDetails;
	}
	public void setCadreFamilyDetails(List<CadreFamilyVO> cadreFamilyDetails) {
		this.cadreFamilyDetails = cadreFamilyDetails;
	}
	public String getRefNo() {
		return refNo;
	}
	public void setRefNo(String refNo) {
		this.refNo = refNo;
	}
	public String getImageBase64String() {
		return imageBase64String;
	}
	public void setImageBase64String(String imageBase64String) {
		this.imageBase64String = imageBase64String;
	}
	public String getAadheerNo() {
		return aadheerNo;
	}
	public void setAadheerNo(String aadheerNo) {
		this.aadheerNo = aadheerNo;
	}
	public String getConstituencyId() {
		return constituencyId;
	}
	public void setConstituencyId(String constituencyId) {
		this.constituencyId = constituencyId;
	}
	public String getPanchayatId() {
		return panchayatId;
	}
	public void setPanchayatId(String panchayatId) {
		this.panchayatId = panchayatId;
	}
	public String getBoothId() {
		return boothId;
	}
	public void setBoothId(String boothId) {
		this.boothId = boothId;
	}
	public String getNomineeName() {
		return nomineeName;
	}
	public void setNomineeName(String nomineeName) {
		this.nomineeName = nomineeName;
	}
	public String getMuncipalityId() {
		return muncipalityId;
	}
	public void setMuncipalityId(String muncipalityId) {
		this.muncipalityId = muncipalityId;
	}
	public Long getVoterRelationId() {
		return voterRelationId;
	}
	public void setVoterRelationId(Long voterRelationId) {
		this.voterRelationId = voterRelationId;
	}
	public String getCadreType() {
		return cadreType;
	}
	public void setCadreType(String cadreType) {
		this.cadreType = cadreType;
	}
	public Long getNomineeAge() {
		return nomineeAge;
	}
	public void setNomineeAge(Long nomineeAge) {
		this.nomineeAge = nomineeAge;
	}
	public String getPhotoType() {
		return photoType;
	}
	public void setPhotoType(String photoType) {
		this.photoType = photoType;
	}
	
	public String getNameType() {
		return nameType;
	}
	public void setNameType(String nameType) {
		this.nameType = nameType;
	}
	public String getNomineeGender() {
		return nomineeGender;
	}
	public void setNomineeGender(String nomineeGender) {
		this.nomineeGender = nomineeGender;
	}
	public String getRelationType() {
		return relationType;
	}
	public void setRelationType(String relationType) {
		this.relationType = relationType;
	}
	
	
	
	public boolean isRelative() {
		return relative;
	}
	public void setRelative(boolean relative) {
		this.relative = relative;
	}
	public Long getRelationTypeId() {
		return relationTypeId;
	}
	public void setRelationTypeId(Long relationTypeId) {
		this.relationTypeId = relationTypeId;
	}
	public String getCandidateAadherNo() {
		return candidateAadherNo;
	}
	public void setCandidateAadherNo(String candidateAadherNo) {
		this.candidateAadherNo = candidateAadherNo;
	}
	public String getSurveyTimeStr() {
		return surveyTimeStr;
	}
	public void setSurveyTimeStr(String surveyTimeStr) {
		this.surveyTimeStr = surveyTimeStr;
	}
	public Long getFamilyVoterId() {
		return familyVoterId;
	}
	public void setFamilyVoterId(Long familyVoterId) {
		this.familyVoterId = familyVoterId;
	}
	public String getCadrePrevYear() {
		return cadrePrevYear;
	}
	public void setCadrePrevYear(String cadrePrevYear) {
		this.cadrePrevYear = cadrePrevYear;
	}
	public String getVoterTeluguName() {
		return voterTeluguName;
	}
	public void setVoterTeluguName(String voterTeluguName) {
		this.voterTeluguName = voterTeluguName;
	}
	public String getPermanentAddress() {
		return permanentAddress;
	}
	public void setPermanentAddress(String permanentAddress) {
		this.permanentAddress = permanentAddress;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	public String getDeliveryMode() {
		return deliveryMode;
	}
	public void setDeliveryMode(String deliveryMode) {
		this.deliveryMode = deliveryMode;
	}
	public String getShipCountry() {
		return shipCountry;
	}
	public void setShipCountry(String shipCountry) {
		this.shipCountry = shipCountry;
	}
	public String getShipAddress() {
		return shipAddress;
	}
	public void setShipAddress(String shipAddress) {
		this.shipAddress = shipAddress;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMandalId() {
		return mandalId;
	}
	public void setMandalId(String mandalId) {
		this.mandalId = mandalId;
	}
	public String getWardId() {
		return wardId;
	}
	public void setWardId(String wardId) {
		this.wardId = wardId;
	}
	public String getOnlineId() {
		return onlineId;
	}
	public void setOnlineId(String onlineId) {
		this.onlineId = onlineId;
	}
	public String getAbsolutePath() {
		return absolutePath;
	}
	public void setAbsolutePath(String absolutePath) {
		this.absolutePath = absolutePath;
	}
	public Long getFamilyRelationId() {
		return familyRelationId;
	}
	public void setFamilyRelationId(Long familyRelationId) {
		this.familyRelationId = familyRelationId;
	}
	public Long getCadreId() {
		return cadreId;
	}
	public void setCadreId(Long cadreId) {
		this.cadreId = cadreId;
	}
	
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	
}
