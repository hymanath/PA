package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.RegionScopes;

public interface IRegionScopesDAO extends GenericDao<RegionScopes, Long> {

	public List<String> getScopeById(Long regionScopesId);
	public List<Object[]> getAllRegionScopes();
	public List<Object[]> getAllRegionScopesWithOutOrderBy();
	public Long getRegionScopeIdByScope(String scope);
	public List<Object[]> getAlertLocationLevelById(List<Long> alertLocationIds);
	public List<Long> getUppderLevelLocationId(Long accessLevelId);
}
