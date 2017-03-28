package com.itgrids.partyanalyst.dao;

import java.util.List;
import java.util.Set;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.dto.SearchAttributeVO;
import com.itgrids.partyanalyst.model.LocationInfo;

public interface ILocationInfoDAO extends GenericDao<LocationInfo, Long>{
	
	public List<Object[]> getLocationWiseTotalCounts(List<Long> levelIds,List<Long> locationIds,Long scopeId);
	public List<Object[]> areaCountListByAreaIdsOnScope(SearchAttributeVO searchAttributeVO,Long stateId);
	
	public Long getTotalCountByScope(Long levelId,Long scopeId,Long scopeValue,Long publicationDateId);
	public List<Object[]> getDistrictWiseTotalCountsByLevelId(Long levelId,Long publicationDateId);
	public List<Object[]> areaCountDetailsListByAreaIdsOnScope(SearchAttributeVO searchAttributeVO,Long stateId,Long publicationDateId);
	public List<Object[]> getTotalActivityLocationWise(List<Long> levelIds,Long scopeId,Set<Long> locationValues);
}
