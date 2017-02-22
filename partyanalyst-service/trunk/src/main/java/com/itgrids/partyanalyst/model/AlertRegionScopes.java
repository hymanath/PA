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
@Table(name = "alert_region_scopes")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AlertRegionScopes extends BaseModel implements Serializable{

	private Long alertRegionScopesId;
	private String scope;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "alert_region_scopes_id", unique = true, nullable = false)
	public Long getAlertRegionScopesId() {
		return alertRegionScopesId;
	}
	public void setAlertRegionScopesId(Long alertRegionScopesId) {
		this.alertRegionScopesId = alertRegionScopesId;
	}
	
	@Column(name = "scope")
	public String getScope() {
		return scope;
	}
	public void setScope(String scope) {
		this.scope = scope;
	}
}
