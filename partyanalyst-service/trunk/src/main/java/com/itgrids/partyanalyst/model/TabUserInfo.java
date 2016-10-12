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
@Table(name = "tab_user_info")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TabUserInfo implements Serializable{

	private Long tabUserInfoId;
	private String name;
	private String mobileNo;
	private String imageStr;
	private String imgPath;
	private Long tabDetailsId;
	private Long cadreSurveyUserId;
	private String isEnabled;
	private String isOtpVerified;
	private Date insertedTime;
	private Date updatedTime;
	
	private TabDetails tabDetails;
	private CadreSurveyUser cadreSurveyUser;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "tab_user_info_id", unique = true, nullable = false)
	public Long getTabUserInfoId() {
		return tabUserInfoId;
	}
	public void setTabUserInfoId(Long tabUserInfoId) {
		this.tabUserInfoId = tabUserInfoId;
	}
	
	@Column(name = "name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name = "mobile_no")
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	
	@Column(name = "image_str")
	public String getImageStr() {
		return imageStr;
	}
	public void setImageStr(String imageStr) {
		this.imageStr = imageStr;
	}
	
	@Column(name = "img_path")
	public String getImgPath() {
		return imgPath;
	}
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
	
	@Column(name = "tab_details_id")
	public Long getTabDetailsId() {
		return tabDetailsId;
	}
	public void setTabDetailsId(Long tabDetailsId) {
		this.tabDetailsId = tabDetailsId;
	}
	
	@Column(name = "cadre_survey_user_id")
	public Long getCadreSurveyUserId() {
		return cadreSurveyUserId;
	}
	public void setCadreSurveyUserId(Long cadreSurveyUserId) {
		this.cadreSurveyUserId = cadreSurveyUserId;
	}
	
	@Column(name = "is_enabled")
	public String getIsEnabled() {
		return isEnabled;
	}
	public void setIsEnabled(String isEnabled) {
		this.isEnabled = isEnabled;
	}
	
	@Column(name = "is_otp_verified")
	public String getIsOtpVerified() {
		return isOtpVerified;
	}
	public void setIsOtpVerified(String isOtpVerified) {
		this.isOtpVerified = isOtpVerified;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "tab_details_id",insertable=false,updatable=false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public TabDetails getTabDetails() {
		return tabDetails;
	}
	public void setTabDetails(TabDetails tabDetails) {
		this.tabDetails = tabDetails;
	}
	
	@Column(name = "inserted_time")
	public Date getInsertedTime() {
		return insertedTime;
	}
	public void setInsertedTime(Date insertedTime) {
		this.insertedTime = insertedTime;
	}
	
	@Column(name = "updated_time")
	public Date getUpdatedTime() {
		return updatedTime;
	}
	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "cadre_survey_user_id",insertable=false,updatable=false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public CadreSurveyUser getCadreSurveyUser() {
		return cadreSurveyUser;
	}
	public void setCadreSurveyUser(CadreSurveyUser cadreSurveyUser) {
		this.cadreSurveyUser = cadreSurveyUser;
	}
	
}

