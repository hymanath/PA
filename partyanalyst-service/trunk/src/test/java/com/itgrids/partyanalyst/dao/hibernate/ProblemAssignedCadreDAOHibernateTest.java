package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;
import com.itgrids.partyanalyst.dao.IProblemAssignedCadreDAO;
import com.itgrids.partyanalyst.model.Cadre;
import com.itgrids.partyanalyst.model.UserProblem;

public class ProblemAssignedCadreDAOHibernateTest extends BaseDaoTestCase{

	private IProblemAssignedCadreDAO problemAssignedCadreDAO;

	public void setProblemAssignedCadreDAO(
			IProblemAssignedCadreDAO problemAssignedCadreDAO) {
		this.problemAssignedCadreDAO = problemAssignedCadreDAO;
	}
	
	/*public void test()
	{
		problemAssignedCadreDAO.getAll();
	}*/
	
	/*public void testgetAssignedCadreProblemsInARegion()
	{
		Long userId = 1l;
		String locationStr = " and model.userProblem.problem.problemCompleteLocation.state.stateId = 10l";
		List<UserProblem> userproblem = problemAssignedCadreDAO.getAssignedCadreProblemsInARegion(userId, locationStr);
	}*/
	/*public void testgetAssignedCadreProblemsCountForAnUser()
	{
		List<Object> value = problemAssignedCadreDAO.getAssignedCadreProblemsCountForAnUser(1l);
		System.out.println(value.size());
	}*/
	
	/*public void testgetAssignedCadreProblemsCountInARegion()
	{
		String locationStr = "and model.userProblem.problem.problemCompleteLocation.state.stateId = 10l";
		List<Object> value = problemAssignedCadreDAO.getAssignedCadreProblemsCountInARegion(1l,locationStr);
		System.out.println(value.size());
	}
*/
	/*public void testgetAssignedCadreProblemsForAnUser()
		{
	List<UserProblem> user = problemAssignedCadreDAO.getAssignedCadreProblemsForAnUser(1l);
	for(UserProblem problemDetails  : user)
	{
		System.out.println(problemDetails.getProblem().getTitle());
	}
		}*/
	
	/*public void testgetCadreForCadreProblemsInARegion()
	{
		String locationStr = "and model.userProblem.problem.problemCompleteLocation.state.stateId = 1";
		List<Cadre> list = problemAssignedCadreDAO.getCadreForCadreProblemsInARegion(1l,locationStr);
		for(Cadre params : list)
		{
			System.out.println(params.getFirstName());
		}
	}*/
	
	/*public void testgetCadreForCadreProblemsForAnUser()
	{
		List<Cadre> list = problemAssignedCadreDAO.getCadreForCadreProblemsForAnUser(1l);
		for(Cadre params : list)
		{
			System.out.println(params.getFirstName());
		}
	}*/
	
}
