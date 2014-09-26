package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class CadreRegistrationVO implements Serializable{

	
	private static final long serialVersionUID = 1951217282876724276L;
	
	
	private String voterName;
	private Date dob;
	private String gender;
	private String relativeName;
	private Long voterId;
	private String previousEnrollmentNumber;
	private String voterCardNo;
	private Date partyMemberSince;
	private Long bloodGroupId;
	private String street;
	private Long casteId;
	private String casteName;
	private String mobileNumber;
	private Long educationId;
	private Long occupationId;
	private String houseNo;
	
	private List<CadrePreviousRollesVO> previousRollesList;
	
	private List<CadrePreviousRollesVO> previousParicaptedElectionsList;
	
	
	public String getVoterName() {
		return voterName;
	}
	public void setVoterName(String voterName) {
		this.voterName = voterName;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getRelativeName() {
		return relativeName;
	}
	public void setRelativeName(String relativeName) {
		this.relativeName = relativeName;
	}
	public Long getVoterId() {
		return voterId;
	}
	public void setVoterId(Long voterId) {
		this.voterId = voterId;
	}
	public String getPreviousEnrollmentNumber() {
		return previousEnrollmentNumber;
	}
	public void setPreviousEnrollmentNumber(String previousEnrollmentNumber) {
		this.previousEnrollmentNumber = previousEnrollmentNumber;
	}
	public String getVoterCardNo() {
		return voterCardNo;
	}
	public void setVoterCardNo(String voterCardNo) {
		this.voterCardNo = voterCardNo;
	}
	public Date getPartyMemberSince() {
		return partyMemberSince;
	}
	public void setPartyMemberSince(Date partyMemberSince) {
		this.partyMemberSince = partyMemberSince;
	}
	public Long getBloodGroupId() {
		return bloodGroupId;
	}
	public void setBloodGroupId(Long bloodGroupId) {
		this.bloodGroupId = bloodGroupId;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public Long getCasteId() {
		return casteId;
	}
	public void setCasteId(Long casteId) {
		this.casteId = casteId;
	}
	public String getCasteName() {
		return casteName;
	}
	public void setCasteName(String casteName) {
		this.casteName = casteName;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public Long getEducationId() {
		return educationId;
	}
	public void setEducationId(Long educationId) {
		this.educationId = educationId;
	}
	public Long getOccupationId() {
		return occupationId;
	}
	public void setOccupationId(Long occupationId) {
		this.occupationId = occupationId;
	}
	public String getHouseNo() {
		return houseNo;
	}
	public void setHouseNo(String houseNo) {
		this.houseNo = houseNo;
	}
	public List<CadrePreviousRollesVO> getPreviousRollesList() {
		return previousRollesList;
	}
	public void setPreviousRollesList(List<CadrePreviousRollesVO> previousRollesList) {
		this.previousRollesList = previousRollesList;
	}
	public List<CadrePreviousRollesVO> getPreviousParicaptedElectionsList() {
		return previousParicaptedElectionsList;
	}
	public void setPreviousParicaptedElectionsList(
			List<CadrePreviousRollesVO> previousParicaptedElectionsList) {
		this.previousParicaptedElectionsList = previousParicaptedElectionsList;
	}
	
	
	
	
}
