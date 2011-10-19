package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.ICandidateProfileDescriptionDAO;

public class CandidateProfileDescriptionDAOHibernateTest extends
		BaseDaoTestCase {
	private ICandidateProfileDescriptionDAO candidateProfileDescriptionDAO;

	public void setCandidateProfileDescriptionDAO(
			ICandidateProfileDescriptionDAO candidateProfileDescriptionDAO) {
		this.candidateProfileDescriptionDAO = candidateProfileDescriptionDAO;
	}

	/*public void test() {
		candidateProfileDescriptionDAO.getAll();

	}*/
	
	public void testGetCandidateProfileDescription()
	{
		List<Object> list = candidateProfileDescriptionDAO.getCandidateProfileDescription(1l);
		
		System.out.println(list.size());
		
		for(Object params : list)
		{
			System.out.println(params.toString());
		}
	}
}
