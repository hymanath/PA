package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IAdminHouseSessionDAO;
import com.itgrids.partyanalyst.model.AdminHouseSession;

public class AdminHouseSessionDAO extends GenericDaoHibernate<AdminHouseSession, Long> implements IAdminHouseSessionDAO {
	public AdminHouseSessionDAO() {
		super(AdminHouseSession.class);

	}
}
