package com.itgrids.partyanalyst.model;

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
@Table(name = "user_type_relation")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UserTypeRelation {
	
	private Long userTypeRelationId;
	private Long userTypeId;
	private Long parentUserTypeId;
	private String isActive;
	
	
	private UserType childUserType;
	private UserType parentUserType;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_type_relation_id", unique = true, nullable = false)
	public Long getUserTypeRelationId() {
		return userTypeRelationId;
	}
	public void setUserTypeRelationId(Long userTypeRelationId) {
		this.userTypeRelationId = userTypeRelationId;
	}
	@Column(name="user_type_id")
	public Long getUserTypeId() {
		return userTypeId;
	}
	public void setUserTypeId(Long userTypeId) {
		this.userTypeId = userTypeId;
	}
	@Column(name="parent_user_type_id")
	public Long getParentUserTypeId() {
		return parentUserTypeId;
	}
	public void setParentUserTypeId(Long parentUserTypeId) {
		this.parentUserTypeId = parentUserTypeId;
	}
	@Column(name="is_active")
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="user_type_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public UserType getChildUserType() {
		return childUserType;
	}
	public void setChildUserType(UserType childUserType) {
		this.childUserType = childUserType;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="user_type_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public UserType getParentUserType() {
		return parentUserType;
	}
	public void setParentUserType(UserType parentUserType) {
		this.parentUserType = parentUserType;
	}
	
	
	
	
	
}
