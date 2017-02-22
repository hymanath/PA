package com.itgrids.partyanalyst.model;

import java.io.Serializable;

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
@Table(name = "govt_department_level")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class GovtDepartmentLevel extends BaseModel implements Serializable{

	private Long govtDepartmentLevelId;
	private String levelName;
	private Long alertRegionScopesId;
	private AlertRegionScopes alertRegionScopes;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "govt_department_level_id", unique = true, nullable = false)
	public Long getGovtDepartmentLevelId() {
		return govtDepartmentLevelId;
	}
	public void setGovtDepartmentLevelId(Long govtDepartmentLevelId) {
		this.govtDepartmentLevelId = govtDepartmentLevelId;
	}
	
	@Column(name = "level_name")
	public String getLevelName() {
		return levelName;
	}
	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}
	
	@Column(name = "alert_region_scopes_id")
	public Long getAlertRegionScopesId() {
		return alertRegionScopesId;
	}
	public void setAlertRegionScopesId(Long alertRegionScopesId) {
		this.alertRegionScopesId = alertRegionScopesId;
	}
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "alert_region_scopes_id", insertable = false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public AlertRegionScopes getAlertRegionScopes() {
		return alertRegionScopes;
	}
	public void setAlertRegionScopes(AlertRegionScopes alertRegionScopes) {
		this.alertRegionScopes = alertRegionScopes;
	}
}
