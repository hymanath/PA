package com.itgrids.dao.impl;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IMobileAppUserTypeDAO;
import com.itgrids.model.MobileAppUserType;

@Repository
public class MobileAppUserTypeDAO extends GenericDaoHibernate<MobileAppUserType, Long> implements IMobileAppUserTypeDAO {

	@Autowired
	SessionFactory sessionFactory; 

	public MobileAppUserTypeDAO() {
		super(MobileAppUserType.class);
      
		
	}

}
