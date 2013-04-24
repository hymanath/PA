package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.UserVoterCategoryValue;

public interface IUserVoterCategoryValueDAO  extends GenericDao<UserVoterCategoryValue, Long>{

	public List<Object[]> getVoterCategoryValues(Long voterCategoryId,String letters);
	
	public List<UserVoterCategoryValue> getCategoryValues();
	
	public List<Long> checkCategoryExist(Long userId,String name, Long id);
	
	public List<Object[]> getCategoryValuesByUserIdCategId(Long userId,Long userCategoryValueId);
	
	public List<Object[]> getCatergoryAndValues(List<Long> categories,Long userId);
}
