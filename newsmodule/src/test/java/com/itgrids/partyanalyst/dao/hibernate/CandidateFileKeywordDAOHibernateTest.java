package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.ICandidateFileKeywordDAO;

public class CandidateFileKeywordDAOHibernateTest extends BaseDaoTestCase{
	
	private ICandidateFileKeywordDAO candidateFileKeywordDAO;

	public void setCandidateFileKeywordDAO(
			ICandidateFileKeywordDAO candidateFileKeywordDAO) {
		this.candidateFileKeywordDAO = candidateFileKeywordDAO;
	}
	
	public void test()
	{
		candidateFileKeywordDAO.getAll();	
	}

}
