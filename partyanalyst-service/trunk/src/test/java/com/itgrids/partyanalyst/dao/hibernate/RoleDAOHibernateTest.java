package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IRoleDAO;
import com.itgrids.partyanalyst.model.Role;

public class RoleDAOHibernateTest extends BaseDaoTestCase{
	
	private IRoleDAO roleDAO;
	
	public void setRoleDAO(IRoleDAO roleDAO) {
		this.roleDAO = roleDAO;
	}
	public IRoleDAO getRoleDAO() {
		return roleDAO;
	}
	public void test()
	{
		roleDAO.getAll();
	}
	
	public void testGetRoleType()
	{
		List<Role> role = roleDAO.getRoleType();
		for(Role roles : role)
		{
			System.out.println(roles.getRoleType().toString());
			
		}
		
	}

	

}
