package com.itgrids.eliteclub.dto;

import java.util.ArrayList;
import java.util.List;

public class CategoryResponseVo {
	private List<CategoriesVo> categories=new ArrayList<CategoriesVo>();

	public List<CategoriesVo> getCategories() {
		return categories;
	}

	public void setCategories(List<CategoriesVo> categories) {
		this.categories = categories;
	}
	 
	private String userId;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	

}
