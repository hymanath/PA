/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on Feb 8, 2010
 * Author Saikrishna g
 */

package com.itgrids.partyanalyst.model;

import java.io.Serializable;
import java.util.Date;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name = "user_group_privileges")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UserGroupPrivileges implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long userGroupPrivilegeId;
	private PersonalUserGroup personalUserGroup;
	private User readRightUser;
	private User writeRightUser;
	private Date updatedDate;
	private Date createdDate;

	// constructors

	/** default constructors */
	public UserGroupPrivileges() {
	}

	/** one argument constructors */
	public UserGroupPrivileges(Long userGroupPrivilegeId) {
		this.userGroupPrivilegeId = userGroupPrivilegeId;
	}

	public UserGroupPrivileges(Long userGroupPrivilegeId,
			PersonalUserGroup personalUserGroup, User readRightUser,
			User writeRightUser, Date createdDate, Date updatedDate) {

		this.userGroupPrivilegeId = userGroupPrivilegeId;
		this.personalUserGroup = personalUserGroup;
		this.readRightUser = readRightUser;
		this.writeRightUser = writeRightUser;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_group_privilege_id", unique = true, nullable = false)
	public Long getUserGroupPrivilegeId() {
		return userGroupPrivilegeId;
	}

	public void setUserGroupPrivilegeId(Long userGroupPrivilegeId) {
		this.userGroupPrivilegeId = userGroupPrivilegeId;
	}

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "personal_user_group_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public PersonalUserGroup getPersonalUserGroup() {
		return personalUserGroup;
	}

	public void setPersonalUserGroup(PersonalUserGroup personalUserGroup) {
		this.personalUserGroup = personalUserGroup;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_date")
	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_date")
	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="read_right_user_id",updatable = false, insertable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public User getReadRightUser() {
		return readRightUser;
	}

	public void setReadRightUser(User readRightUser) {
		this.readRightUser = readRightUser;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="write_right_user_id",updatable = false, insertable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public User getWriteRightUser() {
		return writeRightUser;
	}

	public void setWriteRightUser(User writeRightUser) {
		this.writeRightUser = writeRightUser;
	}

}