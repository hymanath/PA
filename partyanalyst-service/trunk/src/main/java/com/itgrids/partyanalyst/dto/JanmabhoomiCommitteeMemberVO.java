package com.itgrids.partyanalyst.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class JanmabhoomiCommitteeMemberVO {
private Long id;
private String name;
private Long totalMemberCount=0L;
private Long addedMemberCount=0L;
private Long notAddedMemberCount=0L;
private Long RejectedMemberCount=0L;
private String designationName;
private Long roleMemberCount=0L;
private String memeberName;
private String mobileNumber;
private Long voterId;
private String partyName;
private String status;
private Long partyId;
private Long designationId;
private Long casteId;
private Long categoryId;
private String categoryName;
private String casteName;
private String DOB;
private Long tdpCadreId = 0l;
private Long userId ;
private Long enrollmentYrId ;
private Set<Long> committeeIds = new TreeSet<Long>();
private List<JanmabhoomiCommitteeMemberVO> desinationMebersVOList=new ArrayList<JanmabhoomiCommitteeMemberVO>();
private List<JanmabhoomiCommitteeMemberVO> desinationVOList=new ArrayList<JanmabhoomiCommitteeMemberVO>();
private Long committeeId = 0l;
private String memberShipCardId;
private String imageURL;

private String relationshipType;
private String  relativeName;
private String gender;
private Long age;
private String houseNo;
private String bcType;
private String scType;
private String stType;
private String ocType;
private String voterCardNo;
private Long levelId;
private Long levelValue;
private Long publicRepresentativeTypeId;

private Long districtId;
private String districtName;
private Long constituencyId;
private String constituencyName;
private Long parliamentConstituencyId;
private String parliamentConstituencyName;
private Long mandalId;
private String mandalName;
private Long panchayatId;
private String panchayatName;
private Long localElectionBodyId;
private String localElectionBodyName;
private Long wardId;
private String wardName;
public Long getPublicRepresentativeTypeId() {
	return publicRepresentativeTypeId;
}
public void setPublicRepresentativeTypeId(Long publicRepresentativeTypeId) {
	this.publicRepresentativeTypeId = publicRepresentativeTypeId;
}
public String getBcType() {
	return bcType;
}
public void setBcType(String bcType) {
	this.bcType = bcType;
}
public String getScType() {
	return scType;
}
public void setScType(String scType) {
	this.scType = scType;
}
public String getStType() {
	return stType;
}
public void setStType(String stType) {
	this.stType = stType;
}
public Set<Long> getCommitteeIds() {
	return committeeIds;
}
public void setCommitteeIds(Set<Long> committeeIds) {
	this.committeeIds = committeeIds;
}
public String getCategoryName() {
	return categoryName;
}
public void setCategoryName(String categoryName) {
	this.categoryName = categoryName;
}
public String getCasteName() {
	return casteName;
}
public void setCasteName(String casteName) {
	this.casteName = casteName;
}
public String getDOB() {
	return DOB;
}
public void setDOB(String dOB) {
	DOB = dOB;
}
public Long getEnrollmentYrId() {
	return enrollmentYrId;
}
public void setEnrollmentYrId(Long enrollmentYrId) {
	this.enrollmentYrId = enrollmentYrId;
}
public Long getTdpCadreId() {
	return tdpCadreId;
}
public void setTdpCadreId(Long tdpCadreId) {
	this.tdpCadreId = tdpCadreId;
}
public Long getUserId() {
	return userId;
}
public void setUserId(Long userId) {
	this.userId = userId;
}
public Long getCasteId() {
	return casteId;
}
public void setCasteId(Long casteId) {
	this.casteId = casteId;
}
public Long getCategoryId() {
	return categoryId;
}
public void setCategoryId(Long categoryId) {
	this.categoryId = categoryId;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public Long getTotalMemberCount() {
	return totalMemberCount;
}
public void setTotalMemberCount(Long totalMemberCount) {
	this.totalMemberCount = totalMemberCount;
}
public Long getAddedMemberCount() {
	return addedMemberCount;
}
public void setAddedMemberCount(Long addedMemberCount) {
	this.addedMemberCount = addedMemberCount;
}
public Long getNotAddedMemberCount() {
	return notAddedMemberCount;
}
public void setNotAddedMemberCount(Long notAddedMemberCount) {
	this.notAddedMemberCount = notAddedMemberCount;
}
public Long getRejectedMemberCount() {
	return RejectedMemberCount;
}
public void setRejectedMemberCount(Long rejectedMemberCount) {
	RejectedMemberCount = rejectedMemberCount;
}
public String getDesignationName() {
	return designationName;
}
public void setDesignationName(String designationName) {
	this.designationName = designationName;
}
public Long getRoleMemberCount() {
	return roleMemberCount;
}
public void setRoleMemberCount(Long roleMemberCount) {
	this.roleMemberCount = roleMemberCount;
}
public String getMemeberName() {
	return memeberName;
}
public void setMemeberName(String memeberName) {
	this.memeberName = memeberName;
}
public String getMobileNumber() {
	return mobileNumber;
}
public void setMobileNumber(String mobileNumber) {
	this.mobileNumber = mobileNumber;
}
public Long getVoterId() {
	return voterId;
}
public void setVoterId(Long voterId) {
	this.voterId = voterId;
}
public String getPartyName() {
	return partyName;
}
public void setPartyName(String partyName) {
	this.partyName = partyName;
}
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}
public Long getPartyId() {
	return partyId;
}
public void setPartyId(Long partyId) {
	this.partyId = partyId;
}
public Long getDesignationId() {
	return designationId;
}
public void setDesignationId(Long designationId) {
	this.designationId = designationId;
}
public List<JanmabhoomiCommitteeMemberVO> getDesinationMebersVOList() {
	return desinationMebersVOList;
}
public void setDesinationMebersVOList(
		List<JanmabhoomiCommitteeMemberVO> desinationMebersVOList) {
	this.desinationMebersVOList = desinationMebersVOList;
}
public List<JanmabhoomiCommitteeMemberVO> getDesinationVOList() {
	return desinationVOList;
}
public void setDesinationVOList(List<JanmabhoomiCommitteeMemberVO> desinationVOList) {
	this.desinationVOList = desinationVOList;
}
public String getMemberShipCardId() {
	return memberShipCardId;
}
public void setMemberShipCardId(String memberShipCardId) {
	this.memberShipCardId = memberShipCardId;
}
public String getImageURL() {
	return imageURL;
}
public void setImageURL(String imageURL) {
	this.imageURL = imageURL;
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
public String getHouseNo() {
	return houseNo;
}
public void setHouseNo(String houseNo) {
	this.houseNo = houseNo;
}
public String getVoterCardNo() {
	return voterCardNo;
}
public void setVoterCardNo(String voterCardNo) {
	this.voterCardNo = voterCardNo;
}
public Long getCommitteeId() {
	return committeeId;
}
public void setCommitteeId(Long committeeId) {
	this.committeeId = committeeId;
}
public String getOcType() {
	return ocType;
}
public void setOcType(String ocType) {
	this.ocType = ocType;
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
public Long getParliamentConstituencyId() {
	return parliamentConstituencyId;
}
public void setParliamentConstituencyId(Long parliamentConstituencyId) {
	this.parliamentConstituencyId = parliamentConstituencyId;
}
public String getParliamentConstituencyName() {
	return parliamentConstituencyName;
}
public void setParliamentConstituencyName(String parliamentConstituencyName) {
	this.parliamentConstituencyName = parliamentConstituencyName;
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
public Long getPanchayatId() {
	return panchayatId;
}
public void setPanchayatId(Long panchayatId) {
	this.panchayatId = panchayatId;
}
public String getPanchayatName() {
	return panchayatName;
}
public void setPanchayatName(String panchayatName) {
	this.panchayatName = panchayatName;
}
public Long getLocalElectionBodyId() {
	return localElectionBodyId;
}
public void setLocalElectionBodyId(Long localElectionBodyId) {
	this.localElectionBodyId = localElectionBodyId;
}
public String getLocalElectionBodyName() {
	return localElectionBodyName;
}
public void setLocalElectionBodyName(String localElectionBodyName) {
	this.localElectionBodyName = localElectionBodyName;
}
public Long getWardId() {
	return wardId;
}
public void setWardId(Long wardId) {
	this.wardId = wardId;
}
public String getWardName() {
	return wardName;
}
public void setWardName(String wardName) {
	this.wardName = wardName;
}

public Long getLevelId() {
	return levelId;
}
public void setLevelId(Long levelId) {
	this.levelId = levelId;
}
public Long getLevelValue() {
	return levelValue;
}
public void setLevelValue(Long levelValue) {
	this.levelValue = levelValue;
}


}
