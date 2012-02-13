package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IElectionMinistersDAO;

public class ElectionMinistersDAOHibernateTest extends BaseDaoTestCase{

	private IElectionMinistersDAO electionMinistersDAO;

	public void setElectionMinistersDAO(IElectionMinistersDAO electionMinistersDAO) {
		this.electionMinistersDAO = electionMinistersDAO;
	}
	
	public void test()
	{
		electionMinistersDAO.getAll();
	}
}
