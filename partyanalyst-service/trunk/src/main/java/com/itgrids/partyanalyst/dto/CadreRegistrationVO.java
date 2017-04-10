package com.itgrids.partyanalyst.dto;

import java.io.File;

import java.io.Serializable;
import java.util.ArrayList;
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
	private String 			 relativeType;
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
	private String 		marriageDateStr;
	private String 		whatsAppStatus;
	private String faceboohUrl;	
	private AddressVO addressVO;
	
	//private String		cadreRegType;
	private Long		cadreRegTypeId;
	private Long		designationId;
	private Long		zoneId;
	private Long		regionId;
	private Long		depotId;
	private String		idCardNo;
	private String		streetName;
	private String		landMark;
	private Long		perAddrsStateId;
	private Long		perAddrsDistId;
	private Long		perAddrsConstId;
	private Long		perAddrsMandalId;
	private Long		perAddrsLebId;
	private Long		perAddrsDivionId;
	private Long		perAddrsVillId;
	private Long		perAddrsWardId;
	private String 		employeeId;
	private String		mode;
	
	private Long		prsntAddrsStateId;
	private Long		prsntAddrsDistId;
	private Long		prsntAddrsConstId;
	private Long		prsntAddrsMandalId;
	private Long		prsntAddrsLebId;
	private Long		prsntAddrsDivionId;
	private Long		prsntAddrsVillId;
	private Long		prsntAddrsWardId;
	private String      prsntAddrsHNo;
	private String      prsntAddrsLandmark;
	private String      prsntAddrsAreaName;
	private String      prsntAddrsStreet;
	private String      prsntAddrsPincode;
	private String      prsntAddrsApartment;
	private String      prsntAddrsHamlet;
	
	private Long		workAddrsStateId;
	private Long		workAddrsDistId;
	private Long		workAddrsConstId;
	private Long		workAddrsMandalId;
	private Long		workAddrsLebId;
	private Long		workAddrsDivionId;
	private Long		workAddrsVillId;
	private Long		workAddrsWardId;
	private String     	workAddrsHNo;
	private String     	workAddrsLandmark;
	private String      workAddrsAreaName;
	private String      workAddrsStreet;
	private String      workAddrsPincode;
	private String     	workAddrsApartment;
	private String    	workAddrsHamlet;
	
	
	private String      districtId;
	private Long        desigId;
	private String      schoolName;
	private String      memberTypeId;
	private String		relativeVoterCardNo;
	private String      voterCardType;
	private Long        drivingLicenseId;
	private Long        vehicleTypeId;
	private Long        schoolNameId;	
	private Long 		unionTypeId;
	
	private String workHouseNo;
	private String workStreet;
	private String workLandMark;
	private Long presentPerAddrsDistId;
	private Long presentPerAddrsConstId;
	private Long presentPerAddrsMandalId;
	private Long presentPerAddrsVillId;
	private String dataSourceType;
	private String otherDesignationStr;
	private String userIpAddress;
	private Long parentTdpCadreId;
	private List<IdAndNameVO> casteList;
	private List<IdAndNameVO> eduQualftnList;
	private List<IdAndNameVO> relativesList;
	private String cadreName;
	private String aadharNo;
	private String dateOfBirth;
	private Long casteStateId;
	private Long nomineeRelationId;
	private String imageSaveStatus;
	private String memberShipNo;
	private Long webUserId;
	private String 			prevNomineeName;
	private Long			prevNomineeAge;
	private String			preNomineeGender;
	private Long prevNomineeRelationId;
	
	private String newNomineeName;
	private String newNomineeGender;
	private Long newNomineeAge;
	private Long newNomineeRelationTypeId;
	private String isNewImageExist;
	private String 	prevNomineeAadharNo;
	private String isNomineeChanged;
	private Long newVoterId;
	private String deliveryLocation;
	private String nomineeAadharNo;
	private String newNomineeRelationType;
	private Long tabPrimaryKey;
	
	public Long getTabPrimaryKey() {
		return tabPrimaryKey;
	}
	public void setTabPrimaryKey(Long tabPrimaryKey) {
		this.tabPrimaryKey = tabPrimaryKey;
	}
	public String getNomineeAadharNo() {
		return nomineeAadharNo;
	}
	public void setNomineeAadharNo(String nomineeAadharNo) {
		this.nomineeAadharNo = nomineeAadharNo;
	}
	public String getDeliveryLocation() {
		return deliveryLocation;
	}
	public void setDeliveryLocation(String deliveryLocation) {
		this.deliveryLocation = deliveryLocation;
	}
	public Long getNewVoterId() {
		return newVoterId;
	}
	public void setNewVoterId(Long newVoterId) {
		this.newVoterId = newVoterId;
	}
	
	public String getPrsntAddrsAreaName() {
		return prsntAddrsAreaName;
	}
	public void setPrsntAddrsAreaName(String prsntAddrsAreaName) {
		this.prsntAddrsAreaName = prsntAddrsAreaName;
	}
	public String getWorkAddrsAreaName() {
		return workAddrsAreaName;
	}
	public void setWorkAddrsAreaName(String workAddrsAreaName) {
		this.workAddrsAreaName = workAddrsAreaName;
	}
	public String getPrsntAddrsApartment() {
		return prsntAddrsApartment;
	}
	public void setPrsntAddrsApartment(String prsntAddrsApartment) {
		this.prsntAddrsApartment = prsntAddrsApartment;
	}
	public String getPrsntAddrsHamlet() {
		return prsntAddrsHamlet;
	}
	public void setPrsntAddrsHamlet(String prsntAddrsHamlet) {
		this.prsntAddrsHamlet = prsntAddrsHamlet;
	}
	public String getWorkAddrsApartment() {
		return workAddrsApartment;
	}
	public void setWorkAddrsApartment(String workAddrsApartment) {
		this.workAddrsApartment = workAddrsApartment;
	}
	public String getWorkAddrsHamlet() {
		return workAddrsHamlet;
	}
	public void setWorkAddrsHamlet(String workAddrsHamlet) {
		this.workAddrsHamlet = workAddrsHamlet;
	}
	public String getIsNomineeChanged() {
		return isNomineeChanged;
	}
	public void setIsNomineeChanged(String isNomineeChanged) {
		this.isNomineeChanged = isNomineeChanged;
	}
	public String getIsNewImageExist() {
		return isNewImageExist;
	}
	public void setIsNewImageExist(String isNewImageExist) {
		this.isNewImageExist = isNewImageExist;
	}
	public String getPrevNomineeName() {
		return prevNomineeName;
	}
	public void setPrevNomineeName(String prevNomineeName) {
		this.prevNomineeName = prevNomineeName;
	}
	public Long getPrevNomineeAge() {
		return prevNomineeAge;
	}
	public void setPrevNomineeAge(Long prevNomineeAge) {
		this.prevNomineeAge = prevNomineeAge;
	}
	public String getPreNomineeGender() {
		return preNomineeGender;
	}
	public void setPreNomineeGender(String preNomineeGender) {
		this.preNomineeGender = preNomineeGender;
	}
	public Long getPrevNomineeRelationId() {
		return prevNomineeRelationId;
	}
	public void setPrevNomineeRelationId(Long prevNomineeRelationId) {
		this.prevNomineeRelationId = prevNomineeRelationId;
	}
	public Long getWebUserId() {
		return webUserId;
	}
	public void setWebUserId(Long webUserId) {
		this.webUserId = webUserId;
	}
	public String getMemberShipNo() {
		return memberShipNo;
	}
	public void setMemberShipNo(String memberShipNo) {
		this.memberShipNo = memberShipNo;
	}
	public String getImageSaveStatus() {
		return imageSaveStatus;
	}
	public void setImageSaveStatus(String imageSaveStatus) {
		this.imageSaveStatus = imageSaveStatus;
	}
	public Long getNomineeRelationId() {
		return nomineeRelationId;
	}
	public void setNomineeRelationId(Long nomineeRelationId) {
		this.nomineeRelationId = nomineeRelationId;
	}
	public Long getCasteStateId() {
		return casteStateId;
	}
	public void setCasteStateId(Long casteStateId) {
		this.casteStateId = casteStateId;
	}
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getAadharNo() {
		return aadharNo;
	}
	public void setAadharNo(String aadharNo) {
		this.aadharNo = aadharNo;
	}
	public String getCadreName() {
		return cadreName;
	}
	public void setCadreName(String cadreName) {
		this.cadreName = cadreName;
	}
	public List<IdAndNameVO> getEduQualftnList() {
		return eduQualftnList;
	}
	public void setEduQualftnList(List<IdAndNameVO> eduQualftnList) {
		this.eduQualftnList = eduQualftnList;
	}
	public List<IdAndNameVO> getCasteList() {
		return casteList;
	}
	public void setCasteList(List<IdAndNameVO> casteList) {
		this.casteList = casteList;
	}
	public Long getParentTdpCadreId() {
		return parentTdpCadreId;
	}
	public void setParentTdpCadreId(Long parentTdpCadreId) {
		this.parentTdpCadreId = parentTdpCadreId;
	}
	public String getUserIpAddress() {
		return userIpAddress;
	}
	public void setUserIpAddress(String userIpAddress) {
		this.userIpAddress = userIpAddress;
	}
	public String getOtherDesignationStr() {
		return otherDesignationStr;
	}
	public void setOtherDesignationStr(String otherDesignationStr) {
		this.otherDesignationStr = otherDesignationStr;
	}
	public String getDataSourceType() {
		return dataSourceType;
	}
	public void setDataSourceType(String dataSourceType) {
		this.dataSourceType = dataSourceType;
	}
	public Long getPresentPerAddrsDistId() {
		return presentPerAddrsDistId;
	}
	public void setPresentPerAddrsDistId(Long presentPerAddrsDistId) {
		this.presentPerAddrsDistId = presentPerAddrsDistId;
	}
	public Long getPresentPerAddrsConstId() {
		return presentPerAddrsConstId;
	}
	public void setPresentPerAddrsConstId(Long presentPerAddrsConstId) {
		this.presentPerAddrsConstId = presentPerAddrsConstId;
	}
	public Long getPresentPerAddrsMandalId() {
		return presentPerAddrsMandalId;
	}
	public void setPresentPerAddrsMandalId(Long presentPerAddrsMandalId) {
		this.presentPerAddrsMandalId = presentPerAddrsMandalId;
	}
	public Long getPresentPerAddrsVillId() {
		return presentPerAddrsVillId;
	}
	public void setPresentPerAddrsVillId(Long presentPerAddrsVillId) {
		this.presentPerAddrsVillId = presentPerAddrsVillId;
	}
	public String getWorkHouseNo() {
		return workHouseNo;
	}
	public void setWorkHouseNo(String workHouseNo) {
		this.workHouseNo = workHouseNo;
	}
	public String getWorkStreet() {
		return workStreet;
	}
	public void setWorkStreet(String workStreet) {
		this.workStreet = workStreet;
	}
	public String getWorkLandMark() {
		return workLandMark;
	}
	public void setWorkLandMark(String workLandMark) {
		this.workLandMark = workLandMark;
	}
	
	public Long getWorkAddrsStateId() {
		return workAddrsStateId;
	}
	public void setWorkAddrsStateId(Long workAddrsStateId) {
		this.workAddrsStateId = workAddrsStateId;
	}
	public Long getWorkAddrsDistId() {
		return workAddrsDistId;
	}
	public void setWorkAddrsDistId(Long workAddrsDistId) {
		this.workAddrsDistId = workAddrsDistId;
	}
	public Long getWorkAddrsConstId() {
		return workAddrsConstId;
	}
	public void setWorkAddrsConstId(Long workAddrsConstId) {
		this.workAddrsConstId = workAddrsConstId;
	}
	public Long getWorkAddrsMandalId() {
		return workAddrsMandalId;
	}
	public void setWorkAddrsMandalId(Long workAddrsMandalId) {
		this.workAddrsMandalId = workAddrsMandalId;
	}
	public Long getWorkAddrsLebId() {
		return workAddrsLebId;
	}
	public void setWorkAddrsLebId(Long workAddrsLebId) {
		this.workAddrsLebId = workAddrsLebId;
	}
	public Long getWorkAddrsDivionId() {
		return workAddrsDivionId;
	}
	public void setWorkAddrsDivionId(Long workAddrsDivionId) {
		this.workAddrsDivionId = workAddrsDivionId;
	}
	public Long getWorkAddrsVillId() {
		return workAddrsVillId;
	}
	public void setWorkAddrsVillId(Long workAddrsVillId) {
		this.workAddrsVillId = workAddrsVillId;
	}
	public Long getWorkAddrsWardId() {
		return workAddrsWardId;
	}
	public void setWorkAddrsWardId(Long workAddrsWardId) {
		this.workAddrsWardId = workAddrsWardId;
	}
	public String getWorkAddrsHNo() {
		return workAddrsHNo;
	}
	public void setWorkAddrsHNo(String workAddrsHNo) {
		this.workAddrsHNo = workAddrsHNo;
	}
	public String getWorkAddrsLandmark() {
		return workAddrsLandmark;
	}
	public void setWorkAddrsLandmark(String workAddrsLandmark) {
		this.workAddrsLandmark = workAddrsLandmark;
	}
	public String getWorkAddrsStreet() {
		return workAddrsStreet;
	}
	public void setWorkAddrsStreet(String workAddrsStreet) {
		this.workAddrsStreet = workAddrsStreet;
	}
	public String getWorkAddrsPincode() {
		return workAddrsPincode;
	}
	public void setWorkAddrsPincode(String workAddrsPincode) {
		this.workAddrsPincode = workAddrsPincode;
	}
	public Long getUnionTypeId() {
		return unionTypeId;
	}
	public void setUnionTypeId(Long unionTypeId) {
		this.unionTypeId = unionTypeId;
	}
	public String getPrsntAddrsHNo() {
		return prsntAddrsHNo;
	}
	public void setPrsntAddrsHNo(String prsntAddrsHNo) {
		this.prsntAddrsHNo = prsntAddrsHNo;
	}
	public String getPrsntAddrsLandmark() {
		return prsntAddrsLandmark;
	}
	public void setPrsntAddrsLandmark(String prsntAddrsLandmark) {
		this.prsntAddrsLandmark = prsntAddrsLandmark;
	}
	public String getPrsntAddrsStreet() {
		return prsntAddrsStreet;
	}
	public void setPrsntAddrsStreet(String prsntAddrsStreet) {
		this.prsntAddrsStreet = prsntAddrsStreet;
	}
	public String getPrsntAddrsPincode() {
		return prsntAddrsPincode;
	}
	public void setPrsntAddrsPincode(String prsntAddrsPincode) {
		this.prsntAddrsPincode = prsntAddrsPincode;
	}
	public String getMemberTypeId() {
		return memberTypeId;
	}
	public void setMemberTypeId(String memberTypeId) {
		this.memberTypeId = memberTypeId;
	}
	public String getSchoolName() {
		return schoolName;
	}
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
	public String getRelativeVoterCardNo() {
		return relativeVoterCardNo;
	}
	public void setRelativeVoterCardNo(String relativeVoterCardNo) {
		this.relativeVoterCardNo = relativeVoterCardNo;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public String getFaceboohUrl() {
		return faceboohUrl;
	}
	public void setFaceboohUrl(String faceboohUrl) {
		this.faceboohUrl = faceboohUrl;
	}
	public String getWhatsAppStatus() {
		return whatsAppStatus;
	}
	public void setWhatsAppStatus(String whatsAppStatus) {
		this.whatsAppStatus = whatsAppStatus;
	}
	public String getMarriageDateStr() {
		return marriageDateStr;
	}
	public void setMarriageDateStr(String marriageDateStr) {
		this.marriageDateStr = marriageDateStr;
	}
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
	public AddressVO getAddressVO() {
		return addressVO;
	}
	public void setAddressVO(AddressVO addressVO) {
		this.addressVO = addressVO;
	}
	public Long getDesignationId() {
		return designationId;
	}
	public void setDesignationId(Long designationId) {
		this.designationId = designationId;
	}
	public Long getZoneId() {
		return zoneId;
	}
	public void setZoneId(Long zoneId) {
		this.zoneId = zoneId;
	}
	public Long getRegionId() {
		return regionId;
	}
	public void setRegionId(Long regionId) {
		this.regionId = regionId;
	}
	public Long getDepotId() {
		return depotId;
	}
	public void setDepotId(Long depotId) {
		this.depotId = depotId;
	}
	public String getIdCardNo() {
		return idCardNo;
	}
	public void setIdCardNo(String idCardNo) {
		this.idCardNo = idCardNo;
	}
	public String getStreetName() {
		return streetName;
	}
	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}
	public String getLandMark() {
		return landMark;
	}
	public void setLandMark(String landMark) {
		this.landMark = landMark;
	}
	public Long getPerAddrsStateId() {
		return perAddrsStateId;
	}
	public void setPerAddrsStateId(Long perAddrsStateId) {
		this.perAddrsStateId = perAddrsStateId;
	}
	public Long getPerAddrsDistId() {
		return perAddrsDistId;
	}
	public void setPerAddrsDistId(Long perAddrsDistId) {
		this.perAddrsDistId = perAddrsDistId;
	}
	public Long getPerAddrsConstId() {
		return perAddrsConstId;
	}
	public void setPerAddrsConstId(Long perAddrsConstId) {
		this.perAddrsConstId = perAddrsConstId;
	}
	public Long getPerAddrsMandalId() {
		return perAddrsMandalId;
	}
	public void setPerAddrsMandalId(Long perAddrsMandalId) {
		this.perAddrsMandalId = perAddrsMandalId;
	}
	public Long getPerAddrsLebId() {
		return perAddrsLebId;
	}
	public void setPerAddrsLebId(Long perAddrsLebId) {
		this.perAddrsLebId = perAddrsLebId;
	}
	public Long getPerAddrsDivionId() {
		return perAddrsDivionId;
	}
	public void setPerAddrsDivionId(Long perAddrsDivionId) {
		this.perAddrsDivionId = perAddrsDivionId;
	}
	public Long getPerAddrsVillId() {
		return perAddrsVillId;
	}
	public void setPerAddrsVillId(Long perAddrsVillId) {
		this.perAddrsVillId = perAddrsVillId;
	}
	public Long getPerAddrsWardId() {
		return perAddrsWardId;
	}
	public void setPerAddrsWardId(Long perAddrsWardId) {
		this.perAddrsWardId = perAddrsWardId;
	}
	/*public String getCadreRegType() {
		return cadreRegType;
	}
	public void setCadreRegType(String cadreRegType) {
		this.cadreRegType = cadreRegType;
	}*/
	public Long getCadreRegTypeId() {
		return cadreRegTypeId;
	}
	public void setCadreRegTypeId(Long cadreRegTypeId) {
		this.cadreRegTypeId = cadreRegTypeId;
	}
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public Long getPrsntAddrsStateId() {
		return prsntAddrsStateId;
	}
	public void setPrsntAddrsStateId(Long prsntAddrsStateId) {
		this.prsntAddrsStateId = prsntAddrsStateId;
	}
	public Long getPrsntAddrsDistId() {
		return prsntAddrsDistId;
	}
	public void setPrsntAddrsDistId(Long prsntAddrsDistId) {
		this.prsntAddrsDistId = prsntAddrsDistId;
	}
	public Long getPrsntAddrsConstId() {
		return prsntAddrsConstId;
	}
	public void setPrsntAddrsConstId(Long prsntAddrsConstId) {
		this.prsntAddrsConstId = prsntAddrsConstId;
	}
	public Long getPrsntAddrsMandalId() {
		return prsntAddrsMandalId;
	}
	public void setPrsntAddrsMandalId(Long prsntAddrsMandalId) {
		this.prsntAddrsMandalId = prsntAddrsMandalId;
	}
	public Long getPrsntAddrsLebId() {
		return prsntAddrsLebId;
	}
	public void setPrsntAddrsLebId(Long prsntAddrsLebId) {
		this.prsntAddrsLebId = prsntAddrsLebId;
	}
	public Long getPrsntAddrsDivionId() {
		return prsntAddrsDivionId;
	}
	public void setPrsntAddrsDivionId(Long prsntAddrsDivionId) {
		this.prsntAddrsDivionId = prsntAddrsDivionId;
	}
	public Long getPrsntAddrsVillId() {
		return prsntAddrsVillId;
	}
	public void setPrsntAddrsVillId(Long prsntAddrsVillId) {
		this.prsntAddrsVillId = prsntAddrsVillId;
	}
	public Long getPrsntAddrsWardId() {
		return prsntAddrsWardId;
	}
	public void setPrsntAddrsWardId(Long prsntAddrsWardId) {
		this.prsntAddrsWardId = prsntAddrsWardId;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getDistrictId() {
		return districtId;
	}
	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}
	public String getVoterCardType() {
		return voterCardType;
	}
	public void setVoterCardType(String voterCardType) {
		this.voterCardType = voterCardType;
	}
	public Long getDesigId() {
		return desigId;
	}
	public void setDesigId(Long desigId) {
		this.desigId = desigId;
	}
	public Long getSchoolNameId() {
		return schoolNameId;
	}
	public void setSchoolNameId(Long schoolNameId) {
		this.schoolNameId = schoolNameId;
	}
	public Long getDrivingLicenseId() {
		return drivingLicenseId;
	}
	public void setDrivingLicenseId(Long drivingLicenseId) {
		this.drivingLicenseId = drivingLicenseId;
	}
	public Long getVehicleTypeId() {
		return vehicleTypeId;
	}
	public void setVehicleTypeId(Long vehicleTypeId) {
		this.vehicleTypeId = vehicleTypeId;
	}
	public String getRelativeType() {
		return relativeType;
	}
	public void setRelativeType(String relativeType) {
		this.relativeType = relativeType;
	}
	public List<IdAndNameVO> getRelativesList() {
		return relativesList;
	}
	public void setRelativesList(List<IdAndNameVO> relativesList) {
		this.relativesList = relativesList;
	}
	public String getNewNomineeName() {
		return newNomineeName;
	}
	public void setNewNomineeName(String newNomineeName) {
		this.newNomineeName = newNomineeName;
	}
	public String getNewNomineeGender() {
		return newNomineeGender;
	}
	public void setNewNomineeGender(String newNomineeGender) {
		this.newNomineeGender = newNomineeGender;
	}
	public Long getNewNomineeAge() {
		return newNomineeAge;
	}
	public void setNewNomineeAge(Long newNomineeAge) {
		this.newNomineeAge = newNomineeAge;
	}
	public Long getNewNomineeRelationTypeId() {
		return newNomineeRelationTypeId;
	}
	public void setNewNomineeRelationTypeId(Long newNomineeRelationTypeId) {
		this.newNomineeRelationTypeId = newNomineeRelationTypeId;
	}
	public String getPrevNomineeAadharNo() {
		return prevNomineeAadharNo;
	}
	public void setPrevNomineeAadharNo(String prevNomineeAadharNo) {
		this.prevNomineeAadharNo = prevNomineeAadharNo;
	}
	public String getNewNomineeRelationType() {
		return newNomineeRelationType;
	}
	public void setNewNomineeRelationType(String newNomineeRelationType) {
		this.newNomineeRelationType = newNomineeRelationType;
	}
	
	
}
