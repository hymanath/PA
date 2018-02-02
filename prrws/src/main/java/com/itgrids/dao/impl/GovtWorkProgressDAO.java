package com.itgrids.dao.impl;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IGovtWorkProgressDAO;
import com.itgrids.model.GovtWorkProgress;

@Repository
public class GovtWorkProgressDAO extends GenericDaoHibernate<GovtWorkProgress, Long> implements IGovtWorkProgressDAO{

	public GovtWorkProgressDAO() {
		super(GovtWorkProgress.class);
	}

}
