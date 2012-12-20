package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IUserVoterDetailsDAO;

public class UserVoterDetailsDAOHibernateTest extends BaseDaoTestCase  {
	
	private IUserVoterDetailsDAO userVoterDetailsDAO;

		public void setUserVoterDetailsDAO(IUserVoterDetailsDAO userVoterDetailsDAO) {
		this.userVoterDetailsDAO = userVoterDetailsDAO;
	}

	public void testgetUserVoterDetails()
	{
		Object object=userVoterDetailsDAO.getUserVoterDetails(5105l,5l);
		System.out.println(object);
	}

}
