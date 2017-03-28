package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.UserActivityScope;

public interface IUserActivityScopeDAO extends GenericDao<UserActivityScope, Long>{
	
	public List<Object[]> getUserActivityDetailsByUserId(Long userId);

}
