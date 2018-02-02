package com.itgrids.dao.impl;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IMobileAppUserLoginDAO;
import com.itgrids.model.MobileAppUserLogin;

@Repository
public class MobileAppUserLoginDAO extends GenericDaoHibernate<MobileAppUserLogin, Long> implements IMobileAppUserLoginDAO {

	@Autowired
	SessionFactory sessionFactory; 

	public MobileAppUserLoginDAO() {
		super(MobileAppUserLogin.class);
	}

}
