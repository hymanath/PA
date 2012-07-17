package com.itgrids.partyanalyst.dao.hibernate;

import java.text.DecimalFormat;
import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IProblemRatingDAO;

public class ProblemRatingDAOHibernateTest extends BaseDaoTestCase{

	private IProblemRatingDAO problemRatingDAO;

	public void setProblemRatingDAO(IProblemRatingDAO problemRatingDAO) {
		this.problemRatingDAO = problemRatingDAO;
	}
	
	/*public void test()
	{
		problemRatingDAO.getAll();
	}*/
	
	/*public void testGetAverageRatingOfAProblem()
	{
		Double result = problemRatingDAO.getAverageRatingOfAProblem(17l);
		System.out.println(result);
		DecimalFormat resString = new DecimalFormat("#.##");
		double twoDecimal =  Double.valueOf(resString.format(result));
		System.out.println(twoDecimal);
	}*/
	
	public void testGetRatingWiseCountOfAProblem()
	{
		 List<Object[]> result = problemRatingDAO.getRatingWiseCountOfAProblem(17l);
		 for(Object[] paraDouble : result)
		 {
			 System.out.println(paraDouble[0] +" -- "+paraDouble[1]);
		 }
		
	}
}
