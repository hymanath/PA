package com.itgrids.partyanalyst.dto;

import java.io.Serializable;

public class CadreInfo implements Serializable{

	private String firstName;
	private String middleName;
	private String lastName;
	private String mobileNo;
	private String landLineNo;

	private String email;
	private String gender;
	private String cadreGroupName;
	private Boolean defaultGroup;
	private String cadreLevel;
	private String state;
	private String district;
	private String mandal;
	private String village;
	private String booth;
	
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
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
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getLandLineNo() {
		return landLineNo;
	}
	public void setLandLineNo(String landLineNo) {
		this.landLineNo = landLineNo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getCadreGroupName() {
		return cadreGroupName;
	}
	public void setCadreGroupName(String cadreGroupName) {
		this.cadreGroupName = cadreGroupName;
	}
	public Boolean getDefaultGroup() {
		return defaultGroup;
	}
	public void setDefaultGroup(Boolean defaultGroup) {
		this.defaultGroup = defaultGroup;
	}
	public String getCadreLevel() {
		return cadreLevel;
	}
	public void setCadreLevel(String cadreLevel) {
		this.cadreLevel = cadreLevel;
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
	public String getMandal() {
		return mandal;
	}
	public void setMandal(String mandal) {
		this.mandal = mandal;
	}
	public String getVillage() {
		return village;
	}
	public void setVillage(String village) {
		this.village = village;
	}
	public String getBooth() {
		return booth;
	}
	public void setBooth(String booth) {
		this.booth = booth;
	}
}
