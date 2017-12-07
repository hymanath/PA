package com.itgrids.dao.impl;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IPetitionWorkTrackingDAO;
import com.itgrids.model.PetitionWorkTracking;


@Repository
public class PetitionWorkTrackingDAO extends GenericDaoHibernate<PetitionWorkTracking, Long> implements IPetitionWorkTrackingDAO {

	@Autowired
	SessionFactory sessionFactory;
	
	PetitionWorkTrackingDAO(){
		super(PetitionWorkTracking.class);
	}
}
