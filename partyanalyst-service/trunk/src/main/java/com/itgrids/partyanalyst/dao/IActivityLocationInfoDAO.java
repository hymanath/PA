package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.dto.AddressVO;
import com.itgrids.partyanalyst.dto.SearchAttributeVO;
import com.itgrids.partyanalyst.model.ActivityLocationInfo;

public interface IActivityLocationInfoDAO extends GenericDao<ActivityLocationInfo, Long>{
	public List<Object[]> getUpdatedLocationsListForScope(Long activityScopeId,Date startDate,Date endDate);
	public List<Object[]> getAssemblyConstWiseDetails(Date startDate,Date endDate,Long activityScopeId,List<Long> constIds);
	public List<Object[]> getActivityPlannedInfoCellAndIVRCountsByLocation(SearchAttributeVO searchAttributeVO,Long stateId);
	public List<Object[]> getActivityNotPlannedInfoCellAndIVRCountsByLocation(SearchAttributeVO searchAttributeVO,Long stateId);
	public List<Object[]> getActivityAttributeCountsByLocation(SearchAttributeVO searchAttributeVO,Long stateId);
	public List<Object[]> getActivityDayWiseCountsByLocation(SearchAttributeVO searchAttributeVO,Long stateId);
	public List<Long> getActivityLocationInfoIdByLocationLevelAndLocationValue(Long activityScopeId,Long locationLevel,Long locationValue);
	public List<Object[]> getDistrictWiseDetails(Date startDate,Date endDate,Long activityScopeId,List<Long> distIds);
	public List<Object[]> getPlannedCountForAssemblyConstWise(Date startDate,Date endDate,Long activityScopeId,List<Long> constIds);
	public List<Object[]> getNotPlannedCountForAssemblyConstWise(Long activityScopeId,List<Long> constIds);
	public List<Object[]> getNotConductedCountForAssemblyConstWise(Date startDate,Date endDate,Long activityScopeId,List<Long> constIds);
	public List<Long> isAlreadyAvailableActivityLocationDtls(Long activityScopeId,Long locationLevel,Long locationValue);
	public List<Object[]> getActivityLocationDetailsByScopeId(Long scopeId);
	public List<Object[]> getActivityDetailsInCadreLocation(List<Long> activityScopeIds,Long locationId,Long locationValue);
	public List<Object[]> getLocationDetailsByInfoIds(List<Long> activityInfoIds);
	public List<Object[]> getActivityLocationNames(List<Long> activityInfoIds,Long locationlevel);
	public List<Object[]> getLocationWiseUpdatedCountDetails(SearchAttributeVO searchVO);
	public List<Object[]> getActivityNotPlannedDayWiseCountsByLocation(SearchAttributeVO searchAttributeVO,Long stateId);
	public List<Object[]> getConductedActivityDetailsbyScopeAndLocationID(Long activityLevelId,Long panchayatId,Long mandalId,Long lebId,Long assemblyId,Long districtId,Long stateId,Long participatedAssemblyId);
	public List<Object[]> getActivityDetailsByAddressDetails(AddressVO addressVO);	
	public List<Object[]> getConductedActivityDetailsbyScopeAndLocationID(AddressVO addressVO,Long activityLevelId);
	
