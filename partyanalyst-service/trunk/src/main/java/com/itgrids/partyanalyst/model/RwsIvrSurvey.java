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
@Table(name = "rws_ivr_survey")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class RwsIvrSurvey{

	private Long rwsIvrSurveyId;
	private Long rwsIvrTypeId;
	private Date startDate;
	private Date endDate;
	private String insertedBy;
	private Date insertedTime;
	private String isDeleted;
	
	private RwsIvrType rwsIvrType;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "rws_ivr_survey_id", unique = true, nullable = false)
	public Long getRwsIvrSurveyId() {
		return rwsIvrSurveyId;
	}
	public void setRwsIvrSurveyId(Long rwsIvrSurveyId) {
		this.rwsIvrSurveyId = rwsIvrSurveyId;
	}
	@Column(name = "rws_ivr_type_id")
	public Long getRwsIvrTypeId() {
		return rwsIvrTypeId;
	}
	public void setRwsIvrTypeId(Long rwsIvrTypeId) {
		this.rwsIvrTypeId = rwsIvrTypeId;
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
	@Column(name = "inserted_by")
	public String getInsertedBy() {
		return insertedBy;
	}
	public void setInsertedBy(String insertedBy) {
		this.insertedBy = insertedBy;
	}
	@Column(name = "inserted_time")
	public Date getInsertedTime() {
		return insertedTime;
	}
	public void setInsertedTime(Date insertedTime) {
		this.insertedTime = insertedTime;
	}
	@Column(name = "is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "rws_ivr_type_id", insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public RwsIvrType getRwsIvrType() {
		return rwsIvrType;
	}
	public void setRwsIvrType(RwsIvrType rwsIvrType) {
		this.rwsIvrType = rwsIvrType;
	}
}
