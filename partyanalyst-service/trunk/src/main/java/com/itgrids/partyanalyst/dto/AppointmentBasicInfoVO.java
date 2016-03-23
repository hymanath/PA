package com.itgrids.partyanalyst.dto;

import java.util.List;

public class AppointmentBasicInfoVO {

	private String name;
	private Long designationId;
	private String mobileNo;
	private Long locationScopeId;
	private String voterCardNo;
	private String membershipNum;
	private Long districtId;
	private Long constituencyId;
	private Long tehsilId;
	private Long appointmentUserId;
	private String date;
	private Long villageId;
	private Long appointmentLabelId;
	private Long appointCandidateId;
	private String designation;
	private String constituencyName;
	private String reason;
	private String priority;
	private String uniqueId;
	private Integer count;
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public String getUniqueId() {
		return uniqueId;
	}
	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public String getConstituencyName() {
		return constituencyName;
	}
	public void setConstituencyName(String constituencyName) {
		this.constituencyName = constituencyName;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	private List<AppointmentStatusVO> appointStatusCountList;
    private List<AppointmentStatusVO> appointStatusRequestedList;

    public Long getAppointCandidateId() {
		return appointCandidateId;
	}
	public void setAppointCandidateId(Long appointCandidateId) {
		this.appointCandidateId = appointCandidateId;
	}
	public List<AppointmentStatusVO> getAppointStatusCountList() {
		return appointStatusCountList;
	}
	public void setAppointStatusCountList(
			List<AppointmentStatusVO> appointStatusCountList) {
		this.appointStatusCountList = appointStatusCountList;
	}
	public List<AppointmentStatusVO> getAppointStatusRequestedList() {
		return appointStatusRequestedList;
	}
	public void setAppointStatusRequestedList(
			List<AppointmentStatusVO> appointStatusRequestedList) {
		this.appointStatusRequestedList = appointStatusRequestedList;
	}
	public Long getAppointmentLabelId() {
		return appointmentLabelId;
	}
	public void setAppointmentLabelId(Long appointmentLabelId) {
		this.appointmentLabelId = appointmentLabelId;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Long getAppointmentUserId() {
		return appointmentUserId;
	}
	public void setAppointmentUserId(Long appointmentUserId) {
		this.appointmentUserId = appointmentUserId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getDesignationId() {
		return designationId;
	}
	public void setDesignationId(Long designationId) {
		this.designationId = designationId;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public Long getLocationScopeId() {
		return locationScopeId;
	}
	public void setLocationScopeId(Long locationScopeId) {
		this.locationScopeId = locationScopeId;
	}
	public String getVoterCardNo() {
		return voterCardNo;
	}
	public void setVoterCardNo(String voterCardNo) {
		this.voterCardNo = voterCardNo;
	}
	public String getMembershipNum() {
		return membershipNum;
	}
	public void setMembershipNum(String membershipNum) {
		this.membershipNum = membershipNum;
	}
	public Long getDistrictId() {
		return districtId;
	}
	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}
	public Long getConstituencyId() {
		return constituencyId;
	}
	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}
	public Long getTehsilId() {
		return tehsilId;
	}
	public void setTehsilId(Long tehsilId) {
		this.tehsilId = tehsilId;
	}
	public Long getVillageId() {
		return villageId;
	}
	public void setVillageId(Long villageId) {
		this.villageId = villageId;
	}
	
	
	
	
}
