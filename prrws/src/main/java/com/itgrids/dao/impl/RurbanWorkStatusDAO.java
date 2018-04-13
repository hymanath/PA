package com.itgrids.dao.impl;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IRurbanWorkStatusDAO;
import com.itgrids.model.RurbanWorkStatus;

@Repository
public class RurbanWorkStatusDAO extends GenericDaoHibernate<RurbanWorkStatus, Long>implements  IRurbanWorkStatusDAO  {
	@Autowired
	SessionFactory sessionFactory; 

	public RurbanWorkStatusDAO() {
		super(RurbanWorkStatus.class);
	}
}
