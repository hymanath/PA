package com.itgrids.dao.impl;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IConstituencyDAO;
import com.itgrids.model.Constituency;

@Repository
public class ConstituencyDAO extends GenericDaoHibernate<Constituency, Long> implements IConstituencyDAO {

	@Autowired
	SessionFactory sessionFactory;

	public ConstituencyDAO() {
		super(Constituency.class);

	}

}
