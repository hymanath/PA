package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IRegionScopesDAO;
import com.itgrids.partyanalyst.model.RegionScopes;

public class RegionScopesDAO extends GenericDaoHibernate<RegionScopes, Long> implements IRegionScopesDAO {

	public RegionScopesDAO() {
		super(RegionScopes.class);		 
	}
}
