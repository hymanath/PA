package com.itgrids.dao.impl;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.ITehsilDAO;
import com.itgrids.model.Tehsil;
@Repository
public class TehsilDAO extends GenericDaoHibernate<Tehsil,Long> implements ITehsilDAO{
	@Autowired
	SessionFactory sessionFactory; 
	public TehsilDAO() {
		super(Tehsil.class);
	}

}