	public List<Object[]> getPlannedCountsForScopeIds(List<Long> activityScopeIds,String type,Long userAccessLevelId,Set<Long> locationValues);
	public List<Object[]> getIVRCountsForScopeIds(List<Long> activityScopeIds,Long stateId,Long userAccessLevelId,Set<Long> userAccessLevelValues);
	public List<Object[]> getInfocellCountsForScopeIds(List<Long> activityScopeIds,Long stateId1,Long userAccessLevelId,Set<Long> userAccessLevelValues);
	public List<Object[]> getDistrictWiseActivityCounts(Long districtId,Long activityScopeId, String searchType,Long stateId ,String countType,String type,Long userAccessLevelId ,Set<Long> userAccessLevelValues);
	public List<Object[]> getActivityConductedCntBasedOnUserAccesslevel(Long userAccessLevelId,List<Long> userAccessLevelValues,Long stateId,Long activityId,List<Long> activityLevelIds,Date fromDate,Date toDate);
	public void flushAndclearSession();
	public List<Object[]> activitiesDistrictWiseCohort(List<Long> activityIdsLst,Date startDate,Date endDate,Long scopeId,Long stateId,String type,Long userAccessLevelId,Set<Long> userAccessLevelValues);
	public List<Object[]> getActivityAttendedCountLocationWise(Long userAccessLevelId,List<Long> userAccessLevelValues,Long stateId,List<Long> activityIds);
	public List<Object[]> getConductedCounts(Long locationId,Long activityScopeId, String searchType,Long stateId,String levelId,Long locationAccessLevelId
			,Set<Long> locationValues);
	public List<Object[]> getActivityConductedCount(Long userAccessLevelId,List<Long> userAccessLevelValues,Long stateId);
	public List<Object[]> getLocationWiseConductedActivitiesCount(Long userAccessLevelId,List<Long> userAccessLevelValues,Long stateId,List<Long> activitiesList);
	public List<Object[]> getEventAttendedCountLocationWise(Long userAccessLevelId,List<Long> userAccessLevelValues,Long stateId,List<Long> activitiesIdsList);
	public List<Object[]> getEventInviteeAttendedCountLocationWise(Long userAccessLevelId,List<Long> userAccessLevelValues,Long stateId,List<Long> activitiesIdsList);
	public List<Object[]> getLocationWiseTotalActivitiesCount(Long userAccessLevelId,List<Long> userAccessLevelValues,Long stateId,List<Long> activitiesList);
	public List<Object[]> getLocationWiseEventInviteedCntBasedOnUserType(Long userAccessLevelId,List<Long> userAccessLevelValues,Long stateId,List<Long> activitiesIdsList,String locationType);
	public List<Object[]> getLocationWiseEventInviteeAttendedCntBasedOnUserType(Long userAccessLevelId,List<Long> userAccessLevelValues,Long stateId,List<Long> actiovitiesIds,String locationType);
	public List<Object[]> getTotalCountsForScopeIds(List<Long> activityScopeIds,String type,Long userAccessLevelId,Set<Long> userAccessLevelValues);
	public List<Object[]> getActivityTotalCntBasedOnUserAccesslevel(Long userAccessLevelId,List<Long> userAccessLevelValues,Long stateId,Long activityId,List<Long> activityLevelIds,Date fromDate,Date toDate);
	public List<Object[]> activitiesDistrictWiseCount(List<Long> activityIdsLst,Date startDate,Date endDate,Long scopeId,Long stateId,String type,Long userAccessLevelId,Set<Long> userAccessLevelValues);
	public List<Object[]> getCnstenciesByActivityId(Long activityId);
	public List<Object[]> getMandalsByConstituency(Long constituencyId,Long activityScopeId);
	public List<Object[]> getLocationWise(Long userAccessLevelId,Long userAccessLevelValues,Long activityScopeId,String locationType,String checkedValue);
	public List<Object[]> getDistrictsByActivityId(Long activityScopeId);
	public List<Object[]> getMuncipalitiesByConstituency(Long constituencyId,Long activityScopeId);
	public ActivityLocationInfo isAlreadyAvailableLocationDtls(Long locationInfoId);
	public List<Object[]> getPanchayatByTehsil(Long tehsilId,Long activityScopeId );
	public List<Object[]> getWardsByMun(Long muncipId,Long activityScopeId );
	public List<Object[]> getConstituenciesByDistricts(Long districtId,Long activityScopeId );
	public List<Object[]> getConductedForAssemblyConstWise(Long activityScopeId,List<Long> constIds);
	public List<Object[]> getNotConductedForAssemblyConstWise(Long activityScopeId,List<Long> constIds);
	public List<Object[]> getNotUpdatedCuntForAssemblyConstWise(Long activityScopeId,List<Long> constIds);
	public List<Object[]> getConductedCountForDistrict(Long activityScopeId,List<Long> distIds);
	public List<Object[]> getNotConductedCountForDistrict(Long activityScopeId,List<Long> distIds);
	public List<Object[]> getNotUpdatedForDistrict(Long activityScopeId,List<Long> distIds);
	public List<Object[]> getActivityDetailsBasedOnLocation(String groupType,String filterType,List<Long> userAccessLevelValues,Long activityScopeId,Long constituencyId);
	public List<Long> getActivityConductedInfoId(Long  activityScopeId,String locationType,Long locationId);
	public List<Object[]> getLocationwiseCoductedCount(Long activityId,Long scopeId, String locationtype, String type);
}
