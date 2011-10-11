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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name = "group_entitlement")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class GroupEntitlement extends BaseModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long groupEntitlementId;
	private Set<GroupEntitlementRelation> groupEntitlementRelations = new HashSet<GroupEntitlementRelation>(0);
	private Set<UserGroupEntitlement> userGroupEntitlement = new HashSet<UserGroupEntitlement>(0);
	private String description;
	private Set<UserEntitlementGroupRegion> userEntitlementGroupRegions = new HashSet<UserEntitlementGroupRegion>(0);
	
	/** Default Constructor */
    public GroupEntitlement(){
    	super();
    }
	
	/** Parameterized Constructor */
	public GroupEntitlement(Long groupEntitlementId) {
		this.groupEntitlementId = groupEntitlementId;
	}
	
	/** Parameterized Constructor */
	public GroupEntitlement(Long groupEntitlementId,String description) {
		this.groupEntitlementId = groupEntitlementId;
		this.description = description;
	}
	

	/** Parameterized Constructor */
	public GroupEntitlement(Long groupEntitlementId, Set<GroupEntitlementRelation> groupEntitlementRelations,
			Set<UserGroupEntitlement> userGroupEntitlement) {
		super();
		this.groupEntitlementId = groupEntitlementId;
		this.groupEntitlementRelations = groupEntitlementRelations;
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

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "groupEntitlement")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<UserGroupEntitlement> getUserGroupEntitlement() {
		return userGroupEntitlement;
	}
	
	public void setUserGroupEntitlement(
			Set<UserGroupEntitlement> userGroupEntitlement) {
		this.userGroupEntitlement = userGroupEntitlement;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "groupEntitlement")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<GroupEntitlementRelation> getGroupEntitlementRelations() {
		return groupEntitlementRelations;
	}

	public void setGroupEntitlementRelations(
			Set<GroupEntitlementRelation> groupEntitlementRelations) {
		this.groupEntitlementRelations = groupEntitlementRelations;
	}

	@Column(name = "description", length = 250)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "groupEntitlement")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<UserEntitlementGroupRegion> getUserEntitlementGroupRegions() {
		return userEntitlementGroupRegions;
	}

	public void setUserEntitlementGroupRegions(
			Set<UserEntitlementGroupRegion> userEntitlementGroupRegions) {
		this.userEntitlementGroupRegions = userEntitlementGroupRegions;
	}
	
}
