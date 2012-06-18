package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IUserRolesDAO;

public class UserRolesDAOHibernateTest extends BaseDaoTestCase{

	private IUserRolesDAO userRolesDAO;

	public void setUserRolesDAO(IUserRolesDAO userRolesDAO) {
		this.userRolesDAO = userRolesDAO;
	}
	
	/*public void test()
	{
		userRolesDAO.getAll();
	}*/
	
	/*public void testGetUserRoles()
	{
		List<Object[]> userRoles = userRolesDAO.getUserRoles(1L);
		System.out.println(userRoles.size());
		for(Object[] param : userRoles){
			System.out.println((Long)param[0]);
			System.out.println(param[1].toString());
		}
	}
	*/
	public void test()
	{
		 List<Object[]> list = userRolesDAO.getAllFreeuser();
		 for(Object[] params : list)
		 {
			 
			 System.out.println(params[0].toString());
			// System.out.println(params[1].toString());
		 }
		
		
	}
}
