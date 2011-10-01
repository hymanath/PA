package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;
import com.itgrids.partyanalyst.dao.ICandidateGallaryDAO;

public class CandidateGallaryDAOHibernateTest extends BaseDaoTestCase{
	
	private ICandidateGallaryDAO candidateGallaryDAO;

	public void setCandidateGallaryDAO(ICandidateGallaryDAO candidateGallaryDAO) {
		this.candidateGallaryDAO = candidateGallaryDAO;
	}
	
	public void test()
	{
		candidateGallaryDAO.getAll();
	}
}
