package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.UserCountryAccessInfo;

public interface IUserCountryAccessInfoDAO extends GenericDao<UserCountryAccessInfo, Long>{

	public List findByUser(Long userId);
	
	public Integer deleteAllCountryAccess(Long userId);
	
}
