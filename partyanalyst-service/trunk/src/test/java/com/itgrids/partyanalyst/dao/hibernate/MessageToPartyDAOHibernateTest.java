package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IMessageToPartyDAO;

public class MessageToPartyDAOHibernateTest extends BaseDaoTestCase{
	
	private IMessageToPartyDAO messageToPartyDAO;

	public void setMessageToPartyDAO(IMessageToPartyDAO messageToPartyDAO) {
		this.messageToPartyDAO = messageToPartyDAO;
	}
	public void test()
	{
		messageToPartyDAO.getAll();
	}


}
