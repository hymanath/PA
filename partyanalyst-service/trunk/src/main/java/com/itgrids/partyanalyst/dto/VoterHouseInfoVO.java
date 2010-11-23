package com.itgrids.partyanalyst.dto;

public class VoterHouseInfoVO {

	private String houseNo;
	private int numberOfPeople;
	private String cast;
	private String elder;
	private String younger;
	private Long totalHousesCount; 
	
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
	
	
}
