package com.itgrids.electoralconnect.dao.hibernatetest;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.electoralconnect.dao.IUserDAO;
import com.itgrids.electoralconnect.dao.IUserLoginDAO;

public class UserDAOHibernateTest extends BaseDaoTestCase {
	
	private IUserDAO userDAO;

	public void setUserDAO(IUserDAO userDAO) {
		this.userDAO = userDAO;
	}
	
	/*public void test(){
		userDAO.getAll();
	}*/	
	
	/*public void testcheckForValidUser()
	{
		List<Object[]> values = userDAO.checkForValidUser("sasi_1680@gmail.com","0803767");
		System.out.println(values.size());
	}*/
	
	public void testcheckForValidUser()
	{
		int value = userDAO.updatePassword("prasad",18l);
		System.out.println(value);
	}
}
