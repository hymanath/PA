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
@Table(name = "dashboard_user_access_level")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class DashboardUserAccessLevel extends BaseModel{
	
	private static final long serialVersionUID = 6114633320173094734L;
	
	private Long dashboardUserAccessLevelId;
	private Long userId;
	private Long userLevelId;
	private Long levelValue;
	
	private User user;
	private UserLevel userLevel;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "dashboard_user_access_level_id", unique = true, nullable = false)
	public Long getDashboardUserAccessLevelId() {
		return dashboardUserAccessLevelId;
	}
	public void setDashboardUserAccessLevelId(Long dashboardUserAccessLevelId) {
		this.dashboardUserAccessLevelId = dashboardUserAccessLevelId;
	}
	
	@Column(name = "user_id")
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	@Column(name = "user_level_id")
	public Long getUserLevelId() {
		return userLevelId;
	}
	public void setUserLevelId(Long userLevelId) {
		this.userLevelId = userLevelId;
	}
	
	@Column(name = "level_value")
	public Long getLevelValue() {
		return levelValue;
	}
	public void setLevelValue(Long levelValue) {
		this.levelValue = levelValue;
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
	@JoinColumn(name="user_level_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public UserLevel getUserLevel() {
		return userLevel;
	}
	public void setUserLevel(UserLevel userLevel) {
		this.userLevel = userLevel;
	}
	
}
