package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IUserLoginDetailsDAO;

public class UserLoginDetailsDAOHibernateTest extends BaseDaoTestCase{
	
	private IUserLoginDetailsDAO userLoginDetailsDAO;
	
	public void setUserLoginDetailsDAO(IUserLoginDetailsDAO userLoginDetailsDAO){
		this.userLoginDetailsDAO = userLoginDetailsDAO;
	}
	public void test(){
		userLoginDetailsDAO.getAll();
	}

}
