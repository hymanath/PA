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
@Table(name = "user_group_entitlement")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UserGroupEntitlement extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long userGroupEntilementId;
	private UserGroups userGroup;
	private GroupEntitlement groupEntitlement;
	
	/** Default Constructor */
	public UserGroupEntitlement(){
		
	}
	
	/** Parameterized Constructor */
	public UserGroupEntitlement(Long userGroupEntilementId,
			UserGroups userGroup, GroupEntitlement groupEntitlement) {
		this.userGroupEntilementId = userGroupEntilementId;
		this.userGroup = userGroup;
		this.groupEntitlement = groupEntitlement;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_group_entitlement_id", unique = true, nullable = false)
	public Long getUserGroupEntilementId() {
		return userGroupEntilementId;
	}

	public void setUserGroupEntilementId(Long userGroupEntilementId) {
		this.userGroupEntilementId = userGroupEntilementId;
	}

	@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "user_group_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public UserGroups getUserGroup() {
		return userGroup;
	}

	public void setUserGroup(UserGroups userGroup) {
		this.userGroup = userGroup;
	}

	@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "group_entitlement_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public GroupEntitlement getGroupEntitlement() {
		return groupEntitlement;
	}

	public void setGroupEntitlement(GroupEntitlement groupEntitlement) {
		this.groupEntitlement = groupEntitlement;
	}

}
