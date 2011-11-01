package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;
import com.itgrids.partyanalyst.dao.ICandidateUpdatesEmailDAO;

public class CandidateUpdatesEmailDAOHibernateTest extends BaseDaoTestCase{
	
	private ICandidateUpdatesEmailDAO candidateUpdatesEmailDAO;

	public void setCandidateUpdatesEmailDAO(
			ICandidateUpdatesEmailDAO candidateUpdatesEmailDAO) {
		this.candidateUpdatesEmailDAO = candidateUpdatesEmailDAO;
	}
	
	public void test()
	{
		candidateUpdatesEmailDAO.getAll();
	}

}
