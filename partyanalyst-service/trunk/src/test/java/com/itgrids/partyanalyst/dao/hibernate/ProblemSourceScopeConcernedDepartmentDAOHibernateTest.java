package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IProblemSourceScopeConcernedDepartmentDAO;
import com.itgrids.partyanalyst.model.ProblemSourceScopeConcernedDepartment;

public class ProblemSourceScopeConcernedDepartmentDAOHibernateTest extends BaseDaoTestCase{

	private IProblemSourceScopeConcernedDepartmentDAO problemSourceScopeConcernedDepartmentDAO;

	public IProblemSourceScopeConcernedDepartmentDAO getProblemSourceScopeConcernedDepartmentDAO() {
		return problemSourceScopeConcernedDepartmentDAO;
	}

	public void setProblemSourceScopeConcernedDepartmentDAO(
			IProblemSourceScopeConcernedDepartmentDAO problemSourceScopeConcernedDepartmentDAO) {
		this.problemSourceScopeConcernedDepartmentDAO = problemSourceScopeConcernedDepartmentDAO;
	}
	
	/*public void testGetAll(){
		List<ProblemSourceScopeConcernedDepartment> list = problemSourceScopeConcernedDepartmentDAO.getAll();
		assertEquals(1, list.size());
	}*/
	
	/*public void testGetDepartmentsForScope(){
		List<ProblemSourceScopeConcernedDepartment> list = problemSourceScopeConcernedDepartmentDAO.findByProblemScopeId(new Long(2));
		assertEquals(1, list.size());
	}*/
	/*
	public void testFindByDepartmentAndScope(){
		List<ProblemSourceScopeConcernedDepartment> list = problemSourceScopeConcernedDepartmentDAO.findByDepartmentAndScope("Panchayath", "Village");
		assertEquals(1, list.size());
	}*/
	public void testFindDepartmentsByScope()
	{
		List<ProblemSourceScopeConcernedDepartment> list = problemSourceScopeConcernedDepartmentDAO.findDepartmentsByScope("VILLAGE");
		assertEquals(1, list.size());
		for( ProblemSourceScopeConcernedDepartment problemSourceScopeConcernedDepartment: list)
		{
			System.out.println(problemSourceScopeConcernedDepartment.getDepartment());	
		}
	}
}
