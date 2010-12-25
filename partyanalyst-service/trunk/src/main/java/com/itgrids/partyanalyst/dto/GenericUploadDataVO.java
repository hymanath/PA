/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on December 23,2010
 */
package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Sai Krishna
 *
 */
public class GenericUploadDataVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public Long   cadreId;
	public String firstName;
	public String middleName;
	public String lastName;
	public String name;
	public String gender;
	public Date   dateOfBirth;
	public Long   age;
	public String headOfFamily;
	public String father;
	public String spouse;
	public Date   fatherOrSpouseDob;
	public Long   fatherOrSpouseAge;
	public String firstChild;
	public String secondChild;
	public String thirdChild;
	public String fourthChild;
	public String fifthChild;
	public String mobile;
	public String phoneNo;
	public String email;
	public String houseNo;
	public String street;
	public String education;
	public String profession;
	public String caste;
	public String cadreLevel;
	public String memberOfPartySince;
	public String presentResponsibilityInParty;
	public String pincode;
	public String familyMembers;
	public String memberType;
	
	private Boolean canSave;
	
	
	private Long stateId;
	private Long districtId;
	private Long constituencyId;
	private Long mandalId;
	private Long villageId;
	private Long localBodyId;
	private Long wardId;
	private Long boothId;
	private Long hamletId;
	private Long parliamentConstiId;
	
	/**
	 * @return the cadreId
	 */
	public Long getCadreId() {
		return cadreId;
	}
	/**
	 * @param cadreId the cadreId to set
	 */
	public void setCadreId(Long cadreId) {
		this.cadreId = cadreId;
	}
	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	/**
	 * @return the middleName
	 */
	public String getMiddleName() {
		return middleName;
	}
	/**
	 * @param middleName the middleName to set
	 */
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}
	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}
	/**
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}
	/**
	 * @return the headOfFamily
	 */
	public String getHeadOfFamily() {
		return headOfFamily;
	}
	/**
	 * @param headOfFamily the headOfFamily to set
	 */
	public void setHeadOfFamily(String headOfFamily) {
		this.headOfFamily = headOfFamily;
	}
	
	/**
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}
	/**
	 * @param mobile the mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	/**
	 * @return the phoneNo
	 */
	public String getPhoneNo() {
		return phoneNo;
	}
	/**
	 * @param phoneNo the phoneNo to set
	 */
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the houseNo
	 */
	public String getHouseNo() {
		return houseNo;
	}
	/**
	 * @param houseNo the houseNo to set
	 */
	public void setHouseNo(String houseNo) {
		this.houseNo = houseNo;
	}
	/**
	 * @return the education
	 */
	public String getEducation() {
		return education;
	}
	/**
	 * @param education the education to set
	 */
	public void setEducation(String education) {
		this.education = education;
	}
	/**
	 * @return the profession
	 */
	public String getProfession() {
		return profession;
	}
	/**
	 * @param profession the profession to set
	 */
	public void setProfession(String profession) {
		this.profession = profession;
	}
	/**
	 * @return the caste
	 */
	public String getCaste() {
		return caste;
	}
	/**
	 * @param caste the caste to set
	 */
	public void setCaste(String caste) {
		this.caste = caste;
	}
	/**
	 * @return the cadreLevel
	 */
	public String getCadreLevel() {
		return cadreLevel;
	}
	/**
	 * @param cadreLevel the cadreLevel to set
	 */
	public void setCadreLevel(String cadreLevel) {
		this.cadreLevel = cadreLevel;
	}
	/**
	 * @return the memberOfPartySince
	 */
	public String getMemberOfPartySince() {
		return memberOfPartySince;
	}
	/**
	 * @param memberOfPartySince the memberOfPartySince to set
	 */
	public void setMemberOfPartySince(String memberOfPartySince) {
		this.memberOfPartySince = memberOfPartySince;
	}
	/**
	 * @return the pincode
	 */
	public String getPincode() {
		return pincode;
	}
	/**
	 * @param pincode the pincode to set
	 */
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	/**
	 * @return the canSave
	 */
	public Boolean getCanSave() {
		return canSave;
	}
	/**
	 * @param canSave the canSave to set
	 */
	public void setCanSave(Boolean canSave) {
		this.canSave = canSave;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the father
	 */
	public String getFather() {
		return father;
	}
	/**
	 * @param father the father to set
	 */
	public void setFather(String father) {
		this.father = father;
	}
	/**
	 * @return the spouse
	 */
	public String getSpouse() {
		return spouse;
	}
	/**
	 * @param spouse the spouse to set
	 */
	public void setSpouse(String spouse) {
		this.spouse = spouse;
	}
	/**
	 * @return the firstChild
	 */
	public String getFirstChild() {
		return firstChild;
	}
	/**
	 * @param firstChild the firstChild to set
	 */
	public void setFirstChild(String firstChild) {
		this.firstChild = firstChild;
	}
	/**
	 * @return the secondChild
	 */
	public String getSecondChild() {
		return secondChild;
	}
	/**
	 * @param secondChild the secondChild to set
	 */
	public void setSecondChild(String secondChild) {
		this.secondChild = secondChild;
	}
	/**
	 * @return the thirdChild
	 */
	public String getThirdChild() {
		return thirdChild;
	}
	/**
	 * @param thirdChild the thirdChild to set
	 */
	public void setThirdChild(String thirdChild) {
		this.thirdChild = thirdChild;
	}
	/**
	 * @return the fourthChild
	 */
	public String getFourthChild() {
		return fourthChild;
	}
	/**
	 * @param fourthChild the fourthChild to set
	 */
	public void setFourthChild(String fourthChild) {
		this.fourthChild = fourthChild;
	}
	/**
	 * @return the fifthChild
	 */
	public String getFifthChild() {
		return fifthChild;
	}
	/**
	 * @param fifthChild the fifthChild to set
	 */
	public void setFifthChild(String fifthChild) {
		this.fifthChild = fifthChild;
	}
	/**
	 * @return the street
	 */
	public String getStreet() {
		return street;
	}
	/**
	 * @param street the street to set
	 */
	public void setStreet(String street) {
		this.street = street;
	}
	/**
	 * @return the presentResponsibilityInParty
	 */
	public String getPresentResponsibilityInParty() {
		return presentResponsibilityInParty;
	}
	/**
	 * @param presentResponsibilityInParty the presentResponsibilityInParty to set
	 */
	public void setPresentResponsibilityInParty(String presentResponsibilityInParty) {
		this.presentResponsibilityInParty = presentResponsibilityInParty;
	}
	/**
	 * @return the dateOfBirth
	 */
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	/**
	 * @param dateOfBirth the dateOfBirth to set
	 */
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	/**
	 * @return the age
	 */
	public Long getAge() {
		return age;
	}
	/**
	 * @param age the age to set
	 */
	public void setAge(Long age) {
		this.age = age;
	}
	/**
	 * @return the fatherOrSpouseAge
	 */
	public Long getFatherOrSpouseAge() {
		return fatherOrSpouseAge;
	}
	/**
	 * @param fatherOrSpouseAge the fatherOrSpouseAge to set
	 */
	public void setFatherOrSpouseAge(Long fatherOrSpouseAge) {
		this.fatherOrSpouseAge = fatherOrSpouseAge;
	}
	/**
	 * @return the fatherOrSpouseDob
	 */
	public Date getFatherOrSpouseDob() {
		return fatherOrSpouseDob;
	}
	/**
	 * @param fatherOrSpouseDob the fatherOrSpouseDob to set
	 */
	public void setFatherOrSpouseDob(Date fatherOrSpouseDob) {
		this.fatherOrSpouseDob = fatherOrSpouseDob;
	}
	/**
	 * @return the familyMembers
	 */
	public String getFamilyMembers() {
		return familyMembers;
	}
	/**
	 * @param familyMembers the familyMembers to set
	 */
	public void setFamilyMembers(String familyMembers) {
		this.familyMembers = familyMembers;
	}
	/**
	 * @return the stateId
	 */
	public Long getStateId() {
		return stateId;
	}
	/**
	 * @param stateId the stateId to set
	 */
	public void setStateId(Long stateId) {
		this.stateId = stateId;
	}
	/**
	 * @return the districtId
	 */
	public Long getDistrictId() {
		return districtId;
	}
	/**
	 * @param districtId the districtId to set
	 */
	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}
	/**
	 * @return the constituencyId
	 */
	public Long getConstituencyId() {
		return constituencyId;
	}
	/**
	 * @param constituencyId the constituencyId to set
	 */
	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}
	/**
	 * @return the mandalId
	 */
	public Long getMandalId() {
		return mandalId;
	}
	/**
	 * @param mandalId the mandalId to set
	 */
	public void setMandalId(Long mandalId) {
		this.mandalId = mandalId;
	}
	/**
	 * @return the villageId
	 */
	public Long getVillageId() {
		return villageId;
	}
	/**
	 * @param villageId the villageId to set
	 */
	public void setVillageId(Long villageId) {
		this.villageId = villageId;
	}
	/**
	 * @return the localBodyId
	 */
	public Long getLocalBodyId() {
		return localBodyId;
	}
	/**
	 * @param localBodyId the localBodyId to set
	 */
	public void setLocalBodyId(Long localBodyId) {
		this.localBodyId = localBodyId;
	}
	/**
	 * @return the wardId
	 */
	public Long getWardId() {
		return wardId;
	}
	/**
	 * @param wardId the wardId to set
	 */
	public void setWardId(Long wardId) {
		this.wardId = wardId;
	}
	/**
	 * @return the boothId
	 */
	public Long getBoothId() {
		return boothId;
	}
	/**
	 * @param boothId the boothId to set
	 */
	public void setBoothId(Long boothId) {
		this.boothId = boothId;
	}
	/**
	 * @return the hamletId
	 */
	public Long getHamletId() {
		return hamletId;
	}
	/**
	 * @param hamletId the hamletId to set
	 */
	public void setHamletId(Long hamletId) {
		this.hamletId = hamletId;
	}
	/**
	 * @return the parliamentConstiId
	 */
	public Long getParliamentConstiId() {
		return parliamentConstiId;
	}
	/**
	 * @param parliamentConstiId the parliamentConstiId to set
	 */
	public void setParliamentConstiId(Long parliamentConstiId) {
		this.parliamentConstiId = parliamentConstiId;
	}
	/**
	 * @return the memberType
	 */
	public String getMemberType() {
		return memberType;
	}
	/**
	 * @param memberType the memberType to set
	 */
	public void setMemberType(String memberType) {
		this.memberType = memberType;
	}
		

}
