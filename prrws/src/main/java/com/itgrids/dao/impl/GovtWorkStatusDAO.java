package com.itgrids.dao.impl;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IGovtWorkStatusDAO;
import com.itgrids.model.GovtWorkStatus;

@Repository
public class GovtWorkStatusDAO extends GenericDaoHibernate<GovtWorkStatus, Long> implements IGovtWorkStatusDAO{

	public GovtWorkStatusDAO() {
		super(GovtWorkStatus.class);
	}

}
