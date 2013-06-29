package com.itgrids.electoralconnect.dao.hibernatetest;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.electoralconnect.dao.IUserDAO;
import com.itgrids.electoralconnect.dao.IUserLoginDAO;

public class UserDAOHibernateTest extends BaseDaoTestCase {
	
	private IUserDAO userDAO;

	public void setUserDAO(IUserDAO userDAO) {
		this.userDAO = userDAO;
	}
	
	public void test(){
		userDAO.getAll();
	}	
}
