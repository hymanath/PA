package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.ICandidateDAO;

public class CandidateDAOHibernateTest extends BaseDaoTestCase{
	
	private ICandidateDAO candidateDAO;

	public void setCandidateDAO(ICandidateDAO candidateDAO) {
		this.candidateDAO = candidateDAO;
	}
	
	/*public void test()
	{
	  candidateDAO.getAll();
	}*/
	
/*	public void testgetCandidateListByPartyId()
	{
	 List<Object[]> list = candidateDAO.getCandidateListByPartyId(872L);
	 System.out.println(list.size());
	 
	}*/

	public void testgetFilteredCandidateListByName()
	{
		
		List<Object[]> list = candidateDAO.getFilteredCandidateListByName(872L,2L,7L,"District","ya");
		System.out.println(list.size());
		for(Object[] params:list)
		 System.out.println(params[0]+" "+params[1]);
	}
}
