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
@Table(name="govt_department_scope")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class GovtDepartmentScope extends BaseModel implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Long govtDepartmentScopeId;
	private String levelName;
	private Long alertRegionScopesId;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="govt_department_scope_id", unique = true, nullable = false)
	public Long getGovtDepartmentScopeId() {
		return govtDepartmentScopeId;
	}
	public void setGovtDepartmentScopeId(Long govtDepartmentScopeId) {
		this.govtDepartmentScopeId = govtDepartmentScopeId;
	}
	@Column(name="level_name")
	public String getLevelName() {
		return levelName;
	}
	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}
	@Column(name="alert_region_scopes_id")
	public Long getAlertRegionScopesId() {
		return alertRegionScopesId;
	}
	public void setAlertRegionScopesId(Long alertRegionScopesId) {
		this.alertRegionScopesId = alertRegionScopesId;
	}
	
}
