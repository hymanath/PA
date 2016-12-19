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
@Table(name="self_appraisal_designation_target")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SelfAppraisalDesignationTarget {
   /*
    * Author:santosh
    */
	private Long selfAppraisalDesignationTargetId;
	private Long selfAppraisalDesignationId;
	private Date startTime;
	private Date endTime;
	private Long targetDays;
	private Long selfAppraisalTourCategoryId;
	private Long tourTypeId;
	private String isActive;

	private SelfAppraisalDesignation selfAppraisalDesignation;
	private SelfAppraisalTourCategory selfAppraisalTourCategory;
	private TourType tourType;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="self_appraisal_designation_target_id", unique=true, nullable=false)
	public Long getSelfAppraisalDesignationTargetId() {
		return selfAppraisalDesignationTargetId;
	}
	public void setSelfAppraisalDesignationTargetId(
			Long selfAppraisalDesignationTargetId) {
		this.selfAppraisalDesignationTargetId = selfAppraisalDesignationTargetId;
	}
	@Column(name="self_appraisal_designation_id")
	public Long getSelfAppraisalDesignationId() {
		return selfAppraisalDesignationId;
	}
	public void setSelfAppraisalDesignationId(Long selfAppraisalDesignationId) {
		this.selfAppraisalDesignationId = selfAppraisalDesignationId;
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
	@Column(name="target_days")
	public Long getTargetDays() {
		return targetDays;
	}
	public void setTargetDays(Long targetDays) {
		this.targetDays = targetDays;
	}
	@Column(name="self_appraisal_tour_category_id")
	public Long getSelfAppraisalTourCategoryId() {
		return selfAppraisalTourCategoryId;
	}
	public void setSelfAppraisalTourCategoryId(Long selfAppraisalTourCategoryId) {
		this.selfAppraisalTourCategoryId = selfAppraisalTourCategoryId;
	}
	@Column(name = "is_active")
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	@Column(name = "tour_type_id")
	public Long getTourTypeId() {
		return tourTypeId;
	}
	public void setTourTypeId(Long tourTypeId) {
		this.tourTypeId = tourTypeId;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="tour_type_id",insertable=false,updatable=false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public TourType getTourType() {
		return tourType;
	}
	public void setTourType(TourType tourType) {
		this.tourType = tourType;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="self_appraisal_designation_id",insertable=false,updatable=false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public SelfAppraisalDesignation getSelfAppraisalDesignation() {
		return selfAppraisalDesignation;
	}
	public void setSelfAppraisalDesignation(
			SelfAppraisalDesignation selfAppraisalDesignation) {
		this.selfAppraisalDesignation = selfAppraisalDesignation;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="self_appraisal_tour_category_id",insertable=false,updatable=false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public SelfAppraisalTourCategory getSelfAppraisalTourCategory() {
		return selfAppraisalTourCategory;
	}
	public void setSelfAppraisalTourCategory(
			SelfAppraisalTourCategory selfAppraisalTourCategory) {
		this.selfAppraisalTourCategory = selfAppraisalTourCategory;
	}
	
	
}
