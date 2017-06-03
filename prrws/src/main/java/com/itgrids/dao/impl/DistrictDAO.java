package com.itgrids.dao.impl;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IDistrictDAO;
import com.itgrids.model.District;

@Repository
public class DistrictDAO extends GenericDaoHibernate<District, Long> implements IDistrictDAO {

	@Autowired
	SessionFactory sessionFactory; 

	public DistrictDAO() {
		super(District.class);

	}

}
