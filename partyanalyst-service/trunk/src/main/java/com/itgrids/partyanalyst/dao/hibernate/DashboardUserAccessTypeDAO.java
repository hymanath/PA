package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IDashboardUserAccessTypeDAO;
import com.itgrids.partyanalyst.model.DashboardUserAccessType;

public class DashboardUserAccessTypeDAO extends GenericDaoHibernate<DashboardUserAccessType, Long> implements IDashboardUserAccessTypeDAO {
	
	public DashboardUserAccessTypeDAO() {
		super(DashboardUserAccessType.class);
	}

}
