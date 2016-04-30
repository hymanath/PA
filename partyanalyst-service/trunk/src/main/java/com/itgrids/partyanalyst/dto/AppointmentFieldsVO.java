package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class AppointmentFieldsVO implements Serializable{
	
	private Long    appointmentId;
	private String  uniqueId;
	private Long    apptUserId;
	private String  apptUserName;
	private String  createdDate;
	private String  priority;
	private String  reason;
	private Long    preferableTimeId;
	private Long    statusId;
	private String  status;
	
	private String  date;
	private String  fromDate;
	private String  toDate;
	private String  fromTime;
	private String  toTime;
	
	private Long    candidateId;
	private String  candidateName;
	private String  imageURL;
	private Long    candidateTypeId;
	private String  candidateType;
	private Long    designationId;
	private String  designation;
	private String  mobileNo;
	private Long    voterId;
	private String  voterCardNo;
	private Long    tdpCadreId;
	private String  memberShipId;
	
	private String  state;
	private String  district;
	private String  constituency;
	private String  tehsil;
	private String  localElectionBody;
	private String  village;
	private String  ward;
	
	private String candDesignation;
	private String location;
	
	private String voterConstituency;
	private AppointmentScheduleVO appointmentScheduleVO;
	
	private List<AppointmentFieldsVO>     subList;
	
	public Long getAppointmentId() {
		return appointmentId;
	}
	public void setAppointmentId(Long appointmentId) {
		this.appointmentId = appointmentId;
	}
	public String getUniqueId() {
		return uniqueId;
	}
	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}
	public Long getApptUserId() {
		return apptUserId;
	}
	public void setApptUserId(Long apptUserId) {
		this.apptUserId = apptUserId;
	}
	public String getApptUserName() {
		return apptUserName;
	}
	public void setApptUserName(String apptUserName) {
		this.apptUserName = apptUserName;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public Long getStatusId() {
		return statusId;
	}
	public void setStatusId(Long statusId) {
		this.statusId = statusId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
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
	public Long getPreferableTimeId() {
		return preferableTimeId;
	}
	public void setPreferableTimeId(Long preferableTimeId) {
		this.preferableTimeId = preferableTimeId;
	}
	public Long getCandidateId() {
		return candidateId;
	}
	public void setCandidateId(Long candidateId) {
		this.candidateId = candidateId;
	}
	public String getCandidateName() {
		return candidateName;
	}
	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
	}
	public String getImageURL() {
		return imageURL;
	}
	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}
	public Long getCandidateTypeId() {
		return candidateTypeId;
	}
	public void setCandidateTypeId(Long candidateTypeId) {
		this.candidateTypeId = candidateTypeId;
	}
	public String getCandidateType() {
		return candidateType;
	}
	public void setCandidateType(String candidateType) {
		this.candidateType = candidateType;
	}
	public Long getDesignationId() {
		return designationId;
	}
	public void setDesignationId(Long designationId) {
		this.designationId = designationId;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public Long getVoterId() {
		return voterId;
	}
	public void setVoterId(Long voterId) {
		this.voterId = voterId;
	}
	public Long getTdpCadreId() {
		return tdpCadreId;
	}
	public void setTdpCadreId(Long tdpCadreId) {
		this.tdpCadreId = tdpCadreId;
	}
	public String getMemberShipId() {
		return memberShipId;
	}
	public void setMemberShipId(String memberShipId) {
		this.memberShipId = memberShipId;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getConstituency() {
		return constituency;
	}
	public void setConstituency(String constituency) {
		this.constituency = constituency;
	}
	
	public String getVillage() {
		return village;
	}
	public void setVillage(String village) {
		this.village = village;
	}
	public String getWard() {
		return ward;
	}
	public void setWard(String ward) {
		this.ward = ward;
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
	
	public List<AppointmentFieldsVO> getSubList() {
		return subList;
	}
	public void setSubList(List<AppointmentFieldsVO> subList) {
		this.subList = subList;
	}
	public String getFromTime() {
		return fromTime;
	}
	public void setFromTime(String fromTime) {
		this.fromTime = fromTime;
	}
	public String getToTime() {
		return toTime;
	}
	public void setToTime(String toTime) {
		this.toTime = toTime;
	}
	public String getVoterCardNo() {
		return voterCardNo;
	}
	public void setVoterCardNo(String voterCardNo) {
		this.voterCardNo = voterCardNo;
	}
	public AppointmentScheduleVO getAppointmentScheduleVO() {
		return appointmentScheduleVO;
	}
	public void setAppointmentScheduleVO(AppointmentScheduleVO appointmentScheduleVO) {
		this.appointmentScheduleVO = appointmentScheduleVO;
	}
	public String getCandDesignation() {
		return candDesignation;
	}
	public void setCandDesignation(String candDesignation) {
		this.candDesignation = candDesignation;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getVoterConstituency() {
		return voterConstituency;
	}
	public void setVoterConstituency(String voterConstituency) {
		this.voterConstituency = voterConstituency;
	}
	
}
