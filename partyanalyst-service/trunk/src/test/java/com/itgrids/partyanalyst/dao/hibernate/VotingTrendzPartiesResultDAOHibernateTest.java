package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IVotingTrendzPartiesResultDAO;

public class VotingTrendzPartiesResultDAOHibernateTest extends BaseDaoTestCase{

	private IVotingTrendzPartiesResultDAO votingTrendzPartiesResultDAO;

	public void setVotingTrendzPartiesResultDAO(
			IVotingTrendzPartiesResultDAO votingTrendzPartiesResultDAO) {
		this.votingTrendzPartiesResultDAO = votingTrendzPartiesResultDAO;
	}
	
	/*public void test()
	{
		votingTrendzPartiesResultDAO.getAll();
	}*/
	
	public void testgetVotingTrendzPartiesResultList()
	{
		votingTrendzPartiesResultDAO.getVotingTrendzPartiesResultList();
	}
}
