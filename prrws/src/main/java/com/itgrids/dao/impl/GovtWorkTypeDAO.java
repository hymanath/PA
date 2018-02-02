package com.itgrids.dao.impl;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IGovtWorkTypeDAO;
import com.itgrids.model.GovtWorkType;

@Repository
public class GovtWorkTypeDAO extends GenericDaoHibernate<GovtWorkType, Long> implements IGovtWorkTypeDAO {

	@Autowired
	SessionFactory sessionFactory; 

	public GovtWorkTypeDAO() {
		super(GovtWorkType.class);
      
		
	}

}
