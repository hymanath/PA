package com.itgrids.dao.impl;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.ILightsVendorDAO;
import com.itgrids.model.LightsVendor;

@Repository
public class LightsVendorDAO extends GenericDaoHibernate<LightsVendor, Long> implements ILightsVendorDAO {

	@Autowired
	SessionFactory sessionFactory;

	public LightsVendorDAO() {
		super(LightsVendor.class);

	}
}
