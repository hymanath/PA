package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IProblemFileDAO;

public class ProblemFileDAOHibernateTest extends BaseDaoTestCase{
	private IProblemFileDAO problemFileDAO;

	

	public void setProblemFileDAO(IProblemFileDAO problemFileDAO) {
		this.problemFileDAO = problemFileDAO;
	}
	
	/*public void test()
	{
		problemFileDAO.getAll();
		
	}*/
	/*public void testGetAllNonApprovedImagesBetweenDatesWithCompleteData(){
		
		List<Object[]> result = problemFileDAO.getAllNonApprovedImagesBetweenDatesWithCompleteData();
		System.out.println(result.size());
		}*/
	
	public void testGetAllNonApprovedFilesAndProblemDetails()
	{
		List<Object[]> list = problemFileDAO.getAllNonApprovedFilesAndProblemDetails();
		
		System.out.println(list.size());
		
		for(Object[] params : list)
		{
			System.out.println();
			for(Object obj: params)
				System.out.print("----"+obj.toString());
		}
	}
}
