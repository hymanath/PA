package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IDashboardUserAccessLevelDAO;
import com.itgrids.partyanalyst.model.DashboardUserAccessLevel;

public class DashboardUserAccessLevelDAO extends GenericDaoHibernate<DashboardUserAccessLevel, Long> implements IDashboardUserAccessLevelDAO {
	
	public DashboardUserAccessLevelDAO() {
		super(DashboardUserAccessLevel.class);
	}
}
