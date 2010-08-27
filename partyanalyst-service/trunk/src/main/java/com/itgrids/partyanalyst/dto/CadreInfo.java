package com.itgrids.partyanalyst.dto;

import java.io.Serializable;

public class CadreInfo implements Serializable{
	private Long cadreID;
	private String firstName;
	private String middleName;
	private String lastName;
	private String mobile;
	private String landLineNo;
	private String email;
	private String gender;
	private Long cadreLevel;
	private Long cadreLevelValue;
	private String strCadreLevel;
	private String strCadreLevelValue;
	private String state;
	private String district;
	private Long constituencyID;
	private String mandal;
	private String village;
	private String booth;
	private Long userID;
	private String userType;
	
	// new fields
	private String fatherOrSpouseName;
	private String dateOfBirth;
	private String houseNo; 
	private String street;
	private String pinCode;
	private Long education;
	private Long occupation;
	private Long casteCategory;
	private String annualIncome;
	private String memberType;
	private String sameAsCA;
	private String designation; 
	private String effectiveDate;
	private String endingDate;
	//permanenet address
	private String phouseNo; 
	private String pstreet;
	private String ppinCode;
	private String pstate;
	private String pdistrict;
	private Long pconstituencyID;
	private String pmandal;
	private String pvillage;
	private String[] trainingCamps;
	private String[] cadreSkills;
	private String dobOption;
	private String age;
	
	public Long getConstituencyID() {
		return constituencyID;
	}
	public void setConstituencyID(Long constituencyID) {
		this.constituencyID = constituencyID;
	}
	public void setUserID(Long userID) {
		this.userID = userID;
	}
	public Long getCadreID() {
		return cadreID;
	}
	public void setCadreID(Long cadreID) {
		this.cadreID = cadreID;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getLandLineNo() {
		return landLineNo;
	}
	public void setLandLineNo(String landLineNo) {
		this.landLineNo = landLineNo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Long getCadreLevel() {
		return cadreLevel;
	}
	public void setCadreLevel(Long cadreLevel) {
		this.cadreLevel = cadreLevel;
	}
	public Long getCadreLevelValue() {
		return cadreLevelValue;
	}
	public void setCadreLevelValue(Long cadreLevelValue) {
		this.cadreLevelValue = cadreLevelValue;
	}
	
	public String getStrCadreLevel() {
		return strCadreLevel;
	}
	public void setStrCadreLevel(String strCadreLevel) {
		this.strCadreLevel = strCadreLevel;
	}
	public String getStrCadreLevelValue() {
		return strCadreLevelValue;
	}
	public void setStrCadreLevelValue(String strCadreLevelValue) {
		this.strCadreLevelValue = strCadreLevelValue;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getMandal() {
		return mandal;
	}
	public void setMandal(String mandal) {
		this.mandal = mandal;
	}
	public String getVillage() {
		return village;
	}
	public void setVillage(String village) {
		this.village = village;
	}
	public String getBooth() {
		return booth;
	}
	public void setBooth(String booth) {
		this.booth = booth;
	}
	
	public Long getUserID() {
		return userID;
	}
	
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}	
	public String getHouseNo() {
		return houseNo;
	}
	public void setHouseNo(String houseNo) {
		this.houseNo = houseNo;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getPinCode() {
		return pinCode;
	}
	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}
	
	public Long getEducation() {
		return education;
	}
	public void setEducation(Long education) {
		this.education = education;
	}
	public Long getOccupation() {
		return occupation;
	}
	public void setOccupation(Long occupation) {
		this.occupation = occupation;
	}
	public Long getCasteCategory() {
		return casteCategory;
	}
	public void setCasteCategory(Long casteCategory) {
		this.casteCategory = casteCategory;
	}
	public String getAnnualIncome() {
		return annualIncome;
	}
	public void setAnnualIncome(String annualIncome) {
		this.annualIncome = annualIncome;
	}
	public String getMemberType() {
		return memberType;
	}
	public void setMemberType(String memberType) {
		this.memberType = memberType;
	}
	public String getSameAsCA() {
		return sameAsCA;
	}
	public void setSameAsCA(String sameAsCA) {
		this.sameAsCA = sameAsCA;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getPstate() {
		return pstate;
	}
	public void setPstate(String pstate) {
		this.pstate = pstate;
	}
	public String getPdistrict() {
		return pdistrict;
	}
	public void setPdistrict(String pdistrict) {
		this.pdistrict = pdistrict;
	}
	public Long getPconstituencyID() {
		return pconstituencyID;
	}
	public void setPconstituencyID(Long pconstituencyID) {
		this.pconstituencyID = pconstituencyID;
	}
	public String getPmandal() {
		return pmandal;
	}
	public void setPmandal(String pmandal) {
		this.pmandal = pmandal;
	}
	public String getPvillage() {
		return pvillage;
	}
	public void setPvillage(String pvillage) {
		this.pvillage = pvillage;
	}
	public String getPhouseNo() {
		return phouseNo;
	}
	public void setPhouseNo(String phouseNo) {
		this.phouseNo = phouseNo;
	}
	public String getPstreet() {
		return pstreet;
	}
	public void setPstreet(String pstreet) {
		this.pstreet = pstreet;
	}
	public String getPpinCode() {
		return ppinCode;
	}
	public void setPpinCode(String ppinCode) {
		this.ppinCode = ppinCode;
	}
	public String getEffectiveDate() {
		return effectiveDate;
	}
	public void setEffectiveDate(String effectiveDate) {
		this.effectiveDate = effectiveDate;
	}
	public String getEndingDate() {
		return endingDate;
	}
	public void setEndingDate(String endingDate) {
		this.endingDate = endingDate;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getFatherOrSpouseName() {
		return fatherOrSpouseName;
	}
	public void setFatherOrSpouseName(String fatherOrSpouseName) {
		this.fatherOrSpouseName = fatherOrSpouseName;
	}
	public String[] getTrainingCamps() {
		return trainingCamps;
	}
	public void setTrainingCamps(String[] trainingCamps) {
		this.trainingCamps = trainingCamps;
	}
	public String[] getCadreSkills() {
		return cadreSkills;
	}
	public void setCadreSkills(String[] cadreSkills) {
		this.cadreSkills = cadreSkills;
	}
	public String getDobOption() {
		return dobOption;
	}
	public void setDobOption(String dobOption) {
		this.dobOption = dobOption;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	
	
	
	
	
	
	
	
	
	
}
