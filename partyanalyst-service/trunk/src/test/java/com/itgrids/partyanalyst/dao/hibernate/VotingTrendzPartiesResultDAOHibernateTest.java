package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

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
	
	/*public void testgetVotingTrendzPartiesResultList()
	{
		votingTrendzPartiesResultDAO.getVotingTrendzPartiesResultList(232l);
	}*/
	
	public void testGetpartiesListForVotingTrendz()
	{
		List<Object[]> list = votingTrendzPartiesResultDAO.getpartiesListForVotingTrendz(221l);
		System.out.println(list.size());
		for(Object[] params : list)
			System.out.println(params[0]+" -- "+params[1]);
	}
}
