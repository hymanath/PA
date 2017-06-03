package com.itgrids.dao.impl;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IElectionScopeDAO;
import com.itgrids.model.ElectionScope;

@Repository
public class ElectionScopeDAO extends GenericDaoHibernate<ElectionScope, Long> implements IElectionScopeDAO {

	@Autowired
	SessionFactory sessionFactory;

	public ElectionScopeDAO() {
		super(ElectionScope.class);

	}

}
