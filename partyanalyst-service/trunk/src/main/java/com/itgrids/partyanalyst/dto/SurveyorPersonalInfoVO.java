package com.itgrids.partyanalyst.dto;

public class SurveyorPersonalInfoVO {
	private String name;
	private String mobileNumber;
	private String phoneNumber;
	private String age;
	private String email;
	private int qualification;
	private int occupation;
	private int caste;
	private String gender;
	
	private Long state;
	private Long district;
	private Long tehsil;
	private Long township;
	private Long village;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
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
	public int getQualification() {
		return qualification;
	}
	public void setQualification(int qualification) {
		this.qualification = qualification;
	}
	public int getOccupation() {
		return occupation;
	}
	public void setOccupation(int occupation) {
		this.occupation = occupation;
	}
	public int getCaste() {
		return caste;
	}
	public void setCaste(int caste) {
		this.caste = caste;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public Long getState() {
		return state;
	}
	public void setState(Long state) {
		this.state = state;
	}
	public Long getDistrict() {
		return district;
	}
	public void setDistrict(Long district) {
		this.district = district;
	}
	public Long getTehsil() {
		return tehsil;
	}
	public void setTehsil(Long tehsil) {
		this.tehsil = tehsil;
	}
	public Long getVillage() {
		return village;
	}
	public void setVillage(Long village) {
		this.village = village;
	}
	public Long getTownship() {
		return township;
	}
	public void setTownship(Long township) {
		this.township = township;
	}
			
	
}
