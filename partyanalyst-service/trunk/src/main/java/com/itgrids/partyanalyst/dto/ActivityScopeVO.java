/**
 * @author Sasi
 * Jan 11, 2016
 * ActivityScopeVO.java
 * PA - SERV - com.itgrids.partyanalyst.dto
 */
package com.itgrids.partyanalyst.dto;

import java.io.Serializable;

/**
 * @author Sasi
 * @date Jan 11, 2016
 */
public class ActivityScopeVO implements Serializable{

	private Long activityScopeId;
	private Long activityId;
	private Long activityLevelId;
	private Long activityLevelValue;
	private String startDate;
	private String endDate;
	
	
	public Long getActivityLevelValue() {
		return activityLevelValue;
	}
	public void setActivityLevelValue(Long activityLevelValue) {
		this.activityLevelValue = activityLevelValue;
	}
	public Long getActivityScopeId() {
		return activityScopeId;
	}
	public void setActivityScopeId(Long activityScopeId) {
		this.activityScopeId = activityScopeId;
	}
	public Long getActivityId() {
		return activityId;
	}
	public void setActivityId(Long activityId) {
		this.activityId = activityId;
	}
	public Long getActivityLevelId() {
		return activityLevelId;
	}
	public void setActivityLevelId(Long activityLevelId) {
		this.activityLevelId = activityLevelId;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
}
