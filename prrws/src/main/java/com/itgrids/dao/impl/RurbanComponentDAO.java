package com.itgrids.dao.impl;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IRurbanComponentDAO;
import com.itgrids.model.RurbanComponent;
@Repository
public class RurbanComponentDAO extends GenericDaoHibernate<RurbanComponent, Long> implements IRurbanComponentDAO{
	@Autowired
	SessionFactory sessionFactory; 

	public RurbanComponentDAO() {
		super(RurbanComponent.class);
	}
}
