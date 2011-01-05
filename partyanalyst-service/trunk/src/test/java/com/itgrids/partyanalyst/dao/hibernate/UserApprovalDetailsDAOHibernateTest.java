package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IUserApprovalDetailsDAO;

public class UserApprovalDetailsDAOHibernateTest extends BaseDaoTestCase {

	private IUserApprovalDetailsDAO userApprovalDetailsDAO;

	public void setUserApprovalDetailsDAO(
			IUserApprovalDetailsDAO userApprovalDetailsDAO) {
		this.userApprovalDetailsDAO = userApprovalDetailsDAO;
	}
	
	public void test()
	{
		userApprovalDetailsDAO.getAll();
	}
}
