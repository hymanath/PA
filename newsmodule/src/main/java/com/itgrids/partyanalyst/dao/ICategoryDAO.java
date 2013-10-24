package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.Category;

public interface ICategoryDAO extends GenericDao<Category, Long>{
	public List<Object[]> getCategoryDetails();
	
	public List<String> checkCategoryNameExist(String name);
	
	public Long getMaxOrderNo();
	
	public List<Object[]> checkCategoryNameIdExist(String name);
	
	public List<Object[]> getAllCategories();
	

}
