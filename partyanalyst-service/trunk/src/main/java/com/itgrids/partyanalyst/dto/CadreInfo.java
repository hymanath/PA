package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CadreInfo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long cadreID;
	private String cadreId;
	private String firstName;
	private String middleName;
	private String lastName;
	private String mobile;
	private String telephone;
	private String email;
	private String gender;
	private Long cadreLevel;
	private Long cadreLevelValue;
	private String strCadreLevel;
	private String strCadreLevelValue;
	//current address ids
	private String state;
	private String district;
	private Long constituencyID;
	private String mandal;
	private String village;
	//current address location names
	private String stateName;
	private String districtName;
	private String constituencyName;
	private String mandalName;
	private String villageName;
	private String hamletName;
	private String booth;
	private Long userID;
	private String userType;
	private String userPartyName;
	
	// new fields
	private String fatherOrSpouseName;
	private String dateOfBirth;
	private String houseNo; 
	private String street;
	private String pinCode;
	private Long education;
	private Long profession;
	private String professionStr;
	private Long socialStatus;
	private String income;
	private String memberType;
	private Boolean sameAsCA;
	private String designationStr; 
	private Long designation;
	private String effectiveDate;
	private String endingDate;
	//permanenet address
	private String phouseNo; 
	private String pstreet;
	private String ppinCode;
	//permanant or official address location ids
	private String pstate;
	private String pdistrict;
	private Long pconstituencyID;
	private String pmandal;
	private String pvillage;
	//permanant or official address location names
	private String pstateName;
	private String pdistrictName;
	private String pconstituencyName;
	private String pmandalName;
	private String pvillageName;
	private String phamletName;
	private String dobOption;
	private String age;
	private String[] language;
	private List<String> languageOptions_English;
	private List<String> languageOptions_Hindi;
	private String partyCommitteeName;
	private ResultStatus resultStatus;
	//private AddressVO currentAddress;
	//private AddressVO officialAddress;
	private Long partyCommittee;
	private String educationStr;
	private String casteCategoryStr;
	private String[] skills;
	private String[] cadreSkillsNames;
	private List<SelectOptionVO> selectedTrainingCamps = new ArrayList<SelectOptionVO>();
	private String[] cadreParticipatedCampNames;
	private List<String> trainingCamps;
	
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
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
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
	
	public Long getEducation() {
		return education;
	}
	public void setEducation(Long education) {
		this.education = education;
	}
	
	public Long getProfession() {
		return profession;
	}
	public void setProfession(Long profession) {
		this.profession = profession;
	}		
	public Long getSocialStatus() {
		return socialStatus;
	}
	public void setSocialStatus(Long socialStatus) {
		this.socialStatus = socialStatus;
	}
	public String getIncome() {
		return income;
	}
	public void setIncome(String income) {
		this.income = income;
	}
	public String getMemberType() {
		return memberType;
	}
	public void setMemberType(String memberType) {
		this.memberType = memberType;
	}
	public Boolean getSameAsCA() {
		return sameAsCA;
	}
	public void setSameAsCA(Boolean sameAsCA) {
		this.sameAsCA = sameAsCA;
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
	public List<String> getLanguageOptions_English() {
		return languageOptions_English;
	}
	public void setLanguageOptions_English(List<String> languageOptions_English) {
		this.languageOptions_English = languageOptions_English;
	}
	public List<String> getLanguageOptions_Hindi() {
		return languageOptions_Hindi;
	}
	public void setLanguageOptions_Hindi(List<String> languageOptions_Hindi) {
		this.languageOptions_Hindi = languageOptions_Hindi;
	}
	public String[] getLanguage() {
		return language;
	}
	public void setLanguage(String[] language) {
		this.language = language;
	}
	public String getUserPartyName() {
		return userPartyName;
	}
	public void setUserPartyName(String userPartyName) {
		this.userPartyName = userPartyName;
	}
	public ResultStatus getResultStatus() {
		return resultStatus;
	}
	public void setResultStatus(ResultStatus resultStatus) {
		this.resultStatus = resultStatus;
	}		
	public String getCadreId() {
		return cadreId;
	}
	public void setCadreId(String cadreId) {
		this.cadreId = cadreId;
	}
	public Long getPartyCommittee() {
		return partyCommittee;
	}
	public void setPartyCommittee(Long partyCommittee) {
		this.partyCommittee = partyCommittee;
	}
	public void setPartyCommitteeName(String partyCommitteeName) {
		this.partyCommitteeName = partyCommitteeName;
	}	
	public String getPartyCommitteeName() {
		return partyCommitteeName;
	}
	public String getDesignationStr() {
		return designationStr;
	}
	public void setDesignationStr(String designationStr) {
		this.designationStr = designationStr;
	}
	public Long getDesignation() {
		return designation;
	}
	public void setDesignation(Long designation) {
		this.designation = designation;
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
	public Long getConstituencyID() {
		return constituencyID;
	}
	public void setConstituencyID(Long constituencyID) {
		this.constituencyID = constituencyID;
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
	public String getStateName() {
		return stateName;
	}
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	public String getDistrictName() {
		return districtName;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	public String getConstituencyName() {
		return constituencyName;
	}
	public void setConstituencyName(String constituencyName) {
		this.constituencyName = constituencyName;
	}
	public String getMandalName() {
		return mandalName;
	}
	public void setMandalName(String mandalName) {
		this.mandalName = mandalName;
	}
	public String getVillageName() {
		return villageName;
	}
	public void setVillageName(String villageName) {
		this.villageName = villageName;
	}
	public String getPstateName() {
		return pstateName;
	}
	public void setPstateName(String pstateName) {
		this.pstateName = pstateName;
	}
	public String getPdistrictName() {
		return pdistrictName;
	}
	public void setPdistrictName(String pdistrictName) {
		this.pdistrictName = pdistrictName;
	}
	public String getPconstituencyName() {
		return pconstituencyName;
	}
	public void setPconstituencyName(String pconstituencyName) {
		this.pconstituencyName = pconstituencyName;
	}
	public String getPmandalName() {
		return pmandalName;
	}
	public void setPmandalName(String pmandalName) {
		this.pmandalName = pmandalName;
	}
	public String getPvillageName() {
		return pvillageName;
	}
	public void setPvillageName(String pvillageName) {
		this.pvillageName = pvillageName;
	}
	public String getPhamletName() {
		return phamletName;
	}
	public void setPhamletName(String phamletName) {
		this.phamletName = phamletName;
	}
	public String getHamletName() {
		return hamletName;
	}
	public void setHamletName(String hamletName) {
		this.hamletName = hamletName;
	}
	public String getEducationStr() {
		return educationStr;
	}
	public void setEducationStr(String educationStr) {
		this.educationStr = educationStr;
	}
	public String getCasteCategoryStr() {
		return casteCategoryStr;
	}
	public void setCasteCategoryStr(String casteCategoryStr) {
		this.casteCategoryStr = casteCategoryStr;
	}
	public String getProfessionStr() {
		return professionStr;
	}
	public void setProfessionStr(String professionStr) {
		this.professionStr = professionStr;
	}	
	public String[] getCadreSkillsNames() {
		return cadreSkillsNames;
	}
	public void setCadreSkillsNames(String[] cadreSkillsNames) {
		this.cadreSkillsNames = cadreSkillsNames;
	}	
	public String[] getCadreParticipatedCampNames() {
		return cadreParticipatedCampNames;
	}
	public void setCadreParticipatedCampNames(String[] cadreParticipatedCampNames) {
		this.cadreParticipatedCampNames = cadreParticipatedCampNames;
	}
	public String[] getSkills() {
		return skills;
	}
	public void setSkills(String[] skills) {
		this.skills = skills;
	}
	public List<SelectOptionVO> getSelectedTrainingCamps() {
		return selectedTrainingCamps;
	}
	public void setSelectedTrainingCamps(List<SelectOptionVO> selectedTrainingCamps) {
		this.selectedTrainingCamps = selectedTrainingCamps;
	}
	public List<String> getTrainingCamps() {
		return trainingCamps;
	}
	public void setTrainingCamps(List<String> trainingCamps) {
		this.trainingCamps = trainingCamps;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
