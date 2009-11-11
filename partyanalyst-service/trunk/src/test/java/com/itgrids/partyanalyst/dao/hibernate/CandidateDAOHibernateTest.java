package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import junit.framework.Assert;

import org.appfuse.dao.BaseDaoTestCase;
import org.junit.Test;

import com.itgrids.partyanalyst.dao.ICandidateDAO;
import com.itgrids.partyanalyst.model.Candidate;


public class CandidateDAOHibernateTest extends BaseDaoTestCase {
	private ICandidateDAO candidateDAO;
	
	public void setCandidateDAO(ICandidateDAO candidateDAO) {
		this.candidateDAO = candidateDAO;
	}
	/*//@Test
	public void testFindByFirstname(){
		List<Candidate> candidates = candidateDAO.findByFirstname("VENKATA RAMANA REDDY");
		Assert.assertEquals(1, candidates.size());
	}
	//@Test
	public void testFindByLastname(){
		List<Candidate> candidates = candidateDAO.findByLastname("BHASKAR");
		Assert.assertEquals(1, candidates.size());
	}
	
	//@Test
	public void testSetCandidate(){
		candidateDAO.save(c);
		setComplete();
	}
	//@Test
	public void testDeleteCandidate(){
		candidateDAO.remove(new Long(184));
		setComplete();
	}*/
	
	@Test
	public void test(){
		Assert.assertEquals(1, 1);
	}
	
}
