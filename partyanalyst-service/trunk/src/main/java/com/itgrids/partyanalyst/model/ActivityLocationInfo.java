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
@Table(name = "activity_location_info")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ActivityLocationInfo extends BaseModel implements Serializable{
	
	private Long activityLocationInfoId;
	private Long acivityScopeId;
	private Long locationLevel;
	private Long locationValue;
	private Long inviteeCount;
	private Long attendedCount;
	private Date activityDate;
	private Long activityRunningStatusId;
	private Date insertionTime;
	
	private ActivityScope activityScope;
	private ActivityRunningStatus activityRunningStatus;
	private RegionScopes regionScopes;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "activity_location_info_id", unique = true, nullable = false)
	public Long getActivityLocationInfoId() {
		return activityLocationInfoId;
	}
	public void setActivityLocationInfoId(Long activityLocationInfoId) {
		this.activityLocationInfoId = activityLocationInfoId;
	}
	
	@Column(name="acivity_scope_id")
	public Long getAcivityScopeId() {
		return acivityScopeId;
	}
	public void setAcivityScopeId(Long acivityScopeId) {
		this.acivityScopeId = acivityScopeId;
	}
	
	@Column(name="location_level")
	public Long getLocationLevel() {
		return locationLevel;
	}
	public void setLocationLevel(Long locationLevel) {
		this.locationLevel = locationLevel;
	}
	
	@Column(name="location_value")
	public Long getLocationValue() {
		return locationValue;
	}
	public void setLocationValue(Long locationValue) {
		this.locationValue = locationValue;
	}
	
	@Column(name="invitee_count")
	public Long getInviteeCount() {
		return inviteeCount;
	}
	public void setInviteeCount(Long inviteeCount) {
		this.inviteeCount = inviteeCount;
	}
	
	@Column(name="attended_count")
	public Long getAttendedCount() {
		return attendedCount;
	}
	public void setAttendedCount(Long attendedCount) {
		this.attendedCount = attendedCount;
	}
	
	@Column(name="activity_date")
	public Date getActivityDate() {
		return activityDate;
	}
	public void setActivityDate(Date activityDate) {
		this.activityDate = activityDate;
	}
	
	@Column(name="activity_running_status_id")
	public Long getActivityRunningStatusId() {
		return activityRunningStatusId;
	}
	public void setActivityRunningStatusId(Long activityRunningStatusId) {
		this.activityRunningStatusId = activityRunningStatusId;
	}
	
	@Column(name="insertion_time")
	public Date getInsertionTime() {
		return insertionTime;
	}
	public void setInsertionTime(Date insertionTime) {
		this.insertionTime = insertionTime;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="acivity_scope_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public ActivityScope getActivityScope() {
		return activityScope;
	}
	public void setActivityScope(ActivityScope activityScope) {
		this.activityScope = activityScope;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="activity_running_status_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public ActivityRunningStatus getActivityRunningStatus() {
		return activityRunningStatus;
	}
	public void setActivityRunningStatus(ActivityRunningStatus activityRunningStatus) {
		this.activityRunningStatus = activityRunningStatus;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="location_level")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public RegionScopes getRegionScopes() {
		return regionScopes;
	}
	public void setRegionScopes(RegionScopes regionScopes) {
		this.regionScopes = regionScopes;
	}
}
