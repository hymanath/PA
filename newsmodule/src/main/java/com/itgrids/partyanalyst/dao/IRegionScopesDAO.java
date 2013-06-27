package com.itgrids.partyanalyst.dao;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.RegionScopes;

public interface IRegionScopesDAO extends GenericDao<RegionScopes, Long> {

	/*public List<String> getScopeById(Long regionScopesId);
	public List<Object[]> getAllRegionScopes();
	public List<Object[]> getAllRegionScopesWithOutOrderBy();*/
	public Long getRegionScopeIdByScope(String scope);
}
