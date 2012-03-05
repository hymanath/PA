package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.StateRegion;
import com.itgrids.partyanalyst.model.StateRegionDistrict;

public interface IStateRegionDistrictDAO extends GenericDao<StateRegionDistrict, Long> {

	@SuppressWarnings("unchecked")
	public List getStateRegionDistrictByType(Long stateRegionId);
	
	@SuppressWarnings("unchecked")
	public List getDistrictsInARegion(Long regionId);
	
	public List<Long> getConstituenciesCountByDistrictRegion(Long regionId);
}
