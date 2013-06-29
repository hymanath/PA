package com.itgrids.electoralconnect.dao.hibernatetest;

import org.appfuse.dao.BaseDaoTestCase;
import com.itgrids.electoralconnect.dao.IUserLoginDAO;

public class UserLoginDAOHibernateTest extends BaseDaoTestCase {
	
	private IUserLoginDAO userLoginDAO;

	public void setUserLoginDAO(IUserLoginDAO userLoginDAO) {
		this.userLoginDAO = userLoginDAO;
	}
	
	public void test(){
		userLoginDAO.getAll();
	}
}
