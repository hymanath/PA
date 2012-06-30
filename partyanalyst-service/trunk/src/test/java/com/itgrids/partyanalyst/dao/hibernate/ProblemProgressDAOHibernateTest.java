package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;
import com.itgrids.partyanalyst.dao.IProblemProgressDAO;

public class ProblemProgressDAOHibernateTest extends BaseDaoTestCase{

	private IProblemProgressDAO problemProgressDAO;

	public void setProblemProgressDAO(IProblemProgressDAO problemProgressDAO) {
		this.problemProgressDAO = problemProgressDAO;
	}
	
	public void test()
	{
		problemProgressDAO.getAll();
	}
}
