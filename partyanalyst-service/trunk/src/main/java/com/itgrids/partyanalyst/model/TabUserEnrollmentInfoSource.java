package com.itgrids.partyanalyst.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;
@Entity
@Table(name = "tab_user_enrollment_info_source")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)

public class TabUserEnrollmentInfoSource extends BaseModel implements Serializable {
	private Long tabUserEnrollmentInfoId;
	private Long enrollmentYearId;  
	private Long constituencyId;
	private Long stateId;
	private Date surveyTime;
	private String imgPath;
	private Long cadreSurveyUserId;
	private String userName;  
	private Long tabUserInfoId;
	private String tabUserName;
	private String mobileNo;
	private Long totalRecords;
	private Date startTime;
	private Date endTime;
	
	private Constituency Constituency;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "tab_user_enrollment_info_id", unique = true, nullable = false)
	public Long getTabUserEnrollmentInfoId() {
		return tabUserEnrollmentInfoId;
	}

	public void setTabUserEnrollmentInfoId(Long tabUserEnrollmentInfoId) {
		this.tabUserEnrollmentInfoId = tabUserEnrollmentInfoId;
	}
	@Column(name="enrollment_year_id")
	public Long getEnrollmentYearId() {
		return enrollmentYearId;
	}

	public void setEnrollmentYearId(Long enrollmentYearId) {
		this.enrollmentYearId = enrollmentYearId;
	}
	@Column(name="constituency_id")
	public Long getConstituencyId() {
		return constituencyId;
	}

	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}
	@Column(name="state_id")
	public Long getStateId() {
		return stateId;
	}

	public void setStateId(Long stateId) {
		this.stateId = stateId;
	}
	@Column(name="survey_time")
	public Date getSurveyTime() {
		return surveyTime;
	}

	public void setSurveyTime(Date surveyTime) {
		this.surveyTime = surveyTime;
	}
	@Column(name="img_path")
	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
	@Column(name="cadre_survey_user_id")
	public Long getCadreSurveyUserId() {
		return cadreSurveyUserId;
	}

	public void setCadreSurveyUserId(Long cadreSurveyUserId) {
		this.cadreSurveyUserId = cadreSurveyUserId;
	}
	@Column(name="user_name")
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	@Column(name="tab_user_info_id")
	public Long getTabUserInfoId() {
		return tabUserInfoId;
	}

	public void setTabUserInfoId(Long tabUserInfoId) {
		this.tabUserInfoId = tabUserInfoId;
	}
	@Column(name="tab_user_name")
	public String getTabUserName() {
		return tabUserName;
	}

	public void setTabUserName(String tabUserName) {
		this.tabUserName = tabUserName;
	}
	@Column(name="MOBILE_NO")
	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	@Column(name="total_records")
	public Long getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(Long totalRecords) {
		this.totalRecords = totalRecords;
	}
	@Column(name="start_time")
	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	@Column(name="end_time")
	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="constituency_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Constituency getConstituency() {
		return Constituency;  
	}

	public void setConstituency(Constituency constituency) {
		Constituency = constituency;
	} 
}
