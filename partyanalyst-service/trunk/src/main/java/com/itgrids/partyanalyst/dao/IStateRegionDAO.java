package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.District;
import com.itgrids.partyanalyst.model.StateRegion;

public interface IStateRegionDAO extends GenericDao<StateRegion, Long> {

	@SuppressWarnings("unchecked")
	public List getStateRegionByType(Long stateId) ;

    public Long getTotalRegionsInAState(Long stateId);

}
