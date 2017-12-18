package com.itgrids.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.model.UserAccessLevelValue;

public interface IUserAccessLevelValueDAO extends GenericDao<UserAccessLevelValue, Long>{
	
	public List<Object[]> getUserAccessDetails(Long userId);

}
