package com.itgrids.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.model.GovtWorkProgress;

public interface IGovtWorkProgressDAO extends GenericDao<GovtWorkProgress, Long>{
	public List<Object[]> getLatestStatusOfWork(List<Long> workIds);
	public GovtWorkProgress getWorkProgressId(Long workId,Long govtWorkStatusId);
	public List<Object[]> getAllstatusInfoOfGovtWork(Long workId);
	public List<Object[]> getCompletedMianWorkPercentage(Long userId,Long workTypeId,Long locationScopeId,List<Long> locationValues);
	public List<Object[]> getAllFininsedKmsOfWorks(List<Long> workIds);
	public Object getWorkOverallWorkCompletedPercentage(Long govtWorkId);
	public Object[] getWorkCompletedKms(Long workTypeId);
	public List<Object[]> getStatusWiseAllWorksAndKms(Long workTypeId);
	public List<Object[]> getLOCATIONWISEOVERVIEW(Long workTypeId,Long locationScopeId,Long districtId,Long divisonId,Long subDivisonId,Long mandalId,String workZone);
	public List<Object[]> getLocationLevelStatusWiseOverviewDetails(Long locationScopeId,Long locationLevelId,Long workTypeId);
	public List<Object[]> getWorkZoneStatusWiseKms(Long locationScopeId,Long locationValue,Long workTypeId);
}
