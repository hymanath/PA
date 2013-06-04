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
@Table(name = "user_groups")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UserGroups extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long userGroupId;
	private String notes;
	private Set<UserGroupEntitlement> userGroupEntitlement = new HashSet<UserGroupEntitlement>(0);
	//private Set<UserGroupRelation> userGroupRelation = new HashSet<UserGroupRelation>(0);
	
	/** Default Constructor */
	public UserGroups(){
		super();
	}
	
	/** Parameterized Constructor */
	public UserGroups(Long userGroupId, String notes) {
		this.userGroupId = userGroupId;
		this.notes = notes;
	}

	/** Parameterized Constructor */
	public UserGroups(Long userGroupId, String notes,
			Set<UserGroupEntitlement> userGroupEntitlement) {
		super();
		this.userGroupId = userGroupId;
		this.notes = notes;
		this.userGroupEntitlement = userGroupEntitlement;
	}

	/*public UserGroups(Long userGroupId, String notes,
			Set<UserGroupEntitlement> userGroupEntitlement,
			Set<UserGroupRelation> userGroupRelation) {
		super();
		this.userGroupId = userGroupId;
		this.notes = notes;
		this.userGroupEntitlement = userGroupEntitlement;
		this.userGroupRelation = userGroupRelation;
	}
*/
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_group_id", unique = true, nullable = false)
	public Long getUserGroupId() {
		return userGroupId;
	}

	public void setUserGroupId(Long userGroupId) {
		this.userGroupId = userGroupId;
	}

	@Column(name = "notes", length = 250)
	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userGroup")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<UserGroupEntitlement> getUserGroupEntitlement() {
		return userGroupEntitlement;
	}

	public void setUserGroupEntitlement(
			Set<UserGroupEntitlement> userGroupEntitlement) {
		this.userGroupEntitlement = userGroupEntitlement;
	}

	/*@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userGroup")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<UserGroupRelation> getUserGroupRelation() {
		return userGroupRelation;
	}

	public void setUserGroupRelation(Set<UserGroupRelation> userGroupRelation) {
		this.userGroupRelation = userGroupRelation;
	}*/

}
