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
@Table(name = "self_appraisal_rule_program")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SelfAppraisalRuleProgram {

	private Long selfAppraisalRuleProgramId;
	private Long selfAppraisalRuleId;
	private Long selfAppraisalProgramId;
	
	private SelfAppraisalRule selfAppraisalRule;
	private SelfAppraisalProgram selfAppraisalProgram;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="self_appraisal_rule_program_id", unique=true, nullable=false)
	public Long getSelfAppraisalRuleProgramId() {
		return selfAppraisalRuleProgramId;
	}
	public void setSelfAppraisalRuleProgramId(Long selfAppraisalRuleProgramId) {
		this.selfAppraisalRuleProgramId = selfAppraisalRuleProgramId;
	}
	@Column(name = "self_appraisal_rule_id")
	public Long getSelfAppraisalRuleId() {
		return selfAppraisalRuleId;
	}
	public void setSelfAppraisalRuleId(Long selfAppraisalRuleId) {
		this.selfAppraisalRuleId = selfAppraisalRuleId;
	}
	@Column(name = "self_appraisal_program_id")
	public Long getSelfAppraisalProgramId() {
		return selfAppraisalProgramId;
	}
	public void setSelfAppraisalProgramId(Long selfAppraisalProgramId) {
		this.selfAppraisalProgramId = selfAppraisalProgramId;
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
