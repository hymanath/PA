package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.dto.SearchAttributeVO;
import com.itgrids.partyanalyst.model.LocationInfo;

public interface ILocationInfoDAO extends GenericDao<LocationInfo, Long>{
	
	public List<Object[]> getAssemblyWiseTotalCounts(List<Long> levelIds,List<Long> constIds);
	public List<Object[]> areaCountListByAreaIdsOnScope(SearchAttributeVO searchAttributeVO,Long stateId);
}
