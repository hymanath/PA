package com.itgrids.partyanalyst.dto;

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

	
	
}
