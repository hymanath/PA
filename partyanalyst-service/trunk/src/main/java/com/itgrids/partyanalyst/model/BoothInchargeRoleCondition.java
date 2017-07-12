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
@Table(name = "booth_incharge_role_condition")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class BoothInchargeRoleCondition {

	private Long boothInchargeRoleConditionId;
	private Long boothInchargeConditionId;
	private Long boothInchargeRoleId;
	private String minMembers;
	private String maxMembers;
	
	private BoothInchargeCondition boothInchargeCondition;
	private BoothInchargeRole boothInchargeRole;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "booth_incharge_role_condition_id", unique = true, nullable = false)
	public Long getBoothInchargeRoleConditionId() {
		return boothInchargeRoleConditionId;
	}
	public void setBoothInchargeRoleConditionId(Long boothInchargeRoleConditionId) {
		this.boothInchargeRoleConditionId = boothInchargeRoleConditionId;
	}
	
	@Column(name="booth_incharge_condition_id")
	public Long getBoothInchargeConditionId() {
		return boothInchargeConditionId;
	}
	public void setBoothInchargeConditionId(Long boothInchargeConditionId) {
		this.boothInchargeConditionId = boothInchargeConditionId;
	}
	
	@Column(name="booth_incharge_role_id")
	public Long getBoothInchargeRoleId() {
		return boothInchargeRoleId;
	}
	public void setBoothInchargeRoleId(Long boothInchargeRoleId) {
		this.boothInchargeRoleId = boothInchargeRoleId;
	}
	
	@Column(name="min_members")
	public String getMinMembers() {
		return minMembers;
	}
	public void setMinMembers(String minMembers) {
		this.minMembers = minMembers;
	}
	
	@Column(name="max_members")
	public String getMaxMembers() {
		return maxMembers;
	}
	public void setMaxMembers(String maxMembers) {
		this.maxMembers = maxMembers;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="booth_incharge_condition_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public BoothInchargeCondition getBoothInchargeCondition() {
		return boothInchargeCondition;
	}
	public void setBoothInchargeCondition(
			BoothInchargeCondition boothInchargeCondition) {
		this.boothInchargeCondition = boothInchargeCondition;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="booth_incharge_role_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public BoothInchargeRole getBoothInchargeRole() {
		return boothInchargeRole;
	}
	public void setBoothInchargeRole(BoothInchargeRole boothInchargeRole) {
		this.boothInchargeRole = boothInchargeRole;
	}
	
	
	
}
