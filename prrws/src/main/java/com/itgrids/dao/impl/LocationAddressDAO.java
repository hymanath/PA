package com.itgrids.dao.impl;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.ILocationAddressDAO;
import com.itgrids.model.LocationAddress;

@Repository
public class LocationAddressDAO extends GenericDaoHibernate<LocationAddress, Long> implements ILocationAddressDAO {

	@Autowired
	SessionFactory sessionFactory;

	public LocationAddressDAO() {
		super(LocationAddress.class);

	}

}
