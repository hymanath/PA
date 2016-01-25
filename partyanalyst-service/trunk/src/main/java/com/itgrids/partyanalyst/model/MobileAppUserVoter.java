package com.itgrids.partyanalyst.model;

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

@Entity
@Table(name = "mobile_app_user_voter")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class MobileAppUserVoter {
	private Long mobileAppUserVoterId;
	private Long mobileAppUserId;
	private MobileAppUser mobileAppUser;
	private Long wardId;
	private Long boothId;
	private Long voterId;
	private String mobileNo;
	private Long rating;
	private String uniqueKey;
	private String latitude;
	private String longitude;
	private Date surveyTime;
	private Date insertedTime;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "mobile_app_user_voter_id", unique = true, nullable = false)
	public Long getMobileAppUserVoterId() {
		return mobileAppUserVoterId;
	}
	public void setMobileAppUserVoterId(Long mobileAppUserVoterId) {
		this.mobileAppUserVoterId = mobileAppUserVoterId;
	}
	@Column(name = "mobile_app_user_id")
	public Long getMobileAppUserId() {
		return mobileAppUserId;
	}
	public void setMobileAppUserId(Long mobileAppUserId) {
		this.mobileAppUserId = mobileAppUserId;
	}
	@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "mobile_app_user_id",insertable=false, updatable=false)
	public MobileAppUser getMobileAppUser() {
		return mobileAppUser;
	}
	public void setMobileAppUser(MobileAppUser mobileAppUser) {
		this.mobileAppUser = mobileAppUser;
	}
	@Column(name = "ward_id")
	public Long getWardId() {
		return wardId;
	}
	public void setWardId(Long wardId) {
		this.wardId = wardId;
	}
	@Column(name = "booth_id")
	public Long getBoothId() {
		return boothId;
	}
	public void setBoothId(Long boothId) {
		this.boothId = boothId;
	}
	@Column(name = "voter_id")
	public Long getVoterId() {
		return voterId;
	}
	public void setVoterId(Long voterId) {
		this.voterId = voterId;
	}
	@Column(name = "mobile_no")
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	@Column(name = "rating")
	public Long getRating() {
		return rating;
	}
	public void setRating(Long rating) {
		this.rating = rating;
	}
	@Column(name = "unique_key")
	public String getUniqueKey() {
		return uniqueKey;
	}
	public void setUniqueKey(String uniqueKey) {
		this.uniqueKey = uniqueKey;
	}
	@Column(name = "latitude")
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	@Column(name = "longitude")
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	@Column(name = "survey_time")
	public Date getSurveyTime() {
		return surveyTime;
	}
	public void setSurveyTime(Date surveyTime) {
		this.surveyTime = surveyTime;
	}
	@Column(name = "inserted_time")
	public Date getInsertedTime() {
		return insertedTime;
	}
	public void setInsertedTime(Date insertedTime) {
		this.insertedTime = insertedTime;
	}
	
	
	
	
	

}
