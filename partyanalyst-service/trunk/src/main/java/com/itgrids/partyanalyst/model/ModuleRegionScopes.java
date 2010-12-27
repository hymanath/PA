/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on December 06, 2010
 */
package com.itgrids.partyanalyst.model;

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

/**
 * ModuleRegionScopes entity. 
 * @author <a href="mailto:raghavenderprasad@gmail.com">Raghav</a>
 */
@Entity
@Table(name = "module_region_scopes")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ModuleRegionScopes  extends BaseModel implements java.io.Serializable {

	/**
	 * This class contains regional scope details for each module based on state  
	 */
	private static final long serialVersionUID = 1L;
	
	private Long moduleRegionScopesId;
	private State state;
	private ModuleDetails module;
	private RegionScopes regionScope;
	
	public ModuleRegionScopes() {	
	}

	public ModuleRegionScopes(Long moduleRegionScopesId, State state,
			ModuleDetails module, RegionScopes regionScope) {
		this.moduleRegionScopesId = moduleRegionScopesId;
		this.state = state;
		this.module = module;
		this.regionScope = regionScope;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "module_region_scopes_id", nullable = false, unique = true)	
	public Long getModuleRegionScopesId() {
		return moduleRegionScopesId;
	}

	public void setModuleRegionScopesId(Long moduleRegionScopesId) {
		this.moduleRegionScopesId = moduleRegionScopesId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="state_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)	
	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="module_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)	
	public ModuleDetails getModule() {
		return module;
	}

	public void setModule(ModuleDetails module) {
		this.module = module;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="region_scope_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)	
	public RegionScopes getRegionScope() {
		return regionScope;
	}

	public void setRegionScope(RegionScopes regionScope) {
		this.regionScope = regionScope;
	}

}
