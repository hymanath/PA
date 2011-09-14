package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.List;

public class UserAccessRegionVO implements Serializable {
    private Long userId,userCountryAccessId,countryId,userStateAccessId,stateId,userDistrictAccessId,districtId,userConstituecyAccessId,constituencyId;
    private String countryName,stateName,districtName,constituencyName,electionType,message;	
	private List<UserAccessRegionVO> country;
	private List<UserAccessRegionVO> state;
	private List<UserAccessRegionVO> district;
	private List<UserAccessRegionVO> constituency;
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getUserCountryAccessId() {
		return userCountryAccessId;
	}
	public void setUserCountryAccessId(Long userCountryAccessId) {
		this.userCountryAccessId = userCountryAccessId;
	}
	public Long getCountryId() {
		return countryId;
	}
	public void setCountryId(Long countryId) {
		this.countryId = countryId;
	}
	public Long getUserStateAccessId() {
		return userStateAccessId;
	}
	public void setUserStateAccessId(Long userStateAccessId) {
		this.userStateAccessId = userStateAccessId;
	}
	public Long getStateId() {
		return stateId;
	}
	public void setStateId(Long stateId) {
		this.stateId = stateId;
	}
	public Long getUserDistrictAccessId() {
		return userDistrictAccessId;
	}
	public void setUserDistrictAccessId(Long userDistrictAccessId) {
		this.userDistrictAccessId = userDistrictAccessId;
	}
	public Long getDistrictId() {
		return districtId;
	}
	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}
	public Long getUserConstituecyAccessId() {
		return userConstituecyAccessId;
	}
	public void setUserConstituecyAccessId(Long userConstituecyAccessId) {
		this.userConstituecyAccessId = userConstituecyAccessId;
	}
	public Long getConstituencyId() {
		return constituencyId;
	}
	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	public String getStateName() {
		return stateName;
	}
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	public String getDistrictName() {
		return districtName;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	public String getConstituencyName() {
		return constituencyName;
	}
	public void setConstituencyName(String constituencyName) {
		this.constituencyName = constituencyName;
	}
	public List<UserAccessRegionVO> getCountry() {
		return country;
	}
	public void setCountry(List<UserAccessRegionVO> country) {
		this.country = country;
	}
	public List<UserAccessRegionVO> getState() {
		return state;
	}
	public void setState(List<UserAccessRegionVO> state) {
		this.state = state;
	}
	public List<UserAccessRegionVO> getDistrict() {
		return district;
	}
	public void setDistrict(List<UserAccessRegionVO> district) {
		this.district = district;
	}
	public List<UserAccessRegionVO> getConstituency() {
		return constituency;
	}
	public void setConstituency(List<UserAccessRegionVO> constituency) {
		this.constituency = constituency;
	}
	
	public String getElectionType() {
		return electionType;
	}
	public void setElectionType(String electionType) {
		this.electionType = electionType;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
