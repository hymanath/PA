package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IRolesDAO;
import com.itgrids.partyanalyst.model.Roles;

public class RolesDAO extends GenericDaoHibernate<Roles, Long> implements IRolesDAO{

	public RolesDAO() {
		super(Roles.class);
		
	}

}
