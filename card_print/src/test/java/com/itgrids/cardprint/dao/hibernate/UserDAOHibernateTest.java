package com.itgrids.cardprint.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.cardprint.dao.IUserDAO;

public class UserDAOHibernateTest extends BaseDaoTestCase{

	private IUserDAO userDAO;

	public void setUserDAO(IUserDAO userDAO) {
		this.userDAO = userDAO;
	}
	
	public void test()
	{
		userDAO.getAll();
	}
	
}
