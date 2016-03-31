package com.itgrids.partyanalyst.dto;

import java.io.Serializable;

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
       
    
    
}
