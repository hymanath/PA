package com.itgrids.electoralconnect.dao.hibernatetest;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.electoralconnect.dao.IUserRolesDAO;

public class UserRoleDAOHibernateTest extends BaseDaoTestCase{
	
	private IUserRolesDAO userRolesDAO;

	public void setUserRolesDAO(IUserRolesDAO userRolesDAO) {
		this.userRolesDAO = userRolesDAO;
	}
	
	/*public void test(){
		userRolesDAO.getAll();
	}*/
	
	public void testcheckForValidUser()
	{
		List<Object[]> values = userRolesDAO.checkForValidUser("hgjjjkjj@gmail.com","6116796");
		System.out.println(values.size());
	}

}
