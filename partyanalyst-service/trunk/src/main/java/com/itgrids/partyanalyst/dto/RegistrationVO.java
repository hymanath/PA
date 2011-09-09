/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on August 31, 2009
 */
package com.itgrids.partyanalyst.dto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class RegistrationVO extends BaseDTO{

	private static final long serialVersionUID = 1L;
	
	private Long registrationID;
	private String firstName;
	private String middleName;
	private String lastName;
	private String name;
	private String gender;
	private String userName;
	private String password;
	private String dateOfBirth;
	private String email;
	private String phone;
	private String mobile;
	private String address;
	private String country;
	private String state;
	private String district;
	private String constituency;
	
	private String pincode;
	private String accessType;
	private String accessValue;
	private Long party;
	private String partyShortName;
	private String subscribePartyImpDate;
	private String userType;
	private String userStatus; // contains wheather commercial user or free user
    private List<Long> profileOpts;
	private List<String> entitlements = new ArrayList<String>(0);
	private Set<SelectOptionVO> countries = new HashSet<SelectOptionVO>(0);
	private Set<SelectOptionVO> states = new HashSet<SelectOptionVO>(0);
	private Set<SelectOptionVO> districts = new HashSet<SelectOptionVO>(0);
	private Set<SelectOptionVO> parliaments = new HashSet<SelectOptionVO>(0);
	private Set<SelectOptionVO> assemblies = new HashSet<SelectOptionVO>(0);
	private Long parentUserId;
	private String userProfilePic;
	private String isAdmin;
	private Long mainAccountId;
	private Long districtId;

	public Long getDistrictId() {
		return districtId;
	}

	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}
	
	public Long getMainAccountId() {
		return mainAccountId;
	}

	public void setMainAccountId(Long mainAccountId) {
		this.mainAccountId = mainAccountId;
	}

	public String getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(String isAdmin) {
		this.isAdmin = isAdmin;
	}

	public String getUserProfilePic() {
		return userProfilePic;
	}

	public void setUserProfilePic(String userProfilePic) {
		this.userProfilePic = userProfilePic;
	}

	public Long getParentUserId() {
		return parentUserId;
	}

	public void setParentUserId(Long parentUserId) {
		this.parentUserId = parentUserId;
	}

	private ResultStatus resultStatus;
	
	public ResultStatus getResultStatus() {
		return resultStatus;
	}
	
	public void setResultStatus(ResultStatus resultStatus) {
		this.resultStatus = resultStatus;
	}
	
	public Long getRegistrationID() {
		return registrationID;
	}
	
	public void setRegistrationID(Long registrationID) {
		this.registrationID = registrationID;
	}
	
	public RegistrationVO() {
		
  	} 
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getConstituency() {
		return constituency;
	}
	public void setConstituency(String constituency) {
		this.constituency = constituency;
	}
	public String getMiddleName() {
		return middleName;
	}


	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getDateOfBirth() {
		return dateOfBirth;
	}


	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getMobile() {
		return mobile;
	}


	public void setMobile(String mobile) {
		this.mobile = mobile;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPincode() {
		return pincode;
	}


	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	 public String getAccessType() {
		return accessType;
	}

	public void setAccessType(String accessType) {
		this.accessType = accessType;
	}

	public String getAccessValue() {
		return accessValue;
	}

	public void setAccessValue(String accessValue) {
		this.accessValue = accessValue;
	}
	public Long getParty() {
		return party;
	}
	public void setParty(Long party) {
		this.party = party;
	}

	 public String getSubscribePartyImpDate() {
		return subscribePartyImpDate;
	}
	public void setSubscribePartyImpDate(String subscribePartyImpDate) {
		this.subscribePartyImpDate = subscribePartyImpDate;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public List<String> getEntitlements() {
		return entitlements;
	}
	public void setEntitlements(List<String> entitlements) {
		this.entitlements = entitlements;
	}
	
	public Set<SelectOptionVO> getCountries() {
		return countries;
	}
	public void setCountries(Set<SelectOptionVO> countries) {
		this.countries = countries;
	}
	public Set<SelectOptionVO> getStates() {
		return states;
	}
	public void setStates(Set<SelectOptionVO> states) {
		this.states = states;
	}
	public Set<SelectOptionVO> getDistricts() {
		return districts;
	}
	public void setDistricts(Set<SelectOptionVO> districts) {
		this.districts = districts;
	}
	public Set<SelectOptionVO> getParliaments() {
		return parliaments;
	}
	public void setParliaments(Set<SelectOptionVO> parliaments) {
		this.parliaments = parliaments;
	}
	public Set<SelectOptionVO> getAssemblies() {
		return assemblies;
	}
	public void setAssemblies(Set<SelectOptionVO> assemblies) {
		this.assemblies = assemblies;
	}
	public String getPartyShortName() {
		return partyShortName;
	}
	
	public void setPartyShortName(String partyShortName) {
		this.partyShortName = partyShortName;
	}
	public String getUserStatus() {
		return userStatus;
	}
	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}

	public List<Long> getProfileOpts() {
		return profileOpts;
	}

	public void setProfileOpts(List<Long> profileOpts) {
		this.profileOpts = profileOpts;
	}
	
}
