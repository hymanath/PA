package com.itgrids.dao.impl;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IMobileAppUserLocationDAO;
import com.itgrids.model.MobileAppUserLocation;

@Repository
public class MobileAppUserLocationDAO extends GenericDaoHibernate<MobileAppUserLocation, Long> implements IMobileAppUserLocationDAO {

	@Autowired
	SessionFactory sessionFactory; 

	public MobileAppUserLocationDAO() {
		super(MobileAppUserLocation.class);
      
		
	}

}
