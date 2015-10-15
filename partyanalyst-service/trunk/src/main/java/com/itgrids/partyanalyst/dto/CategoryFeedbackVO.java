package com.itgrids.partyanalyst.dto;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.mapping.Array;

public class CategoryFeedbackVO {
	private Long mainCategoryId;
	private String mainCategoryName;
	private Long subCategoryId;
	private String subCategoryName;
	private List<CategoryFeedbackVO> categoryFeedBackList = new ArrayList<CategoryFeedbackVO>(0);
	private List<String> description = new ArrayList<String>(0);
	
	
	
	
	
	
	public List<String> getDescription() {
		return description;
	}
	public void setDescription(List<String> description) {
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
