package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IRegionScopesProblemTypeDAO;
import com.itgrids.partyanalyst.model.RegionScopesProblemType;

public class RegionScopesProblemTypeDAO extends GenericDaoHibernate<RegionScopesProblemType, Long> implements IRegionScopesProblemTypeDAO{

	public RegionScopesProblemTypeDAO() {
		super(RegionScopesProblemType.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getProblemTypesByRegionScopeId(Long regionScopeId)
	{
		return getHibernateTemplate().find("select model.problemType.problemTypeId,model.problemType.problemType from RegionScopesProblemType model where " +
				" model.regionScopes.regionScopesId = ? order by orderNo",regionScopeId);
	}
}
