/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on December 03, 2010
 */
package com.itgrids.partyanalyst.model;

import java.util.HashSet;
import com.itgrids.partyanalyst.model.BaseModel;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
/**
 * Occupation entity. 
 * @author <a href="mailto:raghavenderprasad@gmail.com">Raghav</a>
 */
@Entity
@Table(name = "region_scopes")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class RegionScopes extends BaseModel implements java.io.Serializable {

	/**
	 * This class contains the regional scopes which are used in influencing people, cadre mgmt, political changes, problem management etc.
	 * the scope value indicates to which extent the impact is applicable
	 */
	private static final long serialVersionUID = 1L;
	private Long regionScopesId;
	private String scope;
	private String description;
	
	//private Set<ModuleRegionScopes> moduleRegionScopes = new HashSet<ModuleRegionScopes>(0);
	
	public RegionScopes() {
	}

	public RegionScopes(Long regionScopesId, String scope, String description) {
		this.regionScopesId = regionScopesId;
		this.scope = scope;
		this.description = description;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "region_scopes_id", nullable = false, unique = true)
	public Long getRegionScopesId() {
		return regionScopesId;
	}

	public void setRegionScopesId(Long regionScopesId) {
		this.regionScopesId = regionScopesId;
	}
	
	@Column(name = "scope", length = 45 )
	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	@Column(name = "description", length = 200 )
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	/*
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "regionScope")
	public Set<ModuleRegionScopes> getModuleRegionScopes() {
		return moduleRegionScopes;
	}

	public void setModuleRegionScopes(Set<ModuleRegionScopes> moduleRegionScopes) {
		this.moduleRegionScopes = moduleRegionScopes;
	}*/

	
}
