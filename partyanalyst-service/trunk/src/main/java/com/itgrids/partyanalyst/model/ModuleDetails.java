/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on December 06, 2010
 */

package com.itgrids.partyanalyst.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * ModuleDetails entity. 
 * @author <a href="mailto:raghavenderprasad@gmail.com">Raghav</a>
 */
@Entity
@Table(name = "module_details")
public class ModuleDetails extends BaseModel implements java.io.Serializable {

	/**
	 * This class contains the module names such as influencing people reg, adding new cadres, adding new political changes, adding new problems etc.
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	private Long moduleId;
	private String module;
	private String description;
	private Set<ModuleRegionScopes> moduleRegionScopes = new HashSet<ModuleRegionScopes>(0);
	
	public ModuleDetails() {			
	}

	public ModuleDetails(Long moduleId, String module, String description) {
		this.moduleId = moduleId;
		this.module = module;
		this.description = description;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "module_id", nullable = false, unique = true)	
	public Long getModuleId() {
		return moduleId;
	}

	public void setModuleId(Long moduleId) {
		this.moduleId = moduleId;
	}
	
	@Column(name = "module", length = 45 )
	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	@Column(name = "description", length = 100 )
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "module")
	public Set<ModuleRegionScopes> getModuleRegionScopes() {
		return moduleRegionScopes;
	}

	public void setModuleRegionScopes(Set<ModuleRegionScopes> moduleRegionScopes) {
		this.moduleRegionScopes = moduleRegionScopes;
	}	
}
