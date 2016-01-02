package com.itgrids.partyanalyst.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "activity_level")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ActivityLevel extends BaseModel implements Serializable{
	
	private Long activityLevelId;
	private String level;
	private Long activityOrderId;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "activity_level_id", unique = true, nullable = false)
	public Long getActivityLevelId() {
		return activityLevelId;
	}
	public void setActivityLevelId(Long activityLevelId) {
		this.activityLevelId = activityLevelId;
	}
	
	@Column(name="level")
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	@Column(name="activity_order_id")
	public Long getActivityOrderId() {
		return activityOrderId;
	}
	public void setActivityOrderId(Long activityOrderId) {
		this.activityOrderId = activityOrderId;
	}
	
	
	
}
