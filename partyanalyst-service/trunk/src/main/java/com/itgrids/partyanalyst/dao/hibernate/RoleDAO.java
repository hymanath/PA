package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IRoleDAO;
import com.itgrids.partyanalyst.model.Role;

public class RoleDAO extends GenericDaoHibernate<Role, Long> implements IRoleDAO{

	public RoleDAO() {
		super(Role.class);
	}

}
