package com.itgrids.partyanalyst.model;

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
@Table(name = "static_user_group")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class StaticUserGroup implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long staticUserGroupId;
	private Date updatedDate;
	private StaticUsers staticUser;
	private PersonalUserGroup personalUserGroup;
	
	public StaticUserGroup() {
		
	}

	public StaticUserGroup(Long staticUserGroupId, Date updatedDate,
			StaticUsers staticUser, PersonalUserGroup personalUserGroup) {
		this.staticUserGroupId = staticUserGroupId;
		this.updatedDate = updatedDate;
		this.staticUser = staticUser;
		this.personalUserGroup = personalUserGroup;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "static_user_group_id", unique = true, nullable = false)
	public Long getStaticUserGroupId() {
		return staticUserGroupId;
	}

	public void setStaticUserGroupId(Long staticUserGroupId) {
		this.staticUserGroupId = staticUserGroupId;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_date")
	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "static_user_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public StaticUsers getStaticUser() {
		return staticUser;
	}

	public void setStaticUser(StaticUsers staticUser) {
		this.staticUser = staticUser;
	}
	
	@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "personal_user_group_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public PersonalUserGroup getPersonalUserGroup() {
		return personalUserGroup;
	}

	public void setPersonalUserGroup(PersonalUserGroup personalUserGroup) {
		this.personalUserGroup = personalUserGroup;
	}	
	
}
