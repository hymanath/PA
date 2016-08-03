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
@Table(name = "activity_member_relation")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ActivityMemberRelation {
	
	private Long activityMemberRelationId;
	private Long activityMemberId;
	private Long parentMemberId;
	
	
	private ActivityMember childActivityMember;
	private ActivityMember parentActivityMember;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "activity_member_relation_id", unique = true, nullable = false)
	public Long getActivityMemberRelationId() {
		return activityMemberRelationId;
	}
	public void setActivityMemberRelationId(Long activityMemberRelationId) {
		this.activityMemberRelationId = activityMemberRelationId;
	}

	@Column(name="activity_member_id")
	public Long getActivityMemberId() {
		return activityMemberId;
	}
	public void setActivityMemberId(Long activityMemberId) {
		this.activityMemberId = activityMemberId;
	}

	@Column(name="parent_member_id")
	public Long getParentMemberId() {
		return parentMemberId;
	}
	public void setParentMemberId(Long parentMemberId) {
		this.parentMemberId = parentMemberId;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="activity_member_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public ActivityMember getChildActivityMember() {
		return childActivityMember;
	}
	public void setChildActivityMember(ActivityMember childActivityMember) {
		this.childActivityMember = childActivityMember;
	}

	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="activity_member_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public ActivityMember getParentActivityMember() {
		return parentActivityMember;
	}
	public void setParentActivityMember(ActivityMember parentActivityMember) {
		this.parentActivityMember = parentActivityMember;
	}


}
