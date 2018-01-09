package com.itgrids.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "region_scopes")
public class RegionScopes {
	
	private Long regionScopesId;
	private String scope;
	private String  description;	
	private Long   parentScopeId;
	@Id
	@Column(name="region_scopes_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	public Long getRegionScopesId() {
		return regionScopesId;
	}
	public void setRegionScopesId(Long regionScopesId) {
		this.regionScopesId = regionScopesId;
	}
	@Column(name="scope")
	public String getScope() {
		return scope;
	}
	public void setScope(String scope) {
		this.scope = scope;
	}
	@Column(name="description")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Column(name="parent_scope_id")
	public Long getParentScopeId() {
		return parentScopeId;
	}
	public void setParentScopeId(Long parentScopeId) {
		this.parentScopeId = parentScopeId;
	}
}

