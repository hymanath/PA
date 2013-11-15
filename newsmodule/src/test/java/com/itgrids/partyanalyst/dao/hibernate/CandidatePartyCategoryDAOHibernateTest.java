package com.itgrids.partyanalyst.dao.hibernate;

import com.itgrids.partyanalyst.dao.ICandidatePartyCategoryDAO;

public class CandidatePartyCategoryDAOHibernateTest extends BenefitDAOHibernateTest{
	
	private ICandidatePartyCategoryDAO candidatePartyCategoryDAO;

	public void setCandidatePartyCategoryDAO(
			ICandidatePartyCategoryDAO candidatePartyCategoryDAO) {
		this.candidatePartyCategoryDAO = candidatePartyCategoryDAO;
	}
	
	public void test()
	{
	 candidatePartyCategoryDAO.getAll();	
	}

}
