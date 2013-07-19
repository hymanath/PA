package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IUserDAO;

public class UserDAOHibernateTest extends BaseDaoTestCase{
	
	private IUserDAO userDAO;

	public void setUserDAO(IUserDAO userDAO) {
		this.userDAO = userDAO;
	}
	
	/*public void test()
	{
	  userDAO.getAll();
	}*/
	
	public void testcheckCurrentPasswordExist()
	{
	  System.out.println(userDAO.checkCurrentPasswordExist("a", 1L));
	}

}
