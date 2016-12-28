package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IAdminHouseSessionDayDAO;
import com.itgrids.partyanalyst.model.AdminHouseSessionDay;

public class AdminHouseSessionDayDAO extends GenericDaoHibernate<AdminHouseSessionDay, Long> implements IAdminHouseSessionDayDAO{

	public AdminHouseSessionDayDAO() {
		super(AdminHouseSessionDay.class);
		
	}

}
