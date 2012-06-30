package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IClassifiedProblemsDAO;

public class ClassifiedProblemsDAOHibernateTest extends BaseDaoTestCase{

	private IClassifiedProblemsDAO classifiedProblemsDAO;

	public void setClassifiedProblemsDAO(
			IClassifiedProblemsDAO classifiedProblemsDAO) {
		this.classifiedProblemsDAO = classifiedProblemsDAO;
	}
	
	public void test()
	{
		classifiedProblemsDAO.getAll();
	}
}
