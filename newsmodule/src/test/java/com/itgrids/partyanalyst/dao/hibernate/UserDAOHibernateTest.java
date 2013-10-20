package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

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
	
	/*public void testcheckCurrentPasswordExist()
	{
	  System.out.println(userDAO.checkCurrentPasswordExist("a", 1L));
	}*/
	
	public void testgetUserNameAndPassword()
	{
		List<String> listt = userDAO.getUserNameAndPassword("b", "A");
		System.out.println(listt.size());
		if(listt.size() > 0)
		if(listt.contains("A"))
		 System.out.println("-----------------------------------------A");
		 System.out.println("-----------------------------------------b");
		
	}

}
