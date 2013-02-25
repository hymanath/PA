package com.itgrids.partyanalyst.dao.hibernate;

import java.text.SimpleDateFormat;
import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IProblemFilesDAO;
import com.itgrids.partyanalyst.utils.IConstants;

public class ProblemFilesDAOHibernateTest extends BaseDaoTestCase{

	private IProblemFilesDAO problemFilesDAO;

	public void setProblemFilesDAO(IProblemFilesDAO problemFilesDAO) {
		this.problemFilesDAO = problemFilesDAO;
	}
	
	/*public void test()
	{
		problemFilesDAO.getAll();
	}
	*/
	
	/*public void testfindUserApprovalStatusbetweendates()throws Exception
	{  
		SimpleDateFormat sdf = new SimpleDateFormat(IConstants.DATE_PATTERN_YYYY_MM_DD);
					
		System.out.println("details");
		List<Object[]> list = problemFilesDAO. getCurrentDateFiles(null,null,"false");				
			System.out.println("size"+list.size());
		for(Object[] obj : list)
		{
			System.out.println("1"+obj[0]);
			System.out.println("2"+obj[1]);
			System.out.println("3"+obj[2]);
			System.out.println("4"+obj[3]);
			System.out.println("5"+obj[4]);
			System.out.println("6"+obj[5]);
			System.out.println("7"+obj[6]);
			System.out.println("8"+obj[7]);
			System.out.println("9"+obj[8]);		
			
		}
	}*/
	
	/*public void testGetCountOfNewlyPostedImagesByFreeUser()
	{
		Long newlyPostedImagesCount = problemFilesDAO.getCountOfNewlyPostedImagesByFreeUser();
		System.out.println(newlyPostedImagesCount);
	}*/
	
	public void testgetProblemFileDetailsByProblemFileId()
	{
		List<Object[]> list = problemFilesDAO.getProblemFileDetailsByProblemFileId(35l);
		if(list != null && list.size() > 0)
		{
			for(Object[] params : list)
			{
				System.out.println(params[0]+" "+params[1]+" "+params[2]+" "+params[3]);
			}
		}
	}
}
