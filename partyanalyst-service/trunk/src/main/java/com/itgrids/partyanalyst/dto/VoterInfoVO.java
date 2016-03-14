package com.itgrids.partyanalyst.dto;

import java.util.ArrayList;
import java.util.List;

public class VoterInfoVO implements java.io.Serializable
{

	private Long id;
	private String name;
	private String teluguName;
	private String TeluguRelativeName;
	private String relativeName;
	private String relationType;
	private String age;
	private String gender;
	private String houseNo;
	private String dateOfBirth;
	private String voterCardNo;
	private Long blodGroupId;
	private String activeDate;
	private String location;
	private Long casteId;
	private String casteName;
	private String mobileNo;
	private String education;
	private Long occupationId;
	private String occupation;
	private Long voterId;
	private Long cadreId;
	private String memberShipId;
	private String          relative;
	private Long            relationTypeId;
	private String isRegistered;
	private String  aadharNo;
	private String nomineeName;
	private String nomineAge;
	private Long nomineeGender;
	private Long VoterRelationId;
	private List<VoterInfoVO> voterInfoVOList = new ArrayList<VoterInfoVO>();
	private List<GenericVO> genericVOList = new ArrayList<GenericVO>();
	private List<SelectOptionVO> selectOptionVOList = new ArrayList<SelectOptionVO>();
	private List<GenericVO> aiDetails = new ArrayList<GenericVO>();
	
	private List<GenericVO> cadreRolesList = new ArrayList<GenericVO>();
	private List<GenericVO> previousParticipationInfoList = new ArrayList<GenericVO>();
	private String image;
	private boolean voterImagePresent;
	private String voterImage;
	private boolean cadreImagePresent;
	private String cadreImage;
	
	private String fmlyVCardNo;
	private Long fmlyVtrId;
	private String candidateAadharNo;
	private String nameType;
	private Long count;
	private String emailId;
	
	private String employeeId;
	private Long zoneId;
	private Long regionId;
	private Long depotId;
	private Long designationId; 
	private List<IdNameVO> regionsList;
	private List<IdNameVO> depotsList;
	private String street;
	private String landmark;
	private String schoolName;
	private String pincode;
	
