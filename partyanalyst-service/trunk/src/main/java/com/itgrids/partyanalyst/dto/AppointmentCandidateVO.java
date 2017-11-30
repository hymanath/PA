package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AppointmentCandidateVO implements Serializable{
     
	private String  name;
	private Long    id;
	private String  designation;
    private boolean isCadre;
    private String  mobileNo;
    private String  constituency;
    private String  candidateType;
    private String  memberShipId;
    private String voterCardNo;
    private Long designationId=0l;
    private String imageURL;
    private Long candidateTypeId;
    private Long appointmentCandidateId;
    private Long aptId;
    private String aptName;
    private boolean isAptExists;
    private String aptStatus;
    private String enrollmentYears;
    private Long tdpCadreId;
    List<CadreCommitteeVO> previousElections = new ArrayList<CadreCommitteeVO>();
    private Long voterId;
    
    private String districtName;
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
	public List<CadreCommitteeVO> getPreviousElections() {
		return previousElections;
	}
	public void setPreviousElections(List<CadreCommitteeVO> previousElections) {
		this.previousElections = previousElections;
	}
	public String getEnrollmentYears() {
		return enrollmentYears;
	}
	public void setEnrollmentYears(String enrollmentYears) {
		this.enrollmentYears = enrollmentYears;
	}
	public String getAptStatus() {
		return aptStatus;
	}
	public void setAptStatus(String aptStatus) {
		this.aptStatus = aptStatus;
	}
	public Long getAptId() {
		return aptId;
	}
	public void setAptId(Long aptId) {
		this.aptId = aptId;
	}
	public String getAptName() {
		return aptName;
	}
	public void setAptName(String aptName) {
		this.aptName = aptName;
	}
	public boolean isAptExists() {
		return isAptExists;
	}
	public void setAptExists(boolean isAptExists) {
		this.isAptExists = isAptExists;
	}
	public Long getAppointmentCandidateId() {
		return appointmentCandidateId;
	}
	public void setAppointmentCandidateId(Long appointmentCandidateId) {
		this.appointmentCandidateId = appointmentCandidateId;
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
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	
	public boolean isCadre() {
		return isCadre;
	}
	public void setCadre(boolean isCadre) {
		this.isCadre = isCadre;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getConstituency() {
		return constituency;
	}
	public void setConstituency(String constituency) {
		this.constituency = constituency;
	}
	public String getCandidateType() {
		return candidateType;
	}
	public void setCandidateType(String candidateType) {
		this.candidateType = candidateType;
	}
	public String getMemberShipId() {
		return memberShipId;
	}
	public void setMemberShipId(String memberShipId) {
		this.memberShipId = memberShipId;
	}
	public String getVoterCardNo() {
		return voterCardNo;
	}
	public void setVoterCardNo(String voterCardNo) {
		this.voterCardNo = voterCardNo;
	}
	public Long getDesignationId() {
		return designationId;
	}
	public void setDesignationId(Long designationId) {
		this.designationId = designationId;
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
	public String getDistrictName() {
		return districtName;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
    
    
    
}
