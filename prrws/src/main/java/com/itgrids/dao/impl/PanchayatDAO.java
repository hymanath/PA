package com.itgrids.dao.impl;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IPanchayatDAO;
import com.itgrids.model.Panchayat;

@Repository
public class PanchayatDAO extends GenericDaoHibernate<Panchayat, Long> implements	IPanchayatDAO {

	@Autowired
	SessionFactory sessionFactory; 

	public PanchayatDAO() {
		super(Panchayat.class);

	}
	
}
