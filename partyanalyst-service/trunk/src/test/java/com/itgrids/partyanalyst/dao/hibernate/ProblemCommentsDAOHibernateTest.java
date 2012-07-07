package com.itgrids.partyanalyst.dao.hibernate;

import java.text.SimpleDateFormat;
import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IProblemCommentsDAO;
import com.itgrids.partyanalyst.utils.IConstants;

public class ProblemCommentsDAOHibernateTest extends BaseDaoTestCase{

	private IProblemCommentsDAO problemCommentsDAO;

	public void setProblemCommentsDAO(IProblemCommentsDAO problemCommentsDAO) {
		this.problemCommentsDAO = problemCommentsDAO;
	}
	
	/*public void test()
	{
		problemCommentsDAO.getAll();
	}*/
	
	
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
	}

}
