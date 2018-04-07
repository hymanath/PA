package com.itgrids.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.model.GovtWorkProgressTrack;

public interface IGovtWorkProgressTrackDAO extends GenericDao<GovtWorkProgressTrack, Long>{
	public List<Object[]> getStatusWiseDayReport(Long workTypeId,Long locationScopeId,List<Long> locationIds,Date startDate,Date endDate,String reportType);
	public List<Object[]> getStatusWiseDayReportForWorkZone(List<Long> workZoneIds,Date fromDate,Date toDate,String reportType);
	public List<Object[]> getLocationStatusDayWiseKms(Date startDate,Date endDate,Long statusId,Long workTypeId,Long districtId,Long divisonId,Long subDivisonId,Long mandalId);
	public List<Object[]> getLocationLevelStatusDayWiseKms(Date startDate,Date endDate,Long statusId,Long workTypeId,Long districtId,Long divisonId,Long subDivisonId,Long mandalId,Long locationLevelId);	
	public List<Object[]> getLocationLevelSubDayWiseKms(Date startDate,Date endDate,Long workTypeId,Long locationScopeId,Long locationLevelId);
	public List<Object[]> getWorkZoneWorkStategsDetailsInfo(Date fromDate,Date toDate,Long workId,Long statusId);
	public List<Object[]> getLocationOverviewStatusDayWiseKms(Date startDate,Date endDate,Long locationScopeId,Long locationValue,Long workTypeId,Long statusId);
}
