package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;
import com.itgrids.partyanalyst.dao.IProblemAssignedCadreDAO;

public class ProblemAssignedCadreDAOHibernateTest extends BaseDaoTestCase{

	private IProblemAssignedCadreDAO problemAssignedCadreDAO;

	public void setProblemAssignedCadreDAO(
			IProblemAssignedCadreDAO problemAssignedCadreDAO) {
		this.problemAssignedCadreDAO = problemAssignedCadreDAO;
	}
	
	public void test()
	{
		problemAssignedCadreDAO.getAll();
	}
}
