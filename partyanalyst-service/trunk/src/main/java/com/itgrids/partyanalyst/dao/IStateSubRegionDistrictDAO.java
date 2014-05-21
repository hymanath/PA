package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.StateSubRegionDistrict;

public interface IStateSubRegionDistrictDAO extends GenericDao<StateSubRegionDistrict, Long>{
	public List<Object[]> getAssemblyConstituenciesBySubRegionIds(List<Long> subRegionId);
}
