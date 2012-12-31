package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.UserCategoryValues;

public interface IUserCategoryValuesDAO extends GenericDao<UserCategoryValues, Long>{

	public List<UserCategoryValues> getUserCategoryValues();
	public List<Long> checkCategoryExist(Long userId,String name);
	public List<Object[]> getCategoryValuesList(Long userId);
}
