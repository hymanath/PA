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
@Table(name = "activity_location_attribute_info")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ActivityLocationAttributeInfo extends BaseModel implements Serializable{
	
	private Long activityLocationAttributeInfoId;
	private Long activityLocationInfoId;
	private Long activityAttributeId;
	private String status;
	private Long count;
	private String description;
	
	private ActivityLocationInfo activityLocationInfo;
	private ActivityAttribute activityAttribute;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "activity_location_attribute_info_id", unique = true, nullable = false)
	public Long getActivityLocationAttributeInfoId() {
		return activityLocationAttributeInfoId;
	}
	public void setActivityLocationAttributeInfoId(
			Long activityLocationAttributeInfoId) {
		this.activityLocationAttributeInfoId = activityLocationAttributeInfoId;
	}
	
	@Column(name="activity_location_info_id")
	public Long getActivityLocationInfoId() {
		return activityLocationInfoId;
	}
	public void setActivityLocationInfoId(Long activityLocationInfoId) {
		this.activityLocationInfoId = activityLocationInfoId;
	}
	
	@Column(name="activity_attribute_id")
	public Long getActivityAttributeId() {
		return activityAttributeId;
	}
	public void setActivityAttributeId(Long activityAttributeId) {
		this.activityAttributeId = activityAttributeId;
	}
	
	@Column(name="status")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	@Column(name="count")
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	
	@Column(name="description")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="activity_attribute_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public ActivityAttribute getActivityAttribute() {
		return activityAttribute;
	}
	public void setActivityAttribute(ActivityAttribute activityAttribute) {
		this.activityAttribute = activityAttribute;
	}
}
