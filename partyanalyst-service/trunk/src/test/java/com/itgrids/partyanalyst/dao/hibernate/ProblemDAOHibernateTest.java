package com.itgrids.partyanalyst.dao.hibernate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IProblemDAO;
import com.itgrids.partyanalyst.utils.IConstants;

public class ProblemDAOHibernateTest extends BaseDaoTestCase{

	private IProblemDAO problemDAO;

	public void setProblemDAO(IProblemDAO problemDAO) {
		this.problemDAO = problemDAO;
	}
	
	/*public void test()
	{
		problemDAO.getAll();
	}*/
	
	/*public void testDeleteProblem()
	{
		List<Long> problemIds = new ArrayList<Long>();
		problemIds.add(1l);
		
		Integer result = problemDAO.deleteProblem(problemIds,new Date());
		if(result != 0)
			System.out.println(result);
	}*/
	
	/*public void testDeleteProblemDetails()
	{
		Integer result = problemDAO.deleteProblemDetails(1l, new Date());
		if(result != 0)
			System.out.println(result);
	}*/
	
	public void testGetCountOfNewlyPostedProblemsByFreeUser()
	{
		Long newlyPostedProbCount = problemDAO.getCountOfNewlyPostedProblemsByFreeUser();
		System.out.println(newlyPostedProbCount);
	}
}
