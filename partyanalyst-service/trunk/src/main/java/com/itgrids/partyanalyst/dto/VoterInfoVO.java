package com.itgrids.partyanalyst.dto;

import java.util.ArrayList;
import java.util.List;

public class VoterInfoVO implements java.io.Serializable
{

	private Long id;
	private String name;
	private String relativeName;
	private String relationType;
	private String age;
	private String gender;
	private String houseNo;
	private String dateOfBirth;
	private String voterCardNo;
	private Long blodGroupId;
	private String activeDate;
	private String location;
	private String casteName;
	private String mobileNo;
	private String education;
	private String occupation;
	private Long voterId;
	private Long cadreId;
	
	private List<VoterInfoVO> voterInfoVOList = new ArrayList<VoterInfoVO>();
	
	
	public Long getVoterId() {
		return voterId;
	}
	public void setVoterId(Long voterId) {
		this.voterId = voterId;
	}
	public Long getCadreId() {
		return cadreId;
	}
	public void setCadreId(Long cadreId) {
		this.cadreId = cadreId;
	}
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getVoterCardNo() {
		return voterCardNo;
	}
	public void setVoterCardNo(String voterCardNo) {
		this.voterCardNo = voterCardNo;
	}
	public Long getBlodGroupId() {
		return blodGroupId;
	}
	public void setBlodGroupId(Long blodGroupId) {
		this.blodGroupId = blodGroupId;
	}
	public String getActiveDate() {
		return activeDate;
	}
	public void setActiveDate(String activeDate) {
		this.activeDate = activeDate;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getCasteName() {
		return casteName;
	}
	public void setCasteName(String casteName) {
		this.casteName = casteName;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getEducation() {
		return education;
	}
	public void setEducation(String education) {
		this.education = education;
	}
	public String getOccupation() {
		return occupation;
	}
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	public List<VoterInfoVO> getVoterInfoVOList() {
		return voterInfoVOList;
	}
	public void setVoterInfoVOList(List<VoterInfoVO> voterInfoVOList) {
		this.voterInfoVOList = voterInfoVOList;
	}
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
	public String getRelativeName() {
		return relativeName;
	}
	public void setRelativeName(String relativeName) {
		this.relativeName = relativeName;
	}
	public String getRelationType() {
		return relationType;
	}
	public void setRelationType(String relationType) {
		this.relationType = relationType;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getHouseNo() {
		return houseNo;
	}
	public void setHouseNo(String houseNo) {
		this.houseNo = houseNo;
	}
	
	
}
