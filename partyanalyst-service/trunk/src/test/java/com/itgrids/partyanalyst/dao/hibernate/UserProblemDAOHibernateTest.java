package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;
import com.itgrids.partyanalyst.dao.IUserProblemDAO;

public class UserProblemDAOHibernateTest extends BaseDaoTestCase{

	private IUserProblemDAO userProblemDAO;

	public void setUserProblemDAO(IUserProblemDAO userProblemDAO) {
		this.userProblemDAO = userProblemDAO;
	}
	
	public void test()
	{
		userProblemDAO.getAll();
	}
}
