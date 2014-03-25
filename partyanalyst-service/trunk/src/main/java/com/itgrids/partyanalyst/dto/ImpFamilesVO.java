package com.itgrids.partyanalyst.dto;

import java.io.Serializable;

public class ImpFamilesVO implements Serializable{
	
	
	private static final long serialVersionUID = 5897555724979410783L;
	private Long id;
	private Long boothId;
	private String houseNo;
	private Long count;
	private String caste;
	private String elderPerson;
	private String eldPersomGender;
	private Long elderPersonAge;
	private String youngerPerson;
	private String youngPersomGender;
	private Long youngerPersonAge;
	private String panchayatName;
	private Long panchayatId;
	private String elderVoterId;
	private String youngerVoterId;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getBoothId() {
		return boothId;
	}
	public void setBoothId(Long boothId) {
		this.boothId = boothId;
	}
	public String getHouseNo() {
		return houseNo;
	}
	public void setHouseNo(String houseNo) {
		this.houseNo = houseNo;
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	public String getCaste() {
		return caste;
	}
	public void setCaste(String caste) {
		this.caste = caste;
	}
	public String getElderPerson() {
		return elderPerson;
	}
	public void setElderPerson(String elderPerson) {
		this.elderPerson = elderPerson;
	}
	public String getEldPersomGender() {
		return eldPersomGender;
	}
	public void setEldPersomGender(String eldPersomGender) {
		this.eldPersomGender = eldPersomGender;
	}
	public Long getElderPersonAge() {
		return elderPersonAge;
	}
	public void setElderPersonAge(Long elderPersonAge) {
		this.elderPersonAge = elderPersonAge;
	}
	public String getYoungerPerson() {
		return youngerPerson;
	}
	public void setYoungerPerson(String youngerPerson) {
		this.youngerPerson = youngerPerson;
	}
	public String getYoungPersomGender() {
		return youngPersomGender;
	}
	public void setYoungPersomGender(String youngPersomGender) {
		this.youngPersomGender = youngPersomGender;
	}
	public Long getYoungerPersonAge() {
		return youngerPersonAge;
	}
	public void setYoungerPersonAge(Long youngerPersonAge) {
		this.youngerPersonAge = youngerPersonAge;
	}
	public String getPanchayatName() {
		return panchayatName;
	}
	public void setPanchayatName(String panchayatName) {
		this.panchayatName = panchayatName;
	}
	public Long getPanchayatId() {
		return panchayatId;
	}
	public void setPanchayatId(Long panchayatId) {
		this.panchayatId = panchayatId;
	}
	public String getElderVoterId() {
		return elderVoterId;
	}
	public void setElderVoterId(String elderVoterId) {
		this.elderVoterId = elderVoterId;
	}
	public String getYoungerVoterId() {
		return youngerVoterId;
	}
	public void setYoungerVoterId(String youngerVoterId) {
		this.youngerVoterId = youngerVoterId;
	}
	
	
}
