package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IProblemAssignedDepartmentDAO;

public class ProblemAssignedDepartmentDAOHibernateTest extends BaseDaoTestCase{
	
	private IProblemAssignedDepartmentDAO problemAssignedDepartmentDAO;

	public void setProblemAssignedDepartmentDAO(
			IProblemAssignedDepartmentDAO problemAssignedDepartmentDAO) {
		this.problemAssignedDepartmentDAO = problemAssignedDepartmentDAO;
	}
	
	public void test()
	{
		problemAssignedDepartmentDAO.getAll();
	}

}
