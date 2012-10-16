package com.itgrids.partyanalyst.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IProblemAssignedDepartmentDAO;
import com.itgrids.partyanalyst.model.UserProblem;

public class ProblemAssignedDepartmentDAOHibernateTest extends BaseDaoTestCase{
	
	private IProblemAssignedDepartmentDAO problemAssignedDepartmentDAO;

	public void setProblemAssignedDepartmentDAO(
			IProblemAssignedDepartmentDAO problemAssignedDepartmentDAO) {
		this.problemAssignedDepartmentDAO = problemAssignedDepartmentDAO;
	}
	
	/*public void test()
	{
		problemAssignedDepartmentDAO.getAll();
	}*/

	
	/*public void testgetAssignedProblemsProgressByLocation()
	{
		List<Object[]> list = problemAssignedDepartmentDAO.getAssignedProblemsProgressByLocation(23l);
		System.out.println(list.size());
		
		for(Object[] params : list)
		{
			System.out.println(params[0].toString());
		}
	}*/
	
	/*public void testgetDepartmentWiseProblemsBasedOnStatus()
	{
		String status = "Total";
		List<UserProblem> list = problemAssignedDepartmentDAO.getDepartmentWiseProblemsBasedOnStatus(1l, 1l,"and model.userProblem.problem.problemStatus.status = 'Total' ");
		for(UserProblem user : list)
		{
			System.out.println(user.getProblem().getDescription());
		}
	}*/
	
	/*public void testgetDepartmentWiseProblemStatus()
	{
		List<Object> list = problemAssignedDepartmentDAO.getDepartmentWiseProblemStatus(1l, 1l);
		System.out.println(list.size());
		System.out.println(list);
	}*/
	
	/*public void testgetDeptWiseAssignedProblemProgressIds()
	{
		List<Long> value = problemAssignedDepartmentDAO.getDeptWiseAssignedProblemProgressIds(1l, "and model.departmentLocation.state.stateId = 10");
		System.out.println(value);
	}*/
	
	/*public void testgetProblemsStatusBasedOnAssignedProblemProgressId()
	{
		List<Long> problemIds = new ArrayList<Long>();
		problemIds.add(23l);
		List<Object[]> list = problemAssignedDepartmentDAO.getProblemsStatusBasedOnAssignedProblemProgressId(1l, problemIds);
		System.out.println(list.size());
	}*/
	
	/*public void testgetProblemsBasedOnAssignedProblemProgressIdAndStatus()
	{
		
		
		Long userId=1l;
		List<Long> progressIdList = new ArrayList<Long>();
		progressIdList.add(23l);
		String deptStr="and model.departmentOrganisation.departmentOrganisationId = 1";
		String statusStr="and model.userProblem.problem.problemStatus.status = 'New'";
		List<UserProblem> list = problemAssignedDepartmentDAO.getProblemsBasedOnAssignedProblemProgressIdAndStatus(userId,progressIdList,deptStr,statusStr);
		for(UserProblem user : list)
		{
			System.out.println(user.getProblem().getTitle());
		}
	}*/
	
}
