package com.itgrids.partyanalyst.dto;

import java.io.Serializable;

public class ActivityQuestionnairOptionVO implements Serializable{
	private Long		activityQuestionnairId;
	private Long		optionId;
	private Long		orderNo;
	
	public Long getActivityQuestionnairId() {
		return activityQuestionnairId;
	}
	public void setActivityQuestionnairId(Long activityQuestionnairId) {
		this.activityQuestionnairId = activityQuestionnairId;
	}
	public Long getOptionId() {
		return optionId;
	}
	public void setOptionId(Long optionId) {
		this.optionId = optionId;
	}
	public Long getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Long orderNo) {
		this.orderNo = orderNo;
	}
	
	
}
