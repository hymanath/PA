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

}
