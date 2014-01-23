package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IDebateRolesDAO;
import com.itgrids.partyanalyst.model.DebateRoles;

public class DebateRolesDAO extends GenericDaoHibernate<DebateRoles, Long> implements IDebateRolesDAO{

	public DebateRolesDAO() {
		super(DebateRoles.class);
		// TODO Auto-generated constructor stub
	}

}
