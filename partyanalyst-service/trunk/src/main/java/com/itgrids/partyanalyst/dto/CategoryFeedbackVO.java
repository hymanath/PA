package com.itgrids.partyanalyst.dto;

import java.util.List;

public class CategoryFeedbackVO {
	private Long mainCategoryId;
	private String mainCategoryName;
	private Long subCategoryId;
	private String subCategoryName;
	private List<CategoryFeedbackVO> categoryFeedBackList;
	private String description;
	
	
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Long getMainCategoryId() {
		return mainCategoryId;
	}
	public void setMainCategoryId(Long mainCategoryId) {
		this.mainCategoryId = mainCategoryId;
	}
	public String getMainCategoryName() {
		return mainCategoryName;
	}
	public void setMainCategoryName(String mainCategoryName) {
		this.mainCategoryName = mainCategoryName;
	}
	public Long getSubCategoryId() {
		return subCategoryId;
	}
	public void setSubCategoryId(Long subCategoryId) {
		this.subCategoryId = subCategoryId;
	}
	public String getSubCategoryName() {
		return subCategoryName;
	}
	public void setSubCategoryName(String subCategoryName) {
		this.subCategoryName = subCategoryName;
	}
	public List<CategoryFeedbackVO> getCategoryFeedBackList() {
		return categoryFeedBackList;
	}
	public void setCategoryFeedBackList(
			List<CategoryFeedbackVO> categoryFeedBackList) {
		this.categoryFeedBackList = categoryFeedBackList;
	}
	
	
	
}
