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
@Table(name = "activity_tab_user_location")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ActivityTabUserLocation extends BaseModel implements Serializable{
	
	private Long activityTabUserLocationId;
	private Long userId;
	private Long activityLocationInfoId;
	private Long insertedBy;
	private String isActive;
	private Date insertedtime;
	
	private ActivityTabUser activityTabUser;
	private ActivityLocationInfo activityLocationInfo;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "activity_tab_user_location_id", unique = true, nullable = false)
	public Long getActivityTabUserLocationId() {
		return activityTabUserLocationId;
	}
	public void setActivityTabUserLocationId(Long activityTabUserLocationId) {
		this.activityTabUserLocationId = activityTabUserLocationId;
	}
	
	@Column(name="user_id")
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	@Column(name="activity_location_info_id")
	public Long getActivityLocationInfoId() {
		return activityLocationInfoId;
	}
	public void setActivityLocationInfoId(Long activityLocationInfoId) {
		this.activityLocationInfoId = activityLocationInfoId;
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
	public Date getInsertedtime() {
		return insertedtime;
	}
	public void setInsertedtime(Date insertedtime) {
		this.insertedtime = insertedtime;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="user_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public ActivityTabUser getActivityTabUser() {
		return activityTabUser;
	}
	public void setActivityTabUser(ActivityTabUser activityTabUser) {
		this.activityTabUser = activityTabUser;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="activity_location_info_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public ActivityLocationInfo getActivityLocationInfo() {
		return activityLocationInfo;
	}
	public void setActivityLocationInfo(ActivityLocationInfo activityLocationInfo) {
		this.activityLocationInfo = activityLocationInfo;
	}
}
