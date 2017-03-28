package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.DashboardUserAccessLevel;

public interface IDashboardUserAccessLevelDAO extends GenericDao<DashboardUserAccessLevel, Long>{
	
	public List<Object[]> getUserAccessLevelAndValues(Long userId);
}
