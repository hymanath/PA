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
@Table(name="user_access_level_value")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UserAccessLevelValue extends BaseModel implements Serializable{

	private static final long serialVersionUID = -3806379738904956901L;
	
	private Long userAccessLevelValueId;
	private Long userId;
	private User user;
	private Long accessLevelId;
	private AccessLevel accessLevel;
	private Long accessLevelValue;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="user_access_level_id", unique=true, nullable=false)
	public Long getUserAccessLevelValueId() {
		return userAccessLevelValueId;
	}
	public void setUserAccessLevelValueId(Long userAccessLevelValueId) {
		this.userAccessLevelValueId = userAccessLevelValueId;
	}
	
	@Column(name="user_id")
	public Long getUserId() {
		return userId;
	}
	
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="user_id",updatable = false, insertable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	@Column(name="access_level_id")
	public Long getAccessLevelId() {
		return accessLevelId;
	}
	public void setAccessLevelId(Long accessLevelId) {
		this.accessLevelId = accessLevelId;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="access_level_id",updatable = false, insertable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public AccessLevel getAccessLevel() {
		return accessLevel;
	}
	public void setAccessLevel(AccessLevel accessLevel) {
		this.accessLevel = accessLevel;
	}
	
	@Column(name="access_level_value")
	public Long getAccessLevelValue() {
		return accessLevelValue;
	}
	public void setAccessLevelValue(Long accessLevelValue) {
		this.accessLevelValue = accessLevelValue;
	}
	
}
