package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.UserVoterCategory;

public interface IUserVoterCategoryDAO extends GenericDao<UserVoterCategory, Long>{

	public List<UserVoterCategory> getUserCategoryValues();
	public List<Long> checkCategoryExist(Long userId,String name);
	public List<Object[]> getCategoryValuesList(Long userId);
	public List<UserVoterCategory> getUserCategoriesByUserId(Long userId);
	
	public List<Object[]> getUserCategoriesByUserList(List<Long> userId);
	
	public String getCategoryNameByCategoryId(Long userVoterCategoryId);
}
