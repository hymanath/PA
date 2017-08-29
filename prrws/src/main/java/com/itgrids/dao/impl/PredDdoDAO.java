package com.itgrids.dao.impl;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IPredDdoDAO;
import com.itgrids.model.PredDdo;

@Repository
public class PredDdoDAO extends GenericDaoHibernate<PredDdo, Long> implements IPredDdoDAO {

	@Autowired
	SessionFactory  sessionFactory;
	
	public PredDdoDAO() {
		super(PredDdo.class);
	}

}
