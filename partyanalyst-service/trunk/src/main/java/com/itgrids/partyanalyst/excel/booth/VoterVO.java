package com.itgrids.partyanalyst.excel.booth;


public class VoterVO {
	
	private String firstName;
	private String voterMiddleName;
	private String voterLastName;
	private String houseNo;
	private String relationshipType;
	private String relativeFirstName;
	private String relativeMiddleName;
	private String relativeLastName;
	private String townShip;
	private String hamlet;
	private String localArea;
	private String cast;
	private String castCatagery;
	private String castSubCatagery;
	private String voterIDCardNo;
	private String gender;
	private Long age;
	private String voterId;
	private Long totalVoters;
	
	public VoterVO(){
		
	}

	public VoterVO(String firstName, String voterMiddleName,
			String voterLastName, String houseNo, String relationshipType,
			String relativeFirstName, String relativeMiddleName,
			String relativeLastName, String townShip, String hamlet, String localArea,
			String cast, String castCatagery, String castSubCatagery,
			String voterIDCardNo, String gender, Long age) {
		this.firstName = firstName;
		this.voterMiddleName = voterMiddleName;
		this.voterLastName = voterLastName;
		this.houseNo = houseNo;
		this.relationshipType = relationshipType;
		this.relativeFirstName = relativeFirstName;
		this.relativeMiddleName = relativeMiddleName;
		this.relativeLastName = relativeLastName;
		this.townShip = townShip;
		this.hamlet = hamlet;
		this.localArea = localArea;
		this.cast = cast;
		this.castCatagery = castCatagery;
		this.castSubCatagery = castSubCatagery;
		this.voterIDCardNo = voterIDCardNo;
		this.gender = gender;
		this.age = age;
	}

	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getVoterMiddleName() {
		return voterMiddleName;
	}

	public void setVoterMiddleName(String voterMiddleName) {
		this.voterMiddleName = voterMiddleName;
	}

	public String getVoterLastName() {
		return voterLastName;
	}

	public void setVoterLastName(String voterLastName) {
		this.voterLastName = voterLastName;
	}

	public String getHouseNo() {
		return houseNo;
	}

	public void setHouseNo(String houseNo) {
		this.houseNo = houseNo;
	}

	public String getRelationshipType() {
		return relationshipType;
	}

	public void setRelationshipType(String relationshipType) {
		this.relationshipType = relationshipType;
	}

	public String getRelativeFirstName() {
		return relativeFirstName;
	}

	public void setRelativeFirstName(String relativeFirstName) {
		this.relativeFirstName = relativeFirstName;
	}

	public String getRelativeMiddleName() {
		return relativeMiddleName;
	}

	public void setRelativeMiddleName(String relativeMiddleName) {
		this.relativeMiddleName = relativeMiddleName;
	}

	public String getRelativeLastName() {
		return relativeLastName;
	}

	public void setRelativeLastName(String relativeLastName) {
		this.relativeLastName = relativeLastName;
	}

	public String getTownShip() {
		return townShip;
	}

	public void setTownShip(String townShip) {
		this.townShip = townShip;
	}

	public String getHamlet() {
		return hamlet;
	}

	public void setHamlet(String hamlet) {
		this.hamlet = hamlet;
	}

	public String getCast() {
		return cast;
	}

	public void setCast(String cast) {
		this.cast = cast;
	}

	public String getVoterId() {
		return voterId;
	}

	public void setVoterId(String voterId) {
		this.voterId = voterId;
	}

	public String getCastCatagery() {
		return castCatagery;
	}

	public void setCastCatagery(String castCatagery) {
		this.castCatagery = castCatagery;
	}

	public String getCastSubCatagery() {
		return castSubCatagery;
	}

	public void setCastSubCatagery(String castSubCatagery) {
		this.castSubCatagery = castSubCatagery;
	}

	public String getVoterIDCardNo() {
		return voterIDCardNo;
	}

	public void setVoterIDCardNo(String voterIDCardNo) {
		this.voterIDCardNo = voterIDCardNo;
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

	public String getLocalArea() {
		return localArea;
	}

	public void setLocalArea(String localArea) {
		this.localArea = localArea;
	}

	public Long getTotalVoters() {
		return totalVoters;
	}

	public void setTotalVoters(Long totalVoters) {
		this.totalVoters = totalVoters;
	}
	
}
