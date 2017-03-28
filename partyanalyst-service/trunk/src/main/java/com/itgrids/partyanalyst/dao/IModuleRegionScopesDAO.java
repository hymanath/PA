package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.ModuleRegionScopes;

public interface IModuleRegionScopesDAO extends GenericDao<ModuleRegionScopes, Long>{
	
	public List findRegionScopesForModuleByState(Long moduleId, Long stateId);
	
	
}
