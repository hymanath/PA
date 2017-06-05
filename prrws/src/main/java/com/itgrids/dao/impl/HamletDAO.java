package com.itgrids.dao.impl;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import com.itgrids.dao.IHamletDAO;

import com.itgrids.model.Hamlet;

@Repository
public class HamletDAO extends GenericDaoHibernate<Hamlet, Long> implements IHamletDAO {

	@Autowired
	SessionFactory sessionFactory;

	public HamletDAO() {
		super(Hamlet.class);

	}


}
