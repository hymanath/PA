package com.itgrids.dao.impl;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IPmTrackingDAO;
import com.itgrids.model.PmTracking;

@Repository
public class PmTrackingDAO extends GenericDaoHibernate<PmTracking, Long> implements IPmTrackingDAO {

	@Autowired
	SessionFactory sessionFactory;
	PmTrackingDAO(){
		super(PmTracking.class);
	}
}
