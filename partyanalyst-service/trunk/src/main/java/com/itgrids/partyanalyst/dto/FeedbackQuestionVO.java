package com.itgrids.partyanalyst.dto;

import java.util.List;

public class FeedbackQuestionVO {
	
	private Long programId;
	private Long campId;
	private Long batchId;
	private Long categoryId;
	private String category;
	private List<FeedBackOptionVO> optionsList;
	private boolean optionExists;
	
	
	
	
	public boolean isOptionExists() {
		return optionExists;
	}
	public void setOptionExists(boolean optionExists) {
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
	public Long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	
	

}
