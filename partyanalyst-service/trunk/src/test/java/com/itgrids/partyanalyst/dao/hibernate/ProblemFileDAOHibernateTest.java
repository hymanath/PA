package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IProblemFileDAO;

public class ProblemFileDAOHibernateTest extends BaseDaoTestCase{
	private IProblemFileDAO problemFileDAO;

	

	public void setProblemFileDAO(IProblemFileDAO problemFileDAO) {
		this.problemFileDAO = problemFileDAO;
	}
	
	public void test()
	{
		problemFileDAO.getAll();
		
	}
}
