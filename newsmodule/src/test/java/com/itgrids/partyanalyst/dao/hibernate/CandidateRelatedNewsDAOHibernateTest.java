package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
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
		//List<Object[]> items = candidateRelatedNewsDAO.getCandidatesContainsNews();
	// List<?> items =  candidateRelatedNewsDAO.getNotResponseCountBasedTotalNewsCount(null, null,872l, null, null, null, null, null, null, null, null);//.getNotResponseCountBasedTotalNewsCount(null,null,872,null,null,null,null,null,null,null,null);
		List<Object[]> items = candidateRelatedNewsDAO.getNotResponseCountPerfect(null,null,872l,null,null,null,null,"all",null,null,null);
		System.out.println(items.size());
		long count = 0;
	/*	for (Object[] objects : items) {
			System.out.println(objects[0]+"--"+objects[1]+"--"+objects[2]+"--"+objects[3]);
			count+=((Long)objects[0]);
			
		}*/
		System.out.println(count);
		
		
		
	}
}
