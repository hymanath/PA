package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.ActivityScopeRequiredAttributes;

public interface IActivityScopeRequiredAttributesDAO extends GenericDao<ActivityScopeRequiredAttributes, Long>{
	
	public List<Object[]> getActivityRequiredAttributes(List<Long> scopeIds);
	public List<Object[]> getActivityRequiredAttributesForScope(Long scopeId);

}
