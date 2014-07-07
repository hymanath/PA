package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.ICandidateDetailsDAO;

public class CandidateDetailsDAOHibernateTest extends BaseDaoTestCase
{
	private ICandidateDetailsDAO candidateDetailsDAO;

	public ICandidateDetailsDAO getCandidateDetailsDAO() {
		return candidateDetailsDAO;
	}

	public void setCandidateDetailsDAO(ICandidateDetailsDAO candidateDetailsDAO) {
		this.candidateDetailsDAO = candidateDetailsDAO;
	}
	
	
}
