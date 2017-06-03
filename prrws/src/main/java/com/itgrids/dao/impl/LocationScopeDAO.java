package com.itgrids.dao.impl;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.ILocationScopeDAO;
import com.itgrids.model.LocationScope;

@Repository
public class LocationScopeDAO extends GenericDaoHibernate<LocationScope, Long> implements ILocationScopeDAO {

	@Autowired
	SessionFactory sessionFactory;

	public LocationScopeDAO() {
		super(LocationScope.class);

	}

}
