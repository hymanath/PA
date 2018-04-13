package com.itgrids.dao.impl;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IRurbanClusterPhaseDAO;
import com.itgrids.model.RurbanClusterPhase;
@Repository
public class RurbanClusterPhaseDAO extends GenericDaoHibernate<RurbanClusterPhase, Long> implements IRurbanClusterPhaseDAO {
	@Autowired
	SessionFactory sessionFactory; 

	public RurbanClusterPhaseDAO() {
		super(RurbanClusterPhase.class);
	}
}
