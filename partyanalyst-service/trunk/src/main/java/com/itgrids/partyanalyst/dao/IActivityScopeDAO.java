package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.ActivityScope;

public interface IActivityScopeDAO extends GenericDao<ActivityScope, Long>{
	public List<Object[]> getActivitiesListByTypeAndLevel(Long activityTypeId,Long  activityLevelId);
}
