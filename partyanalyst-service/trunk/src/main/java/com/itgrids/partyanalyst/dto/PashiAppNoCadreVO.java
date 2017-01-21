package com.itgrids.partyanalyst.dto;

import java.io.Serializable;

public class PashiAppNoCadreVO implements Serializable{

	private String  name;
	private String 	imageStr;
	private String  mobileNo;
	private String  voterCardNo;
	private Long 	constituencyId;
	private Long 	appointmentId;
	private String membershipNo;
	private Long userId;
	private Long appointmentCandidateTypeId;
	private Long designationId;

	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getMembershipNo() {
		return membershipNo;
	}
	public void setMembershipNo(String membershipNo) {
		this.membershipNo = membershipNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImageStr() {
		return imageStr;
	}
	public void setImageStr(String imageStr) {
		this.imageStr = imageStr;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getVoterCardNo() {
		return voterCardNo;
	}
	public void setVoterCardNo(String voterCardNo) {
		this.voterCardNo = voterCardNo;
	}
	public Long getConstituencyId() {
		return constituencyId;
	}
	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}
	public Long getAppointmentId() {
		return appointmentId;
	}
	public void setAppointmentId(Long appointmentId) {
		this.appointmentId = appointmentId;
	}
	public Long getAppointmentCandidateTypeId() {
		return appointmentCandidateTypeId;
	}
	public void setAppointmentCandidateTypeId(Long appointmentCandidateTypeId) {
		this.appointmentCandidateTypeId = appointmentCandidateTypeId;
	}
	public Long getDesignationId() {
		return designationId;
	}
	public void setDesignationId(Long designationId) {
		this.designationId = designationId;
	}
	
	

}
