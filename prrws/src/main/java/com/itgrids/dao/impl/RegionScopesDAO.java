package com.itgrids.dao.impl;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

import com.itgrids.model.RegionScopes;

import com.itgrids.dao.IRegionScopesDAO;
@Repository
public class RegionScopesDAO extends GenericDaoHibernate<RegionScopes, Long> implements IRegionScopesDAO{

	public RegionScopesDAO() {
		super(RegionScopes.class);
		
	}

}
