/* 
 * Copyright (c) 2010 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on August 10,2010
 */
package com.itgrids.partyanalyst.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name = "group_entitlement")
public class GroupEntitlement extends BaseModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long groupEntitlementId;
	private Entitlement entitlement;
	private Set<UserGroupEntitlement> userGroupEntitlement = new HashSet<UserGroupEntitlement>(0);
	private String description;
	
	/** Default Constructor */
    public GroupEntitlement(){
    	super();
    }
	
	/** Parameterized Constructor */
	public GroupEntitlement(Long groupEntitlementId, Entitlement entitlement) {
		this.groupEntitlementId = groupEntitlementId;
		this.entitlement = entitlement;
	}

	/** Parameterized Constructor */
	public GroupEntitlement(Long groupEntitlementId, Entitlement entitlement,
			Set<UserGroupEntitlement> userGroupEntitlement) {
		super();
		this.groupEntitlementId = groupEntitlementId;
		this.entitlement = entitlement;
		this.userGroupEntitlement = userGroupEntitlement;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "group_entitlement_id", unique = true, nullable = false)
	public Long getGroupEntitlementId() {
		return groupEntitlementId;
	}

	public void setGroupEntitlementId(Long groupEntitlementId) {
		this.groupEntitlementId = groupEntitlementId;
	}

	@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "entitlement_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Entitlement getEntitlement() {
		return entitlement;
	}

	public void setEntitlement(Entitlement entitlement) {
		this.entitlement = entitlement;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "groupEntitlement")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<UserGroupEntitlement> getUserGroupEntitlement() {
		return userGroupEntitlement;
	}

	public void setUserGroupEntitlement(
			Set<UserGroupEntitlement> userGroupEntitlement) {
		this.userGroupEntitlement = userGroupEntitlement;
	}

	@Column(name = "description", length = 250)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
