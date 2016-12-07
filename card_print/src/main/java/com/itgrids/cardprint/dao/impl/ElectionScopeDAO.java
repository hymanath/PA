package com.itgrids.cardprint.dao.impl;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.cardprint.dao.IElectionScopeDAO;
import com.itgrids.cardprint.model.ElectionScope;

public class ElectionScopeDAO extends GenericDaoHibernate<ElectionScope, Long> implements IElectionScopeDAO{

	public ElectionScopeDAO() {
		super(ElectionScope.class);
	}

}
