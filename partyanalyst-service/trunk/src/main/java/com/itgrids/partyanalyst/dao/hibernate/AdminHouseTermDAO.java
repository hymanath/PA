package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IAdminHouseTermDAO;
import com.itgrids.partyanalyst.model.AdminHouseTerm;

public class AdminHouseTermDAO extends GenericDaoHibernate<AdminHouseTerm, Long> implements IAdminHouseTermDAO{

	public AdminHouseTermDAO() {
		super(AdminHouseTerm.class);
		
	}

}
