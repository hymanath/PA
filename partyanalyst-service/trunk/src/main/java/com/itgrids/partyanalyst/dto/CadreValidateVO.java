package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.List;

public class CadreValidateVO implements Serializable{
	
	private static final long serialVersionUID = -3127780706665858956L;
	
	private Long   tdpCadreId;
	private String memberShipId;
	private Long   voterId;
	private Long   familyVoterId;
	private String firstName;
	private Long   age;
	private String gender;
	private String mobileNo;
	private String image;
	private String voterName;
	private String cadreName;
	
	private String validationMessage;
	
	private List<CadreValidateVO> teluguNamesMissedList;
	private List<CadreValidateVO> specialCharactersList;
	private List<CadreValidateVO> imagesMissedList;
	
	public Long getTdpCadreId() {
		return tdpCadreId;
	}
	public void setTdpCadreId(Long tdpCadreId) {
		this.tdpCadreId = tdpCadreId;
	}
	public String getMemberShipId() {
		return memberShipId;
	}
	public void setMemberShipId(String memberShipId) {
		this.memberShipId = memberShipId;
	}
	public Long getVoterId() {
		return voterId;
	}
	public void setVoterId(Long voterId) {
		this.voterId = voterId;
	}
	public Long getFamilyVoterId() {
		return familyVoterId;
	}
	public void setFamilyVoterId(Long familyVoterId) {
		this.familyVoterId = familyVoterId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public Long getAge() {
		return age;
	}
	public void setAge(Long age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getVoterName() {
		return voterName;
	}
	public void setVoterName(String voterName) {
		this.voterName = voterName;
	}
	public String getCadreName() {
		return cadreName;
	}
	public void setCadreName(String cadreName) {
		this.cadreName = cadreName;
	}
	public List<CadreValidateVO> getTeluguNamesMissedList() {
		return teluguNamesMissedList;
	}
	public void setTeluguNamesMissedList(List<CadreValidateVO> teluguNamesMissedList) {
		this.teluguNamesMissedList = teluguNamesMissedList;
	}
	public List<CadreValidateVO> getSpecialCharactersList() {
		return specialCharactersList;
	}
	public void setSpecialCharactersList(List<CadreValidateVO> specialCharactersList) {
		this.specialCharactersList = specialCharactersList;
	}
	public List<CadreValidateVO> getImagesMissedList() {
		return imagesMissedList;
	}
	public void setImagesMissedList(List<CadreValidateVO> imagesMissedList) {
		this.imagesMissedList = imagesMissedList;
	}
	public String getValidationMessage() {
		return validationMessage;
	}
	public void setValidationMessage(String validationMessage) {
		this.validationMessage = validationMessage;
	}
	
	
}
