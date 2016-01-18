package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

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
}
