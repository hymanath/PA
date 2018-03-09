package com.itgrids.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.model.GovtWork;

public interface IGovtWorkDAO extends GenericDao<GovtWork, Long>{
	public List<Object[]> getWorkZoneDetailsOfMainWork(Long userId,Long mainWorkId);
	public Long getWorkTypeId(Long govtWorkId);
	public List<Object[]> getUsersGovtMainWorks(Long userId,Long govtWorkTypeId,Long locationScopeId,List<Long> locationValues);
	public Object getOverallWork(Long workTypeId,Long locationScopeId,List<Long> locationIds);
	public List<Object[]> getWorksCountByMainType();
	public List<Object[]> getWorkZonesCountForDateType(Long workTypeId);
	public List<Object[]> getAllWorkZonesOfLocations(Long locationScopeId,List<Long> locationIds);
	public Object getOverallLength(List<Long> workZoneIds);
	public List<Object[]> getCompletedWorksCount();
	public Object[] getOverallWorksLengthOfWorkType(Long workTypeId);
	public Object[] getAllworkZonesOfLocation(Long locationScopeId,Long locationValue,Long workTypeId);
	public Object[] getCompletedWorksDetailsOfLocation(Long locationScopeId,Long locationValue,Long workTypeId);
	public Object[] getWorkZoneMainOverview(Long govtWorkId);
}
