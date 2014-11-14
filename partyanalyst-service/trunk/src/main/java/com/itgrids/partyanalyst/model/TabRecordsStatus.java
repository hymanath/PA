package com.itgrids.partyanalyst.model;

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

/**
 * 
 * @author Srishailam Pittala
 *
 */

@Entity
@Table(name = "tab_records_status")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TabRecordsStatus implements java.io.Serializable{

	private Long tabRecordsStatusId;
	private CadreSurveyUser cadreSurveyUser;
	private Long cadreSurveyUserId;
	private Long completedRecords;
	private Long pendingRecords;
	private String tabIMEINo;
	private Date insertedTime;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "tab_records_status_id", unique = true, nullable = false)
	public Long getTabRecordsStatusId() {
		return tabRecordsStatusId;
	}
	public void setTabRecordsStatusId(Long tabRecordsStatusId) {
		this.tabRecordsStatusId = tabRecordsStatusId;
	}
	
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "cadre_survey_userId" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public CadreSurveyUser getCadreSurveyUser() {
		return cadreSurveyUser;
	}
	public void setCadreSurveyUser(CadreSurveyUser cadreSurveyUser) {
		this.cadreSurveyUser = cadreSurveyUser;
	}
	
	@Column(name="cadre_survey_userId")
	public Long getCadreSurveyUserId() {
		return cadreSurveyUserId;
	}
	public void setCadreSurveyUserId(Long cadreSurveyUserId) {
		this.cadreSurveyUserId = cadreSurveyUserId;
	}
	
	@Column(name="completed_records")	
	public Long getCompletedRecords() {
		return completedRecords;
	}
	public void setCompletedRecords(Long completedRecords) {
		this.completedRecords = completedRecords;
	}
	@Column(name="pending_records")
	public Long getPendingRecords() {
		return pendingRecords;
	}
	public void setPendingRecords(Long pendingRecords) {
		this.pendingRecords = pendingRecords;
	}
	
	@Column(name="tab_imei_no")
	public String getTabIMEINo() {
		return tabIMEINo;
	}
	public void setTabIMEINo(String tabIMEINo) {
		this.tabIMEINo = tabIMEINo;
	}
	
	@Column(name="inserted_time")
	public Date getInsertedTime() {
		return insertedTime;
	}
	public void setInsertedTime(Date insertedTime) {
		this.insertedTime = insertedTime;
	}
	
}
