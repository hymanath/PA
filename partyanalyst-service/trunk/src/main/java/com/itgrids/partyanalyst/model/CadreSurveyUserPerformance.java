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
@Table(name = "cadre_survey_user_performance")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CadreSurveyUserPerformance extends BaseModel implements Serializable {
	
	private Long cadreSurveyUserPerformanceId;
	private Long cadreSurveyUserId;
	private Long cadreSurveyUserPerformanceTypeId;
	private Date insertedTime;
	private Date updatedTime;
	private String isDeleted;
	private Date surveyTime;
	private Long   surveyHourNo;
	private  Long createdBy;
	private Long updatedBy;
	private String comment;
	
	private CadreSurveyUser cadreSurveyUser;
	private CadreSurveyUserPerformanceType cadreSurveyUserPerformanceType;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="cadre_survey_user_performance_id", unique=true, nullable=false)
	public Long getCadreSurveyUserPerformanceId() {
		return cadreSurveyUserPerformanceId;
	}
	public void setCadreSurveyUserPerformanceId(Long cadreSurveyUserPerformanceId) {
		this.cadreSurveyUserPerformanceId = cadreSurveyUserPerformanceId;
	}
	
	@Column(name = "cadre_survey_user_id", length = 70)
	public Long getCadreSurveyUserId() {
		return cadreSurveyUserId;
	}
	public void setCadreSurveyUserId(Long cadreSurveyUserId) {
		this.cadreSurveyUserId = cadreSurveyUserId;
	}
	
	@Column(name = "cadre_survey_user_performance_type_id", length = 70)
	public Long getCadreSurveyUserPerformanceTypeId() {
		return cadreSurveyUserPerformanceTypeId;
	}
	public void setCadreSurveyUserPerformanceTypeId(Long cadreSurveyUserPerformanceTypeId) {
		this.cadreSurveyUserPerformanceTypeId = cadreSurveyUserPerformanceTypeId;
	}
	
	@Column(name = "inserted_time", length = 70)
	public Date getInsertedTime() {
		return insertedTime;
	}
	public void setInsertedTime(Date insertedTime) {
		this.insertedTime = insertedTime;
	}
	
	@Column(name = "updated_time", length = 70)
	public Date getUpdatedTime() {
		return updatedTime;
	}
	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}
	
	@Column(name = "is_deleted", length = 70)
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	@Column(name = "survey_time" , length = 70)
	public Date getSurveyTime() {
		return surveyTime;
	}
	public void setSurveyTime(Date surveyTime) {
		this.surveyTime = surveyTime;
	}
	
	@Column(name = "survey_hour_no" , length = 70)
	public Long getSurveyHourNo() {
		return surveyHourNo;
	}
	public void setSurveyHourNo(Long surveyHourNo) {
		this.surveyHourNo = surveyHourNo;
	}
	
	@Column(name = "created_by" , length = 70)
	public Long getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}
	
	@Column(name = "updated_by" , length = 70)
	public Long getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
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
	@JoinColumn(name = "cadre_survey_user_performance_type_id" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public CadreSurveyUserPerformanceType getCadreSurveyUserPerformanceType() {
		return cadreSurveyUserPerformanceType;
	}
	public void setCadreSurveyUserPerformanceType(CadreSurveyUserPerformanceType cadreSurveyUserPerformanceType) {
		this.cadreSurveyUserPerformanceType = cadreSurveyUserPerformanceType;
	}
	
	@Column(name = "comment" , length = 70)
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	
	

}
