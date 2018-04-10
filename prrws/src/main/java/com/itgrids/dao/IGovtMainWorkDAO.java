package com.itgrids.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.model.GovtMainWork;

public interface IGovtMainWorkDAO extends GenericDao<GovtMainWork, Long>{
	public List<Object[]> getPraposalWorksCount(Long workTypeId);
	public List<Object[]> getAllMainWorksForUser(Long workTypeId,Long locationSclopeId,List<Long> locationValues);
	public Object getEstimationCosrOfLocationBasedMainWorks(Long locationScopeId,Long locationValue,Long workTypeId);
	public List<Object[]> getLocationWiseWorksTotalKms(List<Long> locationIds,Long locationLevelId,String workZoneReq);
}
