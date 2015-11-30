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
@Table(name = "activity_sub_type")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ActivitySubType extends BaseModel implements Serializable{
	
	private Long activitySubTypeId;
	private Long activityTypeId;
	private String subType;
	private String isDeleted;
	
	private ActivityType activityType;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "activity_sub_type_id", unique = true, nullable = false)
	public Long getActivitySubTypeId() {
		return activitySubTypeId;
	}
	public void setActivitySubTypeId(Long activitySubTypeId) {
		this.activitySubTypeId = activitySubTypeId;
	}
	
	@Column(name="activity_type_id")
	public Long getActivityTypeId() {
		return activityTypeId;
	}
	public void setActivityTypeId(Long activityTypeId) {
		this.activityTypeId = activityTypeId;
	}
	
	@Column(name="sub_type")
	public String getSubType() {
		return subType;
	}
	public void setSubType(String subType) {
		this.subType = subType;
	}
	
	@Column(name="is_deleted")
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="activity_type_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public ActivityType getActivityType() {
		return activityType;
	}
	public void setActivityType(ActivityType activityType) {
		this.activityType = activityType;
	}
}
