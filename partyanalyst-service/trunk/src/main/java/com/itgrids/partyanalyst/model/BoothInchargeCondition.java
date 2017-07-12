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
@Table(name = "booth_incharge_condition")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class BoothInchargeCondition {

	private Long boothInchargeConditionId;
	private String conditionName;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "booth_incharge_condition_id", unique = true, nullable = false)
	public Long getBoothInchargeConditionId() {
		return boothInchargeConditionId;
	}
	public void setBoothInchargeConditionId(Long boothInchargeConditionId) {
		this.boothInchargeConditionId = boothInchargeConditionId;
	}
	
	@Column(name="condition_name")
	public String getConditionName() {
		return conditionName;
	}
	public void setConditionName(String conditionName) {
		this.conditionName = conditionName;
	}
	
	
}
