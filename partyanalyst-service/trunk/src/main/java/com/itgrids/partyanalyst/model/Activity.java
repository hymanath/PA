package com.itgrids.partyanalyst.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Date;
import java.util.Date;
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
@Table(name = "activity")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Activity extends BaseModel implements Serializable{
	
	private Long activityId;
	private String activityName;
	private Long activitySubTypeId;
	private Date startDate;
	private Date endDate;
	private Long insertedBy;
	private Long updatedBy;
	private Date insertionTime;
	private Date updationTime;
	private String isActive;
	
	private ActivitySubType activitySubType;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "activity_id", unique = true, nullable = false)
	public Long getActivityId() {
		return activityId;
	}
	public void setActivityId(Long activityId) {
		this.activityId = activityId;
	}
	
	@Column(name="activity_name")
	public String getActivityName() {
		return activityName;
	}
	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}
	
	@Column(name="activity_sub_type_id")
	public Long getActivitySubTypeId() {
		return activitySubTypeId;
	}
	public void setActivitySubTypeId(Long activitySubTypeId) {
		this.activitySubTypeId = activitySubTypeId;
	}
	
	@Column(name="start_date")
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	@Column(name="end_date")
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	@Column(name="inserted_by")
	public Long getInsertedBy() {
		return insertedBy;
	}
	public void setInsertedBy(Long insertedBy) {
		this.insertedBy = insertedBy;
	}
	
	@Column(name="updated_by")
	public Long getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
	}
	
	@Column(name="insertion_time")
	public Date getInsertionTime() {
		return insertionTime;
	}
	public void setInsertionTime(Date insertionTime) {
		this.insertionTime = insertionTime;
	}
	
	@Column(name="updation_time")
	public Date getUpdationTime() {
		return updationTime;
	}
	public void setUpdationTime(Date updationTime) {
		this.updationTime = updationTime;
	}
	
	@Column(name="is_active")
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="activity_sub_type_id")
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public ActivitySubType getActivitySubType() {
		return activitySubType;
	}
	public void setActivitySubType(ActivitySubType activitySubType) {
		this.activitySubType = activitySubType;
	}
}
