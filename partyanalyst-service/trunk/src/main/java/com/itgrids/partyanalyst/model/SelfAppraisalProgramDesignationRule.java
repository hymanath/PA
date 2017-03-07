package com.itgrids.partyanalyst.model;

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
@Table(name = "self_appraisal_program_designation_rule")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SelfAppraisalProgramDesignationRule {

	private Long selfAppraisalProgramDesignationRuleId;
	private Long selfAppraisalDesignationId;
	private Long selfAppraisalToursMonthId;
	private Long selfAppraisalRuleId;
	private Long tourVisits;
	
	private SelfAppraisalDesignation selfAppraisalDesignation;
	private SelfAppraisalToursMonth selfAppraisalToursMonth;
	private SelfAppraisalRule selfAppraisalRule;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="self_appraisal_program_designation_rule_id", unique=true, nullable=false)
	public Long getSelfAppraisalProgramDesignationRuleId() {
		return selfAppraisalProgramDesignationRuleId;
	}
	public void setSelfAppraisalProgramDesignationRuleId(
			Long selfAppraisalProgramDesignationRuleId) {
		this.selfAppraisalProgramDesignationRuleId = selfAppraisalProgramDesignationRuleId;
	}
	@Column(name = "self_appraisal_designation_id")
	public Long getSelfAppraisalDesignationId() {
		return selfAppraisalDesignationId;
	}
	public void setSelfAppraisalDesignationId(Long selfAppraisalDesignationId) {
		this.selfAppraisalDesignationId = selfAppraisalDesignationId;
	}
	@Column(name = "self_appraisal_tours_month_id")
	public Long getSelfAppraisalToursMonthId() {
		return selfAppraisalToursMonthId;
	}
	public void setSelfAppraisalToursMonthId(Long selfAppraisalToursMonthId) {
		this.selfAppraisalToursMonthId = selfAppraisalToursMonthId;
	}
	@Column(name = "self_appraisal_rule_id")
	public Long getSelfAppraisalRuleId() {
		return selfAppraisalRuleId;
	}
	public void setSelfAppraisalRuleId(Long selfAppraisalRuleId) {
		this.selfAppraisalRuleId = selfAppraisalRuleId;
	}
	@Column(name = "tour_visits")
	public Long getTourVisits() {
		return tourVisits;
	}
	public void setTourVisits(Long tourVisits) {
		this.tourVisits = tourVisits;
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
	@JoinColumn(name="self_appraisal_rule_id",insertable=false,updatable=false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public SelfAppraisalRule getSelfAppraisalRule() {
		return selfAppraisalRule;
	}
	public void setSelfAppraisalRule(SelfAppraisalRule selfAppraisalRule) {
		this.selfAppraisalRule = selfAppraisalRule;
	}
	
	
}
