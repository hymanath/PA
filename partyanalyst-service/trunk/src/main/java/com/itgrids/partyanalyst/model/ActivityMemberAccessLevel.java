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
@Table(name = "activity_member_access_level")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ActivityMemberAccessLevel {
	
	private Long activityMemberAccessLevelId;
	private Long activityMemberId;
	private Long activityMemberLevelId;
	private Long activityLocationValue;
	
	private ActivityMember activityMember;
	private UserLevel userLevel;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "activity_member_access_level_id", unique = true, nullable = false)
	public Long getActivityMemberAccessLevelId() {
		return activityMemberAccessLevelId;
	}

	public void setActivityMemberAccessLevelId(Long activityMemberAccessLevelId) {
		this.activityMemberAccessLevelId = activityMemberAccessLevelId;
	}
	
	@Column(name="activity_member_id")
	public Long getActivityMemberId() {
		return activityMemberId;
	}

	public void setActivityMemberId(Long activityMemberId) {
		this.activityMemberId = activityMemberId;
	}
	@Column(name="activity_member_level_id")
	public Long getActivityMemberLevelId() {
		return activityMemberLevelId;
	}

	public void setActivityMemberLevelId(Long activityMemberLevelId) {
		this.activityMemberLevelId = activityMemberLevelId;
	}
	@Column(name="activity_location_value")
	public Long getActivityLocationValue() {
		return activityLocationValue;
	}

	public void setActivityLocationValue(Long activityLocationValue) {
		this.activityLocationValue = activityLocationValue;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="activity_member_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public ActivityMember getActivityMember() {
		return activityMember;
	}

	public void setActivityMember(ActivityMember activityMember) {
		this.activityMember = activityMember;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="activity_member_level_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public UserLevel getUserLevel() {
		return userLevel;
	}

	public void setUserLevel(UserLevel userLevel) {
		this.userLevel = userLevel;
	}
	
	
}
