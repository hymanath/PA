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

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name = "user_activity_scope")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UserActivityScope extends BaseModel implements Serializable{
	
	private Long userActivityScopeId;
	private Long userId;
	private Long activityScopeId;
	private Long scopeValue;
	private Long insertedBy;
	private String isActive;
	private Date insertedTime;
	
	private User user;
	private ActivityScope activityScope;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_activity_scope_id", unique = true, nullable = false)
	public Long getUserActivityScopeId() {
		return userActivityScopeId;
	}
	public void setUserActivityScopeId(Long userActivityScopeId) {
		this.userActivityScopeId = userActivityScopeId;
	}
	
	@Column(name="user_id")
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	@Column(name="activity_scope_id")
	public Long getActivityScopeId() {
		return activityScopeId;
	}
	public void setActivityScopeId(Long activityScopeId) {
		this.activityScopeId = activityScopeId;
	}
	
	@Column(name="scope_value")
	public Long getScopeValue() {
		return scopeValue;
	}
	public void setScopeValue(Long scopeValue) {
		this.scopeValue = scopeValue;
	}
	
	@Column(name="inserted_by")
	public Long getInsertedBy() {
		return insertedBy;
	}
	public void setInsertedBy(Long insertedBy) {
		this.insertedBy = insertedBy;
	}
	
	@Column(name="is_active")
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	
	@Column(name="inserted_time")
	public Date getInsertedTime() {
		return insertedTime;
	}
	public void setInsertedTime(Date insertedTime) {
		this.insertedTime = insertedTime;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="user_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="activity_scope_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public ActivityScope getActivityScope() {
		return activityScope;
	}
	public void setActivityScope(ActivityScope activityScope) {
		this.activityScope = activityScope;
	}
}
