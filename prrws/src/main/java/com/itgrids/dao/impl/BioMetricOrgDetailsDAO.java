package com.itgrids.dao.impl;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IBioMetricOrgDetailsDAO;
import com.itgrids.model.BioMetricOrgDetails;

@Repository
public class BioMetricOrgDetailsDAO extends GenericDaoHibernate<BioMetricOrgDetails,Long> implements IBioMetricOrgDetailsDAO {

	@Autowired
	SessionFactory sessionFactory;
	
	public BioMetricOrgDetailsDAO() {
		super(BioMetricOrgDetails.class);

	}
}
