package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.RequiredAttribute;

public interface IRequiredAttributeDAO extends GenericDao<RequiredAttribute, Long>{

	public List<Object[]> getAttributesTypes(Long activityScopeId);
	
}
