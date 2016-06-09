package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ActivityQuestionnairOptionVO implements Serializable{
	private Long		activityQuestionnairId;
	private Long		optionId;
	private String      name;
	private String      optionType;
	private String      remark;
	private Long        orderNo;
	private Long id;
	private List<ActivityQuestionnairOptionVO> subList = new ArrayList<ActivityQuestionnairOptionVO>();
	
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
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public List<ActivityQuestionnairOptionVO> getSubList() {
		return subList;
	}
	public void setSubList(List<ActivityQuestionnairOptionVO> subList) {
		this.subList = subList;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getOptionType() {
		return optionType;
	}
	public void setOptionType(String optionType) {
		this.optionType = optionType;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	
}
