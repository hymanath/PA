/* 
 * Copyright (c) 2010 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on August 10,2010
 */
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
@Table(name = "user_group_relation")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UserGroupRelation extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long userGroupRelationId;
	private Registration user;
	private UserGroups userGroup;
	
	/** Default Constructor */
	public UserGroupRelation(){
		super();
	}
	
	/** Parameterized Constructor */
	public UserGroupRelation(Long userGroupRelationId, Registration user,
			UserGroups userGroup) {
		super();
		this.userGroupRelationId = userGroupRelationId;
		this.user = user;
		this.userGroup = userGroup;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_group_relation_id", unique = true, nullable = false)
	public Long getUserGroupRelationId() {
		return userGroupRelationId;
	}

	public void setUserGroupRelationId(Long userGroupRelationId) {
		this.userGroupRelationId = userGroupRelationId;
	}

	@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Registration getUser() {
		return user;
	}

	public void setUser(Registration user) {
		this.user = user;
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

}
