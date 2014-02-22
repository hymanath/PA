package com.itgrids.partyanalyst.dto;

import java.util.List;

public class HouseHoldVotersVO {
	
	private Long voterId;
	private Long voterFamilyRelationId;
	private Long educationId;
	private Long occupationId;
	private Long socialPstnId;
	
	private List<HouseHoldVotersVO> houseHoldsVoters;
	private Long boothId;
	private String houseNo;
	
	private Long age;
	private String name;
	private String relativeName;
	private String relationShipType;
	private String gender;
	private boolean newPerson;
	private String mobileNo;
	private Long houseHoldsId;
	private Long houseHoldFamilyMemberId = 0L;
	
	public Long getHouseHoldFamilyMemberId() {
		return houseHoldFamilyMemberId;
	}
	public void setHouseHoldFamilyMemberId(Long houseHoldFamilyMemberId) {
		this.houseHoldFamilyMemberId = houseHoldFamilyMemberId;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public boolean isNewPerson() {
		return newPerson;
	}
	public void setNewPerson(boolean newPerson) {
		this.newPerson = newPerson;
	}
	public Long getAge() {
		return age;
	}
	public void setAge(Long age) {
		this.age = age;
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
	public String getRelationShipType() {
		return relationShipType;
	}
	public void setRelationShipType(String relationShipType) {
		this.relationShipType = relationShipType;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
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
	public List<HouseHoldVotersVO> getHouseHoldsVoters() {
		return houseHoldsVoters;
	}
	public void setHouseHoldsVoters(List<HouseHoldVotersVO> houseHoldsVoters) {
		this.houseHoldsVoters = houseHoldsVoters;
	}
	public Long getVoterId() {
		return voterId;
	}
	public void setVoterId(Long voterId) {
		this.voterId = voterId;
	}
	public Long getVoterFamilyRelationId() {
		return voterFamilyRelationId;
	}
	public void setVoterFamilyRelationId(Long voterFamilyRelationId) {
		this.voterFamilyRelationId = voterFamilyRelationId;
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
	public Long getSocialPstnId() {
		return socialPstnId;
	}
	public void setSocialPstnId(Long socialPstnId) {
		this.socialPstnId = socialPstnId;
	}
	
	public Long getHouseHoldsId() {
		return houseHoldsId;
	}
	public void setHouseHoldsId(Long houseHoldsId) {
		this.houseHoldsId = houseHoldsId;
	}
}
