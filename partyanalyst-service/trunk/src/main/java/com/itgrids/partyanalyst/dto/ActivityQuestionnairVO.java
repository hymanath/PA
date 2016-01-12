package com.itgrids.partyanalyst.dto;

import java.io.Serializable;

public class ActivityQuestionnairVO implements Serializable{
	private Long					questionId;
	private Long					activityScopeId;
	private Long					optionTypeId;
	private Long					respondentTypeId;
	private Long					orderNo;
	private Long					parentQuestionnairId;
	private Long					questionnairId;
	
	
	public Long getParentQuestionnairId() {
		return parentQuestionnairId;
	}
	public void setParentQuestionnairId(Long parentQuestionnairId) {
		this.parentQuestionnairId = parentQuestionnairId;
	}
	public Long getQuestionnairId() {
		return questionnairId;
	}
	public void setQuestionnairId(Long questionnairId) {
		this.questionnairId = questionnairId;
	}
	public Long getQuestionId() {
		return questionId;
	}
	public void setQuestionId(Long questionId) {
		this.questionId = questionId;
	}
	public Long getActivityScopeId() {
		return activityScopeId;
	}
	public void setActivityScopeId(Long activityScopeId) {
		this.activityScopeId = activityScopeId;
	}
	public Long getOptionTypeId() {
		return optionTypeId;
	}
	public void setOptionTypeId(Long optionTypeId) {
		this.optionTypeId = optionTypeId;
	}
	public Long getRespondentTypeId() {
		return respondentTypeId;
	}
	public void setRespondentTypeId(Long respondentTypeId) {
		this.respondentTypeId = respondentTypeId;
	}
	public Long getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Long orderNo) {
		this.orderNo = orderNo;
	}
	
}
