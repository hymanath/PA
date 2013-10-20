package com.itgrids.partyanalyst.dao;


import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.UserNewsCategory;

public interface IUserNewsCategoryDAO extends GenericDao<UserNewsCategory, Long>{
	
	public List<Object[]> allCategoriesOfUser(Long userId);
	
	public Integer updateCategory(Long userId,Long categoryId,String categoryName,String visibility);
	
	public Integer updateCategoryName(Long userId,Long categoryId,String categoryName);
	
	public Integer updateCategoryStatus(Long userId,Long categoryId,String status);
	
	public List<Long> getDeletedCategoryIds();
	
}
