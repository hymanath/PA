package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.ActivityRequiredAttribute;

public interface IActivityRequiredAttributeDAO extends GenericDao<ActivityRequiredAttribute, Long>{

	public List<Long> getActivityScopeAttribue(Long activityScopeId);
}
