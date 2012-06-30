package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IProblemCommentsDAO;

public class ProblemCommentsDAOHibernateTest extends BaseDaoTestCase{

	private IProblemCommentsDAO problemCommentsDAO;

	public void setProblemCommentsDAO(IProblemCommentsDAO problemCommentsDAO) {
		this.problemCommentsDAO = problemCommentsDAO;
	}
	
	public void test()
	{
		problemCommentsDAO.getAll();
	}
}
