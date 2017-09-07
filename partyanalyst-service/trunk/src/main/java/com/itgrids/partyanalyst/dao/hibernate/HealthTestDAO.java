package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IHealthTestDAO;
import com.itgrids.partyanalyst.model.HealthTest;

public class HealthTestDAO extends GenericDaoHibernate<HealthTest, Long> implements IHealthTestDAO{

	public HealthTestDAO() {
		super(HealthTest.class);
	}

}
