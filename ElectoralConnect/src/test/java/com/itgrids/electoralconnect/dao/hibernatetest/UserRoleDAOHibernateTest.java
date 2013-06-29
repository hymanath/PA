package com.itgrids.electoralconnect.dao.hibernatetest;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.electoralconnect.dao.IUserRolesDAO;

public class UserRoleDAOHibernateTest extends BaseDaoTestCase{
	
	private IUserRolesDAO userRolesDAO;

	public void setUserRolesDAO(IUserRolesDAO userRolesDAO) {
		this.userRolesDAO = userRolesDAO;
	}
	
	public void test(){
		userRolesDAO.getAll();
	}

}
