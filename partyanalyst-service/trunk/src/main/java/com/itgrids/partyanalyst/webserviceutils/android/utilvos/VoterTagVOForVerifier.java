package com.itgrids.partyanalyst.webserviceutils.android.utilvos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.itgrids.partyanalyst.webservice.utils.VoterTagVO;

public class VoterTagVOForVerifier extends UserResponseVO implements Serializable {

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
	private String name;
	private String constituency;
	private Long hamletId;
	private String hamletName;
	private String localAreaName;
	private String dataTypeId;
	
	



	public String getDataTypeId() {
		return dataTypeId;
	}
	public void setDataTypeId(String dataTypeId) {
		this.dataTypeId = dataTypeId;
	}
	public Long getHamletId() {
		return hamletId;
	}
	public void setHamletId(Long hamletId) {
		this.hamletId = hamletId;
	}
	public String getHamletName() {
		return hamletName;
	}
	public void setHamletName(String hamletName) {
		this.hamletName = hamletName;
	}
	public String getLocalAreaName() {
		return localAreaName;
	}
	public void setLocalAreaName(String localAreaName) {
		this.localAreaName = localAreaName;
	}
	private String voterIdCardNo;
	private List<VoterTagVOForVerifier> votersData=new ArrayList<VoterTagVOForVerifier>(1);

	public List<VoterTagVOForVerifier> getVotersData() {
		return votersData;
	}
	public void setVotersData(List<VoterTagVOForVerifier> votersData) {
		this.votersData = votersData;
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


	
	public String getVoterIdCardNo() {
		return voterIdCardNo;
	}
	public void setVoterIdCardNo(String voterIdCardNo) {
		this.voterIdCardNo = voterIdCardNo;
	}
	

}
