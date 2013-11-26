package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.ICandidatePartyFileDAO;

public class CandidatePartyFileDAOHibernateTest extends BaseDaoTestCase{

	private ICandidatePartyFileDAO candidatePartyFileDAO;

	public void setCandidatePartyFileDAO(
			ICandidatePartyFileDAO candidatePartyFileDAO) {
		this.candidatePartyFileDAO = candidatePartyFileDAO;
	}
	
	/*public void test()
	{
	  candidatePartyFileDAO.getAll();
	}*/
	
	public void testgetSourceCandidates()
	{
		List<Object[]> values = candidatePartyFileDAO.getSourceCandidates();
		System.out.println("source");
		for (Object[] objects : values) {
			System.out.println(objects[0] +":"+ objects[1] +":"+objects[2]);
		}
	}
	
	public void testgetDestinationCandidates()
	{
		List<Object[]> values = candidatePartyFileDAO.getDestinationCandidates();
		System.out.println("destination");
		for (Object[] objects : values) {
			System.out.println(objects[0] +":"+ objects[1] +":"+objects[2]);
		}
	}
	
	
}
