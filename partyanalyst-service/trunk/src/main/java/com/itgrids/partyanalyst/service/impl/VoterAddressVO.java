package com.itgrids.partyanalyst.service.impl;

import java.io.Serializable;
import java.util.List;

import com.itgrids.partyanalyst.dto.IdNameVO;
import com.itgrids.partyanalyst.dto.LocationWiseBoothDetailsVO;

public class VoterAddressVO implements Serializable{
     
	private Long districtId;
	private String districtName;
	private Long parliamentId;
	private String parliamentName;
	private Long constituencyId;
	private String constituencyName;
	private Long tehsilId;
	private String tehsilName;
	private Long localElectionBodyId;
	private String localElectionBodyName;
	private Long villageId;
	private String villageName;
	private Long wardId;
	private String wardName;
	private Long boothId;
	private String boothName;
	private List<IdNameVO> constList;
	private List<LocationWiseBoothDetailsVO> tehLebDivList;
	private List<LocationWiseBoothDetailsVO> villWardList;
	private String houseNo;
	private String street;
	private String landMark;
	private String pincode;
	
	private Long locationScopeId;
	
	public String getDistrictName() {
		return districtName;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	public String getParliamentName() {
		return parliamentName;
	}
	public void setParliamentName(String parliamentName) {
		this.parliamentName = parliamentName;
	}
	public String getConstituencyName() {
		return constituencyName;
	}
	public void setConstituencyName(String constituencyName) {
		this.constituencyName = constituencyName;
	}
	public String getTehsilName() {
		return tehsilName;
	}
	public void setTehsilName(String tehsilName) {
		this.tehsilName = tehsilName;
	}
	public String getLocalElectionBodyName() {
		return localElectionBodyName;
	}
	public void setLocalElectionBodyName(String localElectionBodyName) {
		this.localElectionBodyName = localElectionBodyName;
	}
	public String getVillageName() {
		return villageName;
	}
	public void setVillageName(String villageName) {
		this.villageName = villageName;
	}
	public String getWardName() {
		return wardName;
	}
	public void setWardName(String wardName) {
		this.wardName = wardName;
	}
	public String getBoothName() {
		return boothName;
	}
	public void setBoothName(String boothName) {
		this.boothName = boothName;
	}
	public Long getParliamentId() {
		return parliamentId;
	}
	public void setParliamentId(Long parliamentId) {
		this.parliamentId = parliamentId;
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
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getLandMark() {
		return landMark;
	}
	public void setLandMark(String landMark) {
		this.landMark = landMark;
	}
	public Long getDistrictId() {
		return districtId;
	}
	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}
	public Long getConstituencyId() {
		return constituencyId;
	}
	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}
	public Long getTehsilId() {
		return tehsilId;
	}
	public void setTehsilId(Long tehsilId) {
		this.tehsilId = tehsilId;
	}
	public Long getLocalElectionBodyId() {
		return localElectionBodyId;
	}
	public void setLocalElectionBodyId(Long localElectionBodyId) {
		this.localElectionBodyId = localElectionBodyId;
	}
	public Long getVillageId() {
		return villageId;
	}
	public void setVillageId(Long villageId) {
		this.villageId = villageId;
	}
	public Long getWardId() {
		return wardId;
	}
	public void setWardId(Long wardId) {
		this.wardId = wardId;
	}
	public List<IdNameVO> getConstList() {
		return constList;
	}
	public void setConstList(List<IdNameVO> constList) {
		this.constList = constList;
	}
	public List<LocationWiseBoothDetailsVO> getTehLebDivList() {
		return tehLebDivList;
	}
	public void setTehLebDivList(List<LocationWiseBoothDetailsVO> tehLebDivList) {
		this.tehLebDivList = tehLebDivList;
	}
	public List<LocationWiseBoothDetailsVO> getVillWardList() {
		return villWardList;
	}
	public void setVillWardList(List<LocationWiseBoothDetailsVO> villWardList) {
		this.villWardList = villWardList;
	}
	public String getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	public Long getLocationScopeId() {
		return locationScopeId;
	}
	public void setLocationScopeId(Long locationScopeId) {
		this.locationScopeId = locationScopeId;
	}
    	
	
	
	
}
