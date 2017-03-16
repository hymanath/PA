package com.itgrids.partyanalyst.dto;

import java.io.Serializable;

public class LocationsVO implements Serializable {
	
	private Long districtId;
	private String districtName;
	private Long constId;
	private String constName;
	private Long parConstId;
	private String parConstName;
	private String areaType;
	private Long tehsilId;
	private Long locatElectBodyId;
	private Long boothId;
	private Long panchayatId;
	private Long stateId;
	private Long postMemberId;
	private Long wardId;
	
	
	public Long getWardId() {
		return wardId;
	}
	public void setWardId(Long wardId) {
		this.wardId = wardId;
	}
	public Long getDistrictId() {
		return districtId;
	}
	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}
	public String getDistrictName() {
		return districtName;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	public Long getConstId() {
		return constId;
	}
	public void setConstId(Long constId) {
		this.constId = constId;
	}
	public String getConstName() {
		return constName;
	}
	public void setConstName(String constName) {
		this.constName = constName;
	}
	public Long getParConstId() {
		return parConstId;
	}
	public void setParConstId(Long parConstId) {
		this.parConstId = parConstId;
	}
	public String getParConstName() {
		return parConstName;
	}
	public void setParConstName(String parConstName) {
		this.parConstName = parConstName;
	}
	public String getAreaType() {
		return areaType;
	}
	public void setAreaType(String areaType) {
		this.areaType = areaType;
	}
	public Long getTehsilId() {
		return tehsilId;
	}
	public void setTehsilId(Long tehsilId) {
		this.tehsilId = tehsilId;
	}
	public Long getLocatElectBodyId() {
		return locatElectBodyId;
	}
	public void setLocatElectBodyId(Long locatElectBodyId) {
		this.locatElectBodyId = locatElectBodyId;
	}
	public Long getBoothId() {
		return boothId;
	}
	public void setBoothId(Long boothId) {
		this.boothId = boothId;
	}
	public Long getPanchayatId() {
		return panchayatId;
	}
	public void setPanchayatId(Long panchayatId) {
		this.panchayatId = panchayatId;
	}
	public Long getStateId() {
		return stateId;
	}
	public void setStateId(Long stateId) {
		this.stateId = stateId;
	}
	public Long getPostMemberId() {
		return postMemberId;
	}
	public void setPostMemberId(Long postMemberId) {
		this.postMemberId = postMemberId;
	}
	
}
