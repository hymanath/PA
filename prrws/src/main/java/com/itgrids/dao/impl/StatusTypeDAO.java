package com.itgrids.dao.impl;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IStatusTypeDAO;
import com.itgrids.model.StatusType;

@Repository
public class StatusTypeDAO extends GenericDaoHibernate<StatusType, Long> implements IStatusTypeDAO{

	public StatusTypeDAO() {
		super(StatusType.class);
	}

}
