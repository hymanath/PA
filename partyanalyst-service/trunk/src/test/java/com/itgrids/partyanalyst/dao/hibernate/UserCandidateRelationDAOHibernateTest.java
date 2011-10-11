package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IUserCandidateRelationDAO;

public class UserCandidateRelationDAOHibernateTest extends BaseDaoTestCase{
	
	private IUserCandidateRelationDAO userCandidateRelationDAO;

	public void setUserCandidateRelationDAO(
			IUserCandidateRelationDAO userCandidateRelationDAO) {
		this.userCandidateRelationDAO = userCandidateRelationDAO;
	}
	
	public void test()
	{
		userCandidateRelationDAO.getAll();
	}

}
