/* 
 * Copyright (c) 2010 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on August 10,2010
 */
package com.itgrids.partyanalyst.model;

import java.io.Serializable;
import com.itgrids.partyanalyst.model.BaseModel;

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

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NotFoundAction;

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
	private Set<GroupEntitlementRelation> groupEntitlementRelations = new HashSet<GroupEntitlementRelation>(0);
	
	/** Default Constructor */
	public Entitlement(){
		super();
	}
	
	/** Parameterized Constructor */
	public Entitlement(Long entitlementId) {
		this.entitlementId = entitlementId;
	}

	/** Parameterized Constructor */
	public Entitlement(Long entitlementId, String entitlementType,
			Set<GroupEntitlementRelation> groupEntitlementRelations) {
		super();
		this.entitlementId = entitlementId;
		this.entitlementType = entitlementType;
		this.groupEntitlementRelations = groupEntitlementRelations;
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

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "entitlement")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<GroupEntitlementRelation> getGroupEntitlementRelations() {
		return groupEntitlementRelations;
	}

	public void setGroupEntitlementRelations(
			Set<GroupEntitlementRelation> groupEntitlementRelations) {
		this.groupEntitlementRelations = groupEntitlementRelations;
	}

	

}
