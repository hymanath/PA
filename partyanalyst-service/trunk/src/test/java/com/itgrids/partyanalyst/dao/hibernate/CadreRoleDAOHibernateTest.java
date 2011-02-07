package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.ICadreRoleDAO;

public class CadreRoleDAOHibernateTest extends BaseDaoTestCase{

	private ICadreRoleDAO cadreRoleDAO;

	public void setCadreRoleDAO(ICadreRoleDAO cadreRoleDAO) {
		this.cadreRoleDAO = cadreRoleDAO;
	}
	
	/*public void test()
	{
		cadreRoleDAO.getAll();
	}*/
	
	public void testGetCadreRoles(){
		List<Object[]> list = cadreRoleDAO.getCadreRoles();
		
		for(Object[] params :list)
		{
			System.out.print(params[0]);
			System.out.println("======="+params[1]);
		}
	}
	
	
}
