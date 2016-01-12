/**
 * @author Sasi
 * Jan 11, 2016
 * ActivityMainVO.java
 * PA - SERV - com.itgrids.partyanalyst.dto
 */
package com.itgrids.partyanalyst.dto;

import java.io.Serializable;

/**
 * @author Sasi
 * @date Jan 11, 2016
 */
public class ActivityMainVO implements Serializable{

	private Long activityId;
	private String activityName;
	
	
	public Long getActivityId() {
		return activityId;
	}
	public void setActivityId(Long activityId) {
		this.activityId = activityId;
	}
	public String getActivityName() {
		return activityName;
	}
	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}
}
