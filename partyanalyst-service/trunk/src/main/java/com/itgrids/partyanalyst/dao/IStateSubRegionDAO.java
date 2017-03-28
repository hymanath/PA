package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.StateSubRegion;

public interface IStateSubRegionDAO extends GenericDao<StateSubRegion, Long>{

	public List<Object[]> getStateRegionsBySubRegionIds(List<Long> regionIds);
	
	public List<Object[]> getStateSubRegionsByRegionId(Long stateRegionId);
	
}
