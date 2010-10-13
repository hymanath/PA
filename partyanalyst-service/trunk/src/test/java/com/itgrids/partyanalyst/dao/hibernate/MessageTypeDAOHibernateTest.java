package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IMessageTypeDAO;

public class MessageTypeDAOHibernateTest extends BaseDaoTestCase {

	private IMessageTypeDAO messageTypeDAO;

	public IMessageTypeDAO getMessageTypeDAO() {
		return messageTypeDAO;
	}

	public void setMessageTypeDAO(IMessageTypeDAO messageTypeDAO) {
		this.messageTypeDAO = messageTypeDAO;
	}
	
	public void test(){
		messageTypeDAO.getAll();
	}
}
