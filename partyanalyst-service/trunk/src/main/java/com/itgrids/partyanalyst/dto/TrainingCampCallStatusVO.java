package com.itgrids.partyanalyst.dto;

public class TrainingCampCallStatusVO {
	
	private Long allocatedCallsCount = 0l;
	private Long answerdeCallsCount = 0l;
	private Long dialledCallsCount = 0l;
	private Long userBusyCallsCount = 0l;
	
	private Long interestedMemCount = 0l;
	private Long currentlyNotIntMemCount = 0l;
	private Long notIntereMemCount = 0l;
	
	
	public Long getAllocatedCallsCount() {
		return allocatedCallsCount;
	}
	public void setAllocatedCallsCount(Long allocatedCallsCount) {
		this.allocatedCallsCount = allocatedCallsCount;
	}
	public Long getAnswerdeCallsCount() {
		return answerdeCallsCount;
	}
	public void setAnswerdeCallsCount(Long answerdeCallsCount) {
		this.answerdeCallsCount = answerdeCallsCount;
	}
	public Long getDialledCallsCount() {
		return dialledCallsCount;
	}
	public void setDialledCallsCount(Long dialledCallsCount) {
		this.dialledCallsCount = dialledCallsCount;
	}
	public Long getUserBusyCallsCount() {
		return userBusyCallsCount;
	}
	public void setUserBusyCallsCount(Long userBusyCallsCount) {
		this.userBusyCallsCount = userBusyCallsCount;
	}
	public Long getInterestedMemCount() {
		return interestedMemCount;
	}
	public void setInterestedMemCount(Long interestedMemCount) {
		this.interestedMemCount = interestedMemCount;
	}
	public Long getCurrentlyNotIntMemCount() {
		return currentlyNotIntMemCount;
	}
	public void setCurrentlyNotIntMemCount(Long currentlyNotIntMemCount) {
		this.currentlyNotIntMemCount = currentlyNotIntMemCount;
	}
	public Long getNotIntereMemCount() {
		return notIntereMemCount;
	}
	public void setNotIntereMemCount(Long notIntereMemCount) {
		this.notIntereMemCount = notIntereMemCount;
	}
	
	
	
	

}
