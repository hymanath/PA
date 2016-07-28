package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.DashboardUserAccessType;

public interface IDashboardUserAccessTypeDAO extends GenericDao<DashboardUserAccessType, Long>{
	
	public List<Object[]> getUserTypeByUserId(Long userId);
}
