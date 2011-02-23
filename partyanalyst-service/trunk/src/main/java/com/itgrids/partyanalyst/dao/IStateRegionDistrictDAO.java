package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.StateRegion;
import com.itgrids.partyanalyst.model.StateRegionDistrict;

public interface IStateRegionDistrictDAO extends GenericDao<StateRegionDistrict, Long> {

	public List getStateRegionDistrictByType(Long stateRegionId);
}
