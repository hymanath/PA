package com.itgrids.partyanalyst.dto;

import java.util.ArrayList;
import java.util.List;

public class FeedbackQuestionVO {
	
	private Long programId;
	private Long campId;
	private Long batchId;
	private Long id;
	private String category;
	private List<FeedBackOptionVO> optionsList;
	private String optionExists;
	private Long trainingCampFeedbackCategoryId;
	private List<CategoryFeedbackVO> childCategoryList = new ArrayList<CategoryFeedbackVO>();
	private List<String> mainCategoryAnswers = new ArrayList<String>();
	
	
	
	
	
	public List<CategoryFeedbackVO> getChildCategoryList() {
		return childCategoryList;
	}
	public void setChildCategoryList(List<CategoryFeedbackVO> childCategoryList) {
		this.childCategoryList = childCategoryList;
	}
	public List<String> getMainCategoryAnswers() {
		return mainCategoryAnswers;
	}
	public void setMainCategoryAnswers(List<String> mainCategoryAnswers) {
		this.mainCategoryAnswers = mainCategoryAnswers;
	}
	public Long getTrainingCampFeedbackCategoryId() {
		return trainingCampFeedbackCategoryId;
	}
	public void setTrainingCampFeedbackCategoryId(
			Long trainingCampFeedbackCategoryId) {
		this.trainingCampFeedbackCategoryId = trainingCampFeedbackCategoryId;
	}
	public String getOptionExists() {
		return optionExists;
	}
	public void setOptionExists(String optionExists) {
		this.optionExists = optionExists;
	}
	public List<FeedBackOptionVO> getOptionsList() {
		return optionsList;
	}
	public void setOptionsList(List<FeedBackOptionVO> optionsList) {
		this.optionsList = optionsList;
	}
	public Long getProgramId() {
		return programId;
	}
	public void setProgramId(Long programId) {
		this.programId = programId;
	}
	public Long getCampId() {
		return campId;
	}
	public void setCampId(Long campId) {
		this.campId = campId;
	}
	public Long getBatchId() {
		return batchId;
	}
	public void setBatchId(Long batchId) {
		this.batchId = batchId;
	}

	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	
	

}
