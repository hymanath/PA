package com.itgrids.dao.impl;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IRurbanMissionPhaseDAO;
import com.itgrids.model.District;
import com.itgrids.model.RurbanMissionPhase;
@Repository
public class RurbanMissionPhaseDAO extends GenericDaoHibernate<RurbanMissionPhase, Long> implements IRurbanMissionPhaseDAO {
	@Autowired
	SessionFactory sessionFactory; 

	public RurbanMissionPhaseDAO() {
		super(RurbanMissionPhase.class);
      
		
	}
}
