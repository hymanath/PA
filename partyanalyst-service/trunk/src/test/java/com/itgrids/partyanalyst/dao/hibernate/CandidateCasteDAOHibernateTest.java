package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.ICandidateCasteDAO;

public class CandidateCasteDAOHibernateTest extends BaseDaoTestCase {
	
	private ICandidateCasteDAO candidateCasteDAO;

	public void testGetCandidateCasteDAO() {

		Object object = candidateCasteDAO.getCandidatesCasteDetails((long) 1);
		System.out.println(object.toString());
	}

	public void setCandidateCasteDAO(ICandidateCasteDAO candidateCasteDAO) {
		this.candidateCasteDAO = candidateCasteDAO;
	}
	

}
