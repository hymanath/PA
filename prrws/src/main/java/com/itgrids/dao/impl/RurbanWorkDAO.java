package com.itgrids.dao.impl;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IRurbanWorkDAO;
import com.itgrids.model.RurbanWork;

@Repository
public class RurbanWorkDAO extends GenericDaoHibernate<RurbanWork, Long> implements IRurbanWorkDAO{
	@Autowired
	SessionFactory sessionFactory; 

	public RurbanWorkDAO() {
		super(RurbanWork.class);
	}

}
