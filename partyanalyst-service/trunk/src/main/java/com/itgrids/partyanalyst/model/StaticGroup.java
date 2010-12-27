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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "static_group")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class StaticGroup implements java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8788236821079889706L;
	private Long staticGroupId;
	private String groupName;
	private String groupDescription;
	private Set<PersonalUserGroup> personalUserGroup=new HashSet<PersonalUserGroup>(0);
	private Date createdDate;
	private Date updatedDate;
	
	//constructors
	/*default constructors*/
	public StaticGroup()
	{
	}

	public StaticGroup(Long staticGroupId) {
		
		this.staticGroupId = staticGroupId;
	}

	public StaticGroup(Long staticGroupId, String groupName,
			String groupDescription, Set<PersonalUserGroup> personalUserGroup,Date createdDate, Date updatedDate) {
		
		this.staticGroupId = staticGroupId;
		this.groupName = groupName;
		this.groupDescription = groupDescription;
		this.personalUserGroup = personalUserGroup;
		this.createdDate=createdDate;
		this.updatedDate=updatedDate;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "static_group_id", unique = true, nullable = false)
	public Long getStaticGroupId() {
		return staticGroupId;
	}

	public void setStaticGroupId(Long staticGroupId) {
		this.staticGroupId = staticGroupId;
	}
	@Column(name = "group_name", length = 100)
	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	@Column(name = "group_desc", length = 250)
	public String getGroupDescription() {
		return groupDescription;
	}

	public void setGroupDescription(String groupDescription) {
		this.groupDescription = groupDescription;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "staticGroup")
	public Set<PersonalUserGroup> getPersonalUserGroup() {
		return personalUserGroup;
	}

	public void setPersonalUserGroup(Set<PersonalUserGroup> personalUserGroup) {
		this.personalUserGroup = personalUserGroup;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_date")
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_date")
	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
}
