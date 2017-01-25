package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.ActivityConductedInfo;

public interface IActivityConductedInfoDAO extends GenericDao<ActivityConductedInfo, Long>{
	public List<Object[]> getSpecialActivitiesDetails(Date fromDate,Date toDate,Long stateId);
	public List<Object[]> getPlannedCountsForScopeIds(List<Long> activityScopeIds,String type);
	public List<Object[]> getIVRCountsForScopeIds(List<Long> activityScopeIds);
	public List<Object[]> getInfocellCountsForScopeIds(List<Long> activityScopeIds);
	public List<Object[]> activitiesDistrictWiseCohort(List<Long> activityIdsLst,Date startDate,Date endDate,Long scopeId);
	public List<Object[]> getDistrictWiseActivityCounts(Long districtId,Long activityScopeId, String searchType,Long stateId,String countType);
	public List<Object[]> getActivityConductedCntBasedOnUserAccesslevel(Long userAccessLevelId,List<Long> userAccessLevelValues,Long stateId,Long activityId,List<Long> activityLevelIds,Date fromDate,Date toDate);
}
