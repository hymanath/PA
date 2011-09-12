package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IProblemTypeDAO;

public class ProblemTypeDAOHibernateTest extends BaseDaoTestCase{
	
	private IProblemTypeDAO problemTypeDAO;

	public void setProblemTypeDAO(IProblemTypeDAO problemTypeDAO) {
		this.problemTypeDAO = problemTypeDAO;
	}
	
	public void test()
	{
		problemTypeDAO.getAll();
	}
}
