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
@Table(name = "activity_running_status")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ActivityRunningStatus extends BaseModel implements Serializable{
	
	private Long activityRunningStatusId;
	private String status;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "activity_running_status_id", unique = true, nullable = false)
	public Long getActivityRunningStatusId() {
		return activityRunningStatusId;
	}
	public void setActivityRunningStatusId(Long activityRunningStatusId) {
		this.activityRunningStatusId = activityRunningStatusId;
	}
	
	@Column(name="status")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
