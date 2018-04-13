package com.itgrids.dao.impl;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IRurbanWorkLocationDAO;
import com.itgrids.model.RurbanWorkLocation;

@Repository
public class RurbanWorkLocationDAO extends GenericDaoHibernate<RurbanWorkLocation, Long> implements IRurbanWorkLocationDAO{
	@Autowired
	SessionFactory sessionFactory; 

	public RurbanWorkLocationDAO() {
		super(RurbanWorkLocation.class);
	}
}
