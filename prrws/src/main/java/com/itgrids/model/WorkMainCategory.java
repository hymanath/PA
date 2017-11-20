package com.itgrids.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "work_main_category")
public class WorkMainCategory {

	private Long workMainCategoryId;
	private String categoryName;
	
	@Id
	@Column(name="work_main_category_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	public Long getWorkMainCategoryId() {
		return workMainCategoryId;
	}
	public void setWorkMainCategoryId(Long workMainCategoryId) {
		this.workMainCategoryId = workMainCategoryId;
	}
	
	@Column(name="category_name")
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
}
