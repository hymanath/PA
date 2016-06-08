package com.itgrids.partyanalyst.model;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.util.Date;


/**
 * The persistent class for the activity_invitee database table.
 * 
 */
@Entity
@Table(name="activity_invitee")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ActivityInvitee implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long activityInviteeId;
	private Date activityDate;
	private Long activityScopeId;
	private Long day;
	private Long insertedBy;
	private Date insertionTime;

	public ActivityInvitee() {
	}
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="activity_invitee_id", unique=true, nullable=false)
	public Long getActivityInviteeId() {
		return this.activityInviteeId;
	}

	public void setActivityInviteeId(Long activityInviteeId) {
		this.activityInviteeId = activityInviteeId;
	}

	@Column(name="activity_date")
	public Date getActivityDate() {
		return this.activityDate;
	}

	public void setActivityDate(Date activityDate) {
		this.activityDate = activityDate;
	}


	@Column(name="activity_scope_id")
	public Long getActivityScopeId() {
		return this.activityScopeId;
	}

	public void setActivityScopeId(Long activityScopeId) {
		this.activityScopeId = activityScopeId;
	}

	@Column(name="day")
	public Long getDay() {
		return this.day;
	}

	public void setDay(Long day) {
		this.day = day;
	}


	@Column(name="inserted_by")
	public Long getInsertedBy() {
		return this.insertedBy;
	}

	public void setInsertedBy(Long insertedBy) {
		this.insertedBy = insertedBy;
	}

	@Column(name="insertion_time")
	public Date getInsertionTime() {
		return this.insertionTime;
	}

	public void setInsertionTime(Date insertionTime) {
		this.insertionTime = insertionTime;
	}

}