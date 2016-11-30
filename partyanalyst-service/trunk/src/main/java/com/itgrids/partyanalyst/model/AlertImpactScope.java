package com.itgrids.partyanalyst.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
@Entity
@Table(name = "alert_impact_scope")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AlertImpactScope implements Serializable {
	private Long alertImpactScopeId;
	private String impactScope;
	private Long orderNo;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "alert_impact_scope_id", unique = true, nullable = false)
	public Long getAlertImpactScopeId() {
		return alertImpactScopeId;
	}
	public void setAlertImpactScopeId(Long alertImpactScopeId) {
		this.alertImpactScopeId = alertImpactScopeId;
	}
	@Column(name = "impact_scope")
	public String getImpactScope() {
		return impactScope;
	}
	public void setImpactScope(String impactScope) {
		this.impactScope = impactScope;
	}
	@Column(name = "order_no")
	public Long getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Long orderNo) {
		this.orderNo = orderNo;
	}
}
