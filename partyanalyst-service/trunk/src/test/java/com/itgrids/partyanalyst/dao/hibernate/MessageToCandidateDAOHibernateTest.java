package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IMessageToCandidateDAO;
import com.itgrids.partyanalyst.service.impl.DateService;


public class MessageToCandidateDAOHibernateTest extends BaseDaoTestCase {
	private IMessageToCandidateDAO messageToCandidateDAO;

	public void setMessageToCandidateDAO(
			IMessageToCandidateDAO messageToCandidateDAO) {
		this.messageToCandidateDAO = messageToCandidateDAO;
	}
	
    public void test()
	{
		messageToCandidateDAO.getAll();
		
	}
   /*public void testGetAllOpenedComments()
	{
		System.out.println();
		List list = messageToCandidateDAO.getAllOpenedMessages(DateService.convertStringToDate("2010-12-13", "yyyy-MM-dd"),
				DateService.convertStringToDate("2012-2-7", "yyyy-MM-dd"),"Approved");
		System.out.println(list.size());
	}
  */
		
	
	/*public void testControlMessages()
	{
		Integer result = messageToCandidateDAO.controlMessages(13L, "gk", "true");
		System.out.println(result);
		
	}*/
    
	/*public void testgetUserMessages()
	{
       List<Object[]> list = messageToCandidateDAO.getUserMessages(13889l);
		
		System.out.println(list.size());
		
		for(Object[] params : list)
		{
			System.out.println(params.toString());
		}
	}*/
}
