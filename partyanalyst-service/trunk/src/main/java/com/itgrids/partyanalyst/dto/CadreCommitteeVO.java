package com.itgrids.partyanalyst.dto;

import java.util.ArrayList;
import java.util.List;

public class CadreCommitteeVO implements java.io.Serializable{
	
	private Long tdpCadreId;
	private String cadreName;
	private String relativeName;
	private String memberShipCardId;
	private String DOB;
	private String age;
	private String gender;
	private String mobileNo;
	private String adhaarNo;
	private String voterCardNo;
	private Long educationId;
	private String education;
	private Long occupationId;
	private String occupation;
	private String emailId;
	private String mobileType;
	private Long casteCategoryId;
	private String casteCategory;
	private Long casteStateId;
	private String casteName;
	private Long constituencyId;
	private String constituency;
	private String tehsil;
	private String localElectionBody;
	private String panchayat;
	private String address;
	private String familyVoterCardNo;
	private String constiteuncyNo;
	private String districtNo;
	
	private String fromDate;
	private String toDate;
	
	private Long committeeLevelId;
	private Long committeeTypeId;
	private Long committeeId;
	private Long roleId;
	private String role;
	
	private Long electionTypeId;
	private Long electioinYearId;
	private String electionType;
	private String electionYear;
	
	private String imageURL;
	List<CadreCommitteeVO> previousRoles = new ArrayList<CadreCommitteeVO>();
	List<CadreCommitteeVO> previousElections = new ArrayList<CadreCommitteeVO>();
	List<CadreCommitteeVO> casteList = new ArrayList<CadreCommitteeVO>();
	List<CadrePreviousRollesVO>  eligibleRoles;
	private String electrolLocation;
	private String preEnrollNo;
			
	private Long voterId;
	private String committeeLocation;
	private String committeePosition;
	private String committeeName;
	private String isSmartPhone;
	
	private Long committeeLocationId;
	private String party;
	private String type;
	private String dataSourceType;
	private String deletedStatus;
	private String deletedReason;
	
	private Long tdpCadreCommitteeId;
	
	
	
