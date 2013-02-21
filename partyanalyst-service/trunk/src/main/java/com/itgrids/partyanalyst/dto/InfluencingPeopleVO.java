package com.itgrids.partyanalyst.dto;

import java.io.Serializable;

public class InfluencingPeopleVO implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String designation;
	private String age;
	private String year;	
	private String party;
	private String localArea;
	private Long influencingPeopleId;
	private String personName;
	private String lastName;
	private String gender;
	private String contactNumber;
	private String email;
	private Long partyId;
	private String cast;
	private String occupation;
	private String influencingRange;
	private String influencingRangeName;
	private String position;
	private Long hamletId;
	private Long boothId;
	private Long voterId;
	
	private String fatherOrSpouceName;
	private Long userId;
	private String firstName;
	private Long influencePersonId;
	
	private Long stateId;
	private Long districtId;
	private Long constituencyId;
	private Long mandalId;
	private Long muncipalityId;
	private Long wardId;
	
	public String getFatherOrSpouceName() {
		return fatherOrSpouceName;
	}


	public void setFatherOrSpouceName(String fatherOrSpouceName) {
		this.fatherOrSpouceName = fatherOrSpouceName;
	}


	public Long getUserId() {
		return userId;
	}


	public void setUserId(Long userId) {
		this.userId = userId;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public Long getInfluencePersonId() {
		return influencePersonId;
	}


	public void setInfluencePersonId(Long influencePersonId) {
		this.influencePersonId = influencePersonId;
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


	public Long getConstituencyId() {
		return constituencyId;
	}


	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}


	public Long getMandalId() {
		return mandalId;
	}


	public void setMandalId(Long mandalId) {
		this.mandalId = mandalId;
	}


	public Long getMuncipalityId() {
		return muncipalityId;
	}


	public void setMuncipalityId(Long muncipalityId) {
		this.muncipalityId = muncipalityId;
	}


	public Long getWardId() {
		return wardId;
	}


	public void setWardId(Long wardId) {
		this.wardId = wardId;
	}


	public Long getScopeValue() {
		return scopeValue;
	}


	public void setScopeValue(Long scopeValue) {
		this.scopeValue = scopeValue;
	}

	private Long scopeValue;
	public String getParty() {
		return party;
	}

	
	public Long getVoterId() {
		return voterId;
	}


	public void setVoterId(Long voterId) {
		this.voterId = voterId;
	}


	public void setHamletId(Long hamletId) {
		this.hamletId = hamletId;
	}

	public Long getPartyId() {
		return partyId;
	}

	public void setPartyId(Long partyId) {
		this.partyId = partyId;
	}

	public void setParty(String party) {
		this.party = party;
	}
	
	public Long getHamletId() {
		return hamletId;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCast() {
		return cast;
	}

	public void setCast(String cast) {
		this.cast = cast;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public String getInfluencingRange() {
		return influencingRange;
	}

	public void setInfluencingRange(String influencingRange) {
		this.influencingRange = influencingRange;
	}

	
	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getPersonName() {
		return personName;
	}
	
	public void setPersonName(String personName) {
		this.personName = personName;
	}
	
	public String getDesignation() {
		return designation;
	}
	
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	
	public String getLocalArea() {
		return localArea;
	}
	
	public void setLocalArea(String localArea) {
		this.localArea = localArea;
	}
	
	public String getContactNumber() {
		return contactNumber;
	}
	
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	
	public String getAge() {
		return age;
	}
	
	public void setAge(String age) {
		this.age = age;
	}

	public String getInfluencingRangeName() {
		return influencingRangeName;
	}

	public void setInfluencingRangeName(String influencingRangeName) {
		this.influencingRangeName = influencingRangeName;
	}

	public void setInfluencingPeopleId(Long influencingPeopleId) {
		this.influencingPeopleId = influencingPeopleId;
	}

	public Long getInfluencingPeopleId() {
		return influencingPeopleId;
	}

	public Long getBoothId() {
		return boothId;
	}

	public void setBoothId(Long boothId) {
		this.boothId = boothId;
	}
	
	
	
}
