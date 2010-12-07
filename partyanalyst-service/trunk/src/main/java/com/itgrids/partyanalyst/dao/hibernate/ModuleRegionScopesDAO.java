package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IModuleRegionScopesDAO;
import com.itgrids.partyanalyst.model.ModuleRegionScopes;

public class ModuleRegionScopesDAO extends GenericDaoHibernate<ModuleRegionScopes, Long> implements IModuleRegionScopesDAO {

	public ModuleRegionScopesDAO() {
		super(ModuleRegionScopes.class);		
	}

	public List findRegionScopesForModuleByState(Long moduleId, Long stateId) {
		Object[] params = {moduleId,stateId};
		return getHibernateTemplate().find("select model.regionScope.regionScopesId, model.regionScope.scope from ModuleRegionScopes model" +
				" where model.module.moduleId = ? and model.state.stateId = ?", params);
	}

}
