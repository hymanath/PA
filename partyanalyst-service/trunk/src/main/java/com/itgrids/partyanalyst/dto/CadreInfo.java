package com.itgrids.partyanalyst.dto;

import java.io.Serializable;

public class CadreInfo implements Serializable{
	private Long cadreID;
	private String firstName;
	private String middleName;
	private String lastName;
	private String mobile;
	private String landLineNo;

	private String email;
	private String gender;
	private Long cadreLevel;
	private Long cadreLevelValue;
	private String strCadreLevel;
	private String strCadreLevelValue;
	private String state;
	private String district;
	private String mandal;
	private String village;
	private String booth;
	private Long userID;
	
	public Long getCadreID() {
		return cadreID;
	}
	public void setCadreID(Long cadreID) {
		this.cadreID = cadreID;
	}
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
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
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
	public Long getCadreLevel() {
		return cadreLevel;
	}
	public void setCadreLevel(Long cadreLevel) {
		this.cadreLevel = cadreLevel;
	}
	public Long getCadreLevelValue() {
		return cadreLevelValue;
	}
	public void setCadreLevelValue(Long cadreLevelValue) {
		this.cadreLevelValue = cadreLevelValue;
	}
	
	public String getStrCadreLevel() {
		return strCadreLevel;
	}
	public void setStrCadreLevel(String strCadreLevel) {
		this.strCadreLevel = strCadreLevel;
	}
	public String getStrCadreLevelValue() {
		return strCadreLevelValue;
	}
	public void setStrCadreLevelValue(String strCadreLevelValue) {
		this.strCadreLevelValue = strCadreLevelValue;
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
	
	public Long getUserID() {
		return userID;
	}
	public void setuserID(Long userID) {
		this.userID = userID;
	}
	
}
