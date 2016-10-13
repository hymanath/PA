package com.itgrids.partyanalyst.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name="tab_user_enrollment_info")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TabUserEnrollmentInfo implements Serializable {
	
	private Long tabUserEnrollmentInfoId;
    private Long enrollmentYearId;
	private Long constituencyId;
	private Long stateId;
	private Date surveyTime;
	private String imagePath;
	private Long   cadreSurveyUserId;
	private String username;
	private Long   tabUserInfoId;
	private String tabUserName;
	private String mobileNo;
	private Long   totalRecords;
	private Date   startTime;
	private Date   endTime;
	
	
	 @Id
	 @GeneratedValue(strategy=GenerationType.AUTO)
	 @Column(name="tab_user_enrollment_info_id", unique=true, nullable=false)
	public Long getTabUserEnrollmentInfoId() {
		return tabUserEnrollmentInfoId;
	}
	public void setTabUserEnrollmentInfoId(Long tabUserEnrollmentInfoId) {
		this.tabUserEnrollmentInfoId = tabUserEnrollmentInfoId;
	}
	
	
	@Column(name = "constituency_id")
	public Long getConstituencyId() {
		return constituencyId;
	}
	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}
	
	@Column(name = "survey_time")
	public Date getSurveyTime() {
		return surveyTime;
	}
	public void setSurveyTime(Date surveyTime) {
		this.surveyTime = surveyTime;
	}
	
	@Column(name = "img_path")
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	
	@Column(name = "cadre_survey_user_id")
	public Long getCadreSurveyUserId() {
		return cadreSurveyUserId;
	}
	public void setCadreSurveyUserId(Long cadreSurveyUserId) {
		this.cadreSurveyUserId = cadreSurveyUserId;
	}
	
	@Column(name = "user_name")
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	@Column(name = "tab_user_info_id")
	public Long getTabUserInfoId() {
		return tabUserInfoId;
	}
	public void setTabUserInfoId(Long tabUserInfoId) {
		this.tabUserInfoId = tabUserInfoId;
	}
	
	@Column(name = "tab_user_name")
	public String getTabUserName() {
		return tabUserName;
	}
	public void setTabUserName(String tabUserName) {
		this.tabUserName = tabUserName;
	}
	
	@Column(name = "MOBILE_NO")
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	
	@Column(name = "total_records")
	public Long getTotalRecords() {
		return totalRecords;
	}
	public void setTotalRecords(Long totalRecords) {
		this.totalRecords = totalRecords;
	}
	
	@Column(name = "start_time")
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	
	@Column(name = "end_time")
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
	@Column(name = "enrollment_year_id")
	public Long getEnrollmentYearId() {
		return enrollmentYearId;
	}
	public void setEnrollmentYearId(Long enrollmentYearId) {
		this.enrollmentYearId = enrollmentYearId;
	}
	
	@Column(name = "state_id")
	public Long getStateId() {
		return stateId;
	}
	public void setStateId(Long stateId) {
		this.stateId = stateId;
	}
	
}
