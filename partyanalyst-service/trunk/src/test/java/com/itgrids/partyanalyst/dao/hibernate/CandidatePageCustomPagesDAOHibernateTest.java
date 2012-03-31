package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.ICandidatePageCustomPagesDAO;

public class CandidatePageCustomPagesDAOHibernateTest extends BaseDaoTestCase{
	
	private ICandidatePageCustomPagesDAO candidatePageCustomPagesDAO;

	public void setCandidatePageCustomPagesDAO(
			ICandidatePageCustomPagesDAO candidatePageCustomPagesDAO) {
		this.candidatePageCustomPagesDAO = candidatePageCustomPagesDAO;
	}
	
	/*public void test()
	{
		candidatePageCustomPagesDAO.getAll();
	}*/
	
	public void testGetCustomPagesOfACandidatePage()
	{
		List<Object[]> list = candidatePageCustomPagesDAO.getCustomPagesOfACandidatePage(900l);
		System.out.println(list.size());
	}

}
