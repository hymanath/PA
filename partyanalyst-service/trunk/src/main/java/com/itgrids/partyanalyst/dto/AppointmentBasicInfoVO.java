package com.itgrids.partyanalyst.dto;

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
	
	
	
}
