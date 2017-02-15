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
@Table(name = "committee_confirm_rule_condition")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CommitteeConfirmRuleCondition extends BaseModel{
	
	private Long committeeConfirmRuleConditionId;
	private Long committeeConfirmRuleId;
	private Long tdpRolesId;
	private Long minPositions;
	
	private CommitteeConfirmRule committeeConfirmRule;
	private TdpRoles tdpRoles;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "committee_confirm_rule_condition_id", unique = true, nullable = false)
	public Long getCommitteeConfirmRuleConditionId() {
		return committeeConfirmRuleConditionId;
	}
	public void setCommitteeConfirmRuleConditionId(
			Long committeeConfirmRuleConditionId) {
		this.committeeConfirmRuleConditionId = committeeConfirmRuleConditionId;
	}
	
	@Column(name = "committee_confirm_rule_id")
	public Long getCommitteeConfirmRuleId() {
		return committeeConfirmRuleId;
	}
	public void setCommitteeConfirmRuleId(Long committeeConfirmRuleId) {
		this.committeeConfirmRuleId = committeeConfirmRuleId;
	}
	
	
	@Column(name = "tdp_roles_id")
	public Long getTdpRolesId() {
		return tdpRolesId;
	}
	public void setTdpRolesId(Long tdpRolesId) {
		this.tdpRolesId = tdpRolesId;
	}
	
	@Column(name = "min_positions")
	public Long getMinPositions() {
		return minPositions;
	}
	public void setMinPositions(Long minPositions) {
		this.minPositions = minPositions;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="committee_confirm_rule_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public CommitteeConfirmRule getCommitteeConfirmRule() {
		return committeeConfirmRule;
	}
	public void setCommitteeConfirmRule(CommitteeConfirmRule committeeConfirmRule) {
		this.committeeConfirmRule = committeeConfirmRule;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="tdp_roles_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public TdpRoles getTdpRoles() {
		return tdpRoles;
	}
	public void setTdpRoles(TdpRoles tdpRoles) {
		this.tdpRoles = tdpRoles;
	}
	
	
	
}
