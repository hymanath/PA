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
@Table(name = "self_appraisal_designation_program_target")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SelfAppraisalDesignationProgramTarget {

	private Long selfAppraisalDesignationProgramTargetId;
	private Long selfAppraisalDesignationId;
	private Date startTime;
	private Date endTime;
	private Long selfAppraisalToursMonthId;
	private Long selfAppraisalProgramId;
	private Long targetDays;
	private Long isActive;
	
	private SelfAppraisalDesignation selfAppraisalDesignation;
	private SelfAppraisalToursMonth selfAppraisalToursMonth;
	private SelfAppraisalProgram selfAppraisalProgram;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="self_appraisal_designation_program_target_id", unique=true, nullable=false)
	public Long getSelfAppraisalDesignationProgramTargetId() {
		return selfAppraisalDesignationProgramTargetId;
	}
	public void setSelfAppraisalDesignationProgramTargetId(
			Long selfAppraisalDesignationProgramTargetId) {
		this.selfAppraisalDesignationProgramTargetId = selfAppraisalDesignationProgramTargetId;
	}
	@Column(name = "self_appraisal_designation_id")
	public Long getSelfAppraisalDesignationId() {
		return selfAppraisalDesignationId;
	}
	public void setSelfAppraisalDesignationId(Long selfAppraisalDesignationId) {
		this.selfAppraisalDesignationId = selfAppraisalDesignationId;
	}
	@Column(name = "start_time")
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	@Column(name = "end_time")
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	@Column(name = "self_appraisal_tours_month_id")
	public Long getSelfAppraisalToursMonthId() {
		return selfAppraisalToursMonthId;
	}
	public void setSelfAppraisalToursMonthId(Long selfAppraisalToursMonthId) {
		this.selfAppraisalToursMonthId = selfAppraisalToursMonthId;
	}
	@Column(name = "self_appraisal_program_id")
	public Long getSelfAppraisalProgramId() {
		return selfAppraisalProgramId;
	}
	public void setSelfAppraisalProgramId(Long selfAppraisalProgramId) {
		this.selfAppraisalProgramId = selfAppraisalProgramId;
	}
	@Column(name = "target_days")
	public Long getTargetDays() {
		return targetDays;
	}
	public void setTargetDays(Long targetDays) {
		this.targetDays = targetDays;
	}
	@Column(name = "is_active")
	public Long getIsActive() {
		return isActive;
	}
	public void setIsActive(Long isActive) {
		this.isActive = isActive;
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
	@JoinColumn(name="self_appraisal_tours_month_id",insertable=false,updatable=false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public SelfAppraisalToursMonth getSelfAppraisalToursMonth() {
		return selfAppraisalToursMonth;
	}
	public void setSelfAppraisalToursMonth(
			SelfAppraisalToursMonth selfAppraisalToursMonth) {
		this.selfAppraisalToursMonth = selfAppraisalToursMonth;
	}
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="self_appraisal_program_id",insertable=false,updatable=false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public SelfAppraisalProgram getSelfAppraisalProgram() {
		return selfAppraisalProgram;
	}
	public void setSelfAppraisalProgram(SelfAppraisalProgram selfAppraisalProgram) {
		this.selfAppraisalProgram = selfAppraisalProgram;
	}
	
	
}
