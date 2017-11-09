package com.itgrids.partyanalyst.model;

import java.io.Serializable;

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
@Table(name = "jb_committee_confirm_rule_condition")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class JbCommitteeConfirmRuleCondition extends BaseModel implements java.io.Serializable {
	
	
	private Long jbCommitteeConfirmRuleConditionId;
	private Long jbCommitteeConfirmRuleId;
	private Long  casteCategoryId;
	private Long minMember;
	private Long maxMember;
	private String isDeleted;
	
	private JbCommitteeConfirmRule jbCommitteeConfirmRule;
	private CasteCategory casteCategory;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "jb_committee_confirm_rule_condition_id", unique = true, nullable = false)
	public Long getJbCommitteeConfirmRuleConditionId() {
		return jbCommitteeConfirmRuleConditionId;
	}
	public void setJbCommitteeConfirmRuleConditionId(
			Long jbCommitteeConfirmRuleConditionId) {
		this.jbCommitteeConfirmRuleConditionId = jbCommitteeConfirmRuleConditionId;
	}
	@Column(name="jb_committee_confirm_rule_id")
	public Long getJbCommitteeConfirmRuleId() {
		return jbCommitteeConfirmRuleId;
	}
	public void setJbCommitteeConfirmRuleId(Long jbCommitteeConfirmRuleId) {
		this.jbCommitteeConfirmRuleId = jbCommitteeConfirmRuleId;
	}
	@Column(name="caste_category_id")
	public Long getCasteCategoryId() {
		return casteCategoryId;
	}
	public void setCasteCategoryId(Long casteCategoryId) {
		this.casteCategoryId = casteCategoryId;
	}
	@Column(name="min_member")
	public Long getMinMember() {
		return minMember;
	}
	public void setMinMember(Long minMember) {
		this.minMember = minMember;
	}
	@Column(name="max_member")
	public Long getMaxMember() {
		return maxMember;
	}
	public void setMaxMember(Long maxMember) {
		this.maxMember = maxMember;
	}
	@Column(name="is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "jb_committee_confirm_rule_id" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public JbCommitteeConfirmRule getJbCommitteeConfirmRule() {
		return jbCommitteeConfirmRule;
	}
	public void setJbCommitteeConfirmRule(
			JbCommitteeConfirmRule jbCommitteeConfirmRule) {
		this.jbCommitteeConfirmRule = jbCommitteeConfirmRule;
	}
	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "caste_category_id" , insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public CasteCategory getCasteCategory() {
		return casteCategory;
	}
	public void setCasteCategory(CasteCategory casteCategory) {
		this.casteCategory = casteCategory;
	}
	

}
