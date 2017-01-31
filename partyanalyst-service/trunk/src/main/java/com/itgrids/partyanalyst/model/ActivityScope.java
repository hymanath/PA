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
@Table(name = "activity_scope")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ActivityScope extends BaseModel implements Serializable{
	
	private Long activityScopeId;
	private Long activityId;
	private Long activityLevelId;
	private Long scopeId;
	private Long scopeValue;
	private Long insertedBy;
	private Long updatedBy;
	private Date insertionTime;
	private Date startDate;
	private Date endDate;
	private String isDeleted;
	
	private Activity activity;
	private ActivityLevel activityLevel;
	private Long			maxFilesCount;
	private Long noOfTimes;
	private Long orderNo;
	private Long publicationDateId;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "activity_scope_id", unique = true, nullable = false)
	public Long getActivityScopeId() {
		return activityScopeId;
	}
	public void setActivityScopeId(Long activityScopeId) {
		this.activityScopeId = activityScopeId;
	}
	
	@Column(name="activity_id")
	public Long getActivityId() {
		return activityId;
	}
	public void setActivityId(Long activityId) {
		this.activityId = activityId;
	}
	
	@Column(name="activity_level_id")
	public Long getActivityLevelId() {
		return activityLevelId;
	}
	public void setActivityLevelId(Long activityLevelId) {
		this.activityLevelId = activityLevelId;
	}
	
	@Column(name="scope_id")
	public Long getScopeId() {
		return scopeId;
	}
	public void setScopeId(Long scopeId) {
		this.scopeId = scopeId;
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
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="activity_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Activity getActivity() {
		return activity;
	}
	public void setActivity(Activity activity) {
		this.activity = activity;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="activity_level_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public ActivityLevel getActivityLevel() {
		return activityLevel;
	}
	public void setActivityLevel(ActivityLevel activityLevel) {
		this.activityLevel = activityLevel;
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
	
	@Column(name="max_files_count")
	public Long getMaxFilesCount() {
		return maxFilesCount;
	}
	public void setMaxFilesCount(Long maxFilesCount) {
		this.maxFilesCount = maxFilesCount;
	}
	
	@Column(name="is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	@Column(name="no_of_times")
	public Long getNoOfTimes() {
		return noOfTimes;
	}
	public void setNoOfTimes(Long noOfTimes) {
		this.noOfTimes = noOfTimes;
	}
	
	@Column(name="order_no")
	public Long getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Long orderNo) {
		this.orderNo = orderNo;
	}
	@Column(name = "publication_date_id")
	public Long getPublicationDateId() {
		return publicationDateId;
	}
	public void setPublicationDateId(Long publicationDateId) {
		this.publicationDateId = publicationDateId;
	}
		
}
