package com.itgrids.dao.impl;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.itgrids.dao.IPmOfficerDAO;
import com.itgrids.model.PmOfficer;

public class PmOfficerDAO extends GenericDaoHibernate<PmOfficer, Long> implements IPmOfficerDAO {

	@Autowired
	SessionFactory sessionFactory;
	PmOfficerDAO(){
		super(PmOfficer.class);
	}
}
