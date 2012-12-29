package com.itgrids.partyanalyst.dto;

import java.util.List;

public class VoterHouseInfoVO {

	private String houseNo;
	private int numberOfPeople;
	private String cast;
	private String elder;
	private String younger;
	private Long totalHousesCount; 
	private Long boothId;
	private Long sNo;
	private String name;
	private String gender;
	private Long age;
	private String gaurdian;
	private String relationship;
	private String castCategory;
	private Long voterId;
	private Long partyId;
	private Long userVoterDetailsId;
	private Long userId;
	private String boothName;
	private String villiageCovered;
	private String panchayatName;
	private List<SelectOptionVO> casteGroupNameList;
	private Long userCategoryValuesId;
	private Long userCategoryValuesId1;
	private String userCategoryValuesName;
	private String setValue;
	private Long voterCategoryValuesId;
	private Long categoryValuesId;
	private String youngerGender;
	private Long youngerAge;
	private String elderGender;
	private Long elderAge;
	
	public VoterHouseInfoVO(){
		
	}

	public VoterHouseInfoVO(String houseNo, int numberOfPeople, String cast,
			String elder, String younger) {
		this.houseNo = houseNo;
		this.numberOfPeople = numberOfPeople;
		this.cast = cast;
		this.elder = elder;
		this.younger = younger;
	}

	public VoterHouseInfoVO(Long sNo, String name){
		this.sNo = sNo;
		this.name = name;
	}



	


	public Long getUserCategoryValuesId1() {
		return userCategoryValuesId1;
	}

	public void setUserCategoryValuesId1(Long userCategoryValuesId1) {
		this.userCategoryValuesId1 = userCategoryValuesId1;
	}

	public Long getCategoryValuesId() {
		return categoryValuesId;
	}

	public void setCategoryValuesId(Long categoryValuesId) {
		this.categoryValuesId = categoryValuesId;
	}

	public Long getVoterCategoryValuesId() {
		return voterCategoryValuesId;
	}

	public void setVoterCategoryValuesId(Long voterCategoryValuesId) {
		this.voterCategoryValuesId = voterCategoryValuesId;
	}

	public String getSetValue() {
		return setValue;
	}

	public void setSetValue(String setValue) {
		this.setValue = setValue;
	}

	public Long getUserCategoryValuesId() {
		return userCategoryValuesId;
	}

	public void setUserCategoryValuesId(Long userCategoryValuesId) {
		this.userCategoryValuesId = userCategoryValuesId;
	}

	public String getUserCategoryValuesName() {
		return userCategoryValuesName;
	}

	public void setUserCategoryValuesName(String userCategoryValuesName) {
		this.userCategoryValuesName = userCategoryValuesName;
	}

	public List<SelectOptionVO> getCasteGroupNameList() {
		return casteGroupNameList;
	}

	public void setCasteGroupNameList(List<SelectOptionVO> casteGroupNameList) {
		this.casteGroupNameList = casteGroupNameList;
	}

	public String getBoothName() {
		return boothName;
	}

	public void setBoothName(String boothName) {
		this.boothName = boothName;
	}

	public String getVilliageCovered() {
		return villiageCovered;
	}

	public void setVilliageCovered(String villiageCovered) {
		this.villiageCovered = villiageCovered;
	}

	public String getPanchayatName() {
		return panchayatName;
	}

	public void setPanchayatName(String panchayatName) {
		this.panchayatName = panchayatName;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getUserVoterDetailsId() {
		return userVoterDetailsId;
	}

	public void setUserVoterDetailsId(Long userVoterDetailsId) {
		this.userVoterDetailsId = userVoterDetailsId;
	}

	public Long getPartyId() {
		return partyId;
	}

	public void setPartyId(Long partyId) {
		this.partyId = partyId;
	}

	public String getHouseNo() {
		return houseNo;
	}

	public void setHouseNo(String houseNo) {
		this.houseNo = houseNo;
	}

	public int getNumberOfPeople() {
		return numberOfPeople;
	}

	public void setNumberOfPeople(int numberOfPeople) {
		this.numberOfPeople = numberOfPeople;
	}

	public String getCast() {
		return cast;
	}

	public void setCast(String cast) {
		this.cast = cast;
	}

	public String getElder() {
		return elder;
	}

	public void setElder(String elder) {
		this.elder = elder;
	}

	public String getYounger() {
		return younger;
	}

	public void setYounger(String younger) {
		this.younger = younger;
	}

	public Long getTotalHousesCount() {
		return totalHousesCount;
	}

	public void setTotalHousesCount(Long totalHousesCount) {
		this.totalHousesCount = totalHousesCount;
	}

	public Long getBoothId() {
		return boothId;
	}

	public void setBoothId(Long boothId) {
		this.boothId = boothId;
	}

	public Long getsNo() {
		return sNo;
	}

	public void setsNo(Long sNo) {
		this.sNo = sNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getGaurdian() {
		return gaurdian;
	}

	public void setGaurdian(String gaurdian) {
		this.gaurdian = gaurdian;
	}

	public String getRelationship() {
		return relationship;
	}

	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}

	public String getCastCategory() {
		return castCategory;
	}

	public void setCastCategory(String castCategory) {
		this.castCategory = castCategory;
	}
	
	public Long getVoterId() {
		return voterId;
	}

	public void setVoterId(Long voterId) {
		this.voterId = voterId;
	}

	public String getYoungerGender() {
		return youngerGender;
	}

	public void setYoungerGender(String youngerGender) {
		this.youngerGender = youngerGender;
	}

	public Long getYoungerAge() {
		return youngerAge;
	}

	public void setYoungerAge(Long youngerAge) {
		this.youngerAge = youngerAge;
	}

	public String getElderGender() {
		return elderGender;
	}

	public void setElderGender(String elderGender) {
		this.elderGender = elderGender;
	}

	public Long getElderAge() {
		return elderAge;
	}

	public void setElderAge(Long elderAge) {
		this.elderAge = elderAge;
	}

	
	
}
