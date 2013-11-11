package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.ICandidatePartyFileDAO;

public class CandidatePartyFileDAOHibernateTest extends BaseDaoTestCase{

	private ICandidatePartyFileDAO candidatePartyFileDAO;

	public void setCandidatePartyFileDAO(
			ICandidatePartyFileDAO candidatePartyFileDAO) {
		this.candidatePartyFileDAO = candidatePartyFileDAO;
	}
	
	public void test()
	{
	  candidatePartyFileDAO.getAll();
	}
}
