package com.itgrids.dao.impl;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IGovtWorkDAO;
import com.itgrids.model.GovtWork;

@Repository
public class GovtWorkDAO extends GenericDaoHibernate<GovtWork, Long> implements IGovtWorkDAO{

	public GovtWorkDAO() {
		super(GovtWork.class);
	}

}
