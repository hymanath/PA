/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on Feb, 2010
 * Author Saikrishna.g
 */
package com.itgrids.partyanalyst.model;

import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name = "personal_user_group")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class PersonalUserGroup implements java.io.Serializable {
	/**
	 * 
	 */

	private static final long serialVersionUID = -6936019384483724909L;
	private Long personalUserGroupId;
	private String groupName;
	private String description;
	private StaticGroup staticGroup;
	private PersonalUserGroup parentGroupId;
	private String parentGroupName;
	private Registration createdUserId;
	private Set<UserGroupPrivileges> userGroupPrivileges = new HashSet<UserGroupPrivileges>(
			0);
	private Date updatedDate;
	private Date createdDate;
	private MyGroup myGroup;
	private StaticLocalGroup staticLocalGroup;
	private LocalGroupRegion localGroupRegion;
	private Set<StaticUserGroup> staticUserGroups = new HashSet<StaticUserGroup>(
			0);

	// constructors

	/** default constructors */
	public PersonalUserGroup() {
	}

	/** one parameter constructors */
	public PersonalUserGroup(Long personalUserGroupId) {

		this.personalUserGroupId = personalUserGroupId;
	}

	public PersonalUserGroup(Long personalUserGroupId, String groupName,
			String description, StaticGroup staticGroup,
			PersonalUserGroup parentGroupId, Registration createdUserId,
			Set<UserGroupPrivileges> userGroupPrivileges, Date createdDate,
			String parentGroupName, MyGroup myGroup,
			Set<StaticUserGroup> staticUserGroups) {

		this.personalUserGroupId = personalUserGroupId;
		this.groupName = groupName;
		this.description = description;
		this.staticGroup = staticGroup;
		this.parentGroupId = parentGroupId;
		this.createdUserId = createdUserId;
		this.userGroupPrivileges = userGroupPrivileges;
		this.createdDate = createdDate;
		this.parentGroupName = parentGroupName;
		this.myGroup = myGroup;
		this.staticUserGroups = staticUserGroups;
	}

	public PersonalUserGroup(String groupName, String description,
			StaticGroup staticGroup, PersonalUserGroup parentGroupId,
			String parentGroupName, Registration createdUserId,
			Set<UserGroupPrivileges> userGroupPrivileges, Date updatedDate,
			Date createdDate, MyGroup myGroup,
			StaticLocalGroup staticLocalGroup,
			Set<StaticUserGroup> staticUserGroups,
			LocalGroupRegion localGroupRegion) {
		super();
		this.groupName = groupName;
		this.description = description;
		this.staticGroup = staticGroup;
		this.parentGroupId = parentGroupId;
		this.parentGroupName = parentGroupName;
		this.createdUserId = createdUserId;
		this.userGroupPrivileges = userGroupPrivileges;
		this.updatedDate = updatedDate;
		this.createdDate = createdDate;
		this.myGroup = myGroup;
		this.staticLocalGroup = staticLocalGroup;
		this.staticUserGroups = staticUserGroups;
		this.localGroupRegion = localGroupRegion;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "personal_user_group_id", unique = true, nullable = false)
	public Long getPersonalUserGroupId() {
		return personalUserGroupId;
	}

	public void setPersonalUserGroupId(Long personalUserGroupId) {
		this.personalUserGroupId = personalUserGroupId;
	}

	@Column(name = "group_name", length = 100)
	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	@Column(name = "description", length = 250)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "static_group_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public StaticGroup getStaticGroup() {
		return staticGroup;
	}

	public void setStaticGroup(StaticGroup staticGroup) {
		this.staticGroup = staticGroup;
	}

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_group_id")
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public PersonalUserGroup getParentGroupId() {
		return parentGroupId;
	}

	public void setParentGroupId(PersonalUserGroup parentGroupId) {
		this.parentGroupId = parentGroupId;
	}

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "created_user_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public Registration getCreatedUserId() {
		return createdUserId;
	}

	public void setCreatedUserId(Registration createdUserId) {
		this.createdUserId = createdUserId;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "personalUserGroup")
	public Set<UserGroupPrivileges> getUserGroupPrivileges() {
		return userGroupPrivileges;
	}

	public void setUserGroupPrivileges(
			Set<UserGroupPrivileges> userGroupPrivileges) {
		this.userGroupPrivileges = userGroupPrivileges;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_date")
	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	@Column(name = "parent_group_name", length = 100)
	public String getParentGroupName() {
		return parentGroupName;
	}

	public void setParentGroupName(String parentGroupName) {
		this.parentGroupName = parentGroupName;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_date")
	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "my_group_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action = NotFoundAction.IGNORE)
	public MyGroup getMyGroup() {
		return myGroup;
	}

	public void setMyGroup(MyGroup myGroup) {
		this.myGroup = myGroup;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "personalUserGroup")
	public Set<StaticUserGroup> getStaticUserGroups() {
		return staticUserGroups;
	}

	public void setStaticUserGroups(Set<StaticUserGroup> staticUserGroups) {
		this.staticUserGroups = staticUserGroups;
	}

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "static_local_group_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	public StaticLocalGroup getStaticLocalGroup() {
		return staticLocalGroup;
	}

	public void setStaticLocalGroup(StaticLocalGroup staticLocalGroup) {
		this.staticLocalGroup = staticLocalGroup;
	}

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "local_group_region_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	public LocalGroupRegion getLocalGroupRegion() {
		return localGroupRegion;
	}

	public void setLocalGroupRegion(LocalGroupRegion localGroupRegion) {
		this.localGroupRegion = localGroupRegion;
	}

}