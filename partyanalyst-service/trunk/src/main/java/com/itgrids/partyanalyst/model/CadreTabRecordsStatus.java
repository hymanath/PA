package com.itgrids.partyanalyst.model;

import java.io.Serializable;
import java.util.Date;

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
@Table(name = "cadre_tab_records_status")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CadreTabRecordsStatus extends BaseModel implements Serializable{

	private Long cadreTabRecordStatusId;
	private Long cadreSurveyUserId;
	private Long tabUserInfoId;
	private String imeiNo;
	private String lattitude;
	private String longitude;
	private Long totalRecords;
	private Long sync;
	private Long pending;
	private Date insertedTime;
	private Date minRecordTime;	
	private Date maxRecordTime;	
	private Date surveyDate;
	private String isDeleted;
	
	private CadreSurveyUser cadreSurveyUser;
	private TabUserInfo tabUserInfo;
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.AUTO)
	 @Column(name = "cadre_tab_records_status_id", unique = true, nullable = false)
	public Long getCadreTabRecordStatusId() {
		return cadreTabRecordStatusId;
	}
	public void setCadreTabRecordStatusId(Long cadreTabRecordStatusId) {
		this.cadreTabRecordStatusId = cadreTabRecordStatusId;
	}
	
	@Column(name="cadre_survey_user_id")
	public Long getCadreSurveyUserId() {
		return cadreSurveyUserId;
	}
	public void setCadreSurveyUserId(Long cadreSurveyUserId) {
		this.cadreSurveyUserId = cadreSurveyUserId;
	}
	
	@Column(name="tab_user_info_id")
	public Long getTabUserInfoId() {
		return tabUserInfoId;
	}
	public void setTabUserInfoId(Long tabUserInfoId) {
		this.tabUserInfoId = tabUserInfoId;
	}
	
	@Column(name="imei_no")
	public String getImeiNo() {
		return imeiNo;
	}
	public void setImeiNo(String imeiNo) {
		this.imeiNo = imeiNo;
	}
	@Column(name="lattitude")
	public String getLattitude() {
		return lattitude;
	}
	public void setLattitude(String lattitude) {
		this.lattitude = lattitude;
	}
	@Column(name="longitude")
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	@Column(name="total_records")
	public Long getTotalRecords() {
		return totalRecords;
	}
	public void setTotalRecords(Long totalRecords) {
		this.totalRecords = totalRecords;
	}
	@Column(name="sync")
	public Long getSync() {
		return sync;
	}
	public void setSync(Long sync) {
		this.sync = sync;
	}
	@Column(name="pending")
	public Long getPending() {
		return pending;
	}
	public void setPending(Long pending) {
		this.pending = pending;
	}
	@Column(name="is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	@Column(name="inserted_time")
	public Date getInsertedTime() {
		return insertedTime;
	}
	public void setInsertedTime(Date insertedTime) {
		this.insertedTime = insertedTime;
	}
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "cadre_survey_user_id" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public CadreSurveyUser getCadreSurveyUser() {
		return cadreSurveyUser;
	}
	public void setCadreSurveyUser(CadreSurveyUser cadreSurveyUser) {
		this.cadreSurveyUser = cadreSurveyUser;
	}
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "tab_user_info_id" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public TabUserInfo getTabUserInfo() {
		return tabUserInfo;
	}
	public void setTabUserInfo(TabUserInfo tabUserInfo) {
		this.tabUserInfo = tabUserInfo;
	}
	@Column(name="min_record_time")
	public Date getMinRecordTime() {
		return minRecordTime;
	}
	public void setMinRecordTime(Date minRecordTime) {
		this.minRecordTime = minRecordTime;
	}
	@Column(name="max_record_time")
	public Date getMaxRecordTime() {
		return maxRecordTime;
	}
	public void setMaxRecordTime(Date maxRecordTime) {
		this.maxRecordTime = maxRecordTime;
	}
	@Column(name="survey_date")
	public Date getSurveyDate() {
		return surveyDate;
	}
	public void setSurveyDate(Date surveyDate) {
		this.surveyDate = surveyDate;
	}
	
	
	
}
