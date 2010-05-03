package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

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
	
	
	/*public void test(){
		long startTime = System.currentTimeMillis();
		List<Candidate> candis = candidateDAO.getAll();
		for(Candidate can:candis){
			if(can.getLastname().contains("\n")){
				can.setLastname(can.getLastname().replace("\n", " "));
				candidateDAO.save(can);
			}
		}
		setComplete();
		long endTime = System.currentTimeMillis();
		System.out.println((endTime-startTime)/1000);
	}*/
	
	public void testfindByCandidateLastName(){
		Candidate obj = candidateDAO.findCandidateByLastName("Lachigari Venkataramaan");
		System.out.println(obj);
	}
	
}
