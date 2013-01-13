package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IRegionScopesDAO;
import com.itgrids.partyanalyst.model.RegionScopes;

public class RegionScopesDAO extends GenericDaoHibernate<RegionScopes, Long> implements IRegionScopesDAO {

	public RegionScopesDAO() {
		super(RegionScopes.class);		 
	}
	
	@SuppressWarnings("unchecked")
	public List<String> getScopeById(Long regionScopesId)
	{
		return getHibernateTemplate().find("select model.scope from RegionScopes model where model.regionScopesId = ? ",regionScopesId);
	}
	
	
	public List<Object[]> getAllRegionScopes(){
		
		   return getHibernateTemplate().find("select model.regionScopesId,model.scope from RegionScopes  model order by model.scope ");
		
	}
}
