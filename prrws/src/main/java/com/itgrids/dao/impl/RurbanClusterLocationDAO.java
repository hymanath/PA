package com.itgrids.dao.impl;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IRurbanClusterLocationDAO;
import com.itgrids.model.RurbanCluster;
import com.itgrids.model.RurbanClusterLocation;
@Repository
public class RurbanClusterLocationDAO extends GenericDaoHibernate<RurbanClusterLocation, Long>implements IRurbanClusterLocationDAO{
	@Autowired
	SessionFactory sessionFactory; 

	public RurbanClusterLocationDAO() {
		super(RurbanClusterLocation.class);
	}
}
