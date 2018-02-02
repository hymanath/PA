package com.itgrids.dao.impl;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IGovtWorkProgressUpdateDAO;
import com.itgrids.model.GovtWorkProgressUpdate;

@Repository
public class GovtWorkProgressUpdateDAO extends GenericDaoHibernate<GovtWorkProgressUpdate, Long> implements IGovtWorkProgressUpdateDAO{

	public GovtWorkProgressUpdateDAO() {
		super(GovtWorkProgressUpdate.class);
	}

}
