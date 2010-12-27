/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on October 25, 2010
 */
package com.itgrids.partyanalyst.model;

import java.io.Serializable;
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

/**
 * @author Sai Krishna 
 *
 */
@Entity
@Table(name = "static_local_group")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class StaticLocalGroup extends BaseModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long staticLocalGroupId;
	private String groupType;
	private String description;
	private Date updatedDate;
	private String groupScope;
	private Registration user;
	private Set<StaticUserDesignation> userDesignation = new HashSet<StaticUserDesignation>(0);
	
	//Default constructor
	public StaticLocalGroup(){
		
	}
	
	//parameterized constructor
	public StaticLocalGroup(String groupType, String description,
			Date updatedDate, String groupScope, Registration user,Set<StaticUserDesignation> userDesignation) {
		super();
		this.groupType = groupType;
		this.description = description;
		this.updatedDate = updatedDate;
		this.groupScope = groupScope;
		this.user = user;
		this.userDesignation = userDesignation;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "static_local_group_id", unique = true, nullable = false)
	public Long getStaticLocalGroupId() {
		return staticLocalGroupId;
	}

	public void setStaticLocalGroupId(Long staticLocalGroupId) {
		this.staticLocalGroupId = staticLocalGroupId;
	}

	@Column(name = "group_type", length = 50)
	public String getGroupType() {
		return groupType;
	}

	public void setGroupType(String groupType) {
		this.groupType = groupType;
	}

	@Column(name = "description", length = 300)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "updated_date")
	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	@Column(name = "group_scope", length = 50)
	public String getGroupScope() {
		return groupScope;
	}

	public void setGroupScope(String groupScope) {
		this.groupScope = groupScope;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="user_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Registration getUser() {
		return user;
	}

	public void setUser(Registration user) {
		this.user = user;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "staticLocalGroup")
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Set<StaticUserDesignation> getUserDesignation() {
		return userDesignation;
	}

	public void setUserDesignation(Set<StaticUserDesignation> userDesignation) {
		this.userDesignation = userDesignation;
	}

}
