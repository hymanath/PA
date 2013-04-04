package com.itgrids.partyanalyst.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IProblemCommentsDAO;

public class ProblemCommentsDAOHibernateTest extends BaseDaoTestCase{

	private IProblemCommentsDAO problemCommentsDAO;

	public void setProblemCommentsDAO(IProblemCommentsDAO problemCommentsDAO) {
		this.problemCommentsDAO = problemCommentsDAO;
	}
	
	/*public void test()
	{
		problemCommentsDAO.getAll();
	}*/
	
	/*
	public void testfindUserApprovalStatusbetweendates()throws Exception
	{  
		SimpleDateFormat sdf = new SimpleDateFormat(IConstants.DATE_PATTERN_YYYY_MM_DD);
					
		System.out.println("details");
		List<Object[]> list = problemCommentsDAO. findUserApprovalStatusbetweendates(sdf.parse("2012/6/30"),
				sdf.parse("2012/6/30"));				
			System.out.println("size"+list.size());
		for(Object[] obj : list)
		{
			System.out.println("1"+obj[0]);
			System.out.println("2"+obj[1]);
			System.out.println("3"+obj[2]);
			System.out.println("4"+obj[3]);
			//System.out.println("5"+obj[4]);		
			
		}
	}*/
	/*public void testgetProblemComments()
	{
		List<Object[]> list = problemCommentsDAO.getProblemComments(30l);
		
		for(Object[] params : list)
		{
			System.out.println(params[0].toString());
		}
	}
	public void testGetCountOfNewlyPostedProblemCommentsByUser()
	{
		Long newlyPostedProbCOmmentsCount = problemCommentsDAO.getCountOfNewlyPostedProblemCommentsByUser();
		System.out.println(newlyPostedProbCOmmentsCount);
	}*/
	public void testGetAllProblemComments(){
		List<Long> userIds = new ArrayList<Long>();
		userIds.add(3l);
		List<Object[]> data = problemCommentsDAO.getAllProblemComments(122l,1l, userIds);
		System.out.println(data.size());
		for(Object[] params : data)
		{
			System.out.println(params[0].toString());
		}
	}

}
