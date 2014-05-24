package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.List;

public class CadreVo implements Serializable{
	
	private String firstName;
	private String lastName;
	private String mobileNo;
	private String gender;
	private Long age;
	private String bloodGroup;
	private Long noOfFamilyMembers;
	private String fatherName;
	private Long bloodGroupId;
	private Long noOfVoters;
	private String landNo;
	
	private String emailId;
	private String hno;
	private String street;
	private Long mandalId;
	private Long districtId;
	private Long constituencyId;
	private Long boothNo;
	private Long pinCode;
	private Long villageId;
	private Long casteCategory;
	private String memberType;
	private Long professionId;
	private Long educationId;
	private Long sourceId;
	private Long Income;
	private Long education;
	
	private List<Long> partyDesignationList,govtDesignationList;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
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

	public String getBloodGroup() {
		return bloodGroup;
	}

	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	public Long getNoOfFamilyMembers() {
		return noOfFamilyMembers;
	}

	public void setNoOfFamilyMembers(Long noOfFamilyMembers) {
		this.noOfFamilyMembers = noOfFamilyMembers;
	}

	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public Long getNoOfVoters() {
		return noOfVoters;
	}

	public void setNoOfVoters(Long noOfVoters) {
		this.noOfVoters = noOfVoters;
	}

	public String getLandNo() {
		return landNo;
	}

	public void setLandNo(String landNo) {
		this.landNo = landNo;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getHno() {
		return hno;
	}

	public void setHno(String hno) {
		this.hno = hno;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public Long getMandalId() {
		return mandalId;
	}

	public void setMandalId(Long mandalId) {
		this.mandalId = mandalId;
	}

	public Long getDistrictId() {
		return districtId;
	}

	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}

	public Long getConstituencyId() {
		return constituencyId;
	}

	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}

	public Long getBoothNo() {
		return boothNo;
	}

	public void setBoothNo(Long boothNo) {
		this.boothNo = boothNo;
	}

	public Long getPinCode() {
		return pinCode;
	}

	public void setPinCode(Long pinCode) {
		this.pinCode = pinCode;
	}

	public Long getVillageId() {
		return villageId;
	}

	public void setVillageId(Long villageId) {
		this.villageId = villageId;
	}

	public Long getCasteCategory() {
		return casteCategory;
	}

	public void setCasteCategory(Long casteCategory) {
		this.casteCategory = casteCategory;
	}

	public String getMemberType() {
		return memberType;
	}

	public void setMemberType(String memberType) {
		this.memberType = memberType;
	}

	public Long getProfessionId() {
		return professionId;
	}

	public void setProfessionId(Long professionId) {
		this.professionId = professionId;
	}

	public Long getEducationId() {
		return educationId;
	}

	public void setEducationId(Long educationId) {
		this.educationId = educationId;
	}

	public Long getSourceId() {
		return sourceId;
	}

	public void setSourceId(Long sourceId) {
		this.sourceId = sourceId;
	}

	public Long getIncome() {
		return Income;
	}

	public void setIncome(Long income) {
		Income = income;
	}

	public Long getEducation() {
		return education;
	}

	public void setEducation(Long education) {
		this.education = education;
	}

	public List<Long> getPartyDesignationList() {
		return partyDesignationList;
	}

	public void setPartyDesignationList(List<Long> partyDesignationList) {
		this.partyDesignationList = partyDesignationList;
	}

	public List<Long> getGovtDesignationList() {
		return govtDesignationList;
	}

	public void setGovtDesignationList(List<Long> govtDesignationList) {
		this.govtDesignationList = govtDesignationList;
	}

	public Long getBloodGroupId() {
		return bloodGroupId;
	}

	public void setBloodGroupId(Long bloodGroupId) {
		this.bloodGroupId = bloodGroupId;
	}

	
	
}
