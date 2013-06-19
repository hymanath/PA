package com.itgrids.partyanalyst.dto;

import java.io.Serializable;

@SuppressWarnings("serial")
public class SurveyorVO implements Serializable{
	
	private Long surveyorId;
	private String surveyorName;
	private String mobileNo;
	private String phoneNo;
	private Long age;
	private String emailId;
	private String teamLead;
	private Long teamLeadId;
	private String gender;
	public Long getSurveyorId() {
		return surveyorId;
	}
	
	public SurveyorVO(Long id, String name)
	{
		this.surveyorId = id;
		this.surveyorName = name;	
	}
	public SurveyorVO() {
		// TODO Auto-generated constructor stub
	}

	public void setSurveyorId(Long surveyorId) {
		this.surveyorId = surveyorId;
	}
	public String getSurveyorName() {
		return surveyorName;
	}
	public void setSurveyorName(String surveyorName) {
		this.surveyorName = surveyorName;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public Long getAge() {
		return age;
	}
	public void setAge(Long age) {
		this.age = age;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getTeamLead() {
		return teamLead;
	}
	public void setTeamLead(String teamLead) {
		this.teamLead = teamLead;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Long getTeamLeadId() {
		return teamLeadId;
	}
	public void setTeamLeadId(Long teamLeadId) {
		this.teamLeadId = teamLeadId;
	}
	
	

}
