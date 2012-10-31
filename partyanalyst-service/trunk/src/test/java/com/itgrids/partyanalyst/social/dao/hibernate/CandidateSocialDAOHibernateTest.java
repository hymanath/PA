package com.itgrids.partyanalyst.social.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.social.dao.ICandidateSocialDAO;

public class CandidateSocialDAOHibernateTest extends BaseDaoTestCase{

	private ICandidateSocialDAO candidateSocialDAO;

	public void setCandidateSocialDAO(ICandidateSocialDAO candidateSocialDAO) {
		this.candidateSocialDAO = candidateSocialDAO;
	}

	public void test()
	{
		candidateSocialDAO.getAll();
	}
}
