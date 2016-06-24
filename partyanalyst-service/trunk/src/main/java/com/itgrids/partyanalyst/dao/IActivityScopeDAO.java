package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.ActivityScope;

public interface IActivityScopeDAO extends GenericDao<ActivityScope, Long>{
	public List<Object[]> getActivitiesListByTypeAndLevel(Long activityTypeId,Long  activityLevelId);
	
	public Object[] getDatesForActivityByActivityScopeId(Long activityScopeId);
	public Long getNoOFTimesOfAnActivity(Long activityScopeId);
	
	public Date getActivityStartDateByActivityScopeId(Long activityScopeId);
	public Object[] getDatesForActivityScopeId(Long activityScopeId);
	public List<Object[]> getActivityLevelsDetails(Long requiredAtrrIds);
	public List<Object[]> getActivitiesListByLevelId(Long levelId,Long requiredAtrrIds);
	public List<Object[]> getActivityScopeIdByActivityAndLevelId(Long activityId);
	public List<Object[]> getActivityLevelAndScopeIdByActivity(Long activityId);
	public Object[] getRequiredDatesOfScope(Long scopeId);
	public Long getNoOfTimesCountForActivityScope(Long scopeId);
}
