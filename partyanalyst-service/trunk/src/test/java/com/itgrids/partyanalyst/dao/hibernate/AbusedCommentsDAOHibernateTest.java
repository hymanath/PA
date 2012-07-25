package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IAbusedCommentsDAO;
import com.itgrids.partyanalyst.service.impl.DateService;

public class AbusedCommentsDAOHibernateTest extends BaseDaoTestCase{
	
	private IAbusedCommentsDAO abusedCommentsDAO;

	

	    public void setAbusedCommentsDAO(IAbusedCommentsDAO abusedCommentsDAO) {
		this.abusedCommentsDAO = abusedCommentsDAO;
	}



		/*public void test()
		{
	    	abusedCommentsDAO.getAll();
			
		}*/
	   /* public void testGetAllAbuseComment()
		{
			System.out.println();
			List list = abusedCommentsDAO.getAllAbuseComment(
					DateService.convertStringToDate("2012-7-23", "yyyy-MM-dd"),DateService.convertStringToDate("2012-7-23", "yyyy-MM-dd"),"NEW");
			System.out.println(list.size());
		}*/
	    public void testControlAbuseComments()
		{
			System.out.println();
			Integer list = abusedCommentsDAO.controlAbuseComments(3l,"true","false");
			System.out.println(list);
		}

}
