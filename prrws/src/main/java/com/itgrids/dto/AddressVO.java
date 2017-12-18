package com.itgrids.dto;

import java.util.ArrayList;
import java.util.List;

public class AddressVO implements java.io.Serializable{

	private Long id;
	private String name;
	
	private String street;
	private String landmark;
	private String houseNo;
	private Long count;
	private Long totalCount;
	
	private Long countryId;
	private String countryName;
	
	private Long stateId;
	private String stateName;
	
	private Long districtId;
	private String districtName;
	
	private Long parliamentId;
	private String parliamentName;
	
	private Long assemblyId;
	private String assemblyName;
	
	private Long tehsilId;
	private String tehsilName;
	
	private Long localElectionBodyId;
	private String localElectionBodyName;
	
	private Long panchayatId;
	private String panchayatName;
	
	private Long wardId;
	private String wardName;
	
	private Long hamletId;
	private String hamletName;
	
	private String locationIdStr;
	private String panchayatIdStr;
	private String tehsilIdStr;
	private String assemblyIdStr;
	
	private Long divisionId;
	private String divisionName;
	
	List<KeyValueVO> constituencyList = new ArrayList<KeyValueVO>(0);
	List<KeyValueVO> mandalsList = new ArrayList<KeyValueVO>(0);
	List<KeyValueVO> panchaytsList = new ArrayList<KeyValueVO>(0);
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getLandmark() {
		return landmark;
	}
	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}
	public String getHouseNo() {
		return houseNo;
	}
	public void setHouseNo(String houseNo) {
		this.houseNo = houseNo;
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	public Long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	public Long getCountryId() {
		return countryId;
	}
	public void setCountryId(Long countryId) {
		this.countryId = countryId;
	}
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	public Long getStateId() {
		return stateId;
	}
	public void setStateId(Long stateId) {
		this.stateId = stateId;
	}
	public String getStateName() {
		return stateName;
	}
	public void setStateName(String stateName) {
		this.stateName = stateName;
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
	public Long getParliamentId() {
		return parliamentId;
	}
	public void setParliamentId(Long parliamentId) {
		this.parliamentId = parliamentId;
	}
	public String getParliamentName() {
		return parliamentName;
	}
	public void setParliamentName(String parliamentName) {
		this.parliamentName = parliamentName;
	}
	public Long getAssemblyId() {
		return assemblyId;
	}
	public void setAssemblyId(Long assemblyId) {
		this.assemblyId = assemblyId;
	}
	public String getAssemblyName() {
		return assemblyName;
	}
	public void setAssemblyName(String assemblyName) {
		this.assemblyName = assemblyName;
	}
	public Long getTehsilId() {
		return tehsilId;
	}
	public void setTehsilId(Long tehsilId) {
		this.tehsilId = tehsilId;
	}
	public String getTehsilName() {
		return tehsilName;
	}
	public void setTehsilName(String tehsilName) {
		this.tehsilName = tehsilName;
	}
	public Long getLocalElectionBodyId() {
		return localElectionBodyId;
	}
	public void setLocalElectionBodyId(Long localElectionBodyId) {
		this.localElectionBodyId = localElectionBodyId;
	}
	public String getLocalElectionBodyName() {
		return localElectionBodyName;
	}
	public void setLocalElectionBodyName(String localElectionBodyName) {
		this.localElectionBodyName = localElectionBodyName;
	}
	public Long getPanchayatId() {
		return panchayatId;
	}
	public void setPanchayatId(Long panchayatId) {
		this.panchayatId = panchayatId;
	}
	public String getPanchayatName() {
		return panchayatName;
	}
	public void setPanchayatName(String panchayatName) {
		this.panchayatName = panchayatName;
	}
	public Long getWardId() {
		return wardId;
	}
	public void setWardId(Long wardId) {
		this.wardId = wardId;
	}
	public String getWardName() {
		return wardName;
	}
	public void setWardName(String wardName) {
		this.wardName = wardName;
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
	public String getLocationIdStr() {
		return locationIdStr;
	}
	public void setLocationIdStr(String locationIdStr) {
		this.locationIdStr = locationIdStr;
	}
	public String getPanchayatIdStr() {
		return panchayatIdStr;
	}
	public void setPanchayatIdStr(String panchayatIdStr) {
		this.panchayatIdStr = panchayatIdStr;
	}
   public String getTehsilIdStr() {
		return tehsilIdStr;
	}
	public void setTehsilIdStr(String tehsilIdStr) {
		this.tehsilIdStr = tehsilIdStr;
	}
	public String getAssemblyIdStr() {
		return assemblyIdStr;
	}
	public void setAssemblyIdStr(String assemblyIdStr) {
		this.assemblyIdStr = assemblyIdStr;
	}
	public Long getDivisionId() {
		return divisionId;
	}
	public void setDivisionId(Long divisionId) {
		this.divisionId = divisionId;
	}
	public String getDivisionName() {
		return divisionName;
	}
	public void setDivisionName(String divisionName) {
		this.divisionName = divisionName;
	}
	public List<KeyValueVO> getConstituencyList() {
		return constituencyList;
	}
	public void setConstituencyList(List<KeyValueVO> constituencyList) {
		this.constituencyList = constituencyList;
	}
	public List<KeyValueVO> getMandalsList() {
		return mandalsList;
	}
	public void setMandalsList(List<KeyValueVO> mandalsList) {
		this.mandalsList = mandalsList;
	}
	public List<KeyValueVO> getPanchaytsList() {
		return panchaytsList;
	}
	public void setPanchaytsList(List<KeyValueVO> panchaytsList) {
		this.panchaytsList = panchaytsList;
	}
}
