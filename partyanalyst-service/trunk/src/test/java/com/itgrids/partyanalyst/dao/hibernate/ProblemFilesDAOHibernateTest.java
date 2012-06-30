package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IProblemFilesDAO;

public class ProblemFilesDAOHibernateTest extends BaseDaoTestCase{

	private IProblemFilesDAO problemFilesDAO;

	public void setProblemFilesDAO(IProblemFilesDAO problemFilesDAO) {
		this.problemFilesDAO = problemFilesDAO;
	}
	
	public void test()
	{
		problemFilesDAO.getAll();
	}
}
