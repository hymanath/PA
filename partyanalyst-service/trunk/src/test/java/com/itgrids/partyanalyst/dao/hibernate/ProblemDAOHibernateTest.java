package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IProblemDAO;

public class ProblemDAOHibernateTest extends BaseDaoTestCase{

	private IProblemDAO problemDAO;

	public void setProblemDAO(IProblemDAO problemDAO) {
		this.problemDAO = problemDAO;
	}
	
	public void test()
	{
		problemDAO.getAll();
	}
}
