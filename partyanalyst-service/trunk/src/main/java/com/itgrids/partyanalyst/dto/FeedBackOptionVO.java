package com.itgrids.partyanalyst.dto;

public class FeedBackOptionVO {
	private Long id;
	private String option;
	private Long trainingCampFeedbackCategoryId;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getOption() {
		return option;
	}
	public void setOption(String option) {
		this.option = option;
	}
	public Long getTrainingCampFeedbackCategoryId() {
		return trainingCampFeedbackCategoryId;
	}
	public void setTrainingCampFeedbackCategoryId(
			Long trainingCampFeedbackCategoryId) {
		this.trainingCampFeedbackCategoryId = trainingCampFeedbackCategoryId;
	}
	

}
