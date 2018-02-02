package com.itgrids.dao.impl;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IMobileAppUserDAO;
import com.itgrids.model.MobileAppUser;

@Repository
public class MobileAppUserDAO extends GenericDaoHibernate<MobileAppUser, Long> implements IMobileAppUserDAO {

	@Autowired
	SessionFactory sessionFactory; 

	public MobileAppUserDAO() {
		super(MobileAppUser.class);
      
		
	}

}
