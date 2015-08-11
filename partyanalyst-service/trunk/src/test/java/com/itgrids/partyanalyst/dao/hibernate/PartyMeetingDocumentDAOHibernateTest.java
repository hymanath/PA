package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IPartyMeetingDocumentDAO;

public class PartyMeetingDocumentDAOHibernateTest extends BaseDaoTestCase{

	private IPartyMeetingDocumentDAO partyMeetingDocumentDAO;

	public void setPartyMeetingDocumentDAO(
			IPartyMeetingDocumentDAO partyMeetingDocumentDAO) {
		this.partyMeetingDocumentDAO = partyMeetingDocumentDAO;
	}
	
	public void test()
	{
		partyMeetingDocumentDAO.getAll();
	}
}
