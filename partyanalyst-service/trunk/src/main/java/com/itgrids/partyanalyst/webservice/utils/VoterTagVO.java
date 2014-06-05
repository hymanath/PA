package com.itgrids.partyanalyst.webservice.utils;

import java.io.Serializable;

public class VoterTagVO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long voterId;
	private String uniqueCode;
	private String isCadre;
	private String isInfluencingPeople;
	private String tags;
	private String mobileNo;
	private Long partyId;
	private Long casteStateId;
	private String latitude;
	private String longitude;
	private String insertTime;
	private Long boothActivitiesId;
	private Long boothId;
	private Long totalTagged;
	private Long totalCadre;
	private Long totalInfluencePeople;
	private Long totalInserted;
	private Long cadreInserted;
	private Long influencePeopleInserted;
	private Long constituencyId;
	private String name;
	private String constituency;
	private String gender;
	private String age;
	private Long totalNotInserted;
	private Long cadreNotInserted;
	private Long influencePeopleNotInserted;
	private String voterIdCardNo;
	public Long getConstituencyId() {
		return constituencyId;
	}
	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getConstituency() {
		return constituency;
	}
	public void setConstituency(String constituency) {
		this.constituency = constituency;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public Long getBoothId() {
		return boothId;
	}
	public void setBoothId(Long boothId) {
		this.boothId = boothId;
	}

	public Long getVoterId() {
		return voterId;
	}
	public Long getBoothActivitiesId() {
		return boothActivitiesId;
	}
	public void setBoothActivitiesId(Long boothActivitiesId) {
		this.boothActivitiesId = boothActivitiesId;
	}
	public void setVoterId(Long voterId) {
		this.voterId = voterId;
	}
	public String getUniqueCode() {
		return uniqueCode;
	}
	public void setUniqueCode(String uniqueCode) {
		this.uniqueCode = uniqueCode;
	}
	public String getIsCadre() {
		return isCadre;
	}
	public void setIsCadre(String isCadre) {
		this.isCadre = isCadre;
	}
	public String getIsInfluencingPeople() {
		return isInfluencingPeople;
	}
	public void setIsInfluencingPeople(String isInfluencingPeople) {
		this.isInfluencingPeople = isInfluencingPeople;
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public Long getPartyId() {
		return partyId;
	}
	public void setPartyId(Long partyId) {
		this.partyId = partyId;
	}
	public Long getCasteStateId() {
		return casteStateId;
	}
	public void setCasteStateId(Long casteStateId) {
		this.casteStateId = casteStateId;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getInsertTime() {
		return insertTime;
	}
	public void setInsertTime(String insertTime) {
		this.insertTime = insertTime;
	}
	public Long getTotalTagged() {
		return totalTagged;
	}
	public void setTotalTagged(Long totalTagged) {
		this.totalTagged = totalTagged;
	}
	public Long getTotalCadre() {
		return totalCadre;
	}
	public void setTotalCadre(Long totalCadre) {
		this.totalCadre = totalCadre;
	}
	public Long getTotalInfluencePeople() {
		return totalInfluencePeople;
	}
	public void setTotalInfluencePeople(Long totalInfluencePeople) {
		this.totalInfluencePeople = totalInfluencePeople;
	}
	public Long getTotalInserted() {
		return totalInserted;
	}
	public void setTotalInserted(Long totalInserted) {
		this.totalInserted = totalInserted;
	}
	public Long getCadreInserted() {
		return cadreInserted;
	}
	public void setCadreInserted(Long cadreInserted) {
		this.cadreInserted = cadreInserted;
	}
	public Long getInfluencePeopleInserted() {
		return influencePeopleInserted;
	}
	public void setInfluencePeopleInserted(Long influencePeopleInserted) {
		this.influencePeopleInserted = influencePeopleInserted;
	}
	public Long getTotalNotInserted() {
		return totalNotInserted;
	}
	public void setTotalNotInserted(Long totalNotInserted) {
		this.totalNotInserted = totalNotInserted;
	}
	public Long getCadreNotInserted() {
		return cadreNotInserted;
	}
	public void setCadreNotInserted(Long cadreNotInserted) {
		this.cadreNotInserted = cadreNotInserted;
	}
	public Long getInfluencePeopleNotInserted() {
		return influencePeopleNotInserted;
	}
	public void setInfluencePeopleNotInserted(Long influencePeopleNotInserted) {
		this.influencePeopleNotInserted = influencePeopleNotInserted;
	}
	public String getVoterIdCardNo() {
		return voterIdCardNo;
	}
	public void setVoterIdCardNo(String voterIdCardNo) {
		this.voterIdCardNo = voterIdCardNo;
	}
	

}