	public String getSchoolName() {
		return schoolName;
	}
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getTeluguRelativeName() {
		return TeluguRelativeName;
	}
	public void setTeluguRelativeName(String teluguRelativeName) {
		TeluguRelativeName = teluguRelativeName;
	}
	public String getTeluguName() {
		return teluguName;
	}
	public void setTeluguName(String teluguName) {
		this.teluguName = teluguName;
	}
	public String getCandidateAadharNo() {
		return candidateAadharNo;
	}
	public void setCandidateAadharNo(String candidateAadharNo) {
		this.candidateAadharNo = candidateAadharNo;
	}
	public String getFmlyVCardNo() {
		return fmlyVCardNo;
	}
	public void setFmlyVCardNo(String fmlyVCardNo) {
		this.fmlyVCardNo = fmlyVCardNo;
	}
	public Long getFmlyVtrId() {
		return fmlyVtrId;
	}
	public void setFmlyVtrId(Long fmlyVtrId) {
		this.fmlyVtrId = fmlyVtrId;
	}
	public String getNomineAge() {
		return nomineAge;
	}
	public void setNomineAge(String nomineAge) {
		this.nomineAge = nomineAge;
	}
	public Long getNomineeGender() {
		return nomineeGender;
	}
	public void setNomineeGender(Long nomineeGender) {
		this.nomineeGender = nomineeGender;
	}
	public String getAadharNo() {
		return aadharNo;
	}
	public void setAadharNo(String aadharNo) {
		this.aadharNo = aadharNo;
	}
	public String getNomineeName() {
		return nomineeName;
	}
	public void setNomineeName(String nomineeName) {
		this.nomineeName = nomineeName;
	}
	public Long getVoterRelationId() {
		return VoterRelationId;
	}
	public void setVoterRelationId(Long voterRelationId) {
		VoterRelationId = voterRelationId;
	}
	public String getIsRegistered() {
		return isRegistered;
	}
	public void setIsRegistered(String isRegistered) {
		this.isRegistered = isRegistered;
	}
	public List<GenericVO> getCadreRolesList() {
		return cadreRolesList;
	}
	public void setCadreRolesList(List<GenericVO> cadreRolesList) {
		this.cadreRolesList = cadreRolesList;
	}
	public List<GenericVO> getPreviousParticipationInfoList() {
		return previousParticipationInfoList;
	}
	public void setPreviousParticipationInfoList(
			List<GenericVO> previousParticipationInfoList) {
		this.previousParticipationInfoList = previousParticipationInfoList;
	}
	public Long getCasteId() {
		return casteId;
	}
	public void setCasteId(Long casteId) {
		this.casteId = casteId;
	}
	public Long getOccupationId() {
		return occupationId;
	}
	public void setOccupationId(Long occupationId) {
		this.occupationId = occupationId;
	}
	public String getMemberShipId() {
		return memberShipId;
	}
	public void setMemberShipId(String memberShipId) {
		this.memberShipId = memberShipId;
	}
	public List<SelectOptionVO> getSelectOptionVOList() {
		return selectOptionVOList;
	}
	public void setSelectOptionVOList(List<SelectOptionVO> selectOptionVOList) {
		this.selectOptionVOList = selectOptionVOList;
	}
	public List<GenericVO> getGenericVOList() {
		return genericVOList;
	}
	public void setGenericVOList(List<GenericVO> genericVOList) {
		this.genericVOList = genericVOList;
	}
	public Long getVoterId() {
		return voterId;
	}
	public void setVoterId(Long voterId) {
		this.voterId = voterId;
	}
	public Long getCadreId() {
		return cadreId;
	}
	public void setCadreId(Long cadreId) {
		this.cadreId = cadreId;
	}
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getVoterCardNo() {
		return voterCardNo;
	}
	public void setVoterCardNo(String voterCardNo) {
		this.voterCardNo = voterCardNo;
	}
	public Long getBlodGroupId() {
		return blodGroupId;
	}
	public void setBlodGroupId(Long blodGroupId) {
		this.blodGroupId = blodGroupId;
	}
	public String getActiveDate() {
		return activeDate;
	}
	public void setActiveDate(String activeDate) {
		this.activeDate = activeDate;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getCasteName() {
		return casteName;
	}
	public void setCasteName(String casteName) {
		this.casteName = casteName;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
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
	public List<VoterInfoVO> getVoterInfoVOList() {
		return voterInfoVOList;
	}
	public void setVoterInfoVOList(List<VoterInfoVO> voterInfoVOList) {
		this.voterInfoVOList = voterInfoVOList;
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
	public String getRelativeName() {
		return relativeName;
	}
	public void setRelativeName(String relativeName) {
		this.relativeName = relativeName;
	}
	public String getRelationType() {
		return relationType;
	}
	public void setRelationType(String relationType) {
		this.relationType = relationType;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getHouseNo() {
		return houseNo;
	}
	public void setHouseNo(String houseNo) {
		this.houseNo = houseNo;
	}
	public List<GenericVO> getAiDetails() {
		return aiDetails;
	}
	public void setAiDetails(List<GenericVO> aiDetails) {
		this.aiDetails = aiDetails;
	}
	
	public String getRelative() {
		return relative;
	}
	public void setRelative(String relative) {
		this.relative = relative;
	}
	public Long getRelationTypeId() {
		return relationTypeId;
	}
	public void setRelationTypeId(Long relationTypeId) {
		this.relationTypeId = relationTypeId;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public boolean isVoterImagePresent() {
		return voterImagePresent;
	}
	public void setVoterImagePresent(boolean voterImagePresent) {
		this.voterImagePresent = voterImagePresent;
	}
	public String getVoterImage() {
		return voterImage;
	}
	public void setVoterImage(String voterImage) {
		this.voterImage = voterImage;
	}
	public boolean isCadreImagePresent() {
		return cadreImagePresent;
	}
	public void setCadreImagePresent(boolean cadreImagePresent) {
		this.cadreImagePresent = cadreImagePresent;
	}
	public String getCadreImage() {
		return cadreImage;
	}
	public void setCadreImage(String cadreImage) {
		this.cadreImage = cadreImage;
	}
	public String getNameType() {
		return nameType;
	}
	public void setNameType(String nameType) {
		this.nameType = nameType;
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
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
	public Long getDesignationId() {
		return designationId;
	}
	public void setDesignationId(Long designationId) {
		this.designationId = designationId;
	}
	public List<IdNameVO> getRegionsList() {
		return regionsList;
	}
	public void setRegionsList(List<IdNameVO> regionsList) {
		this.regionsList = regionsList;
	}
	public List<IdNameVO> getDepotsList() {
		return depotsList;
	}
	public void setDepotsList(List<IdNameVO> depotsList) {
		this.depotsList = depotsList;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getLandmark() {
		return landmark;
	}
	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}
	public String getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
    	
	
}
