package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.dto.SearchAttributeVO;
import com.itgrids.partyanalyst.model.LocationInfo;

public interface ILocationInfoDAO extends GenericDao<LocationInfo, Long>{
	
	public List<Object[]> getLocationWiseTotalCounts(List<Long> levelIds,List<Long> locationIds,Long scopeId);
	public List<Object[]> areaCountListByAreaIdsOnScope(SearchAttributeVO searchAttributeVO,Long stateId);
}
