package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IMessageToCandidateDAO;

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
}
