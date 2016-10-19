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

import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name = "tab_user_otp_details")
//@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TabUserOtpDetails implements Serializable{

	private Long tabUserOtpDetailsId;
	private Long tabUserInfoId;
	private Long cadreSurveyUserId;
	private String mobileNo;
	private String otpNo;
	private String referenceId;
	private Date generatedTime;
	private Date updatedTime;
	private String isValid;
	
	private TabUserInfo tabUserInfo;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "tab_user_otp_details_id", unique = true, nullable = false)
	public Long getTabUserOtpDetailsId() {
		return tabUserOtpDetailsId;
	}
	public void setTabUserOtpDetailsId(Long tabUserOtpDetailsId) {
		this.tabUserOtpDetailsId = tabUserOtpDetailsId;
	}
	
	@Column(name = "tab_user_info_id")
	public Long getTabUserInfoId() {
		return tabUserInfoId;
	}
	public void setTabUserInfoId(Long tabUserInfoId) {
		this.tabUserInfoId = tabUserInfoId;
	}
	
	@Column(name = "cadre_survey_user_id")
	public Long getCadreSurveyUserId() {
		return cadreSurveyUserId;
	}
	public void setCadreSurveyUserId(Long cadreSurveyUserId) {
		this.cadreSurveyUserId = cadreSurveyUserId;
	}
	
	@Column(name = "mobile_no")
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	
	@Column(name = "otp_no")
	public String getOtpNo() {
		return otpNo;
	}
	public void setOtpNo(String otpNo) {
		this.otpNo = otpNo;
	}
	
	@Column(name = "reference_id")
	public String getReferenceId() {
		return referenceId;
	}
	public void setReferenceId(String referenceId) {
		this.referenceId = referenceId;
	}
	
	@Column(name = "generated_time")
	public Date getGeneratedTime() {
		return generatedTime;
	}
	public void setGeneratedTime(Date generatedTime) {
		this.generatedTime = generatedTime;
	}
	
	@Column(name = "updated_time")
	public Date getUpdatedTime() {
		return updatedTime;
	}
	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}
	
	@Column(name = "is_valid")
	public String getIsValid() {
		return isValid;
	}
	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "tab_user_info_id",insertable=false,updatable=false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public TabUserInfo getTabUserInfo() {
		return tabUserInfo;
	}
	public void setTabUserInfo(TabUserInfo tabUserInfo) {
		this.tabUserInfo = tabUserInfo;
	}
}
