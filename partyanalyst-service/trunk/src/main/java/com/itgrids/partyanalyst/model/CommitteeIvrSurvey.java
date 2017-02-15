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
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name = "committee_ivr_survey")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CommitteeIvrSurvey {
	
	
	private Long   committeeIvrSurveyId;
	private String surveyName;
	private Long   tdpCommitteeId;
	private Date   startDate;
	private Date   endDate;
	private String isDeleted;
	private Long   insertedBy;
	private Long   updatedBy;
	private Date   insertedTime;
	private Date   updatedTime;
	
	private TdpCommittee tdpCommittee;
	private User   insertedUser;
	private User   updatesUser;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "committee_ivr_survey_id", unique = true, nullable = false)
	public Long getCommitteeIvrSurveyId() {
		return committeeIvrSurveyId;
	}
	public void setCommitteeIvrSurveyId(Long committeeIvrSurveyId) {
		this.committeeIvrSurveyId = committeeIvrSurveyId;
	}
	
	@Column(name = "survey_name")
	public String getSurveyName() {
		return surveyName;
	}
	public void setSurveyName(String surveyName) {
		this.surveyName = surveyName;
	}
	//committee_ivr_survey_id,survey_name,tdp_committee_id,start_date,end_date,is_deleted,inserted_by,updated_by,inserted_time,updated_time
	
	@Column(name = "tdp_committee_id")
	public Long getTdpCommitteeId() {
		return tdpCommitteeId;
	}
	public void setTdpCommitteeId(Long tdpCommitteeId) {
		this.tdpCommitteeId = tdpCommitteeId;
	}
	
	@Column(name = "start_date")
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	@Column(name = "end_date")
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	@Column(name = "is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	@Column(name = "inserted_by")
	public Long getInsertedBy() {
		return insertedBy;
	}
	public void setInsertedBy(Long insertedBy) {
		this.insertedBy = insertedBy;
	}
	
	@Column(name = "updated_by")
	public Long getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
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
	
	@ManyToOne(cascade = CascadeType.ALL , fetch = FetchType.LAZY)
	@JoinColumn(name="tdp_committee_id" , insertable = false , updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public TdpCommittee getTdpCommittee() {
		return tdpCommittee;
	}
	public void setTdpCommittee(TdpCommittee tdpCommittee) {
		this.tdpCommittee = tdpCommittee;
	}
	
	@ManyToOne(cascade = CascadeType.ALL , fetch = FetchType.LAZY)
	@JoinColumn(name="inserted_by" , insertable = false , updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public User getInsertedUser() {
		return insertedUser;
	}
	public void setInsertedUser(User insertedUser) {
		this.insertedUser = insertedUser;
	}
	
	@ManyToOne(cascade = CascadeType.ALL , fetch = FetchType.LAZY)
	@JoinColumn(name="updated_by" , insertable = false , updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public User getUpdatesUser() {
		return updatesUser;
	}
	public void setUpdatesUser(User updatesUser) {
		this.updatesUser = updatesUser;
	}
	
	
}
