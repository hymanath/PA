package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.ActivityConductedInfo;

public interface IActivityConductedInfoDAO extends GenericDao<ActivityConductedInfo, Long>{
	public List<Object[]> getSpecialActivitiesDetails(Date fromDate,Date toDate,Long stateId);
	public List<Object[]> getPlannedCountsForScopeIds(List<Long> activityScopeIds,String type,Long userAccessLevelId,Set<Long> userAccessLevelValues);
	public List<Object[]> getIVRCountsForScopeIds(List<Long> activityScopeIds,Long stateId,Long userAccessLevelId,Set<Long> userAccessLevelValues);
	public List<Object[]> getInfocellCountsForScopeIds(List<Long> activityScopeIds,Long stateId1,Long userAccessLevelId,Set<Long> userAccessLevelValues);
	public List<Object[]> activitiesDistrictWiseCohort(List<Long> activityIdsLst,Date startDate,Date endDate,Long scopeId,String type,Long userAccessLevelId,Set<Long> userAccessLevelValues);
	public List<Object[]> getDistrictWiseActivityCounts(Long districtId,Long activityScopeId, String searchType,Long stateId,String countType,String type,Long userAccessLevelId ,Set<Long> userAccessLevelValues);
	public List<Object[]> getActivityConductedCntBasedOnUserAccesslevel(Long userAccessLevelId,List<Long> userAccessLevelValues,Long stateId,Long activityId,List<Long> activityLevelIds,Date fromDate,Date toDate);
	public List<Object[]> getActivityConductedCount(Long userAccessLevelId,List<Long> userAccessLevelValues,Long stateId);
	public List<Object[]> getLocationWiseConductedActivitiesCount(Long userAccessLevelId,List<Long> userAccessLevelValues,Long stateId,List<Long> activitiesList);
	public List<Object[]> getLocationWiseTotalActivitiesCount(Long userAccessLevelId,List<Long> userAccessLevelValues,Long stateId,List<Long> activitiesList);
	public List<Object[]> getLocationWiseEventInviteedCntBasedOnUserType(Long userAccessLevelId,List<Long> userAccessLevelValues,Long stateId,List<Long> activitiesIdsList,String locationType);
	public List<Object[]> getLocationWiseEventInviteeAttendedCntBasedOnUserType(Long userAccessLevelId,List<Long> userAccessLevelValues,Long stateId,List<Long> activitiesIdsList,String locationType);
}
