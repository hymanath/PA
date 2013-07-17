package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;
import com.itgrids.partyanalyst.dao.IVotingTrendzDAO;

public class VotingTrendzDAOHibernateTest extends BaseDaoTestCase{

	private IVotingTrendzDAO votingTrendzDAO;

	public void setVotingTrendzDAO(IVotingTrendzDAO votingTrendzDAO) {
		this.votingTrendzDAO = votingTrendzDAO;
	}
	
	public void test()
	{
		votingTrendzDAO.getAll();
	}
}
