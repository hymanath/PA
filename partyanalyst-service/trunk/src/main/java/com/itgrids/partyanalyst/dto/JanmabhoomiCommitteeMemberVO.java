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
private String voterId;
private String partyName;
private String status;
private Long partyId;
private Long designationId;
private Long casteId;
private Long categoryId;
private String categoryName;
private String casteName;
private String DOB;
private Long tdpCadreId;
private Long userId ;
private Long enrollmentYrId ;
private Set<Long> committeeIds = new TreeSet<Long>();
private List<JanmabhoomiCommitteeMemberVO> desinationMebersVOList=new ArrayList<JanmabhoomiCommitteeMemberVO>();
private List<JanmabhoomiCommitteeMemberVO> desinationVOList=new ArrayList<JanmabhoomiCommitteeMemberVO>();

private String memberShipCardId;


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
public String getVoterId() {
	return voterId;
}
public void setVoterId(String voterId) {
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

}
