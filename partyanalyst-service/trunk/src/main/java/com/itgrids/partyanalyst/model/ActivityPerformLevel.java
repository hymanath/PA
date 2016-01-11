package com.itgrids.partyanalyst.model;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "activity_perform_level")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ActivityPerformLevel {
	
	private Long activityPerformLevelId;
	private String activityPerformLevel;
	private Long orderNo;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "activity_perform_level_id", unique = true, nullable = false)
	public Long getActivityPerformLevelId() {
		return activityPerformLevelId;
	}
	public void setActivityPerformLevelId(Long activityPerformLevelId) {
		this.activityPerformLevelId = activityPerformLevelId;
	}
	@Column(name = "activity_perform_level")
	public String getActivityPerformLevel() {
		return activityPerformLevel;
	}
	public void setActivityPerformLevel(String activityPerformLevel) {
		this.activityPerformLevel = activityPerformLevel;
	}
	@Column(name = "order_no")
	public Long getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Long orderNo) {
		this.orderNo = orderNo;
	}
	
	
}