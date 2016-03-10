/**
 * @author Sravanth
 * Feb 19, 2016
 * PartyMeetingWSVO.java
 * PA - SERV - com.itgrids.partyanalyst.dto
 */
package com.itgrids.partyanalyst.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Sravanth
 * @date Feb 19, 2016
 */
public class PartyMeetingWSVO {

	private Long id;
	private String name;
	
	private Long tdpCadreId;
	private String cadreName;
	private String memberShipNo;
	private String mobileNo;
	private String designation;
	private String roles;
	private String imgStr;
	private String dateOfBirth;
	private Long age;
	private Long constituencyId;
	private String participatedConstituency;
	private String ownConstituency;
	
	private Long inviteesCount = 0l;
	private Long attendedCount = 0l;
	private Long inviteesAttendedCount = 0l;
	private Long nonInviteesAttendedCount = 0l;
	private Long absentCount = 0l;
	
	private Long count = 0l;
	private String memberType;
	private String voterCardNo;
	private List<PartyMeetingWSVO> partyMeetingWSVoList = new ArrayList<PartyMeetingWSVO>();
	private List<PartyMeetingWSVO> designationWiseCountsList = new ArrayList<PartyMeetingWSVO>();
	private String status;
	private String regThrough;

	/* tab details */
	
	private Long 			tabDetailsId;
	private Date 			attendedTime;
	private String 			imei;
	private String 			uniqueKey;
	private Date 			insertedTime;
	private String 			latitude;
	private String 			longitude;
	private Long 			tabUserId;
	private Long 			currentTabUserId;
	private String 			syncSource;
	private Long 			insertedBy;
	private Long 			tabPrimaryKey;
	private List<String> 	availableDates=new ArrayList<String>(0);
	private String 			surveyDate;
	private String 			userName;
	private Long            voterId;
	
	
	private String         cadreMembershipno;
	private String         cadreDataSourceType;
	/* */
	
	public String getRegThrough() {
		return regThrough;
	}
	public void setRegThrough(String regThrough) {
		this.regThrough = regThrough;
	}
	public String getRoles() {
		return roles;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Long getTabDetailsId() {
		return tabDetailsId;
	}
	public void setTabDetailsId(Long tabDetailsId) {
		this.tabDetailsId = tabDetailsId;
	}
	public Date getAttendedTime() {
		return attendedTime;
	}
	public void setAttendedTime(Date attendedTime) {
		this.attendedTime = attendedTime;
	}
	public String getImei() {
		return imei;
	}
	public void setImei(String imei) {
		this.imei = imei;
	}
	public String getUniqueKey() {
		return uniqueKey;
	}
	public void setUniqueKey(String uniqueKey) {
		this.uniqueKey = uniqueKey;
	}
	public Date getInsertedTime() {
		return insertedTime;
	}
	public void setInsertedTime(Date insertedTime) {
		this.insertedTime = insertedTime;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public Long getTabUserId() {
		return tabUserId;
	}
	public void setTabUserId(Long tabUserId) {
		this.tabUserId = tabUserId;
	}
	public Long getCurrentTabUserId() {
		return currentTabUserId;
	}
	public void setCurrentTabUserId(Long currentTabUserId) {
		this.currentTabUserId = currentTabUserId;
	}
	public String getSyncSource() {
		return syncSource;
	}
	public void setSyncSource(String syncSource) {
		this.syncSource = syncSource;
	}
	public Long getInsertedBy() {
		return insertedBy;
	}
	public void setInsertedBy(Long insertedBy) {
		this.insertedBy = insertedBy;
	}
	public Long getTabPrimaryKey() {
		return tabPrimaryKey;
	}
	public void setTabPrimaryKey(Long tabPrimaryKey) {
		this.tabPrimaryKey = tabPrimaryKey;
	}
	public List<String> getAvailableDates() {
		return availableDates;
	}
	public void setAvailableDates(List<String> availableDates) {
		this.availableDates = availableDates;
	}
	public String getSurveyDate() {
		return surveyDate;
	}
	public void setSurveyDate(String surveyDate) {
		this.surveyDate = surveyDate;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public void setRoles(String roles) {
		this.roles = roles;
	}
	public String getMemberType() {
		return memberType;
	}
	public void setMemberType(String memberType) {
		this.memberType = memberType;
	}
	public String getVoterCardNo() {
		return voterCardNo;
	}
	public void setVoterCardNo(String voterCardNo) {
		this.voterCardNo = voterCardNo;
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
	public String getMemberShipNo() {
		return memberShipNo;
	}
	public void setMemberShipNo(String memberShipNo) {
		this.memberShipNo = memberShipNo;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getImgStr() {
		return imgStr;
	}
	public void setImgStr(String imgStr) {
		this.imgStr = imgStr;
	}
	public Long getInviteesCount() {
		return inviteesCount;
	}
	public void setInviteesCount(Long inviteesCount) {
		this.inviteesCount = inviteesCount;
	}
	public Long getAttendedCount() {
		return attendedCount;
	}
	public void setAttendedCount(Long attendedCount) {
		this.attendedCount = attendedCount;
	}
	public Long getInviteesAttendedCount() {
		return inviteesAttendedCount;
	}
	public void setInviteesAttendedCount(Long inviteesAttendedCount) {
		this.inviteesAttendedCount = inviteesAttendedCount;
	}
	public Long getNonInviteesAttendedCount() {
		return nonInviteesAttendedCount;
	}
	public void setNonInviteesAttendedCount(Long nonInviteesAttendedCount) {
		this.nonInviteesAttendedCount = nonInviteesAttendedCount;
	}
	public List<PartyMeetingWSVO> getPartyMeetingWSVoList() {
		return partyMeetingWSVoList;
	}
	public void setPartyMeetingWSVoList(List<PartyMeetingWSVO> partyMeetingWSVoList) {
		this.partyMeetingWSVoList = partyMeetingWSVoList;
	}
	public Long getAbsentCount() {
		return absentCount;
	}
	public void setAbsentCount(Long absentCount) {
		this.absentCount = absentCount;
	}
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public Long getAge() {
		return age;
	}
	public void setAge(Long age) {
		this.age = age;
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {     
		this.count = count;
	}
	public List<PartyMeetingWSVO> getDesignationWiseCountsList() {
		return designationWiseCountsList;
	}
	public void setDesignationWiseCountsList(
			List<PartyMeetingWSVO> designationWiseCountsList) {
		this.designationWiseCountsList = designationWiseCountsList;
	}
	public Long getConstituencyId() {
		return constituencyId;
	}
	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}
	public String getParticipatedConstituency() {
		return participatedConstituency;
	}
	public void setParticipatedConstituency(String participatedConstituency) {
		this.participatedConstituency = participatedConstituency;
	}
	public String getOwnConstituency() {
		return ownConstituency;
	}
	public void setOwnConstituency(String ownConstituency) {
		this.ownConstituency = ownConstituency;
	}
	public Long getVoterId() {
		return voterId;
	}
	public void setVoterId(Long voterId) {
		this.voterId = voterId;
	}
	public String getCadreMembershipno() {
		return cadreMembershipno;
	}
	public void setCadreMembershipno(String cadreMembershipno) {
		this.cadreMembershipno = cadreMembershipno;
	}
	public String getCadreDataSourceType() {
		return cadreDataSourceType;
	}
	public void setCadreDataSourceType(String cadreDataSourceType) {
		this.cadreDataSourceType = cadreDataSourceType;
	}
	
}
