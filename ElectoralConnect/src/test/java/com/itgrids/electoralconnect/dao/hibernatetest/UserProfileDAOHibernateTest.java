package com.itgrids.electoralconnect.dao.hibernatetest;

import org.appfuse.dao.BaseDaoTestCase;
import com.itgrids.electoralconnect.dao.IUserProfileDAO;

public class UserProfileDAOHibernateTest extends BaseDaoTestCase{
	
	private IUserProfileDAO userProfileDAO;

	public void setUserProfileDAO(IUserProfileDAO userProfileDAO) {
		this.userProfileDAO = userProfileDAO;
	}

	public void test(){
		userProfileDAO.getAll();
	}	
	
}
