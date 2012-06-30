package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.ICadreProblemsDAO;

public class CadreProblemsDAOHibernateTest extends BaseDaoTestCase{

	private ICadreProblemsDAO cadreProblemsDAO;

	public void setCadreProblemsDAO(ICadreProblemsDAO cadreProblemsDAO) {
		this.cadreProblemsDAO = cadreProblemsDAO;
	}
	
	public void test()
	{
		cadreProblemsDAO.getAll();
	}
}
