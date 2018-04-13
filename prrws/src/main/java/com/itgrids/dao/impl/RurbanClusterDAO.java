package com.itgrids.dao.impl;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IRurbanClusterDAO;
import com.itgrids.model.RurbanCluster;
import com.itgrids.model.RurbanMissionPhase;
@Repository
public class RurbanClusterDAO extends GenericDaoHibernate<RurbanCluster, Long> implements IRurbanClusterDAO {
	@Autowired
	SessionFactory sessionFactory; 

	public RurbanClusterDAO() {
		super(RurbanCluster.class);
	}
}
