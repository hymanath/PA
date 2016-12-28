package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IAdminHouseDAO;
import com.itgrids.partyanalyst.model.AdminHouse;

public class AdminHouseDAO extends GenericDaoHibernate<AdminHouse, Long> implements IAdminHouseDAO{

	public AdminHouseDAO() {
		super(AdminHouse.class);
		
	}

}
