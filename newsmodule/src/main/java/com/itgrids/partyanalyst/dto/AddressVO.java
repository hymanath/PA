package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.List;

public class AddressVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long countryId;
	private String countryName;
	private Long stateId;
	private String stateName;
	private Long districtId;
	private String districtName;
	private Long constituencyId;
	private String constituencyName;
	private Long tehsilId;
	private String tehsilName;
	//wantedly declared as string- usage in cadre registration
	private String townshipId;
	private String townshipName;
	private Long hamletId;
	private String hamletName;
	private String houseNo;
	private String street;
	private String pinCode;
	
	
	private Long mandalId;
	private String mandalName;
	private Long addressTypeId;
	private String address1;
	private String address2;
	private Long pincode;
	private String city;

	//private List<SocialNetworkVO> phoneList;
	private List<SelectOptionVO> districtList ;
	private List<SelectOptionVO> tehsilList ;
	private List<SelectOptionVO> addressTypeList;
	
	private Long addressId;
	private Long candidateAddressId;
	private Long addressContactId;

	private Long voterCategoryValuesId;
	private String voterCategoryValuesName;
	private String categoryValue;
	
	
	public Long getVoterCategoryValuesId() {
		return voterCategoryValuesId;
	}
	public void setVoterCategoryValuesId(Long voterCategoryValuesId) {
		this.voterCategoryValuesId = voterCategoryValuesId;
	}
	public String getVoterCategoryValuesName() {
		return voterCategoryValuesName;
	}
	public void setVoterCategoryValuesName(String voterCategoryValuesName) {
		this.voterCategoryValuesName = voterCategoryValuesName;
	}
	public String getCategoryValue() {
		return categoryValue;
	}
	public void setCategoryValue(String categoryValue) {
		this.categoryValue = categoryValue;
	}
	public Long getAddressContactId() {
		return addressContactId;
	}
	public void setAddressContactId(Long addressContactId) {
		this.addressContactId = addressContactId;
	}
	public Long getCandidateAddressId() {
		return candidateAddressId;
	}
	public void setCandidateAddressId(Long candidateAddressId) {
		this.candidateAddressId = candidateAddressId;
	}
	public Long getAddressId() {
		return addressId;
	}
	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}
	public List<SelectOptionVO> getAddressTypeList() {
		return addressTypeList;
	}
	public void setAddressTypeList(List<SelectOptionVO> addressTypeList) {
		this.addressTypeList = addressTypeList;
	}
	public List<SelectOptionVO> getTehsilList() {
		return tehsilList;
	}
	public void setTehsilList(List<SelectOptionVO> tehsilList) {
		this.tehsilList = tehsilList;
	}
	public List<SelectOptionVO> getDistrictList() {
		return districtList;
	}
	public void setDistrictList(List<SelectOptionVO> districtList) {
		this.districtList = districtList;
	}
	/*public List<SocialNetworkVO> getPhoneList() {
		return phoneList;
	}
	public void setPhoneList(List<SocialNetworkVO> phoneList) {
		this.phoneList = phoneList;
	}*/
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
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
	public Long getConstituencyId() {
		return constituencyId;
	}
	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}
	public String getConstituencyName() {
		return constituencyName;
	}
	public void setConstituencyName(String constituencyName) {
		this.constituencyName = constituencyName;
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
	public String getTownshipId() {
		return townshipId;
	}
	public void setTownshipId(String townshipId) {
		this.townshipId = townshipId;
	}
	public String getTownshipName() {
		return townshipName;
	}
	public void setTownshipName(String townshipName) {
		this.townshipName = townshipName;
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
	public String getPinCode() {
		return pinCode;
	}
	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}
	public Long getMandalId() {
		return mandalId;
	}
	public void setMandalId(Long mandalId) {
		this.mandalId = mandalId;
	}
	public String getMandalName() {
		return mandalName;
	}
	public void setMandalName(String mandalName) {
		this.mandalName = mandalName;
	}

	public Long getAddressTypeId() {
		return addressTypeId;
	}
	public void setAddressTypeId(Long addressTypeId) {
		this.addressTypeId = addressTypeId;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public Long getPincode() {
		return pincode;
	}
	public void setPincode(Long pincode) {
		this.pincode = pincode;
	}
	
	

}
