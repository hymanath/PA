package com.itgrids.dao.impl;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IPanchayatHamletDAO;
import com.itgrids.model.PanchayatHamlet;

@Repository
public class PanchayatHamletDAO extends GenericDaoHibernate<PanchayatHamlet, Long> implements IPanchayatHamletDAO{

	@Autowired
	SessionFactory sessionFactory; 

	public PanchayatHamletDAO() {
		super(PanchayatHamlet.class);

	}
}
