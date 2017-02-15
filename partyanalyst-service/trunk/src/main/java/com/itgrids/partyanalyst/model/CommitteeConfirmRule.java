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
@Table(name="committee_confirm_rule")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CommitteeConfirmRule extends BaseModel{
	
	private Long committeeConfirmRuleId;
	private String committeeConfirmRule;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="committee_confirm_rule_id", unique = true, nullable = false)
	public Long getCommitteeConfirmRuleId() {
		return committeeConfirmRuleId;
	}
	public void setCommitteeConfirmRuleId(Long committeeConfirmRuleId) {
		this.committeeConfirmRuleId = committeeConfirmRuleId;
	}
	
	@Column(name="committee_confirm_rule")
	public String getCommitteeConfirmRule() {
		return committeeConfirmRule;
	}
	public void setCommitteeConfirmRule(String committeeConfirmRule) {
		this.committeeConfirmRule = committeeConfirmRule;
	}
	
}
