package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IUserSubuserRelationDAO;

public class UserSubuserRelationDAOHibernateTest extends BaseDaoTestCase{

	private IUserSubuserRelationDAO userSubuserRelationDAO;

	public void setUserSubuserRelationDAO(
			IUserSubuserRelationDAO userSubuserRelationDAO) {
		this.userSubuserRelationDAO = userSubuserRelationDAO;
	} 
	
	public void test()
	{
		userSubuserRelationDAO.getAll();
	}
}
