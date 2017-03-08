package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class VoterSearchVO implements Serializable{

	private Long id;
	private String name;
	
	private String voterId;
	private String relationshipType;
	private String relativeName;
	private String gender;
	private Long age;
	private String voterIDCardNo;
	private String imagePath;
	private String houseNo;
	private Long tdpCadreId;
	private String memberShipNo;
	private Long enrollmentYearId;
	private String status;
	private String totalImagePathStr;
	private String mobileNumber;
	private String actualMobiNumber; 
	private String isCsd;
	private List<String> enrollmentYearList = new ArrayList<String>();
	
	
	public List<String> getEnrollmentYearList() {
		return enrollmentYearList;
	}
	public void setEnrollmentYearList(List<String> enrollmentYearList) {
		this.enrollmentYearList = enrollmentYearList;
	}
	public String getIsCsd() {
		return isCsd;
	}
	public void setIsCsd(String isCsd) {
		this.isCsd = isCsd;
	}
	public String getTotalImagePathStr() {
		return totalImagePathStr;
	}
	public void setTotalImagePathStr(String totalImagePathStr) {
		this.totalImagePathStr = totalImagePathStr;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Long getTdpCadreId() {
		return tdpCadreId;
	}
	public void setTdpCadreId(Long tdpCadreId) {
		this.tdpCadreId = tdpCadreId;
	}
	public String getMemberShipNo() {
		return memberShipNo;
	}
	public void setMemberShipNo(String memberShipNo) {
		this.memberShipNo = memberShipNo;
	}
	public Long getEnrollmentYearId() {
		return enrollmentYearId;
	}
	public void setEnrollmentYearId(Long enrollmentYearId) {
		this.enrollmentYearId = enrollmentYearId;
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
	public String getVoterId() {
		return voterId;
	}
	public void setVoterId(String voterId) {
		this.voterId = voterId;
	}
	public String getRelationshipType() {
		return relationshipType;
	}
	public void setRelationshipType(String relationshipType) {
		this.relationshipType = relationshipType;
	}
	public String getRelativeName() {
		return relativeName;
	}
	public void setRelativeName(String relativeName) {
		this.relativeName = relativeName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Long getAge() {
		return age;
	}
	public void setAge(Long age) {
		this.age = age;
	}
	public String getVoterIDCardNo() {
		return voterIDCardNo;
	}
	public void setVoterIDCardNo(String voterIDCardNo) {
		this.voterIDCardNo = voterIDCardNo;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public String getHouseNo() {
		return houseNo;
	}
	public void setHouseNo(String houseNo) {
		this.houseNo = houseNo;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getActualMobiNumber() {
		return actualMobiNumber;
	}
	public void setActualMobiNumber(String actualMobiNumber) {
		this.actualMobiNumber = actualMobiNumber;
	}
	
}
