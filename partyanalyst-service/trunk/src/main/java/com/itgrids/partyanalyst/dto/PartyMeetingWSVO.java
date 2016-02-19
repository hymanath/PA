/**
 * @author Sravanth
 * Feb 19, 2016
 * PartyMeetingWSVO.java
 * PA - SERV - com.itgrids.partyanalyst.dto
 */
package com.itgrids.partyanalyst.dto;

import java.util.ArrayList;
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
	private String imgStr;
	private String dateOfBirth;
	private Long age;
	
	private Long inviteesCount = 0l;
	private Long attendedCount = 0l;
	private Long inviteesAttendedCount = 0l;
	private Long nonInviteesAttendedCount = 0l;
	private Long absentCount = 0l;
	
	private List<PartyMeetingWSVO> partyMeetingWSVoList = new ArrayList<PartyMeetingWSVO>();
	
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
}
