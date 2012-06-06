package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IRoleDAO;

public class RoleDAOHibernateTest extends BaseDaoTestCase{
	
	private IRoleDAO roleDAO;
	
	public void setRoleDAO(IRoleDAO roleDAO) {
		this.roleDAO = roleDAO;
	}

	public void test()
	{
		roleDAO.getAll();
	}

}
