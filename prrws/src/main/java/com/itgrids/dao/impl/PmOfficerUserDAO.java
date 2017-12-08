package com.itgrids.dao.impl;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.itgrids.dao.IPmOfficerUserDAO;
import com.itgrids.model.PmOfficerUser;

public class PmOfficerUserDAO extends GenericDaoHibernate<PmOfficerUser, Long> implements IPmOfficerUserDAO {

	@Autowired
	SessionFactory sessionFactory;

	PmOfficerUserDAO(){
		super(PmOfficerUser.class);
	}
}
