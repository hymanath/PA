package com.itgrids.dao.impl;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IElectionTypeDAO;
import com.itgrids.model.ElectionType;

@Repository
public class ElectionTypeDAO extends GenericDaoHibernate<ElectionType, Long> implements IElectionTypeDAO {

	@Autowired
	SessionFactory sessionFactory;

	public ElectionTypeDAO() {
		super(ElectionType.class);

	}

}
