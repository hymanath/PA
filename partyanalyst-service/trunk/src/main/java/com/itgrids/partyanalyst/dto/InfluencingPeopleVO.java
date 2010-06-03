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
	private String position;
	private Long hamletId;
	
	public String getParty() {
		return party;
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

	public void setInfluencingPeopleId(Long influencingPeopleId) {
		this.influencingPeopleId = influencingPeopleId;
	}

	public Long getInfluencingPeopleId() {
		return influencingPeopleId;
	}
	
	
}
