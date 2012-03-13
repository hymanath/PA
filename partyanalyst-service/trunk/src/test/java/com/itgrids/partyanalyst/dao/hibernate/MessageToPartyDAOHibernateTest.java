package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IMessageToPartyDAO;
import com.itgrids.partyanalyst.service.impl.DateService;

public class MessageToPartyDAOHibernateTest extends BaseDaoTestCase{
	
	private IMessageToPartyDAO messageToPartyDAO;

	public void setMessageToPartyDAO(IMessageToPartyDAO messageToPartyDAO) {
		this.messageToPartyDAO = messageToPartyDAO;
	}
	/*public void test()
	{
		messageToPartyDAO.getAll();
	}*/
	/* public void testGetAllOpenedComments()
	{
		 
		System.out.println();
		List list = messageToPartyDAO.getAllPartyMessages(DateService.convertStringToDate("2012-03-05", "yyyy-MM-dd"),
				DateService.convertStringToDate("2012-03-05", "yyyy-MM-dd"),"Rejected");
		System.out.println(list.size());
	}*/
	public void testadminModifiedMessages()
	{
		Integer result =messageToPartyDAO.adminModifiedMessages(10l,"suri messaged is updated","true"); 
		System.out.println(result);
	}



}
