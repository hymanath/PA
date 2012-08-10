package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.ICandidateCadreOnlineRegistrationDAO;

public class CandidateCadreOnlineRegistrationDAOHibernateTest extends BaseDaoTestCase{
	
	
	private ICandidateCadreOnlineRegistrationDAO candidateCadreOnlineRegistrationDAO;

	public void setCandidateCadreOnlineRegistrationDAO(
			ICandidateCadreOnlineRegistrationDAO candidateCadreOnlineRegistrationDAO) {
		this.candidateCadreOnlineRegistrationDAO = candidateCadreOnlineRegistrationDAO;
	}

	public void test()
	{
		candidateCadreOnlineRegistrationDAO.getAll();
	}
	

}
