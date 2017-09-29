package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.dto.AddressVO;
import com.itgrids.partyanalyst.model.ActivityScope;

public interface IActivityScopeDAO extends GenericDao<ActivityScope, Long>{
	public List<Object[]> getActivitiesListByTypeAndLevel(Long activityTypeId,Long  activityLevelId);
	
	public Object[] getDatesForActivityByActivityScopeId(Long activityScopeId);
	public Long getNoOFTimesOfAnActivity(Long activityScopeId);
	
	public Date getActivityStartDateByActivityScopeId(Long activityScopeId);
	public Object[] getDatesForActivityScopeId(Long activityScopeId);
	public List<Object[]> getActivityLevelsDetails(Long requiredAtrrId,AddressVO addressVO);
	public List<Object[]> getActivitiesListByLevelId(Long levelId,Long requiredAtrrIds);
	public List<Object[]> getActivityScopeIdByActivityAndLevelId(Long activityId);
	public List<Object[]> getActivityLevelAndScopeIdByActivity(Long activityId);
	public Object[] getRequiredDatesOfScope(Long scopeId);
	public Long getNoOfTimesCountForActivityScope(Long scopeId);
	public List<Object[]> getActivitiesScopeDetailsByAddress(AddressVO addressVO);
	
	public List<Object[]> getActivityDetails(Date fromDate,Date toDate,Long stateId);
	//public List<Object[]> getActivityLevelsByActivity(Long activityId);
	public List<Object[]> getScopeNameByActivity(Long activityId,Long activityScopeId,Date fromDate,Date endDate);
	
	public Long getActivityLevelIdByActivityScopeId(Long activityScopeId,Long stateId);
	public List<Object[]> getActivityLevelIdBasedOnActivityId(List<Long> activityIds);
	public List<Object[]> getActivityLevelsByActivity(List<Long> activityIdsList,Date fromDate , Date toDate,Long stateId);
	public List<Object[]> getLevelAndScopeIds(List<Long> activityIds,String searchType);
	public List<Object[]> getActivityDetails1(Date fromDate,Date toDate,Long stateId);
}