	public String getDeletedReason() {
		return deletedReason;
	}
	public void setDeletedReason(String deletedReason) {
		this.deletedReason = deletedReason;
	}
	public String getDeletedStatus() {
		return deletedStatus;
	}
	public void setDeletedStatus(String deletedStatus) {
		this.deletedStatus = deletedStatus;
	}
	public Long getTdpCadreCommitteeId() {
		return tdpCadreCommitteeId;
	}
	public void setTdpCadreCommitteeId(Long tdpCadreCommitteeId) {
		this.tdpCadreCommitteeId = tdpCadreCommitteeId;
	}
	public String getDataSourceType() {
		return dataSourceType;
	}
	public void setDataSourceType(String dataSourceType) {
		this.dataSourceType = dataSourceType;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getParty() {
		return party;
	}
	public void setParty(String party) {
		this.party = party;
	}
	public String getRelativeName() {
		return relativeName;
	}
	public void setRelativeName(String relativeName) {
		this.relativeName = relativeName;
	}
	
	public Long getCommitteeLocationId() {
		return committeeLocationId;
	}
	public void setCommitteeLocationId(Long committeeLocationId) {
		this.committeeLocationId = committeeLocationId;
	}
	public String getIsSmartPhone() {
		return isSmartPhone;
	}
	public void setIsSmartPhone(String isSmartPhone) {
		this.isSmartPhone = isSmartPhone;
	}
	public String getCommitteeLocation() {
		return committeeLocation;
	}
	public void setCommitteeLocation(String committeeLocation) {
		this.committeeLocation = committeeLocation;
	}
	public String getCommitteePosition() {
		return committeePosition;
	}
	public void setCommitteePosition(String committeePosition) {
		this.committeePosition = committeePosition;
	}
	public String getCommitteeName() {
		return committeeName;
	}
	public void setCommitteeName(String committeeName) {
		this.committeeName = committeeName;
	}
	public Long getVoterId() {
		return voterId;
	}
	public void setVoterId(Long voterId) {
		this.voterId = voterId;
	}
	public Long getConstituencyId() {
		return constituencyId;
	}
	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}
	public List<CadreCommitteeVO> getCasteList() {
		return casteList;
	}
	public void setCasteList(List<CadreCommitteeVO> casteList) {
		this.casteList = casteList;
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
	
	public Long getCasteCategoryId() {
		return casteCategoryId;
	}
	public void setCasteCategoryId(Long casteCategoryId) {
		this.casteCategoryId = casteCategoryId;
	}
	public Long getCasteStateId() {
		return casteStateId;
	}
	public void setCasteStateId(Long casteStateId) {
		this.casteStateId = casteStateId;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public Long getTdpCadreId() {
		return tdpCadreId;
	}
	public void setTdpCadreId(Long tdpCadreId) {
		this.tdpCadreId = tdpCadreId;
	}
	public String getCadreName() {
		return cadreName;
	}
	public void setCadreName(String cadreName) {
		this.cadreName = cadreName;
	}
	public String getMemberShipCardId() {
		return memberShipCardId;
	}
	public void setMemberShipCardId(String memberShipCardId) {
		this.memberShipCardId = memberShipCardId;
	}
	public String getDOB() {
		return DOB;
	}
	public void setDOB(String dOB) {
		DOB = dOB;
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
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getAdhaarNo() {
		return adhaarNo;
	}
	public void setAdhaarNo(String adhaarNo) {
		this.adhaarNo = adhaarNo;
	}
	public String getVoterCardNo() {
		return voterCardNo;
	}
	public void setVoterCardNo(String voterCardNo) {
		this.voterCardNo = voterCardNo;
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
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getMobileType() {
		return mobileType;
	}
	public void setMobileType(String mobileType) {
		this.mobileType = mobileType;
	}
	public String getCasteCategory() {
		return casteCategory;
	}
	public void setCasteCategory(String casteCategory) {
		this.casteCategory = casteCategory;
	}
	public String getCasteName() {
		return casteName;
	}
	public void setCasteName(String casteName) {
		this.casteName = casteName;
	}
	public String getConstituency() {
		return constituency;
	}
	public void setConstituency(String constituency) {
		this.constituency = constituency;
	}
	public String getTehsil() {
		return tehsil;
	}
	public void setTehsil(String tehsil) {
		this.tehsil = tehsil;
	}
	public String getLocalElectionBody() {
		return localElectionBody;
	}
	public void setLocalElectionBody(String localElectionBody) {
		this.localElectionBody = localElectionBody;
	}
	public String getPanchayat() {
		return panchayat;
	}
	public void setPanchayat(String panchayat) {
		this.panchayat = panchayat;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	public Long getCommitteeLevelId() {
		return committeeLevelId;
	}
	public void setCommitteeLevelId(Long committeeLevelId) {
		this.committeeLevelId = committeeLevelId;
	}
	public Long getCommitteeTypeId() {
		return committeeTypeId;
	}
	public void setCommitteeTypeId(Long committeeTypeId) {
		this.committeeTypeId = committeeTypeId;
	}
	public Long getCommitteeId() {
		return committeeId;
	}
	public void setCommitteeId(Long committeeId) {
		this.committeeId = committeeId;
	}
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	public Long getElectionTypeId() {
		return electionTypeId;
	}
	public void setElectionTypeId(Long electionTypeId) {
		this.electionTypeId = electionTypeId;
	}
	public Long getElectioinYearId() {
		return electioinYearId;
	}
	public void setElectioinYearId(Long electioinYearId) {
		this.electioinYearId = electioinYearId;
	}
	public String getElectionType() {
		return electionType;
	}
	public void setElectionType(String electionType) {
		this.electionType = electionType;
	}
	public String getElectionYear() {
		return electionYear;
	}
	public void setElectionYear(String electionYear) {
		this.electionYear = electionYear;
	}
	public String getImageURL() {
		return imageURL;
	}
	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}
	public List<CadreCommitteeVO> getPreviousRoles() {
		return previousRoles;
	}
	public void setPreviousRoles(List<CadreCommitteeVO> previousRoles) {
		this.previousRoles = previousRoles;
	}
	public List<CadreCommitteeVO> getPreviousElections() {
		return previousElections;
	}
	public void setPreviousElections(List<CadreCommitteeVO> previousElections) {
		this.previousElections = previousElections;
	}
	public List<CadrePreviousRollesVO> getEligibleRoles() {
		return eligibleRoles;
	}
	public void setEligibleRoles(List<CadrePreviousRollesVO> eligibleRoles) {
		this.eligibleRoles = eligibleRoles;
	}
	public String getFamilyVoterCardNo() {
		return familyVoterCardNo;
	}
	public void setFamilyVoterCardNo(String familyVoterCardNo) {
		this.familyVoterCardNo = familyVoterCardNo;
	}
	public String getElectrolLocation() {
		return electrolLocation;
	}
	public void setElectrolLocation(String electrolLocation) {
		this.electrolLocation = electrolLocation;
	}
	public String getPreEnrollNo() {
		return preEnrollNo;
	}
	public void setPreEnrollNo(String preEnrollNo) {
		this.preEnrollNo = preEnrollNo;
	}
	public String getConstiteuncyNo() {
		return constiteuncyNo;
	}
	public void setConstiteuncyNo(String constiteuncyNo) {
		this.constiteuncyNo = constiteuncyNo;
	}
	public String getDistrictNo() {
		return districtNo;
	}
	public void setDistrictNo(String districtNo) {
		this.districtNo = districtNo;
	}
	
}
