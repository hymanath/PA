/* 
 * Copyright (c) 2013 TDP PARTY .
 * All Rights Reserved.
 */
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
@Table(name = "entitlement")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Entitlement extends BaseModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2714954727269428144L;
	
	private Long entitlementId;
	private String entitlementType;
	private String name;
	private String projectType;
	
	/** Default Constructor */
	public Entitlement(){
		super();
	}
	
	/** Parameterized Constructor */
	public Entitlement(Long entitlementId) {
		this.entitlementId = entitlementId;
	}

	/** Parameterized Constructor */
	public Entitlement(Long entitlementId, String entitlementType) {
		super();
		this.entitlementId = entitlementId;
		this.entitlementType = entitlementType;
		
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "entitlement_id", unique = true, nullable = false)
	public Long getEntitlementId() {
		return entitlementId;
	}

	public void setEntitlementId(Long entitlementId) {
		this.entitlementId = entitlementId;
	}

	@Column(name = "entitlement_type", length = 25)
	public String getEntitlementType() {
		return entitlementType;
	}

	public void setEntitlementType(String entitlementType) {
		this.entitlementType = entitlementType;
	}

	@Column(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "project_type")
	public String getProjectType() {
		return projectType;
	}

	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}

	

	

}
