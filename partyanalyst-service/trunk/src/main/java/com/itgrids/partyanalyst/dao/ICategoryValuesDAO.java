package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.CategoryValues;

public interface ICategoryValuesDAO  extends GenericDao<CategoryValues, Long>{

	public List<Object[]> getVoterCategoryValues(Long voterCategoryId,String letters);
	
	public List<CategoryValues> getCategoryValues();
	
	public List<Long> checkCategoryExist(Long userId,String name, Long id);
	
	public List<Object[]> getCategoryValuesByUserIdCategId(Long userId,Long userCategoryValueId);
	
	
}
