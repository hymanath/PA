package com.itgrids.partyanalyst.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "self_appraisal_rule")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SelfAppraisalRule {

	private Long selfAppraisalRuleId;
	private String ruleName;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="self_appraisal_rule_id", unique=true, nullable=false)
	public Long getSelfAppraisalRuleId() {
		return selfAppraisalRuleId;
	}
	public void setSelfAppraisalRuleId(Long selfAppraisalRuleId) {
		this.selfAppraisalRuleId = selfAppraisalRuleId;
	}
	@Column(name = "rule_name")
	public String getRuleName() {
		return ruleName;
	}
	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}
	
	
}
