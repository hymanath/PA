package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

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
	
	public List<Object[]> getPlannedCountsForScopeIds(List<Long> activityScopeIds,String type);
	public List<Object[]> getIVRCountsForScopeIds(List<Long> activityScopeIds);
	public List<Object[]> getInfocellCountsForScopeIds(List<Long> activityScopeIds);
	public List<Object[]> activitiesDistrictWiseCohort(List<Long> activityIdsLst,Date startDate,Date endDate);
	public List<Object[]> getDistrictWiseActivityCounts(Long districtId,Long activityScopeId, String searchType,Long stateId ,String countType);
	public List<Object[]> getActivityConductedCntBasedOnUserAccesslevel(Long userAccessLevelId,List<Long> userAccessLevelValues,Long stateId,Long activityId,List<Long> activityLevelIds,Date fromDate,Date toDate);
	public void flushAndclearSession();
}
