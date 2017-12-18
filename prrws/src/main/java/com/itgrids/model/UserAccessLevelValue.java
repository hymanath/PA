package com.itgrids.model;

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

@Entity
@Table(name = "user_access_level_value")
public class UserAccessLevelValue extends BaseModel implements Serializable{

	private Long userAccessLevelValueId;
	private Long userId;
	private Long accessLevelId;
	private Long accessLevelValue;
	
	private User user;
	private AccessLevel accessLevel;
	
	@Id
	@Column(name="user_access_level_value_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
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
	
	@Column(name="access_level_id")
	public Long getAccessLevelId() {
		return accessLevelId;
	}
	public void setAccessLevelId(Long accessLevelId) {
		this.accessLevelId = accessLevelId;
	}
	
	@Column(name="access_level_value")
	public Long getAccessLevelValue() {
		return accessLevelValue;
	}
	public void setAccessLevelValue(Long accessLevelValue) {
		this.accessLevelValue = accessLevelValue;
	}
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", insertable = false, updatable = false)
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "access_level_id", insertable = false, updatable = false)
	public AccessLevel getAccessLevel() {
		return accessLevel;
	}
	public void setAccessLevel(AccessLevel accessLevel) {
		this.accessLevel = accessLevel;
	}
}
