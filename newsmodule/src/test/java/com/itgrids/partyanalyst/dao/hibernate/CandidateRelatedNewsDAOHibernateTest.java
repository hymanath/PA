package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.ICandidateRelatedNewsDAO;

public class CandidateRelatedNewsDAOHibernateTest extends BaseDaoTestCase{

	ICandidateRelatedNewsDAO candidateRelatedNewsDAO;

	public void setCandidateRelatedNewsDAO(
			ICandidateRelatedNewsDAO candidateRelatedNewsDAO) {
		this.candidateRelatedNewsDAO = candidateRelatedNewsDAO;
	}
	
	public void testGetAllfileGallariesOfCandidate(){
		List<Object[]> items = candidateRelatedNewsDAO.getCandidatesContainsNews();
		
		System.out.println(items.size());
		for (Object[] objects : items) {
			System.out.println(objects[2]);
		}
		
	}
}
