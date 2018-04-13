package com.itgrids.dao.impl;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IRurbanClusterStatusDAO;
import com.itgrids.model.RurbanClusterStatus;
@Repository
public class RurbanClusterStatusDAO extends GenericDaoHibernate<RurbanClusterStatus, Long> implements  IRurbanClusterStatusDAO {
	@Autowired
	SessionFactory sessionFactory; 

	public RurbanClusterStatusDAO() {
		super(RurbanClusterStatus.class);
	}
}
