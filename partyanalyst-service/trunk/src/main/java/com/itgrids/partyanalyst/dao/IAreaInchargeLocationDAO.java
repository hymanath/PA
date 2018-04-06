package com.itgrids.partyanalyst.dao;

import java.util.List;
import java.util.Set;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.AreaInchargeLocation;

public interface IAreaInchargeLocationDAO extends GenericDao<AreaInchargeLocation, Long>{

	//public int getLocationIdsOfBooths(List<Long> boothIds);
	public Long getLocationIdsOfBooths(Long boothId);
	public List<Object[]> getAssignedAndUnAssignedBooths(Long levelId,Long levelValue);
	public List<Object[]> getAreaInchargesStatusWiseCount(Long levelId,Long levelValue);
	public List<Object[]> getConstituenciesBaseBoothLocationCount(Long userAccessLevelId,Set<Long> locationValuesSet);
}
